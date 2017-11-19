package Patterns;

public class GermanFactory extends Factory {
    private final double fuelCons = 10.7;

    private GermanFactory() {
        super(10.7);
    }

//    @Override
//    Factory createCar(Factory car) {
//        if (car == null)
//            car = new GermanFactory();
//        return car;
//    }
    @Override
    Factory createCar() {
        return new GermanFactory();
    }

}
