package Shapes;

import LivingThings.State;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * This is a Circle Shape
 */
public class Circle
{
    protected int size;
    protected Point center;
    protected Color color;
    protected Image image;

    /**
     * Circle Constructor
     * @param iSize
     * @param location
     * @param C
     * @param image
     */
    public Circle(int iSize, Point location, Color C, BufferedImage image) {
        setSize(iSize);
        setLocation(location);
        setColor(C);
        setImage(image);
    }

    /**
     * Sets size of the circle
     * @param iSize
     */
    void setSize(int iSize) {
        if (iSize > 1) {
            size = iSize;
        } else {
            size = 1;
        }
    }

    /**
     * Sets the icon for the living thing
     * @param image
     */
    public void setImage(BufferedImage image) {
        this.image = image;
    }

    /**
     * Sets the center on circle
     * @param Pcenter
     */
    void setLocation(Point Pcenter) {
        center = Pcenter;
    }

    /**
     * sets the color of the circle
     * @param Ccolor
     */
    void setColor(Color Ccolor) {
        color = Ccolor;
    }

    /**
     * returns the size of the circle
     * @return
     */
    int getSize()
    {
        return size;
    }

    /**
     * Reuturns the center point
     * @return
     */
    Point getCenter()
    {
        return center;
    }

    /***
     * returns the color of the circle
     */
    Color getColor()
    {
        return color;
    }

    /**
     * Draws the circle / Icon on the screen
     * @param g
     */
    public void draw(Graphics g)
    {
        g.setColor(getColor());

        // draws circle or icon depending on the state
        if(State.Icon) {
            g.drawImage(image,getCenter().x - getSize()/2 ,getCenter().y - getSize()/2, getSize(),getSize(), null);
        } else {
            g.fillOval(getCenter().x - getSize()/2 ,getCenter().y - getSize()/2,getSize(),getSize());
        }


    }
}