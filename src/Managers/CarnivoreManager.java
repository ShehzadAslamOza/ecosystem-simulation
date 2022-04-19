package Managers;

import Factories.CarnivoreFactory;
import LivingThings.Carnivore;
import LivingThings.LivingThing;
import LivingThings.State;

import java.awt.*;
import java.util.ArrayList;

public class CarnivoreManager extends LivingThingManager{

    public static int totalCarnivoreExisted = 0;
    private int IntialCarnivores = State.INITIAL_CARNIVORE;

    ArrayList<LivingThing> carnivoreList = State.carnivoreList;
    CarnivoreFactory carnivoreFactory = CarnivoreFactory.getInstance();

    private void spawnCarnivore(int num) {
        for (int i = 0; i < num; i++) {
            carnivoreList.add(livingThingFactory.getLivingThing("CARNIVORE"));
        }
    }

    public void updateManager(Graphics g) {
        if (totalCarnivoreExisted == 0) {
            spawnCarnivore(IntialCarnivores);
        }

        updateLivingThing(g, carnivoreList);
    }

}
