package com.dev.mud_backend.services;

import com.dev.mud_backend.exceptions.ResourceNotFoundException;
import com.dev.mud_backend.models.*;
import com.dev.mud_backend.repository.MapRepository;
import com.dev.mud_backend.responseObjects.MapDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service(value = "mapService")
public class MapServiceImpl implements MapService {

    @Autowired
    UserService userService;

    @Autowired
    MapRepository mapRepo;

    @Autowired
    MapService mapService;

    @Autowired
    PlayerService playerService;

    @Override
    public List<Map> getMap(Long userid) {
        User tempuser = userService.findUserById(userid);

        List<Map> maplist = tempuser.getUserMap();


        return maplist;
    }


    @Transactional
    @Override
    public Map findById(long mapid) {
        Map target_map = new Map();

        target_map = mapRepo.findByMapid(mapid);

        return target_map;
    }



}
