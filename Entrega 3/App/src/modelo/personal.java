package modelo;
import java.util.List;
import java.util.ArrayList;
public class personal extends Usuario{
    private String tipoPersonal;
    private Sede sede;
    private static Admin credencialAdmin;
    private static List<personal> credencialesPersonal;
    public personal(String login, String password, String tipoPersonal, Sede sede) {
        /**
         * Constructor de la clase Personal que inicializa las credenciales de inicio de sesión
         * y la asociación con una sede.
         *
         * @param login El nombre de usuario para iniciar sesión.
         * @param password La contraseña del usuario para iniciar sesión.
         * @param tipoPersonal El tipo de personal (por ejemplo, "EmpleadoAtencion" o "EmpleadoTecnico").
         * @param sede La sede a la que está asociado el personal.
         */
        super(login, password);
        this.tipoPersonal = tipoPersonal;
        this.sede = sede;
    }
    public void setTipoPersonal(String tipo){
        /**
         * Establece el tipo de personal (por ejemplo, "EmpleadoAtención" o "EmpleadoTécnico").
         *
         * @param tipo El tipo de personal a establecer.
         */
        this.tipoPersonal=tipo;
    }
    public void setSede(Sede sede){
        /**
         * Establece la sede a la que está asociado el personal.
         *
         * @param sede La sede a la que se asociará el personal.
         */
        this.sede=sede;
    }
    public String getTipoPersonal(){
        /**
         * Establece la sede a la que está asociado el personal.
         *
         * @param sede La sede a la que se asociará el personal.
         */
        return this.tipoPersonal;
    }
    public static Admin getCredendialAdmin(){
        /**
         * Establece la sede a la que está asociado el personal.
         *
         * @param sede La sede a la que se asociará el personal.
         */
        return credencialAdmin;
    }
    public static void setAdmin(Admin admin){
        /**
         * Establece las credenciales del administrador.
         *
         * @param admin Las credenciales del administrador a establecer.
         */
        credencialAdmin=admin;
    }
    public static boolean checkLoginAdmin(String login,String password){
        /**
         * Verifica si las credenciales de administrador son válidas.
         *
         * @param login    El nombre de usuario del administrador.
         * @param password La contraseña del administrador.
         * @return `true` si las credenciales son válidas, `false` en caso contrario.
         */
        if ((login.equals(credencialAdmin.getLogin()))&&(password.equals(credencialAdmin.getPassword()))){
            return true;
        }
        else{
            return false;
        }
    }
    public static personal checkLoginPersonal(String login, String password){
        /**
         * Verifica si las credenciales de un miembro del personal son válidas y devuelve el personal correspondiente.
         *
         * @param login    El nombre de usuario del miembro del personal.
         * @param password La contraseña del miembro del personal.
         * @return El objeto de tipo `personal` si las credenciales son válidas, o `null` en caso contrario.
         */
        personal retorno=null;
        for (personal i: getCredencialesPersonal()){
        if ((login.equals(i.getLogin()))&&(password.equals(i.getPassword()))){
            retorno=i;
            break;
        }}
        return retorno;
    }
    public String tipoPersonal(){
        /**
         * Obtiene el tipo de personal.
         *
         * @return El tipo de personal, como una cadena de texto.
         */
        return this.tipoPersonal;
    }
    public Sede getSede(){
        /**
         * Obtiene la sede a la que está asignado el personal.
         *
         * @return La sede a la que está asignado el personal, representada como un objeto de tipo `Sede`.
         */
        return this.sede;
    }
    public static void addCredencialesPersonal(personal personal){
        /**
         * Agrega las credenciales de un personal al registro de credenciales.
         *
         * @param personal El objeto de tipo `personal` que representa al personal cuyas credenciales se agregarán.
         */
        if (credencialesPersonal==null){
            credencialesPersonal= new ArrayList<personal>();
        }
        credencialesPersonal.add(personal);
    }
    public static List<personal> getCredencialesPersonal(){
        /**
         * Obtiene la lista de credenciales del personal registrado en el sistema.
         *
         * @return Una lista de objetos `personal` que contienen las credenciales del personal.
         */
        return credencialesPersonal;
    }
    public static String printRegistroEmpleados(Sede sede){
        /**
         * Imprime en la consola un resumen del registro de empleados de una sede específica.
         *
         * @param sede La sede de la cual se imprimirá el registro de empleados.
         *
         * @throws NullPointerException Si la sede pasada como parámetro es nula.
         */
        String inicio="> ";
        int cantidadPersonalAtencion=0;
        int cantidadPersonalTecnico=0;
        String key1 = "0 empleados de atención. ";
        String key2 = "0 empleados técnicos. ";
        String value1 = "";
        String value2 = "";
    
        if (sede.getAdminLocal() != null) {
            inicio =inicio+ "1 Administrador Local: " + sede.getAdminLocal().getLogin() + "; ";
        } else {
            inicio =inicio+ "0 Administradores Locales. ";
        }
    
        for (personal i : sede.getPersonalSede()) {
            String tipo=i.getTipoPersonal();
            String login=i.getLogin();
            if (tipo.equals("EmpleadoAtencion")) {
                cantidadPersonalAtencion+=1;
                key1 = cantidadPersonalAtencion + " Empleado(s) de atención: ";
                value1 =value1+ login + " , ";
            } else if (i.getTipoPersonal().equals("EmpleadoTecnico")) {
                cantidadPersonalTecnico+=1;
                key2 =cantidadPersonalTecnico + " Empleado(s) técnico(s): ";
                value2 =value2+ login + " , ";
            }
        }
    
        String empleados=(inicio + key1 + value1.substring(0, value1.length() - 2) +";"+ key2 + value2.substring(0, value2.length() - 2) + ";");
        return empleados;
    }   

}
