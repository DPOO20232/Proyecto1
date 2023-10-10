package usuario;
public class Cliente extends Usuario{
    private int numeroCedula;
    private String nombre;
    private String mail;
    private long telefono;
    private int fechaNacimiento;
    private String nacionalidad;
    private Licencia licencia;
    private Tarjeta tarjeta;

    // Constructor
    public Cliente(String login, String password,int numeroCedula, String nombre, String mail, long telefono, int fechaNacimiento, String nacionalidad) {
        super(login, password);
        this.numeroCedula = numeroCedula;
        this.nombre = nombre;
        this.mail = mail;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;
        this.nacionalidad = nacionalidad;
    }

 
    public Tarjeta getTarjeta() {
        return tarjeta;
    }

    public int getNumeroCedula() {
        return numeroCedula;
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

    public long getTelefono() {
        return this.telefono;
    }

    public int getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getNacionalidad() {
        return this.nacionalidad;
    }
    public Licencia getLicencia() {
        return this.licencia;}

    public void setLicencia(Licencia licencia) {
        this.licencia = licencia;}

    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }
    public void setTarjeta(Tarjeta tarjeta) {
        this.tarjeta = tarjeta;}
}

