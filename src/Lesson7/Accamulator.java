package Lesson7;

public class Accamulator {

    int value = 0;

    public static void main(String[] args) {

        Accamulator acc = new Accamulator();
        acc.accamulate(5, new Plus());
        acc.accamulate(2, new Minus());
        acc.accamulate(3, new Multipy());

    }

     void accamulate(int b, Operation oper){

        oper.saveArg(b);
        System.out.println(value + oper.toString() + " = " + (value =+ oper.getResult(value)));

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
