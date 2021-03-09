package com.dev.mud_backend.repository;

import com.dev.mud_backend.models.Room;
import org.springframework.data.repository.CrudRepository;

public interface RoomRepository extends CrudRepository<Room, Long> {
}
