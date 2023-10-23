package Menus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import inventario.Inventario;
import inventario.Sede;
import usuario.EmpleadoAtencion;
import usuario.EmpleadoTecnico;
import usuario.Usuario;
import usuario.personal;

public class MenuUsuario {
    
    public static void registrarAdminLocal(){
        try {
            String login=input("Ingrese el login del usuario");
            boolean continuar=true;
            while(continuar){
            String password=input("Ingrese la contraseña del usuario");
            if(((Usuario.checkNombresLogins(login)==false))) {                    
            Sede sede=null;
            boolean continuar2=true;
            while(continuar2){
            int Idsede=Integer.parseInt(input("ingrese el ID de la sede"));
            if ((Idsede>=1&& Idsede<=Inventario.getListaSedes().size())){
                sede=Inventario.assignSede(Idsede);
                if(sede.getAdminLocal()==null){
                sede= Inventario.assignSede(Idsede);
                personal adminlocal=new personal(login, password, "AdminLocal", sede);
                personal.addCredencialesPersonal(adminlocal);
                sede.setAdminLocal(adminlocal);
                System.out.println(">El nuevo admin local de la sede con ID "+ Integer.toString(Idsede)+" ha sido creado.");
                continuar2=false;
                continuar=false;
            }else{
                System.out.println(">La sede ya tiene un administrador local asignado");
                continuar2=false;
                continuar=false;}
            }else{System.out.println(">Ingresó un ID de sede inválido");}
            }}
            else{System.out.println(">El password no es válido, digite otro");}}
        } catch (Exception e) {
             System.out.println(">Ingrese solo números en los campos correspondientes");
        }
    }
    public static void actualizarAdminLocal(){
        try {
            String login=input("Ingrese el login del administrador");
            List<personal> listpersonal= personal.getCredencialesPersonal();
            int NidSede=Integer.parseInt(input("Ingrese el Id de la nueva sede"));
            if (NidSede>=1&& NidSede<=Inventario.getListaSedes().size()){
            Sede sede=null;
            for(personal i:listpersonal){
                if (i.getLogin().equals(login) && i.getTipoPersonal().equals("AdminLocal")){
                    {
                        sede= Inventario.assignSede(NidSede);
                        if(sede.getAdminLocal()!=null){
                        i.setSede(sede);
                        System.out.println(">El admin ha sido asignado correctamente a la sede "+ Integer.toString(NidSede));
                        break;
                        }
                        else{
                        System.out.println(">Ya hay un admin local en la sede "+ Integer.toString(NidSede));
                        }
                    }
                }}}
            else{System.out.println(">Ingresó un ID de sede inválido");}
        } 
        catch (Exception e) {
            System.out.println(">Ingrese solo números en los campos correspondientes");
        }
    }

    public static void addPersonalSede(Sede sede){
        boolean continuar=true;
        while(continuar){
        String login= input("Asigne un login al empleado de formato inicial nombre.apellido (Ej: Ana Luz -> a.luz)");
        if(((Usuario.checkNombresLogins(login)==false))) {
            String password= input("Asigne una contraseña");                 
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
                continuar2=false;
                System.out.println("> Información guardada.");

            }
            else if(opcion==2){
                EmpleadoTecnico empleado= new EmpleadoTecnico(login, password, "EmpleadoTecnico", sede);
                sede.addPersonalSede(empleado);
                personal.addCredencialesPersonal(empleado);
                continuar2=false;
                System.out.println("> Información guardada.");
            }
            else{System.out.println("> Seleccione una opción valida.");}
            }
            continuar=false;
        }
        else{System.out.println("> Ingrese otro login válido.");}
        }}

    public static void actualizarPersonal(Sede sede){
        String login= input("Ingrese el login del usuario al que desea modificar la clave");
        if(personal.checkNombresLogins(login)==true){
            personal empleado=null;
            for(personal i: personal.getCredencialesPersonal()){
                if ((i.getLogin().equals(login))&&(i.getSede().equals(sede))){
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
        String inicio="> ";
        int cantidadPersonalAtencion=0;
        int cantidadPersonalTecnico=0;
        String key1 = "0 empleados de atención. ";
        String key2 = "0 empleados técnicos. ";
        String value1 = "";
        String value2 = "";
    
        if (sede.getAdminLocal() != null) {
            inicio =inicio+ "1 Administrador Local: " + sede.getAdminLocal().getLogin() + ". ";
        } else {
            inicio =inicio+ "0 Administradores Locales. ";
        }
    
        for (personal i : sede.getPersonalSede()) {
            String tipo=i.getTipoPersonal();
            String login=i.getLogin();
            if (tipo.equals("EmpleadoAtencion")) {
                cantidadPersonalAtencion+=1;
                key1 = cantidadPersonalAtencion + " Empleado(s) de atención: ";
                value1 =value1+ login + ", ";
            } else if (i.getTipoPersonal().equals("EmpleadoTecnico")) {
                cantidadPersonalTecnico+=1;
                key2 =cantidadPersonalTecnico + " Empleado(s) técnico(s): ";
                value2 =value2+ login + ", ";
            }
        }
    
        System.out.println(inicio + key1 + value1.substring(0, value1.length() - 2) +". "+ key2 + value2.substring(0, value2.length() - 2) + ".");
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
		return null;
    }

}
