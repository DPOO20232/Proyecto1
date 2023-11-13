package vista;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import controlador.MenuAlquiler;
import controlador.MenuInventario;
import controlador.MenuUsuario;
import modelo.Cliente;
import modelo.Inventario;
import modelo.Sede;
import modelo.Usuario;
import modelo.personal;

public class Consola {
    public static void MenuInicial() throws IOException{
    boolean continuar=true;
    while (continuar){
        
        try
			{
            System.out.println("\n\t\t>>> Menú principal");    
			System.out.println("1. Mostrar información de la empresa");
			System.out.println("2. Iniciar sesión");
			System.out.println("3. Crear cuenta");
			System.out.println("4. Salir de la aplicación\n");

            int opcion_seleccionada = Integer.parseInt(input("Por favor seleccione una opción"));
            if (opcion_seleccionada==1) {
			System.out.println("\n>Nombre de la empresa: "+Inventario.getNombreCompania());
            System.out.println("\n>Sedes: ");                
            List<Sede> sedes = Inventario.getListaSedes();
            for (int i = 0; i < sedes.size(); i++) {
                sedes.get(i).printInfo();
            }   
        }

            if (opcion_seleccionada==2) {
            String perfil;
            String login = input("Usuario");
            String password = input("Contraseña");
            //MENU ADMIN -> DONE
            if (personal.checkLoginAdmin(login,password)==true){
                boolean continuarAdmin=true;
                perfil="Admin";
                System.out.println("\n\t\t>>>Hola, Admin!");
                while (continuarAdmin==true){
                System.out.println("\nOpciones de la aplicación\n");
                System.out.println("1. Monitorear un vehículo");//CHECKED
                System.out.println("2. Crear categoría"); //CHECKED
                System.out.println("3. Añadir vehículo al inventario");//CHECKED
                System.out.println("4. Eliminar vehículo del inventario");//CHECKED
                System.out.println("5. Obtener historial de un vehículo (archivo Log)");//CHECKED
                System.out.println("6. Cambiar sede de un vehículo (traslado interno)");//CHECKED
                System.out.println("7. Crear un seguro");//CHECKED
                System.out.println("8. Modificar informacion de un seguro");//CHECKED
                System.out.println("9. Eliminar seguro");//CHECKED
                System.out.println("10. Registrar una nueva sede");//CHECKED
                System.out.println("11. Modificar informacion de una sede");//CHECKED
                System.out.println("12. Registrar un administrador local");//CHECKED
                System.out.println("13. Actualizar información de  un administrador local");//CHECKED
                System.out.println("14. Actualizar costo por conductor adicional");//CHECKED
                System.out.println("15. Actualizar costo por traslado de sedes para un alquiler");//CHECKED
                System.out.println("16. Actualizar periodo de temporada alta");//CHECKED
                System.out.println("17. Actualizar periodo de temporada baja");//CHECKED
                System.out.println("18. Cerrar sesión\n");//CHECKED
                int opcion_admin = Integer.parseInt(input("Por favor seleccione una opción"));
                try{
                if (opcion_admin==1){MenuInventario.statusVehiculo();Inventario.updateSistema();}
                else if (opcion_admin==2){MenuInventario.crearCategoria();Inventario.updateSistema();}
                else if(opcion_admin==3){MenuInventario.crearVehiculo();Inventario.updateSistema();}
                else if(opcion_admin==4){MenuInventario.eliminarVehiculo();Inventario.updateSistema();}
                else if(opcion_admin==5){MenuInventario.logVehiculo();Inventario.updateSistema();}
                else if(opcion_admin==6){MenuInventario.trasladoVehiculo();Inventario.updateSistema();}
                else if(opcion_admin==7){MenuInventario.nuevoSeguro();Inventario.updateSistema();}
                else if(opcion_admin==8){MenuInventario.editarSeguro();Inventario.updateSistema();}
                else if(opcion_admin==9){MenuInventario.eliminarSeguro();Inventario.updateSistema();}
                else if(opcion_admin==10){MenuInventario.nuevaSede();Inventario.updateSistema();}
                else if(opcion_admin==11){MenuInventario.editarSede();Inventario.updateSistema();}
                else if(opcion_admin==12){MenuUsuario.registrarAdminLocal();Inventario.updateSistema();}
                else if(opcion_admin==13){MenuUsuario.actualizarAdminLocal();Inventario.updateSistema();}
                else if(opcion_admin==14){MenuInventario.editarCostoPorConductorAdicional();Inventario.updateSistema();}
                else if(opcion_admin==15){MenuInventario.editarCostoPorTrasladoSedes();Inventario.updateSistema();}
                else if(opcion_admin==16){MenuInventario.editarPeriodoTemporadaAlta();Inventario.updateSistema();}
                else if(opcion_admin==17){MenuInventario.editarPeriodoTemporadaBaja();Inventario.updateSistema();}
                else if(opcion_admin==18){continuarAdmin=false;}
                else{System.out.println("Por favor seleccione una opción válida.");}
                }
                catch(NumberFormatException e){System.out.println("Ingrese solo números en los campos correspondientes");}}}
            //MENU ADMIN LOCAL
            else if (personal.checkLoginPersonal(login, password)!=null){
                perfil=(personal.checkLoginPersonal(login, password)).getTipoPersonal();
                Sede sedePersonal= personal.checkLoginPersonal(login, password).getSede();
                personal adminlocal= personal.checkLoginPersonal(login, password);
                if (perfil.equals("AdminLocal")){
                    boolean continuarAdminL=true;
                    Sede adminSede=adminlocal.getSede();
                    String nomSede= adminSede.getNombre();
                    System.out.println("\n\t\t>>>Hola, Admin local!");
                    while (continuarAdminL==true){
                    System.out.println("\nOpciones de la aplicación\n");
                    System.out.println("1. Registrar empleado en la sede: "+nomSede);//CHECKED
                    System.out.println("2. Actualizar información de un empleado de la sede: "+nomSede);//CHECKED
                    System.out.println("3. Obtener registro de los empleados de la sede: "+nomSede);//CHECKED
                    System.out.println("4. Cerrar sesión\n");
                    int opcion_adminL = Integer.parseInt(input("Por favor seleccione una opción"));
                    try{
                    if (opcion_adminL==1){MenuUsuario.addPersonalSede(adminSede);
                            Inventario.updateSistema();}
                    else if(opcion_adminL==2){MenuUsuario.actualizarPersonal(adminSede);
                            Inventario.updateSistema();}
                    else if(opcion_adminL==3){personal.printRegistroEmpleados(adminSede);
                            Inventario.updateSistema();}
                    else if(opcion_adminL==4){continuarAdminL=false;}
                    else{System.out.println("Por favor seleccione una opción válida.");}
                    }catch(NumberFormatException e){System.out.println("Ingrese solo números en los campos correspondientes");}}}
                //MENU PERSONAL DE ATENCIÓN
            else if (perfil.equals("EmpleadoAtencion")){
                boolean continuarPersonal1=true;
                System.out.println("\n\t\t>>>Hola, gracias por colaborarnos en el área de atención!");
                while (continuarPersonal1==true){
                    System.out.println("\nOpciones de la aplicación\n");
                    System.out.println("1. Registrar un alquiler");//CHECKED
                    System.out.println("2. Completar un alquiler");
                    System.out.println("3. Registrar una reserva");
                    System.out.println("4. Modificar una reserva");
                    System.out.println("5. Cerrar sesión\n");
                    int opcion_empleadoA = Integer.parseInt(input("Por favor seleccione una opción"));
                    try{
                        if (opcion_empleadoA==1){MenuAlquiler.crearAlquiler(sedePersonal);
                            Inventario.updateSistema();}
                        else if(opcion_empleadoA==2){MenuAlquiler.completarAlquiler(sedePersonal);
                            Inventario.updateSistema();}
                        else if(opcion_empleadoA==3){ MenuAlquiler.crearReserva(null, true);
                            Inventario.updateSistema();}
                        else if(opcion_empleadoA==4){MenuAlquiler.modificarReserva(null);
                            Inventario.updateSistema();}
                        else if(opcion_empleadoA==5){continuarPersonal1=false;}
                        else{System.out.println("Por favor seleccione una opción válida.");}
                    } catch(NumberFormatException e){System.out.println("Ingrese solo números en los campos correspondientes");}
                    }
                }
                //MENU PERSONAL TÉCNICO -> DONE
                else {
                    System.out.println("\n\t\t>>>Hola, gracias por colaborarnos en el área técnica!");
                    boolean continuarPersonal2=true;
                    while (continuarPersonal2==true){
                        System.out.println("\nOpciones de la aplicación\n");
                        System.out.println("1. Actualizar estado de un vehículo: ");//CHECKED
                        System.out.println("2. Cerrar sesión\n");
                        int opcion_empleadoT = Integer.parseInt(input("Por favor seleccione una opción"));

                        try {
                            if (opcion_empleadoT==1){MenuInventario.crearEvento();                          
                            Inventario.updateSistema();}
                            else if(opcion_empleadoT==2){continuarPersonal2=false;}
                            else{System.out.println("Por favor seleccione una opción válida.");}
                        } catch(NumberFormatException e){}
                    }
                }
            }
            //MENU CLIENTE
            else if (Usuario.checkLoginCliente(login, password)!=null){
                Cliente cliente= Usuario.checkLoginCliente(login, password);
                boolean continuarCliente=true;
                System.out.println("\n\t\t>>>Hola, "+cliente.getNombre()+" :)");
                while (continuarCliente){
                    System.out.println("\nOpciones de la aplicación\n");
                    System.out.println("1. Cambiar contraseña");//CHECKED
                    System.out.println("2. Actualizar información personal");
                    System.out.println("3. Crear una reserva");
                    System.out.println("4. Modificar datos de mi reserva");
                    System.out.println("5. Cancelar reserva");
                    System.out.println("6. Cerrar sesión\n");
                    int opcion_cliente = Integer.parseInt(input("Por favor seleccione una opción"));
                    try{
                    if (opcion_cliente==1){MenuUsuario.actualizarPassword(cliente);
                    Inventario.updateSistema();}
                    if (opcion_cliente==2){MenuUsuario.actualizarCliente(cliente);
                    Inventario.updateSistema();}
                    else if(opcion_cliente==3){MenuAlquiler.crearReserva(cliente,false);
                    Inventario.updateSistema();}
                    else if(opcion_cliente==4){MenuAlquiler.modificarReserva(cliente);
                    Inventario.updateSistema();}
                    else if(opcion_cliente==5){MenuAlquiler.eliminarReserva(cliente);
                    Inventario.updateSistema();}
                    else if(opcion_cliente==6){continuarCliente=false;}
                    else{System.out.println("Por favor seleccione una opción válida.");}
                    }catch(NumberFormatException e){System.out.println("Ingrese solo números en los campos correspondientes");}}
            }

            else{System.out.println("\n\t>>> Credenciales incorrectas, intentelo de nuevo.");}          
            }
            else if(opcion_seleccionada==3){
                MenuUsuario.nuevoCliente();//CHECKED
                
            }
            else if(opcion_seleccionada==4){
                Inventario.updateSistema();
                continuar=false;
            }
            else{
                System.out.println("Por favor seleccione una opción válida.");
            }
        }
		catch (NumberFormatException e){
				System.out.println("Por favor seleccione una opción válida.");
			}
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
		return null;
    }
    public static void main(String[] args) throws IOException{	
    Inventario.loadSistema();
    MenuInicial();
	}
}
