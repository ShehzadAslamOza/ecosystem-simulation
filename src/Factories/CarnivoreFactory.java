package Factories;

import LivingThings.Carnivore;

import java.awt.*;

public class CarnivoreFactory {
    private static CarnivoreFactory instance = null;

    private CarnivoreFactory() {

    }

    public static CarnivoreFactory getInstance() {

        if (instance == null) {
            instance = new CarnivoreFactory();
        }

        return instance;

    }

    public Carnivore generateCarnivore() {
        return new Carnivore();
    }

    public Carnivore generateCarnivore(Point center) {
        return new Carnivore(center);
    }
}
