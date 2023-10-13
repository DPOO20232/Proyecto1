package consola;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;

import alquiler.Reserva;
import inventario.Inventario;
import inventario.Sede;
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
            System.out.println("\nOpciones de la aplicación\n");
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
                while (continuarAdmin==true){
                //ESTOS METODOS HAY QUE PASARLOS A ADMIN
                System.out.println("\n\t\t>>>Hola, Admin!");
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
                    //TODO
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
                    while (continuarAdminL==true){
                    System.out.println("\n\t\t>>>Hola, Admin local!");
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

                }
                else {
                    //aqui va EmpleadoTecnico

                }
            }
            //MENU CLIENTE
            else if (Usuario.checkLoginCliente(login, password)!=null){
                Cliente cliente= Usuario.checkLoginCliente(login, password);
                System.out.println("\n\t>>>Bienvenid@, "+cliente.getNombre());
                while (continuarAdminL==true){
                    System.out.println("1. Crear una reserva");
                    System.out.println("2. Modificar datos de mi reserva");
                    System.out.println("3. Cancelar reserva");
                    System.out.println("4. Modificar mis datos");
                    System.out.println("5. Salir de la aplicación\n");
                    int opcion_cliente = Integer.parseInt(input("Por favor seleccione una opción"));
                    try{
                    boolean ensede = false
                    if (opcion_adminL==1){Reserva.crearReserva(ensede, cliente);}
                    //else if(opcion_adminL==2){}
                    //else if(opcion_adminL==3){}
                    //else if(opcion_adminL==4){}
                    else if(opcion_cliente==5){continuarAdminL=false;}
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
                    boolean continuar2=true;
                    System.out.println("\n>A continuación tiene que ingresar una licencia de conducción y un medio de pago.");
                    while(continuar2){
                    int numerolicencia = Integer.parseInt(input("Por favor ingrese el número de su licencia de conducción"));
                    if (Usuario.checkLicencia(numerolicencia)==false){
                    int expedicion = Integer.parseInt(input("Por favor ingrese la fecha de expedición de su licencia(en formato aaaammdd)"));
                    int vencimiento = Integer.parseInt(input("Por favor ingrese la fecha de vencimiento de su licencia(en formato aaaammdd)"));
                    String pais = input("Por favor ingrese el país de expedición de su licencia");
                    Licencia licencia = new Licencia(numerolicencia, expedicion, vencimiento, pais);
                    if(Usuario.checkVencimientoLicencia(licencia,0,0,0)==false){
                    Usuario.addLicencia(licencia);
                    boolean continuar3=false;
                    System.out.println("\n>Nuestro sistema solamente acepta tarjetas de crédito como medio de pago");
                    while(continuar3){
                    Long numerotarjeta = Long.parseLong(input("Por favor ingrese el número de su tarjeta de crédito (en formato 1111222233334444)"));
                    int vencimiento_2 = Integer.parseInt(input("Por favor ingrese la fecha de vencimiento de su tarjeta de crédito(en formato aaaammdd)"));
                    String marca = input("Por favor ingrese la marca de su tarjeta");
                    String titular = input("Por favor ingrese el nombre de la persona o entidad titular de la tarjeta");
                    Tarjeta tarjeta = new Tarjeta(numerotarjeta, vencimiento_2, marca, titular);
                    if (tarjeta.checkVencimientoTarjeta(0,0,0)==false){
                    Cliente cliente = new Cliente(login, password, cedula, nombre, correo, telefono, fnacimiento, nacionalidad);
                    Usuario.addNombreLogin(login);
                    Usuario.addNumCedulas(cedula);
                    Usuario.addCliente(cliente);
                    cliente.setLicencia(licencia);
                    cliente.setTarjeta(tarjeta);
                    continuar3=false;
                    continuar2=false;
                    continuar=false;
                    }
                    else{
                        System.out.println("\n>La tarjeta ingresada ya caducó, desea ingresar otra?");
                        System.out.println("1.Sí");
                        System.out.println("2.No(ó cualquier otro número)");
                        int opcion = Integer.parseInt(input("Por favor seleccione una opción"));
                        if(opcion==2){
                            continuar3=false;
                            continuar2=false;
                            continuar=false;
                    }
                    }
                    }}
                    else{
                        System.out.println("\n>La licencia ingresada ya caducó, desea ingresar otra?");
                        System.out.println("1.Sí");
                        System.out.println("2.No(ó cualquier otro número)");
                        int opcion = Integer.parseInt(input("Por favor seleccione una opción"));
                        if(opcion==2){
                            continuar2=false;
                            continuar=false;
                        }  
                    }
                    }
                    else{
                        System.out.println("\n>Ese número de licencia esta asignado a otro cliente.");
                        System.out.println(">Desea ingresar otra licencia?");
                        System.out.println("1.Sí");
                        System.out.println("2.No(ó cualquier otro número)");
                        int opcion = Integer.parseInt(input("Por favor seleccione una opción"));
                        if(opcion==2){
                            continuar2=false;
                            continuar=false;
                        }    
                    }
                    }}
                else {
                    System.out.println("\n>El nombre de usuario ya ha sido utilizado. Por favor, elija otro.");
                }
                }
            }}
            
        else{
                    System.out.println("\n>Ya existe una cuenta con esta cédula. ¿Desea cambiar su contraseña?");
                    System.out.println("1.Sí");
                    System.out.println("2.No(ó cualquier otro número)");
                    int opcion = Integer.parseInt(input("Por favor seleccione una opción"));    
                    if (opcion==1){
                        int fechaNac_u=Integer.parseInt(input("Ingrese su fecha de nacimiento en el formato ddmmaaaa para verificar que es usted"));
                        Cliente cliente= Inventario.assignCliente(cedula);
                        if (cliente.getFechaNacimiento()==fechaNac_u){
                        String new_password=input("Ingrese una nueva contraseña");
                        cliente.setPassword(new_password);
                        System.out.println("\n>Su contraseña se ha actualizado correctamente");
                        }
                        else{
                        System.out.println("\n>Verificación fallida, contactese con soporte@rentacar.com para solicitar la actualización de su contraseña.");
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
