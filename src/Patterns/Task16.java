package Patterns;

public class Task16 {
    public static void main(String[] args) {

        Factory jp = Factory.getFactory("JP");
        Factory de = Factory.getFactory("DE");
        Factory ru = Factory.getFactory("RU");

        Car tayota = jp.createCar();
        Car bmw = de.createCar();
        Car uaz = ru.createCar();

        System.out.println("tayota: " + tayota.drive(100, 3));
        System.out.println("bmw: " + bmw.drive(100, 3));
        System.out.println("uaz: " + uaz.drive(100, 3));


    }
}
