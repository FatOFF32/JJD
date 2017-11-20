package Patterns;

// Решил сделать абстрактный класс, а не интерфейс, т.к. на текущем этапе ту задачу можно решить проще через абстрактный класс
// Хотя, на самом деле, думаю что я не прав, т.к. возможно с абстрактный класс будет сложнее допиливать, когда каждая машина станет индивидуальной...
public abstract class Car {

    private final double fuelCons;

    protected Car(double fuelCons) {
        this.fuelCons = fuelCons;
    }

    public int drive(int distance, int fuel) {
        int possibleDist = (int) (fuel * 100 / fuelCons);

        if (distance > possibleDist)
            return possibleDist;
        return distance;
    }

}
