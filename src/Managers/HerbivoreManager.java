package Managers;

import Factories.HerbivoreFactory;
import LivingThings.Herbivore;
import LivingThings.State;

import java.awt.*;
import java.util.ArrayList;

public class HerbivoreManager {

    public static int totalHerbivoreExisted = 0;
    private int InitialHerbivores = 50;

    ArrayList<Herbivore> herbivoreList = State.herbivoreList;
    HerbivoreFactory herbivoreFactory = HerbivoreFactory.getInstance();

    private void spawnHerbivores(int num) {
        for (int i = 0; i < num; i++) {
            herbivoreList.add(herbivoreFactory.generateHerbivore());
        }
    }

    private void updateHerbivores(Graphics g) {
        int numOfHerbivore = herbivoreList.size();
        int i = 0;

        while (i < numOfHerbivore) {

            //removes dead herbovore
            if (herbivoreList.get(i).isDead()) {
                herbivoreList.remove(i);
                numOfHerbivore--;
                continue;
            }

            // draws Plants
            herbivoreList.get(i).update();
            herbivoreList.get(i).draw(g);
            i++;
        }
    }

    public void updateHerbivoreManager(Graphics g) {
        if (totalHerbivoreExisted == 0) {
            spawnHerbivores(InitialHerbivores);
        }

        updateHerbivores(g);
    }

}
