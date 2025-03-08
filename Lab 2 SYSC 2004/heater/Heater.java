/**
 * A Heater models a simple space-heater. The operations provided by a Heater
 * object are:
 * 1. Increase and decrease the temperature setting by a set amount.
 * 2. Return the current temperature setting.
 * 3. Change the set amount by which the temperature is increased and lowered.
 *
 * @author L.S. Marshall, SCE, Carleton University
 * (incomplete implementation for SYSC 2004 Lab 2)
 * @author James Gohl
 * @version 1.03 Jan 13, 2025
 */
public class Heater
{
    /**
     * The temperature setting that the heater should maintain.
     */
    private int temperature;

    /**
     * The temperature setting for a newly created heater.
     */
    private static final int INITIAL_TEMPERATURE = 15;

    /**
     * The amount by which the temperature setting is raised/lowered when
     * warmer() and cooler() are invoked.
     */
    private int increment;

    /**
     * The default amount by which the temperature setting is
     * increased when warmer() is invoked and decreased when cooler()
     * is invoked.
     */
    private static final int DEFAULT_INCREMENT = 5;

    /**
     * The minimum temperature setting that the heater can be.
     */
    private int min;

    /**
     * The max temperature setting that the heater can be.
     */
    private int max;

    /**
     * The default minimum by which the temperature setting is
     * can be
     */
    private static final int DEFAULT_MIN = 0;

    /**
     * The default maximum by which the temperature setting is
     * can be
     */
    private static final int DEFAULT_MAX = 100;

    /**
     * Constructs a new Heater with an initial temperature setting of 15
     * degrees, and which increments and decrements the temperature
     * setting in increments of 5 degrees.
     */
    public Heater()
    {
        temperature = INITIAL_TEMPERATURE;
        increment = DEFAULT_INCREMENT;
        min = DEFAULT_MIN;
        max = DEFAULT_MAX;
    }

    /**
     * Write an appropriate comment here.
     */
    public Heater(int minTemp, int maxTemp)
    {
        temperature = INITIAL_TEMPERATURE;
        increment = DEFAULT_INCREMENT;
        min = minTemp;
        max = maxTemp;
    }

    /**
     * Returns the current temperature settings.
     */
    public int temperature()
    {
        return temperature;
    }

    /**
     * Increase the temperature by increment.
     */
    public void warmer()
    {
        if (temperature + increment <= max)
        {
            temperature += increment;
        }
    }

    /**
     * Decreases the temperature by increment.
     */
    public void cooler()
    {
        if (temperature - increment >= min)
        {

            temperature -= increment;
        }

    }


    /**
     * Write an appropriate comment here.
     */
    public void setIncrement(int newIncrement)
    {
        if (newIncrement > 0)
        {
            increment = newIncrement;
        }
    }
}
