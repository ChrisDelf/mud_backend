package com.dev.mud_backend.services;

import com.dev.mud_backend.models.Player;

public interface playerService {
    int playerDamaged(int damage, Player player);

    int playerHealed(int heal_amount, Player player);


}
