package com.dev.mud_backend.models;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name = "placedRooms")
public class PlacedRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long placedRoomId;

    @Column(length = 1000000)
    String grid;

//    private ArrayList<ArrayList<Cell>> grid = new ArrayList<ArrayList<Cell>>();
//
    private ArrayList<Room> placedRooms = new ArrayList<Room>();

    public String getGrid() {
        return grid;
    }

    public void setGrid(String grid) {
        this.grid = grid;
    }
//    public ArrayList<ArrayList<Cell>> getGrid() {
//        return grid;
//    }
//
//    public void setGrid(ArrayList<ArrayList<Cell>> grid) {
//        this.grid = grid;
//    }

    public ArrayList<Room> getPlacedRooms() {
        return placedRooms;
    }

    public void setPlacedRooms(ArrayList<Room> placedRooms) {
        this.placedRooms = placedRooms;
    }
}
