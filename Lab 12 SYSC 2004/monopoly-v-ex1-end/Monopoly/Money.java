package Monopoly;

/**
 * An amount of money in Canadian currency; i.e., dollars and cents.
 *
 * @author Lynn Marshall, Department of Systems and Computer Engineering
 * @version 1.0
 */
public class Money
{
    /**
     * The total amount of money, in cents
     */
    private int totalCents;

    /**
     * Constructs a Money object whose value is equal to the specified
     * quantity of cents, which can be positive or negative, and < -99 or > 99.
     *
     * @param cents A quantity of cents.
     * @see Money#Money(int, int)
     */
    public Money(int cents)
    {
        totalCents = cents;
    }

    /**
     * Constructs a Money object whose value is equal to the specified
     * quantity of dollars and cents, both of which can be positive or
     * negative. Quantities of cents < -99 or > 99 will result in the
     * dollar value being adjusted appropriately.
     *
     * @param dollars A quantity of dollars.
     * @param cents   A quantity of cents.
     * @see Money#Money(int)
     */
    public Money(int dollars, int cents)
    {
        totalCents = dollars * 100 + cents;
    }

    /**
     * dollars returns the dollars part of this Money object. For example, if this
     * object represents $9.37, 9 is returned; and if this object represents
     * -$7.14, -7 is returned.
     *
     * @return the dollars part of this Money object.
     */
    private int dollars()
    {
        return totalCents / 100;
    }

    /**
     * cents returns the cents part of this Money object. For example, if this
     * object represents $9.37, 37 is returned; and if this object represents
     * -$7.14, -14 is returned.
     *
     * @return the cents part of this Money object, between -99 and 99, inclusive.
     */
    private int cents()
    {
        return totalCents % 100;
    }

    /**
     * plus returns the sum of this Money object and the specified amount of money.
     *
     * @param anAmount the Money object that is to be added to this object.
     * @return a Money object equal to the sum of this Money object and anAmount.
     */
    public Money plus(Money anAmount)
    {
        return new Money(totalCents + anAmount.totalCents);
    }

    /**
     * minus returns the difference of this Money object and the specified amount of money.
     *
     * @param anAmount the Money object that is to be subtracted from this object.
     * @return a Money object equal to the result of subtracting anAmount from this
     * Money object.
     */
    public Money minus(Money anAmount)
    {
        return new Money(totalCents - anAmount.totalCents);
    }

    /**
     * isEqualTo returns true if this and anAmount represent the same amount of money,
     * and false otherwise.
     *
     * @param anAmount the Money object to compare this to.
     * @return true if this and anAmount represent the same amount of money, false otherwise.
     */
    public boolean isEqualTo(Money anAmount)
    {
        return this.totalCents == anAmount.totalCents;
    }

    /**
     * to String method
     *
     * @return the string reperesentation of the money
     */
    public String toString()
    {
        if (cents() > 9)
        {
            return "$" + dollars() + "." + cents();
        } else
        {
            return "$" + dollars() + ".0" + cents();
        }

    }
}

