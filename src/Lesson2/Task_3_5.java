package Lesson2;

import java.util.Scanner;

public class Task_3_5 {
    public static void main(String[] args) {

        Scanner scr = new Scanner(System.in);
        int x = scr.nextInt();

        int m = 1;
        for (int z = 1; z <= x; z++)
            m = m*z;
        System.out.println(m);

    }
}
