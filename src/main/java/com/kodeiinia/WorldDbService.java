
package com.kodeiinia;

import java.util.ArrayList;

public interface WorldDbService<T> {

    public Boolean create(T entity);

    public T readOne(int id);

    public Boolean update(int id, int turn);

    public Boolean delete(int id);
}
