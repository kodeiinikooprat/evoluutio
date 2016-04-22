package com.kodeiinia.database;

import java.util.ArrayList;

public interface SpeciesDbService<T> {

    public Boolean create(T entity);

    public T readOne(int id);

    public Boolean update(int id, String name, int number);

    public Boolean delete(int id);
    
    public ArrayList<T> readAll();
    
    public int getNextId();
}
