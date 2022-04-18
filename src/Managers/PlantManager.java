package Managers;

import Factories.PlantFactory;
import LivingThings.Plant;
import LivingThings.State;

import java.awt.*;
import java.util.ArrayList;

public class PlantManager {

    public static int totalPlantsExisted = 0;
    ArrayList<Plant> plantList = State.plantList;


    PlantFactory plantFactory = PlantFactory.getInstance();

    private int timeBeforePlantGeneration = 0;
    private int numOfPlantsToGenerate = 200;

    private void growNewPlants(int num) {
        for (int i = 0; i < num; i++) {
            plantList.add(plantFactory.generatePlant());
        }
    }

    //private void removeDeadPlants();

    private void updatePlants(Graphics g) {
//        for(Plant plant: plantList) {
//            plant.update();
//            plant.draw(g);
//        }
        int numOfPlants = plantList.size();
        int i = 0;

        while (i < numOfPlants) {

            //removes dead plants
            if (plantList.get(i).isDead()) {
                plantList.remove(i);
                numOfPlants--;
                continue;
            }

            // draws Plants
            plantList.get(i).update();
            plantList.get(i).draw(g);
            i++;
        }



    }


    public void updatePlantManager(Graphics g) {

        timeBeforePlantGeneration--;
        if (timeBeforePlantGeneration <= 0) {
            growNewPlants(numOfPlantsToGenerate);
            timeBeforePlantGeneration = 400;
        }

        updatePlants(g);
    }
}
