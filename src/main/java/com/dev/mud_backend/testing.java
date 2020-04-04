package com.dev.mud_backend;

import com.dev.mud_backend.models.Cell;

import java.util.ArrayList;

public class testing {
    public static void main() {
        Cell cell1 = new Cell(1,2,"floor", 3, 4);

        ArrayList testArray = new ArrayList();
        testArray.add(cell1);
        System.out.println(testArray);

    }
}
