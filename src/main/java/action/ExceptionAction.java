package action;

/**
 * Created by Malkov Nikifor on 13.12.2015.
 */
public class ExceptionAction extends RuntimeException {

    public ExceptionAction() {super();}

    public ExceptionAction(String message) {
        super(message);
    }

    public ExceptionAction(String message, Throwable cause) {
        super(message, cause);
    }

    public ExceptionAction(Throwable cause) {
        super(cause);
    }

}