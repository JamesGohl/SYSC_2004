/**
 * Implements a rollover counter
 *
 * @author James Gohl
 * @version March, 1 2025
 */
public class RollOverCounter extends Counter
{

    /**
     * Constructs a new RollOverCounter whose current count is
     * initialized to DEFAULT_MINIMUM, and which counts between
     * DEFAULT_MINIMUM and DEFAULT_MAXIMUM, inclusive.
     */
    public RollOverCounter()
    {
        super();
    }

    /**
     * Constructs a new RollOverCounter whose current count is
     * initialized to minCount, and which counts between
     * minCount and maxCount, inclusive.
     *
     * @param minCount the min count
     * @param maxCount the max count
     */
    public RollOverCounter(int minCount, int maxCount)
    {
        super(minCount, maxCount);
    }

    /**
     * Increment this counter by 1.
     */
    public void countUp()
    {
        // If we've reached the maximum count, invoking this
        // method rolls the counter over to its minimum value.
        if (super.isAtMaximum())
        {
            super.reset();
        } else
        {
            super.countUp();
        }
    }

    /**
     * Decrement the counter by 1. If we have reached the minimum count,
     * invoking this method rolls the counter over to its maximum value.
     */
    public void countDown()
    {
        if (isAtMinimum())
        {
            setToMaximum();
        } else
        {
            super.decrementCount();
        }
    }
}
