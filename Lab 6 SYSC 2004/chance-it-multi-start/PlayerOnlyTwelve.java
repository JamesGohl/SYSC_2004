/**
 * PlayerMinimumSeven models a player with the Chance It game strategy of
 * rolling the dice until the roll is 12.
 *
 * @author James Gohl
 * @version 1.0 February 5, 2025
 */
public class PlayerOnlyTwelve extends Player
{
    /**
     * Constructor for objects of class PlayerOnly12
     *
     * @param dice The pair of dice shared by all players.
     * @param name The player's name.
     */
    public PlayerOnlyTwelve(Dice dice, String name)
    {
        super(dice, name);
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
        return currentRoll == 12;
    }
}
