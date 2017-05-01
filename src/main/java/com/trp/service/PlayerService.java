package com.trp.service;

import com.trp.domain.Player;
import com.trp.domain.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {

    @Autowired
    protected PlayerRepository playerRepository;

    public List<Player> findAll() {
        return playerRepository.findAll();
    }

    public Player save(Player entry) {
        return playerRepository.save(entry);
    }

    public void delete(int playerId) {

        List Player = playerRepository.findById(playerId);
        if (Player != null) {
          playerRepository.delete(Player);
       }
    }
    public List<Player> find(int id) {

        return playerRepository.findById(id);
    }

    public Player update (Player entry){

        if (entry.getId() == null) {
            playerRepository.save(entry);
            return entry;
        } else {;

            return playerRepository.save(entry);
        }

    }


}
