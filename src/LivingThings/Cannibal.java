package LivingThings;

import Factories.CannibalFactory;
import Managers.CannibalManager;
import Shapes.Circle;
import Simulator.Board;

import java.awt.*;
import java.util.Random;

import static LivingThings.State.cannibalList;
import static LivingThings.State.carnivoreList;



public class Cannibal extends Carnivore{


    CannibalFactory cannibalFactory = CannibalFactory.getInstance();
    private int cannibalEatTime = 300;

    public Cannibal() {
        Random rand = new Random();
        this.ID = generateCannibalID();
        this.size = rand.nextInt(10,15);
        this.center = new Point(rand.nextInt(1, Board.B_WIDTH), rand.nextInt(1,Board.B_HEIGHT));
        this.color = Color.BLACK;
        this.speed = 3;
        this.timeToLive = 1000;
        this.shape = new Circle(size,center,color);
    }

    public Cannibal(Point center) {
        Random rand = new Random();
        this.ID = generateCannibalID();
        this.size = rand.nextInt(5,10);
        this.center = center;
        this.color = Color.BLACK;
        this.speed = 3;
        this.timeToLive = 1000;
        this.shape = new Circle(size,center,color);
    }

    @Override
    public void eat(LivingThing livingThing) {

        if (this.size >= livingThing.size) {
            timeToLive = 4000;
            // kill the plant
            if (livingThing instanceof Herbivore) {
                for (Herbivore h: State.herbivoreList) {
                    if (h.ID.equals(livingThing.ID)) {
                        h.Die();
                        break;
                    }
                }
            } else if (livingThing instanceof Carnivore) {
                for (Carnivore h: carnivoreList) {
                    if (h.ID.equals(livingThing.ID)) {
                        h.Die();
                        break;
                    }
                }
            } else if (livingThing instanceof Cannibal) {
                for (Cannibal h: cannibalList) {
                    if (h.ID.equals(livingThing.ID)) {
                        h.Die();
                        break;
                    }
                }
            }


            // increase Carnivore Size
            GrowBy(livingThing.size);
            target = null;
            // check if animal is of maxSize
            if (isMaxSize()) {
                Die();
                generateOffSprings(6);
            }
        } else {
            chooseTarget();
        }
    }

    public void generateOffSprings(int num) {
        Random rand = new Random();
        for (int i = 0; i < num; i++) {
            Point coordinates = new Point(center.x + rand.nextInt(-10,30),center.y + rand.nextInt(-5,15));
            cannibalList.add(cannibalFactory.generateCarnivore(coordinates));
        }
    }

    public String generateCannibalID() {
        CannibalManager.totalCannibalExisted++;

        return "Cannibal" + CannibalManager.totalCannibalExisted++;
    }


    @Override
    public void chooseTarget() {


        int min = Integer.MAX_VALUE;


        for (Herbivore herbivore: State.herbivoreList) {

            if (this.size >= herbivore.size) {
                int distance = calculateDistance(center,herbivore);

                if (distance < min) {
                    min = distance;
                    target = herbivore;
                }
            }

        }
        for (Carnivore carnivore: carnivoreList) {

            if (this.size >= carnivore.size) {
                int distance = calculateDistance(center,carnivore);

                if (distance < min) {
                    min = distance;
                    target = carnivore;
                }
            }

        }

        if (cannibalEatTime <= 0 || target == null) {
            for (Cannibal cannibal: cannibalList) {

                if (this.size >= cannibal.size) {
                    int distance = calculateDistance(center,cannibal);

                    if (distance < min) {
                        min = distance;
                        target = cannibal;
                    }
                }

            }
        }

        cannibalEatTime--;



    }
}
