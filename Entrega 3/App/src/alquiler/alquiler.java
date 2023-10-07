package alquiler;
import java.util.ArrayList;
import inventario.Seguro;
import usuario.Conductor;

public class Alquiler{
    private int idAlquiler;
    private double pagoFinal;
    private Reserva reserva;
    private ArrayList<Conductor> conductores;
    private ArrayList<Seguro> seguros;
    private ArrayList<PagoExcedente> pagosExcedentes;
    private static ArrayList<Alquiler> listaAlquileres;
    public Alquiler(Reserva reserva){
        this.idAlquiler= reserva.getID();
        this.pagoFinal=-1;
        this.reserva=reserva;
        this.conductores= new ArrayList<Conductor>();
        this.seguros= new ArrayList<Seguro>();
        this.pagosExcedentes= new ArrayList<PagoExcedente>();
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
    public ArrayList<Seguro> getSeguros(){
        return this.seguros;
    }
    public ArrayList<PagoExcedente> getPagosExcedentes(){
        return this.pagosExcedentes;
    }
    public Reserva getReserva(){
        return this.reserva;
    }
    public static ArrayList<Alquiler> getListaAlquileres(){
        return listaAlquileres;
    }
    public void setPagoFinal(int valor){
        this.pagoFinal=valor;
    }
    public double calcularTarifaFinal(){
        return 0.1;
    }
    public void addConductor(Conductor conductor){
        this.conductores.add(conductor);
    }
    public void addSeguro(Seguro seguro){
        this.seguros.add(seguro);
    }
    public void addPagoExcedente(PagoExcedente pago){
        this.pagosExcedentes.add(pago);
    }
    public String crearReciboAlquiler(){
        return "Recibo creado correctamente";
    }
    public static void addAlquiler(Alquiler alquiler){
        if (listaAlquileres==null){ listaAlquileres= new ArrayList<Alquiler>();}
        listaAlquileres.add(alquiler);
    }
    public static Alquiler assignAlquiler(int id_alquiler){
        Alquiler retorno = null;
        for(Alquiler i: Alquiler.getListaAlquileres()){
            if(i.getID()==id_alquiler){
            retorno= i;
            break;
            }}
        return retorno;
    }
}
