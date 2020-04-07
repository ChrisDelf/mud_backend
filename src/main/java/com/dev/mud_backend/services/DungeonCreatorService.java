package com.dev.mud_backend.services;

import com.dev.mud_backend.models.PlacedRooms;
import com.dev.mud_backend.models.Room;
import org.springframework.stereotype.Service;

import java.util.ArrayList;



public interface DungeonCreatorService {

    ArrayList<ArrayList> generateGrid(int gridwidth, int gridheight, int maxrooms);

    PlacedRooms createFromSeed(ArrayList<ArrayList> grid, Room room, int[] roomRange);

    ArrayList<ArrayList> placedCells(ArrayList<ArrayList> grid, Room room, String Type);
}
