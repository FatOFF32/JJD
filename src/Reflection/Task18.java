package Reflection;

import Lessons7andAbove.Utils;

public class Task18 {
    public static void main(String[] args) throws IllegalAccessException, ClassNotFoundException, InstantiationException {

//        // 1
//        System.out.println(Utils.toString(new ClassToString()));

        // 2
        DIContext injector = DIContext.instance();

        B b = injector.getObject("Reflection.B");
        I i = injector.getObject("Reflection.Impl");


        System.out.println("Before injection: " + b);
        System.out.println("Before injection: " + i);

        System.out.println("assert = " + (i == b.getI()));

        injector.inject(b);
        injector.inject(i);

        System.out.println("After injection: " + b);
        System.out.println("After injection: " + i);

        System.out.println("assert = " + (i == b.getI()));

        assert i == b.getI(); // что за хрень?

    }
}
