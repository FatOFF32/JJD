package Lesson7;

import java.util.Iterator;

import static java.lang.System.out;

public class LinkedList implements List{

    Item head;
    private int size; // Для подсчета количества элементов в листе

    @Override
    public List getCopyList() {
        return new LinkedList();
    }

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
            Item item = head;
            @Override
            public boolean hasNext() {
                return item != null;
            }

            @Override
            public Object next() {
                Object o = item.value;
                item = item.next;
                return o;
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

    @Override
    public void push(Object object) {

        Item item = new Item(object);
        item.next = head;
        head = item;
        size++;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LinkedList that = (LinkedList) o;

        if (size != that.size) return false;

        Item item = head;
        Item thatItem = that.head;
        while (item != null)
            if(!item.equals(thatItem))
                return false;
        return true;
    }

    @Override
    public int hashCode() {

        Item item = head;
        int result = 0;

        while (item != null){
            result = 31 * result + size;
            item = item.next;
        }
        return result;
    }
}
