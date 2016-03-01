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

    String basepath = "/admin";
    

    public AdminRoutes() {
        setupEndpoints();
    }

    private void setupEndpoints() {
        get(new FreeMarkerRoute(basepath + "/resetdb") {
            
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
