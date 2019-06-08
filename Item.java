
public class Item
{
    private String id;
    private String descripcion;
    private int peso;
    
    /**
     * @param String Descripcion del objeto
     * @param int Peso del objeto
     */
    public Item(String id, String descripcion, int peso){
        this.id = id;
        this. descripcion = descripcion;
        this.peso = peso;
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
}
