package dao.factory;


import java.sql.SQLException;

/**
 * Created by DiZi on 01.12.2015.
 */
public abstract class DaoFactory {

    //TODO выпилить обычные дао, доработать factory

    /**
     *
     */
    private static DaoFactory instance;

    /**
     *
     * @return
     */
    public static DaoFactory getInstance() {
        if (instance == null) {
            instance =  new DaoFactoryCreate();
        }
        return instance;
    }

    /**
     *
     * @param clazz
     * @return
     */
    public abstract GenericDao getDao(Class clazz);

    /**
     *
     */
    public abstract void beginTransaction();

    /**
     *
     */
    public abstract void commit();

    /**
     *
     */
    public abstract void rollback();

    /**
     *
     */
    public abstract void close();
}