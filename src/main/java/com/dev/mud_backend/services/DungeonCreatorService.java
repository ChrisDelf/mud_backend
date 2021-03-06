package com.dev.mud_backend.services;

import com.dev.mud_backend.models.Cell;
import com.dev.mud_backend.models.Map;
import com.dev.mud_backend.models.PlacedRoom;
import com.dev.mud_backend.models.Room;

import java.util.ArrayList;



public interface DungeonCreatorService {

    ArrayList<ArrayList<Cell>> generateGrid(int gridwidth, int gridheight, int maxrooms, long mapid, Map map);

    PlacedRoom createFromSeed(PlacedRoom placedRooms, Room room, int[] roomRange, long mapid);

    ArrayList<ArrayList<Cell>> placeCells(ArrayList<ArrayList<Cell>> grid, Room room, String Type);


    boolean isValidRoomPlacement(ArrayList<ArrayList<Cell>> grid,Room room);

    PlacedRoom growMap(PlacedRoom seedRoom, int counter, int maxRooms, int [] roomRange, long mapid);


    ArrayList<Cell> getMap();



}
