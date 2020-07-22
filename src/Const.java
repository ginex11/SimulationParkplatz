import java.util.Random;

public class Const {
    // Zufallsgenerator
    public static Random rand = new Random();
    // Mindest Abstand zum nächsten Auto in mm
    public static final int GAP = 400;
    //Länge des Parkstreifens in mm
    public static final int SIZE = 98400;
    //Wahrscheinlichkeit, dass ein Auto zum parken eintrifft in einer Sekunde
    public static final double CHANCE = 0.03;

    public static int SIMULATION_TIME = 60 * 60 * 24 * 7 * 30; // Eine Woche
}
