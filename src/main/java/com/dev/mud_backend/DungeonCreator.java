package com.dev.mud_backend;

import com.dev.mud_backend.models.Cell;
import com.dev.mud_backend.repository.CellRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.stream.IntStream;

public class DungeonCreator {

    @Autowired
    CellRepository cellRepo;
//
//    private long gridwidth;
//
//    private long gridheight;
//
//    private int maxrooms;
//
//    private int roomsizerange;
//
//    ArrayList<ArrayList> gridArray;
//
//    List<Long> grid = new ArrayList<>();
//
//
////    public CreateDungeon() {
////    }
//
//    public DungeonCreator(long gridwidth, long gridheight, int maxrooms, int roomsizerange) {
//        this.gridwidth = gridwidth;
//        this.gridheight = gridheight;
//        this.maxrooms = maxrooms;
//        this.roomsizerange = roomsizerange;
//        this.gridArray = new ArrayList<ArrayList>();
//    }

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
                cell.setCellType("Wall");
                cellRepo.save(cell);
                row.add(cell);

            });

            i++;

        }

        return gridArray;
    }
    }



