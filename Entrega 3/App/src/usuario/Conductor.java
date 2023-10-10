package usuario;

public class Conductor {
    private String nombre;
    private int cedula;
    private Licencia licencia;

    public Conductor(String nombre, int cedula, Licencia licencia){
        this.nombre=nombre;
        this.cedula=cedula;
        this.licencia=licencia;
    }
    
}
