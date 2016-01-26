package connection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * Created by Malkov Nikifor on 29.11.2015.
 */
public class ConnectionPool {

    private static final Logger log = LoggerFactory.getLogger(ConnectionPool.class);
    private static final ConnectionPool pool = new ConnectionPool();
    private List<PooledConnection> connectionList;
    private String driverClassName;
    private String url;
    private String userName;
    private String password;
    private Integer connectionNumber;
    private Semaphore semaphore;

    /**
     * Set driver class name
     * @param driverClassName
     */
    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    /**
     *
     * @param url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     *
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *
     * @param connectionNumber
     */
    public void setConnectionNumber(Integer connectionNumber) {
        this.connectionNumber = connectionNumber;
    }

    /**
     *
     */
    private ConnectionPool(){}

    /**
     * Initialization connections
     */
    public void initConnections (){
        try {
            Class.forName(driverClassName);
        } catch (ClassNotFoundException e) {
            log.error("Impossible to find driver");
            throw new ConnectionPoolException(e);
        }
        semaphore = new Semaphore(connectionNumber);
        connectionList = new LinkedList<PooledConnection>();
        for (int i = 0; i < connectionNumber; i++){
            try {
                Connection connection = DriverManager.getConnection(url, userName, password);
                PooledConnection pooledConnection = new PooledConnection(connection);
                connectionList.add(pooledConnection);
            } catch (SQLException e) {
                log.error("SQL error");
                throw new ConnectionPoolException(e);
            }
        }
    }

    /**
     *
     * @return
     */
    public static ConnectionPool getInstance(){
        return  pool;
    }

    /**
     *
     * @return
     */
    public PooledConnection getConnection(){
        try {
            semaphore.acquire();
            return connectionList.remove(0);
        } catch (InterruptedException e) {
            log.error("The problems with connections");
            throw new ConnectionPoolException(e);
        }
    }

    /**
     *
     */
    public void closeConnection (){
        int realeseNumber = connectionNumber - semaphore.availablePermits();
        semaphore.release(realeseNumber);
    }
}
