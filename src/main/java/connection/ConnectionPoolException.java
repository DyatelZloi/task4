package connection;

/**
 * Created by DiZi on 29.11.2015.
 */
public class ConnectionPoolException extends RuntimeException{

    public ConnectionPoolException() {
        super();
    }

    public ConnectionPoolException(String message) {
        super(message);
    }

    public ConnectionPoolException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConnectionPoolException(Throwable cause) {
        super(cause);
    }
}