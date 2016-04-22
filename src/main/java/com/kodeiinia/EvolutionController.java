package com.kodeiinia;

import com.kodeiinia.database.SpeciesDbService;
import com.kodeiinia.database.SpeciesMongoDao;
import com.kodeiinia.gamelogic.World;
import com.kodeiinia.database.WorldDbService;
import com.kodeiinia.database.WorldMongoDao;
import com.kodeiinia.gamelogic.Species;
import com.mongodb.DB;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;
import static spark.Spark.get;
import static spark.Spark.post;
import spark.template.freemarker.FreeMarkerRoute;

public class EvolutionController {

    private WorldDbService<World> worldDbService;
    private SpeciesDbService<Species> speciesDbService;

    public EvolutionController(DB Db) {
        worldDbService = new WorldMongoDao(Db);
        speciesDbService = new SpeciesMongoDao(Db);
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

                Species species = speciesDbService.readOne(1);
                if (species != null) {
                    viewObjects.put("species", species);
                } else {
                    viewObjects.put("noSpecies", "No species present in database!");
                }

                viewObjects.put("sidebar", "sidebar_speciesCreator.ftl");
                viewObjects.put("primary", "world.ftl");
                return modelAndView(viewObjects, "layout.ftl");
            }
        });

        get(new FreeMarkerRoute("/nextturn") {
            @Override
            public ModelAndView handle(Request request, Response response) {
                Map<String, Object> viewObjects = new HashMap();
                World world = worldDbService.readOne(1);
                Species species = speciesDbService.readOne(1);
                world.increaseTurn();
                species.grow();
                int turn = world.getTurn();
                int newAmount = species.getNumberOfAnimals();

                worldDbService.update(world.getId(),
                        turn);

                speciesDbService.update(species.getId(),
                        species.getName(),
                        newAmount);

                viewObjects.put("world", world);
                viewObjects.put("species", species);

                viewObjects.put("sidebar", "sidebar_speciesCreator.ftl");
                viewObjects.put("primary", "world.ftl");

                return modelAndView(viewObjects, "layout.ftl");

            }
        });

        post(new Route("/species/create") {
            @Override
            public Object handle(Request request, Response response) {
                int id = speciesDbService.getNextId();
                System.out.println("species id: " + id);
                String name = request.queryParams("species-name");
                int numberOfAnimals = 2;
                int worldRef = 1;
                Species species = new Species(id, name, numberOfAnimals, worldRef);
                speciesDbService.create(species);
                response.status(201);
                response.redirect("/");
                return "";
            }
        });
    }

}
