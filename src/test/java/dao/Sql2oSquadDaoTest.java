package dao;
import models.Player;
import models.Squad;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.sql2o.*;

public class Sql2oSquadDaoTest {

    private static Sql2oSquadDao squadDao;
    private static Sql2oPlayerDao playerDao;
    private static Connection conn;

    public Squad createTestSquad(){
        return new Squad("Avengers","Defeat Thanos","Kenyatta");
    }

    @BeforeClass //changed to @BeforeClass (run once before running any tests in this file)
    public static void setUp() throws Exception { //changed to static
        String connectionString = "jdbc:postgresql://localhost:5432/todolist_test"; // connect to postgres test database
        Sql2o sql2o = new Sql2o(connectionString, null, null); // changed user and pass to null
        squadDao = new Sql2oSquadDao(sql2o);
        playerDao = new Sql2oPlayerDao(sql2o);
        conn = sql2o.open(); // open connection once before this test file is run
    }
/*

    @AfterEach // run after every test
    public void tearDown() throws Exception { //I have changed
        System.out.println("clearing database");
        playerDao.clearAllPlayers(); // clear all tasks after every test
    }
*/

    @AfterClass // changed to @AfterClass (run once after all tests in this file completed)
    public static void shutDown() throws Exception { //changed to static and shutDown
        conn.close(); // close connection once after this entire test file is finished
        System.out.println("connection closed");
    }

    @Test
    public void addingCourseSetsId() throws Exception {
        Squad squad = createTestSquad();
        int originalTaskId = squad.getId();
        squadDao.add(squad);
        assertNotEquals(originalTaskId, squad.getId());
    }

    @Test
    public void getAllPlayersInSquadsCorrectly() throws Exception {
        Squad squad = createTestSquad();
        squadDao.add(squad);
        int squadId = squad.getId();
        Player newPlayer = new Player("Batman",23,"Wealth","bats", 90,squadId);
        Player otherPlayer = new Player("Spiderman",23,"Spider Powers","bats", 85, squadId);
        Player thirdPlayer = new Player("Antman",23,"Size","bats", 88, squadId);
        playerDao.add(newPlayer);
        playerDao.add(otherPlayer); //we are not adding task 3 so we can test things precisely.
        assertEquals(2, squadDao.getAllPlayersInSquad(squadId));
        assertTrue(squadDao.getAllPlayersInSquad(squadId).contains(newPlayer));
        assertTrue(squadDao.getAllPlayersInSquad(squadId).contains(otherPlayer));
        assertFalse(squadDao.getAllPlayersInSquad(squadId).contains(thirdPlayer)); //things are accurate!
    }

}
