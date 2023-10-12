package consola;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;

import inventario.Inventario;
import usuario.Cliente;
import usuario.Usuario;
import usuario.personal;
import usuario.Licencia;
import usuario.Tarjeta;
import usuario.AdminLocal;
public class Consola {
    public static void MenuInicial(){
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
                //ESTAS CLASES HAY QUE PASARLAS A ADMIN
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
                System.out.println("17. Salir de la aplicación\n");
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
            }catch(NumberFormatException e){System.out.println("Ingrese solo números en los campos correspondientes");}}}
            //MENU PERSONAL
            else if (personal.checkLoginPersonal(login, password)!=null){
                perfil=(personal.checkLoginPersonal(login, password)).getTipoPersonal();
                personal adminlocal= personal.checkLoginPersonal(login, password);
                    if (perfil.equals("AdminLocal")){
                    boolean continuarAdminL=true;
                    while (continuarAdminL==true){
                    System.out.println("\n\t\t>>>Hola, Admin local!");
                    System.out.println("1. Registrar empleado de atención categoría");
                    System.out.println("2. Crear categoría");
                    System.out.println("3. Crear categoría");

                    int opcion_admin = Integer.parseInt(input("Por favor seleccione una opción"));
                    }
                //ESTAS CLASES HAY QUE PASARLAS A ADMIN

                }
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



            }
            else{System.out.println("\n\t>>> Credenciales incorrectas, intentelo de nuevo.");}          
            }
            else if(opcion_seleccionada==2){
                nuevoCliente();
                //chequear que nadie tenga ese login y usuario (en caso de cliente tampoco licencia)
            }
            else if(opcion_seleccionada==3){
                Inventario.closeSistema();
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
            String nombre = input("Por favor ingrese su nombre completo");
            String correo = input("Por favor ingrese su correo electrónico");
            int telefono = Integer.parseInt(input("Por favor ingrese su número de teléfono celular"));
            int anacimiento = Integer.parseInt(input("Por favor ingrese su año de nacimiento"));
            int mnacimiento = Integer.parseInt(input("Por favor ingrese su mes de nacimiento"));
            int dnacimiento = Integer.parseInt(input("Por favor ingrese su día de nacimiento"));
            String nacionalidad = input("Por favor ingrese su nacionalidad");
            int fnacimiento = anacimiento + mnacimiento*10000 + dnacimiento*1000000;
            //ddmmaaaa
            boolean menor = esMayorDeEdad(anacimiento, mnacimiento, dnacimiento);
            if (menor) {
                System.out.println("No es posible registrarlo como cliente porque es menor de edad.");
            } else {
                System.out.println("Ahora necesita crear su usuario y contraseña.");
                String login = input("Por favor ingrese su nombre de usuario");
                boolean continuar=true;
                while (continuar){
                String password = input("Por favor ingrese una contraseña");
                if((personal.checkLoginPersonal(login, password).equals(null)&&(personal.checkLoginAdmin(login, password)==false)&&(personal.checkLoginCliente(login, password).equals(null)))) {                    
        
                    Cliente cliente = new Cliente(login, password, cedula, nombre, correo, telefono, fnacimiento, nacionalidad);
                    Usuario.addCliente(cliente);
                    System.out.println("A continuación hay tiene que ingresar una licencia de conducción y un medio de pago");
                    int numerolicencia = Integer.parseInt(input("Por favor ingrese el número de su licencia de conducción"));
                    int expedicion = Integer.parseInt(input("Por favor ingrese la fecha de expedición de su licencia(en formato ddmmaaaa)"));
                    int vencimiento = Integer.parseInt(input("Por favor ingrese la fecha de vencimiento de su licencia(en formato ddmmaaaa)"));
                    String pais = input("Por favor ingrese el país de expedición de su licencia");
                    Licencia licencia = new Licencia(numerolicencia, expedicion, vencimiento, pais);
                    cliente.setLicencia(licencia);
                    Usuario.addLicencia(licencia);
                    // Licencia a lista de licencias
                    System.out.println("Nuestra página solamente acepta tarjetas de crédito como medio de pago");
                    Long numerotarjeta = Long.parseLong(input("Por favor ingrese el número de su tarjeta de crédito"));
                    int vencimiento_2 = Integer.parseInt(input("Por favor ingrese la fecha de vencimiento de su tarjeta de crédito(en formato mmaaaa)"));
                    String marca = input("Por favor ingrese la marca de su tarjeta");
                    String titular = input("Por favor ingrese el nombre de la persona o entidad titular de la tarjeta");
                    Tarjeta tarjeta = new Tarjeta(numerotarjeta, vencimiento_2, marca, titular);
                    // Tarjeta a tarjetas
                    cliente.setTarjeta(tarjeta);
                    continuar=false;
                } else {
                    System.out.println("El nombre de usuario ya ha sido utilizado. Por favor, elija otro.");
                }}}}catch(NumberFormatException e) {
        System.out.println("Debe ingresar los datos requeridos para que la creación de la cuenta sea exitosa.");}}

    public static boolean esMayorDeEdad(int anho, int mes, int dia) {
        boolean mayor = false;
        Calendar fechaActual = Calendar.getInstance();
        int diaactual = fechaActual.get(Calendar.DAY_OF_MONTH);
        int mesactual = fechaActual.get(Calendar.MONTH) + 1;
        int anhoactual = fechaActual.get(Calendar.YEAR);

        if (anho < (anhoactual - 18)) {
            mayor = true;
        }
        if ((anho==(anhoactual-18) && mes < mesactual)) {
            mayor = true;
        }
        if ((anho==(anhoactual-18) && mes == mesactual && dia < diaactual)) {
            mayor = true;
        }

        return mayor;
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
    public static void main(String[] args)
	{
    Inventario.loadSistema();
    System.out.println("\n\t\t>>> Bienvenid@ a "+Inventario.getNombreCompania()+" :)");    
    MenuInicial();
    
	}
}