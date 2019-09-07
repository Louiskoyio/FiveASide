package dao;
import models.Hero;
import models.Squad;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.sql2o.*;

public class Sql2oSquadDaoTest {

    private Sql2oSquadDao squadDao;
    private Connection conn;

    public Squad createTestSquad(){
        return new Squad("Avengers","Defeat Thanos");
    }
    @BeforeEach
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        squadDao = new Sql2oSquadDao(sql2o); //ignore me for now
        conn = sql2o.open(); //keep connection open through entire test so it does not get erased
    }

    @AfterEach
    public void tearDown() throws Exception {
        conn.close();
    }


    @Test
    public void addingCourseSetsId() throws Exception {
        Squad squad = createTestSquad();
        int originalTaskId = squad.getId();
        squadDao.add(squad);
        assertNotEquals(originalTaskId, squad.getId());
    }

}
