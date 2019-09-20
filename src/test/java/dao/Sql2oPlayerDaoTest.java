package dao;
import models.Player;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.sql2o.*;

public class Sql2oPlayerDaoTest {
    private Sql2oPlayerDao playerDao;
    private Connection conn; //must be sql2o class conn

    public Player createTestPlayer(){
        return new Player("Batman",23,"Wealth","bats", 1,98);
    }
    @BeforeEach
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        playerDao = new Sql2oPlayerDao(sql2o); //ignore me for now
        conn = sql2o.open(); //keep connection open through entire test so it does not get erased
    }

    @AfterEach
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void addingCourseSetsId() throws Exception {
        Player hero = createTestPlayer();
        int originalTaskId = hero.getId();
        playerDao.add(hero);
        assertNotEquals(originalTaskId, hero.getId());
    }

    @Test
    public void squadIdIsReturnedCorrectly() throws Exception {
        Player hero = createTestPlayer();
        int originalSquadId = hero.getSquadId();
        playerDao.add(hero);
        assertEquals(originalSquadId, playerDao.findById(hero.getId()).getSquadId());
    }

    @Test
    public void existingPlayersCanBeFoundById() throws Exception {
        Player hero = createTestPlayer();
        playerDao.add(hero);
        Player foundPlayer = playerDao.findById(hero.getId());
        assertEquals(hero, foundPlayer);
    }



    @Test
    public void updateChangesTaskContent() throws Exception {
        int initialSquad = 0;
        Player hero = createTestPlayer();
        playerDao.add(hero);
        playerDao.assignSquad(hero.getId(),1);
        Player updatedPlayerSquad = playerDao.findById(hero.getId());
        assertNotEquals(hero.getSquadId(), updatedPlayerSquad.getSquadId());
    }


    @Test
    public void assignsSquadSuccessfully() {
        playerDao.assignSquad(2,2);
        Player editedPlayer = playerDao.findById(1);
        int expectedPlayerSquadId = 2;
        assertEquals(editedPlayer.getSquadId(), expectedPlayerSquadId);

    }
}
