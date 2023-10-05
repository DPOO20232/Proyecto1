package usuario;
public class Cliente{
    private int numeroCedula;
    private String nombre;
    private String mail;
    private int telefono;
    private int fechaNacimiento;
    private String nacionalidad;
    private Licencia licencia;
    private Tarjeta tarjeta;

    // Constructor
    public Cliente(int numeroCedula, String nombre, String mail, int telefono, int fechaNacimiento, String nacionalidad) {
        this.numeroCedula = numeroCedula;
        this.nombre = nombre;
        this.mail = mail;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;
        this.nacionalidad = nacionalidad;
    }

 
    //public Tarjeta getTarjeta() {
    //    return tarjeta;
    //}

    public String getNumeroCedula() {
        return Integer.toString(numeroCedula);
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getMail() {
        return this.mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getTelefono() {
        return this.telefono;
    }

    public int getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getNacionalidad() {
        return this.nacionalidad;
    }
    //public Licencia getLicencia() {
    //    return this.licencia;}

    //public void setLicencia(Licencia licencia) {
    //    this.licencia = licencia;}

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }
    //public void setTarjeta(Tarjeta tarjeta) {
    //    this.tarjeta = tarjeta;}
}
