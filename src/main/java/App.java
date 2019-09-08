
import dao.Sql2oHeroDao;
import dao.Sql2oSquadDao;
import models.Hero;
import dao.Sql2oHeroDao;
import models.Squad;
import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");
        String connectionString = "jdbc:h2:~/todolist.db;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        Sql2oHeroDao heroDao= new Sql2oHeroDao(sql2o);
        Sql2oSquadDao squadDao=new Sql2oSquadDao(sql2o);



        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Hero> heroes = heroDao.getAll();
            model.put("heroes", heroes);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());



        get("/squads", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Squad> squads = squadDao.getAll();
            model.put("squads", squads);
            return new ModelAndView(model, "squads.hbs");
        }, new HandlebarsTemplateEngine());

        get("/heroes", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Hero> heroes = heroDao.getAll();
            model.put("heroes", heroes);
            return new ModelAndView(model, "heroes.hbs");
        }, new HandlebarsTemplateEngine());

        get("/squads/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfSquadToFind = Integer.parseInt(req.params("id")); //pull id - must match route segment
            Squad foundSquad = squadDao.findById(idOfSquadToFind);
            List<Hero> heroesInSquad = squadDao.getAllHeroesInSquad(idOfSquadToFind);
            List<Hero> heroes = heroDao.getAll();
            model.put("heroesInSquad", heroesInSquad);
            model.put("heroes", heroes);
            model.put("squad", foundSquad); //add it to model for template to display
            return new ModelAndView(model , "squad-details.hbs"); //individual task page.
        }, new HandlebarsTemplateEngine());

        get("/squads/:id/add/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int squadId = Integer.parseInt(req.params("id"));
            int heroId = Integer.parseInt(req.params("id"));
            heroDao.assignSquad(heroId,squadId);
            List<Squad> squads = squadDao.getAll();
            model.put("squads", squads);
            return new ModelAndView(model , "squads.hbs"); //individual task page.
        }, new HandlebarsTemplateEngine());


        get("/heroes/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "hero-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/heroes", (req, res) -> { //URL to make new task on POST route
            Map<String, Object> model = new HashMap<>();
            String name = req.queryParams("name");
            int age =  Integer.parseInt(req.queryParams("age"));
            String strength = req.queryParams("strength");
            String weaknesses = req.queryParams("weaknesses");
            int overallRating = Integer.parseInt(req.queryParams("overallRating"));
            int squadId = Integer.parseInt(req.queryParams("squadId"));
            Hero newHero = new Hero(name,age,strength,weaknesses, overallRating, squadId);
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());

        get("/heroes/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfTaskToFind = Integer.parseInt(req.params("id")); //pull id - must match route segment
            Hero foundHero = heroDao.findById(idOfTaskToFind); //use it to find task
            model.put("hero", foundHero); //add it to model for template to display
            return new ModelAndView(model, "hero-detail.hbs"); //individual task page.
        }, new HandlebarsTemplateEngine());
    }
}


