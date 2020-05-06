package com.dev.mud_backend.services;

import com.dev.mud_backend.exceptions.ResourceNotFoundException;
import com.dev.mud_backend.models.Map;
import com.dev.mud_backend.models.User;
import com.dev.mud_backend.repository.MapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service(value = "mapService")
public class MapServiceImpl implements MapService {

    @Autowired
    UserService userService;

    @Autowired
    MapRepository mapRepo;

    @Override
    public List<Map> getMap(Long userid) {
        User tempuser = userService.findUserById(userid);

        List<Map> maplist = tempuser.getUserMap();



        return maplist;
    }

    @Override
    public Map getPlayerLocation(Long mapid) throws ResourceNotFoundException {
        Map tempMap = new Map();

       tempMap = mapRepo.findById(mapid).orElseThrow(() -> new EntityNotFoundException(Long.toString(mapid)));
        return tempMap;
    }

    @Override
    public Map updatePlayer(Map playerMap, long mapid) {
        Map tempMap = new Map();
        tempMap = mapRepo.findById(mapid).orElseThrow(() -> new EntityNotFoundException(Long.toString(mapid)));
        tempMap.setPlayerx(playerMap.getPlayerx());
        tempMap.setPlayery(playerMap.getPlayery());
        return mapRepo.save(tempMap);
    }
}
