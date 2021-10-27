
/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Zachary Harris
 * @version 10/26/21
 */

import java.util.ArrayList;

public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private ArrayList<String> inventory;
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
        ArrayList<String> inventory = new ArrayList<>();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room outside, theater, pub, lab, office;
        Items roomItems = new Items();
      
        // create the rooms
        outside = new Room("outside the main entrance of the university");
        
        theater = new Room("in a lecture theater");
        theater.addRoomItems(roomItems.getRandomItems(3));
        
        pub = new Room("in the campus pub");
        pub.addRoomItems(roomItems.getRandomItems(3));
        
        lab = new Room("in a computing lab");
        lab.addRoomItems(roomItems.getRandomItems(3));
        
        office = new Room("in the computing admin office");
        office.addRoomItems(roomItems.getRandomItems(3));
        
        
        // initialise room exits
        outside.setExit("east", theater);
        outside.setExit("south", lab);
        outside.setExit("west", pub);

        theater.setExit("west", outside);

        pub.setExit("east", outside);

        lab.setExit("north", outside);
        lab.setExit("east", office);

        office.setExit("west", lab);

        currentRoom = outside;  // start game outside
        
        
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
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
        System.out.println("Welcome to my survival text adventure!!");
        System.out.println("You will navigate several buildings");
        System.out.println("Each building will have health pickups and collectables");
        System.out.println("Health pickups and collectibles will be randomized");
        System.out.println("Find as many collectibles as you can/Don't let your health run out");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        switch (commandWord) {
            case UNKNOWN:
                System.out.println("I don't know what you mean...");
                break;

            case HELP:
                printHelp();
                break;

            case GO:
                goRoom(command);
                break;

            case QUITGAME:
                wantToQuit = quit(command);
                break;
                
            case LOOK:
                look();
                break;
                
            case TAKE:
                
                
            
                
            
        }
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You must Survive.");
        System.out.println("You have been stationed on the front lines of war.");
        System.out.println("You will be faced with a serious of decisions");
        System.out.println("that will determine your fate on the coming conflict");
        System.out.println();
        System.out.println("Your command words are:");
        System.out.println(parser.getCommands().getCommandList());
    }

    /** 
     * Try to go in one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
            currentRoom.displayItemsInRoom();
        }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
    
    /**
     * This method will give the long description
     * of the current room the user is in.
     */
    private void look(){
        System.out.println(currentRoom.getLongDescription());
    }
}
