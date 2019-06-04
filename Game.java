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
 * @version 2011.07.31
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;

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
        Room salaInicial, salaMaterialLaboratorio, salaTanquePrincipal, salaOrdenadores, salaContencionPequena;
        
        //Objeto para la sala del tanque principal que consistira en una escama rosada de 5g
        Item objeto = new Item("una escama rosada", 5);
        // create the rooms
        salaInicial = new Room("completamente vacia, en silencio, lo unico que ves es una luz de emergencia indicando que algo ha sucedido", null);
        salaMaterialLaboratorio = new Room("con material de laboratorio con cristales rotos por todas " + 
            "partes y unas muestras de color rosado en una estanteria de cristal", null);
        salaTanquePrincipal = new Room("con un tanque enorme roto cuyos cristales de gran grosor rotos estan esparcidos por el suelo ", objeto);
        salaOrdenadores = new Room("llena de ordenadores, uno de ellos esta encendido y pone" + 
            " 'Dia 42: Esto esta fuera del limite de nuestra comprension, vamos a morir todos'", null);
        salaContencionPequena = new Room("amplia con tanques de contencion del tama�o de una persona abiertos sin visibilidad de haber sido forzados", null);

        // initialise room exits (Room north, Room east, Room south, Room west, Room southeast, Room northEast) 
        //Puertas salaInicial
        salaInicial.addExit("north", salaMaterialLaboratorio);
        salaInicial.addExit("east", salaTanquePrincipal);
        salaInicial.addExit("northEast", salaTanquePrincipal);
        //Puertas salaMaterialLaboratorio
        salaMaterialLaboratorio.addExit("east", salaTanquePrincipal);
        salaMaterialLaboratorio.addExit("south", salaInicial);
        salaMaterialLaboratorio.addExit("southeast", salaOrdenadores);
        //Puertas salaTanquePrincipal
        salaTanquePrincipal.addExit("east", salaContencionPequena);
        salaTanquePrincipal.addExit("south", salaOrdenadores);
        salaTanquePrincipal.addExit("west", salaMaterialLaboratorio);
        salaTanquePrincipal.addExit("southwest", salaInicial);
        //Puertas salaOrdenadores
        salaOrdenadores.addExit("north", salaTanquePrincipal);
        salaOrdenadores.addExit("west", salaInicial);
        //Puertas salaContencionPequena
        salaContencionPequena.addExit("west", salaTanquePrincipal);

        currentRoom = salaInicial;  // start game outside
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
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        printLocationInfo();
        System.out.println();
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
        else if(commandWord.equals("look")){
            look();
        }
        else if(commandWord.equals("eat")){
            eat();
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
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        System.out.println(parser.showCommands());
        System.out.println();
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

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

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
            return true;  // signal that we want to quit
        }
    }

    /**
     * Nos muestra los datos de la sala actual y las salas a las que podemos acceder desde la actual
     */
    private void printLocationInfo(){
        System.out.println(currentRoom.getLongDescription());
        System.out.println();
    }

    /**
     * Permite volver a mostrar la descripcion y las salidas de la sala actual
     */
    private void look() {   
        System.out.println(currentRoom.getLongDescription());
        System.out.println();
    }

    /**
     * Permite comer
     */
    private void eat() {   
        System.out.println("You have eaten now and you are not hungry any more");
        System.out.println();
    }
}
