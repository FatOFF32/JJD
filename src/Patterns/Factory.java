package Patterns;

public abstract class Factory {

    private final double fuelCons;

    private static Factory jp;
    private static Factory de;
    private static Factory ru;

    Factory(double fuelCons) {
        this.fuelCons = fuelCons;
    }

    //    @Override
    //    Factory createCar(Factory car) {
    //        if (car == null)
    //            car = new RussianFactory();
    //        return car;
    //    }
        abstract Factory createCar();

    double getFuelCons() {
        return fuelCons;
    }

    public int drive(int distance, int fuel) {
        int possibleDist = (int) (fuel * 100 / fuelCons);

        if (distance > possibleDist)
            return possibleDist;
        return distance;
    }

    public static Factory getFactory(String countryCar){

        Factory car = null;

        switch (countryCar){
            case "JP":
                if (jp == null)
                    jp = JapanFactory.createCar();
                car = jp;
                break;
            case "DE":
                if (de == null)
                    de = de.createCar();
                car = de;
                break;
            case "RU":
                if (ru == null)
                    ru = ru.createCar();
                car = ru;
                break;
        }
        return car;

    }
}
