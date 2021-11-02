
/**
 * This class generates the 'items' that can be collected in game.
 *
 * @author Zachary Harris
 * @version 10/26/21
 */

import java.util.Random;
import java.util.ArrayList;

public class Items
{
    // instance variables - replace the example below with your own

    private Item[] itemArray;
    private Random rand;
    /**
     * Constructor for objects of class Items
     */
    public Items()
    {
        //itemArray = new Item[] {"Book", "Letter", "Matches", "Lighter", "Pen", "Jacket",
        //"Keycard", "Flashlight", "Textbook", "IDPic", "Lunchbox",
        //"Toolbox", "Labtop"};
        itemArray = new Item[10];
        itemArray[0] = new Item("Book", 10, 5);
        itemArray[1] = new Item("Letter", 5, 1);
        itemArray[2] = new Item("Matches", 15, 2);
        itemArray[3] = new Item("Lighter", 30, 3);
        itemArray[4] = new Item("Pen", 20, 2);
        itemArray[5] = new Item("Jacket", 10, 7);
        itemArray[6] = new Item("Flashlight", 35, 8);
        itemArray[7] = new Item("Keycard", 50, 1);
        itemArray[8] = new Item("Textbook", 20, 10);
        itemArray[9] = new Item("Lunchbox", 10, 10);
        rand = new Random();
    }

    /**
     * This method allows randomizes the items that can be found in each room.
     * @param numItems - number of game items that spawn in each room.
     * @return - an array of random items.
     */
    public ArrayList<Item> getRandomItems(int numItems)
    {
        ArrayList<Item> items = new ArrayList<Item>();
        for(int i = 0; i < numItems; i++){
            items.add(itemArray[rand.nextInt(itemArray.length)]);
        }
        return items;

    }

}
