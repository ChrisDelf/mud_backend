package com.dev.mud_backend.services;

import com.dev.mud_backend.models.*;
import com.dev.mud_backend.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service(value = "dungeonCreatorService")
public class DungeonCreatorImpl implements DungeonCreatorService{

    @Autowired
    CellRepository cellRepo;

    @Autowired
    PlacedRoomRepository placedRoomRepo;

    @Autowired
    RoomRepository roomRepo;

    @Autowired
    MapRepository mapRepo;


    @Autowired
    private CellService cellService;

    @Autowired
    private MonsterService monsterService;

    @Autowired
    private MonsterRepository monsterRepo;

    @Autowired
    private MapService mapService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private PlayerService playerService;

    @Override
    public ArrayList<Cell> getMap() {

    ArrayList<Cell> returnArray = new ArrayList<>();

        cellRepo.findAll().iterator().forEachRemaining(returnArray::add);
        return returnArray;
    }

    @Override
    public ArrayList<ArrayList<Cell>> generateGrid(int gridwidth, int gridheight, int maxrooms, long mapid, Map map) {
        ArrayList<ArrayList<Cell>> gridArray = new ArrayList<ArrayList<Cell>>();
        int i = 0;

        Room room = new Room();
        room.setHeight(4);
        room.setWidth(5);
        room.setY(30);
        room.setX(30);
        // setting the room to a map id
        room.setMap(mapService.findById(mapid));
        roomRepo.save(room);


        while (i < gridheight)
        {
            ArrayList<Cell> row = new ArrayList<Cell>();
            gridArray.add(row);


            for(int j = 0; j < gridwidth; j++){


                Cell cell = new Cell();
                cell.setCellType("Wall");
                cell.setX(j);
                cell.setY(i);
                cell.setMapid(mapid);
                ArrayList<Long> fillerArray = new ArrayList<>();
                cell.setContainsP(fillerArray);
                cell.setContainsM(fillerArray);
                if (cell.getX() == mapService.findById(mapid).getPlayers().get(0).getPlayerx() && cell.getY() == mapService.findById(mapid).getPlayers().get(0).getPlayery()){
                    Long playerId = mapService.findById(mapid).getPlayers().get(0).getPlayerid();
                    Player temp_player = playerService.findById(playerId);
                    ArrayList<Long> tempArray = new ArrayList<>();
                    tempArray.add(mapService.findById(mapid).getPlayers().get(0).getPlayerid());
                    cell.setContainsP(tempArray);
                    cellRepo.save(cell);
                    temp_player.setCellId(cell.getCellid());

                }

                cellRepo.save(cell);

                row.add(cell);

            }

            i++;

        }


        int[] myRangeArray = new int[2];
        myRangeArray[0] = 3;
        myRangeArray[1] = 5;



        PlacedRoom seedRoom = new PlacedRoom();
        gridArray = placeCells(gridArray,room, "Floor");
        seedRoom.getPlacedRooms().add(room);
        seedRoom.setGrid(gridArray);

        gridArray = growMap(seedRoom,0, 10,myRangeArray,mapid).getGrid();

        return gridArray;
    }


    @Override
    public boolean isValidRoomPlacement(ArrayList<ArrayList<Cell>> grid, Room room) {

        // making sure the cells aren't out of bounds of the grid

        int roomY = room.getY();
        int roomX = room.getX();

        if (roomY < 1 || (roomY + room.getHeight() > grid.size() - 1)) {

            return false;

        }

        if (roomX < 1 || roomX + room.getWidth() > grid.get(0).size()) {


            return false;
        }

        for (int i = roomY; i-1 < (room.getHeight() + room.getY()); i++) {

            for (int j = roomX; j-1 < (room.getWidth() + room.getX()); j++) {

                if (grid.get(i).get(j).getCellType() == "Floor") {

                    return false;

                }


            }



        }

        return true;
    }


    @Override
    public ArrayList<ArrayList<Cell>> placeCells(ArrayList<ArrayList <Cell>> grid, Room room, String type) {


        int roomX = room.getX();
        int roomY = room.getY();

        if (type == null)
        {
            type = "Floor";
        }

        for (int i = roomY; i < (room.getHeight() + room.getY()); i++) {

            for (int j = roomX; j < (room.getWidth() + room.getX()); j++) {


                if (type == "Floor") {

                    grid.get(i).get(j).setCellType("Floor");
                    grid.get(i).get(j).setRoomid(room.getRoomId());
                    cellRepo.save(grid.get(i).get(j));

                }

                else if (type == "Door"){
                    grid.get(i).get(j).setCellType("Door");
                    grid.get(i).get(j).setRoomid(room.getRoomId());
                    cellRepo.save(grid.get(i).get(j));

                }



            }



        }

        return grid;
    }

    @Override
    public PlacedRoom createFromSeed(PlacedRoom placedRoom, Room room, int[] roomRange, long mapid)
    {
        int mini = roomRange[0];
        int maxi = roomRange[1];

        ArrayList<ArrayList<Cell>> grid = new ArrayList<ArrayList<Cell>>();
        grid = placedRoom.getGrid();
        ArrayList<Room> roomValues = new ArrayList<Room>();

        Random rand = new Random();

        // creating a possible north room
        Room north = new Room();
        // generating random height and width of the room
        north.setHeight(rand.ints(mini,(maxi+1)).findFirst().getAsInt());
        north.setWidth(rand.ints(mini,(maxi+1)).findFirst().getAsInt());
        // X and Y being set
        north.setX(rand.ints(room.getX(),(room.getX() + room.getWidth())).findFirst().getAsInt());
        north.setY((room.getY() - (north.getHeight()))-1);
        // creating the door
        // chose the smallest max
        int doorMaxN = Math.min((north.getX() + north.getWidth()),(room.getX() + room.getWidth()));

        north.setDoorX(rand.ints(north.getX(),doorMaxN).findFirst().getAsInt());
        north.setDoorY(room.getY()-1);

        roomValues.add(north);

        //creating a possible east room
        Room east = new Room();
        // generating random height and width of the room
        east.setHeight(rand.ints(mini,(maxi+1)).findFirst().getAsInt());
        east.setWidth(rand.ints(mini,(maxi+1)).findFirst().getAsInt());
        // X and Y being set
        east.setX(room.getX() + (room.getWidth())+1);
        east.setY(rand.ints(room.getY(),(room.getY() + room.getHeight())).findFirst().getAsInt());
        // creating the door
        // chose the smallest max
        int doorMaxE = Math.min((east.getY() + east.getHeight()),(room.getY() + room.getHeight()));

        east.setDoorX(east.getX()-1);
        east.setDoorY(rand.ints(east.getY(),doorMaxE).findFirst().getAsInt());

        roomValues.add(east);

        //creating a possible west room

        Room west = new Room();
        // generating random height and width of the room
        west.setHeight(rand.ints(mini,(maxi+1)).findFirst().getAsInt());
        west.setWidth(rand.ints(mini,(maxi+1)).findFirst().getAsInt());
        // X and Y being set
        west.setX(room.getX()-(west.getWidth()+1));
        west.setY(rand.ints(room.getY(),(room.getY() + room.getHeight())).findFirst().getAsInt());
        //Creating a door
        int doorMaxW = Math.min((west.getY() + west.getHeight()),(room.getY() + room.getHeight()));

        west.setDoorX(room.getX()-1);
        west.setDoorY(rand.ints(west.getY(),doorMaxW).findFirst().getAsInt());

        roomValues.add(west);


        //creating a possible south room

        Room south = new Room();
        // generating random height and width of the room
        south.setHeight(rand.ints(mini,(maxi+1)).findFirst().getAsInt());
        south.setWidth(rand.ints(mini,(maxi+1)).findFirst().getAsInt());
        // X and Y being set
        south.setX(rand.ints(room.getX(),(room.getX() + room.getWidth())).findFirst().getAsInt());
        south.setY(room.getY() + (room.getHeight())+1);
        //creating a door

        int doorMaxS = Math.min((south.getX() + south.getWidth()),(room.getX() + room.getWidth()));

        south.setDoorX(rand.ints(south.getX(),doorMaxS).findFirst().getAsInt());
        south.setDoorY(room.getY()+room.getHeight());

        roomValues.add(south);



        // now we go iterate through the roomvalues to see if we can place them
        ArrayList<Room> roomsPlaced = new ArrayList<>();

        PlacedRoom placedRooms = new PlacedRoom();

        placedRooms.setPlacedRooms(roomsPlaced);
        placedRooms.setGrid(grid);

        for (int i = 0; i < roomValues.size(); i++){

            if( isValidRoomPlacement(grid, roomValues.get(i))){
                roomValues.get(i).setMap(mapRepo.findByMapid(mapid));
                roomRepo.save(roomValues.get(i));
                // setting up ids
                roomValues.get(i).setMap(mapService.findById(mapid));

                // Creating a door
                Room newDoor = new Room();
                newDoor.setX(roomValues.get(i).getDoorX());
                newDoor.setY(roomValues.get(i).getDoorY());
                newDoor.setHeight(1);
                newDoor.setWidth(1);
                //map getting associated with the door
                newDoor.setMap(mapService.findById(mapid));
                roomRepo.save(newDoor);
                // Creating a monster
                Monster newMonster = new Monster("Gobo",5,2,2,1,1,10,"standing");

                newMonster.setMonsterX(rand.ints(roomValues.get(i).getX(), (roomValues.get(i).getX() + roomValues.get(i).getWidth() - 1)).findFirst().getAsInt());
                newMonster.setMonsterY(rand.ints(roomValues.get(i).getY(), (roomValues.get(i).getY() + roomValues.get(i).getHeight() - 1)).findFirst().getAsInt());



              //  System.out.println("Room" + (roomValues.get(i).getX() + roomValues.get(i).getWidth()) + " " + (roomValues.get(i).getY() + roomValues.get(i).getHeight()) );
              //  System.out.println("Monster" + newMonster.getMonsterX() + " " + newMonster.getMonsterY());
                newMonster.setMap(mapRepo.findByMapid(mapid));
                monsterRepo.save(newMonster);
                // next we are going to place the string monsters monster into the cell
                // for the next project I should have created and entity model
                Cell tempCell = grid.get(newMonster.getMonsterY()).get(newMonster.getMonsterX());

                ArrayList<Long> tempArray = new ArrayList<Long>();
                tempArray.add(newMonster.getMonsterid());
                tempCell.setContainsM(tempArray);
                cellRepo.save(tempCell);



                //


                //connecting the newly create monster with the map
                Map temp_map = mapService.findById(mapid);
                List<Monster> monster_list_t = new ArrayList<>();
                monster_list_t = temp_map.getMonsters();
                monster_list_t.add(newMonster);

                roomRepo.save(roomService.findById(roomValues.get(i).getRoomId()));
                mapRepo.save(temp_map);


                Room constructRoom = roomService.findById(roomValues.get(i).getRoomId());




                // Placing the cells to create the room

                // for the room it self
                grid = placeCells(grid,constructRoom, "Floor");
                // for the door
                grid = placeCells(grid,newDoor, "Door");



                roomsPlaced.add(roomValues.get(i));

                placedRooms.setGrid(grid);
            }
        }

        // we have the save the grid to save the lastest update to the grid



        return placedRooms;
    }

    @Override
    public PlacedRoom growMap(PlacedRoom roomsPlaced, int counter, int maxRooms, int [] roomRange, long mapid) {
      //  System.out.println("Counter : " + counter + " Rooms Placed" +  roomsPlaced.getPlacedRooms().size());
        if ((counter + roomsPlaced.getPlacedRooms().size() > maxRooms) || roomsPlaced.getPlacedRooms().size() == 0) {

            return roomsPlaced;
        }

        if (roomsPlaced.getPlacedRooms().size() > 0)
        {

            roomsPlaced = createFromSeed(roomsPlaced, roomsPlaced.getPlacedRooms().remove(0), roomRange, mapid);

        }

        PlacedRoom rooms = roomsPlaced;

        counter += roomsPlaced.getPlacedRooms().size();

        return growMap(roomsPlaced, counter, maxRooms, roomRange,mapid);
    }
}
