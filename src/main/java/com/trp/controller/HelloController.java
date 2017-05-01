package com.trp.controller;

import com.trp.domain.Player;
import com.trp.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class HelloController {

	@Autowired
	protected PlayerService playerService;

	private static final String PLAYER_FORM = "player";
	private static final String EDIT_FORM = "edit";
	private static final String ENTRIES_KEY = "players";
	private static final String FORM_KEY = "form";

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String displayPlayers( Model model ) {

		model.addAttribute(ENTRIES_KEY, playerService.findAll());
		model.addAttribute(FORM_KEY, new Player());

		return PLAYER_FORM;
	}

	@RequestMapping(value = "/remove")
	public String deletePlayer( Model model, @RequestParam String id ) {
		int pId = Integer.parseInt(id);

		playerService.delete(pId);

		return "redirect:/";
	}

	@RequestMapping(value = "/edit")
	public String findPlayer( Model model, @RequestParam String id ) {

		int pId = Integer.parseInt(id);
		model.addAttribute(ENTRIES_KEY, playerService.find(pId));
		model.addAttribute(FORM_KEY, new Player());

		return EDIT_FORM;
	}

	@RequestMapping(value = "/edit/update")
	public String editPlayer(
			Model model,
			@Valid @ModelAttribute(FORM_KEY) Player form,
			BindingResult bindingResult ) {

		if ( bindingResult.hasErrors() ) {
			model.addAttribute(ENTRIES_KEY, playerService.find(form.getId()));
			return EDIT_FORM;
		}
		System.out.println("debug " + form);
		playerService.update(form);

		return "redirect:/";
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String addPlayer(
			Model model,
			@Valid @ModelAttribute(FORM_KEY) Player form,
			BindingResult bindingResult ) {

		if ( bindingResult.hasErrors() ) {
			model.addAttribute(ENTRIES_KEY, playerService.findAll());
			return PLAYER_FORM;
		}

		playerService.save(form);

		return "redirect:/";
	}
}