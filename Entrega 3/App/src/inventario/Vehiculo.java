package inventario;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import inventario.Evento;
import alquiler.alquiler;
import alquiler.Reserva;
import inventario.Sede;
import inventario.Categoria;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
    private List<alquiler> historialAlquiler;
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
        this.historialAlquiler=new ArrayList<alquiler>();
        this.reservasActivas=new ArrayList<Reserva>();
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
    public List<alquiler> getHistorialAlquileres(){return this.historialAlquiler;}
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
    public void addAlquiler(alquiler alquiler){
        this.historialAlquiler.add(alquiler);}
    public void eliminarReservaActiva(int idReserva){
        List<Reserva> reservas_activas= this.getReservasActivas();
        for (int i = 0; i < reservas_activas.size(); i++){
            Reserva i_reserva= reservas_activas.get(i);
            if (i_reserva.getID()==(idReserva)){
                reservas_activas.remove(i);
                break;
            }
        }}
    public boolean estaDisponible(int fecha1, int hora1, int fecha2, int hora2){
        boolean reservaInPeriodoReserva=false;
        boolean eventoInPeriodoReserva=false;

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmm");
        Date fhInicioReserva= new Date();
        Date fhFinReserva= new Date();
        try {
            fhInicioReserva = dateFormat.parse(String.format("%08d%04d", fecha1,hora1));
            fhFinReserva = dateFormat.parse(String.format("%08d%04d",fecha2,hora2));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int numReservasActivas=this.getReservasActivas().size();
        int numEventos=this.getHistorialEventos().size();
        if (numEventos>1){
            Evento ultimoEvento= this.getHistorialEventos().get(numEventos-1);
            Date fhInicioEvento= new Date();
            Date fhFinEvento= new Date();
            try {
                fhInicioEvento = dateFormat.parse(String.format("%08d%04d", ultimoEvento.getFechaInicio(), ultimoEvento.getHoraInicio()));
                fhFinEvento = dateFormat.parse(String.format("%08d%04d", ultimoEvento.getFechaFin(), ultimoEvento.getHoraFin()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            eventoInPeriodoReserva=(fhFinEvento.before(fhInicioReserva) || fhInicioEvento.after(fhFinReserva));
        }

        if(numReservasActivas==0){
            reservaInPeriodoReserva=false;
        }
        else{
            for (Reserva i: this.getReservasActivas()){
                Date i_inicioReserva= new Date();
                Date i_finReserva= new Date();
                try {
                    i_inicioReserva = dateFormat.parse(String.format("%08d%04d", i.getFechaRecoger(), i.getHoraRecoger()));
                    i_finReserva = dateFormat.parse(String.format("%08d%04d", i.getFechaEntregar(),i.getHoraEntregar()));
                    if (i_finReserva.before(fhInicioReserva) || i_inicioReserva.after(fhFinReserva)){
                        reservaInPeriodoReserva=true;
                        break;
                    }
                } catch (ParseException e) {
                e.printStackTrace();
            }
            }
        }
        if ((reservaInPeriodoReserva==false) && (eventoInPeriodoReserva==false)){
            return true;
        }
        else{
            return false;
        }
        //implementacion: revisar todas las reservas para ver si esta ocupado en ese rango
    }
}
