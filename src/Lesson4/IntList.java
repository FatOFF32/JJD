package Lesson4;

import static java.lang.System.in;
import static java.lang.System.out;

public class IntList {
    Item head;

    public static void main(String... args){

//        // проверка работоспособности. задание 2
//        IntList intList = new IntList(10);
//        intList.add(17);
//        intList.add(25);
//        intList.add(27);
//        intList.add(40);
//
//        for (int i = 0; i < 5; i++)
//            out.println(intList.get(i));
//
//        out.println("Удаляем! 3 и 0");
//        intList.remove(3);
//        intList.remove(0);
//
//        for (int i = 0; i < 3; i++)
//            out.println(intList.get(i));
//
////        intList.remove(5);
////        out.println(intList.get(5));

        // проверка работоспособности задание 3
        IntList intList = new IntList(10);
        intList.addSort(5);
        intList.addSort(25);
        intList.addSort(20);
        intList.addSort(15);

        for (int i = 0; i < 5; i++)
            out.println(intList.get(i));

    }
    public IntList(int valueHead){
        head = new Item(valueHead);
    }
    public void add(int i){

        Item item = head;
        while (item.next != null)
            item = item.next;

        item.next = new Item(i);
    }
    public void addSort(int i){

        Item item = head;
        while (item.next != null &&
                !(item.value <= i && i <= item.next.value)){
            item = item.next;
        }

        if (item == head && item.value > i){
            Item itemNext = new Item(i);
            itemNext.next = item;
            head = itemNext;
        }
        else if (item.next == null)
            item.next = new Item(i);
        else {
            Item itemNext = new Item(i);
            itemNext.next = item.next;
            item.next = itemNext;
        }
    }
    public int get(int index){

        Item item = getItem(index);
        if (item == null)
            return -1;
        return item.value;
    }
    public int remove(int index){

        Item item = getItem(index-1);
        Item itemRemove = getItem(index);

        if (itemRemove == null)
            return -1;
        else if (index == 0)
            head = itemRemove.next;
        else item.next = itemRemove.next;

        return itemRemove.value;
    }
    private Item getItem(int index){

        Item item = head;
        for (int i = 0; i < index; i++){
            if (item == null) {
                out.println("Чувак, у тебя не правильный индекс!");
                break;
            }
            item = item.next;
        }
        return item;
    }
}
