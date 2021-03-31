package com.dev.mud_backend.services;

import com.dev.mud_backend.models.Map;
import com.dev.mud_backend.responseObjects.MapDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public interface MapService {
    List<Map> getMap(Long userid);

    Map getPlayerLocation(Long mapid);

    Map updatePlayer(Map playerMap, long mapid);

    Map findById(long mapid);

    HashMap<Long, ArrayList<Long>> getMapDetails(long mapid);



}
