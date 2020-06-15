package com.dev.mud_backend.controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/game")
public class GameController {
    private static final Logger logger = LoggerFactory.getLogger(GameController.class);



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
    @PutMapping(value ="player/combatAction", produces = {"application/json"})
    public  ResponseEntity<?> playerCombat(long monserId){

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
