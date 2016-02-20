
package com.kodeiinia;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import java.sql.Date;
import java.util.ArrayList;


public class WorldMongoDao<T extends World> implements WorldDbService<T> {

    private DBCollection collection;

    public WorldMongoDao() {

        try {
            // Connect to MongoDB using the default port on your local machine
            MongoClient mongoClient = new MongoClient("localhost");
            // Note that the sparkledb will not actually be created until we save a document
            DB db = mongoClient.getDB("evolutiondb");
            collection = db.getCollection("Worlds");

            System.out.println("Connecting to MongoDB@" + mongoClient.getAllAddress());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Boolean create(T entity) {
        // MongoDB is a document store which by default has no concept of a schema so its
        // entirely up to the developer to decide which attributes a document will use
        BasicDBObject doc = new BasicDBObject("title", entity.getTitle()).
                append("id", entity.getId());
//                append("content", entity.getContent()).
//                append("summary", entity.getSummary()).
//                append("deleted", false).
//                append("createdAt", new Date(new java.util.Date().getTime()));

        // As soon as we insert a doucment into our collection MongoDB will craete our sparkle database and
        // World collection within it.
        collection.insert(doc);
        return true;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T readOne(int id) {
        // MongoDB queries are not queries in the sense that you have a separate language such as SQL
        // With MongoDB you can think of it as Document pattern matching
        // Thus we construct a document with a specific id value and ask Mongo to search our World
        // collection for all documents which match
        BasicDBObject query = new BasicDBObject("id", id);

        // Cursors are the default representation of Mongo query result
        // Think of cursors as a pointer to a array of documents
        // It can traverse the array of documents and when requested can dereference and pull out the contents
        // But at any given time it only takes up enough memory needed to maintain the reference of the data type it points to
        // MongoDB was written in C++ so an analogy to the C language is probably how Cursors were implemented
        // A technical presentation by Dwight Merriman co-founder of 10gen the company that makes MongoDB
        // @see http://www.mongodb.com/presentations/mongodb-internals-tour-source-code
        // Watch that shit... best technical deep-dive of MongoDB ever!
        DBCursor cursor = collection.find(query);

        try {
            if (cursor.hasNext()) {
                BasicDBObject doc = (BasicDBObject) cursor.next();
                World entity = new World(
                        doc.getInt("id"),
                        doc.getString("title")
                //                        doc.getString("summary"),
                //                        doc.getString("content"),

                //                        doc.getDate("createdAt"),
                //                        doc.getBoolean("deleted")
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
    public Boolean update(int id, int turn) {
        // NOTE: MongoDB also allow us to do SQL style updates by specifying update conditions
        // within our query document. It requires a much deeper knowledge of MongoDB but for now
        // we can stick with the less performant(two operations versus one) find() and put() style of updating
        BasicDBObject query = new BasicDBObject("id", id);

        DBCursor cursor = collection.find(query);

        try {
            if (cursor.hasNext()) {
                BasicDBObject doc = (BasicDBObject) cursor.next();
                // BasicDBObject::put() allows us to update a document in-place
                doc.put("turn", turn);
//                doc.put("title", title);
//                doc.put("summary", summary);
//                doc.put("content", content);

                collection.save(doc);

                return true;
            } else {
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
