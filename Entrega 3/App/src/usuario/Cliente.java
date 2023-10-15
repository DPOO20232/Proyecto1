package usuario;
import java.util.Calendar;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Cliente extends Usuario{
    private int numeroCedula;
    private String nombre;
    private String mail;
    private long telefono;
    private int fechaNacimiento;
    private String nacionalidad;
    private Licencia licencia;
    private Tarjeta tarjeta;

    // Constructor
    public Cliente(String login, String password,int numeroCedula, String nombre, String mail, long telefono, int fechaNacimiento, String nacionalidad) {
        super(login, password);
        this.numeroCedula = numeroCedula;
        this.nombre = nombre;
        this.mail = mail;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;
        this.nacionalidad = nacionalidad;
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
                    Licencia licencia= Licencia.crearLicencia();
                    cliente.setLicencia(licencia);
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
 
    public Tarjeta getTarjeta() {
        return tarjeta;
    }
   
    public int getNumeroCedula() {
        return numeroCedula;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getMail() {
        return this.mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public long getTelefono() {
        return this.telefono;
    }

    public int getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getNacionalidad() {
        return this.nacionalidad;
    }
    public Licencia getLicencia() {
        return this.licencia;}

    public void setLicencia(Licencia licencia) {
        this.licencia = licencia;}

    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }
    public void setTarjeta(Tarjeta tarjeta) {
        this.tarjeta = tarjeta;}
        
    public void setTarjeta(){
        try{
        boolean continuar=true;
        while(continuar){
        Long numerotarjeta = Long.parseLong(input("\nPor favor ingrese el número de su tarjeta de crédito (en formato 1111222233334444)"));
        int vencimiento_2 = Integer.parseInt(input("Por favor ingrese la fecha de vencimiento de su tarjeta de crédito(en formato aaaammdd)"));
        String marca = input("Por favor ingrese la marca de su tarjeta");
        String titular = input("Por favor ingrese el nombre de la persona o entidad titular de la tarjeta");
        Tarjeta tarjeta = new Tarjeta(numerotarjeta, vencimiento_2, marca, titular);
        if (tarjeta.checkVencimientoTarjeta(0,0,0)==false){
        this.setTarjeta(tarjeta);
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

    public void actualizarCliente(){
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
                if(opcion_cliente==1){
                    String nuevoCorreo = input("Por favor ingrese su nuevo correo electrónico");
                    this.setMail(nuevoCorreo);
                    System.out.println("Correo electrónico actualizado correctamente.");
                }
                else if(opcion_cliente==2){
                    long nuevoTelefono = Long.parseLong(input("Por favor ingrese su nuevo número de teléfono"));
                    this.setTelefono(nuevoTelefono);
                    System.out.println("Número de teléfono actualizado correctamente.");
                }
                else if(opcion_cliente==3){
                    getListaLicencias().remove(this.getLicencia());
                    this.setLicencia(null);
                    Licencia licencia=Licencia.crearLicencia();
                    this.setLicencia(licencia);}
                else if(opcion_cliente==4){this.setTarjeta();}
                else if(opcion_cliente==5){continuarCliente=false;
                            System.out.println(">\nInformación actualizada.\n");}
                else{System.out.println("Por favor seleccione una opción válida.");}
            } catch(NumberFormatException e){System.out.println("\n>Debe ingresar los datos requeridos en el formato adecuado para que la creación de la cuenta sea exitosa.");}
        }
    }

}

