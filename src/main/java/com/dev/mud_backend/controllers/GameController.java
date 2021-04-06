package com.dev.mud_backend.controllers;


import com.dev.mud_backend.models.Map;
import com.dev.mud_backend.models.Monster;
import com.dev.mud_backend.responseObjects.PlayerAction;
import com.dev.mud_backend.services.MonsterService;
import com.dev.mud_backend.services.PlayerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("/game")
public class GameController {
    private static final Logger logger = LoggerFactory.getLogger(GameController.class);


    @Autowired
    private PlayerService playerService;

    @Autowired
    private MonsterService monsterService;

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
                                           PlayerAction playerAction, @PathVariable long playerid, long monsterid){
        ArrayList<Long> return_array = new ArrayList<>();
        long updated_number = 0;
        if (playerAction.getActionType() == "Heal"){
            updated_number = playerService.playerHealed(playerAction.getActionNum(),playerid);
        }

        if (playerAction.getActionType() == "Attack"){
            updated_number = playerService.playerAttack(playerAction.getActionNum(), playerid, monsterid);
        }

        if (playerAction.getActionType() == "Status"){
            // if the player status changes
        }

        return_array.add(playerid);
        return_array.add(monsterid);
        return_array.add(updated_number);




        return new ResponseEntity<>(updated_number, HttpStatus.OK);
    }



    // ---------------------- Monster Actions
    // update
    /// /monster/pickupItem/

    // update
    // /monster/dropItem/

    // update
    // /monster/actio/
    @PutMapping(value ="/monster/action/{playerid}/{monsterid}", produces = {"application/json"})
    public ResponseEntity<?> monsterActions(@Valid @RequestBody
                                                       PlayerAction monsterAction, @PathVariable long playerid, long monsterid){
        String action = monsterAction.getActionType();
        long updated_number;
        if (action == "Heal"){
            updated_number = monsterService.MonsterHeal(monsterid);

        }
        if (action == "Attack"){
            updated_number = monsterService.MonsterAttack(monsterid, playerid);

        }

        if (action == "Status"){
            // need to create a status change service for monsters
        }




        return new ResponseEntity<>(HttpStatus.OK);
    }
    //Get
    // getting the monsters stats
    @GetMapping(value ="/monster/monster/{monsterid}", produces = {"application/json"})
    public ResponseEntity<?> monsterStatus(long monsterid) {

        Monster repsonseMonster = monsterService.findById(monsterid);


        return new ResponseEntity<>(repsonseMonster, HttpStatus.OK);
    }
    // /monster/checkInventory/




    // ----------------------- Game Actions
    // delete
    // /monsterDefeat/

    // update
    // /playerDefeat/
}
