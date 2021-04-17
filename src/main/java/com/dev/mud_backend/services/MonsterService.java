package com.dev.mud_backend.services;

import com.dev.mud_backend.models.Monster;

import java.util.ArrayList;
import java.util.List;

public interface MonsterService {




    Monster updateMonster(Monster monster, long id);

    String MonsterDeath(long monsterId);

    ArrayList<Monster> GenerateMonsters(int monsterLimit);

    Monster findById(long monster_id);

}
