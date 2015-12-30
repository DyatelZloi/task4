package dao;


/**
 * Created by DiZi on 01.12.2015.
 */
public abstract class FactoryDao {

    private static FactoryDao instance;

    /**
     *
     * @return
     */
    public static FactoryDao getInstance() {
        if (instance == null) {
            instance =  new FactoryCreateDao();
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