package Patterns;

public class GermanFactory extends Factory {
    private GermanFactory() {
    }

    static Factory getFactory(){
        return new GermanFactory();
    }
    @Override
    public Car createCar(String nameCar) {
        Car car;
        switch (nameCar){
            case "BMW":
                car = new Bmw();
                break;
            default:
                car = new Bmw();
        }
        return car;
    }

    @Override
    public Car createCar() {
        return new Bmw();
    }
}
