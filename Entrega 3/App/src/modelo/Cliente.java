package modelo;
import java.util.Calendar;


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
        /**
         * Constructor de la clase Cliente.
         *
         * @param login           Nombre de usuario para iniciar sesión como cliente.
         * @param password        Contraseña para iniciar sesión como cliente.
         * @param numeroCedula    Número de cédula del cliente.
         * @param nombre          Nombre completo del cliente.
         * @param mail            Correo electrónico del cliente.
         * @param telefono        Número de teléfono del cliente.
         * @param fechaNacimiento Fecha de nacimiento del cliente en formato AAAAMMDD.
         * @param nacionalidad    Nacionalidad del cliente.
         */
        super(login, password);
        this.numeroCedula = numeroCedula;
        this.nombre = nombre;
        this.mail = mail;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;
        this.nacionalidad = nacionalidad.toUpperCase();
    }

    
        public static boolean esMayorDeEdad(int anho, int mes, int dia) {
        /**
         * Verifica si una fecha de nacimiento corresponde a una persona mayor de edad.
         *
         * @param anho Año de nacimiento.
         * @param mes  Mes de nacimiento (1 para enero, 2 para febrero, etc.).
         * @param dia  Día de nacimiento.
         * @return `true` si la fecha de nacimiento corresponde a una persona mayor de edad; de lo contrario, `false`.
         */
        Calendar fechaActual = Calendar.getInstance();
        int diaActual = fechaActual.get(Calendar.DAY_OF_MONTH);
        int mesActual = fechaActual.get(Calendar.MONTH) + 1;
        int anhoActual = fechaActual.get(Calendar.YEAR);
    
        if (anho + 18 < anhoActual) {
            return true;
        } else if (anho + 18 == anhoActual && mes < mesActual) {
            return true;
        } else if (anho + 18 == anhoActual && mes == mesActual && dia <= diaActual) {
            return true;
        }
        return false;
    }
 
    public Tarjeta getTarjeta() {
        /**
         * Obtiene la tarjeta asociada a este cliente.
         *
         * @return La tarjeta de crédito o débito del cliente.
         */
        return tarjeta;
    }
   
    public int getNumeroCedula() {
        /**
         * Obtiene el número de cédula del cliente.
         *
         * @return El número de cédula del cliente.
         */
        return numeroCedula;
    }

    public String getNombre() {
        /**
         * Obtiene el nombre del cliente.
         *
         * @return El nombre del cliente.
         */
        return this.nombre;
    }

    public String getMail() {
        /**
         * Obtiene la dirección de correo electrónico del cliente.
         *
         * @return La dirección de correo electrónico del cliente.
         */
        return this.mail;
    }

    public void setMail(String mail) {
        /**
         * Establece la dirección de correo electrónico del cliente.
         *
         * @param mail La nueva dirección de correo electrónico del cliente.
         */
        this.mail = mail;
    }

    public long getTelefono() {
        /**
         * Obtiene el número de teléfono del cliente.
         *
         * @return El número de teléfono del cliente.
         */
        return this.telefono;
    }

    public int getFechaNacimiento() {
        /**
         * Obtiene la fecha de nacimiento del cliente.
         *
         * @return La fecha de nacimiento del cliente en formato entero (yyyyMMdd).
         */
        return fechaNacimiento;
    }

    public String getNacionalidad() {
        /**
         * Obtiene la nacionalidad del cliente.
         *
         * @return La nacionalidad del cliente.
         */
        return this.nacionalidad;
    }
    public Licencia getLicencia() {
        /**
         * Obtiene la licencia de conducir asociada al cliente.
         *
         * @return La licencia de conducir del cliente.
         */
        return this.licencia;}

    public void setLicencia(Licencia licencia) {
        /**
         * Establece la licencia de conducir del cliente.
         *
         * @param licencia La nueva licencia de conducir del cliente.
         */
        this.licencia = licencia;}

    public void setTelefono(long telefono) {
        /**
         * Establece el número de teléfono del cliente.
         *
         * @param telefono El nuevo número de teléfono del cliente.
         */
        this.telefono = telefono;
    }
    public void setTarjeta(Tarjeta tarjeta) {
        /**
         * Establece la tarjeta de crédito o débito asociada al cliente.
         *
         * @param tarjeta La nueva tarjeta de crédito o débito del cliente.
         */
        this.tarjeta = tarjeta;}
}

