package LivingThings;


import Factories.HerbivoreFactory;
import Managers.HerbivoreManager;
import Managers.PlantManager;
import Shapes.Circle;
import Simulator.Board;

import java.awt.*;
import java.util.Random;

import static LivingThings.State.herbivoreList;


public class Herbivore extends LivingThing implements Hunter{
    public static int maxSize = 70;
    private Plant target = null;
    private int reactionTime = 4;
    HerbivoreFactory herbivoreFactory = HerbivoreFactory.getInstance();

    public Herbivore() {
        Random rand = new Random();
        this.ID = generateHerbivoreID();
        this.size = rand.nextInt(7,11);
        this.center = new Point(rand.nextInt(1, Board.B_WIDTH), rand.nextInt(1,Board.B_HEIGHT));
        this.color = Color.RED;
        this.speed = 2;
        this.timeToLive = 1000;
        this.shape = new Circle(size,center,color);
    }

    public Herbivore(Point center) {
        Random rand = new Random();
        this.ID = generateHerbivoreID();
        this.size = rand.nextInt(5,10);
        this.center = center;
        this.color = Color.RED;
        this.speed = 2;
        this.timeToLive = 1000;
        this.shape = new Circle(size,center,color);
    }




    public void update() {
        if (timeToLive == 0) {
            Die();
        } else {
            timeToLive--;
        }

        if (target == null) {
            chooseTarget();
        } else if (target.isDead()) {
            chooseTarget();
        }

        chaseTarget();

        checkCollision(target);


    }

    public boolean isMaxSize() {
        if (size >= maxSize) {
            return true;
        }

        return false;
    }

    public String generateHerbivoreID() {
        HerbivoreManager.totalHerbivoreExisted++;

        return "HERBIVORE" + HerbivoreManager.totalHerbivoreExisted++;
    }

    private int calculateDistance(Point currentPos, Plant plant) {

        return (int) (Math.sqrt(Math.pow((currentPos.x - plant.center.x), 2) + Math.pow((currentPos.y - plant.center.y), 2)));

    }

    private float getAngleFromTarget(Point origin,Point target) {
        float angle = (float) Math.toDegrees(Math.atan2(target.y - origin.y, target.x - origin.x));

        if(angle < 0){
            angle += 360;
        }

        return angle;
    }





    @Override
    public void chooseTarget() {


            int min = Integer.MAX_VALUE;


            for (Plant plant: State.plantList) {

                if (this.size >= plant.size) {
                    int distance = calculateDistance(center,plant);

                    if (distance < min) {
                        min = distance;
                        target = plant;
                    }
                }


            }


    }



    @Override
    public void chaseTarget() {
        if (reactionTime <= 0) {
            // chase

            if (target != null && target.isAlive) {
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

    @Override
    public void eat(LivingThing plant) {

        if (this.size >= plant.size) {
            timeToLive = 4000;
            // kill the plant
            for (Plant p: State.plantList) {
                if (p.ID.equals(plant.ID)) {
                    p.Die();
                    break;
                }
            }

            // increase Herbivore Size
            GrowBy(plant.size);
            target = null;
            // check if animal is of maxSize
            if (isMaxSize()) {
                Die();
                generateOffSprings(4);
            }
        } else {
            chooseTarget();
        }




    }

    public void generateOffSprings(int num) {
        Random rand = new Random();
        for (int i = 0; i < num; i++) {
            Point coordinates = new Point(center.x + rand.nextInt(-10,30),center.y + rand.nextInt(-5,15));
            herbivoreList.add(herbivoreFactory.generateHerbivore(coordinates));
        }
    }

    @Override
    public void checkCollision(Plant plant) {

        if (target != null && target.isAlive) {
            int distance = calculateDistance(center,plant);

            int plantRadius = plant.size / 2;
            int herbivoreRadius = size / 2;

            if ((herbivoreRadius + plantRadius) >= distance) {
                eat(plant);
            }
        }



    }
}
