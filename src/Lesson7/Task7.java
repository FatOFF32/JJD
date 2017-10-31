package Lesson7;

import static java.lang.System.out;

public class Task7 {
    public static void main(String[] args) {

        LinkedList linkedList = new LinkedList();
//        ArrayList linkedList = new ArrayList();

//        // Задание 2
//        linkedList.add(new Integer(1));
//        linkedList.add(new Integer(2));
//        linkedList.add(new Integer(3));
//        linkedList.add(new Integer(4));
//        linkedList.add(new Integer(5));
//        linkedList.add(new Integer(6));
//
//        for (Object o:linkedList)
//            out.println(o.toString());

        // Задание 3
        linkedList.add("aa");
        linkedList.add("s");
        linkedList.add("df");
        linkedList.add("a");
        linkedList.add("ee");
        linkedList.add("f");

        // Задание 3_а
        Object o = Utils.find(new Predicate() {
            @Override
            public boolean apply(Object o) {
                return "a".equals(o);
            }
        }, linkedList);

        out.println(o.toString());

        out.println("-------------");

        // Задание 3_б
        List list = Utils.filter(new Predicate() {
            @Override
            public boolean apply(Object o) {
                return o.toString().length() == 1;
            }
        }, linkedList);

        for(Object o1 : list)
            out.println(o1.toString());

        out.println("-------------");

        // Задание 3_в
        List list1 = Utils.transform(new Transformer() {
            @Override
            public String apply(Object o) {
                return o.toString() + o.toString();
            }
        }, linkedList);

        for (Object o2 : list1)
            out.println(o2.toString());

    }
}
