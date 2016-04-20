package com.kodeiinia.admin;

import com.kodeiinia.database.AdminMongoDao;
import com.mongodb.DB;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
//import spark.Route;
import static spark.Spark.get;
import spark.template.freemarker.FreeMarkerRoute;
//import static com.kodeiinia.Main.evoluutioDb;

public class AdminController {

//    private DB evoluutioDb;
    private static AdminMongoDao adminMongo;

    private String[] basePath = new String[2];
    private String[] resetDbPath = new String[2];
    private String[] createTestDataPath = new String[2];

    public AdminController(DB Db) {
//        evoluutioDb = Db;
        adminMongo = new AdminMongoDao(Db);

        basePath[0] = "/admin";
        basePath[1] = "Admin Home";
        resetDbPath[0] = basePath[0] + "/resetdb";
        resetDbPath[1] = "Reset database";
        createTestDataPath[0] = basePath[0] + "/createtestdata";
        createTestDataPath[1] = "Create testdata";
        setupEndpoints();
    }

    private String getLink(String[] path) {
        String link = path[0];
//        String name = path[1];
        return link;
    }

    private String getName(String[] path) {
        String name = path[1];
        return name;
    }

    private void setupEndpoints() {

        get(new FreeMarkerRoute(basePath[0]) {

            @Override
            public ModelAndView handle(Request request, Response response) {

                System.out.println(adminMongo.getDb().getName());
                System.out.println(adminMongo.getAllCollectionNames());

                Map<String, Object> viewObjects = new HashMap<>();
                viewObjects.put("resetButtonLink", getLink(resetDbPath));
                viewObjects.put("resetButtonText", getName(resetDbPath));
                viewObjects.put("testdataButtonLink", getLink(createTestDataPath));
                viewObjects.put("testdataButtonText", getName(createTestDataPath));
                viewObjects.put("templateName", "adminBase.ftl");
                viewObjects.put("sidebar", "sidebar.ftl");
                return modelAndView(viewObjects, "layout.ftl");
            }

        });

        get(new FreeMarkerRoute(resetDbPath[0]) {

            @Override
            public ModelAndView handle(Request request, Response response) {

                adminMongo.clearDb();

                Map<String, Object> viewObjects = new HashMap();
                String message = "Database has been reset! (possibly)";
                viewObjects.put("message", message);
                viewObjects.put("templateName", "adminMessage.ftl");
                viewObjects.put("sidebar", "sidebar.ftl");
                return modelAndView(viewObjects, "layout.ftl");
            }
        });

        get(new FreeMarkerRoute(createTestDataPath[0]) {

            @Override
            public ModelAndView handle(Request request, Response response) {

                boolean success = adminMongo.createTestData();
                Map<String, Object> viewObjects = new HashMap();

                String message = "Failed to create testdata";
                if (success) {
                    message = "Testdata created.";
                }

                viewObjects.put("message", message);
                viewObjects.put("templateName", "adminMessage.ftl");
                viewObjects.put("sidebar", "sidebar.ftl");
                return modelAndView(viewObjects, "layout.ftl");
            }

        });

    }

}
