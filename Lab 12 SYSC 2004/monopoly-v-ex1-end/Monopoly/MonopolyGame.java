package Monopoly;

import java.util.ArrayList;

/**
 * A simulation of an extremely simplified version of the
 * game of Monopoly.
 * 
 * @author Lynn Marshall, SCE, Carleton University
 * @version 1.0
 */
public class MonopolyGame
{
    // The game board
    private Board board;
    
    // The dice shared by all players
    private Dice dice;    
    
    // The list of players
    private ArrayList<Player> players;
 
    /**
     * Constructs a new MonopolyGame with no players.
     */
    public MonopolyGame()
    {     
        board = new Board();
        dice = new Dice();       
        players = new ArrayList<Player>();                      
    }
 
    /**
     * Adds a player with the specified name to the game.
     *
     * @param name The player's name.
     */
    public void addPlayer(String name)
    {
        players.add(new Player(name, board, dice));
    }
    
    /**
     * Plays the game for the specified number of rounds.
     *
     * @param rounds the number of rounds to play.
     */
    public void playGame(int rounds)
    {
        for (int i = 1; i <= rounds; i++) {
            playRound();
        }
    }
    
    /**
     * Plays one round of the game, requesting each player to take one turn.
     */
    private void playRound()
    {
        for (Player player : players) {
            player.takeTurn();
        }
    }
}

        
    