package models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HeroTest {
    @BeforeEach
    public void setUp() throws Exception {
    }

    @AfterEach
    public void tearDown() throws Exception {
        Hero.clearAllHeroes(); // clear out all the heroes before each test
    }

    @Test
    public void NewHeroObjectGetsCorrectlyCreated_true() throws Exception {
        Hero hero = createTestHero();
        assertEquals(true, hero instanceof Hero);
    }


    public Hero createTestHero(){
        return new Hero("Glass",23,"Invisibility","bananas", overallRating);
    }
}