package Lesson1;

import javax.swing.*;

// Этот же модуль решает и 4 задачу.
public class Task_2 {
    public static void main(String[] args){

        int q = 585;
        int w = q;
        int i = 0;

        while (w != 0){
            i +=w%10;
            w /=10;
        }

        JOptionPane.showMessageDialog(null,"Сумма цифр " + i);

    }

}
