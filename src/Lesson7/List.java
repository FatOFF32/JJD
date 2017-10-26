package Lesson7;

public interface List extends Iterable{

    void add(Object object);
    Object get(int index);
    Object remove(int index);
    int size();

}
