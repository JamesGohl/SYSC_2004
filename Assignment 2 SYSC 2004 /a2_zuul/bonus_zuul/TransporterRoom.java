import java.util.Random;
/**
 * this is a subclass of rooms for the transporter room
 * When the go command is used it randomly transports you
 * to another room
 *
 * @author James Gohl 101299043
 * @version March 4, 2025
 */
public class TransporterRoom extends Room
{
    Random rand;
    /**
     * transporter room constructor
     */
    public TransporterRoom()
    {
        super("in a transporter room where leaving takes you somewhere random", "transporter.jpg");
        rand = new Random();
    }
    /**
     * Returns a random room, independent of the direction parameter
     *
     * @param direction Ignored
     * @return A randomly selected room.
     */

    public Room getExit(String direction)
    {
        return findRandomRoom();
    }

    /**
     * Choose a random room.
     *
     * @return The room we end up in upon leaving this one.
     */
    private Room findRandomRoom()
    {
        return super.getRoomArr().get(rand.nextInt(super.getRoomArr().size()));
    }
}
