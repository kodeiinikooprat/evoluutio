package com.kodeiinia;

import com.kodeiinia.gamelogic.World;
import com.kodeiinia.gamelogic.WorldMongoDao;
import com.kodeiinia.gamelogic.WorldDbService;
import com.kodeiinia.admin.AdminRoutes;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import static spark.Spark.get;
import spark.template.freemarker.FreeMarkerRoute;


public class Main {

    public static WorldDbService<World> worldDbService = new WorldMongoDao();

    public static void main(String[] args) {
      
        SiteRoutes baseRoutes = new SiteRoutes();
        AdminRoutes adminRoutes = new AdminRoutes();
        
    }

}
