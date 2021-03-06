package LivingThings;


import Managers.CarnivoreManager;
import Shapes.Circle;
import Simulator.Board;

import java.awt.*;
import java.util.Random;

import static LivingThings.State.cannibalList;
import static LivingThings.State.carnivoreList;

/**
 * Carnivore class that extends Animal
 */
public class Carnivore extends Animal {
    public static int maxSize = State.CARNIVORE_MAX_SIZE;


    /**
     * Carnivore Constructor
     */
    public Carnivore() {
        Random rand = new Random();
        this.ID = generateID();
        this.size = rand.nextInt(20,30);
        this.center = new Point(rand.nextInt(1, Board.B_WIDTH), rand.nextInt(1,Board.B_HEIGHT));
        this.color = Color.CYAN;
        this.speed = 2;
        this.timeToLive = State.CARNIVORE_TIME_TO_LIVE;
        this.image = ImageReader("C:\\Users\\shehzad\\IdeaProjects\\ecosystem\\out\\assests\\tiger.png");
        this.shape = new Circle(size,center,color,image);
    }

    /**
     * Carnivore Constructor
     */
    public Carnivore(Point center) {
        Random rand = new Random();
        this.ID = generateID();
        this.size = rand.nextInt(20,30);
        this.center = center;
        this.color = Color.CYAN;
        this.speed = 2;
        this.timeToLive = State.CARNIVORE_TIME_TO_LIVE;
        this.image = ImageReader("C:\\Users\\shehzad\\IdeaProjects\\ecosystem\\out\\assests\\tiger.png");
        this.shape = new Circle(size,center,color,image);
    }

    /**
     * Generates unique id for carnivore
     * @return
     */
    public String generateID() {
        CarnivoreManager.totalCarnivoreExisted++;

        return "Carnivore" + CarnivoreManager.totalCarnivoreExisted++;
    }


    /**
     * Chooses ther nearest prey
     */
    @Override
    public void chooseTarget() {


        int min = Integer.MAX_VALUE;


        for (LivingThing herbivore: State.herbivoreList) {

            if (this.size >= herbivore.size) {
                int distance = calculateDistance(center,herbivore);

                if (distance < min) {
                    min = distance;
                    target = herbivore;
                }
            }

        }

//        //comment out
//        for (LivingThing cannibal: cannibalList) {
//
//            if (this.size >= cannibal.size) {
//                int distance = calculateDistance(center,cannibal);
//
//                if (distance < min) {
//                    min = distance;
//                    target = cannibal;
//                }
//            }
//
//        }



    }


    /**
     * Runs after its prey
     */
    @Override
    public void chaseTarget() {
        if (reactionTime <= 0) {
            // chase



            if (target != null && target.isAlive) {

                if (target.size > size) {
                    chooseTarget();
                }

                double angle = getAngleFromTarget(center, target.center);

                // Move Right
                if ((angle >= 0 && angle <= 30) || (angle > 330 && angle <= 360)) {
                    moveBy(1,0);
                }
                // Move Right Up
                else if ((angle > 30 && angle <= 60)) {
                    moveBy(1,1);
                }
                // Move Up
                else if ((angle > 60 && angle <= 120)) {
                    moveBy(0,1);
                }

                // Move Left Up
                else if ((angle > 120 && angle <= 150)) {
                    moveBy(-1,1);
                }

                // Move Left
                else if ((angle > 150 && angle <= 210)) {
                    moveBy(-1,0);
                }

                // Move Left Down
                else if ((angle > 210 && angle <= 240)) {
                    moveBy(-1,-1);
                }

                // Move  Down
                else if ((angle > 240 && angle <= 300)) {
                    moveBy(0,-1);
                }

                // Move  Down Right
                else if ((angle > 300 && angle <= 330)) {
                    moveBy(0,-1);
                }

                reactionTime = 2;
            } else {
                chooseTarget();
            }



        } else {
            reactionTime -= speed;
        }
    }

    /**
     * Eats its prey
     * @param livingThing
     */
    public void eat(LivingThing livingThing) {

        if (this.size >= livingThing.size) {
            timeToLive = State.CARNIVORE_TIME_TO_LIVE;;
            // kill the plant
            if (livingThing instanceof Herbivore) {
                for (LivingThing h: State.herbivoreList) {
                    if (h.ID.equals(livingThing.ID)) {
                        h.Die();
                        break;
                    }
                }
            } else if (livingThing instanceof Cannibal) {
                for (LivingThing h: cannibalList) {
                    if (h.ID.equals(livingThing.ID)) {
                        h.Die();
                        break;
                    }
                }
            }



            // check if animal is of maxSize
            if (this.size + target.size >= maxSize) {
                Die();
                generateOffSprings(4);
            } else {
                // increase Carnivore Size
                GrowBy(livingThing.size / 4);
            }

            target = null;
        } else {
            chooseTarget();
        }
    }

    /**
     * genreates offspring
     * @param num
     */
    public void generateOffSprings(int num) {
        Random rand = new Random();
        for (int i = 0; i < num; i++) {
            Point coordinates = new Point(center.x + rand.nextInt(-50,50),center.y + rand.nextInt(-50,50));
            carnivoreList.add(livingThingFactory.getLivingThing("CARNIVORE",coordinates));
        }
    }


}
