package dao.factory;

/**
 * Created by DiZi on 03.12.2015.
 */
public class DaoFactoryCreate extends DaoFactory {

    @Override
    public GenericDao getDao(Class clazz) {
        GenericDao genericDao = null;
        try {
            genericDao = (GenericDao) clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return genericDao;
    }
}