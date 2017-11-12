package Lessons7andAbove;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import static java.lang.System.out;

public class LinkedList <T> implements List<T>, Cloneable{

    Item head;
    private int size; // Для подсчета количества элементов в листе

    @Override
    public List getCopyList() {
        return new LinkedList();
    }

    public static void main(String[] args) {

    }

    @Override
    public void add(T object) {

        Item item = getItem(-1);
        if (item == null)
            head = new Item(object);
        else
            item.next = new Item(object);
        size++;
    }

    @Override
    public T get(int index) {

        if(listEmpty())
            return null;

        if(index > size)
            throw new MyIndexOutOfBoundsException("Индекс находится за пределами массива!");

        Item item = getItem(index);
        if (item == null){
            out.println("По этому индексу [" + index + "] ничего не нашлось.");
            return null;
        }
        return (T)item.value;
    }

    @Override
    public Iterator iterator() {
        int sizeToChange = size;

        return new Iterator() {
            Item item = head;
            @Override
            public boolean hasNext() {
                // Проверка срабатывания исключения.
                return item != null;
            }

            @Override
            public T next() {
                if(sizeToChange != size) // Упростим проверку, сделаем её только размеру списка.
                    throw new ConcurrentModificationException("Список был отредактирован!");
                T o = (T) item.value;
                item = item.next;
                return o;
            }
        };
    }

    @Override
    public T remove(int index) {

        T object;
        Item item;

        // Проверка на заполненность списка
        if(listEmpty())
            return null;

        // Удаление head
        if (index == 0){
            object = (T) head.value;
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
        object = (T) item.next.value;
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
    public void push(T object) {

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
        while (item != null) {
            if (!item.equals(thatItem))
                return false;
            item = item.next;
            thatItem = thatItem.next;
        }
        return true;
    }

    @Override
    public String toString() {

        Item item = head;

        if(item == null) return "";

        StringBuilder sb = new StringBuilder();
        sb.append("[");

        while (item != null) {
            sb.append(item.value.toString()).append(", ");
            item = item.next;
        }

        sb.delete(sb.length() - 2, sb.length());
        sb.append("]");

        return sb.toString();
    }

    @Override
    public int hashCode() {

        Item item = head;
        int result = 1;

        while (item != null){
            //result = item.hashCode();
            result = result * 31 + item.hashCode();// + size;
            item = item.next;
        }
        return result;
    }

    @Override
    public List cloneList() throws CloneNotSupportedException {
        return (List) this.clone();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {

        LinkedList listClone = new LinkedList();

        for (Object o : this) listClone.add(o);

        return listClone;
    }

}
