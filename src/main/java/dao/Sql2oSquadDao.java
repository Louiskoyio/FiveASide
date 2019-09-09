package dao;

import models.Hero;
import models.Squad;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oSquadDao implements SquadDao{

    private final Sql2o sql2o;

    public Sql2oSquadDao(Sql2o sql2o) { this.sql2o = sql2o; }

    @Override
    public void add(Squad squad) {
        String sql = "INSERT INTO squads (name,objective) VALUES (:name,:objective)"; //raw sql
        try(Connection con = sql2o.open()){ //try to open a connection
            int id = (int) con.createQuery(sql, true) //make a new variable
                    .bind(squad)
                    .throwOnMappingFailure(false)
                    .executeUpdate()
                    .getKey();
            squad.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex); //oops we have an error!
        }
    }

    @Override
    public List<Hero> getAllHeroesInSquad(int squadId) {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM heroes WHERE squad_id = :squadId")
                    .addParameter("squadId", squadId)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(Hero.class);
        }
    }

    @Override
    public List<Squad> getAll() {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM squads") //raw sql
                    .throwOnMappingFailure(false)
                    .executeAndFetch(Squad.class); //fetch a list
        }
    }

    @Override
    public Squad findById(int id) {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM squads WHERE id = :id")
                    .addParameter("id", id) //key/value pair, key must match above
                    .throwOnMappingFailure(false)
                    .executeAndFetchFirst(Squad.class); //fetch an individual item
        }
    }

}
