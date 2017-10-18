package Lesson4;

public class IntList {
    Item head;

    public static void main(String... args){

        

    }

    public void add(int i){

        Item item = head;
        while (item.next != null)
            item = item.next;

        item.next = new Item();
        item.next.value = i;
    }

    public int get(int index){

        Item item = head;
        for (int i = 0; i <= index; i++){
            item = item.next;
            if(item.next == null){
                System.out.println("Чувак, у тебя не правильный индекс!");
                return -1;
            }
        }
        return item.value;
    }
    public int remove(int index){

        Item item = head;

        for (int i = 0; i < index; i++){
            item = item.next;
            if(item.next == null) {
                System.out.println("Чувак, у тебя не правильный индекс!");
                return -1;
            }
        }
        item.next = item.next.next;
        return item.next.value;
    }
}
