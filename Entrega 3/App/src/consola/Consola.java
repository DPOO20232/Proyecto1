package consola;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.List;

import alquiler.Reserva;
import inventario.Inventario;
import inventario.Sede;
import inventario.Vehiculo;
import usuario.Cliente;
import usuario.Usuario;
import usuario.personal;
import usuario.Licencia;
import usuario.Tarjeta;
public class Consola {
    public static void MenuInicial() throws IOException{
    boolean continuar=true;
    while (continuar){
        try
			{
			System.out.println("1. Iniciar sesión");
			System.out.println("2. Crear cuenta");
			System.out.println("3. Salir de la aplicación\n");

            int opcion_seleccionada = Integer.parseInt(input("Por favor seleccione una opción"));
            if (opcion_seleccionada==1) {
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
                System.out.println("1. Crear categoría");
                System.out.println("2. Añadir vehículo al inventario");
                System.out.println("3. Eliminar vehículo al inventario");
                System.out.println("4. Obtener historial de un vehículo");
                System.out.println("5. Cambiar sede de un vehículo (traslado interno)");
                System.out.println("6. Crear un seguro");
                System.out.println("7. Modificar informacion de un seguro");
                System.out.println("8. Eliminar seguro");
                System.out.println("9. Registrar una nueva sede");
                System.out.println("10. Modificar informacion de una sede");
                System.out.println("11. Registrar un administrador local");
                System.out.println("12. Actualizar información de  un administrador local");
                System.out.println("13. Actualizar costo por conductor adicional");
                System.out.println("14. Actualizar costo por traslado de sedes para un alquiler");
                System.out.println("15. Actualizar periodo de temporada alta");
                System.out.println("16. Actualizar periodo de temporada baja");
                System.out.println("17. Cerrar sesión\n");
                int opcion_admin = Integer.parseInt(input("Por favor seleccione una opción"));
                try{
                if (opcion_admin==1){Inventario.NuevaCategoria();}
                else if(opcion_admin==2){Inventario.nuevoVehiculo();}
                else if(opcion_admin==3){Inventario.eliminarVehiculo();}
                else if(opcion_admin==4){
                    String placa = input("Ingrese la placa del vehículo que desee consultar");
                    List<Vehiculo> lista = Inventario.getListaVehiculos();
                    Inventario.obtenerHistorial(lista, placa);
                }
                else if(opcion_admin==5){
                    //TODO
                }
                else if(opcion_admin==6){Inventario.nuevoSeguro();}
                else if(opcion_admin==7){Inventario.editarSeguro();}
                else if(opcion_admin==8){Inventario.eliminarSeguro();}
                else if(opcion_admin==9){Inventario.nuevaSede();}
                else if(opcion_admin==10){Inventario.editarSede();}
                else if(opcion_admin==11){Inventario.registrarAdminLocal();}
                else if(opcion_admin==12){Inventario.actualizarAdminLocal();}
                else if(opcion_admin==13){Inventario.setCostoPorConductorAdicional();}
                else if(opcion_admin==14){Inventario.setCostoPorTrasladoSedes();}
                else if(opcion_admin==15){Inventario.setPeriodoTemporadaAlta();}
                else if(opcion_admin==16){Inventario.setPeriodoTemporadaBaja();}
                else if(opcion_admin==17){continuarAdmin=false;}
                else{System.out.println("Por favor seleccione una opción válida.");}
            }catch(NumberFormatException e){System.out.println("Ingrese solo números en los campos correspondientes");}}}
            //MENU PERSONAL
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
                }catch(NumberFormatException e){}}}
                    //ESTAS CLASES HAY QUE PASARLAS A ADMIN
                else if (perfil.equals("EmpleadoAtencion")){
                    //FALTA EMPLEADOS MENU TODO
                    boolean continuarPersonal1=true;
                    System.out.println("\n\t\t>>>Hola, gracias por colaborarnos en el área de atención!");
                    while (continuarPersonal1==true){
                    System.out.println("\nOpciones de la aplicación\n");
                    System.out.println("1. Registrar empleado en la sede: ");
                    System.out.println("2. Actualizar información de un empleado de la sede: ");
                    System.out.println("3. Obtener registro de los empleados de la sede: ");
                    System.out.println("4. Cerrar sesión\n");
                    int opcion_adminL = Integer.parseInt(input("Por favor seleccione una opción"));
                    try{
                    if (opcion_adminL==1){}
                    else if(opcion_adminL==2){}
                    else if(opcion_adminL==3){}
                    else if(opcion_adminL==4){continuarPersonal1=false;}
                    else{System.out.println("Por favor seleccione una opción válida.");}
                }catch(NumberFormatException e){}}


                }
                else {
                    //aqui va EmpleadoTecnico

                }
            }
            //MENU CLIENTE
            else if (Usuario.checkLoginCliente(login, password)!=null){
                Cliente cliente= Usuario.checkLoginCliente(login, password);
                boolean continuarCliente=true;
                System.out.println("\n\t>>>Bienvenid@, "+cliente.getNombre());
                while (continuarCliente){
                    System.out.println("1. Actualizar datos");
                    System.out.println("2. Crear una reserva");
                    System.out.println("3. Modificar datos de mi reserva");
                    System.out.println("4. Cancelar reserva");
                    System.out.println("6. Cerrar sesión\n");
                    int opcion_cliente = Integer.parseInt(input("Por favor seleccione una opción"));
                    try{
                    boolean ensede = false;
                    if (opcion_cliente==1){}
                    else if(opcion_cliente==2){Reserva.crearReserva(cliente,ensede);}
                    else if(opcion_cliente==2){}
                    else if(opcion_cliente==3){}
                    else if(opcion_cliente==4){}
                    else if(opcion_cliente==5){}
                    else if(opcion_cliente==6){continuarCliente=false;}
                    else{System.out.println("Por favor seleccione una opción válida.");}
                    }catch(NumberFormatException e){}}
            }

            else{System.out.println("\n\t>>> Credenciales incorrectas, intentelo de nuevo.");}          
            }
            else if(opcion_seleccionada==2){
                nuevoCliente();
            }
            else if(opcion_seleccionada==3){
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
    public static void nuevoCliente(){
        try {
            System.out.println("\n¡Bienvenido a nuestro sistema!\n");
            int cedula = Integer.parseInt(input("Por favor ingrese su número de documento de identidad"));
            if (Usuario.checkCedulas(cedula)==false){
            String nombre = input("Por favor ingrese su nombre completo");
            String correo = input("Por favor ingrese su correo electrónico");
            long telefono = Long.parseLong(input("Por favor ingrese su número de teléfono celular"));
            int anacimiento = Integer.parseInt(input("Por favor ingrese su año de nacimiento"));
            int mnacimiento = Integer.parseInt(input("Por favor ingrese su mes de nacimiento"));
            int dnacimiento = Integer.parseInt(input("Por favor ingrese su día de nacimiento"));
            String nacionalidad = input("Por favor ingrese su pais de nacionalidad").toUpperCase();
            int fnacimiento = anacimiento + mnacimiento*10000 + dnacimiento*1000000;
            //ddmmaaaa
            boolean mayor = esMayorDeEdad(anacimiento, mnacimiento, dnacimiento);
            if (!mayor) {
                System.out.println("\n>No es posible registrarlo como cliente porque es menor de edad.");
            } else {
                System.out.println("\n>Ahora necesita crear su usuario y contraseña.");
                boolean continuar=true;
                while (continuar){
                //COMPARACION
                String login = input("Por favor ingrese su nombre de usuario");
                if(((Usuario.checkNombresLogins(login)==false))) {   
                    String password = input("Por favor ingrese una contraseña");
                    Cliente cliente = new Cliente(login, password, cedula, nombre, correo, telefono, fnacimiento, nacionalidad);
                    System.out.println("\n>A continuación tiene que ingresar una licencia de conducción y un medio de pago.");
                    cliente.setLicencia();
                    if(cliente.getLicencia()!=null){
                    System.out.println("\n>Nuestro sistema solamente acepta tarjetas de crédito como medio de pago");
                    cliente.setTarjeta();
                    if (cliente.getTarjeta()!=null){
                    Usuario.addNombreLogin(login);
                    Usuario.addNumCedulas(cedula);
                    Usuario.addCliente(cliente);
                    Usuario.addLicencia(cliente.getLicencia());
                    continuar=false;
                    }
                    else{continuar=false;}                    
                }
                    else{continuar=false;}
                }
                else{System.out.println("\n>El nombre de usuario ya ha sido utilizado. Por favor, elija otro.");}
                }}}
        else{
                    System.out.println("\n>Ya existe una cuenta con esta cédula. ¿Desea cambiar su contraseña?");
                    System.out.println("1.Sí");
                    System.out.println("2.No(ó cualquier otro número)");
                    int opcion = Integer.parseInt(input("Por favor seleccione una opción"));    
                    if (opcion==1){
                        Cliente cliente=Usuario.assignCliente(cedula);
                        int fechaNac_u=Integer.parseInt(input("Ingrese su fecha de nacimiento en el formato ddmmaaaa para verificar que es usted"));
                        if (cliente.getFechaNacimiento()==fechaNac_u){
                        String new_password=input("Ingrese una nueva contraseña");
                        cliente.setPassword(new_password);
                        System.out.println("\n>Su contraseña se ha actualizado correctamente.");
                        }
                        else{
                        System.out.println("\n>Verificación fallida.");
                        }
                    }
                }}
            catch(NumberFormatException e) {
        System.out.println("\n>Debe ingresar los datos requeridos en el formato adecuado para que la creación de la cuenta sea exitosa.");}}

    public static boolean esMayorDeEdad(int anho, int mes, int dia) {
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
    System.out.println("\n\t\t>>> Bienvenid@ a "+Inventario.getNombreCompania()+" :)");    
    MenuInicial();
    
	}
}
