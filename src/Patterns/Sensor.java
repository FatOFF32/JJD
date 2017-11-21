package Patterns;

public class Sensor {

    

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

    class Green extends abstractAlarm{

        Green() {
            super(100);
        }

        @Override
        void notifyAlarm() {
            System.out.println("ЗЕЛЕНЫЙ УРОВЕНЬ УГРОЗЫ! Температура превысила 100 градусов!");
        }

    }

    class Yellow extends abstractAlarm{

        Yellow() {
            super(200);
        }

        @Override
        void notifyAlarm() {
            System.out.println("ЖЕЛТЫЙ УРОВЕНЬ УГРОЗЫ! Температура превысила 200 градусов!");
        }

    }

    class Red extends abstractAlarm{

        Red() {
            super(300);
        }

        @Override
        void notifyAlarm() {
            System.out.println("КРАСНЫЙ УРОВЕНЬ УГРОЗЫ! Температура превысила 300 градусов!");
        }

    }
}
