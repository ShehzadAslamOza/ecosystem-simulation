package Managers;

import Factories.CannibalFactory;
import Factories.CarnivoreFactory;
import LivingThings.Cannibal;
import LivingThings.Carnivore;
import LivingThings.LivingThing;
import LivingThings.State;

import java.awt.*;
import java.util.ArrayList;

public class CannibalManager extends LivingThingManager{

    public static int totalCannibalExisted = 0;
    private int InitialCannibals = State.INITIAL_CANNIBAL;

    ArrayList<LivingThing> cannibalList = State.cannibalList;


    private void spawnCannibal(int num) {
        for (int i = 0; i < num; i++) {
            cannibalList.add(livingThingFactory.getLivingThing("CANNIBAL"));
        }
    }


    public void updateManager(Graphics g) {
        if (totalCannibalExisted == 0) {
            spawnCannibal(InitialCannibals);
        }

        updateLivingThing(g, cannibalList);
    }

}
