package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by Malkov Nikifor on 25.01.2016.
 */
public class MyConnectionPool {

    public static final MyConnectionPool pool = new MyConnectionPool();

    private BlockingQueue<PooledConnection> connectionList;
    private String driverClassName;
    private String url;
    private String userName;
    private String password;
    private Integer connectionNumber;

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

    MyConnectionPool (){}

    public void initConnections (){
        try {
            Class.forName(driverClassName);
        } catch (ClassNotFoundException e) {
            throw new ConnectionPoolException("Проблемы с драйвером", e);
        }
        connectionList = new ArrayBlockingQueue(connectionNumber);
        for (int i = 0; i < connectionNumber; i++){
            try {
                Connection connection = DriverManager.getConnection(url, userName, password);
                PooledConnection pooledConnection = new PooledConnection(connection);
                connectionList.put(pooledConnection);
            } catch (SQLException | InterruptedException e) {
                throw new ConnectionPoolException("Проблемы с конекшеном", e);
            }
        }
    }

    /**
     *
     * @return
     */
    public static MyConnectionPool getInstance(){
        return  pool;
    }

    /**
     *
     * @return
     */
    public PooledConnection getConnection(){
        synchronized (pool) {
            try {
                return connectionList.take();
            } catch (InterruptedException e) {
                throw new ConnectionPoolException("Нет конекшенов", e);
            }
        }
    }

    /**
     *
     */
    public void closeConnection (PooledConnection pooledConnection){
        try {
            connectionList.put(pooledConnection);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
