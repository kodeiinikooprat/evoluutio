package com.kodeiinia;

import com.kodeiinia.gamelogic.World;
import com.kodeiinia.database.WorldDbService;
import com.kodeiinia.database.WorldMongoDao;
import com.mongodb.DB;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import static spark.Spark.get;
import spark.template.freemarker.FreeMarkerRoute;

public class EvolutionController {

    private WorldDbService<World> worldDbService;

    public EvolutionController(DB Db) {
        worldDbService = new WorldMongoDao(Db);
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
