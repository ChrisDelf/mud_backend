package com.dev.mud_backend.services;

import com.dev.mud_backend.models.Monster;
import com.dev.mud_backend.models.Room;
import com.dev.mud_backend.repository.MonsterRepository;
import com.dev.mud_backend.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service(value = "monsterService")
public class MonsterServiceImpl implements MonsterService  {
    @Autowired
    RoomRepository roomRepo;

    @Autowired
    MonsterRepository monsterRepo;


    @Override
    public ArrayList<ArrayList> MonsterStats(long m_id, long room_id) {
        ArrayList<ArrayList> return_list = new ArrayList<ArrayList>();
    return return_list;
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
