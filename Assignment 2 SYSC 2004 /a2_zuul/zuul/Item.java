/**
 * This class is the item class for the game of zuul.
 * <p>
 * This item class has a constructor that takes
 * an items description and its weight and there is a
 * method that returns a string representation of the item.
 *
 * @author James Gohl, 101299043
 * @version March 8, 2025
 */
public class Item
{
    private String description;
    private double weight;
    private String name;


    /**
     * Constructor for the item needing a description and weight
     *
     * @param description The item's description
     * @param name        The item's name
     * @param weight      The item's weight
     */
    public Item(String description, String name, double weight)
    {
        this.description = description;
        this.name = name;
        this.weight = weight;
    }


    /**
     * returns a String representation of the item
     * Includes description, name and weight
     *
     * @return String representation of item
     */
    public String getDescription()
    {
        return ("Item: " + description + " Name: " + name + " Weight(kg): " + weight);
    }

    /**
     * returns a String representation of the item's name
     *
     * @return String representation of item's name
     */
    public String getName()
    {
        return this.name;
    }

}
