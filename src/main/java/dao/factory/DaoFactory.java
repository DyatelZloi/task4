package dao.factory;

/**
 * Created by DiZi on 01.12.2015.
 */
abstract class DaoFactory {

    private static DaoFactory instance;

    public static DaoFactory getInstance() {
        if (instance == null) {
            instance = new DaoFactoryCreate();
        }
        return instance;
    }

    public abstract Dao  getDao(Class clazz);
}