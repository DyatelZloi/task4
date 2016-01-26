package entity;

/**
 * Created by Malkov Nikifor on 30.12.2015.
 */
public class FactoryException extends RuntimeException {

    public FactoryException() {super();}

    public FactoryException(String message) {
        super(message);
    }

    public FactoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public FactoryException(Throwable cause) {
        super(cause);
    }

}