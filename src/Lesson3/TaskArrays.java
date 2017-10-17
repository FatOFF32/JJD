package Lesson3;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

import static java.lang.System.out;

public class TaskArrays {
    public static void main(String[] args) {

        // Бонусная задача
        //bonusTask();
        //task_1();
        //task_2();
        //task_3();
        //task_4();
        //task_5();
        //task_6();
        //task_8();
        task_10();

    }

    public static void task_1(){

        int [] array = new int[10];
        for (int i = 1; i <= 10; i++)
            array[i-1] = i*2;

        // Вывод в столбец
        for (int a:array)
            out.println(a);

        // вывод в строку с пробелами
        for (int a:array)
            out.print(a + " ");
        out.println();

    }

    public static void task_2(){

        Integer [] array = new Integer[50];
        for (int i = 1; i <= 50; i++)
            array[i-1] = i*2 -1;

        // вывод в строку
        out.println(Arrays.toString(array));

        // в обратном порядке
        Arrays.sort(array, Collections.reverseOrder());
        out.println(Arrays.toString(array));

    }

    public static void task_3(){

        int [] array = new int[15];
        for (int i = 0; i < 15; i++)
            array[i] = (int)(Math.random() * 9);

        // вывод в строку
        out.println(Arrays.toString(array));

        // Подсчитаем количество четных
        int t = 0;
        for (int a:array)
            if (a%2 == 0)
                t++;
        out.println("Количество четных: " + t);

    }

    public static void task_4(){

        int [] array = new int[8];
        for (int i = 0; i < 8; i++)
            array[i] = 1 + (int)(Math.random() * 9);

        // вывод в строку
        out.println(Arrays.toString(array));

        // Обнулим нечетные
        for (int i = 1; i <= 7; i += 2)
            array[i] = 0;
        out.println(Arrays.toString(array));

    }

    public static void task_5(){

        int [] array = new int[5];
        for (int i = 0; i < 5; i++)
            array[i] = (int)(Math.random() * 5);

        int [] array1 = new int[5];
        for (int i = 0; i < 5; i++)
            array1[i] = (int)(Math.random() * 5);

        out.println(Arrays.toString(array));
        out.println(Arrays.toString(array1));

        double average = Arrays.stream(array).average().getAsDouble();
        double average1 = Arrays.stream(array1).average().getAsDouble();

//        out.println(average);
//        out.println(average1);

        if (average > average1)
            out.println("Первый массив The Best");
        else if (average < average1)
            out.println("Второй массив The Best");
        else out.println("Массивы одинаково круты!");

    }

    public static void task_6(){

        int [] array = new int[4];
        for (int i = 0; i < 4; i++)
            array[i] = 10 + (int)(Math.random() * 89);

        out.println(Arrays.toString(array));

        int [] array1 = array.clone();
        Arrays.sort(array1);

        if (Arrays.equals(array, array1))
            out.println("Массив строго возрастающий");
        else out.println("Массив НЕ строго возрастающий");

    }

    public static void task_8(){

        int [] array = new int[12];
        for (int i = 0; i < 12; i++)
            array[i] = (int)(Math.random() * 30) - 15;


        out.println(Arrays.toString(array));

        // Максимальное значение массива
        out.println("Максимальное значение массива: " + Arrays.stream(array).max().getAsInt());

//        int x = Arrays.stream(array).max().getAsInt();
//        Arrays.stream(array).filter((p)->p==x);



    }

    public static void task_10(){


    }

    // Бонусная задача
    public static void bonusTask() {

        Scanner scr = new Scanner(System.in);

        out.println("Введите слово для кодировки:");
        String text = scr.nextLine();

        out.println("Введите ключ для кодировки:");
        String key = scr.nextLine();

        String textCod = koding(text, key);
        out.println(textCod);

        String unCod = koding(textCod, key);
        out.println(unCod);

    }

    public static String koding(String text, String key){

        byte[] bytetext = text.getBytes();
        byte[] bytekey = key.getBytes();

        for (int i = 0; i < bytetext.length; i++)
            bytetext[i] ^= bytekey[i%bytekey.length];
        return new String(bytetext);

    }

}

