package dao.factory;



/**
 * Created by DiZi on 01.12.2015.
 */
public abstract class DaoFactory {

    //TODO выпилить обычные дао, доработать factory

    private static DaoFactory instance;

    public static DaoFactory getInstance() {
        if (instance == null) {
            instance =  new DaoFactoryCreate();
        }
        return instance;
    }

    public abstract GenericDao getDao(Class clazz);
}