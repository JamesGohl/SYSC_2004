import java.util.Stack;

/**
 * This class is the main class of the "World of Zuul" application.
 * "World of Zuul" is a very simple, text based adventure game.  Users
 * can walk around some scenery. That's all. It should really be extended
 * to make it more interesting!
 * <p>
 * To play this game, create an instance of this class and call the "play"
 * method.
 * <p>
 * This main class creates and initialises all the others: it creates all
 * rooms, creates the parser and starts the game.  It also evaluates and
 * executes the commands that the parser returns.
 *
 * @author Michael Kolling and David J. Barnes
 * @author Lynn Marshall
 * @author James Gohl, 1012990543
 * @version February 12, 2025
 */

public class Game
{
    private Parser parser;
    private Room currentRoom;
    private Room previousRoom;
    private Stack<Room> roomStack;

    /**
     * Create the game and initialise its internal map.
     */
    public Game()
    {
        createRooms();
        parser = new Parser();
        roomStack = new Stack<>();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room outside, theatre, pub, lab, office;
        Item chair, bench, sign, garbageCan, computer, mouse, bottle, pen;

        // create the rooms
        outside = new Room("outside the main entrance of the university");
        theatre = new Room("in a lecture theatre");
        pub = new Room("in the campus pub");
        lab = new Room("in a computing lab");
        office = new Room("in the computing admin office");

        // create the items
        chair = new Item("office chair", 10);
        bench = new Item("outdoor bench", 20);
        sign = new Item("sign", 0.5);
        garbageCan = new Item("garbage can", 7);
        computer = new Item("Windows desktop", 12.3);
        mouse = new Item("wired computer mouse", 0.04);
        bottle = new Item("juice bottle", 0.32);
        pen = new Item("red bic pen", 0.03);

        // add the items to the rooms
        outside.addItem(bench);
        outside.addItem(sign);
        theatre.addItem(sign);
        theatre.addItem(bottle);
        pub.addItem(bottle);
        pub.addItem(garbageCan);
        lab.addItem(mouse);
        lab.addItem(computer);
        lab.addItem(chair);
        office.addItem(pen);

        // initialise room exits
        outside.setExit("east", theatre);
        outside.setExit("south", lab);
        outside.setExit("west", pub);

        theatre.setExit("west", outside);

        pub.setExit("east", outside);

        lab.setExit("north", outside);
        lab.setExit("east", office);

        office.setExit("west", lab);

        currentRoom = outside;  // start game outside
        previousRoom = null; // no previous room at start of game
    }

    /**
     * Main play routine.  Loops until end of play.
     */
    public void play()
    {
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (!finished)
        {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     *
     * @param command The command to be processed
     * @return true If the command ends the game, false otherwise
     */
    private boolean processCommand(Command command)
    {
        boolean wantToQuit = false;

        if (command.isUnknown())
        {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help"))
        {
            printHelp();
        } else if (commandWord.equals("go"))
        {
            goRoom(command);
        } else if (commandWord.equals("look"))
        {
            look(command);
        } else if (commandWord.equals("eat"))
        {
            eat(command);
        } else if (commandWord.equals("back"))
        {
            back(command);
        } else if (commandWord.equals("stackBack"))
        {
            stackBack(command);
        } else if (commandWord.equals("quit"))
        {
            wantToQuit = quit(command);
        }
        // else command not recognised.
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print a cryptic message and a list of the
     * command words.
     */
    private void printHelp()
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        String[] commands = parser.getCommands();
        for (String command : commands)
        {
            System.out.print(command + " ");
        }
    }

    /**
     * Try to go to one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     *
     * @param command The command to be processed
     */
    private void goRoom(Command command)
    {
        if (!command.hasSecondWord())
        {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null)
        {
            System.out.println("There is no door!");
        } else
        {
            roomStack.push(currentRoom);
            previousRoom = currentRoom;
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
        }
    }

    /**
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     *
     * @param command The command to be processed
     * @return true, if this command quits the game, false otherwise
     */
    private boolean quit(Command command)
    {
        if (command.hasSecondWord())
        {
            System.out.println("Quit what?");
            return false;
        } else
        {
            return true;  // signal that we want to quit
        }
    }

    /**
     * "look" was entered. Check the rest of the command to see
     * whether it only has one word. Outputs long description of current room
     *
     * @param command The command to be processed
     */
    private void look(Command command)
    {
        if (command.hasSecondWord())
        {
            System.out.println("Look what?");
        } else
        {
            System.out.println(currentRoom.getLongDescription());
        }
    }

    /**
     * "eat" was entered. Check the rest of the command to see
     * whether it only has one word. Outputs that you have eaten
     *
     * @param command The command to be processed
     */
    private void eat(Command command)
    {
        if (command.hasSecondWord())
        {
            System.out.println("Eat what?");
        } else
        {
            System.out.println("You have eaten and are no longer hungry");
        }
    }


    /**
     * "back" was entered. Check the rest of the command to see
     * whether it only has one word. Takes you to previous room
     *
     * @param command The command to be processed
     */
    private void back(Command command)
    {
        if (command.hasSecondWord())
        {
            System.out.println("Back what?");
        } else if (previousRoom == null)
        {
            System.out.println("You are at the start of the game and cannot go back");
        } else
        {
            Room temp;
            temp = currentRoom;
            currentRoom = previousRoom;
            roomStack.push(currentRoom);
            previousRoom = temp;
            System.out.println("You have gone back");
            System.out.println(currentRoom.getLongDescription());
        }
    }

    /**
     * "stackBack" was entered. Check the rest of the command to see
     * whether it only has one word. Takes you to previous rooms until
     * the beginning in a stack like fashion
     *
     * @param command The command to be processed
     */
    private void stackBack(Command command)
    {
        if (command.hasSecondWord())
        {
            System.out.println("Back what?");
        } else if (roomStack.isEmpty())
        {
            System.out.println("You are at the beginning, you cannot go stackBack");
        } else
        {
            previousRoom = currentRoom;
            currentRoom = roomStack.pop();
            System.out.println("You have gone stackBack");
            System.out.println(currentRoom.getLongDescription());

        }
    }
}
