package com.kodeiinia.database;

import com.kodeiinia.gamelogic.Species;
import com.kodeiinia.gamelogic.World;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import java.util.Set;

public class AdminMongoDao {

    private DB db;
    private Set<String> allCollectionNames;
    private int numberOfCollections;
    private WorldDbService<World> worldDbService;
    private SpeciesDbService<Species> speciesDbService;

//    private DBCollection worldCollection;
    public AdminMongoDao(DB db) {
        try {
            this.db = db;
            this.allCollectionNames = this.db.getCollectionNames();
            this.numberOfCollections = allCollectionNames.size();
            this.worldDbService = new WorldMongoDao<>(this.db);
            this.speciesDbService = new SpeciesMongoDao<>(this.db);
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

    public boolean collectionExists(String collectionName) {
        if (this.db.collectionExists(collectionName)) {
            return true;
        } else {
            return false;
        }
    }

    public DB getDb() {
        return this.db;
    }

    public boolean clearDb() {
        try {
            this.db.dropDatabase();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

    }

    public boolean createTestData() {
        if (this.collectionExists("Worlds")) {
            System.out.println("Collection \"Worlds\" already exists!");
            return false;
        } else {
            World world = new World(1, "Maa", 0);
            this.worldDbService.create(world);
//            Species species = new Species(1, "Hamsteri", 2, world.getId());
//            this.speciesDbService.create(species);
            return true;
        }
    }

}
