package com.kodeiinia.database;

import com.kodeiinia.gamelogic.Species;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import static java.lang.Math.toIntExact;
import java.util.ArrayList;

/**
 *
 * @param <T>
 */
public class SpeciesMongoDao<T extends Species> implements SpeciesDbService<T> {

    private DBCollection collection;

    public SpeciesMongoDao(DB db) {

        try {
            collection = db.getCollection("Species");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Boolean create(T entity) {

        BasicDBObject doc = new BasicDBObject(
                "id", entity.getId())
                .append("name", entity.getName())
                .append("number", entity.getNumberOfAnimals())
                .append("worldref", entity.getWorldRef());

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
                        doc.getInt("number"),
                        doc.getInt("worldref"));

                return (T) entity;
            } else {
                return null;
            }
        } finally {
            cursor.close();
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public ArrayList<T> readAll() {
        DBCursor cursor = collection.find();

        ArrayList<Species> results = (ArrayList<Species>) new ArrayList<T>();

        try {
            while (cursor.hasNext()) {
                BasicDBObject doc = (BasicDBObject) cursor.next();

                Species entity = new Species(
                        doc.getInt("id"),
                        doc.getString("name"),
                        doc.getInt("number"),
                        doc.getInt("worldref"));

                results.add(entity);
            }

            return (ArrayList<T>) results;
        } finally {
            cursor.close();
        }
    }

    @Override
    public int getNextId() {
        int nextId = toIntExact(collection.getCount()) + 1; // getCount palauttaa long
        return nextId;
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
