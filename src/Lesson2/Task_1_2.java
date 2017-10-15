package Lesson2;

import java.util.Scanner;

public class Task_1_2 {
    public static void main(String[] args){

        Scanner Scr = new Scanner(System.in);
        System.out.println("Введите число n");
        double n = Scr.nextDouble();

        System.out.println("Введите число m");
        double m = Scr.nextDouble();

        if (Math.abs(10-n) > Math.abs(10-m))
            System.out.println("Число M ближе к 10");
        else if (Math.abs(10-n) < Math.abs(10-m))
            System.out.println("Число N ближе к 10");
        else
            System.out.println("Число ровнозначно удалены от 10");

    }
}
