package Factories;

import LivingThings.*;

import java.awt.*;

/**
 * LivingThingFactory generates new Living THings
 */

public class LivingThingFactory {

    // Only one instance of factory as singleton pattern
    private static LivingThingFactory instance = null;

    /**
     * Factory Constructor
     */
    private LivingThingFactory() {

    }


    /**
     * Returns the instance of the factory
     * @return
     */
    public static LivingThingFactory getInstance() {

        if (instance == null) {
            instance = new LivingThingFactory();
        }

        return instance;

    }

    /**
     * Returns the required Living Thing
     * @param livingThing
     * @return
     */
    public LivingThing getLivingThing(String livingThing) {
        return switch (livingThing) {
            case "PLANT" -> new Plant();
            case "HERBIVORE" -> new Herbivore();
            case "CARNIVORE" -> new Carnivore();
            case "CANNIBAL" -> new Cannibal();
            default -> null;
        };
    }

    /**
     * Returns the required Livingthind and sets is center
     * @param livingThing
     * @param center
     * @return
     */
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
