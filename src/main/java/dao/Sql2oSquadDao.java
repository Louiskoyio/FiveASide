package dao;

import models.Hero;
import models.Squad;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oSquadDao implements SquadDao{

    private final Sql2o sql2o;

    public Sql2oHeroDao(Sql2o sql2o) { this.sql2o = sql2o; }
    @Override
    public void add(Squad squad) {
        String sql = "INSERT INTO squads (name,objective) VALUES (:name,:objective)"; //raw sql
        try(Connection con = sql2o.open()){ //try to open a connection
            int id = (int) con.createQuery(sql, true) //make a new variable
                    .bind(squad) //map my argument onto the query so we can use information from it
                    .executeUpdate() //run it all
                    .getKey(); //int id is now the row number (row “key”) of db
            squad.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex); //oops we have an error!
        }
    }

    @Override
    public List<Squad> getAll() {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM squads") //raw sql
                    .executeAndFetch(Squad.class); //fetch a list
        }
    }
}
