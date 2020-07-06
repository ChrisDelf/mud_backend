package com.dev.mud_backend.controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

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
    @PutMapping(value ="/player/combatAction", produces = {"application/json"})
    public  ResponseEntity<?> playerCombat(long montserId){

        return new ResponseEntity<>(HttpStatus.OK);
    }



    // ---------------------- Monster Actions
    // update
    /// /monster/pickupItem/
    @PutMapping(value = "/monster/itemAction", produces = {"application/json"})
    public ResponseEntity<?> monsterItem(long monsterId){

        return new ResponseEntity<>(HttpStatus.OK);
    }



    //Get
    // /monster/checkInventory/
    @GetMapping(value = "/monster/checkInventory", produces = {"application/json"})
    public ResponseEntity<?> monsterInvetory(long monsterId){


        return new ResponseEntity<>(HttpStatus.OK);
    }


    // ----------------------- Game Actions
    // delete
    // /monsterDefeat/

    @DeleteMapping(value = "/game/monsterdeath", produces = {"application/json"})
    public ResponseEntity<?> monsterDeath(long monsterId){

        return new ResponseEntity<>(HttpStatus.OK);
    }

    // update
    // /playerDefeat/

    @PutMapping(value = "/game/playerdefeat", produces = {"application/json"})
    public ResponseEntity<?> playerDefeat(long playerId){

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
