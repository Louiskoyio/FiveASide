package dao;
import models.Player;
import java.util.List;

public interface PlayerDao {

    // LIST
    List<Player> getAll();

    // CREATE
    void add(Player player);

    // READ
    Player findById(int id);

    // UPDATE
    void assignSquad(int id, int squadId);

    void dropFromSquad(int id);


}
