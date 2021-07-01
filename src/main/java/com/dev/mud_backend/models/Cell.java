package com.dev.mud_backend.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;

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
    private String cellType;

    private long roomid;

    private long mapid;


    private ArrayList<Long> containsM;

    private ArrayList<Long> containsP;

    public Cell() {
    }

    public Cell(int x, int y, String cellType, int roomheight, int roomwidth, ArrayList<Long> containsM, ArrayList<Long> containsP) {
     this.x = x;
     this.y = y;
        this.cellType = cellType;

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

    public String getCellType() {
        return cellType;
    }

    public void setCellType(String cellType) {
        this.cellType = cellType;
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

    public ArrayList<Long> getContainsM() {
        return containsM;
    }

    public void setContainsM(ArrayList<Long> containsM) {
        this.containsM = containsM;
    }

    public ArrayList<Long> getContainsP() {
        return containsP;
    }

    public void setContainsP(ArrayList<Long> containsP) {
        this.containsP = containsP;
    }
}


