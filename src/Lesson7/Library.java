package Lesson7;


public class Library {

    int sizeArray = 6;
    List [] arrayBooks = new List[sizeArray];

    public static void main(String... args){

        Book book1 = new Book(new String("Фатов") , new String("Бла бла"), 20);
        Book book2 = new Book(new String("Тртилек") , new String("1С профессионал"), 45);
        Book book3 = new Book(new String("Пастушенко") , new String("Лотус МОЛОДЕЦ"),32);
        Book book4 = new Book(new String("Фатов") , new String("Бла бла. вер. 2"), 20);
        Book book5 = new Book(new String("Тртилек") , new String("1С профессионал. вер. 2"), 45);
        Book book6 = new Book(new String("Пастушенко") , new String("Лотус МОЛОДЕЦ. вер. 2"),32);

        Library library = new Library();

        // Задание 6
        library.put(book1,  10);
        library.put(book2,  20);
        library.put(book3,  30);
        library.put(book4,  10);
        library.put(book5,  20);
        library.put(book6,  30);

        library.put(book3,  3);
        library.put(book4,  4);

        // Выведем остаток книг
        BooksShelf booksShelf;
        for (int i = 0; i < library.arrayBooks.length; i++) {
            if (library.arrayBooks[i] == null)
                continue;
            for(Object o : library.arrayBooks[i]){
                booksShelf = (BooksShelf)o;
                System.out.println("Ячейка " + i + " " + booksShelf.book + " остаток " + booksShelf.quantity);
            }
        }

    }

    public void put(Book book, int quantity){

        // Получим индекс в массиве книжных полок через хеш-код
        int i = Math.abs(book.hashCode()%sizeArray);
        //i = 0;

        // Если ниша свободна, добавим туда создадим новый Лист
        if (arrayBooks[i] == null){
            arrayBooks[i] = new LinkedList(); // можно использовать и new ArrayList();
        }
        // Если ниша занята, переберем книги и добавим количество к найденным книгам, либо добавим книгу рядом
        else {
            BooksShelf booksShelf;
            for(Object o : arrayBooks[i]){
                booksShelf = (BooksShelf)o;
                if (booksShelf.book.equals(book)){
                    booksShelf.quantity += quantity;
                    return;
                }
            }
        }
        arrayBooks[i].add(new BooksShelf(book, quantity));
    }
}
