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

    public String getNombre(){return this.nombre;}
    public int getCedula(){return this.cedula;}
    public Licencia getLicencia(){return this.licencia;}
    
}
