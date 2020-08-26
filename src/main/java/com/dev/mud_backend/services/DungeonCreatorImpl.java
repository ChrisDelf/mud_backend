package com.dev.mud_backend.services;

import com.dev.mud_backend.models.Cell;
import com.dev.mud_backend.models.Monster;
import com.dev.mud_backend.models.PlacedRooms;
import com.dev.mud_backend.models.Room;
import com.dev.mud_backend.repository.CellRepository;
import com.dev.mud_backend.repository.PlacedRoomsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.IntStream;

@Service(value = "dungeonCreatorService")
public class DungeonCreatorImpl implements DungeonCreatorService{

    @Autowired
    CellRepository cellRepo;

    @Autowired
    PlacedRoomsRepository placedRoomsRepository;

    @Autowired
    private CellService cellService;

    @Autowired
    private MonsterService monsterService;

    @Override
    public ArrayList<Cell> getMap() {

    ArrayList<Cell> returnArray = new ArrayList<>();

        cellRepo.findAll().iterator().forEachRemaining(returnArray::add);
        return returnArray;
    }

    @Override
    public PlacedRooms generateGrid(int gridwidth, int gridheight, int maxrooms, long mapid) {
        ArrayList<ArrayList<Cell>> gridArray = new ArrayList<ArrayList<Cell>>();
        int i = 0;

        while (i < gridheight)
        {
            ArrayList<Cell> row = new ArrayList<Cell>();
            gridArray.add(row);


            for(int j = 0; j < gridwidth; j++){

                Cell cell = new Cell();
                cell.setRoomType("Wall");
                cell.setX(j);
                cell.setY(i);
                cell.setMapid(mapid);
                cellRepo.save(cell);
                row.add(cell);

            }
//            IntStream.range(0, gridwidth).forEach(n -> {
//
//                Cell cell = new Cell();
//                cell.setRoomType("Wall");
//                cell.setX(n);
//                cell.setY(i);
//                cellRepo.save(cell);
//                row.add(cell);
//
//            });

            i++;

        }
        Room room = new Room();
        room.setHeight(4);
        room.setWidth(5);
        room.setY(30);
        room.setX(30);
        int[] myRangeArray = new int[2];
        myRangeArray[0] = 3;
        myRangeArray[1] = 5;





        PlacedRooms seedRoom = new PlacedRooms();
        seedRoom.setGrid(placeCells(gridArray,room, "Floor"));
        seedRoom.getPlacedRooms().add(room);
        seedRoom.setGrid(gridArray);
        seedRoom = growMap(seedRoom, seedRoom.getPlacedRooms(),0, 10,myRangeArray);

//        return gridArray;
        return seedRoom;
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

                if (grid.get(i).get(j).getRoomType() == "Floor") {

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

                    grid.get(i).get(j).setRoomType("Floor");
                    cellRepo.save(grid.get(i).get(j));

                }
                else if (type == "Door"){
                    grid.get(i).get(j).setRoomType("Door");
                    cellRepo.save(grid.get(i).get(j));

                }
                else if (type == "Monster"){
                    grid.get(i).get(j).setRoomType("Monster");
                    cellRepo.save(grid.get(i).get(j));
                    // hello what is going on?
                    
                }


            }



        }

        return grid;
    }

    @Override
    public PlacedRooms createFromSeed(ArrayList<ArrayList<Cell>> grid, Room room, int[] roomRange)
    {
        int mini = roomRange[0];
        int maxi = roomRange[1];

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


        for (int i = 0; i < roomValues.size(); i++){

            if( isValidRoomPlacement(grid, roomValues.get(i))){
//                int x;
//                int y;
//                x = roomValues.get(i).getX();
//                y = roomValues.get(i).getY();

                // placing monster in random room //
                ArrayList<Integer> monsterCoor = new ArrayList<Integer>();
                // creating a new instance of a monster
                int monsterX;
                int monsterY;
                Monster roomMonster = new Monster("Goblin",12,2,4,1,5);
                roomMonster.setMonsterX(rand.ints(roomValues.get(i).getX(),roomValues.get(i).getX() + roomValues.get(i).getWidth()).findFirst().getAsInt());
                roomMonster.setMonsterY(rand.ints(roomValues.get(i).getY(),roomValues.get(i).getY() + roomValues.get(i).getHeight()).findFirst().getAsInt());



                Room newDoor = new Room();
                newDoor.setX(roomValues.get(i).getDoorX());
                newDoor.setY(roomValues.get(i).getDoorY());
                newDoor.setHeight(1);
                newDoor.setWidth(1);
                grid = placeCells(grid,roomValues.get(i), "Floor");
                grid = placeCells(grid,newDoor, "Door");

                roomsPlaced.add(roomValues.get(i));


            }
        }
        PlacedRooms placedRooms = new PlacedRooms();
        placedRooms.setGrid(grid);
        placedRooms.setPlacedRooms(roomsPlaced);



        return placedRooms;
    }

    @Override
    public PlacedRooms growMap(PlacedRooms roomsPlaced, ArrayList<Room> seedRooms, int counter, int maxRooms, int [] roomRange) {

        if ((counter + roomsPlaced.getPlacedRooms().size() > maxRooms) || seedRooms.size() == 0) {
//            placedRoomsRepository.save(roomsPlaced);
            return roomsPlaced;
        }

        if (seedRooms.size() > 0)
        {

            roomsPlaced = createFromSeed(roomsPlaced.getGrid(), seedRooms.remove(0), roomRange);

        }
        PlacedRooms rooms = new PlacedRooms();
        rooms = roomsPlaced;

        for (int i = 0; i < rooms.getPlacedRooms().size(); i++)
        {
            seedRooms.add(rooms.getPlacedRooms().get(i));
        }
        counter += roomsPlaced.getPlacedRooms().size();

        return growMap(roomsPlaced, roomsPlaced.getPlacedRooms(), counter, maxRooms, roomRange);
    }
}
