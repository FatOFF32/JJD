package Patterns;

public class JapanFactory extends Factory {

    private JapanFactory() {
    }

    // Во всех непонятных случаях - создаем тайоту!
    @Override
    public Car createCar() {
        return new Tayota();
    }

    @Override
    public Car createCar(String nameCar) {

        Car car;
        switch (nameCar){
            case "Tayota":
                car = new Tayota();
                break;
            default:
                car = new Tayota();
        }

        return car;
    }

    static Factory getFactory(){
        return new JapanFactory();
    }
}
