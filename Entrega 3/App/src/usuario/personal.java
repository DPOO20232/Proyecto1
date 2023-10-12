package usuario;
import java.util.List;

import inventario.Inventario;
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

    public static void actualizarPersonal(Sede sede){
        String login= input("Ingrese el login del usuario al que desea modificar la clave");
        if(checkNombresLogins(login)==true){
            personal empleado=null;
            for(personal i: getCredencialesPersonal()){
                if ((i.getLogin()==login)&&(i.getSede().equals(sede))){
                    empleado=i;
                    break;
                }}
            if (empleado!=null){
            System.out.println("\nDesea cambiar la clave del empleado?\n");
            System.out.println("1.Sí");
            System.out.println("2.No(ó cualquier otro número)");
            int opcion1 = Integer.parseInt(input("Por favor seleccione una opción")); 
            if(opcion1==1){
            String password= input("Ingrese la nueva contraseña");
            empleado.setPassword(password);
            }
            System.out.println("\nDesea cambiar la sede del empleado?\n");
            System.out.println("1.Sí");
            System.out.println("2.No(ó cualquier otro número)");
            int opcion2 = Integer.parseInt(input("Por favor seleccione una opción")); 
            if(opcion2==1){
            boolean continuar=true;
            while(continuar){
            int idNuevaSede= Integer.parseInt(input("Ingrese el ID de la sede"));
            if ((idNuevaSede>0)&&(idNuevaSede<=Inventario.getListaSedes().size())){
            empleado.setSede(Inventario.assignSede(idNuevaSede));
            sede.getPersonalSede().remove(empleado);
            continuar=false;}
            else{System.out.println("> Ingrese un ID de sede válido.");}
            }}
            System.out.println("> Información actualizada.");
            }
            else{System.out.println("> El usuario ingresado no pertenece a su sede.");}

        }
        else{System.out.println("> Ingrese un login válido.");
        }}

    public static void printRegistroEmpleados(Sede sede){
        int cantidadPersonalAtencion=0;
        int cantidadPersonalTecnico=1;
        String printeo="";
        for(personal i: sede.getPersonalSede()){
            if(i.getTipoPersonal().equals("PersonalAtencion")){ cantidadPersonalAtencion+=1;}
            else if{cantidadPersonalTecnico+=1}
        }
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
