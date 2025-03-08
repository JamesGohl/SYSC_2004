/**
 * This class is the item class for the game of zuul.
 * <p>
 * This item class has a constructor that takes
 * an items description and its weight and there is a
 * method that returns a string representation of the item.
 *
 * @author James Gohl, 101299043
 * @version Jan 26, 2025
 */
public class Item
{
    private String description;
    private double weight;


    /**
     * Constructor for the item needing a description and weight
     *
     * @param description The item's description
     * @param weight      The item's weight
     */
    public Item(String description, double weight)
    {
        this.description = description;
        this.weight = weight;
    }


    /**
     * returns a String representation of the item
     *
     * @return String representation of item
     */
    public String getDescription()
    {
        return ("Item: " + description + ". Weight(kg): " + weight);
    }

}
