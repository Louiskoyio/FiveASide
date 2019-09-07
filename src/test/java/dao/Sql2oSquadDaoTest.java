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
    private Sql2oHeroDao heroDao;
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

    @Test
    public void getAllHeroesInSquadsCorrectly() throws Exception {
        Squad squad = createTestSquad();
        squadDao.add(squad);
        int squadId = squad.getId();
        Hero newHero = new Hero("Batman",23,"Wealth","bats", 90,squadId);
        Hero otherHero = new Hero("Spiderman",23,"Spider Powers","bats", 85, squadId);
        Hero thirdHero = new Hero("Antman",23,"Size","bats", 88, squadId);
        heroDao.add(newHero);
        heroDao.add(otherHero); //we are not adding task 3 so we can test things precisely.
        assertEquals(2, squadDao.getAllHeroesInSquad(squadId));
        assertTrue(squadDao.getAllHeroesInSquad(squadId).contains(newHero));
        assertTrue(squadDao.getAllHeroesInSquad(squadId).contains(otherHero));
        assertFalse(squadDao.getAllHeroesInSquad(squadId).contains(thirdHero)); //things are accurate!
    }

}
