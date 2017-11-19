package Patterns;

public class JapanFactory extends Factory {
    private final double fuelCons = 9.3;

    private JapanFactory() {
        super(9.3);
    }

//    @Override
//    public double getFuelCons() {
//        return fuelCons;
//    }

//    @Override
//    Factory createCar(Factory car) {
//        if (car == null)
//            car = new JapanFactory();
//        return car;
//    }
    @Override
    static Factory createCar() {
        return new JapanFactory();
    }
}
