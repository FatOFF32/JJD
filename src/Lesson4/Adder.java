package Lesson4;

import static java.lang.System.out;

public class Adder {
    int value;
    int step;

    public static void main(String... args){

        Adder adder = new Adder(15);
        out.println(adder.getValue());

        adder.add();
        out.println(adder.value);
        adder.add();
        out.println(adder.value);
    }

    public Adder(int step){
        this.step = step;
    }

    public int getValue(){

        return value;

    }

    public void add(){
        value += step;
    }
}
