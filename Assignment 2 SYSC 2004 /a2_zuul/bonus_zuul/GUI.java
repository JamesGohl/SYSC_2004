import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * This is a class that implemetns the GUI for the ZUUl Game
 *
 * @author James Gohl
 * @version March 4, 2025
 */
public class GUI
{
    private JFrame frame;
    private JButton quit, help, look, back, stackBack, eat, drop, charge, fire, take, go;
    private ArrayList<JButton> button1CommandArr, button2CommandArr;
    private Game my_game;
    private JTextArea textArea;
    private JTextField textBox;
    private String command2, command1;
    private JLabel imageLabel;
    /**
     * Constructor of gui
     */
    public GUI(Game my_game)
    {
        this.my_game = my_game;
        command2 = null;
        frame = new JFrame();
        frame.setTitle("ZUul GUI");
        frame.setSize(600, 600);

        //make buttons
        quit = new JButton("Quit");
        quit.setName("quit");

        help = new JButton("Help");
        help.setName("help");

        look = new JButton("Look");
        look.setName("look");

        back = new JButton("Back");
        back.setName("back");

        stackBack = new JButton("stackBack");
        stackBack.setName("stackBack");

        eat = new JButton("Eat");
        eat.setName("eat");

        drop = new JButton("Drop");
        drop.setName("drop");

        charge = new JButton("charge");
        charge.setName("charge");

        fire = new JButton("Fire");
        fire.setName("fire");

        take = new JButton("Take");
        take.setName("take");

        go = new JButton("Go");
        go.setName("go");

        button1CommandArr = new ArrayList<JButton>();
        button1CommandArr.add(quit);
        button1CommandArr.add(help);
        button1CommandArr.add(quit);
        button1CommandArr.add(look);
        button1CommandArr.add(back);
        button1CommandArr.add(stackBack);
        button1CommandArr.add(eat);
        button1CommandArr.add(drop);
        button1CommandArr.add(charge);
        button1CommandArr.add(fire);

        button2CommandArr = new ArrayList<JButton>();
        button2CommandArr.add(go);
        button2CommandArr.add(take);

        textArea = new JTextArea(20, 20);
        textBox = new JTextField((15));
        imageLabel = new JLabel();
        updateImage("outside.jpg");
        frame.add(imageLabel);

        frame.add(textArea);


        // layout manager makes components in a row
        frame.setLayout(new FlowLayout());
        addButtons1Command();
        addButtons2Command();
        textBoxAction();
        //frame.setVisible(true);
        //my_game = new Game();
        //my_game.printWelcome();
        //my_game.play();
    }

    /**
     * Adds the one word command buttons to the gui
     */
    private void addButtons1Command()
    {
        for (JButton button : button1CommandArr)
        {
            frame.add(button);
            //remove text box if button pressed in middle of 2 word command
            frame.remove(textBox);
            button.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    processCommand(button.getName(), null);
                    frame.remove(textBox);
                }
            });
        }
    }

    /**
     * Adds the two word command buttons to the gui
     */
    private void addButtons2Command()
    {
        for (JButton button : button2CommandArr)
        {
            frame.add(button);
            button.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    frame.add(textBox);
                    command1 = button.getName();
                    // add something for getting the second word like a text field
                    // with a box or something
                    textArea.append("\n Please enter the second word in the text box and input enter");
                    //textBoxAction( button.getName());


                }
            });
        }
    }


    /**
     * processes the command when a button is clicked
     */
    private void processCommand(String command1, String command2)
    {

        Command command = new Command(command1, command2);
        my_game.getProcessCommand(command);
    }

    private void textBoxAction()
    {
        textBox.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("textbox");
                command2 = textBox.getText();
                textBox.setText("");
                processCommand(command1, command2);
                frame.remove(textBox);
            }
        });
    }

    public JTextArea getTextArea()
    {
        return textArea;
    }

    public void setFrameVisible()
    {
        frame.setVisible(true);
    }

    /**
     * sets the image in the gui to image path
     * @param imagePath the file of the image
     */
    public void updateImage(String imagePath)
    {
        imageLabel.setIcon(new ImageIcon(imagePath));

    }


}
