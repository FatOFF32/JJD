package Lessons7andAbove;

public class ArrayCheckExeption extends Exception {

    public ArrayCheckExeption(String message) {
        super(message);
    }

    public ArrayCheckExeption(String message, Throwable cause) {
        super(message, cause);
    }
}
