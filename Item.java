
/**
 * This class is part of the "Escape Room" application.
 *
 * This class holds all attributes of each item in the game.
 * 
 * @author Zachary Harris
 * @version 10/31/21
 */
public class Item
{
    // instance variables - replace the example below with your own
    private String itemDescription;
    private int itemValue;
    private int itemWeight;

    /**
     * Constructor for objects of class Item
     * @param itemDescription item details
     * @param itemValue item value
     * @param itemWeight item weight.
     */
    public Item(String itemDescription, int itemValue, int itemWeight)
    {
        this.itemDescription = itemDescription;
        this.itemValue = itemValue;
        this.itemWeight = itemWeight;
    }

    /**
     * This method returns the full description of each item.
     * @return String: returns a string of the details of each item.
     */
    public String toString()
    {
        return "\n\t\tDescription: " + itemDescription + 
                "\n\t\tWeight: " + itemWeight +
                "\n\t\tValue: " + itemValue + "\n";
    }
    
    /**
     * This method gets the items name.
     * @return String: returns the name of the item.
     */
    public String getDescription(){
        return itemDescription;
    }
    
    /**
     * This method gets the value of an item.
     * @return int: returns the value of the item
     */
    public int getValue()
    {
        return itemValue;
    }
    
    /**
     * This method gets the weight of an item.
     * @return int: returns the weight of the item.
     */
    public int getWeight()
    {
        return itemWeight;
    }
    
}
