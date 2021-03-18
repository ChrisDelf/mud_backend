package com.dev.mud_backend.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "cells")
public class Cell {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long cellid;

    private int x;

    private int y;

    @Column(nullable = false,
            unique = false)
    private String roomType;

   @ManyToOne
   @JoinColumn(name = "roomid",
           nullable = false)
   @JsonIgnoreProperties("cells")
   private Room room;

    private long mapid;




    public Cell() {
    }

    public Cell(int x, int y, String roomType, int roomheight, int roomwidth) {
//        this.x = x;
//        this.y = y;
        this.roomType = roomType;

    }

    public long getMapid() {
        return mapid;
    }

    public void setMapid(long mapid) {
        this.mapid = mapid;
    }

    public long getRoomid() {
        return cellid;
    }

    public void setRoomid(long roomid) {
        this.cellid = roomid;
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


