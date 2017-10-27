package Lesson7;


import java.util.Iterator;

import static java.lang.System.out;

public class ArrayList implements List {
    @Override
    public List getCopyList() {
        return new ArrayList();
    }

    Object[] objects = new Object[5];
    private int size; // Для подсчета количества элементов в листе

    public static void main(String[] args) {

//        // Задание 2
//        ArrayList linkedList = new ArrayList();
//        linkedList.add(new Integer(1));
//        linkedList.add(new Integer(2));
//        linkedList.add(new Integer(3));
//        linkedList.add(new Integer(4));
//        linkedList.add(new Integer(5));
//        linkedList.add(new Integer(6));
//
//        for (Object o : linkedList)
//            out.println(o.toString());

        // Задание 3_а и 3_b
        ArrayList linkedList = new ArrayList();
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

    }

    @Override
    public Iterator iterator() {
        return new Iterator() {
            int i;

            @Override
            public boolean hasNext() {
                return i < size;
            }

            @Override
            public Object next() {
                return get(i++);
            }
        };
    }

    @Override
    public void add(Object object) {

        checkSize();

        objects[size] = object;
        size++;
    }

    @Override
    public Object get(int index) {

        if (listEmpty())
            return null;

        return objects[index];
    }

    @Override
    public Object remove(int index) {

        Object object;

        // Проверка на заполненность списка
        if (listEmpty())
            return null;

        object = objects[index];

        for (int i = index; i < size - 1; i++)
            objects[i] = objects[i + 1];
        size--;

        return object;
    }

    @Override
    public int size() {
        return size;
    }

    // Служебные процедуры
    private boolean listEmpty() {
        if (size == 0) {
            out.println("Список пуст! Операции получения и удаления элементов недоступны!");
            return true;
        }
        return false;
    }

    private void checkSize() {

        // Если массив полностью заполнен, тогда создадим новый, который больше в 1,5 раза и заполним его элементами.
        if (objects.length == size) {
            Object[] objectsClone = objects.clone();
            objects = new Object[(int) (size * 1.5)];
            System.arraycopy(objectsClone, 0, objects, 0, objectsClone.length);
        }
    }
}
