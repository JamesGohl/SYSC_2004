/**
 * Implements a limited counter
 *
 * @author James Gohl
 * @version February, 16 2025
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
            super.incrementCount();
        }
    }
}
