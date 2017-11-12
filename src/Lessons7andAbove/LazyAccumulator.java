package Lessons7andAbove;

public class LazyAccumulator {

    int value = 0;
    List list = new ArrayList();

    public static void main(String[] args) {

        LazyAccumulator acc = new LazyAccumulator();

//        // Задание 4
//        acc.add(5, new Plus());
//        acc.add(2, new Minus());
//        acc.add(3, new Multipy());
//
//        System.out.println("Результат вычисления: " + acc.calculate());

        // Задание 5
        acc.push(5, new Plus());
        acc.push(2, new Minus());
        acc.push(3, new Multipy());

        System.out.println("Результат вычисления: " + acc.calculate());

    }

    public int calculate(){

        System.out.println("Промежуточные результаты:");
        for (Object oper : list) {
            System.out.println(value + oper.toString() + " = " + (value =+ ((Operation) oper).getResult(value)));
        }

        return value;
    }

    public void add(int b, Operation oper){
        oper.saveArg(b); // можно бы было облегчить условие через конструктор (например new Plus(10)), но будем придерживаться условию задачи
        list.add(oper);
    }

    public void push(int b, Operation oper){
        oper.saveArg(b); // можно бы было облегчить условие через конструктор (например new Plus(10)), но будем придерживаться условию задачи
        list.push(oper);
    }

    static class Plus implements Operation{
        int b;
        @Override
        public void saveArg(int b) {
            this.b = b;
        }

        @Override
        public String toString() {
            return " + " + b;
        }

        @Override
        public int getResult(int a) {
            return a + b;
        }
    }

    static class Minus implements Operation{
        int b;
        @Override
        public void saveArg(int b) {
            this.b = b;
        }

        @Override
        public String toString() {
            return " - " + b;
        }

        @Override
        public int getResult(int a) {
            return a - b;
        }
    }

    static class Multipy implements Operation{
        int b;
        @Override
        public void saveArg(int b) {
            this.b = b;
        }

        @Override
        public String toString() {
            return " * " + b;
        }

        @Override
        public int getResult(int a) {
            return a * b;
        }
    }

}
