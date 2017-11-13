package Lessons7andAbove;

import MadCalc.Calc;
import MadCalc.NotCorrectCurrentOperands;

import java.util.Scanner;

public class Task13 {

    public static void main(String[] args) {

        // Небольшая проверка по исключениям. урок 13 задача 1
//        List list = new ArrayList();
//        list.add(1);
//        list.add(2);
//        list.add(3);
//        list.add(4);
//        list.add(5);
//        list.add(6);
//        list.add(7);

        // 1.a
        //System.out.println(list.get(100));

        // 1.b
//        for (Object o : list)
//            System.out.println(o);

        Scanner scr = new Scanner(System.in);
        int result = 0;
        try {
            result = Calc.execute(scr.nextLine());
        } catch (NotCorrectCurrentOperands notCorrectCurrentOperands) {
            notCorrectCurrentOperands.printStackTrace();
           
        }
        finally {
            // Злой калькулятор может и не посчитать, но опплюёт ВСЕГДА!
            System.out.println("Тьфу на тебя ещё раз!");
        }

        System.out.println(result);


    }
}
