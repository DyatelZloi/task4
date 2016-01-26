package dao;

import connection.ConnectionPool;
import connection.MyConnectionPool;
import connection.PooledConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

/**
 * Created by Malkov Nikifor on 03.12.2015.
 */
public class FactoryCreateDao extends FactoryDao {

    private static final Logger log = LoggerFactory.getLogger(FactoryCreateDao.class);
    private PooledConnection connection;
    private ConnectionPool instance;
    private static final String DRIVER_CLASS_NAME = "org.h2.Driver";

    /**
     *
     */
    //todo  вынести в проперти
    public FactoryCreateDao(){
        instance = ConnectionPool.getInstance();
        instance.setDriverClassName(DRIVER_CLASS_NAME);
        instance.setUserName("GOD");
        instance.setPassword("GOD");
        instance.setUrl("jdbc:h2:~/course;AUTO_SERVER=TRUE");
        instance.setConnectionNumber(4);
        instance.initConnections();
        connection = instance.getConnection();
    }

    /**
     *
     * @param clazz
     * @return
     */
    @Override
    public GenericDao getDao(Class clazz) {
        GenericDao genericDao = null;
        try {
                genericDao = (GenericDao) clazz.getDeclaredConstructor(PooledConnection.class).newInstance(connection);
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            log.error("Can not create DAO");
            throw new ExceptionDao(e);
        }
        return genericDao;
    }

    /**
     *
     */
    @Override
    public void beginTransaction() {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            log.error("Error executing query");
            throw new ExceptionDao(e);
        }
    }

    /**
     *
     */
    @Override
    public void commit() {
        try {
            connection.commit();
        } catch (SQLException e) {
            log.error("Error executing query");
            throw new ExceptionDao(e);
        }
    }

    /**
     *
     */
    @Override
    public void rollback() {
        try {
            connection.rollback();
        } catch (SQLException e) {
            log.error("Error executing query");
            throw new ExceptionDao(e);
        }
    }

    /**
     *
     */
    @Override
    public void close() {
        instance.closeConnection();
    }
}