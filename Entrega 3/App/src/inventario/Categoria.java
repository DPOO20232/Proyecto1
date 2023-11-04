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
    private static int lastId;
    public Categoria(int id,String nombreCategoria, int capacidadPersonas,double pctg_temporadaAlta, double pctg_temporadaBaja,
                     int costoAveriaLeve, int costoAveriaModerada, int costoAveriaTotal, int tarifaDiaria,int id_Padre) {
        /**
         * Constructor de la clase `Categoria` que inicializa una categoría con los parámetros proporcionados.
         *
         * @param id El ID de la categoría.
         * @param nombreCategoria El nombre de la categoría.
         * @param capacidadPersonas La capacidad de personas que puede albergar esta categoría.
         * @param pctg_temporadaAlta El porcentaje de tarifa para temporada alta.
         * @param pctg_temporadaBaja El porcentaje de tarifa para temporada baja.
         * @param costoAveriaLeve El costo de avería leve.
         * @param costoAveriaModerada El costo de avería moderada.
         * @param costoAveriaTotal El costo de avería total.
         * @param tarifaDiaria La tarifa diaria de la categoría.
         * @param id_Padre El ID de la categoría padre.
         */
                        this.idCategoria=id;
                        if (id>lastId){lastId=id;}
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
    public Categoria(String nombreCategoria, int capacidadPersonas,double pctg_temporadaAlta, double pctg_temporadaBaja,
                     int costoAveriaLeve, int costoAveriaModerada, int costoAveriaTotal, int tarifaDiaria,int id_Padre) {
                        /**
         * Constructor de la clase `Categoria` que inicializa una categoría con los parámetros proporcionados, generando un ID automático.
         *
         * @param nombreCategoria El nombre de la categoría.
         * @param capacidadPersonas La capacidad de personas que puede albergar esta categoría.
         * @param pctg_temporadaAlta El porcentaje de tarifa para temporada alta.
         * @param pctg_temporadaBaja El porcentaje de tarifa para temporada baja.
         * @param costoAveriaLeve El costo de avería leve.
         * @param costoAveriaModerada El costo de avería moderada.
         * @param costoAveriaTotal El costo de avería total.
         * @param tarifaDiaria La tarifa diaria de la categoría.
         * @param id_Padre El ID de la categoría padre.
         */
                        this.setID();
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
        /**
         * Obtiene el ID de la categoría.
         *
         * @return El ID de la categoría.
         */
        return this.idCategoria;
    }

    public String getnombreCategoria() {
        /**
         * Obtiene el nombre de la categoría.
         *
         * @return El nombre de la categoría.
         */
        return this.nombreCategoria;
    }
    public int getCapacidad() {
        /**
         * Obtiene la capacidad de personas que puede albergar esta categoría.
         *
         * @return La capacidad de personas de la categoría.
         */
        return this.capacidadPersonas;
    }

    public double getPctg_temporadaAlta() {
        /**
         * Obtiene el porcentaje de tarifa para temporada alta de la categoría.
         *
         * @return El porcentaje de tarifa para temporada alta.
         */
        return this.pctg_temporadaAlta;
    }

    public double getPctg_temporadaBaja() {
        /**
         * Obtiene el porcentaje de tarifa para temporada baja de la categoría.
         *
         * @return El porcentaje de tarifa para temporada baja.
         */
        return this.pctg_temporadaBaja;
    }

    public int getCostoAveriaLeve() {
        /**
         * Obtiene el costo de avería leve de la categoría.
         *
         * @return El costo de avería leve.
         */
        return this.costoAveriaLeve;
    }

    public int getCostoAveriaModerada() {
        /**
         * Obtiene el costo de avería moderada de la categoría.
         *
         * @return El costo de avería moderada.
         */
        return this.costoAveriaModerada;
    }

    public int getCostoAveriaTotal() {
        /**
         * Obtiene el costo de avería total de la categoría.
         *
         * @return El costo de avería total.
         */
        return this.costoAveriaTotal;
    }

    public int getTarifaDiaria() {
        /**
         * Obtiene la tarifa diaria de la categoría.
         *
         * @return La tarifa diaria de la categoría.
         */
        return this.tarifaDiaria;
    }

    public int getId_Padre() {
        /**
         * Obtiene el ID de la categoría padre.
         *
         * @return El ID de la categoría padre.
         */
        return this.id_Padre;
    }
    public Categoria getPadre(Categoria i) {
        /**
         * Obtiene la categoría padre de la categoría actual.
         *
         * @param i La categoría actual.
         * @return La categoría padre de la categoría actual o la categoría misma si no tiene padre.
         */
        if (i.getId_Padre()!=0){
        return i.Padre;
            }
        else{
            return i;
            }
    }
    public void setID(){
        /**
         * Establece el ID de la categoría, incrementando el valor del último ID.
         */
        this.idCategoria=lastId+=1;
        lastId=this.getID();

    }
    public void setPctg_temporadaAlta(int fechaInicio, int fechaFin) {
        /**
         * Establece el porcentaje de tarifa para temporada alta de la categoría.
         *
         * @param fechaInicio La fecha de inicio de la temporada alta.
         * @param fechaFin La fecha de fin de la temporada alta.
         */
        this.pctg_temporadaAlta = fechaInicio;
    }
    

    public void setPctg_temporadaBaja(int fechaInicio, int fechaFin) {
        /**
         * Establece el porcentaje de tarifa para temporada baja de la categoría.
         *
         * @param fechaInicio La fecha de inicio de la temporada baja.
         * @param fechaFin La fecha de fin de la temporada baja.
         */
        this.pctg_temporadaBaja = fechaFin;
    }

    public void setCostoAveriaLeve(int costoALeve) {
        /**
         * Establece el costo de avería leve de la categoría.
         *
         * @param costoALeve El costo de avería leve.
         */
        this.costoAveriaLeve = costoALeve;
    }

    public void setCostoAveriaModerada(int costoAModerada) {
        /**
         * Establece el costo de avería leve de la categoría.
         *
         * @param costoALeve El costo de avería leve.
         */
        this.costoAveriaModerada = costoAModerada;
    }

    public void setCostoAveriaTotal(int costoATotal) {
        /**
         * Establece el costo de avería total de la categoría.
         *
         * @param costoATotal El costo de avería total.
         */
        this.costoAveriaTotal = costoATotal;
    }

    public void setTarifaDiaria(int costoTarifa) {
        /**
         * Establece el costo de avería total de la categoría.
         *
         * @param costoATotal El costo de avería total.
         */
        this.tarifaDiaria = costoTarifa;
    }

    public void setId_Padre(int id_Padre) {
        /**
         * Establece el ID de la categoría padre.
         *
         * @param id_Padre El ID de la categoría padre.
         */
        this.id_Padre=id_Padre;
    }
    
    public void setPadre(Categoria Padre) {
        /**
         * Establece la categoría padre de la categoría actual.
         *
         * @param Padre La categoría padre.
         */
        this.Padre=Padre;
    }
    public void eliminarCategoria(){
        /**
         * Elimina la categoría actual de la lista de categorías en el inventario.
         * La categoría se elimina en función de su ID.
         */
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
