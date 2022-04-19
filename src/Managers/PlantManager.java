package Managers;

import LivingThings.LivingThing;
import LivingThings.State;

import java.awt.*;
import java.util.ArrayList;

/**
 * Manages the plant
 */
public class PlantManager extends LivingThingManager{

    public static int totalPlantsExisted = 0;
    private int timeBeforePlantGeneration = 0;
    private int numOfPlantsToGenerate = State.NUM_PLANT_TO_GENERATE_INITIAL;
    ArrayList<LivingThing> plantList = State.plantList;


    /**
     * Spawns plants randomly on the screen
     * @param num
     */
    private void spawnPlants(int num) {
        for (int i = 0; i < num; i++) {
            plantList.add(livingThingFactory.getLivingThing("PLANT"));
        }
    }

    /**
     * updates the manager that update the plants
     * @param g
     */
    public void updateManager(Graphics g) {

        timeBeforePlantGeneration--;
        if (timeBeforePlantGeneration <= 0) {
            spawnPlants(numOfPlantsToGenerate);
            timeBeforePlantGeneration = State.TIME_BEFORE_NEW_PLANTS;
            numOfPlantsToGenerate = State.NUM_PLANT_TO_GENERATE;
        }

        updateLivingThing(g, plantList);
    }
}
