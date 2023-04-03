package exceptions;


public class ExceptionHandler extends Exception {
    String message;

    public ExceptionHandler() {
    }

    public ExceptionHandler(String message) {
        super();
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
