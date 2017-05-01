package com.trp.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PlayerRepository extends JpaRepository<Player,Long> {
    List<Player> findById(int id);


}
