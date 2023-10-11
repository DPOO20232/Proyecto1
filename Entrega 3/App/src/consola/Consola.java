package consola;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import inventario.Inventario;
import usuario.Cliente;
import usuario.Usuario;
import usuario.personal;
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
            if (personal.checkLoginAdmin(login,password)==true){
                perfil="Admin";
                System.out.println(">>>\n\t\tBienvenid@, Admin!");
            System.out.println("\nOpciones\n");
            System.out.println("1.Crear categoría de vehículo");
			System.out.println("2. Añadir vehículo al inventario");
			System.out.println("3. Eliminar vehículo al inventario");
			System.out.println("4. Obtener historial de un vehículo");
			System.out.println("5. Cambiar sede de un vehículo (traslado interno)");
			System.out.println("6. Crear un seguro");
			System.out.println("7. Modificar precio de un seguro");
            System.out.println("8. Modificar descripción de un seguro");
            System.out.println("9. Eliminar seguro");
            System.out.println("10. Registrar una nueva sede");
            System.out.println("11. Modificar nombre de una sede");
            System.out.println("12. Modificar horario de atención de una sede");
            System.out.println("13. Modificar dirección de una sede");
            System.out.println("14. Actualizar información de  un administrador local");
            System.out.println("15. Registrar un administrador local");
            System.out.println("16. Actualizar periodo de temporada alta");
            System.out.println("17. Actualizar periodo de temporada baja");
            System.out.println("18. Salir de la aplicación\n");

            }
            else if (personal.checkLoginPersonal(login, password)!=null){
                perfil=(personal.checkLoginPersonal(login, password)).getTipoPersonal();
                if (perfil.equals("AdminLocal")){

                }
                else if (perfil.equals("EmpleadoAtencion")){

                }
                else {
                    //aqui va EmpleadoTecnico

                }
            }
            else if (Usuario.checkLoginCliente(login, password)!=null){
                Cliente cliente= Usuario.checkLoginCliente(login, password);
                System.out.println("\n\t>>>Bienvenid@, "+cliente.getNombre());



            }
            else{System.out.println("\n\t>>> Credenciales incorrectas, intentelo de nuevo.");}          
            }
            else if(opcion_seleccionada==2){
                //chequear que nadie tenga ese login y usuario (en caso de cliente tampoco licencia)
            }
            else if(opcion_seleccionada==3){
                Inventario.closeSistema();
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
    
    //1) crear usuario tipo cliente
    //2) iniciar sesion   
    //admin (rf 1-14) (nico)
    //admin local(15-17)
    //cliente - personalatencion(19-20)
    //personalatencion(en reserva->21,25-27)
    //personalTecnico(20)
    //cliente (24-27)
    
   
    public static void NuevoCliente(){
<<<<<<< HEAD
    int cedula;
    boolean existe=false;
        
=======
        int cedula;
        boolean existe=false;

>>>>>>> 5036c82ee8a1dad3e6ac51c01376a6d0573371cf
    //josé
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

