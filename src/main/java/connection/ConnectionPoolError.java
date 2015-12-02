package connection;

/**
 * Created by DiZi on 29.11.2015.
 */
public class ConnectionPoolError extends Exception{
    public ConnectionPoolError (Throwable cause) {
        super(cause);
    }
}
