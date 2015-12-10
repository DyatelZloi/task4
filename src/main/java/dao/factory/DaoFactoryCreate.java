package dao.factory;

/**
 * Created by DiZi on 03.12.2015.
 */
public class DaoFactoryCreate extends DaoFactory {

    @Override
    public Dao getDao(Class clazz) {
            Class c = null;
            Dao dao = null;
            try {
                c = clazz.getClass();
                dao = (Dao) c.newInstance();
            } catch ( InstantiationException | IllegalAccessException e) {
                throw new RuntimeException("Unable to create/find DAO instance", e);
            }
            return dao;
    }
}