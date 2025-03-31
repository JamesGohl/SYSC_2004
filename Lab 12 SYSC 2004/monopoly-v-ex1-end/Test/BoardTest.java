/**
 * Java classes that test board in Monopoly
 *
 * @author James Gohl
 * @version March 30, 2025
 */
package Test;

import Monopoly.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest
{
    Board board;

    /**
     * Runs once before all the tests
     */
    @BeforeAll
    public static void init()
    {
        System.out.println("before all for BoardTest");

    }

    /**
     * Runs once after all the tests.
     */
    @AfterAll
    public static void close()
    {
        System.out.println("after all for BoardTest");
    }

    /**
     * Sets up the test ficture.
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp()
    {
        System.out.println("before each for BoardTest");
        board = new Board();

    }

    /**
     * Tears down the test ficture.
     * Called after every test case method.
     */
    @AfterEach
    public void teardown()
    {
        System.out.println("after each for BoardTest");
    }

    /**
     * Test if the board works properly starts square is 1
     */
    @Test
    public void testStart1()
    {
        assertEquals(1, board.startingSquare().number());
    }

    /**
     * Test if the squares are numbered 1-40
     */
    @Test
    public void testOne40()
    {
        Square square = board.startingSquare();
        for (int i = 1; i <= 40; i++)
        {

            assertEquals(i, square.number());
            square = board.nextSquare(square, 1);
        }

    }

    /**
     * Test if the next square works
     */
    @Test
    public void testNextSquare()
    {
        Square square = board.startingSquare();
        int squareNum = 1;
        for (int i = 1; i <= 12; i++)
        {

            assertEquals(squareNum, square.number());
            square = board.nextSquare(square, i);
            squareNum += i;
            if (squareNum > 40)
            {
                squareNum -= 40;
            }
        }



    }
    /**
     * Test if the next square wraps around
     */
    @Test
    public void testWrap()
    {
        Square start = board.startingSquare();
        assertEquals(6, board.nextSquare(start, 45).number());
        Square square = new Square(Square.LOT, "test", 40);
        square = board.nextSquare(square, 5);
        assertEquals(5, square.number());
    }




}
