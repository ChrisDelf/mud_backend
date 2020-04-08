package com.dev.mud_backend.models;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;

public class PlacedRooms {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long placedRoomId;

    //    private int x;
//
//    private int y;
    private ArrayList<ArrayList<Cell>> grid = new ArrayList<ArrayList<Cell>>();

    private ArrayList<Room> placedRooms = new ArrayList<Room>();


    public ArrayList<ArrayList<Cell>> getGrid() {
        return grid;
    }

    public void setGrid(ArrayList<ArrayList<Cell>> grid) {
        this.grid = grid;
    }

    public ArrayList<Room> getPlacedRooms() {
        return placedRooms;
    }

    public void setPlacedRooms(ArrayList<Room> placedRooms) {
        this.placedRooms = placedRooms;
    }
}
