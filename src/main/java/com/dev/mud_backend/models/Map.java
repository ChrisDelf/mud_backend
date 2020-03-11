package com.dev.mud_backend.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;



@Entity
@Table(name ="users")
public class Map {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long roomid;


    private long x;

    private long y;

    private String roomType;

    public Map() {
    }

    public Map(long x, long y, String roomType) {
        this.x = x;
        this.y = y;
        this.roomType = roomType;

    }

    public long getRoomid() {
        return roomid;
    }

    public long getX() {
        return x;
    }

    public long getY() {
        return y;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setX(long x) {
        this.x = x;
    }

    public void setY(long y) {
        this.y = y;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }
}
