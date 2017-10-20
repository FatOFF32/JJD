package Lesson4;

public class Library {

    final int maxQuantityBook = 10;
    BooksShelf[] arrayBooksShelf = new BooksShelf[maxQuantityBook];

    public void put(Book book, int quality){

        BooksShelf booksShelf;
        int i;
        for (i = 0; i < arrayBooksShelf.length; i++){
            if (arrayBooksShelf[i] == null || arrayBooksShelf[i].book == book){
                booksShelf = arrayBooksShelf[i];
                break;
            }
        }
        if(arrayBooksShelf[i] == null)
            arrayBooksShelf[i] = new BooksShelf(book, quality);
        else if(arrayBooksShelf[i].book != book)
            System.out.println("В данной библиотеке мест для книг НЕТ!");
        else
            arrayBooksShelf[i].quantity = arrayBooksShelf[i].quantity + quality;

    }


}
