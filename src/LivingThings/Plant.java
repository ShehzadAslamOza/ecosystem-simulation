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

public class Plant extends LivingThing {
    public static int maxSize = State.PLANT_MAX_SIZE;
    private int timeBeforeGrow = State.TIME_BEFORE_GROW;




    public Plant() {



        Random rand = new Random();
        this.ID = generatePlantID();
        this.size = rand.nextInt(15,20);
        this.center = new Point(rand.nextInt(1, Board.B_WIDTH), rand.nextInt(1,Board.B_HEIGHT));
        this.color = Color.GREEN;
        this.speed = 0;
        this.image = ImageReader("assests/plant.png");
        this.shape = new Circle(size,center,color,image);
    }

    public BufferedImage ImageReader(String path) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return image;
    }

    public void update() {

        if (this.size != maxSize) {

            // Updating Time Before Grow
            this.timeBeforeGrow--;

            if (this.timeBeforeGrow <= 0) {
                GrowBy(1);
                this.timeBeforeGrow = 400;
            }

        }
    }



    private String generatePlantID() {
        PlantManager.totalPlantsExisted++;

        return "PLANT" + PlantManager.totalPlantsExisted;
    }


}
