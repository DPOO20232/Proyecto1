package inventario;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import alquiler.Reserva;

public class Evento {
    
    private int idEvento;
    private int fechaInicio;
    private int fechaFin;
    private int horaInicio;
    private int horaFin;
    private String descripcion;
    private static int lastId;
    public Evento(int id,int fechaInicio, int fechaFin, int horaInicio, int horaFin, String descripcion) {
        this.idEvento=id;
        if (idEvento>lastId){lastId=idEvento;}
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.descripcion = descripcion;
    }

    public Evento(int fechaInicio, int fechaFin, int horaInicio, int horaFin, String descripcion) {
        this.setID();
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.descripcion = descripcion;
    }
    public int getID(){
        return this.idEvento;
    }
    public int getFechaInicio() {
        return this.fechaInicio;
    }

    public int getFechaFin() {
        return this.fechaFin;
    }

    public int getHoraInicio() {
        return this.horaInicio;
    }

    public int getHoraFin() {
        return this.horaFin;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setID(){
        this.idEvento=lastId+=1;
        lastId=this.getID();
    }
    public void setFechaInicio(int fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
    public void setFechaFin(int fechaFin) {
        this.fechaFin = fechaFin;
    }

    public void setHoraInicio(int horaInicio) {
        this.horaInicio = horaInicio;
    }

    public void setHoraFin(int horaFin) {
        this.horaFin = horaFin;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public static void crearEvento(String descripcion, Vehiculo vehiculo) {
        System.out.println("\n¡Bienvenido a nuestro sistema de reservas!\n");
        try { 
            boolean continuar=true;
            while(continuar){
                int finicio = Integer.parseInt(input("Por favor ingrese la fecha en la que tendrá inicio el evento(en formato aaaammdd)"));
                int ffinal = Integer.parseInt(input("Por favor ingrese la fecha en la que finalizará el evento(en formato aaaammdd)"));
                int hinicio = Integer.parseInt(input("Ingrese la hora en la que tendrá inicio el evento(en formato 24h de tipo hhmm)"));
                int hfinal = Integer.parseInt(input("Ingrese la hora en la que finalizará el evento(en formato 24h de tipo hhmm)"));
                boolean horaVinicio= Reserva.horaValida(hinicio);
                boolean horaVfin = Reserva.horaValida(hfinal);
                int fechaActual= Integer.parseInt(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")));
                if(horaVinicio&&horaVfin&&finicio>=fechaActual){
                    Evento evento = new Evento(finicio, ffinal, hinicio, hfinal, descripcion);
                    vehiculo.addEvento(evento);
                } else {System.out.println(">Las horas ingresadas no son válidas. Por favor, inténtelo nuevamente.");}
            }
        }catch(NumberFormatException e){System.out.println("\n>Ingrese los datos requeridos en el formato especificado");}
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
