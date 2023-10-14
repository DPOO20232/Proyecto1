package usuario;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


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
    public void setLicencia(){
        boolean continuar=true;
        while(continuar){
        int numerolicencia = Integer.parseInt(input("\nPor favor ingrese el número de su licencia de conducción"));
        if (Usuario.checkLicencia(numerolicencia)==false){
        int expedicion = Integer.parseInt(input("Por favor ingrese la fecha de expedición de su licencia(en formato aaaammdd)"));            int vencimiento = Integer.parseInt(input("Por favor ingrese la fecha de vencimiento de su licencia(en formato aaaammdd)"));
        String pais = input("Por favor ingrese el país de expedición de su licencia");
        Licencia licencia = new Licencia(numerolicencia, expedicion, vencimiento, pais);
        if(Usuario.checkVencimientoLicencia(licencia,0,0,0)==false){
            this.setLicencia(licencia);
            }
            else{
                System.out.println("\n>La licencia ingresada ya caducó, desea ingresar otra?");
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
        }}
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

