package com.dev.mud_backend.services;

import com.dev.mud_backend.models.Item;
import com.dev.mud_backend.models.Player;

public interface PlayerService {
    long playerDamaged(int damage, Player player);

    long playerHealed(int heal_amount, Player player);

    long playerAttack(long player_id, String weapon, long monster_id);

    Player findById(long id);


}
