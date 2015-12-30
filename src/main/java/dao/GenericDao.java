package dao;

import connection.PooledConnection;
import entity.BaseEntity;

import java.util.List;

/**
 * Created by DiZi on 03.12.2015.
 */
public abstract class GenericDao<T extends BaseEntity>{

    //TODO возможно не стоит делать все методы абстрактными

    /**
     *
     *
     * @param connection
     */
    public GenericDao(PooledConnection connection){
    }

    /**
     *
     */
    public GenericDao() {
    }

    /**
     * Create object
     *
     * @param object
     * @return
     */
    public abstract T create(T object);

    /**
     * Update object by id
     *
     * @param object
     * @param i
     * @return
     */
    public abstract T update(T object, int i);

    /**
     * Delete object by id
     *
     * @param i
     * @return
     */
    public abstract boolean delete(int i);

    /**
     * Find object by id
     *
     * @param i
     * @return
     */
    public abstract T find(int i);

    /**
     * Find object by string
     *
     * @param string
     * @return
     */
    public abstract T findBy(String string);

    /**
     * Find all objects
     *
     * @return
     */
    public abstract List<T> findAll();
}
