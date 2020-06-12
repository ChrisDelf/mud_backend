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


    // Stats

    private long monsterHealth;

    private long strength;

    private long agility;

    private long intellect;

    private long stamina;

    @OneToMany(mappedBy = "monster",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonIgnoreProperties("monster")
    private List<Item> itemsList = new ArrayList<>();



    public Monster(String monsterName, long monsterHealth, long strength, long agility, long intellect, long stamina) {
        this.monsterName = monsterName;
        this.monsterHealth = monsterHealth;
        this.strength = strength;
        this.agility = agility;
        this.intellect = intellect;
        this.stamina = stamina;
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
}
