package com.dev.mud_backend.controllers;

import com.dev.mud_backend.DungeonCreator;
import com.dev.mud_backend.models.*;
import com.dev.mud_backend.repository.CellRepository;
import com.dev.mud_backend.repository.MapRepository;
import com.dev.mud_backend.services.DungeonCreatorService;
import com.dev.mud_backend.services.MapService;
import com.dev.mud_backend.services.UserService;
import com.google.gson.Gson;
import com.google.gson.internal.$Gson$Preconditions;
import io.swagger.annotations.ApiOperation;
import org.codehaus.jackson.map.ObjectMapper;
import org.h2.util.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import java.util.Arrays;
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

        Map newMap = new Map();
        newMap.setWidth(50);
        newMap.setHeight(50);
        newMap.setPlayerx(32);
        newMap.setPlayery(32);
        newMap.setUser(userService.findUserById(userid));
        mapRepo.save(newMap);

        PlacedRooms dungeonArray = dungeonCreatorService.generateGrid(50,50,2, newMap.getMapid());


        ArrayList<ArrayList<String>> visualGrid = new ArrayList<>();
//        int y = 0;
//        for (int i = 0; i < dungeonArray.getGrid().size(); i++)
//        {
//            ArrayList<String> row_string = new ArrayList<>();
//            for(int j = 0; j < dungeonArray.getGrid().get(y).size() - 1; j++) {
//
//                if (dungeonArray.getGrid().get(i).get(j).getRoomType() == "Floor") {
//
//                    row_string.add("f");
//                }
//                if (dungeonArray.getGrid().get(i).get(j).getRoomType() == "Wall") {
//
//                    row_string.add("W");
//                }
//                if (dungeonArray.getGrid().get(i).get(j).getRoomType() == "Door") {
//
//                    row_string.add("D");
//                }
//
//
//            }
//            row_string.add("\n");
//            y++;
//            visualGrid.add(row_string);
//
//        }
//        System.out.println(visualGrid);

        Gson gson = new Gson();

        String json = gson.toJson(dungeonArray.getGrid());


        newMap.setGrid(json);

        mapRepo.save(newMap);
        System.out.println("Dungeon array"+ dungeonArray);


//        dungeonCreatorService.createFromSeed(dungeonArray,room,myRangeArray);
//        System.out.println(seedRoom.getPlacedRooms().size());
//        seedRoom = dungeonCreatorService.growMap(seedRoom, seedRoom.getPlacedRooms(),0, 10,myRangeArray);

        return  new ResponseEntity<>(dungeonArray.getGrid(),HttpStatus.OK);
    }
    @GetMapping (value = "/display/{username}", produces = {"application/json"})
    public ResponseEntity<?> getUserInfo(@Valid @PathVariable String username){
//        UserDetails tempUser = userService.loadUserByUsername(username);
        Long userid = userService.findUserID(username);

    return new ResponseEntity<>(userid, HttpStatus.OK);
    }
    @GetMapping(value ="/getmap/{userid}", produces = {"application/json"})
    public ResponseEntity<?> grabMap(@Valid @PathVariable Long userid){
//        ArrayList<Cell> mapArray = new ArrayList<Cell>();
        List<Map> mapList = new ArrayList<>();
        List<Map> gridList = new ArrayList<>();

        mapList = mapService.getMap(userid);
    for( int i = 0; i < mapList.size(); i++)
    {
       Map tempMap = new Map();
//        Gson gson = new Gson();
//       int [][] dataGrid = gson.fromJson(mapList.get(i).getGrid(), int[][].class);

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
}
