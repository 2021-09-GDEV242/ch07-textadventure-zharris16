import java.util.ArrayList;
import java.util.ListIterator;

/**
 * This class is part of the "Escape Room" application.
 * 
 * This class holds all player attriubutes and functions.
 *
 * @author Zachary Harris
 * @version 10/27/21
 */

public class Player
{
    // instance variables - replace the example below with your own
    private String playerName;
    private Room playerCurrentRoom;
    private ArrayList<Item> inventory;
    /**
     * Constructor for objects of class Player
     * @param name - name of player
     */
    public Player(String name)
    {
        playerName = name;
        inventory = new ArrayList<Item>();
    }
    
    /**
     * This method sets the current room for the player.
     * @param room - room to be set
     */
    public void setRoom(Room room){
        playerCurrentRoom = room;
    }
    
    /**
     * This method returns the current room of the player.
     * @return Room - returns the player current room.
     */
    public Room getRoom(){
        return playerCurrentRoom;
    }
    
    /**
     * This method adds the item to the players inventory.
     * @param item - the item being taken and stored in the inventory.
     */
    public void addToInventory(Item item){
        inventory.add(item);
    }
    
    /**
     * This method displays the player inventory.
     */
    public void displayInventory(){
        System.out.println("Player Inventory: " + inventory);
    }
    
    /**
     * This method returns the players current inventory.
     * @return - array list of player inventory items
     */
    public ArrayList<Item> getInventory(){
        return inventory;
    }
    
    /**
     * This method gets a specific inventory item.
     * @param item - the item being read from the player inventory.
     * @return Item - instance of Item class
     */
    public Item getInventoryItem(String item){
        ListIterator<Item> iterator = inventory.listIterator();
        while(iterator.hasNext()){
            Item tempItem = iterator.next();
            if(item.equals(tempItem.getDescription())){
                return tempItem;
            }
        }
        return null;
    }

}
