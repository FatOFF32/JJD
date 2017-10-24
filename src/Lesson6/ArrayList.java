package Lesson6;

import static java.lang.System.out;

public class ArrayList implements List, Queue, Stack{

    Object [] objects = new Object[5];
    private int size; // Для подсчета количества элементов в листе

    public static void main(String[] args) {

        ArrayList linkedList = new ArrayList();
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

        checkSize();

        objects[size] = object;
        size++;
    }

    @Override
    public Object get(int index) {

        if(listEmpty())
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

    @Override
    public Object poll() {

        // В условии задачи мы должны взять по FIFO, но для разнообразия возьмём по LIFO.
        return remove(size-1);
    }

    @Override
    public void push(Object object) {

        checkSize();

        // Сместим элементы на позицию вперед.
        for (int i = 0; i < size; i++)
            objects[size - i] = objects[size - i - 1];

        objects[0] = object;
        size++;
    }

    @Override
    public Object pop() {
        return remove(0);
    }

    // Служебные процедуры
    private boolean listEmpty(){
        if (size == 0){
            out.println("Список пуст! Операции получения и удаления элементов недоступны!");
            return true;
        }
        return false;
    }

    private void checkSize(){

        // Если массив полностью заполнен, тогда создадим новый, который больше в 1,5 раза и заполним его элементами.
        if(objects.length == size){
            Object [] objectsClone = objects.clone();
            objects = new Object[(int)(size * 1.5)];
            for (int i = 0; i < objectsClone.length; i++)
                objects[i] = objectsClone[i];
        }
    }
}
