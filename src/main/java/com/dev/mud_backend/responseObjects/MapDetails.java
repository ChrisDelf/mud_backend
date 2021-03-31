package com.dev.mud_backend.responseObjects;

import com.dev.mud_backend.models.Monster;
import com.dev.mud_backend.models.Room;

import java.util.ArrayList;

public class MapDetails {

    private ArrayList<Long> rooms = new ArrayList<Long>();

    private ArrayList<Monster> monsters = new ArrayList<Monster>();

    public MapDetails() {
    }

    public MapDetails(ArrayList<Long> rooms, ArrayList<Monster> monsters) {
        this.rooms = rooms;
        this.monsters = monsters;
    }

    public ArrayList<Long> getRooms() {
        return rooms;
    }

    public void setRooms(ArrayList<Long> rooms) {
        this.rooms = rooms;
    }

    public ArrayList<Monster> getMonsters() {
        return monsters;
    }

    public void setMonsters(ArrayList<Monster> monsters) {
        this.monsters = monsters;
    }
}
