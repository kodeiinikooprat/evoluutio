package com.kodeiinia;


import com.kodeiinia.database.AdminMongoDao;
import com.mongodb.DB;
import com.mongodb.DBApiLayer;
import com.mongodb.MongoClient;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class AdminMongoDaoTest {

    DB db;
    AdminMongoDao adminDao;

    @Before
    public void setUp() {
        try {
            MongoClient mongoClient = new MongoClient("localhost");
            db = mongoClient.getDB("tests-evolutiondb");
            System.out.println("Connecting to MongoDB@" + mongoClient.getAllAddress());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        adminDao = new AdminMongoDao(db);
        adminDao.createTestData();
        
    }

    @After
    public void tearDown() {
        db.dropDatabase();
    }

    @Test
    public void createTestDataOnClearDb() {
//        adminDao.createTestData();
        adminDao.clearDb();
        boolean testdataCreated = adminDao.createTestData();
        assertTrue(testdataCreated);
    }

    @Test
    public void createTestDataOnExistingDb() {
//        adminDao.createTestData();
        boolean testdataCreated = adminDao.createTestData();
        assertFalse(testdataCreated);
    }
    
    @Test
    public void clearDbTest(){
        adminDao.createTestData();
        adminDao.clearDb();
        int numberOfCollections = adminDao.getNumberOfCollections();
        assertEquals(0, numberOfCollections);
    }
    
    @Test
    public void getDbTest() {
        DB gottenDb = adminDao.getDb();
        System.out.println(gottenDb.toString());
        assertEquals(DBApiLayer.class, gottenDb.getClass());
//        assert shouldBeDb instanceof DB;
    }
    
    
}
