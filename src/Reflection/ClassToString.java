package Reflection;

import Lessons7andAbove.Book;
import Lessons7andAbove.Item;

public class ClassToString {
    int int1 = 777;

    @Exclude(false)
    String string = "Hello";

    @Exclude(value = false)
    boolean gut = true;

    @Exclude
    String str = "НЕ УЧАСТВУЕТ!";

    Book book = new Book("Anrdew", "Супер", 15);
    Item item;
}
