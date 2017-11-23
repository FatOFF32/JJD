package Patterns;


import java.util.*;

public class Sensor {

    private List<Alarm> list = new ArrayList<>();

    public static void main(String[] args) {

        Sensor sensor = new Sensor();

        sensor.addAlarm(sensor.new Green());
        sensor.addAlarm(sensor.new Yellow());
        sensor.addAlarm(sensor.new Red());

        for (int i = 0; i < 120; i++) {
            sensor.tempChanget(i);
        }

        for (int i = 120; i > 90; i--) {
            sensor.tempChanget(i);
        }

        for (int i = 90; i < 320; i++) {
            sensor.tempChanget(i);
        }
    }

    public void addAlarm(Alarm alarm){
        list.add(alarm);
    }

    private void notifyAlarm(int temp){
        for (Alarm alarm : list){
            alarm.tempChanget(temp);
        }
    }

    public void tempChanget(int temp){
        System.out.print(temp + " ");

        notifyAlarm(temp);
    }

    interface Alarm{
        void tempChanget(int temp);
    }

    abstract class abstractAlarm implements Alarm{
        final int tempOper;
        boolean oper;

        protected abstractAlarm(int tempOper) {
            this.tempOper = tempOper;
        }

        @Override
        public void tempChanget(int temp) {
            if (temp > tempOper && !oper){
                notifyAlarm();
                oper = true;
            }
            else if (temp < tempOper && oper)
                oper = false;
        }

        abstract void notifyAlarm();
    }

    public class Green extends abstractAlarm{

        Green() {
            super(100);
        }

        @Override
        void notifyAlarm() {
            System.out.println("\n ЗЕЛЕНЫЙ УРОВЕНЬ УГРОЗЫ! Температура превысила 100 градусов!");
        }

    }

    class Yellow extends abstractAlarm{

        Yellow() {
            super(200);
        }

        @Override
        void notifyAlarm() {
            System.out.println("\n ЖЕЛТЫЙ УРОВЕНЬ УГРОЗЫ! Температура превысила 200 градусов!");
        }

    }

    class Red extends abstractAlarm{

        Red() {
            super(300);
        }

        @Override
        void notifyAlarm() {
            System.out.println("\n КРАСНЫЙ УРОВЕНЬ УГРОЗЫ! Температура превысила 300 градусов!");
        }

    }
}
