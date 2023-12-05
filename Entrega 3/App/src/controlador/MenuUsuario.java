package controlador;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import modelo.Cliente;
import modelo.EmpleadoAtencion;
import modelo.EmpleadoTecnico;
import modelo.Inventario;
import modelo.Licencia;
import modelo.Sede;
import modelo.Tarjeta;
import modelo.Usuario;
import modelo.personal;

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

    public static void nuevoCliente(){
        try {
            System.out.println("\n¡Bienvenido a nuestro sistema!\n");
            int cedula = Integer.parseInt(input("Por favor ingrese su número de documento de identidad"));
            if (Usuario.checkCedulas(cedula)==false){
            String nombre = input("Por favor ingrese su nombre completo");
            String correo = input("Por favor ingrese su correo electrónico");
            long telefono = Long.parseLong(input("Por favor ingrese su número de teléfono celular"));
            int anacimiento = Integer.parseInt(input("Por favor ingrese el número de su año de nacimiento"));
            int mnacimiento = Integer.parseInt(input("Por favor ingrese el número de su mes de nacimiento"));
            int dnacimiento = Integer.parseInt(input("Por favor ingrese el número de su día de nacimiento"));
            String nacionalidad = input("Por favor ingrese su pais de nacionalidad").toUpperCase();
            int fnacimiento = anacimiento*10000 + mnacimiento*100 + dnacimiento;
            //ddmmaaaa
            boolean mayor = Cliente.esMayorDeEdad(anacimiento, mnacimiento, dnacimiento);
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
                    Licencia licencia= crearLicencia();
                    cliente.setLicencia(licencia);
                    if(cliente.getLicencia()!=null){
                    System.out.println("\n>Nuestro sistema solamente acepta tarjetas de crédito como medio de pago");
                    setTarjeta(cliente);
                    if (cliente.getTarjeta()!=null){
                    Usuario.addNombreLogin(login);
                    Usuario.addNumCedulas(cedula);
                    Usuario.addCliente(cliente);
                    System.out.println("\n>Su cuenta se registró con éxito!\n");

                    continuar=false;
                    }
                    else{
                        System.out.println("\n>Se cancela la creación de cuenta dado que es necesario ingresar un método de pago válido\n");

                        continuar=false;}                    
                }
                    else{
                        System.out.println("\n>Se cancela la creación de cuenta dado que es necesario ingresar una licencia válida\n");
                        continuar=false;}
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
                        int fechaNac_u=Integer.parseInt(input("Ingrese su fecha de nacimiento en el formato aaaammdd para verificar que es usted"));
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
    
    public static void actualizarCliente(Cliente cliente){
        boolean continuarCliente=true;
        while (continuarCliente){
            System.out.println("\nPor favor seleccione que desea modificar\n");
            System.out.println("1. Mi correo electrónico");
            System.out.println("2. Mi número de teléfono");
            System.out.println("3. Mi Licencia de Conducción");
            System.out.println("4. Mi Tarjeta de Crédito");
            System.out.println("5. He terminado\n");
            int opcion_cliente = Integer.parseInt(input("Por favor seleccione una opción"));
            try {
                Licencia licenciaAnterior=cliente.getLicencia();
                if(opcion_cliente==1){
                    String nuevoCorreo = input("Por favor ingrese su nuevo correo electrónico");
                    cliente.setMail(nuevoCorreo);
                    System.out.println("Correo electrónico actualizado correctamente.");
                    }
                else if(opcion_cliente==2){
                    long nuevoTelefono = Long.parseLong(input("Por favor ingrese su nuevo número de teléfono"));
                    cliente.setTelefono(nuevoTelefono);
                    System.out.println("Número de teléfono actualizado correctamente.");
                    }
                else if(opcion_cliente==3){
                    Cliente.getListaLicencias().remove(cliente.getLicencia());
                    cliente.setLicencia(null);
                    Licencia licencia=crearLicencia();
                    cliente.setLicencia(licencia);}
                else if(opcion_cliente==4){setTarjeta(cliente);}
                else if(opcion_cliente==5){continuarCliente=false;
                    System.out.println(">\nInformación actualizada.\n");}
                else{System.out.println("Por favor seleccione una opción válida.");}
                if (cliente.getLicencia()==null){cliente.setLicencia(licenciaAnterior);}
                } catch(NumberFormatException e){System.out.println("\n>Debe ingresar los datos requeridos en el formato adecuado para que la creación de la cuenta sea exitosa.");}
            }
        }
    
    public static Licencia crearLicencia(){
        Licencia retorno=null;
        try{
        boolean continuar=true;
        while(continuar){
        int numerolicencia = Integer.parseInt(input("\nPor favor ingrese el número de su licencia de conducción"));
        if (Usuario.checkLicencia(numerolicencia)==false){
        int expedicion = Integer.parseInt(input("Por favor ingrese la fecha de expedición de su licencia(en formato aaaammdd)"));            
        int vencimiento = Integer.parseInt(input("Por favor ingrese la fecha de vencimiento de su licencia(en formato aaaammdd)"));
        String pais = input("Por favor ingrese el país de expedición de su licencia").toUpperCase();
        Licencia licencia = new Licencia(numerolicencia, expedicion, vencimiento, pais);
        int fechaActual= Integer.parseInt(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")));
        if(Usuario.checkVencimientoLicencia(licencia,0,0,0)==false&& expedicion<=fechaActual){
            retorno=licencia;
            Usuario.addLicencia(licencia);
            //MIENTRAS ACTUALIZO NUEVA LICENCIA DEBO "BORRAR" LA ANTERIOR DE LA LISTA DE LICENCIAS
            continuar=false;
            }
            else{
                System.out.println("\n>La licencia ingresada ya caducó/no es válida, desea ingresar otra?");
                System.out.println("1.Sí");
                System.out.println("2.No(ó cualquier otro número)");
                int opcion = Integer.parseInt(input("Por favor seleccione una opción"));
                if(opcion==2){
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
                continuar=false;  
            }
        }
        }
        }catch(NumberFormatException e){System.out.println("\n>Debe ingresar los datos requeridos en el formato adecuado para que la creación de la cuenta sea exitosa.");}   
        return retorno;
    
    }

    public static void setTarjeta(Cliente cliente){
        try{
        boolean continuar=true;
        while(continuar){
        Long numerotarjeta = Long.parseLong(input("\nPor favor ingrese el número de su tarjeta de crédito (en formato 1111222233334444)"));
        int vencimiento_2 = Integer.parseInt(input("Por favor ingrese la fecha de vencimiento de su tarjeta de crédito(en formato aaaammdd)"));
        String marca = input("Por favor ingrese la marca de su tarjeta");
        String titular = input("Por favor ingrese el nombre de la persona o entidad titular de la tarjeta");
        Tarjeta tarjeta = new Tarjeta(numerotarjeta,false,0L, vencimiento_2, marca, titular);
        if (tarjeta.checkVencimientoTarjeta(0,0,0)==false){
        cliente.setTarjeta(tarjeta);
        continuar=false;
        }
        else{
        System.out.println("\n>La tarjeta ingresada ya caducó, desea ingresar otra?");
        System.out.println("1.Sí");
        System.out.println("2.No(ó cualquier otro número)");
        int opcion = Integer.parseInt(input("Por favor seleccione una opción"));
        if(opcion==2){
            continuar=false;
            }  
        }
        }
        }
    catch(NumberFormatException e){System.out.println("\n>Debe ingresar los datos requeridos en el formato adecuado para que la creación de la cuenta sea exitosa.");}
    }
    
    public static void actualizarPassword(Cliente cliente){
        String nueva_contraseña = input("Ingrese una nueva contraseña");
        cliente.setPassword(nueva_contraseña);
        System.out.println(">Contraseña actualizada\n");
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
