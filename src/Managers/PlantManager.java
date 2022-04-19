package Managers;

import Factories.PlantFactory;
import LivingThings.LivingThing;
import LivingThings.Plant;
import LivingThings.State;

import java.awt.*;
import java.util.ArrayList;

public class PlantManager extends LivingThingManager{

    public static int totalPlantsExisted = 0;
    private int timeBeforePlantGeneration = 0;
    private int numOfPlantsToGenerate = State.NUM_PLANT_TO_GENERATE_INITIAL;
    ArrayList<LivingThing> plantList = State.plantList;





    private void spawnPlants(int num) {
        for (int i = 0; i < num; i++) {
            plantList.add(livingThingFactory.getLivingThing("PLANT"));
        }
    }


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
