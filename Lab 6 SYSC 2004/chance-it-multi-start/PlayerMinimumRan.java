import java.util.Random;
/**
 * PlayerMinimumSeven models a player with the Chance It game strategy of
 * rolling the dice until the minimum is a random number.
 *
 * @author James Gohl
 * @version 1.0 February 5, 2025
 */
public class PlayerMinimumRan extends Player
{
    int randomInt;
    /**
     * Constructor for objects of class PlayerOnly12
     *
     * @param dice The pair of dice shared by all players.
     * @param name The player's name.
     */
    public PlayerMinimumRan(Dice dice, String name)
    {
        super(dice, name);
        Random rand = new Random();
        randomInt = rand.nextInt(13);
    }

    /**
     * Encapsulates the strategy used by this player to determine when to
     * stop rolling the dice during each turn.
     * <p>
     * All concrete subclasses must provide a strategy for the player
     *
     * @return true when the player decides to end their current turn;
     * otherwise returns false.
     */
    protected boolean stopRolling()
    {
        return currentRoll >= randomInt;
    }
}
