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

public class DiceTest
{

    /**
     * Runs once before all the tests
     */
    @BeforeAll
    public static void init()
    {
        System.out.println("before all for DiceTest");

    }

    /**
     * Runs once after all the tests.
     */
    @AfterAll
    public static void close()
    {
        System.out.println("after all for DiceTest");
    }

    /**
     * Sets up the test ficture.
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp()
    {
        System.out.println("before each for DiceTest");

    }

    /**
     * Tears down the test ficture.
     * Called after every test case method.
     */
    @AfterEach
    public void teardown()
    {
        System.out.println("after each for DiceTest");
    }

    /**
     * Test if the dice roll in the corredct range
     */
    @Test
    public void testDieRange()
    {
        System.out.println("Testing dice range");

        Dice d = new Dice();
        for (int i = 0; i < 1000; i++)
        {
            int num = d.roll();
            assertTrue(1 < num && 13 > num);
        }

    }


}
