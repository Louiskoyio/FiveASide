package dao;
import models.Hero;
import models.Squad;

import java.util.List;

public interface SquadDao {

    List<Hero> getAllHeroesInSquad(int squadId);

    // LIST
    List<Squad> getAll();

    // CREATE
    void add(Squad squad);

    Squad findById(int id);


}
