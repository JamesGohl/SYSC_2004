import java.util.Stack;

/**
 * This class is the main class of the "World of Zuul" application.
 * "World of Zuul" is a very simple, text based adventure game.  Users
 * can walk around some scenery. They can eat food, and grab and drop items
 * <p>
 * To play this game, create an instance of this class and call the "play"
 * method.
 * <p>
 * This main class creates and initialises all the others: it creates all
 * rooms, items, creates the parser and starts the game.  It also evaluates and
 * executes the commands that the parser returns.
 *
 * @author Michael Kolling and David J. Barnes
 * @author Lynn Marshall
 * @author James Gohl, 1012990543
 * @version March 8, 2025
 */

public class Game
{
    private Parser parser;
    private Room currentRoom;
    private Room previousRoom;
    private Stack<Room> roomStack;
    private boolean holding;
    private Item heldItem;
    private int health;

    /**
     * Create the game and initialise its internal map.
     */
    public Game()
    {
        createRooms();
        parser = new Parser();
        roomStack = new Stack<>();
        holding = false;
        heldItem = null;
        health = 0;
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room outside, theatre, pub, lab, office, transporter;
        Item chair, bench, sign1, sign2, garbageCan, computer, mouse, bottle1, bottle2, pen, cookie1, cookie2, cookie3,
                beamer1, beamer2;

        // create the rooms
        outside = new Room("outside the main entrance of the university");
        theatre = new Room("in a lecture theatre");
        pub = new Room("in the campus pub");
        lab = new Room("in a computing lab");
        office = new Room("in the computing admin office");
        transporter = new TransporterRoom();


        // create the items
        chair = new Item("an office chair (chair)", "chair", 10);
        bench = new Item("an outdoor bench (bench)", "bench", 20);
        sign1 = new Item("a sign with university logo (sign1)", "sign1", 0.5);
        sign2 = new Item("a sign about the theatre (sign2)", "sign2", 0.5);
        garbageCan = new Item("a metal garbage can (garbage)", "garbage", 7);
        computer = new Item("a Windows desktop (computer)", "computer", 12.3);
        mouse = new Item("a wired computer mouse (mouse)", "mouse", 0.04);
        bottle1 = new Item("a juice bottle (bottle1)", "bottle1", 0.32);
        bottle2 = new Item("a sprite bottle (bottle2)", "bottle", 0.32);
        pen = new Item("a red bic pen (pen)", "pen", 0.03);
        cookie1 = new Item("a chocolate chip cookie (cookie)", "cookie1", 0.01);
        cookie2 = new Item("a chocolate chip cookie (cookie)", "cookie", 0.01);
        cookie3 = new Item("a chocolate chip cookie (cookie) ", "cookie", 0.01);
        beamer1 = new Beamer("a beamer (beamer1)", "beamer1");
        beamer2 = new Beamer("a beamer (beamer2)", "beamer2");

        // add the items to the rooms
        outside.addItem(bench);
        outside.addItem(sign1);
        theatre.addItem(sign2);
        theatre.addItem(cookie1);
        theatre.addItem(bottle1);
        pub.addItem(bottle2);
        pub.addItem(cookie2);
        pub.addItem(garbageCan);
        lab.addItem(mouse);
        lab.addItem(computer);
        lab.addItem(chair);
        office.addItem(pen);
        office.addItem(cookie3);
        office.addItem(beamer1);
        outside.addItem(beamer2);

        // initialise room exits
        outside.setExit("east", theatre);
        outside.setExit("south", lab);
        outside.setExit("west", pub);
        outside.setExit("north", transporter);

        theatre.setExit("west", outside);

        pub.setExit("east", outside);

        lab.setExit("north", outside);
        lab.setExit("east", office);

        office.setExit("west", lab);

        transporter.setExit("south", outside);

        currentRoom = outside;  // starts the game outside
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
        } else if (commandWord.equals("take"))
        {
            take(command);
        } else if (commandWord.equals("drop"))
        {
            drop(command);
        } else if (commandWord.equals("charge"))
        {
            charge(command);
        } else if (commandWord.equals("fire"))
        {
            fire(command);
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
     * If you are in a transporter room, exit to a random room
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
        Room nextRoom;
        if (currentRoom instanceof TransporterRoom)
        {
            System.out.println("You are being transported to:");
        }
        nextRoom = currentRoom.getExit(direction);


        if (nextRoom == null)
        {
            System.out.println("There is no door!");
        } else
        {
            roomStack.push(currentRoom);
            previousRoom = currentRoom;
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
            System.out.println(whatHolding());
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
            System.out.println(whatHolding());
        }
    }

    /**
     * "eat" was entered. Check the rest of the command to see
     * whether it only has one word. Outputs that you have eaten
     * if holding food.
     *
     * @param command The command to be processed
     */
    private void eat(Command command)
    {
        if (command.hasSecondWord())
        {
            System.out.println("Eat what?");
        } else if (!holding)
        {
            System.out.println("You are not holding anything so you can not eat");
        } else if (heldItem != null && heldItem.getName().equals("cookie"))
        {
            System.out.println("You have eaten the cookie and are no longer hungry and can now pick up 5 items before being " +
                    "hungry again");
            heldItem = null;
            holding = false;
            health = 5; // number of items you can now pick up
        } else
        {
            System.out.println("You are not holding any food");
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
            temp = previousRoom;
            roomStack.push(currentRoom);
            previousRoom = currentRoom;
            currentRoom = temp;
            System.out.println("You have gone back");
            System.out.println(currentRoom.getLongDescription());
            System.out.println(whatHolding());
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
            System.out.println(whatHolding());

        }
    }

    /**
     * "take ______" was entered. picks up the item specified in the command
     *
     * @param command The command to be processed
     */
    private void take(Command command)
    {
        if (!command.hasSecondWord())
        {
            // if there is no second word, we don't know what to take
            System.out.println("Take what?");
            return;
        }
        String item = command.getSecondWord();

        if (holding)
        {
            System.out.println("You are already holding something.");
            return;
        }
        if (health <= 0 && !item.equals("cookie"))
        {
            System.out.println("You are hungry and cannot pick up an item. Please find and eat a cookie.");
            return;
        }
        for (Item x : currentRoom.getItemArr())
        {
            //object is in the room
            if (x.getName().equals(item))
            {
                System.out.println("You picked up " + item);
                decreaseHealth();
                currentRoom.removeItem(x);
                holding = true;
                heldItem = x;
                return;
            }
        }
        System.out.println("That item is not in the room.");
    }

    /**
     * "charge" was entered. Check if the user is holding a beamer
     * If holding beamer, charges beamer unless already charged
     *
     * @param command The command to be processed
     */
    private void charge(Command command)
    {
        if (command.hasSecondWord())
        {
            System.out.println("charge is a one word command.");
        } else if (!(heldItem instanceof Beamer))
        {
            System.out.println("You are not holding a beamer, so you cannot use the charge command");
        } else
        {
            ((Beamer) heldItem).charge(currentRoom);

        }
    }

    /**
     * "fire" was entered. Check if the user is holding a beamer
     * If holding beamer, beamer fires
     *
     * @param command The command to be processed
     */
    private void fire(Command command)
    {
        if (command.hasSecondWord())
        {
            System.out.println("fire is a one word command.");
        } else if (!(heldItem instanceof Beamer))
        {
            System.out.println("You are not holding a beamer, so you cannot use the fire command");
        } else
        {
            currentRoom = ((Beamer) heldItem).fire(currentRoom);

        }
    }

    /**
     * "drop" was entered. drops item if user is carrying something
     *
     * @param command The command to be processed
     */
    private void drop(Command command)
    {
        if (command.hasSecondWord())
        {
            System.out.println("drop what?");
        } else if (!holding)
        {
            System.out.println("You have nothing to drop");
        } else
        {
            currentRoom.getItemArr().add(heldItem);
            System.out.println("You have dropped " + heldItem.getName() + ".");
            holding = false;
            heldItem = null;
        }
    }

    /**
     * Outputs a string of what the player is holding
     * Informs the game if user is not holding anything
     *
     * @return the string of what you are holding
     */
    private String whatHolding()
    {
        if (holding)
        {
            return "You are holding " + heldItem.getName() + ".";
        } else
        {
            return "You are not holding anything,";
        }
    }

    /**
     * Decrease the player's health by 1 to a min of 0
     */
    private void decreaseHealth()
    {
        if (health > 0)
        {
            health--;
            System.out.println("By picking up an item, you have decreased your health you can now pick up to only "
                    + health + " items before becoming tired and needing to eat.");
        }

    }
}
