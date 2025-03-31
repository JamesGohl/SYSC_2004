package Monopoly;

import java.util.Random;

/**
 * Dice models a pair of 6-sided dice.
 * 
 * @author D.L. Bailey, SCE, Carleton University
 * @version 1.01
 */
public class Dice
{
    private Die die1;
    private Die die2;
    
    /**
     * Constructs a pair of six-sided dice.
     */
    public Dice()
    {
        Random r = new Random();
        die1 = new Die(r);
        die2 = new Die(r);
    }
    
    /**
     * Returns the result of rolling a pair of dice.
     * 
     * @return A pseudorandom integer between 2 and 12, inclusive.
     */
    public int roll()
    {
        return die1.roll() + die2.roll();
    }
}
