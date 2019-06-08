
public class Item
{
    private String id;
    private String descripcion;
    private int peso;
    private boolean coger;
    
    /**
     * @param String Descripcion del objeto
     * @param int Peso del objeto
     * @param Boolean Si se puede coger el objeto o no
     */
    public Item(String id, String descripcion, int peso, Boolean coger){
        this.id = id;
        this. descripcion = descripcion;
        this.peso = peso;
        this.coger = coger;
    }
    
    /**
     * @return String Datos del objeto
     */
    public String toString(){
        return descripcion + " de " + peso +  " gramos" + "(" + id + ")";
    }
    
    /**
     * @return La descripcion del objeto
     */
    public String getDescripcion(){
        return descripcion;
    }
    
    /**
     * @return  el peso del objeto
     */
    public int getPeso(){
        return peso;
    }
    
    /**
     * @return  el id del objeto
     */
    public String getId(){
        return id;
    }
    
    public boolean sePuedeCoger(){
        return coger;
    }
}
