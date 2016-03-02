package com.kodeiinia.admin;

import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
//import spark.Route;
import static spark.Spark.get;
import spark.template.freemarker.FreeMarkerRoute;

public class AdminRoutes {

    private String[] basePath = new String[2];
    private String[] resetDbPath = new String[2];
    
    public static AdminMongo adminMongo = new AdminMongo();

    public AdminRoutes() {
        basePath[0] = "/admin";
        basePath[1] = "Admin Home";
        resetDbPath[0] = basePath[0] + "/resetdb";
        resetDbPath[1] = "Reset Database";
        setupEndpoints();
    }

    private String getLink(String[] path) {
        String link = path[0];
        String name = path[1];
        return "<a href='" + link + "'>" + name + "</a>";
    }
    
    private void setupEndpoints() {

        get(new FreeMarkerRoute(basePath[0]) {

            @Override
            public ModelAndView handle(Request request, Response response) {
                
                Map<String, Object> viewObjects = new HashMap<>();
                viewObjects.put("resetButtonLink", getLink(resetDbPath));
                viewObjects.put("templateName", "adminBase.ftl");
                return modelAndView(viewObjects, "layout.ftl");
            }

        });
        
        get(new FreeMarkerRoute(resetDbPath[0]) {

            @Override
            public ModelAndView handle(Request request, Response response) {

                Map<String, Object> viewObjects = new HashMap();
                String testmessage1 = "Tän pitäs toimia.";
                viewObjects.put("message", testmessage1);
                viewObjects.put("templateName", "adminMessage.ftl");
                return modelAndView(viewObjects, "layout.ftl");
            }
        });

    }

}
