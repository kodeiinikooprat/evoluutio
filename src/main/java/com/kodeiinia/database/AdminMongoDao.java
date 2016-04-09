package com.kodeiinia.database;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import java.util.Set;

public class AdminMongoDao {

    private DB db;
    private Set<String> allCollectionNames;
    private int numberOfCollections;

    public AdminMongoDao(DB db) {
        try {
            this.db = db;
            this.allCollectionNames = this.db.getCollectionNames();
            this.numberOfCollections = allCollectionNames.size();
            System.out.println("DB name is: " + this.db.getName());
            System.out.println("Mongo instance is: " + this.db.getMongo());
            System.out.println("Collections: " + this.allCollectionNames);
            System.out.println("Number of collections: " + this.numberOfCollections);
            System.out.println("Collection Worlds exists?: " + db.collectionExists("Worlds"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public AdminMongoDao() {

        try {
            // Connect to MongoDB using the default port on your local machine
            MongoClient mongoClient = new MongoClient("localhost");
            // Note that the sparkledb will not actually be created until we save a document
            this.db = mongoClient.getDB("evolutiondb");
            this.allCollectionNames = db.getCollectionNames();
            this.numberOfCollections = allCollectionNames.size();
            System.out.println("Connecting to MongoDB@" + mongoClient.getAllAddress());
            System.out.println("Collections: " + this.allCollectionNames);
            System.out.println("Number of collections: " + this.numberOfCollections);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Set<String> getAllCollectionNames() {
        return allCollectionNames;
    }

    public int getNumberOfCollections() {
        return numberOfCollections;
    }

    public DB getDb() {
        return db;
    }

    public boolean clearDb() {
        try {
            for (String colName : this.allCollectionNames) {
                DBCollection collection = this.db.getCollection(colName);
                String collectionName = collection.getName();
                System.out.println("collection name to be dropped: "
                        + collectionName);
                if (!collectionName.equals("system.indexes")) {
                    collection.drop();
                    System.out.println("Collection " + colName + " dropped!");
                } else {
                    System.out.println("We don't want to drop system.indexes!");
                }
            }

            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

    }

}
