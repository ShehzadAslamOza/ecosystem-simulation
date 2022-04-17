package Factories;

import LivingThings.Herbivore;

import java.awt.*;

public class HerbivoreFactory {
    private static HerbivoreFactory instance = null;

    private HerbivoreFactory() {

    }

    public static HerbivoreFactory getInstance() {

        if (instance == null) {
            instance = new HerbivoreFactory();
        }

        return instance;

    }

    public Herbivore generateHerbivore() {
        return new Herbivore();
    }

    public Herbivore generateHerbivore(Point center) {
        return new Herbivore(center);
    }
}
