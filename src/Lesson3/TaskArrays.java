package Lesson3;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

import static java.lang.System.out;

public class TaskArrays {
    public static final Charset UTF_8 = Charset.forName("UTF-8");

    public static void main(String[] args) {

        // Бонусная задача
        bonusTask();
        //task_1();
        //task_2();
        //task_3();
        //task_4();
        //task_5();
        //task_6();
        //task_8();
        //task_10();
        //task_14();
        //task_15();
        //task_16();
        //task_18();

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

    public static void task_14(){

        int [][] array = new int[8][5];
        for (int i = 0; i < array.length; i++)
            for (int ii = 0; ii < array[i].length; ii++)
                array[i][ii] = 10 + (int)(Math.random() * 89);


        for (int i = 0; i < array.length;i++)
            out.println(Arrays.toString(array[i]));

    }

    public static void task_15(){

        int max = 0;
        int [][] array = new int[5][8];
        for (int i = 0; i < array.length; i++)
            for (int ii = 0; ii < array[i].length; ii++) {
                array[i][ii] = (int) (Math.random() * 198) - 99;
                max = Integer.max(max, array[i][ii]);
            }


        for (int i = 0; i < array.length;i++)
            out.println(Arrays.toString(array[i]));

        out.println("Максимальное значение:" + max);

    }

    public static void task_16(){

        int[] indexMax = new int[3];
        int[][] array = new int[7][4];
        for(int i = 0; i < array.length; i++) {
            indexMax[2] = 1;
            for (int ii = 0; ii < array[i].length; ii++) {
                array[i][ii] = (int) (Math.random() * 10) - 5;
                indexMax[2] = indexMax[2] * Math.abs(array[i][ii]);
            }

            if (indexMax[0] < indexMax[2]) {
                indexMax[0] = indexMax[2];
                indexMax[1] = i;
            }
            out.println(Arrays.toString(array[i]));

        }

        out.println("Индекс строки с наибольшем по модулю произведением элементов: " + indexMax[1]);

    }

    public static void task_18() {

        String test = new String();
        int[][] array = new int[2][15];
        for (int i = 0; i < 15; i++) {
            int[] arrayTest = getArray();
            while (test.contains(arrayTest[0] + "x" + arrayTest[1])
                    || test.contains(arrayTest[1] + "x" + arrayTest[0])
                    || arrayTest[1] == arrayTest[0]) {
                arrayTest = getArray();
            }
            array[0][i] = arrayTest[0];
            array[1][i] = arrayTest[1];
            test = test + arrayTest[0] + "x" + arrayTest[1] + arrayTest[1] + "x" + arrayTest[0];

        }
        out.println(Arrays.toString(array[0]));
        out.println(Arrays.toString(array[1]));
    }

    public static int[] getArray(){

        int[] arrayTest = new int[2];
        arrayTest[0] = 2 + (int) (Math.random() * 7);
        arrayTest[1] = 2 + (int) (Math.random() * 7);

        return arrayTest;

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

    // Бонусная задача
    public static void bonusTask2() {
        Scanner scr = new Scanner(System.in);

        out.println("Введите слово для кодировки:");
        String text = scr.nextLine();

        out.println("Введите ключ для кодировки:");
        String key = scr.nextLine();

        byte[] encoded = koding(text.getBytes(), key.getBytes());
        out.println(new String(encoded));

        String unCod = new String(koding(encoded, key.getBytes()));
        out.println(unCod);

    }


    public static String koding(String text, String key){

        byte[] bytetext = text.getBytes(UTF_8);
        byte[] bytekey = key.getBytes(UTF_8);

        for (int i = 0; i < bytetext.length; i++)
            bytetext[i] ^= bytekey[i%bytekey.length];
        return new String(bytetext, UTF_8);

    }

    public static byte[] koding(byte[] bytetext, byte[] bytekey){
        for (int i = 0; i < bytetext.length; i++)
            bytetext[i] ^= bytekey[i%bytekey.length];

        return bytetext;
    }
}

