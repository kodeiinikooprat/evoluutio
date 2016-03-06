package com.kodeiinia;

import com.kodeiinia.gamelogic.World;
import com.kodeiinia.gamelogic.WorldMongoDao;
import com.kodeiinia.gamelogic.WorldDbService;
import com.kodeiinia.admin.AdminRoutes;
import com.mongodb.MongoClient;
//import java.util.HashMap;
//import java.util.Map;
//import spark.ModelAndView;
//import spark.Request;
//import spark.Response;
//import static spark.Spark.get;
//import spark.template.freemarker.FreeMarkerRoute;
import com.mongodb.DB;

public class Main {

    public static DB evoluutioDb = setDb();

    public static WorldDbService<World> worldDbService = new WorldMongoDao(evoluutioDb);

    public static void main(String[] args) {

//        evoluutioDb = setDb();
//        System.out.println(evoluutioDb.getCollectionNames());
        SiteRoutes baseRoutes = new SiteRoutes();
        AdminRoutes adminRoutes = new AdminRoutes();

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
