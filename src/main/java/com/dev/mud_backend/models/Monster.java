package com.dev.mud_backend.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "monsters")
public class Monster {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long monsterid;

    String monsterName;

    private long mapid;


    // Stats

    private long monsterHealth;

    private long strength;

    private long agility;

    private long intellect;

    private long stamina;

    private int monsterX;

    private int monsterY;

    private long maxhealth;

    private String status;

    @OneToMany(mappedBy = "monster",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonIgnoreProperties("monster")
    private List<Item> itemsList = new ArrayList<>();

//    @ManyToOne
//    @JoinColumn(name = "roomId",
//            nullable = false)
//    @JsonIgnoreProperties("monsters")
//    private Room room;

    public Monster() {

    }

    public Monster( String monsterName, long monsterHealth, long strength, long agility, long intellect, long stamina, long maxhealth, String status) {
        this.monsterName = monsterName;
        this.monsterHealth = monsterHealth;
        this.strength = strength;
        this.agility = agility;
        this.intellect = intellect;
        this.stamina = stamina;
        this.maxhealth = maxhealth;
        this.status = status;
    }


    public int getMonsterX() {
        return monsterX;
    }

    public void setMonsterX(int monsterX) {
        this.monsterX = monsterX;
    }

    public void setMonsterY(int monsterY) {
        this.monsterY = monsterY;
    }

    public int getMonsterY() {
        return monsterY;
    }


    public String getMonsterName() {
        return monsterName;
    }

    public void setMonsterName(String monsterName) {
        this.monsterName = monsterName;
    }

    public long getMonsterHealth() {
        return monsterHealth;
    }

    public void setMonsterHealth(long monsterHealth) {
        this.monsterHealth = monsterHealth;
    }

    public long getStrength() {
        return strength;
    }

    public void setStrength(long strength) {
        this.strength = strength;
    }

    public long getAgility() {
        return agility;
    }

    public void setAgility(long agility) {
        this.agility = agility;
    }

    public long getIntellect() {
        return intellect;
    }

    public void setIntellect(long intellect) {
        this.intellect = intellect;
    }

    public long getStamina() {
        return stamina;
    }

    public void setStamina(long stamina) {
        this.stamina = stamina;
    }

    public long getMonsterid() {
        return monsterid;
    }

    public long getMaxhealth() {
        return maxhealth;
    }

    public void setMaxhealth(long maxhealth) {
        this.maxhealth = maxhealth;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

//    public Room getRoom() {
//        return room;
//    }
//
//    public void setRoom(Room room) {
//        this.room = room;
//    }
//

    public long getMapid() {
        return mapid;
    }

    public void setMapid(long mapid) {
        this.mapid = mapid;
    }
}
