package Lesson2;

import java.util.Arrays;

public class Task_2_3 {
    public static void main(String[] args) {

    int a = 3;
    int b = 9;
    int c = -1;

    System.out.println("Числа в переменных a,b и c: " + a + "," + b + "," + c);

    // Заполним массив, отсортируем, а затем присвоим значения переменным
    int array[] = new int[]{a,b,c};
    Arrays.sort(array);
    System.out.println("Возврастающая последовательность: " + Arrays.toString(array));

    }
}
