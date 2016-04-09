package com.kodeiinia.database;

/**
 *
 * @param <T>
 */
public interface UserDbService<T> {

    public Boolean create(T entity);

    public T readOne(int id);

    public Boolean update(int id, String username, String password);

    public Boolean delete(int id);
    
}
