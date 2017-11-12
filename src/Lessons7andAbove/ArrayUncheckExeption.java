package Lessons7andAbove;

public class ArrayUncheckExeption extends RuntimeException {

    public ArrayUncheckExeption(String message) {
        super(message);
    }

    public ArrayUncheckExeption(String message, Throwable cause) {
        super(message, cause);
    }
}

