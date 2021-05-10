package com.dev.mud_backend.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;


@Entity
@Table(name = "items")
public class Item {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "itemid")
    private long itemid;

    private long mapid;

    private String itemname;

    private String itemtype;

    private String itemposition;
    // Weapon Stats

    private long cost;

    private long weight;

    private long damage;

    @ManyToOne
    @JoinColumn(name = "playerid",
            nullable = false)
    @JsonIgnoreProperties({"itemsList","map","user"})
    private Player player;

    @ManyToOne
    @JoinColumn(name = "monsterid",
                nullable = false)
    @JsonIgnoreProperties("itemsList")
    private  Monster monster;

    public Item(String itemname, String itemtype, String itemposition, long cost, long weight, long damage, Player player) {
        this.itemname = itemname;
        this.itemtype = itemtype;
        this.itemposition = itemposition;
        this.cost = cost;
        this.weight = weight;
        this.damage = damage;
        this.player = player;
    }

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
