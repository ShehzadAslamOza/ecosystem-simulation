package LivingThings;

import java.util.ArrayList;

public class State {

    public static boolean Icon = true;

    // Plants
    public static int PLANT_MAX_SIZE = 35;
    public static int TIME_BEFORE_GROW = 400;
    public static int TIME_BEFORE_NEW_PLANTS = 300;
    public static int NUM_PLANT_TO_GENERATE_INITIAL = 250;
    public static int NUM_PLANT_TO_GENERATE = 100;


    // Carnivore
    public static int CARNIVORE_MAX_SIZE = 80;
    public static int INITIAL_CARNIVORE = 14;
    public static int CARNIVORE_TIME_TO_LIVE = 1600;


    // Cannibal
    public static int CANNIBAL_MAX_SIZE = 80;
    public static int INITIAL_CANNIBAL = 10;
    public static int CANNIBAL_TIME_TO_LIVE = 1600;


    //Herbivore
    public static int HERBIVORE_MAX_SIZE = 100;
    public static int INITIAL_HERBIVORE = 20;
    public static int HERBIVORE_TIME_TO_LIVE = 1500;

    public static ArrayList<LivingThing> plantList = new ArrayList<>();
    public static ArrayList<LivingThing> herbivoreList = new ArrayList<>();
    public static ArrayList<LivingThing> carnivoreList = new ArrayList<>();
    public static ArrayList<LivingThing> cannibalList = new ArrayList<>();
}
