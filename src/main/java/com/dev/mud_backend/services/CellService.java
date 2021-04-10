package com.dev.mud_backend.services;

import com.dev.mud_backend.models.Cell;

public interface CellService {


    Cell getCellById(long id);

    String addorRemoveM(long monsterid, String command);

    Cell updateCell(Cell cell);
}
