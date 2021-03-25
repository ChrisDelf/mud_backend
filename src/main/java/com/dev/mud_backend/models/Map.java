package com.dev.mud_backend.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



@Entity
@Table(name = "maps")
public class Map implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long mapid;

    private int width;

    private int height;

    private int playerx;

    private int playery;

    @Column(length = 1000000)
    String grid;


    @ManyToOne
    @JoinColumn(name = "userid",
            nullable = false)
    @JsonIgnoreProperties("maps")
    private User user;

    @OneToMany(mappedBy="map",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonIgnoreProperties("map")
    private List<Room> rooms = new ArrayList<>();



    public Map() {
    }

    public Map(long mapid, String grid, User user,int width, int height) {
        this.mapid = mapid;
        this.width = width;
        this.height = height;
        this.user = user;
    }

    public int getPlayerx() {
        return playerx;
    }

    public void setPlayerx(int playerx) {
        this.playerx = playerx;
    }

    public int getPlayery() {
        return playery;
    }

    public void setPlayery(int playery) {
        this.playery = playery;
    }

    public String getGrid() {
        return grid;
    }

    public void setGrid(String grid) {
        this.grid = grid;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public long getRoomid() {
        return mapid;
    }



    public long getMapid() {
        return mapid;
    }

    public void setMapid(long mapid) {
        this.mapid = mapid;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}
