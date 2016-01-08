package dao;

import connection.PooledConnection;
import entity.BaseEntity;
import entity.User;

import java.util.List;

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

    public abstract T findBy(String string);

    public abstract List<T> findAll();

    public abstract List<T> findAllBy(int string);

    public abstract User update2(User user, int id);
}
