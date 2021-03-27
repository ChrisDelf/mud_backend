package com.dev.mud_backend.services;

import com.dev.mud_backend.models.Cell;
import com.dev.mud_backend.models.PlacedRoom;
import com.dev.mud_backend.models.Room;

import java.util.ArrayList;



public interface DungeonCreatorService {

    ArrayList<ArrayList<Cell>> generateGrid(int gridwidth, int gridheight, int maxrooms, long mapid);

    ArrayList<ArrayList<Cell>> createFromSeed(ArrayList<ArrayList<Cell>> grid, Room room, int[] roomRange, long mapid);

    ArrayList<ArrayList<Cell>> placeCells(ArrayList<ArrayList<Cell>> grid, Room room, String Type);


    boolean isValidRoomPlacement(ArrayList<ArrayList<Cell>> grid,Room room);

    ArrayList<ArrayList<Cell>> growMap(PlacedRoom roomsPlaced, ArrayList<Room> seedRoom, int counter, int maxRooms, int [] roomRange, long mapid,ArrayList<ArrayList<Cell>> grid);

    ArrayList<Cell> getMap();



}
