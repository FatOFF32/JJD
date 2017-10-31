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

    @Override
    public List cloneList() throws CloneNotSupportedException {
        return (List) this.clone();
    }

    public static void main(String[] args) {

        // Все проверяем в LinkedList

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
    public void push(Object object) {

        checkSize();

        // Сместим элементы на позицию вперед.
        for (int i = 0; i < size; i++)
            objects[size - i] = objects[size - i - 1];

        objects[0] = object;
        size++;
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
