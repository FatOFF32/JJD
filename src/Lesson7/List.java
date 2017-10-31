package Lesson7;

public interface List extends Iterable, Cloneable{

    void add(Object object);
    Object get(int index);
    Object remove(int index);
    int size();
    List getCopyList();
    void push(Object object);
    List cloneList() throws CloneNotSupportedException;

}
