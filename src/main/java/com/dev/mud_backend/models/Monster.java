package com.dev.mud_backend.models;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
