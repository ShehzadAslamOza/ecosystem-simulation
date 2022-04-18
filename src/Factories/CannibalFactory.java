package Factories;

import LivingThings.Cannibal;


import java.awt.*;

public class CannibalFactory {
    private static CannibalFactory instance = null;

    private CannibalFactory() {

    }

    public static CannibalFactory getInstance() {

        if (instance == null) {
            instance = new CannibalFactory();
        }

        return instance;

    }

    public Cannibal generateCarnivore() {
        return new Cannibal();
    }

    public Cannibal generateCarnivore(Point center) {
        return new Cannibal(center);
    }
}
