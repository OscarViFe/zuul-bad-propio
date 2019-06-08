
public class Item
{
    private String descripcion;
    private int peso;
    
    /**
     * @param String Descripcion del objeto
     * @param int Peso del objeto
     */
    public Item(String descripcion, int peso){
        this. descripcion = descripcion;
        this.peso = peso;
    }
    
    /**
     * @return String Datos del objeto
     */
    public String toString(){
        return descripcion + " de " + peso +  " gramos";
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
}
