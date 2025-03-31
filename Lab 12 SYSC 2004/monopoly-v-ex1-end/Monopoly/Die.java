package Monopoly;

import java.util.Random;

/**
 * Die models a 6-sided die.
 * 
 * @author D.L. Bailey, SCE, Carleton University
 * @version 1.01
 */
public class Die
{
    private Random generator;
 
    /**
     * Constructs a new Die. 
     *
     * @param r The random number generator for this die.
     */
    public Die(Random r)
    {
        generator = r;
    }
 
    /**
     * Returns the result of rolling a six-sided die.
     *
     * @return A pseudorandom integer between 1 and 6, inclusive.
     */
    public int roll()
    {
        /* The value returned by nextInt(6) will be a pseudorandom integer
         * value between 0 and 5, inclusive. Map this to a value between
         * 1 to 6, inclusive.
         */
        return generator.nextInt(6) + 1;
    }
}
