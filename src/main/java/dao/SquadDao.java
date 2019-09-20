package dao;
import models.Player;
import models.Squad;

import java.util.List;

public interface SquadDao {

    List<Player> getAllPlayersInSquad(int squadId);

    // LIST
    List<Squad> getAll();

    // CREATE
    void add(Squad squad);

    Squad findById(int id);


}
