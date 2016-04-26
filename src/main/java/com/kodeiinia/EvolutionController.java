package com.kodeiinia;

import com.kodeiinia.database.SpeciesDbService;
import com.kodeiinia.database.SpeciesMongoDao;
import com.kodeiinia.gamelogic.World;
import com.kodeiinia.database.WorldDbService;
import com.kodeiinia.database.WorldMongoDao;
import com.kodeiinia.gamelogic.Species;
import com.mongodb.DB;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
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

        get(new Route("/") {
            @Override
            public Object handle(Request request, Response response) {
                response.status(301);
                response.redirect("/world/1");
                return "";
            }
        });

        get(new Route("/nextturn") {
                    @Override
                    public Object handle(Request request, Response response) {
                        World world = worldDbService.readOne(1);
                        ArrayList<Species> speciesList = speciesDbService.readAll();

                        world.increaseTurn();
                        int turn = world.getTurn();

                        worldDbService.update(world.getId(),
                                turn);

                        for (Species species : speciesList) {
                            species.grow();
                            int newAmount = species.getNumberOfAnimals();
                            speciesDbService.update(species.getId(),
                                    species.getName(),
                                    newAmount);
                        }

                        response.status(301);
                        response.redirect("/");
                        return "";

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
                Species species = new Species(
                        id, name, numberOfAnimals, worldRef);
                speciesDbService.create(species);
                response.status(201);
                response.redirect("/");
                return "";
            }
        });

        get(new FreeMarkerRoute("/world/:worldId/species/:speciesId") {
            @Override
            public ModelAndView handle(Request request, Response response) {
                Integer worldId = Integer.parseInt(
                        request.params(":worldId"));
                Integer speciesId = Integer.parseInt(
                        request.params(":speciesId"));

                Map<String, Object> viewObjects = new HashMap();
                World world = worldDbService.readOne(worldId);
                Species species = speciesDbService.readOne(speciesId);

//                viewObjects.put("noWorld", "No such World!");
//                viewObjects.put("noSpecies", "No such Species!");
                viewObjects.put("species", species);
                viewObjects.put("world", world);
                viewObjects.put("sidebar", "sidebar_speciesCreator.ftl");
                viewObjects.put("primary", "species.ftl");
                return modelAndView(viewObjects, "layout.ftl");
            }

        });

        get(new FreeMarkerRoute("/world/:worldId") {
            @Override
            public ModelAndView handle(Request request, Response response) {
                Integer worldId = Integer.parseInt(
                        request.params(":worldId"));

                Map<String, Object> viewObjects = new HashMap();
                World world = worldDbService.readOne(worldId);
                ArrayList<Species> speciesList = speciesDbService.readAll();

                if (world != null) {
                    viewObjects.put("world", world);

                    if (speciesList.isEmpty()) {
                        String message = "The world \""
                                + world.getTitle()
                                + "\" is bereft of life!";
                        viewObjects.put("noSpecies", message);
                    } else {
                        Deque<Species> showSpecies = new ArrayDeque<>();

                        for (Species species : speciesList) {
                            showSpecies.addFirst(species);
                        }

                        viewObjects.put("speciesList", showSpecies);
                    }

                } else {
                    viewObjects.put("noWorlds",
                            "The universe is empty and sad!");
                }

                viewObjects.put("sidebar", "sidebar_speciesCreator.ftl");
                viewObjects.put("primary", "world.ftl");
                return modelAndView(viewObjects, "layout.ftl");
            }

        });

    }

}
