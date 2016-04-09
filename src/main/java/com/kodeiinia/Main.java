package com.kodeiinia;

import com.kodeiinia.admin.AdminController;
import com.mongodb.MongoClient;
import com.mongodb.DB;

public class Main {

    public static DB evoluutioDb = setDb();


    public static void main(String[] args) {

        EvolutionController baseRoutes = new EvolutionController(evoluutioDb);
        AdminController adminRoutes = new AdminController(evoluutioDb);

    }

    static DB setDb() {

        try {
            // Connect to MongoDB using the default port on your local machine
            MongoClient mongoClient = new MongoClient("localhost");
            DB db = mongoClient.getDB("evolutiondb");
            System.out.println("Connecting to MongoDB@" + mongoClient.getAllAddress());
            return db;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

}
