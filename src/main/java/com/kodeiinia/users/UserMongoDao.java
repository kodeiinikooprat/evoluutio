/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kodeiinia.users;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

/**
 *
 * @author ville
 */
public class UserMongoDao<T extends User> implements UserDbService<T> {

    private DBCollection collection;

    public UserMongoDao() {

        try {
            // Connect to MongoDB using the default port on your local machine
            MongoClient mongoClient = new MongoClient("localhost");
            // Note that the sparkledb will not actually be created until we save a document
            DB db = mongoClient.getDB("evolutiondb");
            collection = db.getCollection("Users");

            System.out.println("Connecting to MongoDB@" + mongoClient.getAllAddress());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        
    }

    @Override
    public Boolean create(T entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public T readOne(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean update(int id, String username, String password) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    

}
