package com.dev.mud_backend.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



@Entity
@Table(name = "maps")
public class Map {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long mapid;

    String grid;

    @ManyToOne
    @JoinColumn(name = "userid",
            nullable = false)
    @JsonIgnoreProperties("maps")
    private User user;


    public Map() {
    }

    public Map(long mapid, String grid, User user) {
        this.mapid = mapid;
        this.grid = grid;
        this.user = user;
    }

    public long getRoomid() {
        return mapid;
    }

    public String getGrid() {
        return grid;
    }

    public void setGrid(String grid) {
        this.grid = grid;
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
