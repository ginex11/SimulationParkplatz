import java.util.*;

/**
 * Parkplatz
 */
public class Parkplatz {
    //Gesamtanzahl der Autos, die kein Parkplatz bekommen haben.
    public double notParkedCars = 0;
    //Gesamtanzahl der Autos, die ein Parkplatz bekommen haben.
    public double parkedCars = 0;
    // Liste mit allen Autos.
    private List<Auto> cars = new LinkedList<>();
    // Anzahl der Lücken > 2m, wenn das Auto kein Parkplatz findet.
    public List<Double> parkGaps = new LinkedList<>();

    public TreeMap<Integer, Integer> MapForDist = new TreeMap<>();

    public Parkplatz() {
    }

    public List<Auto> getCars() {
        return cars;
    }

    /**
     * Bestimmt die größte Lücke und parkt das Auto ein
     *
     * @param car das Auto was einparken möchte
     */
    public void addCar(Auto car) {
        double start = 0;
        double end = 0;
        if (cars.size() == 0) {
            car.park(start, Const.SIZE);
            cars.add(car);
        } else {
            for (int i = 0, j = 1; i < cars.size(); i++, j++) { // Vom erstem Auto bis zum lezten.
                if (j < cars.size()) {
                    if (cars.get(j).getStart() - cars.get(i).getEnd() >= end - start) {
                        start = cars.get(i).getEnd();
                        end = cars.get(j).getStart();
                    }
                }
            }
            if (cars.get(0).getStart() >= end - start) {    // Vom Anfang des Parkplatzes bis zum ersten Auto.
                start = 0;
                end = cars.get(0).getStart();
            }
            if (Const.SIZE - cars.get(cars.size() - 1).getEnd() >= end - start) { // Vom letzen Auto bis zum Ende des Parkplatzes.
                start = cars.get(cars.size() - 1).getEnd();
                end = Const.SIZE;
            }
            if (car.park(start, end)) { //einparken
                parkedCars++;
                car.park(start, end);
                cars.add(car);
            }
            if (!car.park(start, end) && end - start > 2000) {
                notParkedCars++;
                calcGaps();
            }
        }
        cars.sort(Comparator.comparing(Auto::getStart));
    }

    /**
     * Holt die Anzahl der nicht nutzbaren Lücken, die breiter als 2 m sind.
     */
    public void calcGaps() {
        double max = 0;
        for (int i = 0, j = 1; i < cars.size(); i++, j++) {
            if (j < cars.size()) {
                if (cars.get(j).getStart() - cars.get(i).getEnd() > 2000) {
                    max++;
                }
            }
        }
        if (cars.get(0).getStart() > 2000)
            max++;
        if (Const.SIZE - cars.get(cars.size() - 1).getEnd() > 2000)
            max++;
        parkGaps.add(max);
        if (!MapForDist.containsKey(cars.size()))
            MapForDist.put(cars.size(), 0);
        MapForDist.put(cars.size(), MapForDist.get(cars.size()) + 1);
    }
}
