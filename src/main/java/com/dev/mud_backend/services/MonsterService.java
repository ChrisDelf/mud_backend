package com.dev.mud_backend.services;

import com.dev.mud_backend.models.Monster;

public interface MonsterService {

    Monster MonsterStats(long id);

    long MonsterAttack(long monsterId, long playerId);

    long MonsterHeal(long monsterId);

    String MonsterDeath(long monsterId);

}