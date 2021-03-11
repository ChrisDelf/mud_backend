package com.dev.mud_backend.services;

import com.dev.mud_backend.models.Monster;
import com.dev.mud_backend.models.Room;
import com.dev.mud_backend.repository.MonsterRepository;
import com.dev.mud_backend.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service(value = "monsterService")
public class MonsterServiceImpl implements MonsterService  {
    @Autowired
    private RoomRepository roomRepo;

    @Autowired
    private MonsterRepository monsterRepo;

    @Autowired
    private RoomService roomService;

    @Override
    public Monster MonsterStats(long m_id, long room_id) {
        // instantiate the monster that we are going pull the stats int
        Monster target_monster = new Monster();
        // first we need to find if the room exist
        Room target_room = roomRepo.findByRoomId(room_id);
        // if we don't find a room
        if (target_room == null){
            return target_monster;
        }
        target_monster = roomService.getMonster(target_room, m_id);

        return target_monster;
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
