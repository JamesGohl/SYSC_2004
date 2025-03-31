/**
 * Java classes that test die in Monopoly
 *
 * @author James Gohl
 * @version March 31, 2025
 */
package Test;

import Monopoly.*;
import org.junit.jupiter.api.*;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class DieTest
{

    /**
     * Runs once before all the tests
     */
    @BeforeAll
    public static void init()
    {
        System.out.println("before all for DieTest");

    }

    /**
     * Runs once after all the tests.
     */
    @AfterAll
    public static void close()
    {
        System.out.println("after all for DieTest");
    }

    /**
     * Sets up the test ficture.
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp()
    {
        System.out.println("before each for DieTest");

    }

    /**
     * Tears down the test ficture.
     * Called after every test case method.
     */
    @AfterEach
    public void teardown()
    {
        System.out.println("after each for DieTest");
    }

    /**
     * Test if the die roll in the correct range
     */
    @Test
    public void testDieRange()
    {
        System.out.println("Testing die range");

        Die d = new Die(new Random());
        for (int i = 0; i < 1000; i++)
        {
            int num = d.roll();
            assertTrue(0 < num && 7 > num);
        }

    }


}
