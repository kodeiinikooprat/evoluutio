package com.kodeiinia.admin;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import java.util.Set;

public class AdminMongo {

    private DB db;
    private DBCollection collection;
    private Set<String> allCollectionNames;
    private int numberOfCollections;

    public AdminMongo() {

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

    public boolean clearDb() {
        try {
            for (String colName : this.allCollectionNames) {
                this.collection = this.db.getCollection(colName);
                this.collection.drop();
                System.out.println("Collection " + colName + " dropped!");
            }

            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

    }

}
