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
        Cell target_Cell = new Cell();

        if(cellService.getCellById(cellId) != null){

            return null;
        }
        else{
            target_Cell = cellService.getCellById(cellId);

        }



        return null;
    }
}
