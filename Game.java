
/**
 *  This class is the main class of the my "Escape Room" inspired game.
 *  You will be locked in a gated University Campus.
 *  You must collect a keycard to unlock the main gate to escape and find valuable collectibles.
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
import java.util.Stack;

public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private ArrayList<String> inventory;
    private Player player;
    private String lastDirection;
    private Stack<Room> previousRooms;
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        player = new Player("Bob");
        createRooms();
        parser = new Parser();
        ArrayList<String> inventory = new ArrayList<>();
        previousRooms = new Stack<Room>();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room 
        outside, frontGate,
        theater, theaterBackstage, theaterDressRoom, theaterSoundBooth, 
        pub, pubKitchen, pubManagerOffice,
        complab, complabAdmin, complabITDep,
        courtyard, weightRoom, coachingOffice, basketballCourt, badmittenCourt, tennisCourt;
        Items roomItems = new Items();
      
        // create the rooms
        outside = new Room("beyond the courtyard in the middle of the University");
        
        
        
        frontGate = new Room("at the front gate/exit");
        frontGate.addRoomItems(roomItems.getRandomItems(3));
        
        theater = new Room("in a lecture theater");
        theater.addRoomItems(roomItems.getRandomItems(3));
        
        theaterBackstage = new Room("in the backstage area of the theator");
        theaterBackstage.addRoomItems(roomItems.getRandomItems(3));
        
        theaterDressRoom = new Room("behind the backstage area in the dressing rooms");
        theaterDressRoom.addRoomItems(roomItems.getRandomItems(3));
        
        theaterSoundBooth = new Room("above the theator in the sound booth");
        theaterSoundBooth.addRoomItems(roomItems.getRandomItems(3));
        
        
        
        pub = new Room("in the campus pub");
        pub.addRoomItems(roomItems.getRandomItems(3));
        
        pubKitchen = new Room("in the campus pubs kitchen");
        pubKitchen.addRoomItems(roomItems.getRandomItems(3));
        
        pubManagerOffice = new Room("in the campus pubs managers office");
        pubManagerOffice.addRoomItems(roomItems.getRandomItems(3));
        

        
        complab = new Room("in a computing lab");
        complab.addRoomItems(roomItems.getRandomItems(3));
        
        complabAdmin = new Room("in the computer labs Admin Office");
        complabAdmin.addRoomItems(roomItems.getRandomItems(3));
        
        complabITDep = new Room("in the computer labs IT department");
        complabITDep.addRoomItems(roomItems.getRandomItems(3));
        
        
        
        courtyard = new Room("in the campus courtyard/activity area");
        courtyard.addRoomItems(roomItems.getRandomItems(3));
        
        weightRoom = new Room("in the campuses weight room next to the courtyard");
        weightRoom.addRoomItems(roomItems.getRandomItems(3));
        
        coachingOffice = new Room("in the coaching offices for the university sports teams");
        coachingOffice.addRoomItems(roomItems.getRandomItems(3));
        
        basketballCourt = new Room("in the courtyards basketball court");
        basketballCourt.addRoomItems(roomItems.getRandomItems(3));
        
        badmittenCourt = new Room("in the courtyards badmitten court");
        badmittenCourt.addRoomItems(roomItems.getRandomItems(3));
        
        tennisCourt = new Room("in the courtyards tennis court");
        tennisCourt.addRoomItems(roomItems.getRandomItems(3));
        
        
        
        
        // initialise room exits
        outside.setExit("east", pub);
        outside.setExit("south", complab);
        outside.setExit("west", theater);
        outside.setExit("north", courtyard);

        
        
        theater.setExit("east", outside);
        theater.setExit("west", theaterBackstage);
        theater.setExit("north", theaterSoundBooth);
        
        theaterBackstage.setExit("east", theater);
        theaterBackstage.setExit("north", theaterDressRoom);
        
        theaterDressRoom.setExit("east", theaterSoundBooth);
        theaterDressRoom.setExit("south", theaterBackstage);
        
        theaterSoundBooth.setExit("west", theaterDressRoom);
        theaterSoundBooth.setExit("south", theater);

        
        
        pub.setExit("east", pubKitchen);
        pub.setExit("west", outside);
        
        pubKitchen.setExit("west", pub);
        pubKitchen.setExit("south", pubManagerOffice);
        
        pubManagerOffice.setExit("north", pubKitchen);
        
        

        complab.setExit("north", outside);
        complab.setExit("east", complabAdmin);
        complab.setExit("south", complabITDep);

        
        
        complabAdmin.setExit("east", complab);
        
        
        
        complabITDep.setExit("north", complab);
        
        
        
        courtyard.setExit("north", basketballCourt);
        courtyard.setExit("south", outside);
        courtyard.setExit("west", weightRoom);
        courtyard.setExit("east", tennisCourt);
        
        
        
        basketballCourt.setExit("south", courtyard);
        basketballCourt.setExit("north", frontGate);
        
        //. . . . . . . . . 

        currentRoom = outside;  // start game outside
        player.setRoom(currentRoom);
        
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
        System.out.println("Welcome to my escape room text adventure!");
        System.out.println("You will need to escape a locked gated University campus.");
        System.out.println("Each building will have collectibles and ");
        System.out.println("one keycard that will open the front main gate to escape.");
        System.out.println("Collecitble items and the keycard will be randomized");
        System.out.println("Find as many collectibles as you of the highest value.");
        System.out.println("Type '" + CommandWord.HELP + "' to see your list of commands.");
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
                System.out.println("I dont know what that means...");
                break;

            case HELP:
                printHelp();
                System.out.println();
                break;

            case GO:
                goRoom(command);
                System.out.println();
                break;
                
            case BACK:
                goBack();
                System.out.println();
                break;

            case QUITGAME:
                wantToQuit = quit(command);
                System.out.println();
                break;
                
            case LOOK:
                look();
                System.out.println();
                break;
                
            case INVENTORY:
                player.displayInventory();
                System.out.println();
                break;
                
            case TAKE:
                take(command);
                System.out.println();
                break;
                
            
                
            
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
            previousRooms.push(currentRoom);
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
            currentRoom.displayItemsInRoom();
            player.setRoom(currentRoom);
        }
    }
    
    /**
     * This method is NOT used. Was a product of Exercise 8.23
     */
    private void goBackOne(Command command) 
    {
        if (lastDirection.equals("north")){
            currentRoom = currentRoom.getExit("south");
            lastDirection = "south";
            System.out.println(currentRoom.getLongDescription());
            currentRoom.displayItemsInRoom();
            
        }
        else if (lastDirection.equals("south")) {                 
            currentRoom = currentRoom.getExit("north");
            lastDirection = "north";
            System.out.println(currentRoom.getLongDescription());
            currentRoom.displayItemsInRoom();
            
        }
        else if (lastDirection.equals("west")) {
            currentRoom = currentRoom.getExit("east");
            lastDirection = "east";
            System.out.println(currentRoom.getLongDescription());
            currentRoom.displayItemsInRoom();
            
        } 
        else if (lastDirection.equals("east")) {
            currentRoom = currentRoom.getExit("west");
            lastDirection = "west";
            System.out.println(currentRoom.getLongDescription());
            currentRoom.displayItemsInRoom();
            
        }
    }
    
    private void goBack(){
        if(previousRooms.size() == 0){
            System.out.println("You are at the starting area");
        } else{
            currentRoom = previousRooms.pop();
            System.out.println(currentRoom.getLongDescription());
            currentRoom.displayItemsInRoom();
        }
        
    }
    
    /**
     * This method takes an item from the currentRoom
     */
    private void take(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Take what?");
            return;
        }

        String item = command.getSecondWord();

        // Try to leave current room.
        String takenItem = currentRoom.takeRoomItem(item);

        if (takenItem.length() == 0) {
            System.out.println("That item is not in the room!");
        }
        else {
            player.setInventory(takenItem);
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
