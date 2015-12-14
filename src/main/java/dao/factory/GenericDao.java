package dao.factory;

import connection.PooledConnection;
import entity.BaseEntity;

/**
 * Created by DiZi on 03.12.2015.
 */
public abstract class GenericDao<T extends BaseEntity>{
    public GenericDao(PooledConnection connection){
    }

    public GenericDao() {

    }

    public abstract T create(T object);
    public abstract T update(T object, int i);
    public abstract boolean delete(int i);
    public abstract T find(int i);
}
