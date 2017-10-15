package Lesson2;

import java.util.Scanner;

public class Task_1_3 {
    public static void main(String[] args){

        Scanner Scr = new Scanner(System.in);
        System.out.println("Введите число a");
        double a = Scr.nextDouble();

        System.out.println("Введите число b");
        double b = Scr.nextDouble();

        System.out.println("Введите число c");
        double c = Scr.nextDouble();

        double x = (-b-Math.sqrt(b*b-4*a*c))/2*a;
        double x1 = (-b+Math.sqrt(b*b-4*a*c))/2*a;

        if (Double.isNaN(x) && Double.isNaN(x1))
            System.out.println("Корней НЕТ!");
        else
            System.out.println("Корни квадратного уравнения:" + x + " и " + x1);

    }
}
