package Managers;

import LivingThings.LivingThing;
import LivingThings.State;

import java.awt.*;
import java.util.ArrayList;

/**
 * Manages Cannibals
 */
public class CannibalManager extends LivingThingManager{

    public static int totalCannibalExisted = 0;
    private int InitialCannibals = State.INITIAL_CANNIBAL;
    ArrayList<LivingThing> cannibalList = State.cannibalList;


    /**
     * Spawns the cannibals randomly around the screen
     * @param num
     */
    private void spawnCannibal(int num) {
        for (int i = 0; i < num; i++) {
            cannibalList.add(livingThingFactory.getLivingThing("CANNIBAL"));
        }
    }

    /**
     * Updates the manager that updates all the cannibals
     * @param g
     */
    public void updateManager(Graphics g) {
        if (totalCannibalExisted == 0) {
            spawnCannibal(InitialCannibals);
        }

        updateLivingThing(g, cannibalList);
    }

}
