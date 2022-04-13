package Managers;

import Generators.PlantGenerator;
import LivingThings.Plant;

import java.awt.*;
import java.util.ArrayList;

public class PlantManager {

    ArrayList<Plant> plantList = new ArrayList<>();

    PlantGenerator plantGenerator = PlantGenerator.getInstance();
    public static int totalPlantsExisted = 0;
    private int timeBeforePlantGeneration = 0;
    private int numOfPlantsToGenerate = 20;

    private void growNewPlants(int num) {
        for (int i = 0; i < num; i++) {
            plantList.add(plantGenerator.generatePlant());
        }
    }

    //private void removeDeadPlants();

    private void updatePlants(Graphics g) {
        for(Plant plant: plantList) {
            plant.update();
            plant.draw(g);
        }
    }


    public void updatePlantManager(Graphics g) {

        timeBeforePlantGeneration--;
        if (timeBeforePlantGeneration <= 0) {
            growNewPlants(numOfPlantsToGenerate);
            timeBeforePlantGeneration = 600;
        }

        //removeDeadPlants

        updatePlants(g);
    }
}
