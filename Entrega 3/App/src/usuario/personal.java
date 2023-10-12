package usuario;
import java.util.List;
import inventario.Sede;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
public class personal extends Usuario{
    private String tipoPersonal;
    private Sede sede;
    private static Admin credencialAdmin;
    private static List<personal> credencialesPersonal;
    public personal(String login, String password, String tipoPersonal, Sede sede) {
        super(login, password);
        this.tipoPersonal = tipoPersonal;
        this.sede = sede;
    }
    public void setTipoPersonal(String tipo){
        this.tipoPersonal=tipo;
    }
        public void setSede(Sede sede){
        this.sede=sede;
    }
    public String getTipoPersonal(){
        return this.tipoPersonal;
    }
    public static void setAdmin(Admin admin){
        credencialAdmin=admin;
    }
    public static boolean checkLoginAdmin(String login,String password){
        if ((login.equals(credencialAdmin.getLogin()))&&(password.equals(credencialAdmin.getPassword()))){
            return true;
        }
        else{
            return false;
        }
    }
    public static personal checkLoginPersonal(String login, String password){
        personal retorno=null;
        for (personal i: getCredencialesPersonal()){
        if ((login.equals(i.getLogin()))&&(password.equals(i.getPassword()))){
            retorno=i;
            break;
        }}
        return retorno;
    }
    public String tipoPersonal(){
        return this.tipoPersonal;
    }
    public Sede getSede(){
        return this.sede;
    }
    public static void addCredencialesPersonal(personal personal){
        if (credencialesPersonal==null){
            credencialesPersonal= new ArrayList<personal>();
        }
        credencialesPersonal.add(personal);


    }
    public static List<personal> getCredencialesPersonal(){
        return credencialesPersonal;
    }
    public static void addPersonalSede(Sede sede){
        String login= input("Asigne un login al empleado de formato inicial nombre.apellido (Ej: Ana Luz -> a.luz)");
        boolean continuar=true;
        while(continuar){
        String password= input("Asigne una contraseña");
        if(((Usuario.checkNombresLogins(login)==false)&&(personal.checkLoginPersonal(login, password).equals(null))&&(personal.checkLoginAdmin(login, password)==false)&&(personal.checkLoginCliente(login, password).equals(null)))) {                    
            Usuario.addNombreLogin(login);
            boolean continuar2=true;
            while(continuar2){
            System.out.println("\nElija el tipo de usuario que será asignado al empleado");
            System.out.println("1. Empleado de atención");
            System.out.println("2. Empleado técnico");
            int opcion= Integer.parseInt(input("Porfavor seleccione una opción"));
            if(opcion==1){
                EmpleadoAtencion empleado= new EmpleadoAtencion(login, password, "EmpleadoAtencion", sede);
                sede.addPersonalSede(empleado);
                personal.addCredencialesPersonal(empleado);
            }
            else if(opcion==2){
                EmpleadoTecnico empleado= new EmpleadoTecnico(login, password, "EmpleadoTecnico", sede);
                sede.addPersonalSede(empleado);
                personal.addCredencialesPersonal(empleado);
            }
            else{System.out.println("> Seleccione una opción valida.");}
            }
        }}}
    public static void actualizarPersonal(){
        String login= input("Ingrese el login del usuario al que desea modificar la clave");
    }
        public static String input(String mensaje)
	{
		try
		{
			System.out.print(mensaje + ": ");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			return reader.readLine();
		}
		catch (IOException e)
		{
			System.out.println("Error leyendo de la consola");
			e.printStackTrace();
		}
		return null;}
}
