package com.dev.mud_backend.services;

import com.dev.mud_backend.models.Cell;
import com.dev.mud_backend.models.PlacedRooms;
import com.dev.mud_backend.models.Room;
import org.springframework.stereotype.Service;

import java.util.ArrayList;



public interface DungeonCreatorService {

    ArrayList<ArrayList> generateGrid(int gridwidth, int gridheight, int maxrooms);

    PlacedRooms createFromSeed(ArrayList<ArrayList<Cell>> grid, Room room, int[] roomRange);

    ArrayList<ArrayList<Cell>> placedCells(ArrayList<ArrayList<Cell>> grid, Room room, String Type);


    boolean isValidRoomPlacement(ArrayList<ArrayList<Cell>> grid,Room room);




}
