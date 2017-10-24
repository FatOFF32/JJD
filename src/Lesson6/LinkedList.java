package Lesson6;

public class LinkedList implements List, Queue, Stack{

    Item head;

    @Override
    public void add(Object object) {

        Item item = getItem(-1);
        if (item == null)
            head = new Item(object);
        else item.next = new Item(object);

    }

    @Override
    public Object get(int index) {

        Item item = getItem(index);
        if (item == null){
            System.out.println("По этому индексу [" + index + "] ничего не нашлось.");
            return null;
        }
        return item.value;
    }

    @Override
    public Object remove(int index) {

        Object object;
        Item item;

        // Проверка на заполненность списка
        if(head == null){
            System.out.println("Мне нечего удалять!");
            return null;
        }

        // Удаление head
        if (index == 0){
            object = head.value;
            head = head.next;
            return object;
        }

        // Проверка на корректность индекса
        item = getItem(index);
        if(item == null || item.next == null){
            System.out.println("Нет элемента в списке под индексом [" + index + "]");
            return null;
        }

        // Все проверки прошли, удаляем элемент, перепривязываем next, возвращаем удаленный объект.
        object = item.next.value;
        item.next = item.next.next;

        return object;
    }

    @Override
    public int size() {

        Item item = head;
        int i = 0;

        // Вернём количество эллементов в связанном списке.
        while (item != null){
            i++;
            item = item.next;
        }

        return i;
    }

    @Override
    public Object poll() {
        return null;
    }

    @Override
    public void push(Object object) {

    }

    private Item getItem(int idx){

        Item item = head;
        int i = 0;

        // ещем Item простым перебором.
        // Если передали -1, значит возвращаем последний заполненный
        // если не нашли, возвращаем null
        while (item != null){
            if(i++ == idx || i < 0 && item.next == null)
                break;
            item = item.next;
        }
        return item;
    }

    @Override
    public Object pop() {
        return null;
    }
}
