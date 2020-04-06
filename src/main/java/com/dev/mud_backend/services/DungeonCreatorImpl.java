package com.dev.mud_backend.services;

import com.dev.mud_backend.models.Cell;
import com.dev.mud_backend.models.PlacedRooms;
import com.dev.mud_backend.models.Room;
import com.dev.mud_backend.repository.CellRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.IntStream;

@Service(value = "dungeonCreatorService")
public class DungeonCreatorImpl implements DungeonCreatorService{

    @Autowired
    CellRepository cellRepo;


    @Override
    public ArrayList<ArrayList> generateGrid(int gridwidth, int gridheight, int maxrooms) {
        ArrayList<ArrayList> gridArray = new ArrayList<ArrayList>();
        int i = 0;
        int j = 0;

        while (i < gridheight)
        {
            ArrayList<Cell> row = new ArrayList<Cell>();
            gridArray.add(row);

            j = 0;

            IntStream.range(0, gridwidth).forEach(n -> {
                Cell cell = new Cell();
                cell.setRoomType("Wall");
                cellRepo.save(cell);
                row.add(cell);

            });

            i++;

        }

        return gridArray;
    }

    @Override
    public PlacedRooms createFromSeed(ArrayList<ArrayList> grid, Room room, int[] roomRange)
    {

        int mini = roomRange[0];
        int maxi = roomRange[1];

        ArrayList<Room> roomValues = new ArrayList<Room>();

        Random rand = new Random();





        // creating a possible north room
        Room north = new Room();
        north.setHeight(rand.ints(mini,(maxi+1)).findFirst().getAsInt());
        System.out.print(north.getHeight());
        north.setWidth(rand.ints(mini,(maxi+1)).findFirst().getAsInt());
        north.setX(rand.ints(room.getX(),(room.getX() + room.getWidth())).findFirst().getAsInt());
        north.setY(room.getY() - (north.getHeight())-1);
        // creating the door
        // chose the smallest max
        int doorMaxN = Math.min((north.getX() + north.getWidth()),(room.getY() + north.getHeight()));

        north.setDoorX(rand.ints(north.getX(),doorMaxN+1).findFirst().getAsInt());
        north.setDoorY(room.getY()-1);

        roomValues.add(north);

        //creating a possible east room
        Room east = new Room();
        east.setHeight(rand.ints(mini,(maxi+1)).findFirst().getAsInt());

        east.setWidth(rand.ints(mini,(maxi+1)).findFirst().getAsInt());
        east.setX(room.getX() + (room.getWidth())+1);
        east.setY(rand.ints(room.getY(),(room.getY() + room.getHeight())).findFirst().getAsInt());
        // creating the door
        // chose the smallest max
        int doorMaxE = Math.min((east.getX() + east.getWidth()),(room.getY() + east.getHeight()));

        east.setDoorX(east.getX()-1);
        east.setDoorY(rand.ints(east.getY(),doorMaxE-1).findFirst().getAsInt());

        roomValues.add(east);







        return null;
    }
}
