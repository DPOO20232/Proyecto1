package consola;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import alquiler.Reserva;
import inventario.Evento;
import inventario.Inventario;
import inventario.Sede;
import inventario.Vehiculo;
import usuario.Cliente;
import usuario.Usuario;
import usuario.personal;

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
            //MENU ADMIN
            if (personal.checkLoginAdmin(login,password)==true){
                boolean continuarAdmin=true;
                perfil="Admin";
                System.out.println("\n\t\t>>>Hola, Admin!");
                while (continuarAdmin==true){
                System.out.println("\nOpciones de la aplicación\n");
                //ESTOS METODOS HAY QUE PASARLOS A ADMIN
                System.out.println("1. Monitorear un vehículo");
                System.out.println("2. Crear categoría");
                System.out.println("3. Añadir vehículo al inventario");
                System.out.println("4. Eliminar vehículo al inventario");
                System.out.println("5. Obtener historial de un vehículo");
                System.out.println("6. Cambiar sede de un vehículo (traslado interno)");
                System.out.println("7. Crear un seguro");
                System.out.println("8. Modificar informacion de un seguro");
                System.out.println("9. Eliminar seguro");
                System.out.println("10. Registrar una nueva sede");
                System.out.println("11. Modificar informacion de una sede");
                System.out.println("12. Registrar un administrador local");
                System.out.println("13. Actualizar información de  un administrador local");
                System.out.println("14. Actualizar costo por conductor adicional");
                System.out.println("15. Actualizar costo por traslado de sedes para un alquiler");
                System.out.println("16. Actualizar periodo de temporada alta");
                System.out.println("17. Actualizar periodo de temporada baja");
                System.out.println("18. Cerrar sesión\n");
                int opcion_admin = Integer.parseInt(input("Por favor seleccione una opción"));
                try{
                if (opcion_admin==1){}
                if (opcion_admin==2){Inventario.crearCategoria();}
                else if(opcion_admin==3){Inventario.crearVehiculo();}
                else if(opcion_admin==4){Inventario.eliminarVehiculo();}
                else if(opcion_admin==5){
                    String placa = input("Ingrese la placa del vehículo que desee consultar");
                    Vehiculo vehiculo= Inventario.assignVehiculo(placa);
                    if (vehiculo!=null){
                    vehiculo.obtenerLog();}
                    else{
                    System.out.println("\n>La placa ingresada no corresponde a ningún vehículo del inventario.\n");
                    }
                    
                }
                else if(opcion_admin==6){
                    String placa = input("Ingrese la placa del vehículo al que desee trasladar");
                    Vehiculo vehiculo=Inventario.assignVehiculo(placa);
                    if(vehiculo!=null){

                    System.out.println("\n>Lista de Sedes Disponibles:");
                    List<Sede> sedes = Inventario.getListaSedes();
                    for (int i = 0; i < sedes.size(); i++) {
                        System.out.println((i + 1) + ". " + sedes.get(i).getNombre()+" ("+sedes.get(i).getUbicacion()+").");}
                    int idSede=Integer.parseInt(input("Ingrese la opción de la sede a la que desea trasladar el vehículo"));
                    if(idSede<=sedes.size()&& idSede<=sedes.size()){
                        vehiculo.setTrasladoASede(Inventario.assignSede(idSede));
                    }
                    else{
                        System.out.println("\n>Ingrese una opción de sede válida.\n");
                    }
                    }
                    else{
                        System.out.println("\n>La placa ingresada no corresponde a ningún vehículo del inventario.\n");
                    }
                }
                else if(opcion_admin==7){Inventario.nuevoSeguro();}
                else if(opcion_admin==8){Inventario.editarSeguro();}
                else if(opcion_admin==9){Inventario.eliminarSeguro();}
                else if(opcion_admin==10){Inventario.nuevaSede();}
                else if(opcion_admin==11){Inventario.editarSede();}
                else if(opcion_admin==12){Inventario.registrarAdminLocal();}
                else if(opcion_admin==13){Inventario.actualizarAdminLocal();}
                else if(opcion_admin==14){Inventario.setCostoPorConductorAdicional();}
                else if(opcion_admin==15){Inventario.setCostoPorTrasladoSedes();}
                else if(opcion_admin==16){Inventario.setPeriodoTemporadaAlta();}
                else if(opcion_admin==17){Inventario.setPeriodoTemporadaBaja();}
                else if(opcion_admin==18){continuarAdmin=false;}
                else{System.out.println("Por favor seleccione una opción válida.");}
                }catch(NumberFormatException e){System.out.println("Ingrese solo números en los campos correspondientes");}}}
            //MENU ADMIN LOCAL
            else if (personal.checkLoginPersonal(login, password)!=null){
                perfil=(personal.checkLoginPersonal(login, password)).getTipoPersonal();
                personal adminlocal= personal.checkLoginPersonal(login, password);
                if (perfil.equals("AdminLocal")){
                    boolean continuarAdminL=true;
                    Sede adminSede=adminlocal.getSede();
                    String nomSede= adminSede.getNombre();
                    System.out.println("\n\t\t>>>Hola, Admin local!");
                    while (continuarAdminL==true){
                    System.out.println("\nOpciones de la aplicación\n");
                    System.out.println("1. Registrar empleado en la sede: "+nomSede);
                    System.out.println("2. Actualizar información de un empleado de la sede: "+nomSede);
                    System.out.println("3. Obtener registro de los empleados de la sede: "+nomSede);
                    System.out.println("4. Cerrar sesión\n");
                    int opcion_adminL = Integer.parseInt(input("Por favor seleccione una opción"));
                    try{
                    if (opcion_adminL==1){personal.addPersonalSede(adminSede);}
                    else if(opcion_adminL==2){personal.actualizarPersonal(adminSede);}
                    else if(opcion_adminL==3){personal.printRegistroEmpleados(adminSede);}
                    else if(opcion_adminL==4){continuarAdminL=false;}
                    else{System.out.println("Por favor seleccione una opción válida.");}
                    }catch(NumberFormatException e){System.out.println("Ingrese solo números en los campos correspondientes");}}}
                //MENU PERSONAL DE ATENCIÓN
            else if (perfil.equals("EmpleadoAtencion")){
                boolean continuarPersonal1=true;
                System.out.println("\n\t\t>>>Hola, gracias por colaborarnos en el área de atención!");
                while (continuarPersonal1==true){
                    System.out.println("\nOpciones de la aplicación\n");
                    System.out.println("1. Registrar un alquiler: ");
                    System.out.println("2. Modificar un alquiler: ");
                    System.out.println("3. Registrar una reserva: ");
                    System.out.println("4. Registrar evento de un vehículo: ");
                    System.out.println("6. Cerrar sesión\n");
                    int opcion_empleadoA = Integer.parseInt(input("Por favor seleccione una opción"));
                    try{
                        if (opcion_empleadoA==1){
                            int cedulaCliente = Integer.parseInt(input("Ingrese la cédula del cliente"));
                            Cliente reservante=Usuario.assignCliente(cedulaCliente);
                            if(reservante!=null){
                                //TODO
                            }
                            else{
                                System.out.println("No fue posible hallar al cliente");
                            }
                        }
                        else if(opcion_empleadoA==2){}
                        else if(opcion_empleadoA==3){
                            int cedulaCliente = Integer.parseInt(input("Ingrese la cédula del cliente"));
                            Cliente reservante=Usuario.assignCliente(cedulaCliente);
                            if(reservante!=null){
                                Reserva.crearReserva(reservante, true);
                            }
                            else{
                                System.out.println("No fue posible hallar al cliente");
                            }
                        }
                        else if(opcion_empleadoA==4){}
                        else if(opcion_empleadoA==5){}
                        else if(opcion_empleadoA==6){continuarPersonal1=false;}
                        else{System.out.println("Por favor seleccione una opción válida.");}
                    } catch(NumberFormatException e){System.out.println("Ingrese solo números en los campos correspondientes");}
                    }
                }
                //MENU PERSONAL TÉCNICO
                else {
                    System.out.println("\n\t\t>>>Hola, gracias por colaborarnos en el área técnica!");
                    boolean continuarPersonal2=true;
                    while (continuarPersonal2==true){
                        System.out.println("\nOpciones de la aplicación\n");
                        System.out.println("1. Actualizar estado de un vehículo: ");
                        System.out.println("2. Cerrar sesión\n");
                        int opcion_empleadoT = Integer.parseInt(input("Por favor seleccione una opción"));

                        try {
                            if (opcion_empleadoT==1){
                                String placa = input("\nIngrese la placa del vehículo al que le asignará un nuevo estado");
                                System.out.println("Ingrese que se va a realizar en el vehículo\n");
                                System.out.println("1. Lavado");
                                System.out.println("2. Mantenimiento");
                                int opcion_empleadoT2 = Integer.parseInt(input("Por favor seleccione una opción"));
                                if(opcion_empleadoT2==1){String descripcion = "En Lavado";
                                    Evento.crearEvento(descripcion, placa);
                                }
                                else if(opcion_empleadoT2==2){String descripcion = "En Mantenimiento";
                                    Evento.crearEvento(descripcion, placa);
                                }
                                else{System.out.println("\n>Por favor seleccione una opción válida.");}
                               
                            }
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
                    System.out.println("1. Cambiar contraseña");
                    System.out.println("2. Actualizar información personal");
                    System.out.println("3. Crear una reserva");
                    System.out.println("4. Modificar datos de mi reserva");
                    System.out.println("5. Cancelar reserva");
                    System.out.println("6. Cerrar sesión\n");
                    int opcion_cliente = Integer.parseInt(input("Por favor seleccione una opción"));
                    try{
                    if (opcion_cliente==1){
                        String nueva_contraseña = input("Ingrese una nueva contraseña");
                        System.out.println(">Contraseña actualizada\n");
                        cliente.setPassword(nueva_contraseña);}
                    if (opcion_cliente==2){cliente.actualizarCliente();}
                    else if(opcion_cliente==3){Reserva.crearReserva(cliente,false);}
                    else if(opcion_cliente==4){Reserva.modificarReserva(cliente);}
                    else if(opcion_cliente==5){Reserva.eliminarReserva(cliente);}
                    else if(opcion_cliente==6){continuarCliente=false;}
                    else{System.out.println("Por favor seleccione una opción válida.");}
                    }catch(NumberFormatException e){System.out.println("Ingrese solo números en los campos correspondientes");}}
            }

            else{System.out.println("\n\t>>> Credenciales incorrectas, intentelo de nuevo.");}          
            }
            else if(opcion_seleccionada==3){
                Cliente.nuevoCliente();
                
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
				System.out.println("Debe seleccionar uno de los números de las opciones y cargar la información del restaurante.");
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
    public static void main(String[] args) throws IOException
	{
    Inventario.loadSistema();
    System.out.println("\n\t\t>>> Bienvenid@ a "+Inventario.getNombreCompania());    
    MenuInicial();
    
	}
}
