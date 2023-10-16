package inventario;

import java.util.List;

public class Seguro {
    private int idSeguro;
    private double pctg_tarifaDiaria;
    private String descripcion;
    private static int lastId;
    // Constructor
    public Seguro(int id,double pctg_tarifaDiaria, String desc) {
        this.idSeguro=id;
        if (idSeguro>lastId){lastId=idSeguro;}
        this.pctg_tarifaDiaria = pctg_tarifaDiaria;
        this.descripcion = desc;
    }
    public Seguro(double pctg_tarifaDiaria, String desc) {
        this.setID();
        this.pctg_tarifaDiaria = pctg_tarifaDiaria;
        this.descripcion = desc;
    }
    // Métodos para obtener valores
    public int getID() {
        return this.idSeguro;
    }

    public double getPctg_TarifaDiaria() {
        return this.pctg_tarifaDiaria;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setID(){
        this.idSeguro=lastId+=1;
        lastId=this.getID();
    }
    public void setPctg_TarifaDiaria(double tarifa) {
        this.pctg_tarifaDiaria = tarifa;
    }

    public void setDescripcion(String desc) {
        this.descripcion = desc;
    }
    public void eliminarSeguro(){
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

    

    

