package com.kodeiinia;

import static com.kodeiinia.Main.worldDbService;
import com.kodeiinia.gamelogic.World;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import static spark.Spark.get;
import spark.template.freemarker.FreeMarkerRoute;

public class SiteRoutes {

    public SiteRoutes() {
        setupEndpoints();
    }

    private void setupEndpoints() {
        get(new FreeMarkerRoute("/") {
            @Override
            public ModelAndView handle(Request request, Response response) {
                Map<String, Object> viewObjects = new HashMap();
                World world = worldDbService.readOne(1);

                if (world != null) {
                    viewObjects.put("world", world);
                } else {
                    viewObjects.put("noWorlds", "No worlds present in database!");
                }

                viewObjects.put("templateName", "world.ftl");
                return modelAndView(viewObjects, "layout.ftl");
            }
        });

        get(new FreeMarkerRoute("/world/create") {
            @Override
            public Object handle(Request request, Response response) {
                Map<String, Object> viewObjects = new HashMap();
                World world = new World(1, "Maa", 0);
                worldDbService.create(world);
                viewObjects.put("world", world);

                viewObjects.put("templateName", "world.ftl");

                return modelAndView(viewObjects, "layout.ftl");
            }
        });

        get(new FreeMarkerRoute("/nextturn") {
            @Override
            public ModelAndView handle(Request request, Response response) {
                Map<String, Object> viewObjects = new HashMap();
                World world = worldDbService.readOne(1);
                world.increaseTurn();
                int turn = world.getTurn();

//                System.out.println("WUORO O " + turn);
                worldDbService.update(world.getId(), turn);

                viewObjects.put("world", world);

                viewObjects.put("templateName", "world.ftl");

                return modelAndView(viewObjects, "layout.ftl");

            }
        });
    }

}
