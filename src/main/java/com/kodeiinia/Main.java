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

        get(new FreeMarkerRoute("/") {
            @Override
            public ModelAndView handle(Request request, Response response) {
                Map<String, Object> viewObjects = new HashMap();
                World world = worldDbService.readOne(1);
                viewObjects.put("world", world);

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

//        post(new Route("/world/create") {
//            @Override
//            public Object handle(Request request, Response response) {
//                String title = request.queryParams("article-title");
//                String summary = request.queryParams("article-summary");
//                String content = request.queryParams("article-content");
//
//                Article article = new Article(title, summary, content, articleDbService.readAll().size());
//                articleDbService.create(article);
//
//                response.status(201);
//                response.redirect("/");
//                return "";
//            }
//        });
        get(new FreeMarkerRoute("/nextturn") {
            @Override
            public ModelAndView handle(Request request, Response response) {
                Map<String, Object> viewObjects = new HashMap();
//                world.increaseTurn();
                World world = worldDbService.readOne(1);
                world.increaseTurn();
                int turn = world.getTurn();

                System.out.println("WUORO O " + turn);
                worldDbService.update(world.getId(), turn);
                
                viewObjects.put("world", world);

                viewObjects.put("templateName", "world.ftl");

                return modelAndView(viewObjects, "layout.ftl");

            }
        });

        AdminRoutes adminRoutes = new AdminRoutes();
        
    }

}
