/**
 * This is a beamer class which is a subclass of item
 * A beamer can be charged and then fired to transport you
 * to the room it was charged in. Firing it means
 * it must be recharged.
 *
 * @author James Gohl 101299043
 * @version March 8, 2025
 */
public class Beamer extends Item
{
    private Boolean charged;
    private Room room;

    /**
     * creates a Beamer object
     *
     * @param description the beamers description
     * @param name        the name of the specific beamer
     */
    public Beamer(String description, String name)
    {
        super(description, name, 1);
        charged = false;
    }

    /**
     * sets the room that the beamer will go to when charged
     *
     * @param room the room it will go to
     */
    private void setRoom(Room room)
    {
        this.room = room;
    }

    /**
     * returns true if the beamer is charged
     *
     * @return true if Beamer is charged
     */
    public Boolean getCharged()
    {
        return charged;
    }

    /**
     * Charges the beamer
     * outputs if the beamer is successfully charged or not
     *
     * @param room the room it is being charged in
     */
    public void charge(Room room)
    {
        if (this.charged)
        {
            System.out.println("Beamer is charged and thus cannot be charged");
        } else
        {
            this.charged = true;
            setRoom(room);
            System.out.println("Beamer has been successfully charged");
        }
    }

    /**
     * Fires the beamer
     * outputs if the beamer is successfully fired or not
     *
     * @param currentRoom the current room you are in
     * @return the room transported to, or the current room if not charged
     */
    public Room fire(Room currentRoom)
    {
        if (!this.charged)
        {
            System.out.println("Beamer is not charged and thus cannot be fired");
            return currentRoom;
        } else
        {
            this.charged = false;

            System.out.println("You have been transported to " + this.room.getShortDescription());
            System.out.println(this.room.getLongDescription());
            Room returnVal = this.room;
            setRoom(null);
            return returnVal;
        }
    }
}