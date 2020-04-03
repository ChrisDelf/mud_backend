package com.dev.mud_backend;

import com.dev.mud_backend.models.Room;

import java.util.ArrayList;

public class testing {
    public static void main() {
        Room room1 = new Room(1,2,"floor", 3, 4);

        ArrayList testArray = new ArrayList();
        testArray.add(room1);
        System.out.println(testArray);

    }
}
