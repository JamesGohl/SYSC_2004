/**
 * Java classes that test dice in Monopoly
 *
 * @author James Gohl
 * @version March 31, 2025
 */
package Test;

import Monopoly.*;
import org.junit.jupiter.api.*;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest
{

    /**
     * Runs once before all the tests
     */
    @BeforeAll
    public static void init()
    {
        System.out.println("before all for PlayerTest");

    }

    /**
     * Runs once after all the tests.
     */
    @AfterAll
    public static void close()
    {
        System.out.println("after all for PlayerTest");
    }

    /**
     * Sets up the test ficture.
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp()
    {
        System.out.println("before each for PlayerTest");

    }

    /**
     * Tears down the test ficture.
     * Called after every test case method.
     */
    @AfterEach
    public void teardown()
    {
        System.out.println("after each for PlayerTest");
    }

    /**
     * Test if the dice roll in the Player class works correctly
     */
    @Test
    public void testPlayer()
    {
        Board b = new Board();
        Dice d = new Dice();
        Player p = new Player("James", b, d);
        assertEquals("James", p.name());
        assertEquals(b, p.board());
        assertEquals(d, p.dice());
        assertEquals(b.startingSquare(), p.location());
        p.takeTurn();
        p.location().number();
        assertEquals(b.nextSquare(b.startingSquare(), (p.location().number() - 1)), p.location());

    }


}
