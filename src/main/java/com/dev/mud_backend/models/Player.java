package com.dev.mud_backend.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "players")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long playerid;

    private long playerHealth;

    private String playerName;

    private long playery;

    private long playerx;

    private long playerStrength;

    private long playerIntellect;

    private long playerAgility;

    private long playerStamina;


    @OneToMany(mappedBy = "player",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonIgnoreProperties("player")
    private List<Item> itemsList = new ArrayList<>();

    public Player(long playerHealth, String playerName, long playery, long playerx, long playerStrength, long playerIntellect, long playerAgility, long playerStamina, List<Item> itemsList) {
        this.playerHealth = playerHealth;
        this.playerName = playerName;
        this.playery = playery;
        this.playerx = playerx;
        this.playerStrength = playerStrength;
        this.playerIntellect = playerIntellect;
        this.playerAgility = playerAgility;
        this.playerStamina = playerStamina;
        this.itemsList = itemsList;
    }

    public long getPlayerHealth() {
        return playerHealth;
    }

    public void setPlayerHealth(long playerHealth) {
        this.playerHealth = playerHealth;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public long getPlayery() {
        return playery;
    }

    public void setPlayery(long playery) {
        this.playery = playery;
    }

    public long getPlayerx() {
        return playerx;
    }

    public void setPlayerx(long playerx) {
        this.playerx = playerx;
    }

    public long getPlayerStrength() {
        return playerStrength;
    }

    public void setPlayerStrength(long playerStrength) {
        this.playerStrength = playerStrength;
    }

    public long getPlayerIntellect() {
        return playerIntellect;
    }

    public void setPlayerIntellect(long playerIntellect) {
        this.playerIntellect = playerIntellect;
    }

    public long getPlayerAgility() {
        return playerAgility;
    }

    public void setPlayerAgility(long playerAgility) {
        this.playerAgility = playerAgility;
    }

    public long getPlayerStamina() {
        return playerStamina;
    }

    public void setPlayerStamina(long playerStamina) {
        this.playerStamina = playerStamina;
    }

    public List<Item> getItemsList() {
        return itemsList;
    }

    public void setItemsList(List<Item> itemsList) {
        this.itemsList = itemsList;
    }

    public long getPlayerid() {
        return playerid;
    }
}
