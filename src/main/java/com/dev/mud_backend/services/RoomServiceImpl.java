package com.dev.mud_backend.services;

import com.dev.mud_backend.exceptions.ResourceNotFoundException;
import com.dev.mud_backend.models.Cell;
import com.dev.mud_backend.models.Monster;
import com.dev.mud_backend.models.Room;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service(value = "roomService")
public class RoomServiceImpl implements RoomService{

    @Override
    public Room setMonsterLimit(Room room, int value) {
        room.setMonsterLimit(value);
        return room;
    }

    @Override
    public int removeFromMLimit(Room room, int value) {
        int monster_limit = room.getMonsterLimit();
        room.setMonsterLimit(monster_limit - value);
        return room.getMonsterLimit();
    }

//    @Override
//    public Monster getMonster(Room room, long m_id) throws ResourceNotFoundException {
//        ArrayList<Monster> monsterList = room.getMonstersList();
//
//
//
//
//        // now that we got our list of monster for that room we need to fide it
//        for (int i = 0; i < monsterList.size(); i++)
//        {
//
//            Monster t_monster = monsterList.get(i);
//            if (t_monster.getMonsterid() == m_id) {
//                Monster found_m = t_monster;
//                return found_m;
//            }
//        }
//
//        // if we don't find the monster id in the list theny we need to throw an Exception
//        throw new ResourceNotFoundException("Monster id " + m_id + " not found!");
//
//
//    }
}
