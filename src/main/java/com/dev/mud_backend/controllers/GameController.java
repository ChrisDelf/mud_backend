package com.dev.mud_backend.controllers;


import com.dev.mud_backend.models.*;
import com.dev.mud_backend.repository.MapRepository;
import com.dev.mud_backend.repository.PlayerRepository;
import com.dev.mud_backend.responseObjects.PlayerAction;
import com.dev.mud_backend.services.*;
import com.google.gson.Gson;
import org.h2.util.json.JSONObject;
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
    private DungeonCreatorService dungeonCreatorService;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private MonsterService monsterService;

    @Autowired
    private UserService userService;

    @Autowired
    PlayerRepository playerRepo;

    @Autowired
    MapRepository mapRepo;

    @Autowired
    private MapService mapService;


    //--------------------------- Game info

    //get
    // get list of monsters
    @GetMapping(value ="/monsterslist/{mapid}" , produces = {"application/json"})
    public ResponseEntity<?> getMonsterList(@Valid @PathVariable long mapid) {

        Map tempmap = new Map();

        tempmap = mapService.findById(mapid);

        return new ResponseEntity<>(tempmap.getMonsters(), HttpStatus.OK);
    }

    //get
    //find one monster
    @GetMapping(value ="/monster/{monsterid}", produces = {"application/json"})
    public ResponseEntity<?> getMonster(@PathVariable long monsterid){

        Monster target_monster = new Monster();
        target_monster = monsterService.findById(monsterid);


        return new ResponseEntity<>(target_monster, HttpStatus.OK);
    }

    @GetMapping(value ="/generatemap/{userid}", produces = {"application/json"})
    public ResponseEntity<?> getTest(@Valid @PathVariable long userid) {

        ArrayList<ArrayList<Cell>> grid = new ArrayList<ArrayList<Cell>>();

        Map newMap = new Map();
        newMap.setWidth(50);
        newMap.setHeight(50);
        // we have the create the player
        List<Item> items = new ArrayList<>();
        Player newPlayer = new Player(50,"Doofus",32,32,5,5,5,5,items,"standing",50,"safe");
        List<Player> playerList = new ArrayList<>();
        playerList = newMap.getPlayers();
        playerList.add(newPlayer);

        newMap.setPlayers(playerList);
        newMap.setUser(userService.findUserById(userid));
        mapRepo.save(newMap);
        newPlayer.setMap(newMap);
        playerRepo.save(newPlayer);

        grid = dungeonCreatorService.generateGrid(50,50,2, newMap.getMapid());

        ArrayList<ArrayList<String>> visualGrid = new ArrayList<>();


        Gson gson = new Gson();

        String json = gson.toJson(grid);


        newMap.setGrid(json);

        mapRepo.save(newMap);
        /// creating a new map object
        Map return_map = new Map();
        return_map.setMapid(newMap.getMapid());
        return_map.setHeight(newMap.getHeight());
        return_map.setWidth(newMap.getWidth());
        return_map.setGrid(newMap.getGrid());
        return_map.setMonsters(newMap.getMonsters());
        return_map.setPlayers(newMap.getPlayers());





        return  new ResponseEntity<>(return_map,HttpStatus.OK);
    }
    //--------------------------- Player Actions
    // update
    /// /player/pickupItem/

    @PutMapping(value ="/player/itemInteraction",  consumes = {"application/json"} , produces = {"application/json"})
    public ResponseEntity<?> playerItemInteraction(@Valid Player player){




        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Get
    // /player
    @GetMapping(value ="/player/{playerid}",produces = {"application/json"})
    public ResponseEntity<?> playerInfo(@Valid @PathVariable long playerid) {
        Player player = new Player();

        player = playerService.findById(playerid);

        return new ResponseEntity<>(player,HttpStatus.OK);

    }





    // update
    // /player/update
    @PutMapping(value ="player/update/{playerid}",  consumes = {"application/json"},produces = {"application/json"})
    public  ResponseEntity<?> playerCombat(HttpServletRequest request, @Valid @RequestBody
                                           Player player, @PathVariable long playerid){

        Player temp_player = new Player();
        temp_player = playerService.updatePlayer(player, playerid);

        return new ResponseEntity<>(temp_player, HttpStatus.OK);
    }



    // ---------------------- Monster Actions
    // update
    /// /monster
    @PutMapping(value = "/monster/update/{monsterid}", produces = {"application/json"})
    public ResponseEntity<?> monsterItem(@Valid @RequestBody Monster monster, @PathVariable long monsterid){

        Monster temp_monster = new Monster();

        temp_monster = monsterService.updateMonster(monster, monsterid);

        return new ResponseEntity<>(temp_monster, HttpStatus.OK);
    }





    // ----------------------- Game Actions
    // delete
    // /monsterDefeat/

    @DeleteMapping(value = "/game/monsterdeath/{monsterid}", produces = {"application/json"})
    public ResponseEntity<?> monsterDeath(@PathVariable long monsterId){

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
