package com.dev.mud_backend.services;

import com.dev.mud_backend.models.Item;
import com.dev.mud_backend.models.Player;

public interface PlayerService {

    Player updatePlayer(Player player, long id);

    Player findById(long id);


}
