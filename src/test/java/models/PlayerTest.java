package models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    @BeforeEach
    public void setUp() throws Exception {
    }

    @AfterEach
    public void tearDown() throws Exception {
       // Player.clearAllPlayers(); // clear out all the player before each test
    }

    @Test
    public void NewPlayerObjectGetsCorrectlyCreated_true() throws Exception {
        Player hero = createTestPlayer();
        assertEquals(true, hero instanceof Player);
    }


    public Player createTestPlayer(){
        return new Player("Glass",23,"Invisibility","bananas", 90,1);
    }
}