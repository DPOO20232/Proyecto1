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

    // Getters y Setters
    //public Licencia getLicencia() {
    //    return this.licencia;}

    //public void setLicencia(Licencia licencia) {
    //    this.licencia = licencia;}

    //public Tarjeta getTarjeta() {
    //    return tarjeta;
    //}

    public String getNumeroCedula() {
        return Integer.toString(numeroCedula);
    }

    public String getNombre() {
        return nombre;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public int getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }
    //public void setTarjeta(Tarjeta tarjeta) {
    //    this.tarjeta = tarjeta;}
}
