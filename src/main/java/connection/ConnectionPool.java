package connection;

import java.sql.Connection;

/**
 * Created by DiZi on 29.11.2015.
 */
public class ConnectionPool {

    /**
     *
     */
    public static ConnectionPool instance;

    /**
     *
     */
    public static final Connection connection = null;

    /**
     *
     */
    private String driverName;

    /**
     *
     */
    private String url;

    /**
     *
     */
    private String user;

    /**
     *
     */
    private String password;

    /**
     *
     * @param driverName
     * @param url
     * @param user
     * @param password
     */
    private ConnectionPool(String driverName, String url, String user, String password){
        this.driverName = driverName;
        this.url = url;
        this.user = user;
        this.password = password;
    }
}
