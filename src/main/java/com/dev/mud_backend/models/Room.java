package com.dev.mud_backend.models;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long roomId;

    private int x;

    private int y;

    private int height;

    private int width;

    private int doorY;

    private int doorX;



    public Room() {
    }

    public Room(int x, int y, int height, int width, int doorY, int doorX) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.doorY = doorY;
        this.doorX = doorX;
    }

    public long getRoomId() {
        return roomId;
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

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getDoorY() {
        return doorY;
    }

    public void setDoorY(int doorY) {
        this.doorY = doorY;
    }

    public int getDoorX() {
        return doorX;
    }

    public void setDoorX(int doorX) {
        this.doorX = doorX;
    }
}
