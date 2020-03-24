package com.dev.mud_backend.models;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private long roomid;

    private int x;

    private int y;

    private String roomType;

    private int roomheight;

    private int roomwidth;


    public Room() {
    }

    public Room(int x, int y, String roomType, int roomheight, int roomwidth) {
        this.x = x;
        this.y = y;
        this.roomType = roomType;
        this.roomheight = roomheight;
        this.roomwidth = roomwidth;
    }

    public void setRoomid(long roomid) {
        this.roomid = roomid;
    }

    public int getRoomheight() {
        return roomheight;
    }

    public void setRoomheight(int roomheight) {
        this.roomheight = roomheight;
    }

    public int getRoomwidth() {
        return roomwidth;
    }

    public void setRoomwidth(int roomwidth) {
        this.roomwidth = roomwidth;
    }

    public long getRoomid() {
        return roomid;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }
}
