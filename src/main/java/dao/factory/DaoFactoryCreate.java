package dao.factory;

import connection.ConnectionPool;
import connection.PooledConnection;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by DiZi on 03.12.2015.
 */
public class DaoFactoryCreate extends DaoFactory {

    PooledConnection connection;

    public static final String DRIVER_CLASS_NAME = "org.h2.Driver";

    public DaoFactoryCreate (){
        ConnectionPool instance = ConnectionPool.getInstance();
        instance = ConnectionPool.getInstance();
        instance.setDriverClassName(DRIVER_CLASS_NAME);
        instance.setUserName("GOD");
        instance.setPassword("GOD");
        instance.setUrl("jdbc:h2:~/course");
        instance.setConnectionNumber(4);
        instance.initConnections();
        connection = instance.getConnection();
    }

    @Override
    public GenericDao getDao(Class clazz) {
        GenericDao genericDao = null;
        try {
                genericDao = (GenericDao) clazz.getDeclaredConstructor(PooledConnection.class).newInstance(connection);
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return genericDao;
    }
}