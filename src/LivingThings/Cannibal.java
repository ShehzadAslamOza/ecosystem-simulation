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

    private static final int maxSize = State.CANNIBAL_MAX_SIZE;
    private int cannibilismBuffer = 200;
    CannibalFactory cannibalFactory = CannibalFactory.getInstance();


    public Cannibal() {
        Random rand = new Random();
        this.ID = generateID();
        this.size = rand.nextInt(25,35);
        this.center = new Point(rand.nextInt(1, Board.B_WIDTH), rand.nextInt(1,Board.B_HEIGHT));
        this.color = Color.BLACK;
        this.speed = 3;
        this.timeToLive = State.CANNIBAL_TIME_TO_LIVE;
        this.image = ImageReader("assests/bear.png");
        this.shape = new Circle(size,center,color,image);
    }

    public Cannibal(Point center) {
        Random rand = new Random();
        this.ID = generateID();
        this.size = rand.nextInt(20,30);
        this.center = center;
        this.color = Color.BLACK;
        this.speed = 3;
        this.timeToLive = State.CANNIBAL_TIME_TO_LIVE;
        this.image = ImageReader("assests/bear.png");
        this.shape = new Circle(size,center,color,image);
    }

    @Override
    public void eat(LivingThing livingThing) {

        if (this.size >= livingThing.size) {
            timeToLive = State.CANNIBAL_TIME_TO_LIVE;
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
            } else if (livingThing instanceof Carnivore) {
                for (LivingThing h: carnivoreList) {
                    if (h.ID.equals(livingThing.ID)) {
                        h.Die();
                        break;
                    }
                }
            }


            // check if animal is of maxSize
            if (this.size + target.size >= maxSize) {
                Die();
                generateOffSprings(2);
            } else {
                // increase Carnivore Size
                GrowBy(livingThing.size / 3);
            }

            target = null;
        } else {
            chooseTarget();
        }
    }

    public void generateOffSprings(int num) {
        Random rand = new Random();
        for (int i = 0; i < num; i++) {
            Point coordinates = new Point(center.x + rand.nextInt(-30,30),center.y + rand.nextInt(-5,15));
            cannibalList.add(cannibalFactory.generateCarnivore(coordinates));
        }
    }

    public String generateID() {
        CannibalManager.totalCannibalExisted++;

        return "Cannibal" + CannibalManager.totalCannibalExisted++;
    }


    @Override
    public void chooseTarget() {


        int min = Integer.MAX_VALUE;
        cannibilismBuffer--;





        for (LivingThing herbivore: State.herbivoreList) {

            if (this.size >= herbivore.size) {
                int distance = calculateDistance(center,herbivore);

                if (distance < min) {
                    min = distance;
                    target = herbivore;
                }
            }

        }

        for (LivingThing carnivore: carnivoreList) {

            if (this.size >= carnivore.size) {
                int distance = calculateDistance(center,carnivore);

                if (distance < min) {
                    min = distance;
                    target = carnivore;
                }
            }

        }

        if ((cannibilismBuffer <= 0) || target ==null) {
            for (LivingThing cannibal: cannibalList) {

                if (this.size >= cannibal.size && !cannibal.ID.equals(this.ID)) {
                    int distance = calculateDistance(center,cannibal);

                    if (distance< min) {
                        min = distance;
                        target = cannibal;
                    }
                }

            }
        }




    }

}
