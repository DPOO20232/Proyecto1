package inventario;
import java.util.ArrayList;
import java.util.List;

import alquiler.Alquiler;


public class Vehiculo {
    private  String placa;
    private  String marca;
    private  String modelo;
    private  String color;
    private  String tipoTransmision;
    private  String ubicacionGPS;
    private  String estado;
    private boolean averiado;
    private  Sede sede;
    private Categoria categoria;
    private List<Evento> historialEvento;
    private List<Alquiler> historialAlquiler;
    private List<Reserva> reservasActivas;



    public Vehiculo(String placa, String marca, String modelo, String color, String tipoTransmision, String ubicacionGPS,String estado,boolean averiado, Categoria categoria,Sede sede){
        this.placa=placa;
        this.marca=marca;
        this.modelo=modelo;
        this.color=color;
        this.tipoTransmision=tipoTransmision;
        this.ubicacionGPS=ubicacionGPS;
        this.estado=estado;
        this.averiado=averiado;
        this.sede=sede;
        this.categoria=categoria;
        this.historialEvento=new ArrayList<Evento>();
        this.historialAlquiler=new ArrayList<Alquiler>();
        this.reservasActivas=new ArrayList<Reserva>();
    }
    public String getPlaca(){
        return this.placa;
    }
    public String getMarca(){
        return this.marca;
    }
    public String getModelo(){
        return this.modelo;
    }
    public String getEstado(){
        return this.estado;
    }
    public boolean getAveriado(){
        return this.averiado;
    }
    // public list<Evento> getHistorialEvento(){return this.historialEvento}
    // public list<Alquiler> getHistorialAlquiler(){return this.historialAlquiler}
    // public list<Reserva> getReservasActivas(){return this.reservasActivas}

    public void setPlaca(){
        this.placa=placa;
    }
    public void setMarca(){
        this.marca=marca;
    }
    public void setModelo(){
        this.modelo=modelo;
    }
    public void setEstado(){
        this.estado=estado;
    }
    public void setAveriado(){
        this.averiado=averiado;
    }
    
    public void addEvento(Evento evento){
        this.historialEvento.add(evento);}
    public void addReservasActivas(Reserva reserva){
        this.reservasActivas.add(reserva);}
    public void addAlquiler(Alquiler alquiler){
        this.historialAlquiler.add(alquiler);}
    //public void addAlquiler(Alquiler alquiler, list<Alquiler> historialAlquiler){
        //historialAlquiler.add(alquiler);}
    //public void addReservasActivas(Reserva reserva, list<Reserva> reservasActivas){
        //reservasActivas.add(reserva);}
    //public void deleteEvento (Evento evento, list<Evento> historialEvento){
        //historialEvento.remove(evento)}
    //public void checkUltimoEvento(list<Evento> historialEvento){
        //int indice= historialEvento.size()-1; 
        //Evento ultimoEvento=historialEvento(indice);}
}
