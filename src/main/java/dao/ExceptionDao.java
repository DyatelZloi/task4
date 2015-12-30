package dao;

/**
 * Created by DiZi on 30.12.2015.
 */
public class ExceptionDao extends RuntimeException {

    public ExceptionDao() {super();}

    public ExceptionDao(String message) {
        super(message);
    }

    public ExceptionDao(String message, Throwable cause) {
        super(message, cause);
    }

    public ExceptionDao(Throwable cause) {
        super(cause);
    }
}
