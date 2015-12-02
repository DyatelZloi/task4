package connection;

import java.sql.Connection;

/**
 * Created by DiZi on 29.11.2015.
 */
public class ConnectionPool {
    //TO DO Сделать конекшн пулл
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

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
