package com.dev.mud_backend.services;

import org.springframework.stereotype.Service;

import java.util.ArrayList;



public interface DungeonCreatorService {

    ArrayList<ArrayList> generateGrid(int gridwidth, int gridheight, int maxrooms);
}
