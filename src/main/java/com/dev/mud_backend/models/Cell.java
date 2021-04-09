package com.dev.mud_backend.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "cells")
public class Cell implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long cellid;

    private int x;

    private int y;

    @Column(length = 1000000)
    String entities;

    @Column(length = 1000000)
    String items;

    @Column(nullable = false,
            unique = false)
    private String roomType;

    private long roomid;

    private long mapid;

    public Cell() {
    }

    public Cell(int x, int y, String roomType, int roomheight, int roomwidth) {
     this.x = x;
     this.y = y;
        this.roomType = roomType;

    }

    public long getMapid() {
        return mapid;
    }

    public void setMapid(long mapid) {
        this.mapid = mapid;
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

    public long getCellid() {
        return cellid;
    }

    public void setCellid(long cellid) {
        this.cellid = cellid;
    }

    public long getRoomid() {
        return roomid;
    }

    public void setRoomid(long roomid) {
        this.roomid = roomid;
    }

    public String getEntities() {
        return entities;
    }

    public void setEntities(String entities) {
        this.entities = entities;
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }
}


