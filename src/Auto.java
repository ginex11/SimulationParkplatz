
import org.uncommons.maths.random.GaussianGenerator;
import org.uncommons.maths.random.MersenneTwisterRNG;

import java.util.Random;

public class Auto {
    //Breite des Autos
    private double width;
    // Aufenthaltsdauer
    private int duration;
    // Startposition des Autos
    private double start;
    private Random rng = new MersenneTwisterRNG();

    public Auto(int pastTime) {
        int tempW = new GaussianGenerator(2050, 90, rng).nextValue().intValue();
        int tempD = new GaussianGenerator(900, 200, rng).nextValue().intValue();
        tempW = tempW > 2300 ? 2300 : Math.max(tempW, 1500);
        tempD = tempD > 1300 ? 1300 : Math.max(tempD, 400);
        this.width = tempW;
        this.duration = pastTime + tempD;
    }

    public int getDuration() {
        return duration;
    }

    public double getStart() {
        return start;
    }

    public void setStart(double start) {
        this.start = start;
    }

    public double getEnd() {
        return getStart() + width;
    }

    /**
     * Überprüft ob das Auto in die Lücke passt.
     * @param start Startpostion der Lücke.
     * @param end   Endposition der Lücke.
     * @return  true wenn das Auto in die Lücke passt, sonst false.
     */
    public boolean park(double start, double end) {
        if (width + 2 * Const.GAP <= end - start) {
            this.setStart((end + start) / 2 - width / 2.0);
            return true;
        }
        return false;

    }

    @Override
    public String toString() {
        return
                "width=" + width +
                        //", duration=" + duration +
                        "[Pos:" + start + " " + getEnd() + "]" + "}\n";
    }
}

