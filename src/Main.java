
public class Main {
    // Vergangene Zeit in der Simulation.
    private static int pastTime = 0;

    public static void main(String[] args) {
        Parkplatz parkplatz = new Parkplatz();
        while (pastTime++ < Const.SIMULATION_TIME) {
            if (Const.rand.nextDouble() < Const.CHANCE) {
                Auto auto1 = new Auto(pastTime);
                parkplatz.addCar(auto1);
            }
            duration_check(parkplatz);
        }
        System.out.println("Index I:\t" + parkplatz.parkGaps.stream().reduce(Double::sum).get() / parkplatz.parkGaps.size());
        System.out.println("Normalverteilung:\t" + parkplatz.MapForDist);
        System.out.println("%-Anteil der nicht parkenden Autos:\t" + parkplatz.notParkedCars / parkplatz.parkedCars + "%");
    }

    /**
     * Entfernt alle parkenden Autos, deren Zeit abgelaufen ist.
     *
     * @param parkplatz Parkplatz, der überprüft wird.
     */
    private static void duration_check(Parkplatz parkplatz) {
        int i = 0;
        while (i < parkplatz.getCars().size()) {
            Auto car = parkplatz.getCars().get(i);
            if (car.getDuration() <= pastTime) {
                parkplatz.getCars().remove(i);
            } else {
                i++;
            }
        }
    }
}

