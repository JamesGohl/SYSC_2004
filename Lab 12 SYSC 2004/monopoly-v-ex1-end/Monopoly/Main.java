package Monopoly;

/**
 * The main program for the Monopoly Game.
 *
 * @author Lynn Marshall, SCE, Carleton University
 * @version 1.0
 */
public class Main
{
  public static void main(String[] args) {
    // Create a MonopolyGame object called monopoly
    MonopolyGame monopoly = new MonopolyGame();
    monopoly.addPlayer("Susan");
    monopoly.addPlayer("Mo");
    monopoly.addPlayer("Ike");
    monopoly.playGame(5);
  }
}

