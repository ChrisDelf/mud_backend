package com.dev.mud_backend.controllers;


import com.dev.mud_backend.models.Map;
import com.dev.mud_backend.models.Monster;
import com.dev.mud_backend.models.Player;
import com.dev.mud_backend.responseObjects.PlayerAction;
import com.dev.mud_backend.services.MapService;
import com.dev.mud_backend.services.MonsterService;
import com.dev.mud_backend.services.PlayerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/game")
public class GameController {
    private static final Logger logger = LoggerFactory.getLogger(GameController.class);


    @Autowired
    private PlayerService playerService;

    @Autowired
    private MonsterService monsterService;

    @Autowired
    private MapService mapService;

    //--------------------------- Game info

    //get
    // get list of monsters
    @GetMapping(value ="/monsterslist/{mapid}" , produces = {"application/json"})
    public ResponseEntity<?> getMonsterList(@PathVariable long mapid) {

        Map temp_map = new Map();

        temp_map = mapService.findById(mapid);

        List<Monster> return_array = temp_map.getMonsters();



        return new ResponseEntity<>(return_array, HttpStatus.OK);
    }

    //get
    //find one monster
    @GetMapping(value ="/monster/{monsterid}", produces = {"application/json"})
    public ResponseEntity<?> getMonster(@PathVariable long monsterid){

        Monster target_monster = new Monster();
        target_monster = monsterService.findById(monsterid);


        return new ResponseEntity<>(target_monster, HttpStatus.OK);
    }


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

    @PutMapping(value ="player/combatAction/{playerid}/{monsterid}",  consumes = {"application/json"},produces = {"application/json"})
    public  ResponseEntity<?> playerCombat(HttpServletRequest request, @Valid @RequestBody
                                           PlayerAction playerAction, @PathVariable long playerid, long monsterid){
        ArrayList<Long> return_array = new ArrayList<>();
        long updated_number = 0;
        Player temp_player = new Player();

        if (playerAction.getActionType() == "Heal"){
            updated_number = playerService.playerHealed(playerAction.getActionNum(),playerid);
            temp_player = playerService.findById(playerid);
        }

        if (playerAction.getActionType() == "Attack"){
            updated_number = playerService.playerAttack(playerAction.getActionNum(), playerid, monsterid);
            temp_player = playerService.findById(playerid);
        }

        if (playerAction.getActionType() == "Status"){
            // if the player status changes
            temp_player = playerService.findById(playerid);
        }

        return_array.add(playerid);
        return_array.add(monsterid);
        return_array.add(updated_number);




        return new ResponseEntity<>(temp_player, HttpStatus.OK);
    }



    // ---------------------- Monster Actions
    // update
    /// /monster/pickupItem/
    @PutMapping(value = "/monster/itemAction", produces = {"application/json"})
    public ResponseEntity<?> monsterItem(long monsterId){

        return new ResponseEntity<>(HttpStatus.OK);
    }



    // update
    // /monster/actio/
    @PutMapping(value ="/monster/action/{playerid}/{monsterid}", produces = {"application/json"})
    public ResponseEntity<?> monsterActions(@Valid @RequestBody
                                                       PlayerAction monsterAction, @PathVariable long playerid, long monsterid){
        String action = monsterAction.getActionType();
        long updated_number;
        String server_response;
        Monster temp_monster = new Monster();
        if (action == "Heal"){
            updated_number = monsterService.MonsterHeal(monsterid);
            temp_monster = monsterService.findById(monsterid);




        }
        if (action == "Attack"){
            updated_number = monsterService.MonsterAttack(monsterid, playerid);
            temp_monster = monsterService.findById(monsterid);

        }

        if (action == "Status"){
            // need to create a status change service for monsters
            temp_monster = monsterService.findById(monsterid);
        }








        return new ResponseEntity<>(temp_monster, HttpStatus.OK);
    }
    //Get
    // getting the monsters stats
    @GetMapping(value ="/monster/monster/{monsterid}", produces = {"application/json"})
    public ResponseEntity<?> monsterStatus(long monsterid) {

        Monster repsonseMonster = monsterService.findById(monsterid);


        return new ResponseEntity<>(repsonseMonster, HttpStatus.OK);
    }
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
