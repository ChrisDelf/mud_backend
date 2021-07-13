package com.dev.mud_backend.services;


import com.dev.mud_backend.exceptions.ResourceNotFoundException;
import com.dev.mud_backend.models.Cell;
import com.dev.mud_backend.repository.CellRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service (value = "cellService")
public class CellServiceImpl implements CellService{

    @Autowired
    CellRepository cellRepo;

    @Autowired
    CellService cellService;

    @Override
    public Cell getCellById(long id) {
        Cell s_cell = new Cell();
        s_cell = cellRepo.findByCellid(id);
        if ( s_cell == null) {
            throw new ResourceNotFoundException("The player by the id" + id + " was not found");
        }
        else {
            return  s_cell;
        }
    }

    @Override
    public String addorRemoveM(long monsterid, String command) {
        if (command == "remove"){


        }

        if (command == "monsterid"){

        }

        return null;
    }

    @Override
    public Cell removeEntityFromCell(Cell cell, String entity, long id) {

        if (entity == "player" ) {
            ArrayList<Long> temp_character_array = cell.getContainsP();
            for (int i = 0; i < temp_character_array.size(); i++) {
                if (temp_character_array.get(i) == id) {
                    temp_character_array.remove(id);
                }
            }
        }

            if (entity == "item" ) {
                ArrayList<Long> temp_item_array = cell.getContainsP();
                for(int i = 0; i < temp_item_array.size(); i ++){
                    if (temp_item_array.get(i) == id){
                        temp_item_array.remove(id);
                    }
                }

            }

            if (entity == "monster" ) {
                ArrayList<Long> temp_monster_array = cell.getContainsP();
                for (int i = 0; i < temp_monster_array.size(); i++) {
                    if (temp_monster_array.get(i) == id) {
                        temp_monster_array.remove(id);
                    }
                }

            }
            cellRepo.save(cell);
                return cell;
    }

    @Override
    public Cell updateCell(Cell cell, long cellId) {
        Cell target_cell = new Cell();

        if(cellService.getCellById(cellId) != null){
            target_cell = cellService.getCellById(cellId);


        }

        else{
          return null;
        }

        if (cell.getX() != target_cell.getX()){
            target_cell.setX(cell.getX());

        }
        if (cell.getY() != target_cell.getY()){
            target_cell.setY(cell.getY());

        }

        if (cell.getCellType() != target_cell.getCellType()){
            target_cell.setCellType(cell.getCellType());

        }

        if(cell.getRoomid() != target_cell.getRoomid()){
            target_cell.setRoomid(cell.getRoomid());
        }

        if(cell.getMapid() != target_cell.getMapid()){
            target_cell.setMapid(cell.getMapid());
        }

        if(cell.getContainsM().size() != target_cell.getContainsM().size())
        {
            target_cell.setContainsM(cell.getContainsM());

        }
        if(cell.getContainsP().size() != target_cell.getContainsP().size())
        {
            target_cell.setContainsP(cell.getContainsP());

        }
        cellRepo.save(target_cell);
        return  target_cell;
    }
}
