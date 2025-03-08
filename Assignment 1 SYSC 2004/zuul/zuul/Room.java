import java.util.ArrayList;
import java.util.Set;
import java.util.HashMap;

/**
 * Class Room - a room in an adventure game.
 * <p>
 * This class is part of the "World of Zuul" application.
 * "World of Zuul" is a very simple, text based adventure game.
 * <p>
 * A "Room" represents one location in the scenery of the game.
 * There are items in a room.
 * It is connected to other rooms via exits.  For each existing exit, the room
 * stores a reference to the neighboring room.
 *
 * @author Michael Kolling and David J. Barnes
 * @author Lynn Marshall
 * @author James Gohl, 101299043
 * @version February 12, 2025
 */

public class Room
{
    private String description;
    private HashMap<String, Room> exits;        // stores exits of this room.
    private ArrayList<Item> itemArr;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     *
     * @param description The room's description.
     */
    public Room(String description)
    {
        this.description = description;
        exits = new HashMap<String, Room>();
        itemArr = new ArrayList<>();
    }

    /**
     * Define an exit from this room.
     *
     * @param direction The direction of the exit
     * @param neighbour The room to which the exit leads
     */
    public void setExit(String direction, Room neighbour)
    {
        exits.put(direction, neighbour);
    }

    /**
     * Returns a short description of the room, i.e. the one that
     * was defined in the constructor
     *
     * @return The short description of the room
     */
    public String getShortDescription()
    {
        return description;
    }

    /**
     * Return a long description of the room in the form:
     * You are in the kitchen.
     * Exits: north west
     * Item:
     * items
     *
     * @return A long description of this room
     */
    public String getLongDescription()
    {
        return "You are " + description + ".\n" + getExitString() + "\n" + getItems();
    }

    /**
     * Return a string describing the room's exits, for example
     * "Exits: north west".
     *
     * @return Details of the room's exits
     */
    private String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for (String exit : keys)
        {
            returnString += " " + exit;
        }
        return returnString;
    }

    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     *
     * @param direction The exit's direction
     * @return The room in the given direction
     */
    public Room getExit(String direction)
    {
        return exits.get(direction);
    }


    /**
     * Adds an item to the room
     *
     * @param item The item to be added
     */
    public void addItem(Item item)
    {
        itemArr.add(item);
    }

    /**
     * Return a string describing all the room's items
     *
     * @return String of room's items
     */
    private String getItems()
    {
        String returnString = "Items:\n";
        for (Item item : itemArr)
        {
            returnString += item.getDescription() + "\n";
        }
        return returnString;
    }
}





