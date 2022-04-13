package Generators;

import LivingThings.Plant;

public class PlantGenerator {
    private static PlantGenerator instance = null;

    private PlantGenerator() {

    }

    public static PlantGenerator getInstance() {

        if (instance == null) {
            instance = new PlantGenerator();
        }

        return instance;

    }

    public Plant generatePlant() {
        return new Plant();
    }

}
