package com.dev.mud_backend.services;

import com.dev.mud_backend.models.Map;
import com.dev.mud_backend.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "mapService")
public class MapServiceImpl implements MapService {

    @Autowired
    UserService userService;

    @Override
    public List<Map> getMap(Long userid) {
        User tempuser = userService.findUserById(userid);

        List<Map> maplist = tempuser.getUserMap();



        return maplist;
    }
}
