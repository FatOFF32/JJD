package Lesson2;

import java.util.Random;

public class Task_2_2 {
    public static void main(String[] args){

        Random rnd = new Random();

        // Сгенирируем случайное число до 1000, затем переведем его в строку
        int x = rnd.nextInt(1000);
        String xstring = Integer.toString(x);

        // Получим массив char-ов из строки
        char arreyX[] = xstring.toCharArray();

        int xmax = 0;
        int xTek;
        for (char c:arreyX) {
            xTek = Character.getNumericValue(c);
            if (xTek > xmax)
                xmax = xTek;
        }

        System.out.println("В числе " + x + " наибольшая цифра " + xmax);

    }
}
