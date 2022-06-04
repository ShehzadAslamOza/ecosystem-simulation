package LivingThings;

import Managers.PlantManager;
import Shapes.Circle;
import Simulator.Board;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

/**
 * Plant Class which extends LivingThing
 */

public class Plant extends LivingThing {
    public static int maxSize = State.PLANT_MAX_SIZE;       // Maximum Size of the plant
    private int timeBeforeGrow = State.TIME_BEFORE_GROW;    // Time after which the plants grow themselves


    /**
     * Plant Constructor
     */
    public Plant() {
        Random rand = new Random();
        this.ID = generateID();
        this.size = rand.nextInt(15,20);
        this.center = new Point(rand.nextInt(1, Board.B_WIDTH), rand.nextInt(1,Board.B_HEIGHT));
        this.color = Color.GREEN;
        this.speed = 0;
        this.image = ImageReader("C:\\Users\\shehzad\\IdeaProjects\\ecosystem\\out\\assests\\plant.png");
        this.shape = new Circle(size,center,color,image);
    }



    /**
     * Updates the plant after each iteration
     */
    public void update() {

        // Plants don't grow more than a specifies size
        if (this.size != maxSize) {

            // Updating Time Before Grow
            this.timeBeforeGrow--;

            // Grows the plants after an interval
            if (this.timeBeforeGrow <= 0) {
                GrowBy(1);
                this.timeBeforeGrow = 400;
            }

        }
    }


    /**
     * Generates unique id for plant
     * @return
     */
    public String generateID() {
        PlantManager.totalPlantsExisted++;

        return "PLANT" + PlantManager.totalPlantsExisted;
    }


}
