package usuario;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Licencia {
    private int numeroLicencia;
    private int fechaExpedicion;
    private int fechaVencimiento;
    private String paisExpedicion;
    private String imagenLicencia;
    
    public Licencia(int numeroLicencia, int fechaExpedicion, int fechaVencimiento, String paisExpedicion) {
        this.numeroLicencia = numeroLicencia;
        this.fechaExpedicion = fechaExpedicion;
        this.fechaVencimiento = fechaVencimiento;
        this.paisExpedicion = paisExpedicion;
        this.imagenLicencia="No disponible :c";
    }
    
    public int getNumeroLicencia() {
        return this.numeroLicencia;
    }

    public int getFechaExpedicion() {
        return this.fechaExpedicion;
    }

    public int getFechaVencimiento() {
        return this.fechaVencimiento;
    }

    public String getPaisExpedicion() {
        return this.paisExpedicion;
    }
    public String getImagenLicencia(){
        return this.imagenLicencia; 
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
