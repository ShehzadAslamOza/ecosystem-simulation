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


    public Circle(int iSize, Point location, Color C, BufferedImage image) {
        setSize(iSize);
        setLocation(location);
        setColor(C);
        setImage(image);
    }
    void setSize(int iSize) {
        if (iSize > 1) {
            size = iSize;
        } else {
            size = 1;
        }
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    void setLocation(Point Pcenter) {
        center = Pcenter;
    }

    void setColor(Color Ccolor) {
        color = Ccolor;
    }

    int getSize()
    {
        return size;
    }

    Point getCenter()
    {
        return center;
    }

    Color getColor()
    {
        return color;
    }

    public void draw(Graphics g)
    {
        g.setColor(getColor());

        if(State.Icon) {
            g.drawImage(image,getCenter().x - getSize()/2 ,getCenter().y - getSize()/2, getSize(),getSize(), null);
        } else {
            g.fillOval(getCenter().x - getSize()/2 ,getCenter().y - getSize()/2,getSize(),getSize());
        }


    }
}