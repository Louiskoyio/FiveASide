
import dao.Sql2oPlayerDao;
import dao.Sql2oSquadDao;
import models.Player;
import dao.Sql2oPlayerDao;
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
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567;
    }

    public static void main(String[] args) {
        staticFileLocation("/public");
        port(getHerokuAssignedPort());
        String connectionString = "jdbc:postgresql://localhost:5432/five_a_side"; //connect to todolist, not todolist_test!
        Sql2o sql2o = new Sql2o(connectionString, "louis", "23Armin23");
/*        String connectionString = "jdbc:postgresql://ec2-174-129-27-158.compute-1.amazonaws.com:5432/d2qg5l5e2coola";
        Sql2o sql2o = new Sql2o(connectionString, "epopnniolaxqnq", "ac6a6b613a72352157f516422a043de4abbe264d294405cceb17e982e6007edb");*/
        Sql2oPlayerDao playerDao= new Sql2oPlayerDao(sql2o);
        Sql2oSquadDao squadDao=new Sql2oSquadDao(sql2o);



        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Player> players = playerDao.getAll();
            model.put("players", players);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());



        get("/squads", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Squad> squads = squadDao.getAll();
            model.put("squads", squads);
            return new ModelAndView(model, "squads.hbs");
        }, new HandlebarsTemplateEngine());

        get("/players", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Player> players = playerDao.getAll();
            model.put("players", players);
            return new ModelAndView(model, "players.hbs");
        }, new HandlebarsTemplateEngine());

        get("/squads/:squad_id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfSquadToFind = Integer.parseInt(req.params("squad_id")); //pull id - must match route segment
            Squad foundSquad = squadDao.findById(idOfSquadToFind);
            List<Player> playersInSquad = squadDao.getAllPlayersInSquad(idOfSquadToFind);
            List<Player> players = playerDao.getAll();
            model.put("playersInSquad", playersInSquad);
            model.put("players", players);
            model.put("squad", foundSquad); //add it to model for template to display
            return new ModelAndView(model , "squad-details.hbs"); //individual task page.
        }, new HandlebarsTemplateEngine());

        get("/squads/:squad_id/add/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int squadId = Integer.parseInt(req.params("squad_id"));
            int playerId = Integer.parseInt(req.params("id"));
            playerDao.assignSquad(playerId,squadId);
            List<Squad> squads = squadDao.getAll();
            model.put("squads", squads);
            return new ModelAndView(model , "squads.hbs"); //individual task page.
        }, new HandlebarsTemplateEngine());

        get("/squads/:squad_id/drop/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int heroId = Integer.parseInt(req.params("id"));
            playerDao.dropFromSquad(heroId);
            List<Squad> squads = squadDao.getAll();
            model.put("squads", squads);
            return new ModelAndView(model , "squads.hbs"); //individual task page.
        }, new HandlebarsTemplateEngine());


        get("/players/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "player-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/players", (req, res) -> { //URL to make new task on POST route
            Map<String, Object> model = new HashMap<>();
            String name = req.queryParams("name");
            int age =  Integer.parseInt(req.queryParams("age"));
            String special_power = req.queryParams("special_power");
            String weaknesses = req.queryParams("weaknesses");
            int overall_rating = Integer.parseInt(req.queryParams("overall_rating"));
            int squad_id = Integer.parseInt(req.queryParams("squad_id"));
            String position = req.queryParams("position");
            int attack = Integer.parseInt(req.queryParams("attack"));
            int defence = Integer.parseInt(req.queryParams("defence"));
            int chemistry = Integer.parseInt(req.queryParams("chemistry"));
            int passing = Integer.parseInt(req.queryParams("passing"));
            int physical = Integer.parseInt(req.queryParams("physical"));
            Player newPlayer = new Player(name,age,special_power,weaknesses, overall_rating, squad_id,position,attack,defence,chemistry,passing,physical);
            playerDao.add(newPlayer);
            res.redirect("/players");
            return null;
        }, new HandlebarsTemplateEngine());

        get("/players/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfTaskToFind = Integer.parseInt(req.params("id")); //pull id - must match route segment
            Player foundPlayer = playerDao.findById(idOfTaskToFind); //use it to find task
            model.put("player", foundPlayer); //add it to model for template to display
            return new ModelAndView(model, "players-detail.hbs"); //individual task page.
        }, new HandlebarsTemplateEngine());
    }
}


