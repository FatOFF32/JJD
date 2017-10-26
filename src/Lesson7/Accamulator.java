package Lesson7;

public class Accamulator {

    public static void main(String[] args) {

        class Plus implements Operation{
            @Override
            public int getResult(int a, int b) {
                return a + b;
            }
        }

        class Minus implements Operation{
            @Override
            public int getResult(int a, int b) {
                return a - b;
            }
        }

        class Multipy implements Operation{
            @Override
            public int getResult(int a, int b) {
                return a * b;
            }
        }

        Plus plus = new Plus();
        System.out.println("2 + 5 = " + plus.getResult(2, 5));

        Minus minus = new Minus();
        System.out.println("2 - 5 = " + minus.getResult(2, 5));

        Multipy multipy = new Multipy();
        System.out.println("2 * 5 = " + multipy.getResult(2, 5));

//        Operation oper = new Operation() {
//            @Override
//            public int getResult() {
//                return 0;
//            }
//
//        };

    }
}
