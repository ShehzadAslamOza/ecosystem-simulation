package Factories;

import LivingThings.*;

import java.awt.*;

public class LivingThingFactory {
    private static LivingThingFactory instance = null;

    private LivingThingFactory() {

    }

    public static LivingThingFactory getInstance() {

        if (instance == null) {
            instance = new LivingThingFactory();
        }

        return instance;

    }

    public LivingThing getLivingThing(String livingThing) {
        return switch (livingThing) {
            case "PLANT" -> new Plant();
            case "HERBIVORE" -> new Herbivore();
            case "CARNIVORE" -> new Carnivore();
            case "CANNIBAL" -> new Cannibal();
            default -> null;
        };
    }

    public LivingThing getLivingThing(String livingThing, Point center) {
        return switch (livingThing) {
            case "PLANT" -> new Plant();
            case "HERBIVORE" -> new Herbivore(center);
            case "CARNIVORE" -> new Carnivore(center);
            case "CANNIBAL" -> new Cannibal(center);
            default -> null;
        };
    }
}
