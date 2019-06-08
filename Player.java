import java.util.Stack;
import java.util.ArrayList;

public class Player{

    private Room currentRoom;
    private Stack<Room> habitacionesPasadas;
    private ArrayList<Item> objetosJugador;

    public Player(Room habitacionInicial){
        currentRoom = habitacionInicial;
        habitacionesPasadas = new Stack<Room>();
        objetosJugador = new ArrayList<Item>();
    }

    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    public void goRoom(Command command) 
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
            habitacionesPasadas.push(currentRoom);
            currentRoom = nextRoom;

        }
    }

    /**
     * Permite volver a la habitacion anterior 
     */
    public void back() {
        if(!habitacionesPasadas.empty()){
            currentRoom = habitacionesPasadas.pop();
            look();
        }
        else{
            System.out.println("No puedes retroceder, ya estas en el inicio, prueba con un go");
            System.out.println();
        }
    }

    /**
     * Permite volver a mostrar la descripcion y las salidas de la sala actual
     */
    public void look() {   
        System.out.println(currentRoom.getLongDescription());
        System.out.println();
    }

    /**
     * El usuario coge el objeto y se elimina de la sala
     * @param Command comando para saber el dato del objeto a coger
     */
    public void take(Command command){
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Take what?");
            return;
        }

        Item objetoRecogido = currentRoom.cogerObjeto(command.getSecondWord());
        if(objetoRecogido == null){
            System.out.println("El objeto que buscas no existe");
            System.out.println();
        }
        else{
            if(objetoRecogido.sePuedeCoger()){
                objetosJugador.add(objetoRecogido);
                System.out.println("Has recogido " + objetoRecogido.toString());
                System.out.println();
            }
            else{
                currentRoom.addItem(objetoRecogido);
                System.out.println("No puedes coger ese objeto");
                System.out.println();
            }
        }
    }

    /**
     * Añade el objeto a la sala y lo elimina del inventario del usuario
     * @param Command comando para saber el dato del objeto a soltar
     */
    public void drop(Command command){
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Drop what?");
            return;
        }
        else{
            int contador = 0;
            boolean encontrado = false;
            while(!encontrado && contador < objetosJugador.size()){
                if(objetosJugador.get(contador).getDescripcion().contains(command.getSecondWord())){
                    encontrado = true;
                    contador --;
                }
                contador ++;
            }
            if(!encontrado){
                System.out.println("No tienes ese objeto en el inventario");
                System.out.println();
            }
            else{
                System.out.println("Has soltado " + objetosJugador.get(contador).toString());
                System.out.println();
                currentRoom.addItem(objetosJugador.get(contador));
                objetosJugador.remove(contador);
            }
        }
    }

    /**
     * Permite comer
     */
    public void eat() {   
        System.out.println("You have eaten now and you are not hungry any more");
        System.out.println();
    }

    /**
     * Muestra los objetos que tiene el usuario en el inventario
     */
    public void items(){
        String texto = "";
        if(!objetosJugador.isEmpty()){
            texto += "Tienes estos objetos:";
            for(Item objeto : objetosJugador){
                texto += " " + objeto.toString() + ",";
            }
            texto = texto.substring(0, texto.length() - 1);
        }
        else{
            texto += "No tienes ningun objeto.";
        }
        texto += "\n";
        System.out.println(texto);
    }   
}
