package com.dev.mud_backend.services;


import com.dev.mud_backend.exceptions.ResourceNotFoundException;
import com.dev.mud_backend.models.Cell;
import com.dev.mud_backend.repository.CellRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service (value = "cellService")
public class CellServiceImpl implements CellService{

    @Autowired
    CellRepository cellRepo;

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
    public Cell updateCell(Cell cell) {
//        Cell update_cell = cell(cell.getCellid());

        return null;
    }
}
