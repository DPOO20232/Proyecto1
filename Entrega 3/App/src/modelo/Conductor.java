package modelo;

public class Conductor {
    private String nombre;
    private int cedula;
    private Licencia licencia;

    public Conductor(String nombre, int cedula, Licencia licencia){
        /**
         * Crea un objeto Conductor con el nombre, número de cédula y licencia especificados.
         *
         * @param nombre   El nombre del conductor.
         * @param cedula   El número de cédula del conductor.
         * @param licencia La licencia de conducir asociada al conductor.
         */
        this.nombre=nombre;
        this.cedula=cedula;
        this.licencia=licencia;
    }

    public String getNombre(){
        /**
         * Obtiene el nombre del conductor.
         *
         * @return El nombre del conductor.
         */
        return this.nombre;}
    public int getCedula(){
        /**
         * Obtiene el número de cédula del conductor.
         *
         * @return El número de cédula del conductor.
         */
        return this.cedula;}
    public Licencia getLicencia(){
        /**
         * Obtiene el número de cédula del conductor.
         *
         * @return El número de cédula del conductor.
         */
        return this.licencia;}
    
}
