package Lesson7;

import Lesson4.IntList;

public class Task10 {
    public static void main(String[] args) {

//        // Задрание 1
//        System.out.println("Earth weight: " + Planets.Earth.getWeight());
//        System.out.println("Earth radius: " + Planets.Earth.radius);

        // Задание 2
        List<String> list = new LinkedList();
        list.add("Адын");
        list.add("Дыва");
        list.add("Тари");
        list.add("Шатыры");
        list.add("Пьять");

        for (String s :
                list) {
            System.out.println(s);
        }


    }
}
