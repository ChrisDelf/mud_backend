package com.dev.mud_backend.repository;


import com.dev.mud_backend.models.Player;
import org.springframework.data.repository.CrudRepository;

public interface PlayerRepository extends CrudRepository<Player, Long> {

    Player findPlayer(long id);
}
