package Patterns;

public class Task16 {
    public static void main(String[] args) {

        // 1
        Factory jp = Factory.getFactory("JP");
        Factory de = Factory.getFactory("DE");
        Factory ru = Factory.getFactory("RU");

        Car tayota = jp.createCar();
        Car bmw = de.createCar();
        Car uaz = ru.createCar();

        System.out.println("tayota: " + tayota.drive(100, 3));
        System.out.println("bmw: " + bmw.drive(100, 3));
        System.out.println("uaz: " + uaz.drive(100, 3));

        // 2
        Pizza pizza = new Pizza.Builder(true).addBecon(2).addCheese(3).build();
        Pizza pizza1 = Pizza.builder1(true).addBecon(2).addSauseg(8).build();
        System.out.println(pizza);
        System.out.println(pizza1);


    }
}
