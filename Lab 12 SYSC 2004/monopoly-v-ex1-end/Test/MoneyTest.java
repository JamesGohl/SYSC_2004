/**
 * Java classes that test money in Monopoly
 *
 * @author James Gohl
 * @version March 30, 2025
 */
package Test;

import Monopoly.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class MoneyTest
{
    Money money1, money2, money3;
    /**
     * Runs once before all the tests
     */
    @BeforeAll
    public static void init()
    {
        System.out.println("before all for MoneyTest");

    }

    /**
     * Runs once after all the tests.
     */
    @AfterAll
    public static void close()
    {
        System.out.println("after all for MoneyTest");
    }

    /**
     * Sets up the test ficture.
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp()
    {
        System.out.println("before each for MoneyTest");
        money1 = new Money(100);
        money2 = new Money(100);
        money3 = new Money(50);
    }

    /**
     * Tears down the test ficture.
     * Called after every test case method.
     */
    @AfterEach
    public void teardown()
    {
        System.out.println("after each for MoneyTest");
    }

    /**
     * Test if the money are equal to each other
     */
    @Test
    public void testIsEqualTo()
    {
        System.out.println("Testing isEqualTo");

        assertTrue((money1.isEqualTo(money2)));
        assertFalse((money1.isEqualTo(money3)));
        assertFalse((money3.isEqualTo(money2)));
    }

    /**
     * test the plus() method in money
     */
    @Test
    public void testPlus()
    {
        System.out.println("Testing plus()");
        //makes money 1 not equals to money
        money1 = money1.plus(money2);
        assertFalse((money1.isEqualTo(money2)));

        //subtracts what was added to money 1 so now equal to money2
        money1 = money1.minus(money2);
        assertTrue((money1.isEqualTo(money2)));

        //makes money 3 equal to 1 and 2 by doubling its
        money3 = money3.plus(money3);
        assertTrue(money3.isEqualTo(money2));
        assertTrue(money3.isEqualTo(money1));
    }

    /**
     * test the minus() method in money
     */
    @Test
    public void testMinus()
    {
        System.out.println("Testing minus()");
        //makes money 1 not equals to money
        money1 = money1.minus(money2);
        assertFalse((money1.isEqualTo(money2)));
        assertTrue(money1.isEqualTo(new Money(0)));


        //makes money 2 equal to 3 by subtracting 3
        money2 = money2.minus(money3);
        assertTrue(money3.isEqualTo(money2));
        assertFalse(money3.isEqualTo(money1));
    }
}

