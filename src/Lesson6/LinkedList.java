package Lesson6;

import static java.lang.System.out;

public class LinkedList implements List, Queue, Stack{

    Item head;
    private int size; // Для подсчета количества элементов в листе

    public static void main(String[] args) {

        LinkedList linkedList = new LinkedList();
        linkedList.add(new Integer(1));
        linkedList.add(new Integer(2));
        linkedList.add(new Integer(3));
        linkedList.add(new Integer(4));
        linkedList.add(new Integer(5));
        linkedList.add(new Integer(6));
        linkedList.push(new Integer(7));
        linkedList.push(new Integer(8));
        linkedList.poll();
        linkedList.pop();
        linkedList.remove(4);
        for (int i = 0; i < linkedList.size(); i++)
            out.println(linkedList.get(i));
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

        // Так будет дешевле получать количесто...
        return size;

//        Item item = head;
//        int i = 0;
//
//        // Вернём количество эллементов в связанном списке.
//        while (item != null){
//            i++;
//            item = item.next;
//        }
//
//        return i;
    }

    @Override
    public Object poll() {

        // В условии задачи мы должны взять по FIFO, но для разнообразия возьмём по LIFO.
        return remove(size-1);
    }

    @Override
    public void push(Object object) {

        Item item = new Item(object);
        item.next = head;
        head = item;
        size++;
    }

    @Override
    public Object pop() {
        return remove(0);
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
