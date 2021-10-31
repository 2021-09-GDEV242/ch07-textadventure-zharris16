
/**
 * This class stores the 'items' that can be collected in game.
 *
 * @author Zachary Harris
 * @version 10/26/21
 */

import java.util.Random;
import java.util.ArrayList;

public class Items
{
    // instance variables - replace the example below with your own
    
    private String[] itemArray;
    private Random rand;
    /**
     * Constructor for objects of class Items
     */
    public Items()
    {
        itemArray = new String[] {"Book", "Letter", "Matches", "Lighter", "Pen", "Jacket",
                                  "Keycard", "Flashlight", "Textbook", "IDPic", "Lunchbox",
                                  "Toolbox", "Labtop"};
        rand = new Random();
    }

    /**
     * This method allows randomizes the items that can be found in each room.
     */
    public ArrayList<String> getRandomItems(int numItems)
    {
        ArrayList<String> items = new ArrayList<String>();
        for(int i = 0; i < numItems; i++){
            items.add(itemArray[rand.nextInt(itemArray.length)]);
        }
        return items;
         
    }
}
