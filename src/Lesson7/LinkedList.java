package Lesson7;

import java.util.Iterator;

import static java.lang.System.out;

public class LinkedList implements List{

    Item head;
    private int size; // Для подсчета количества элементов в листе

    public static void main(String[] args) {

//        // Задание 2
//        LinkedList linkedList = new LinkedList();
//        linkedList.add(new Integer(1));
//        linkedList.add(new Integer(2));
//        linkedList.add(new Integer(3));
//        linkedList.add(new Integer(4));
//        linkedList.add(new Integer(5));
//        linkedList.add(new Integer(6));
//
//        for (Object o:linkedList)
//            out.println(o.toString());

        LinkedList linkedList = new LinkedList();
        linkedList.add("b");
        linkedList.add("s");
        linkedList.add("d");
        linkedList.add("a");
        linkedList.add("e");
        linkedList.add("f");

        Object o = Utils.find(new Predicate() {
            @Override
            public boolean apply(Object o) {
                return "a".equals(o);
            }
        }, linkedList);

        out.println(o.toString());
    }

    @Override
    public void add(Object object) {

        Item item = getItem(-1);
        if (item == null)
            head = new Item(object);
        else
            item.next = new Item(object);
        size++;
    }

    @Override
    public Object get(int index) {

        if(listEmpty())
            return null;

        Item item = getItem(index);
        if (item == null){
            out.println("По этому индексу [" + index + "] ничего не нашлось.");
            return null;
        }
        return item.value;
    }

    @Override
    public Iterator iterator() {

        return new Iterator() {
            int i = 0;
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
    public Object remove(int index) {

        Object object;
        Item item;

        // Проверка на заполненность списка
        if(listEmpty())
            return null;

        // Удаление head
        if (index == 0){
            object = head.value;
            head = head.next;
            size--;
            return object;
        }

        // Проверка на корректность индекса
        item = getItem(index-1);
        if(item == null || item.next == null){
            out.println("Нет элемента в списке под индексом [" + index + "]");
            return null;
        }

        // Все проверки прошли, удаляем элемент, перепривязываем next, возвращаем удаленный объект.
        object = item.next.value;
        item.next = item.next.next;
        size--;

        return object;
    }

    @Override
    public int size() {

        return size;

    }


    // Служебные процедуры
    private Item getItem(int idx){

        Item item = head;
        int i = 0;

        // ещем Item простым перебором.
        // Если передали -1, значит возвращаем последний заполненный
        //      (Можно было обойтись и без этого, т.к. потом добавили переменную size)
        // если не нашли, возвращаем null
        while (item != null){
            if(i++ == idx || idx < 0 && item.next == null)
                break;
            item = item.next;
        }
        return item;
    }

    private boolean listEmpty(){
        if (size == 0){
            out.println("Список пуст! Операции получения и удаления элементов недоступны!");
            return true;
        }
        return false;
    }
}
