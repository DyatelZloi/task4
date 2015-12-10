package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * Created by DiZi on 29.11.2015.
 */
public class ConnectionPool {
    //TO DO внести некоторые корректировки
    public static final ConnectionPool pool = new ConnectionPool();
    private List<PooledConnection> connectionList;
    private String driverClassName;
    private String url;
    private String userName;
    private String password;
    private Integer connectionNumber;
    private Semaphore semaphore;

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setConnectionNumber(Integer connectionNumber) {
        this.connectionNumber = connectionNumber;
    }

    private ConnectionPool(){}

    public void initConnections (){
        try {
            Class.forName(driverClassName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        semaphore = new Semaphore(connectionNumber);
        connectionList = new LinkedList<PooledConnection>();
        for (int i = 0; i < connectionNumber; i++){
            try {
                Connection connection = DriverManager.getConnection(url, userName, password);
                PooledConnection pooledConnection = new PooledConnection(connection);
                connectionList.add(pooledConnection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static ConnectionPool getInstance(){
        return  pool;
    }

    public PooledConnection getConnection(){
        try {
            semaphore.acquire();
            PooledConnection pooledConnection = connectionList.remove(0);
            return pooledConnection;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void CloseConnection (){
        int realeseNumber = connectionNumber - semaphore.availablePermits();
        semaphore.release(realeseNumber);

    }
}
