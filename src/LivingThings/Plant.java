package LivingThings;

import Managers.PlantManager;
import Shapes.Circle;
import Simulator.Board;

import java.awt.*;
import java.util.Random;

public class Plant extends LivingThing {
    public static int maxSize = 10;
    private int timeBeforeGrow = 100;


    public Plant() {
        Random rand = new Random();
        this.ID = generatePlantID();
        this.size = rand.nextInt(1,5);
        this.center = new Point(rand.nextInt(1, Board.B_WIDTH), rand.nextInt(1,Board.B_HEIGHT));
        this.color = Color.GREEN;
        this.speed = 0;
        this.shape = new Circle(size,center,color);
    }


    public void update() {

        if (this.size != maxSize) {

            // Updating Time Before Grow
            this.timeBeforeGrow--;

            if (this.timeBeforeGrow <= 0) {
                GrowBy(1);
                this.timeBeforeGrow = 100;
            }

        }
    }

    public void draw(Graphics g) {
        shape.draw(g);
    }

    private String generatePlantID() {
        PlantManager.totalPlantsExisted++;

        return "PLANT" + PlantManager.totalPlantsExisted;
    }
}