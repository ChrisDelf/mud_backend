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
        return cellRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Room id " + id + " not found!"));
    }
}
