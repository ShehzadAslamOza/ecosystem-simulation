package LivingThings;


import Managers.HerbivoreManager;
import Shapes.Circle;
import Simulator.Board;

import java.awt.*;
import java.util.Random;

import static LivingThings.State.herbivoreList;

/**
 * Herbovore Class that extends Animal
 */
public class Herbivore extends Animal {
    public static int maxSize = State.HERBIVORE_MAX_SIZE;


    /**
     * Herbovore Constructor
     */
    public Herbivore() {
        Random rand = new Random();
        this.ID = generateID();
        this.size = rand.nextInt(20,30);
        this.center = new Point(rand.nextInt(1, Board.B_WIDTH), rand.nextInt(1,Board.B_HEIGHT));
        this.color = Color.GRAY;
        this.speed = 1;
        this.timeToLive = State.HERBIVORE_TIME_TO_LIVE;
        this.image = ImageReader("C:\\Users\\shehzad\\IdeaProjects\\ecosystem\\out\\assests\\deer.png");
        this.shape = new Circle(size,center,color,image);
    }

    /**
     * Herbovore Constructor
     */
    public Herbivore(Point center) {
        Random rand = new Random();
        this.ID = generateID();
        this.size = rand.nextInt(20,30);
        this.center = center;
        this.color = Color.GRAY;
        this.speed = 1;
        this.timeToLive = State.HERBIVORE_TIME_TO_LIVE;
        this.image = ImageReader("C:\\Users\\shehzad\\IdeaProjects\\ecosystem\\out\\assests\\deer.png");
        this.shape = new Circle(size,center,color,image);
    }


    /**
     * Gerneates unique id for herbivore
     * @return
     */
    public String generateID() {
        HerbivoreManager.totalHerbivoreExisted++;

        return "HERBIVORE" + HerbivoreManager.totalHerbivoreExisted++;
    }


    /**
     * Herbivore chooses its closest plant
     */
    public void chooseTarget() {


            int min = Integer.MAX_VALUE;


            for (LivingThing plant: State.plantList) {

                if (this.size >= plant.size) {
                    int distance = calculateDistance(center,plant);

                    if (distance < min) {
                        min = distance;
                        target = plant;
                    }
                }


            }


    }


    /**
     * Herbovore runs towards the plant
     */
    public void chaseTarget() {
        if (reactionTime <= 0) {
            // chase



            if (target != null && target.isAlive) {

                // Chases only if the size of the target it smaller or equal to itself
                if (target.size > size) {
                    chooseTarget();
                }

                // angle between the prey and predator
                double angle = getAngleFromTarget(center, target.center);


                // Move Right
                if ((angle >= 0 && angle <= 30) || (angle > 330 && angle <= 360)) {
                    moveBy(1,0);
                }
                // Move Left Up
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

                reactionTime = 4;
            } else {
                chooseTarget();
            }



        } else {
            reactionTime -= speed;
        }
    }

    /**
     * Herbovore wats its prey
     * @param plant
     */
    public void eat(LivingThing plant) {

        if (this.size >= plant.size) {
            timeToLive = State.HERBIVORE_TIME_TO_LIVE;;
            // kill the plant
            for (LivingThing p: State.plantList) {
                if (p.ID.equals(plant.ID)) {
                    p.Die();
                    break;
                }
            }



            // check if animal is of maxSize
            if (this.size + target.size >= maxSize) {
                Die();
                generateOffSprings(8);
            } else {
                // increase Carnivore Size
                GrowBy(plant.size / 3);
            }

            target = null;
        } else {
            chooseTarget();
        }




    }

    /**
     * Generates offprings
     * @param num
     */
    public void generateOffSprings(int num) {
        Random rand = new Random();
        for (int i = 0; i < num; i++) {
            Point coordinates = new Point(center.x + rand.nextInt(-50,50),center.y + rand.nextInt(-50,50));
            herbivoreList.add(livingThingFactory.getLivingThing("HERBIVORE",coordinates));
        }
    }



}
