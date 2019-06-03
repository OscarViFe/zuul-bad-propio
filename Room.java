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
 * @author  Michael KÃ¶lling and David J. Barnes
 * @version 2011.07.31
 */
public class Room 
{
    private String description;
    private Room northExit;
    private Room southExit;
    private Room eastExit;
    private Room westExit;
    private Room southEastExit;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
    }

    /**
     * Define the exits of this room.  Every direction either leads
     * to another room or is null (no exit there).
     * @param north The north exit.
     * @param east The east east.
     * @param south The south exit.
     * @param west The west exit.
     */
    public void setExits(Room north, Room east, Room south, Room west, Room southeast) 
    {
        if(north != null)
            northExit = north;
        if(east != null)
            eastExit = east;
        if(south != null)
            southExit = south;
        if(west != null)
            westExit = west;
        if(southeast != null)
            southEastExit = southeast;
    }

    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * Devuelve la habitacion que esta en esa direccion
     *  @param String direccion
     *  @return Room La habitacion en esa direccion
     */
    public Room getExit(String direccion){
        Room direccionBuscada = null;
        if(direccion.equals("north")) {
            direccionBuscada = northExit;
        }
        if(direccion.equals("east")) {
            direccionBuscada = eastExit;
        }
        if(direccion.equals("south")) {
            direccionBuscada = southExit;
        }
        if(direccion.equals("west")) {
            direccionBuscada = westExit;
        }
        if(direccion.equals("southEast")){
            direccionBuscada = southEastExit;
        }
        return direccionBuscada;
    }

    /**
     * Devuelve la información de las salidas existentes
     * Por ejemplo: "Exits: north east west"
     *
     * @return Una descripción de las salidas existentes.
     */
    public String getExitString(){
        String salidas = "Exits: ";
        if(northExit != null) {
            salidas += "north ";
        }
        if(eastExit != null) {
            salidas += "east ";
        }
        if(southExit != null) {
            salidas += "south ";
        }
        if(westExit != null) {
            salidas += "west ";
        }
        if(southEastExit != null) {
            salidas += "southEast ";
        }
        return salidas;
    }

}
