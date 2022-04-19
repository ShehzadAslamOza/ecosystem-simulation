package LivingThings;

import Shapes.Circle;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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

    protected void Die() {
        this.isAlive = false;
    }

    protected void GrowBy(int growBy) {
        this.size += growBy;
        this.shape = new Circle(size,center,color, image);
    }

    protected void moveBy(int x,int y) {
        this.center.x += x;
        this.center.y += y;

        // Logic for corners need to implement
    }

    public boolean isDead() {
        return !this.isAlive;
    }

    public void draw(Graphics g) {
        shape.draw(g);
    }

    protected int calculateDistance(Point currentPos, LivingThing plant) {

        return (int) (Math.sqrt(Math.pow((currentPos.x - plant.center.x), 2) + Math.pow((currentPos.y - plant.center.y), 2)));

    }

    protected float getAngleFromTarget(Point origin,Point target) {
        float angle = (float) Math.toDegrees(Math.atan2(target.y - origin.y, target.x - origin.x));

        if(angle < 0){
            angle += 360;
        }

        return angle;
    }

    protected BufferedImage ImageReader(String path) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return image;
    }

    public abstract void update();

    // protected generateOffSprings
}