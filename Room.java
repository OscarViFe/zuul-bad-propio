import java.util.HashMap;
import java.util.Set;

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
     * Metodo para introducir las puertas y posiciones de las mismas dentro de la habitacion.
     * @param direccion La direccion en la que esta la puerta.
     * @param habitacion La habitacion con la que conecta esa puerta.
     */
    public void addRoom(String direccion, Room habitacion) 
    {
        habitaciones.put(direccion, habitacion);
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
        Set<String> puertas = habitaciones.keySet();
        for(String puerta: puertas){
            salidas += puerta + " ";
        }
        return salidas;
    }

}
