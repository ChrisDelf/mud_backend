package com.dev.mud_backend.controllers;


import com.dev.mud_backend.models.Map;
import com.dev.mud_backend.services.PlayerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/game")
public class GameController {
    private static final Logger logger = LoggerFactory.getLogger(GameController.class);


    @Autowired
    private PlayerService playerService;
    //--------------------------- Player Actions
    // update
    /// /player/pickupItem/

    @PutMapping(value ="/player/itemInteraction", produces = {"application/json"})
    public ResponseEntity<?> playerItemInteraction(long itemId){




        return new ResponseEntity<>(HttpStatus.OK);
    }


    //Get
    // /player/checkInventory/
    @GetMapping(value ="/player/checkInventory", produces = {"application/json"})
    public ResponseEntity<?> playerCheckInventory(long playerId) {

        return new ResponseEntity<>(HttpStatus.OK);

    }

    //Get
    // /player/insight
    @GetMapping(value ="/player/insight", produces = {"applications/json"})
    public ResponseEntity<?>  playerInspect(long id){

        return new ResponseEntity<>(HttpStatus.OK);
    }




    // update
    // /player/combatAction
    @PutMapping(value ="player/combatAction/{playerid}/{monsterid}", produces = {"application/json"})
    public  ResponseEntity<?> playerCombat(@Valid @RequestBody
                                                       Map updateMap, @PathVariable long playerid, long monsterid){


        return new ResponseEntity<>(HttpStatus.OK);
    }



    // ---------------------- Monster Actions
    // update
    /// /monster/pickupItem/

    // update
    // /monster/dropItem/

    //Get
    // /monster/checkInventory/


    // ----------------------- Game Actions
    // delete
    // /monsterDefeat/

    // update
    // /playerDefeat/
}
