import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * A class modelling a tic-tac-toe (noughts and crosses, Xs and Os) game in a very
 * simple GUI window.
 * 
 * @author Lynn Marshall
 * @author James Gohl
 * @version March 21, 2025
 */

public class TicTacToeFrame extends TicTacToe 
{ 
   private JTextArea status; // text area to print game status
   private JFrame frame;
   private JScrollPane scroll;
   
   /** 
    * Constructs a new Tic-Tac-Toe board and sets up the basic
    * JFrame containing a JTextArea in a JScrollPane GUI.
    */
   public TicTacToeFrame()
   { 
       frame = new JFrame("Tic Tac Toe Game");
       frame.setSize(400, 300);
       status = new JTextArea(10, 30);
       scroll = new JScrollPane(status);

       frame.getContentPane().add(scroll, BorderLayout.CENTER);
       frame.setVisible(true);
   }
   
   /**
    * Prints the board to the GUI using toString().
    */
    public void print() 
    {  
        status.append(super.toString() + "\n");
    }

}