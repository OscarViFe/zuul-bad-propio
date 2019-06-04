
public class Item
{
    private String descripcion;
    private int peso;
    
    public Item(String descripcion, int peso){
        this. descripcion = descripcion;
        this.peso = peso;
    }
    
    public String toString(){
        return descripcion + " de " + peso +  " gramos.";
    }
}
