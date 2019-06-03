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
 * @author  Michael KÃ¶lling and David J. Barnes
 * @version 2011.07.31
 */
public class Room 
{
    private String description;
    HashMap<String, Room> habitaciones;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        habitaciones = new HashMap<>();
    }

    /**
     * Define the exits of this room.  Every direction either leads
     * to another room or is null (no exit there).
     * @param north The north exit.
     * @param east The east east.
     * @param south The south exit.
     * @param west The west exit.
     */
    public void setExits(Room north, Room east, Room south, Room west, Room southeast, Room northEast) 
    {
        if(north != null)
            habitaciones.put("north", north);
        if(east != null)
            habitaciones.put("east", east);
        if(south != null)
            habitaciones.put("south", south);
        if(west != null)
            habitaciones.put("west", west);
        if(southeast != null)
            habitaciones.put("southeast", southeast);
        if(northEast != null)
            habitaciones.put("northEast", northEast);
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
        return habitaciones.get(direccion);
    }

    /**
     * Devuelve la información de las salidas existentes
     * Por ejemplo: "Exits: north east west"
     *
     * @return Una descripción de las salidas existentes.
     */
    public String getExitString(){
        String salidas = "Exits: ";
        if(habitaciones.get("north") != null) {
            salidas += "north ";
        }
        if(habitaciones.get("east") != null) {
            salidas += "east ";
        }
        if(habitaciones.get("south") != null) {
            salidas += "south ";
        }
        if(habitaciones.get("west") != null) {
            salidas += "west ";
        }
        if(habitaciones.get("southEast") != null) {
            salidas += "southEast ";
        }
        if(habitaciones.get("northEast") != null){
            salidas += "northEast ";
        }
        return salidas;
    }

}
