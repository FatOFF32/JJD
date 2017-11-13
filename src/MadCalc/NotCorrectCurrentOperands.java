package MadCalc;

public class NotCorrectCurrentOperands extends Exception {

    public NotCorrectCurrentOperands(String message) {
        super(message);
    }

    public NotCorrectCurrentOperands(String message, Throwable cause) {
        super(message, cause);
    }
}
