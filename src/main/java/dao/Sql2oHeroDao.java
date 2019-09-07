package dao;
import models.Hero;
import org.sql2o.*;
import java.util.List;


public class Sql2oHeroDao implements HeroDao {

    private final Sql2o sql2o;

    public Sql2oHeroDao(Sql2o sql2o) { this.sql2o = sql2o; }

    @Override
    public void add(Hero hero) {
        String sql = "INSERT INTO heroes (name,age,special_power,weakness,overall_rating) VALUES (:name,:age,:specialPower,:weakness,:overallRating)"; //raw sql
        try(Connection con = sql2o.open()){ //try to open a connection
            int id = (int) con.createQuery(sql, true) //make a new variable
                    .bind(hero) //map my argument onto the query so we can use information from it
                    .executeUpdate() //run it all
                    .getKey(); //int id is now the row number (row “key”) of db
            hero.setId(id);
            hero.setSquadId(0);
        } catch (Sql2oException ex) {
            System.out.println(ex); //oops we have an error!
        }
    }

    @Override
    public List<Hero> getAll() {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM heroes") //raw sql
                    .executeAndFetch(Hero.class); //fetch a list
        }
    }

    @Override
    public Hero findById(int id) {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM heroes WHERE id = :id")
                    .addParameter("id", id) //key/value pair, key must match above
                    .executeAndFetchFirst(Hero.class); //fetch an individual item
        }
    }



    @Override
    public void update(int id, String newName, int newAge, String newSpecialPower, String newWeakness, int newOverall){
        String sql = "UPDATE heroes SET name,age,special_power,weakness,overall_rating = :name,:age,:special_power,:weakness,:overall_rating WHERE id=:id;";
        try(Connection con = sql2o.open()){
            con.createQuery(sql)
                    .addParameter("id", id)
                    .addParameter("name", newName)
                    .addParameter("age", newAge)
                    .addParameter("specialPower", newSpecialPower)
                    .addParameter("weakness", newWeakness)
                    .addParameter("overallRating", newOverall)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

}
