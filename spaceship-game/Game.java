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
 * @author  Michael Kölling and David J. Barnes
 * @version 7.0
 */
public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private Room finalRoom;
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room hull, control, storage, engine, sleeping, mess;
      
        // create the rooms
        hull = new Room("""
        in the hull of the Argo. There is an enormous window in front of you.
        You feel like you can see the entire universe from here.
        """);
        control = new Room("""
        in the control room. There are switches and maps everywhere to set a
        course for the Argo. None of it works, there is a problem with the engine.
        """);
        storage = new Room("in the storage room. There are crates of tools and rations everywhere.");
        engine = new Room("""
        in the engine room. The problem with the engine is obvious to you.You 
        recieved extensive training before being allowed to man the Argo alone. You 
        fix it without much difficulty
        """);
        sleeping = new Room("""
        in the sleeping quarters. It looks like every other room with the exception of
        the cocoon-like sleeping bag strapped to the wall.
        """);
        mess = new Room("""
        in the mess hall. It is more of a closet with a few cabinets of freeze-dried
        food and a water jug.
        """);
        //8
        // initialise room exits
        hull.setExit("south",control);
        control.setExit("north",hull);
        control.setExit("east",sleeping);
        control.setExit("south",mess);
        control.setExit("west",storage);
        storage.setExit("north",engine);
        storage.setExit("east",control);
        sleeping.setExit("west", control);
        mess.setExit("north",control);

        // start game outside
        currentRoom = control;
        finalRoom = engine;
    }

    /**
     *  Main play routine. Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
        
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = (processCommand(command)||checkWin())? true:false;
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the Argo!");
        System.out.println("Argo is a new, incredibly boring space adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        printLocationInfo();
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            goRoom(command);
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }
        //14
        else if (commandWord.equals("look")){
            printLocationInfo();
        }
        else if(commandWord.equals("eat")){
            System.out.println("""
            You've already eaten too much freeze-dried paste today. You aren't 
            hungry anymore.
            """);
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
        System.out.println("You are alone. You wander around at the ship.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.getCommandWords().showAll(); //16
    }

    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();
        System.out.println(direction);
        // Try to leave current room.
        Room nextRoom;
        nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            currentRoom = nextRoom;
            printLocationInfo();
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
            // signal that we want to quit
            return true;  
        }
    }
    private boolean checkWin(){
        if (currentRoom==finalRoom){
            return true;
        }
        return false;
    }
    //5
    private void printLocationInfo(){
        System.out.printf(currentRoom.getLongDescription());
        System.out.println();
    }
}
