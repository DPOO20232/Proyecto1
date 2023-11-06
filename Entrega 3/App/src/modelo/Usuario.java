package modelo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Usuario {
    private String login;
    private String password;
    private static List<Usuario> credenciales;
    private static List<Cliente> ListaClientes;
    private static List<Licencia> ListaLicencias;
    private static List<String> ListaNombresLogins;
    private static List<Integer> ListaNumCedulas;
    public Usuario(String login, String password) {
        /**
         * Crea un nuevo objeto Usuario con el login y la contraseña especificados.
         *
         * @param login    El nombre de usuario o identificador del usuario.
         * @param password La contraseña asociada al usuario.
         */
        this.login = login;
        this.password = password;
    }

    public String getLogin(){
        /**
         * Obtiene el nombre de usuario o identificador del usuario.
         *
         * @return El nombre de usuario del usuario.
         */
        return this.login;
    }
    public String getPassword(){
        /**
         * Obtiene el nombre de usuario o identificador del usuario.
         *
         * @return El nombre de usuario del usuario.
         */
        return this.password;
    }
    public void setPassword(String newpassword) {

        /**
         * Obtiene la contraseña asociada al usuario.
         *
         * @return La contraseña del usuario.
         */
        this.password = newpassword;
    }
    public boolean checkCredenciales(String login, String password) {
        /**
         * Verifica las credenciales de un usuario comprobando si el nombre de usuario y contraseña proporcionados coinciden
         * con las credenciales de algún usuario registrado en el sistema.
         *
         * @param login    El nombre de usuario proporcionado.
         * @param password La contraseña proporcionada.
         * @return `true` si las credenciales son válidas y coinciden con algún usuario registrado, de lo contrario, devuelve `false`.
         */
        boolean retorno = false;
        for (Usuario i : credenciales) {
            if (i.getLogin().equals(login) && i.getPassword().equals(password)) {
                retorno = true;
                break;
            }
        }
        return retorno;
    }
    public static List<Cliente> getListaClientes(){ 
        /**
         * Obtiene una lista de clientes registrados en el sistema.
         *
         * @return Lista de objetos Cliente registrados.
         */
        return ListaClientes;}
    public static List<Licencia> getListaLicencias(){ 
        /**
         * Obtiene una lista de licencias registradas en el sistema.
         *
         * @return Lista de objetos Licencia registradas.
         */
        return ListaLicencias;}
    public static List<String> getListaNombresLogins(){
        /**
         * Obtiene una lista de nombres de usuario (logins) registrados en el sistema.
         *
         * @return Lista de nombres de usuario registrados.
         */
        return ListaNombresLogins;}
    public static List<Integer> getListaNumCedulas(){
        /**
         * Obtiene una lista de números de cédula registrados en el sistema.
         *
         * @return Lista de números de cédula registrados.
         */
        return ListaNumCedulas;}

    public static void addCliente(Cliente cliente){
        /**
         * Obtiene una lista de clientes registrados en el sistema.
         *
         * @return Lista de objetos Cliente registrados.
         */
        if (getListaClientes()==null){
            ListaClientes= new ArrayList<Cliente>();
        }
        ListaClientes.add(cliente);
    }
    public static void addLicencia(Licencia licencia){
        /**
         * Agrega un cliente a la lista de clientes registrados en el sistema.
         *
         * @param cliente Objeto Cliente a agregar a la lista de clientes.
         */
        if (getListaLicencias()==null){
            ListaLicencias= new ArrayList<Licencia>();
        }
        ListaLicencias.add(licencia);
    }
    public static void addNombreLogin(String nom){
        /**
         * Agrega un nombre de login a la lista de nombres de logins registrados en el sistema.
         *
         * @param nom Nombre de login a agregar a la lista.
         */
        if (getListaNombresLogins()==null){
            ListaNombresLogins= new ArrayList<String>();
        }
        ListaNombresLogins.add(nom);
    }
    public static void addNumCedulas(int cedula){
        /**
         * Agrega un número de cédula a la lista de números de cédula registrados en el sistema.
         *
         * @param cedula Número de cédula a agregar a la lista.
         */
        if (getListaNumCedulas()==null){
            ListaNumCedulas= new ArrayList<Integer>();
        }
        ListaNumCedulas.add(cedula);
    }
    public static Cliente assignCliente(int cedula){
        /**
         * Asigna un cliente basado en su número de cédula.
         *
         * @param cedula Número de cédula del cliente a buscar.
         * @return Cliente correspondiente al número de cédula especificado o null si no se encuentra.
         */
        Cliente retorno=null;
        for(Cliente i: Usuario.getListaClientes()){
            if (i.getNumeroCedula()==cedula){
                retorno=i;
            }
        }
        return retorno;
    }
    public static Licencia assignLicencia(int num_licencia){
        /**
         * Asigna una licencia basada en su número de licencia.
         *
         * @param num_licencia Número de licencia de la licencia a buscar.
         * @return Licencia correspondiente al número de licencia especificado o null si no se encuentra.
         */
        Licencia retorno=null;
        for(Licencia i: Usuario.getListaLicencias()){
            if (i.getNumeroLicencia()==num_licencia){
                retorno=i;
                break;
            }
        }
        return retorno;
    }
    public static Cliente checkLoginCliente(String login, String password){
        /**
         * Verifica las credenciales de un cliente basado en su nombre de usuario y contraseña.
         *
         * @param login Nombre de usuario del cliente.
         * @param password Contraseña del cliente.
         * @return Cliente correspondiente a las credenciales especificadas o null si no se encuentra.
         */
        Cliente retorno=null;
        for (Cliente i: getListaClientes()){
        if ((login.equals(i.getLogin()))&&(password.equals(i.getPassword()))){
            retorno=i;
            break;
        }}
        return retorno;
    }
    public static boolean checkNombresLogins(String login){
        /**
         * Verifica si un nombre de login ya existe en la lista de nombres de logins registrados en el sistema.
         *
         * @param login Nombre de login a verificar.
         * @return True si el nombre de login ya existe, de lo contrario False.
         */
        boolean encontrado=false;
        for(String i: getListaNombresLogins()){
            if (i.equals(login)){encontrado=true;break;}
        }
        return encontrado;
    }
    public static boolean checkCedulas(int cedula){
        /**
         * Verifica si un número de cédula ya existe en la lista de números de cédula registrados en el sistema.
         *
         * @param cedula Número de cédula a verificar.
         * @return True si el número de cédula ya existe, de lo contrario False.
         */
        boolean retorno=false;
        for (int i: getListaNumCedulas()){
            if (i==cedula){
                retorno=true;
                break;
            }
        }
        return retorno;
    }
    public static boolean checkLicencia(int num_licencia){
        /**
         * Verifica si un número de cédula ya existe en la lista de números de cédula registrados en el sistema.
         *
         * @param cedula Número de cédula a verificar.
         * @return True si el número de cédula ya existe, de lo contrario False.
         */
        boolean retorno=false;
        for(Licencia i: getListaLicencias()){
            if(i.getNumeroLicencia()==num_licencia){
                retorno=true;
                break;
            }
        }
        return retorno;
    }
    public static boolean checkVencimientoLicencia(Licencia licencia ,int dia, int mes, int anio){
        /**
         * Verifica si una licencia está vencida comparando su fecha de vencimiento con una fecha dada.
         *
         * @param licencia      La licencia a verificar.
         * @param dia           Día de la fecha a comparar.
         * @param mes           Mes de la fecha a comparar.
         * @param anio          Año de la fecha a comparar.
         * @return True si la licencia no ha vencido en la fecha especificada, de lo contrario False.
         */

        boolean vence=true;
        if (dia==0 && mes==0 && anio==0){
        Calendar fechaActual = Calendar.getInstance();
        dia = fechaActual.get(Calendar.DAY_OF_MONTH);
        mes = fechaActual.get(Calendar.MONTH) + 1;
        anio = fechaActual.get(Calendar.YEAR);}
        String fechaString = String.format("%04d%02d%02d", anio, mes, dia);
        int fechaTope = Integer.parseInt(fechaString);
        if (licencia.getFechaVencimiento()>= fechaTope) {
            vence= false;
    }
        return vence;
    }
}
