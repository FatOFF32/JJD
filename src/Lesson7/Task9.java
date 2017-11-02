package Lesson7;

import java.io.File;

public class Task9 {

    public static void main(String[] args) {

        LinkedList list1 = new LinkedList();
        list1.add(1);
        list1.add(2);
        list1.add(3);
        list1.add(4);
        list1.add(5);
        list1.add(6);

        LinkedList list2 = new LinkedList();
        list2.add(10);
        list2.add(20);
        list2.add(30);
        list2.add(40);

        Iterable itList1 = Utils.filterView(new Predicate() {
            @Override
            public boolean apply(Object o) {
                return ((int) o) % 2 == 0;
            }
        }, list1);
        Iterable itList2 = Utils.transformView(new Transformer2() {
            @Override
            public int apply(Object o) {
                return ((int) o) / 5;
            }
        }, list2);

        Iterable itResult = Utils.view(itList1, itList2);

        for (Object o :
                itResult) {
            System.out.println(o.toString());
        }


    }
}
