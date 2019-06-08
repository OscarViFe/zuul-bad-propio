import java.util.HashMap;
import java.util.Set;
import java.util.ArrayList;

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
    private HashMap<String, Room> habitaciones;
    private ArrayList<Item> objetos;

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
        objetos = new ArrayList<Item>();
    }

    /**
     * Metodo para introducir las puertas y posiciones de las mismas dentro de la habitacion.
     * @param direccion La direccion en la que esta la puerta.
     * @param habitacion La habitacion con la que conecta esa puerta.
     */
    public void addExit(String direccion, Room habitacion) 
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

    /**
     * Devuelve un texto con la descripcion larga de la habitacion del tipo:
     *     You are in the 'name of room'
     *     Exits: north west southwest
     * @return Una descripcion de la habitacion incluyendo sus salidas
     */
    public String getLongDescription(){
        String texto = "Estas en un habitacion " + description + "\n";
        if(!objetos.isEmpty()){
            texto += "En esta habitacion hay:";
            for(Item objeto : objetos){
                texto += " " + objeto.toString() + ",";
            }
            texto = texto.substring(0, texto.length() - 1);
        }
        else{
            texto += "En esta habitacion no hay nada.";
        }
        texto += "\n" + getExitString();
        return texto;
    }

    /**
     * Metodo para añadir objetos a la habitacion
     * @param Un objeto de la clase Item
     */
    public void addItem(Item objeto){
        objetos.add(objeto);
    }
    
    /**
     * Da al usuario el objeto y lo elimina de la sala
     * @param String texto contenido dentro de la descripcion del objeto
     */
    public Item cogerObjeto(String objetoACoger){
        Item objetoADevolver = null;
        int contador = 0;
        while(objetoADevolver == null && contador < objetos.size()){
            if(objetos.get(contador).getId().equals(objetoACoger)){
                objetoADevolver = objetos.get(contador);
                objetos.remove(contador);
            }
            contador ++;
        }
        return objetoADevolver;
    }
}
