package com.dev.mud_backend.services;

import com.dev.mud_backend.models.Monster;

import java.util.ArrayList;
import java.util.List;

public interface MonsterService {

    List<ArrayList> MonsterStats(long m_id, long room_id);

    long MonsterAttack(long monsterId, long playerId);

    long MonsterHeal(long monsterId);

    String MonsterDeath(long monsterId);

    ArrayList<Monster> GenerateMonsters(int monsterLimit);

}
