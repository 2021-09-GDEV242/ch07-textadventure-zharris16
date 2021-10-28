import java.util.ArrayList;
/**
 * Write a description of class Player here.
 *
 * @author Zachary Harris
 * @version 10/27/21
 */

public class Player
{
    // instance variables - replace the example below with your own
    private String playerName;
    private Room playerCurrentRoom;
    private ArrayList<String> inventory;
    /**
     * Constructor for objects of class Player
     */
    public Player(String name)
    {
        playerName = name;
        inventory = new ArrayList<String>();
    }
    
    public void setRoom(Room room){
        playerCurrentRoom = room;
    }
    
    public Room getRoom(){
        return playerCurrentRoom;
    }
    
    public void setInventory(String item){
        inventory.add(item);
    }
    
    public void displayInventory(){
        System.out.println("Player Inventory: " + inventory);
    }
    
    public ArrayList<String> getInventory(){
        return inventory;
    }

}
