package Lesson2;

public class Task_2_4 {
    public static void main(String[] args) {

        int x = (int) (Math.random() * 28800);
        int hour;
        String text;

        hour = x/3600;

        if (hour>4) text = "Остаток " + hour + " часов";
        else if (hour<5 && hour > 1) text = "Остаток " + hour + " часа";
        else if ( hour == 1) text = "Остаток " + hour + " час";
        else text = "Осталось менее часа";

        System.out.println(x);
        System.out.println(text);

    }
}
