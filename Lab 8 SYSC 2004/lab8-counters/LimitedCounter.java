/**
 * Implements a limited counter
 *
 * @author James Gohl
 * @version March, 1 2025
 */
public class LimitedCounter extends Counter
{

    /**
     * Constructs a new RollOverCounter whose current count is
     * initialized to DEFAULT_MINIMUM, and which counts between
     * DEFAULT_MINIMUM and DEFAULT_MAXIMUM, inclusive.
     */
    public LimitedCounter()
    {
        super();
    }

    /**
     * Constructs a new RollOverCounter whose current count is
     * initialized to minCount, and which counts between
     * minCount and maxCount, inclusive.
     * @param minCount the min count
     * @param maxCount the maximum count
     */
    public LimitedCounter(int minCount, int maxCount)
    {
        super(minCount, maxCount);
    }

    /**
     * Increment this counter by 1.
     */
    public void countUp()
    {
        // If we've reached the maximum count, invoking this
        // method does nothing.
        if (!super.isAtMaximum())
        {
            super.countUp();
        }
    }

    /**
     * Decrement the counter by 1. If we have reached the minimum count,
     * invoking this method does nothing.
     */
    public void countDown()
    {
        if (!isAtMinimum())
        {
            super.decrementCount();
        }
    }
}
