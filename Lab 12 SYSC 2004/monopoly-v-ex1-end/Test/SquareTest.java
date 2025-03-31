/**
 * Java classes that test square in Monopoly
 *
 * @author James Gohl
 * @version March 30, 2025
 */
package Test;

import Monopoly.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class SquareTest
{

    /**
     * Runs once before all the tests
     */
    @BeforeAll
    public static void init()
    {
        System.out.println("before all for SquareTest");

    }

    /**
     * Runs once after all the tests.
     */
    @AfterAll
    public static void close()
    {
        System.out.println("after all for SquareTest");
    }

    /**
     * Sets up the test ficture.
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp()
    {
        System.out.println("before each for SquareTest");

    }

    /**
     * Tears down the test ficture.
     * Called after every test case method.
     */
    @AfterEach
    public void teardown()
    {
        System.out.println("after each for SquareTest");
    }

    /**
     * Test if the square works properly
     */
    @Test
    public void testSquare()
    {
        System.out.println("Testing square");

        Square go = new Square(Square.GO, "Go", 1);

        Square lot = new Square(Square.LOT, "Duckworth Street", 2);
        lot.setPrice(new Money(60, 0));

        Square cc = new Square(Square.EMPTY, "Community Chest", 3);

        Square tax = new Square(Square.INCOME_TAX, "Income Tax", 4);
        tax.setPrice(new Money(220, 0));

        //check all squares types are correct
        assertEquals(Square.GO, go.type());
        assertEquals(Square.LOT, lot.type());
        assertEquals(Square.EMPTY, cc.type());
        assertEquals(Square.INCOME_TAX, tax.type());

        //check all squares names are correct
        assertEquals("Go", go.name());
        assertEquals("Duckworth Street", lot.name());
        assertEquals("Community Chest", cc.name());
        assertEquals("Income Tax", tax.name());

        //check tax max tax are correct
        assertTrue(new Money(200, 0).isEqualTo(tax.maximumTax()));

        //check go salary correct
        assertTrue(new Money(200, 0).isEqualTo(go.salary()));

        //check lot salary correct
        assertTrue(new Money(60, 0).isEqualTo(lot.price()));


        //test the description of all squares
        assertEquals("Go, collect $200.00", go.description());
        assertEquals("Community Chest (not implemented)", cc.description());
        assertEquals("Duckworth Street, price is $60.00", lot.description());
        assertEquals("Income Tax, maximum tax payable is $200.00", tax.description());


    }


}
