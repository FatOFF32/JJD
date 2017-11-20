package Patterns;

public class RussianFactory extends Factory {

    private RussianFactory() {
    }

    // Во всех непонятных случаях - создаем уаз!
    @Override
    public Car createCar() {
        return new Uaz();
    }

    @Override
    public Car createCar(String nameCar) {

        Car car;
        switch (nameCar){
            case "Uaz":
                car = new Uaz();
                break;
            default:
                car = new Uaz();
        }

        return car;
    }

    static Factory getFactory(){
        return new RussianFactory();
    }
}
