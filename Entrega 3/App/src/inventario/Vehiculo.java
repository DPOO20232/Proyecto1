package inventario;
import java.util.ArrayList;
import java.util.List;
<<<<<<< HEAD

import alquiler.Alquiler;
import alquiler.Reserva;

=======
import inventario.Evento;
import alquiler.alquiler;
import alquiler.Reserva;
import inventario.Sede;
import inventario.Categoria;
>>>>>>> NP

public class Vehiculo {
    private  String placa;
    private  String marca;
    private  String modelo;
    private  String color;
    private  String tipoTransmision;
    private  String ubicacionGPS;
    private  String estado;
    private boolean averiado;
<<<<<<< HEAD
    private ArrayList<Evento> historialEvento;
    private ArrayList<alquiler> historialAlquiler;
    private List<Reserva> reservasActivas;
    private  Sede sede;
    private Categoria categoria;


    public Vehiculo(String placa, String marca, String modelo, String color, String tipoTransmision, String ubicacionGPS,String estado,boolean averiado,List<Evento> historialEvento,ArrayList<alquiler> historialAlquiler){
=======
    private  Sede sede;
    private Categoria categoria;
    private List<Evento> historialEvento;
    private List<Alquiler> historialAlquiler;
    private List<Reserva> reservasActivas;



    public Vehiculo(String placa, String marca, String modelo, String color, String tipoTransmision, String ubicacionGPS,String estado,boolean averiado, Categoria categoria,Sede sede){
>>>>>>> main
        this.placa=placa;
        this.marca=marca;
        this.modelo=modelo;
        this.color=color;
        this.tipoTransmision=tipoTransmision;
        this.ubicacionGPS=ubicacionGPS;
        this.estado=estado;
        this.averiado=averiado;
<<<<<<< HEAD
        this.historialEvento=new ArrayList<>();
        this.historialAlquiler=new ArrayList<>();
=======
        this.sede=sede;
        this.categoria=categoria;
        this.historialEvento=new ArrayList<Evento>();
        this.historialAlquiler=new ArrayList<Alquiler>();
        this.reservasActivas=new ArrayList<Reserva>();
>>>>>>> main
    }
    public String getPlaca(){return this.placa;}
    public String getMarca(){return this.marca;}
    public String getModelo(){return this.modelo;}
    public String getColor(){return this.color;}
    public String getTipoTransmicion(){return this.tipoTransmision;}
    public String getUbicacionGPS(){return this.ubicacionGPS;}
    public String getEstado(){return this.estado;}
    public boolean getAveriado(){return this.averiado;}
    public Sede getSede(){return this.sede;}
    public Categoria getCategoria(){return this.categoria;}
    public List<Evento> getHistorialEventos(){return this.historialEvento;}
    public Evento getUltimoEvento(){return this.historialEvento.get(historialEvento.size()-1);}
    public List<Alquiler> getHistorialAlquileres(){return this.historialAlquiler;}
    public List<Reserva> getReservasActivas(){return this.reservasActivas;}

    public void setPlaca(String placa){this.placa=placa;}
    public void setColor(String color){this.color=color;}
    public void setEstado(String estado){
        //para definir estado en el menu dar opciones que representen los estados definidos
        this.estado=estado;}
    public void setAveriado(boolean averiado){this.averiado=averiado;}
    public void setTrasladoASede(Sede nuevaSede){
        //incluir logica de cambio de sede (crear nuevo evento, cambiar estado)
    }
    public void addEvento(Evento evento){
        this.historialEvento.add(evento);}
    public void addReservaActiva(Reserva reserva){
        this.reservasActivas.add(reserva);}
    public void addAlquiler(Alquiler alquiler){
        this.historialAlquiler.add(alquiler);}
    public void eliminarReservaActiva(int idReserva){
        List<Reserva> reservas_activas= this.getReservasActivas();
        int idEliminar=this.getID();
        for (int i = 0; i < reservas_activas.size(); i++){
            Reserva i_reserva= reservas_activas.get(i);
            if (i_reserva.getID()==(idEliminar)){
                reservas_activas.remove(i);
                break;
            }
        }
    public boolean estaDisponible(int fecha1, int hora1, int fecha2, int hora2){
        //implementacion: revisar todas las reservas para ver si esta ocupado en ese rango
    }
    }
<<<<<<< HEAD
    public void setEstado(){
        this.estado=estado;
    }
    public void setAveriado(){
        this.averiado=averiado;
    }
    
    public void addEvento(Evento evento){
        this.historialEvento.add(evento);}
    public void addAlquiler(alquiler alquiler){
        this.historialAlquiler.add(alquiler);}
    public void addReservasActivas(Reserva reserva, list<Reserva> reservasActivas){
        reservasActivas.add(reserva);}
    //public void deleteEvento (Evento evento, list<Evento> historialEvento){
        //historialEvento.remove(evento)}
    //public void checkUltimoEvento(list<Evento> historialEvento){
        //int indice= historialEvento.size()-1; 
        //Evento ultimoEvento=historialEvento(indice);}
    
    
=======

>>>>>>> main
}
