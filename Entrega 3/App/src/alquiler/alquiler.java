package alquiler;
import java.time.LocalDate;
import java.util.ArrayList;
import alquiler.Reserva;
import inventario.Seguro;
import usuario.Conductor;

public class Alquiler{
    private int idAlquiler;
    private double pagoFinal;
    private Reserva reserva;
    private ArrayList<Conductor> conductores;
    private ArrayList<Seguro> seguros;
    public Alquiler(Reserva reserva){
        this.reserva=reserva;
    }
    public int getID(){
        return this.idAlquiler;
    }
    public double getPagoFinal(){
        return this.pagoFinal;
    }
    public ArrayList<Conductor> getConductores(){
        return this.conductores;
    }
    public ArrayList<Seguro> Seguros(){
        return this.seguros;
    }
    public Reserva getReserva(){
        return this.reserva;
    }
    public void setPagoFinal(Reserva reserva){
        this.reserva=reserva;
    }
    
    public void setConductores(Conductor conductor){
        this.conductores.add(idAlquiler, conductor);;
    }
    public void setSeguros(Seguro seguro){
        this.seguros.add(idAlquiler, seguro);
    }
    public String crearReciboAlquiler(){
        return "Recibo creado correctamente";
    }
}