package Patterns;

public class Task16 {
    public static void main(String[] args) {

        Factory jp = Factory.getFactory("JP");
        Factory de = Factory.getFactory("DE");
        Factory ru = Factory.getFactory("RU");

        System.out.println("JP: " + jp.drive(100, 3));
        System.out.println("DE: " + de.drive(100, 3));
        System.out.println("RU: " + ru.drive(100, 3));


    }
}
