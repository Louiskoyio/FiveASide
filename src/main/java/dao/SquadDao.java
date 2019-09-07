package dao;
import models.Squad;

import java.util.List;

public interface SquadDao {

    // LIST
    List<Squad> getAll();

    // CREATE
    void add(Squad squad);

    // READ
    Squad findById(int id);
}
