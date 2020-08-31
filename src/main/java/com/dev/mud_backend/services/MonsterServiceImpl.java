package com.dev.mud_backend.services;

import com.dev.mud_backend.models.Monster;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service(value = "monsterService")
public class MonsterServiceImpl implements MonsterService  {
    @Override
    public Monster MonsterStats(long id) {
        return null;
    }

    @Override
    public long MonsterAttack(long monsterId, long playerId) {
        return 0;
    }

    @Override
    public long MonsterHeal(long monsterId) {
        return 0;
    }

    @Override
    public String MonsterDeath(long monsterId) {
        return null;
    }

    @Override
    public ArrayList<Monster> GenerateMonsters(int monsterLimit) {
        return null;
    }
}
