package inventario;

import java.util.List;

public class Seguro {
    private int idSeguro;
    private double pctg_tarifaDiaria;
    private String descripcion;
    private static int lastId;
    // Constructor
    public Seguro(int id,double pctg_tarifaDiaria, String desc) {
         /**
         * Constructor para la creación de un seguro de vehículo.
         *
         * @param id El identificador único del seguro.
         * @param pctg_tarifaDiaria El porcentaje de la tarifa diaria que cubre el seguro.
         * @param desc La descripción del seguro.
         */
        this.idSeguro=id;
        if (idSeguro>lastId){lastId=idSeguro;}
        this.pctg_tarifaDiaria = pctg_tarifaDiaria;
        this.descripcion = desc;
    }
    public Seguro(double pctg_tarifaDiaria, String desc) {
        /**
         * Constructor para la creación de un seguro de vehículo.
         *
         * @param pctg_tarifaDiaria El porcentaje de la tarifa diaria que cubre el seguro.
         * @param desc La descripción del seguro.
         */
        this.setID();
        this.pctg_tarifaDiaria = pctg_tarifaDiaria;
        this.descripcion = desc;
    }
    // Métodos para obtener valores
    public int getID() {
        /**
         * Obtiene el identificador único del seguro.
         *
         * @return El identificador único del seguro.
         */
        return this.idSeguro;
    }

    public double getPctg_TarifaDiaria() {
        /**
         * Obtiene el porcentaje de la tarifa diaria que cubre el seguro.
         *
         * @return El porcentaje de la tarifa diaria cubierto por el seguro.
         */
        return this.pctg_tarifaDiaria;
    }

    public String getDescripcion() {
        /**
         * Obtiene el porcentaje de la tarifa diaria que cubre el seguro.
         *
         * @return El porcentaje de la tarifa diaria cubierto por el seguro.
         */
        return this.descripcion;
    }

    public void setID(){
        /**
         * Establece el identificador único del seguro. El identificador debe ser único y no se recomienda modificarlo manualmente.
         */
        this.idSeguro=lastId+=1;
        lastId=this.getID();
    }
    public void setPctg_TarifaDiaria(double tarifa) {
        /**
         * Establece el porcentaje de la tarifa diaria que cubre el seguro.
         *
         * @param tarifa El nuevo porcentaje de tarifa diaria cubierto por el seguro.
         */
        this.pctg_tarifaDiaria = tarifa;
    }

    public void setDescripcion(String desc) {
        /**
         * Establece la descripción del seguro.
         *
         * @param desc La nueva descripción del seguro.
         */
        this.descripcion = desc;
    }
    public void eliminarSeguro(){
        /**
         * Elimina el seguro del inventario, lo cual lo excluye de todas las sedes.
         */
        List<Sede> sedes_inventario= Inventario.getListaSedes();
        int idEliminar=this.getID();
        for (int i = 0; i < sedes_inventario.size(); i++){
            Sede i_sede= sedes_inventario.get(i);
            if (i_sede.getID()==(idEliminar)){
                sedes_inventario.remove(i);
                break;
            }
        }
}
}

    

    

