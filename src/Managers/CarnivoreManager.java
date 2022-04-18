package Managers;

import Factories.CarnivoreFactory;
import LivingThings.Carnivore;
import LivingThings.Herbivore;
import LivingThings.State;

import java.awt.*;
import java.util.ArrayList;

public class CarnivoreManager {

    public static int totalCarnivoreExisted = 0;
    private int IntialCarnivores = 10;

    ArrayList<Carnivore> carnivoreList = State.carnivoreList;
    CarnivoreFactory carnivoreFactory = CarnivoreFactory.getInstance();

    private void spawnCarnivore(int num) {
        for (int i = 0; i < num; i++) {
            carnivoreList.add(carnivoreFactory.generateCarnivore());
        }
    }

    private void updateHerbivores(Graphics g) {
        int numOfHerbivore = carnivoreList.size();
        int i = 0;

        while (i < numOfHerbivore) {

            //removes dead herbovore
            if (carnivoreList.get(i).isDead()) {
                carnivoreList.remove(i);
                numOfHerbivore--;
                continue;
            }

            // draws Plants
            carnivoreList.get(i).update();
            carnivoreList.get(i).draw(g);
            i++;
        }
    }

    public void updateCarnivoreManager(Graphics g) {
        if (totalCarnivoreExisted == 0) {
            spawnCarnivore(IntialCarnivores);
        }

        updateHerbivores(g);
    }

}
