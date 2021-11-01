import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.ListIterator;

/**
 * Class Room - a room in the escape room game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  For each existing exit, the room 
 * stores a reference to the neighboring room.
 * 
 * @author  Zachary Harris
 * @version 10.30.21
 */

public class Room 
{
    private String description;
    private HashMap<String, Room> exits;   // stores exits of this room.
    private ArrayList<Item> roomItems;     //will be used to set the items in a room.
    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<>();
        roomItems = new ArrayList<Item>();
    }

    /**
     * This method allows an item to be added to a room.
     * @param items - an arraylist to store items to put into a room.
     */
    public void addRoomItems(ArrayList<Item> items){
        roomItems = items;
    }
    
    /**
     * This method displays the items in the current room
     */
    public void displayItemsInRoom(){
        System.out.println("Items in the room: " + roomItems.toString());
    }
    
    /**
     * This methods takes a specified room item by the user.
     * @param item - the item being taken.
     * @return tempItem - the item being taken, instance of Item
     */
    public Item takeRoomItem(String item){
        ListIterator<Item> iterator = roomItems.listIterator();
        while(iterator.hasNext()){
            Item tempItem = iterator.next();
            if(item.equals(tempItem.getDescription())){
                return tempItem;
            }
        }
        return null;
    }
    
    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor  The room to which the exit leads.
     */
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }

    /**
     * @return The short description of the room
     * (the one that was defined in the constructor).
     */
    public String getShortDescription()
    {
        return description;
    }

    /**
     * Return a description of the room in the form:
     *     You are in the kitchen.
     *     Exits: north west
     * @return A long description of this room
     */
    public String getLongDescription()
    {
        return "You are " + description + ".\n" + getExitString();
    }

    /**
     * Return a string describing the room's exits, for example
     * "Exits: north west".
     * @return Details of the room's exits.
     */
    private String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     * @param direction The exit's direction.
     * @return The room in the given direction.
     */
    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }
}

