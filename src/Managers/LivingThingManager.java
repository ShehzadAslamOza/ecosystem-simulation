package Managers;

import Factories.LivingThingFactory;
import LivingThings.Carnivore;
import LivingThings.LivingThing;

import java.awt.*;
import java.util.ArrayList;

/**
 * Abstract class that manages living things
 */
public abstract class LivingThingManager {

    protected LivingThingFactory livingThingFactory = LivingThingFactory.getInstance();

    /**
        Updates all living things stored
     */
    protected void updateLivingThing(Graphics g, ArrayList<LivingThing> livingThingList) {
        int livingThingCount = livingThingList.size();
        int i = 0;

        while (i < livingThingCount) {

            //removes dead livingThing
            if (livingThingList.get(i).isDead()) {
                livingThingList.remove(i);
                livingThingCount--;
                continue;
            }

            // draws Living Thing
            livingThingList.get(i).update();
            livingThingList.get(i).draw(g);
            i++;
        }
    }

    /**
     * updates the manager that updates the living things
     * @param g
     */
    public abstract void updateManager(Graphics g);
}
