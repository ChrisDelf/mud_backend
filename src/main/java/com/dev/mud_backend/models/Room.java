package com.dev.mud_backend.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "rooms")
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

    private int monsterLimit;

//    @OneToMany(mappedBy="room",
//            cascade = CascadeType.ALL,
//            orphanRemoval = true)
//    @JsonIgnoreProperties("room")
//    private ArrayList<Monster> monsters = new ArrayList<Monster>();

//    @OneToMany(mappedBy="room"
//            //cascade = CascadeType.ALL,
//           // orphanRemoval = true//
//    )
//    @JsonIgnoreProperties("room")
//    private ArrayList<Cell> cells = new ArrayList<Cell>();

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

    public int getMonsterLimit() {
        return monsterLimit;
    }

    public void setMonsterLimit(int monsterLimit) {
        this.monsterLimit = monsterLimit;
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

   // public ArrayList<Monster> getMonstersList() {
   //     return monsters;
   // }

  //  public void setMonstersList(ArrayList<Monster> monstersList) {
    //    this.monsters = monstersList;
   // }




}
