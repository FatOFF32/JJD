package Lesson4;

import static java.lang.System.out;

public class Library {

    final int maxQuantityBook = 10;
    BooksShelf[] arrayBooksShelf = new BooksShelf[maxQuantityBook];

    public static void main(String... args){

        Book book1 = new Book(new String("Фатов") , new String("Бла бла"), 20);
        Book book2 = new Book(new String("Тртилек") , new String("1С профессионал"), 45);
        Book book3 = new Book(new String("Пастушенко") , new String("Лотус МОЛОДЕЦ"),32);

        Library library = new Library();
        library.put(book1,  10);
        library.put(book2,  20);
        library.put(book3,  30);

        out.println("После операции осталось " + library.get(book2,16));
        out.println("После операции осталось " + library.get(book3,10));

        out.println("После операции осталось " + library.get(book2,10));
    }

    public int get(Book book, int quantity){

        int i;
        for (i = 0; i < arrayBooksShelf.length; i++){
            if (arrayBooksShelf[i] == null || arrayBooksShelf[i].book == book){
                break;
            }
        }

        if(arrayBooksShelf[i] == null || arrayBooksShelf[i].book != book)
            out.println("Такой книги в библиотеке нет!");
        else if (arrayBooksShelf[i].quantity < quantity)
            out.println("Запрошенного количества книг (" + quantity + " шт.) нет в библиотеке! Остаток книг: " + arrayBooksShelf[i].quantity);
        else
            arrayBooksShelf[i].quantity = arrayBooksShelf[i].quantity - quantity;

        if(arrayBooksShelf[i].quantity == quantity)
            arrayBooksShelf[i] = null;
        return arrayBooksShelf[i].quantity;

    }
    public void put(Book book, int quantity){

        int i;
        for (i = 0; i < arrayBooksShelf.length; i++){
            if (arrayBooksShelf[i] == null || arrayBooksShelf[i].book == book){
                break;
            }
        }
        if(arrayBooksShelf[i] == null)
            arrayBooksShelf[i] = new BooksShelf(book, quantity);
        else if(arrayBooksShelf[i].book != book)
            out.println("В данной библиотеке мест для книг НЕТ!");
        else
            arrayBooksShelf[i].quantity = arrayBooksShelf[i].quantity + quantity;

    }
}
