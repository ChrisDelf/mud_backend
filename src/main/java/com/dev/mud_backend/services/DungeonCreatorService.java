package com.dev.mud_backend.services;

import com.dev.mud_backend.models.Cell;
import com.dev.mud_backend.models.PlacedRooms;
import com.dev.mud_backend.models.Room;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;



public interface DungeonCreatorService {

    PlacedRooms generateGrid(int gridwidth, int gridheight, int maxrooms, long mapid);

    PlacedRooms createFromSeed(ArrayList<ArrayList<Cell>> grid, Room room, int[] roomRange, long mapid);

    ArrayList<ArrayList<Cell>> placeCells(ArrayList<ArrayList<Cell>> grid, Room room, String Type);


    boolean isValidRoomPlacement(ArrayList<ArrayList<Cell>> grid,Room room);

    PlacedRooms growMap(PlacedRooms roomsPlaced, ArrayList<Room> seedRoom, int counter, int maxRooms, int [] roomRange, long mapid);

    ArrayList<Cell> getMap();



}
