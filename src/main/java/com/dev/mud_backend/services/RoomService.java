package com.dev.mud_backend.services;

import com.dev.mud_backend.models.Monster;
import com.dev.mud_backend.models.Room;

public interface RoomService {

    Room setMonsterLimit(Room room, int value);

    int removeFromMLimit(Room room, int value);

//    Monster getMonster(Room room, long m_id);


}
