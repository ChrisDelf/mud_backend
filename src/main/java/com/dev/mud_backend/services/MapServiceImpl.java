package com.dev.mud_backend.services;

import com.dev.mud_backend.exceptions.ResourceNotFoundException;
import com.dev.mud_backend.models.Map;
import com.dev.mud_backend.models.Monster;
import com.dev.mud_backend.models.Room;
import com.dev.mud_backend.models.User;
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

//    @Override
//    public HashMap<Long, ArrayList<Long>> getMapDetails(long mapid) {
//        // going to need to get the array object for Mapdetials
//        List<Room> temp_list = new ArrayList<Room>();
//        temp_list = mapService.findById(mapid).getRooms();
//
//        MapDetails responseM = new MapDetails();
//        ArrayList<Long> rooms = responseM.getRooms();
//        ArrayList<HashMap> r_array = new ArrayList<>();
//        HashMap<Long, ArrayList<Long>> roomsAndMonsters = new  HashMap<Long, ArrayList<Long>>();
//
//        for (int i =0; i < temp_list.size(); i++){
//            List<Monster> monsterList = temp_list.get(i).getMonstersList();
//            ArrayList<Long> monsterIds = new ArrayList<>();
//            if (monsterList.size() > 0){
//                for (int j = 0; j < monsterList.size(); j++){
//                    monsterIds.add(monsterList.get(j).getMonsterid());
//
//
//                }
//
//            }
//
//            roomsAndMonsters.put(temp_list.get(i).getRoomId(), monsterIds);
//
//
//        }
//
//        return roomsAndMonsters;
//    }


    @Transactional
    @Override
    public Map findById(long mapid) {
        Map target_map = new Map();

        target_map = mapRepo.findByMapid(mapid);

        return target_map;
    }



}
