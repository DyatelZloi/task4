package dao.factory;

import dao.factory.Dao;

/**
 * Created by DiZi on 03.12.2015.
 */
public class DaoFactoryCreate extends DaoFactory {

    @Override
    public Dao getDao(Class clazz) {
            Class c = null;
            Dao dao = null;
        try {
            dao = (Dao) clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return dao;
    }
}