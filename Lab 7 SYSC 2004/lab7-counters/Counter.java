/**
 * Super class for the counter defines its max and min
 * @author James Gohl
 * @version February 16, 2025
 */
public class Counter
{
    /** The current value of this counter. */
    private int count;

    /** The minimum value of this counter. */
    private int minimumCount;

    /** The maximum value of this counter. */
    private int maximumCount;

    /** The default minimum value of this counter. */
    private static final int DEFAULT_MINIMUM = 0;

    /** The default maximum value of this counter. */
    private static final int DEFAULT_MAXIMUM = 999;

    /**
     * Constructs a new RollOverCounter whose current count is
     * initialized to DEFAULT_MINIMUM, and which counts between
     * DEFAULT_MINIMUM and DEFAULT_MAXIMUM, inclusive.
     */
    public Counter()
    {
        minimumCount = DEFAULT_MINIMUM;
        maximumCount = DEFAULT_MAXIMUM;
        count = minimumCount;
    }

    /**
     * Constructs a new RollOverCounter whose current count is
     * initialized to minCount, and which counts between
     * minCount and maxCount, inclusive.
     * @param minCount the min count
     * @param maxCount the max count
     */
    public Counter(int minCount, int maxCount)
    {
        minimumCount = minCount;
        maximumCount = maxCount;
        count = minimumCount;
    }

    /**
     * Returns the maximum value of this counter.
     * @return max value of counter
     */
    public int maximumCount()
    {
        return maximumCount;
    }

    /**
     * Returns the minimum value of this counter.
     * @return the min value of counter
     */
    public int minimumCount()
    {
        return minimumCount;
    }

    /**
     * Returns this counter's current count.
     * @return the current count
     */
    public int count()
    {
        return count;
    }

    /**
     * Returns true if this counter is at its minimum value.
     * @return true  if the counter is at the minimum
     */
    public boolean isAtMinimum()
    {
        return (count == minimumCount);
    }

    /**
     * Returns true if this counter is at its maximum value.
     * @return true if counter at maximum
     */
    public boolean isAtMaximum()
    {
        return (count == maximumCount);
    }

    /**
     * Resets this counter to its minimum value.
     */
    public void reset()
    {
        count = minimumCount;
    }
    /**
     * Increment this counter by 1.
     */
    public void incrementCount()
    {
        count++;
    }
}
