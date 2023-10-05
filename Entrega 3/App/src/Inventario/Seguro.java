package Inventario;

public class Seguro {
    private int idSeguro;
    private double pctg_tarifaDiaria;
    private String descripcion;

    // Constructor
    public Seguro(int tarifaDiaria, String desc) {
        this.pctg_tarifaDiaria = tarifaDiaria;
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

    public void setID(int id) {
        this.idSeguro = id;
    }

    public void setPctg_TarifaDiaria(double tarifa) {
        this.pctg_tarifaDiaria = tarifa;
    }

    public void setDescripcion(String desc) {
        this.descripcion = desc;
    }

    // Método para agregar un ID a la lista de IDs de clase (implementación no proporcionada)
    //public void addEnIDsClase(int id) {
        // Implementar la lógica de agregado aquí}

    // Método para eliminar un ID de la lista de IDs de clase (implementación no proporcionada)
    //public void deleteEnIDsClase(int id) {
        // Implementar la lógica de eliminación aquí}

    // Método para eliminar un seguro (implementación no proporcionada)
    //public void eliminarSeguro() {
        // Implementar la lógica de eliminación aquí}
}

    

