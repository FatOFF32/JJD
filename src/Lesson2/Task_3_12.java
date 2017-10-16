package Lesson2;

import java.lang.reflect.Array;
import java.util.Arrays;

import static java.util.Arrays.*;

public class Task_3_12 {
        public static void main(String[] args) {

        String[] arrayH = new String[59];

        for (int h = 1; h <= 59; h++) {
            StringBuffer buffer = new StringBuffer((h<10 ? "0": "") + Integer.toString(h));
            buffer.reverse();
            arrayH[h-1] = buffer.toString();
        }


        sort(arrayH);
        int x = binarySearch(arrayH, "24");

        System.out.println("Количество совпадений:" + (x+1));

    }
}
