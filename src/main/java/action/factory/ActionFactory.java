package action.factory;

import dao.factory.Dao;
import dao.factory.DaoFactoryCreate;

/**
 * Created by DiZi on 03.12.2015.
 */
abstract class ActionFactory {
    private static ActionFactory instance;

    public static ActionFactory getInstance() {
        if (instance == null) {
            instance = new ActionCreate();
        }
        return instance;
    }

    public abstract Action getAction(Class clazz);
}