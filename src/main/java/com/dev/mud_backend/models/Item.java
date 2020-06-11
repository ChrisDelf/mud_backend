package com.dev.mud_backend.models;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Item {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long itemid;

    String itemname;

    String itemtype;

    String itemposition;
    // Weapon Stats

    private long cost;

    private long weight;

    private long damage;


    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public String getItemtype() {
        return itemtype;
    }

    public void setItemtype(String itemtype) {
        this.itemtype = itemtype;
    }

    public String getItemposition() {
        return itemposition;
    }

    public void setItemposition(String itemposition) {
        this.itemposition = itemposition;
    }

    public long getCost() {
        return cost;
    }

    public void setCost(long cost) {
        this.cost = cost;
    }

    public long getWeight() {
        return weight;
    }

    public void setWeight(long weight) {
        this.weight = weight;
    }

    public long getDamage() {
        return damage;
    }

    public void setDamage(long damage) {
        this.damage = damage;
    }
}
