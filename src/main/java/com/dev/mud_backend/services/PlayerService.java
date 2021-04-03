package com.dev.mud_backend.services;

import com.dev.mud_backend.models.Item;
import com.dev.mud_backend.models.Player;

public interface PlayerService {
    long playerDamaged(long damage, long playerid);

    long playerHealed(long heal_amount, long playerid);

    long playerAttack( long damage_num, long player_id,long monster_id);

    Player findById(long id);


}
