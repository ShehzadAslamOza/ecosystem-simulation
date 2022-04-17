package Factories;

import LivingThings.Plant;

public class PlantFactory {
    private static PlantFactory instance = null;

    private PlantFactory() {

    }

    public static PlantFactory getInstance() {

        if (instance == null) {
            instance = new PlantFactory();
        }

        return instance;

    }

    public Plant generatePlant() {
        return new Plant();
    }

}
