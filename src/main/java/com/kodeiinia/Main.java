/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kodeiinia;

import static com.kodeiinia.HelloSpark.articleDbService;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import static spark.Spark.get;
import spark.template.freemarker.FreeMarkerRoute;

/**
 *
 * @author Sony
 */
public class Main {

    public static void main(String[] args) {

        get(new FreeMarkerRoute("/") {
            @Override
            public ModelAndView handle(Request request, Response response) {
                Map<String, Object> viewObjects = new HashMap();
                World world = new World(1, "Maa");
                
                /* Refactor */
//                ArrayList<Article> articles = articleDbService.readAll();
//
//                if (articles.isEmpty()) {
//                    viewObjects.put("hasNoArticles", "Welcome, please click \"Write Article\" to begin.");
//                } else {
//                    Deque<Article> showArticles = new ArrayDeque();
//
//                    for (Article article : articles) {
//                        if (article.readable()) {
//                            showArticles.addFirst(article);
//                        }
//                    }

                    viewObjects.put("world", world);
//                }

                viewObjects.put("templateName", "world.ftl");
                

                return modelAndView(viewObjects, "layout.ftl");
            }
        });
    }

}
