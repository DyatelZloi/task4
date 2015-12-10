package action.factory;

import dao.factory.Dao;

/**
 * Created by DiZi on 06.12.2015.
 */
public class ActionCreate extends ActionFactory {

    @Override
    public Action getAction(Class clazz) {
        Class c = null;
        Action action = null;
        try {
            c = clazz.getClass();
            action = (Action) c.newInstance();
        } catch ( InstantiationException | IllegalAccessException e) {
            throw new RuntimeException("Unable to create/find DAO instance", e);
        }
        return action;
    }
}
