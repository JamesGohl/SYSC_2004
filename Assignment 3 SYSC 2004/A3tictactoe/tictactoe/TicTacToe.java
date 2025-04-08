import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * A class modelling a tic-tac-toe (noughts and crosses, Xs and Os) game.
 *
 * @author Lynn Marshall
 * @author James Gohl 101299043
 * @version April 7, 2025
 */

public class TicTacToe implements ActionListener
{
    public static final String PLAYER_X = "X"; // player using "X"
    public static final String PLAYER_O = "O"; // player using "O"
    public static final String EMPTY = " ";  // empty cell
    public static final String TIE = "T"; // game ended in a tie

    private String player;   // current player (PLAYER_X or PLAYER_O)

    private String winner;   // winner: PLAYER_X, PLAYER_O, TIE, EMPTY = in progress

    private int numFreeSquares; // number of squares still free
    private JButton[][] board; // 3x3 array representing the board

    private JFrame frame; // the tictactoe frame
    private JPanel buttonPanel; // the panel containing the 3x3 grid of buttons

    private JLabel gameState; // the label that outputs the state of the game

    private JMenuBar menuBar;
    private JMenu gameMenu;
    private JMenuItem newItem, quitItem;

    /**
     * Constructs a new Tic-Tac-Toe board.
     */
    public TicTacToe()
    {
        frame = new JFrame("Tic Tac Toe Game");
        frame.setSize(400, 300);

        menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        gameMenu = new JMenu("Game");
        menuBar.add(gameMenu);

        newItem = new JMenuItem("New");
        quitItem = new JMenuItem("Quit");

        // Makes it so the correct command or ctrl key for shortcuts based on operating system
        final int SHORTCUT_MASK = Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx();

        newItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, SHORTCUT_MASK));
        quitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, SHORTCUT_MASK));

        newItem.addActionListener(this);
        quitItem.addActionListener(this);

        gameMenu.add(newItem);
        gameMenu.add(quitItem);

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 3));

        board = new JButton[3][3];

        // construct JButtons
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                JButton button = new JButton();
                board[i][j] = button;
                button.addActionListener(this);
                buttonPanel.add(button);

                // stores a key value pair of the buttons coordinates
                button.putClientProperty("row", i);
                button.putClientProperty("col", j);
                button.setFont(new Font("Arial", Font.PLAIN, 70));
            }
        }

        gameState = new JLabel("", JLabel.CENTER);

        frame.getContentPane().add(buttonPanel, BorderLayout.CENTER);
        frame.getContentPane().add(gameState, BorderLayout.SOUTH);


    }

    /**
     * Sets everything up for a new game.  Marks all squares in the Tic Tac Toe board as empty,
     * and indicates no winner yet, 9 free squares and the current player is player X.
     */
    private void clearBoard()
    {
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                board[i][j].setText("");
                board[i][j].setEnabled(true);
            }
        }
        winner = EMPTY;
        numFreeSquares = 9;
        player = PLAYER_X;     // Player X always has the first turn.
        displayGameState();
    }

    /**
     * Action performed method
     *
     * @param e the event that happened
     */

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() instanceof JButton)
        {
            buttonClick((JButton) e.getSource());
        }
        //menu item
        else
        {
            menuClick((JMenuItem) e.getSource());
        }
    }

    /**
     * the action listener calls this when the button is pressed
     *
     * @param button the button that was clicked
     */
    private void buttonClick(JButton button)
    {
        button.setText(player);
        numFreeSquares--;

        if (haveWinner((int) button.getClientProperty("row"), (int) button.getClientProperty("col")))
        {
            winner = player; // must be the player who just went
            buttonOff(); // turn off buttons cause game is over
        } else if (numFreeSquares == 0)
        {
            winner = TIE; // board is full so it's a tie
            buttonOff(); // turn off buttons cause game is over
        } else // if no winner changes the player to the next
        {
            if (player == PLAYER_X)
                player = PLAYER_O;
            else
                player = PLAYER_X;
        }
        button.setEnabled(false); // disable the button just clicked
        displayGameState();
    }

    /**
     * Action performed calls this when a menu item or menu shortcut is clicked/inputted
     *
     * @param item the menu item clicked
     */
    private void menuClick(JMenuItem item)
    {
        if (item == quitItem)
        {
            System.exit(0);
        }
        // if new item
        else
        {
            playGame();
        }
    }

    /**
     * Plays one game of Tic Tac Toe.
     */
    public void playGame()
    {
        clearBoard();
        frame.setVisible(true);
    }


    /**
     * Returns true if filling the given square gives us a winner, and false
     * otherwise.
     *
     * @param row row of square just set
     * @param col col of square just set
     * @return true if we have a winner, false otherwise
     */
    private boolean haveWinner(int row, int col)
    {
        // unless at least 5 squares have been filled, we don't need to go any further
        // (the earliest we can have a winner is after player X's 3rd move).

        if (numFreeSquares > 4) return false;

        // Note: We don't need to check all rows, columns, and diagonals, only those
        // that contain the latest filled square.  We know that we have a winner
        // if all 3 squares are the same, as they can't all be blank (as the latest
        // filled square is one of them).

        // check row "row"
        if ((board[row][0]).getText().equals((board[row][1]).getText()) &&
                (board[row][0]).getText().equals((board[row][2]).getText())) return true;

        // check column "col"
        if ((board[0][col]).getText().equals((board[1][col]).getText()) &&
                (board[0][col]).getText().equals((board[2][col]).getText())) return true;

        // if row=col check one diagonal
        if (row == col)
            if ((board[0][0]).getText().equals((board[1][1]).getText()) &&
                    (board[0][0]).getText().equals((board[2][2]).getText())) return true;

        // if row=2-col check other diagonal
        if (row == 2 - col)
            if ((board[0][2]).getText().equals((board[1][1]).getText()) &&
                    (board[0][2]).getText().equals((board[2][0]).getText())) return true;

        // no winner yet
        return false;
    }


    /**
     * Turns all the buttons in the grid to not enabled
     */
    public void buttonOff()
    {
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                board[i][j].setEnabled(false);
            }
        }
    }

    /**
     * updates the JLabel that says which turn it is or who the winner is
     */
    private void displayGameState()
    {
        if (winner == TIE)
        {
            gameState.setText("Game over! It is a tie.");

        } else if (winner != EMPTY)
        {
            gameState.setText("Game over! " + winner + " wins.");

        } else
        {
            gameState.setText("Game in progress and " + player + "'s turn.");
        }
    }
}

