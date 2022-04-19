package LivingThings;

import Factories.LivingThingFactory;
import Shapes.Circle;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Abstract Class of Living Things
 */
public abstract class LivingThing {
    protected String ID;
    protected Point center;
    protected Color color;
    protected BufferedImage image;
    protected boolean isAlive = true;
    protected int size;
    protected int speed;
    protected int timeToLive;
    protected Circle shape;

    // Living Thing Factory to create Living Things
    protected LivingThingFactory livingThingFactory = LivingThingFactory.getInstance();

    /**
     * Returns the angle between the target and the prey
     * @param origin
     * @param target
     * @return
     */
    protected float getAngleFromTarget(Point origin,Point target) {
        float angle = (float) Math.toDegrees(Math.atan2(target.y - origin.y, target.x - origin.x));

        if(angle < 0){
            angle += 360;
        }

        return angle;
    }

    /**
     * Reads and returns the Icon of the Living Thing
     * @param path
     * @return
     */
    protected BufferedImage ImageReader(String path) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return image;
    }


    /**
     * Calculates the distance betweeen two living things
     * @param currentPos
     * @param plant
     * @return
     */
    protected int calculateDistance(Point currentPos, LivingThing plant) {
        return (int) (Math.sqrt(Math.pow((currentPos.x - plant.center.x), 2) + Math.pow((currentPos.y - plant.center.y), 2)));
    }

    /**
     * Increses the size of the living thing
     * @param growBy
     */
    protected void GrowBy(int growBy) {
        this.size += growBy;
        this.shape = new Circle(size,center,color, image);
    }

    /**
     * Moves the living thing in the disered direction
     * @param x
     * @param y
     */
    protected void moveBy(int x,int y) {
        this.center.x += x;
        this.center.y += y;
    }


    /**
     * Causes the living thing to die
     */
    protected void Die() {
        this.isAlive = false;
    }

    /**
     * Checks whether the living thing is dead or alive
     * @return
     */
    public boolean isDead() {
        return !this.isAlive;
    }

    /**
     * Draws the living thing on the screen
     * @param g
     */
    public void draw(Graphics g) {
        shape.draw(g);
    }


    /**
     * Updates the living thing
     */
    public abstract void update();

    /**
     * Returns A unique ID for animal
     */
    public abstract String generateID();


}