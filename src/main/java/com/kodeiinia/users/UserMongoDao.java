package com.kodeiinia.users;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;


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

        BasicDBObject doc = new BasicDBObject("id", entity.getId()).
                append("username", entity.getUsername()).
                append("password", entity.getPassword());

        collection.insert(doc);
        return true;
    }

    @Override
    public T readOne(int id) {

        BasicDBObject query = new BasicDBObject("id", id);
        DBCursor cursor = collection.find(query);

        try {
            if (cursor.hasNext()) {
                BasicDBObject doc = (BasicDBObject) cursor.next();
                User entity = new User(
                        doc.getInt("id"),
                        doc.getString("username"),
                        doc.getString("password")
                );
                return (T) entity;
            } else {
                return null;
            }

        } finally {
            cursor.close();
        }

    }

    @Override
    public Boolean update(int id, String username, String password) {
        BasicDBObject query = new BasicDBObject("id", id);

        DBCursor cursor = collection.find(query);

        try {
            if (cursor.hasNext()) {
                BasicDBObject doc = (BasicDBObject) cursor.next();

                doc.put("username", username);
                doc.put("password", password);

                collection.save(doc);
                System.out.println("update toimi!");
                return true;
            } else {
                System.out.println("update kusi!");
                return false;
            }
        } finally {
            cursor.close();
        }
    }

    @Override
    public Boolean delete(int id) {

        BasicDBObject query = new BasicDBObject("id", id);
        DBCursor cursor = collection.find(query);

        try {

            if (cursor.hasNext()) {
                collection.remove(cursor.next());
                return true;
            } else {
                return false;
            }

        } finally {
            cursor.close();
        }
    }

}
