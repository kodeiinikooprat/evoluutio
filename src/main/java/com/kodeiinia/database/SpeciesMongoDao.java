package com.kodeiinia.database;

import com.kodeiinia.gamelogic.Species;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

/**
 *
 * @param <T>
 */

public class SpeciesMongoDao<T extends Species> implements SpeciesDbService<T> {

    private DBCollection collection;

    public SpeciesMongoDao() {

        try {
            // Connect to MongoDB using the default port on your local machine
            MongoClient mongoClient = new MongoClient("localhost");
            // Note that the sparkledb will not actually be created until we save a document
            DB db = mongoClient.getDB("evolutiondb");
            collection = db.getCollection("Species");

            System.out.println("Connecting to MongoDB@" + mongoClient.getAllAddress());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Boolean create(T entity) {

        BasicDBObject doc = new BasicDBObject("title", entity.getName()).
                append("id", entity.getId()).
                append("name", entity.getName()).
                append("number", entity.getNumberOfAnimals());

        collection.insert(doc);

        return true;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T readOne(int id) {

        BasicDBObject query = new BasicDBObject("id", id);

        DBCursor cursor = collection.find(query);

        try {
            if (cursor.hasNext()) {
                BasicDBObject doc = (BasicDBObject) cursor.next();
                Species entity = new Species(
                        doc.getInt("id"),
                        doc.getString("name"),
                        doc.getInt("number")
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
    public Boolean update(int id, String name, int number) {

        BasicDBObject query = new BasicDBObject("id", id);

        DBCursor cursor = collection.find(query);

        try {
            if (cursor.hasNext()) {
                BasicDBObject doc = (BasicDBObject) cursor.next();

                doc.put("name", name);
                doc.put("number", number);

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
                // Deleting works by telling the cursor to free the document currently being pointed at
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
