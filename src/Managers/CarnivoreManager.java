package Managers;

import LivingThings.LivingThing;
import LivingThings.State;

import java.awt.*;
import java.util.ArrayList;

/**
 * Manages Carnivores
 */
public class CarnivoreManager extends LivingThingManager{

    public static int totalCarnivoreExisted = 0;
    private final int IntialCarnivores = State.INITIAL_CARNIVORE;   //

    ArrayList<LivingThing> carnivoreList = State.carnivoreList;


    /**
     * Spawns carnivores randomly on the screen
     * @param num
     */
    private void spawnCarnivore(int num) {
        for (int i = 0; i < num; i++) {
            carnivoreList.add(livingThingFactory.getLivingThing("CARNIVORE"));
        }
    }

    /**
     * Updates the manager which im turn updates all the carnivores
     * @param g
     */
    public void updateManager(Graphics g) {
        if (totalCarnivoreExisted == 0) {
            spawnCarnivore(IntialCarnivores);
        }

        updateLivingThing(g, carnivoreList);
    }

}
