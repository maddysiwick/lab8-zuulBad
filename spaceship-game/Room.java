import java.util.HashMap;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 7.0
 */
public class Room 
{
    private String description;
    private HashMap<String,Room> exits;

    /**
     * Create a room described "description". Initially, it has no exits. 
     * "description" is something like "a kitchen" or "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        exits=new HashMap<>();
    }

    /**
     * Define the exits of this room.  Every direction either leads
     * to another room or is null (no exit there).
     * @param north The north exit.
     * @param east The east east.
     * @param south The south exit.
     * @param west The west exit.
     */
    //8
    public void setExit(String direction, Room neighbour) 
    {
        exits.put(direction,neighbour);
    }

    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }
    //7
    public Room getExit(String direction){
        for(String exit : exits.keySet()){
            if(exit.equals(direction)){
                return exits.get(exit);
            }
        }
        return null;
    }
    //10
    public String getExitString(){
        String exitString = "";
        for(String exit : exits.keySet()){
            exitString+=exit+" ";
        }
        return exitString;
    }
    //11
    public String getLongDescription(){
        return "You are " +getDescription()+ "\n" + "Exits: " + getExitString();
    }
}
