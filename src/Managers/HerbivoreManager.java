package Managers;

import Factories.HerbivoreFactory;
import LivingThings.Herbivore;
import LivingThings.LivingThing;
import LivingThings.State;

import java.awt.*;
import java.util.ArrayList;

public class HerbivoreManager extends LivingThingManager{

    public static int totalHerbivoreExisted = 0;
    private int InitialHerbivores = State.INITIAL_HERBIVORE;

    ArrayList<LivingThing> herbivoreList = State.herbivoreList;


    private void spawnHerbivores(int num) {
        for (int i = 0; i < num; i++) {
            herbivoreList.add(livingThingFactory.getLivingThing("HERBIVORE"));
        }
    }


    public void updateManager(Graphics g) {
        if (totalHerbivoreExisted == 0) {
            spawnHerbivores(InitialHerbivores);
        }

        updateLivingThing(g, herbivoreList);
    }

}
