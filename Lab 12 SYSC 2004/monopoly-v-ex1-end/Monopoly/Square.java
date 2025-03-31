package Monopoly;

/**
 * A square on the Monopoly game board.
 *
 * @author D.L. Bailey, SCE, Carleton University
 * @author James Gohl
 * @version march 30, 2025
 */
public class Square
{
    /**
     * The square's type.
     */
    private int type;

    /**
     * Types of squares.
     */
    /* An empty square is a placeholder for a square on the real Monopoly
     * game board that has not yet been implemented in a class.
     */
    public final static int EMPTY = 0;

    /* The Go square. Players collect their salary when they reach this
     * square.
     */
    public final static int GO = 1;

    /* The Income Tax square. Players must pay income tax if they land
     * on this square.
     */
    public final static int INCOME_TAX = 2;

    /* A square with a street name. Players can purchase these squares.
     */
    public final static int LOT = 3;

    /**
     * The square's name.
     */
    private String name;

    /**
     * The square's number.
     */
    private int number;

    /**
     * The maximum amount of tax players must pay when they
     * land on an income tax square.
     */
    private Money maximumTax;

    /**
     * The amount players receives when they land on the GO square.
     */
    private Money salary;

    /**
     * The purchase price of this square (only valid for squares of type LOT).
     */
    private Money price;

    /**
     * Constructs a new square with the specified type, name and
     * number.
     *
     * @param type   The square's type (see the constant defintions
     *               for a list of valid types). The value of this
     *               parameter is not checked to ensure that it is
     *               valid.
     * @param name   The square's name.
     * @param number The square's number.
     */
    public Square(int type, String name, int number)
    {
        this.type = type;
        this.name = name;
        this.number = number;

        if (type == GO)
        {
            salary = new Money(200, 0);
        } else if (type == INCOME_TAX)
        {
            maximumTax = new Money(200, 0);
        }
    }

    /**
     * Returns the description of this square.
     *
     * @return a string containing the square's name and other
     * information about the square.
     */
    public String description()
    {
        String s;
        s = name;

        if (type == EMPTY)
        {
            s = s + " (not implemented)";
        } else if (type == GO)
        {
            s = s + ", collect " + salary.toString();
        } else if (type == INCOME_TAX)
        {
            s = s + ", maximum tax payable is " + maximumTax.toString();
        } else if (type == LOT)
        {
            s = s + ", price is " + price.toString();
        }
        return s;
    }

    /**
     * Returns this square's number.
     *
     * @return the square's number.
     */
    public int number()
    {
        return number;
    }

    /**
     * Sets this square's purchase price to the specified amount.
     * Should only be invoked on Square objects with type LOT.
     *
     * @param amount The purchase price of this square.
     */
    public void setPrice(Money amount)
    {
        if (type == LOT)
            price = amount;
    }

    /**
     * getter for the type field
     *
     * @return returns the type of the square
     */
    public int type()
    {
        return type;
    }

    /**
     * getter for the name field
     *
     * @return returns the name of the square
     */
    public String name()
    {
        return name;
    }

    /**
     * getter for the max tax field
     *
     * @return returns the max tax of the square
     */
    public Money maximumTax()
    {
        return maximumTax;
    }

    /**
     * getter for the  salary field
     *
     * @return returns the salary of the square
     */
    public Money salary()
    {
        return salary;
    }

    /**
     * getter for the price field
     *
     * @return returns the prie of the square
     */
    public Money price()
    {
        return price;
    }


}
