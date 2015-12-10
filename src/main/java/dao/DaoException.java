package dao;

import java.sql.SQLException;

/**
 * Created by DiZi on 25.11.2015.
 */
public class DaoException extends RuntimeException {
    public DaoException(Throwable cause) {
        super(cause);
    }

    public DaoException(String s, SQLException e) {
    }
}
