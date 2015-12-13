package action;

/**
 * Created by DiZi on 13.12.2015.
 */
public class ActionException extends RuntimeException {

    public ActionException(String message) {
        super(message);
    }

    public ActionException(String message, Exception cause) {
        super(message, cause);
    }
}