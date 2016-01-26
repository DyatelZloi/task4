package dao;

import entity.BaseEntity;


import java.util.List;

/**
 * Created by Malkov Nikifor on 03.12.2015.
 */
public abstract class GenericDao<T extends BaseEntity>{

    public GenericDao() {
    }

    public abstract T create(T object);

    public abstract T update(T object, int i);

    public abstract boolean delete(int i);

    public abstract T find(int i);

    public abstract T findBy(String string);

    public abstract List<T> findAll();

    public abstract List<T> findAllBy(int id);

    public abstract T update2(T t, int id);

    public abstract List<T> findAllBy(long id);
}
