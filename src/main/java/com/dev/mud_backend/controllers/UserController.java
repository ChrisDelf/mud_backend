package com.dev.mud_backend.controllers;

import com.dev.mud_backend.models.*;
import com.dev.mud_backend.repository.CellRepository;
import com.dev.mud_backend.repository.MapRepository;
import com.dev.mud_backend.services.DungeonCreatorService;
import com.dev.mud_backend.services.MapService;
import com.dev.mud_backend.services.UserService;
import com.google.gson.Gson;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController
{
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    CellRepository cellRepo;

    @Autowired
    private DungeonCreatorService dungeonCreatorService;

    @Autowired
    private UserService userService;

    @Autowired
    MapRepository mapRepo;

    @Autowired
    private MapService mapService;

    @GetMapping(value = "/users",
            produces = {"application/json"})
    public ResponseEntity<?> listAllUsers(HttpServletRequest request)
    {
        logger.trace(request.getMethod()
                .toUpperCase() + " " + request.getRequestURI() + " accessed");

        List<User> myUsers = userService.findAll();
        return new ResponseEntity<>(myUsers, HttpStatus.OK);
    }


    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping(value = "/user/{userId}",
            produces = {"application/json"})
    public ResponseEntity<?> getUserById(HttpServletRequest request,
                                         @PathVariable
                                                 Long userId)
    {
        logger.trace(request.getMethod()
                .toUpperCase() + " " + request.getRequestURI() + " accessed");

        User u = userService.findUserById(userId);
        return new ResponseEntity<>(u, HttpStatus.OK);
    }


    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping(value = "/user/name/{userName}",
            produces = {"application/json"})
    public ResponseEntity<?> getUserByName(HttpServletRequest request,
                                           @PathVariable
                                                   String userName)
    {
        logger.trace(request.getMethod()
                .toUpperCase() + " " + request.getRequestURI() + " accessed");

        User u = userService.findByName(userName);
        return new ResponseEntity<>(u, HttpStatus.OK);
    }


    @GetMapping(value = "/getusername",
            produces = {"application/json"})
    @ResponseBody
    public ResponseEntity<?> getCurrentUserName(HttpServletRequest request, Authentication authentication)
    {
        logger.trace(request.getMethod()
                .toUpperCase() + " " + request.getRequestURI() + " accessed");

        return new ResponseEntity<>(authentication.getPrincipal(), HttpStatus.OK);
    }


    @ApiOperation("Registers a user")
    @PostMapping(value = "/register",
            consumes = {"application/json"},
            produces = {"application/json"})
    public ResponseEntity<?> addNewUser(HttpServletRequest request, @Valid
    @RequestBody
            User newuser) throws URISyntaxException
    {


        logger.trace(request.getMethod()
                .toUpperCase() + " " + request.getRequestURI() + " accessed");

        newuser = userService.save(newuser);

        // set the location header for the newly created resource

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newUserURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{userid}")
                .buildAndExpand(newuser.getUserid())
                .toUri();
        responseHeaders.setLocation(newUserURI);
        responseHeaders.add("Authorization", "Basic " );


        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }


    @PutMapping(value = "/user/{id}")
    public ResponseEntity<?> updateUser(HttpServletRequest request,
                                        @RequestBody
                                                User updateUser,
                                        @PathVariable
                                                long id)
    {
        logger.trace(request.getMethod()
                .toUpperCase() + " " + request.getRequestURI() + " accessed");

        userService.update(updateUser, id, request.isUserInRole("ADMIN"));
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> deleteUserById(HttpServletRequest request,
                                            @PathVariable
                                                    long id)
    {
        logger.trace(request.getMethod()
                .toUpperCase() + " " + request.getRequestURI() + " accessed");

        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/user/{userid}/role/{roleid}")
    public ResponseEntity<?> deleteUserRoleByIds(HttpServletRequest request,
                                                 @PathVariable
                                                         long userid,
                                                 @PathVariable
                                                         long roleid)
    {
        logger.trace(request.getMethod()
                .toUpperCase() + " " + request.getRequestURI() + " accessed");

        userService.deleteUserRole(userid, roleid);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/user/{userid}/role/{roleid}")
    public ResponseEntity<?> postUserRoleByIds(HttpServletRequest request,
                                               @PathVariable
                                                       long userid,
                                               @PathVariable
                                                       long roleid)
    {
        logger.trace(request.getMethod()
                .toUpperCase() + " " + request.getRequestURI() + " accessed");

        userService.addUserRole(userid, roleid);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping(value ="/test/{userid}", produces = {"application/json"})
    public ResponseEntity<?> getTest(@Valid @PathVariable long userid) {

        ArrayList<ArrayList<Cell>> grid = new ArrayList<ArrayList<Cell>>();
        Map newMap = new Map();
        newMap.setWidth(50);
        newMap.setHeight(50);
        newMap.setPlayerx(32);
        newMap.setPlayery(32);
        newMap.setUser(userService.findUserById(userid));
        mapRepo.save(newMap);
        grid = dungeonCreatorService.generateGrid(50,50,2, newMap.getMapid());

        ArrayList<ArrayList<String>> visualGrid = new ArrayList<>();


        Gson gson = new Gson();

        String json = gson.toJson(grid);


        newMap.setGrid(json);

        mapRepo.save(newMap);



        return  new ResponseEntity<>(newMap,HttpStatus.OK);
    }
    @GetMapping (value = "/display/{username}", produces = {"application/json"})
    public ResponseEntity<?> getUserInfo(@Valid @PathVariable String username){
        UserDetails tempUser = userService.loadUserByUsername(username);
        Long userid = userService.findUserID(username);

    return new ResponseEntity<>(userid, HttpStatus.OK);
    }
    @GetMapping(value ="/getmap/{userid}", produces = {"application/json"})
    public ResponseEntity<?> grabMap(@Valid @PathVariable Long userid){

        List<Map> mapList = new ArrayList<>();
        List<Map> gridList = new ArrayList<>();

        mapList = mapService.getMap(userid);
    for( int i = 0; i < mapList.size(); i++)
    {
       Map tempMap = new Map();


       tempMap.setGrid(mapList.get(i).getGrid());
       tempMap.setHeight(mapList.get(i).getHeight());
       tempMap.setWidth(mapList.get(i).getWidth());
       tempMap.setMapid(mapList.get(i).getMapid());
        gridList.add(tempMap);
    }

        return new ResponseEntity<> (gridList, HttpStatus.OK);
    }

    // need to give the user his player location.
    @GetMapping(value ="/playerlocation/{mapid}", produces = {"application/json"})
    public ResponseEntity<?> getUserPosition(@Valid @PathVariable Long mapid){
        Map tempMap = new Map();

        tempMap = mapService.getPlayerLocation(mapid);

        Map resMap = new Map();

        resMap.setPlayerx(tempMap.getPlayerx());
        resMap.setPlayery(tempMap.getPlayery());
        resMap.setGrid(tempMap.getGrid());
        resMap.setWidth(tempMap.getWidth());
        resMap.setHeight(tempMap.getHeight());


        return new ResponseEntity<>(resMap, HttpStatus.OK);
    }

    @PutMapping(value ="/moveplayer/{mapid}")
    public ResponseEntity<?> movePlayer(
            @RequestBody
                    Map updateMap,
            @PathVariable long mapid
    )
    {

        mapService.updatePlayer(updateMap, mapid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    @GetMapping(value ="/mapdetails/{mapid}",
//            produces = {"application/json"})
//    public ResponseEntity<?> getMapDetails(@Valid @PathVariable Long mapid){
//
//        HashMap<Long, ArrayList<Long>> roomsAndMonsterLists = mapService.getMapDetails(mapid);
//
//
//        return new ResponseEntity<>(roomsAndMonsterLists, HttpStatus.OK);
//    }
}
