package inventario;

import java.util.List;

public class Categoria {
    private int idCategoria;
    private String nombreCategoria;
    private int capacidadPersonas;
    private double pctg_temporadaAlta;
    private double pctg_temporadaBaja;
    private int costoAveriaLeve;
    private int costoAveriaModerada;
    private int costoAveriaTotal;
    private int tarifaDiaria;
    private int id_Padre;
    private Categoria Padre;
    
    public Categoria(int id,String nombreCategoria, int capacidadPersonas,double pctg_temporadaAlta, double pctg_temporadaBaja,
                     int costoAveriaLeve, int costoAveriaModerada, int costoAveriaTotal, int tarifaDiaria,int id_Padre) {
                        this.idCategoria=id;
                        this.nombreCategoria = nombreCategoria;
                        this.capacidadPersonas= capacidadPersonas;
                        this.pctg_temporadaAlta = pctg_temporadaAlta;
                        this.pctg_temporadaBaja = pctg_temporadaBaja;
                        this.costoAveriaLeve = costoAveriaLeve;
                        this.costoAveriaModerada = costoAveriaModerada;
                        this.costoAveriaTotal = costoAveriaTotal;
                        this.tarifaDiaria = tarifaDiaria;
                        this.id_Padre=id_Padre;
                        this.Padre=null;
                     }
    
    public int getID() {
        return this.idCategoria;
    }

    public String getnombreCategoria() {
        return this.nombreCategoria;
    }
    public int getCapacidad() {
        return this.capacidadPersonas;
    }

    public double getPctg_temporadaAlta() {
        return this.pctg_temporadaAlta;
    }

    public double getPctg_temporadaBaja() {
        return this.pctg_temporadaBaja;
    }

    public int getCostoAveriaLeve() {
        return this.costoAveriaLeve;
    }

    public int getCostoAveriaModerada() {
        return this.costoAveriaModerada;
    }

    public int getCostoAveriaTotal() {
        return this.costoAveriaTotal;
    }

    public int getTarifaDiaria() {
        return this.tarifaDiaria;
    }

    public int getId_Padre() {
        return this.id_Padre;
    }
    public Categoria getPadre(Categoria i) {
        if (i.getId_Padre()!=0){
        return i.Padre;
    }
    else{
        return i;
    }
    }
    public void setID(int id){
        this.idCategoria=id;
    }
    public void setPctg_temporadaAlta(int fechaInicio, int fechaFin) {
        this.pctg_temporadaAlta = fechaInicio;
    }
    

    public void setPctg_temporadaBaja(int fechaInicio, int fechaFin) {
        this.pctg_temporadaBaja = fechaFin;
    }

    public void setCostoAveriaLeve(int costoALeve) {
        this.costoAveriaLeve = costoALeve;
    }

    public void setCostoAveriaModerada(int costoAModerada) {
        this.costoAveriaModerada = costoAModerada;
    }

    public void setCostoAveriaTotal(int costoATotal) {
        this.costoAveriaTotal = costoATotal;
    }

    public void setTarifaDiaria(int costoTarifa) {
        this.tarifaDiaria = costoTarifa;
    }

    public void setId_Padre(int id_Padre) {
        this.id_Padre=id_Padre;
    }
    
    public void setPadre(Categoria Padre) {
        this.Padre=Padre;
    }
    public void eliminarCategoria(){
        List<Categoria> categorias_inventario= Inventario.getListaCategorias();
        int idEliminar=this.getID();
        for (int i = 0; i < categorias_inventario.size(); i++){
            Categoria i_categoria= categorias_inventario.get(i);
            if (i_categoria.getID()==(idEliminar)){
                categorias_inventario.remove(i);
                break;
            }
        }
    }
}
