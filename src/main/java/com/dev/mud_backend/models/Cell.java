package com.dev.mud_backend.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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

    private String cellType;

    private long roomid;

    private ArrayList<Long> containsM;

    private ArrayList<Long> containsP;

    private long mapid;


    public Cell() {
    }

    public Cell(int x, int y, String cellType, int roomheight, int roomwidth, ArrayList<Long> containsM, ArrayList<Long> containsP) {
     this.x = x;
     this.y = y;
     this.cellType = cellType;
     this.containsM = containsM;
     this.containsP = containsP;

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


