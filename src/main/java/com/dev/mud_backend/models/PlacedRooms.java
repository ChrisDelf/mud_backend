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
    private ArrayList<ArrayList> grid = new ArrayList<ArrayList>();

    private ArrayList<Room> placedRooms = new ArrayList<Room>();





}
