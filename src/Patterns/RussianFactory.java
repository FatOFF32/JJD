package Patterns;

public class RussianFactory extends Factory {
    //private final double fuelCons = 20.7;

    private RussianFactory() {
        super(20.7);
    }


//    @Override
//    Factory createCar(Factory car) {
//        if (car == null)
//            car = new RussianFactory();
//        return car;
//    }
    @Override
    static Factory createCar() {
        return new RussianFactory();
    }
}
