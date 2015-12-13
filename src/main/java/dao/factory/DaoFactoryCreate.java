package dao.factory;

/**
 * Created by DiZi on 03.12.2015.
 */
public class DaoFactoryCreate extends DaoFactory {

    @Override
    public Dao getDao(Class clazz) {
        Dao dao = null;
        try {
            dao = (Dao) clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return dao;
    }
}