package Simulator;

import LivingThings.State;
import Managers.CannibalManager;
import Managers.CarnivoreManager;
import Managers.HerbivoreManager;
import Managers.PlantManager;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.*;
import javax.swing.*;

public class Board extends JPanel implements ActionListener, MouseListener {

//    public static final int B_WIDTH = 350;
//    public static final int B_HEIGHT = 350;
    public static final int B_WIDTH = 1200;
    public static final int B_HEIGHT = 800;
    private final int INITIAL_X = -40;
    private final int INITIAL_Y = -40;
    private final int DELAY = 25;

    private Timer timer;
    private int x, y;

    // Mana
    PlantManager plantManager = new PlantManager();
    HerbivoreManager herbivoreManager = new HerbivoreManager();
    CarnivoreManager carnivoreManager = new CarnivoreManager();
    CannibalManager cannibalManager = new CannibalManager();

    public Board() {

        initBoard();
    }

    

    private void initBoard() {

        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        
        x = INITIAL_X;
        y = INITIAL_Y;
        
        timer = new Timer(DELAY, this);
        timer.start();
        this.addMouseListener( this );
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Draw(g);
    }

    private void Draw(Graphics g) {

        Toolkit.getDefaultToolkit().sync();
        plantManager.updateManager(g);
        herbivoreManager.updateManager(g);
        carnivoreManager.updateManager(g);
        cannibalManager.updateManager(g);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        x += 1;
        y += 1;

        if (y > B_HEIGHT) {

            y = INITIAL_Y;
            x = INITIAL_X;
        }

        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(SwingUtilities.isRightMouseButton(e))
        {

            State.Icon = !State.Icon;
            System.out.println("right pressed");
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }


}