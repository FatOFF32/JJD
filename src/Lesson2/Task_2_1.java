package Lesson2;

public class Task_2_1 {
    public static void main(String[] args){

        int min = 5;
        int max = 155;

        int result = random(min, max);
        String pref = result>25&& result<100? " ": " не ";

        System.out.println("Число " + result + pref + "содержится в интервале (25,100)");

    }

    public static int random(int min, int max){

        max -= min;
        return (int) (Math.random()*max + min);
    }
}
