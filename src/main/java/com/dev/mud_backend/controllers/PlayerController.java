package com.dev.mud_backend.controllers;

import com.dev.mud_backend.models.Map;
import com.dev.mud_backend.models.Player;
import com.dev.mud_backend.models.User;
import com.dev.mud_backend.repository.PlayerRepository;
import com.dev.mud_backend.services.PlayerService;
import org.h2.util.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/player")
public class PlayerController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private PlayerService playerService;

    @PutMapping(value = "/attack/{playerid}",
            consumes = {"application/json"},
            produces = {"application/json"})
    public ResponseEntity<?> playerAttack(HttpServletRequest request,
                                          @RequestBody
                                          JSONObject json,
                                          @PathVariable long playerid,
                                          @PathVariable long monsterid)
    {
        ArrayList<Long> monsterId_L = new ArrayList<Long>();
        System.out.println("Link workded");
        // let use grab out player and our monsterid
        //Player target_player = playerService.findById(playerid);




        return new ResponseEntity<>( HttpStatus.OK);
    }
}
