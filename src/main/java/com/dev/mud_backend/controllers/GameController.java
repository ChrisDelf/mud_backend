package com.dev.mud_backend.controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/game")
public class GameController {
    private static final Logger logger = LoggerFactory.getLogger(GameController.class);



    //--------------------------- Player Actions
    // update
    /// /player/pickupItem/

    // update
    // /player/dropItem/

    //Get
    // /player/checkInventory/

    //Get
    // /player/inspect

    // update
    // /player/combatAction



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
