package dao;
import models.Player;
import org.sql2o.*;
import java.util.List;


public class Sql2oPlayerDao implements PlayerDao {

    private final Sql2o sql2o;

    public Sql2oPlayerDao(Sql2o sql2o) { this.sql2o = sql2o; }

    @Override
    public void add(Player player) {
        String sql = "INSERT INTO players (name,age,special_power,weakness,squad_id,overall_rating,position,attack,defence,chemistry,passing,physical) VALUES (:name,:age,:special_power,:weakness,:squad_id,:overall_rating,:position,:attack,:defence,:chemistry,:passing,:physical)"; //raw sql
        System.out.println(sql);
        try(Connection con = sql2o.open()){ //try to open a connection
            int id = (int) con.createQuery(sql, true) //make a new variable
                    .bind(player)
                    .executeUpdate() //run it all
                    .getKey(); //int id is now the row number (row “key”) of db
            player.setId(id);

        } catch (Sql2oException ex) {
            System.out.println(ex); //oops we have an error!
        }
    }

    @Override
    public List<Player> getAll() {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM players") //raw sql
                    .throwOnMappingFailure(false).executeAndFetch(Player.class); //fetch a list
        }
    }

    @Override
    public Player findById(int id) {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM players WHERE id = :id")
                    .addParameter("id", id) //key/value pair, key must match above
                    .throwOnMappingFailure(false)
                    .executeAndFetchFirst(Player.class); //fetch an individual item
        }
    }



    @Override
    public void assignSquad(int id, int squadId){
        String sql = "UPDATE players SET (squad_id) = (:squadId) WHERE id=:id;";
        try(Connection con = sql2o.open()){
            con.createQuery(sql)
                    .addParameter("id", id)
                    .addParameter("squadId", squadId)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println("SQUAD NOT ASSIGNED:"+ex);
        }
    }

    @Override
    public void dropFromSquad(int id){
        String sql = "UPDATE players SET (squad_id) = 0 WHERE id=:id;";
        try(Connection con = sql2o.open()){
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println("SQUAD NOT ASSIGNED:"+ex);
        }
    }
}
