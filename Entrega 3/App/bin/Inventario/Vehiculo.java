package Inventario;


public class Vehiculo {
    private  String placa;
    private  String marca;
    private  String modelo;
    private  String color;
    private  String tipoTransmision;
    private  String ubicacionGPS;
    private  String estado;
    private boolean averiado;
    // private list<Evento> historialEvento;
    // private list<Alquiler> historialAlquiler;
    // private list<Reserva> reservasActivas;
    // private  Sede sede;
    // private Categoria categoria;


    public Vehiculo(String placa, String marca, String modelo, String color, String tipoTransmision, String ubicacionGPS){
        this.placa=placa;
        this.marca=marca;
        this.modelo=modelo;
        this.color=color;
        this.tipoTransmision=tipoTransmision;
        this.ubicacionGPS=ubicacionGPS;

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
    
    //public void addEvento(Evento evento, list<Evento> historialEvento){
        //historialEvento.add(evento);}
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
