package dao;
import models.Hero;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.sql2o.*;

public class Sql2oHeroDaoTest {
    private Sql2oHeroDao heroDao;
    private Connection conn; //must be sql2o class conn

    public Hero createTestHero(){
        return new Hero("Batman",23,"Wealth","bats", 1,98);
    }
    @BeforeEach
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        heroDao = new Sql2oHeroDao(sql2o); //ignore me for now
        conn = sql2o.open(); //keep connection open through entire test so it does not get erased
    }

    @AfterEach
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void addingCourseSetsId() throws Exception {
        Hero hero = createTestHero();
        int originalTaskId = hero.getId();
        heroDao.add(hero);
        assertNotEquals(originalTaskId, hero.getId());
    }

    @Test
    public void squadIdIsReturnedCorrectly() throws Exception {
        Hero hero = createTestHero();
        int originalSquadId = hero.getSquadId();
        heroDao.add(hero);
        assertEquals(originalSquadId, heroDao.findById(hero.getId()).getSquadId());
    }

    @Test
    public void existingHeroesCanBeFoundById() throws Exception {
        Hero hero = createTestHero();
        heroDao.add(hero);
        Hero foundHero = heroDao.findById(hero.getId());
        assertEquals(hero, foundHero);
    }



    @Test
    public void updateChangesTaskContent() throws Exception {
        int initialSquad = 0;
        Hero hero = createTestHero();
        heroDao.add(hero);
        heroDao.assignSquad(hero.getId(),1);
        Hero updatedHeroSquad = heroDao.findById(hero.getId());
        assertNotEquals(hero.getSquadId(), updatedHeroSquad.getSquadId());
    }


    @Test
    public void assignsSquadSuccessfully() {
        heroDao.assignSquad(2,2);
        Hero editedHero = heroDao.findById(1);
        int expectedHeroSquadId = 2;
        assertEquals(editedHero.getSquadId(), expectedHeroSquadId);

    }
}
