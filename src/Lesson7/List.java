package Lesson7;

public interface List <T> extends Iterable <T>, Cloneable{

    void add(T object);
    T get(int index);
    T remove(int index);
    int size();
    List getCopyList();
    void push(T object);
    List cloneList() throws CloneNotSupportedException;

}
