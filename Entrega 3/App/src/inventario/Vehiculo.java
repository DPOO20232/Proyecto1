package inventario;
import java.util.ArrayList;
import java.util.List;
import alquiler.alquiler;
import usuario.Conductor;
import alquiler.PagoExcedente;
import alquiler.Reserva;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

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
        this.estado=estado;}
    //TODO quitar atributos estado
    public void setAveriado(boolean averiado){this.averiado=averiado;}
    public void setTrasladoASede(Sede nuevaSede){
        this.sede=nuevaSede;
        int fechaActual= Integer.parseInt(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")));
        int fechaFinal=Integer.parseInt((LocalDate.now()).plusDays(1).format(DateTimeFormatter.ofPattern("yyyyMMdd")));
        LocalTime horaActual = LocalTime.now();
        int horaEnFormatoHHMM = horaActual.getHour() * 100 + horaActual.getMinute();
        if (this.actualizarEstado(fechaActual, horaEnFormatoHHMM, fechaFinal, horaEnFormatoHHMM).equals("Disponible")){
        Evento nuevoEvento= new Evento(fechaActual, fechaFinal, horaEnFormatoHHMM, horaEnFormatoHHMM, "EnTraslado");
        this.addEvento(nuevoEvento);
        Inventario.getListaEventos().add(nuevoEvento);
        this.setEstado("EnTraslado");
        System.out.println("\n>Solicitud de traslado registrada. En\n");

        }
        else{
        System.out.println("\n>El vehículo actualmente no está disponible para trasladar, monitoree el vehículo para solicitar el traslado más adelante.\n");

        }
    }
    public void addEvento(Evento evento){
        this.historialEvento.add(evento);
        Inventario.getListaEventos().add(evento);
        }
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
    //TODO cambias estaDisponible->actualizarEstado
    public String actualizarEstado(int fecha1, int hora1, int fecha2, int hora2){
        String retorno= "NoDisponible";

        try{
        boolean ocupado=false;
        DateTimeFormatter formatter =DateTimeFormatter.ofPattern("yyyyMMddHHmm");
        LocalDateTime fhInicioReserva = LocalDateTime.parse(String.format("%08d%04d", fecha1, hora1), formatter);
        LocalDateTime fhFinReserva = LocalDateTime.parse(String.format("%08d%04d", fecha2, hora2), formatter);
        int numReservasActivas=this.getReservasActivas().size();
        int numEventos=this.getHistorialEventos().size();
        if(numEventos>0){
            for (Evento i: this.getHistorialEventos()){
            LocalDateTime fhInicioEvento= LocalDateTime.parse(String.format("%08d%04d", i.getFechaInicio(), i.getHoraInicio()), formatter);
            LocalDateTime fhFinEvento= LocalDateTime.parse(String.format("%08d%04d", i.getFechaFin(), i.getHoraFin()), formatter);
            if (!fhFinEvento.isBefore(fhInicioReserva) && !fhInicioEvento.isAfter(fhFinReserva)) {
                retorno=i.getDescripcion();
                ocupado=true;
                break;
            }
        }}
        if(numReservasActivas>0){
            for (Reserva i: this.getReservasActivas()){
                //A i_finReserva se le suma 1 para prever el periodo de 24h en el que el vehículo será EnLimpieza
                LocalDateTime i_inicioReserva = LocalDateTime.parse(String.format("%08d%04d",  i.getFechaRecoger(), i.getHoraRecoger()), formatter);
                LocalDateTime i_finReserva = (LocalDateTime.parse(String.format("%08d%04d",  i.getFechaEntregar(), i.getHoraEntregar()), formatter)).plusDays(1);
                if (!i_finReserva.isBefore(fhInicioReserva) && !i_inicioReserva.isAfter(fhFinReserva)) {
                        retorno="EnReserva";
                        ocupado=true;
                        break;
                }
            }
        }
        if (ocupado==false){retorno="Disponible";}
        return retorno;

        
    }catch(DateTimeParseException e){
        System.out.println("\n>Se presentó un error al verificar la disponibilidad");
        return retorno;
    }}
    
    public void obtenerLog(){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("./historiales/"+placa+".txt"));
                System.out.println("\n>Se encontró el vehículopondientes");
                writer.write("Placa: " + this.getPlaca());
                writer.newLine();
                writer.write("Marca: " + this.getMarca());
                writer.newLine();
                writer.write("Modelo: " + this.getModelo());
                writer.newLine();
                writer.write("Color: " + this.getColor());
                writer.newLine();
                writer.write("Tipo de Transmisión: " + this.getTipoTransmicion());
                writer.newLine();
                writer.write("Ubicación GPS: " + this.getUbicacionGPS());
                writer.newLine();
                writer.write("Estado: " + this.getEstado());
                writer.newLine();
                writer.write("Averiado: " + this.getAveriado());
                writer.newLine();
                writer.write("Sede: " + this.getSede().getNombre());
                writer.newLine();
                writer.write("Categoría: " + this.getCategoria().getnombreCategoria());
                writer.newLine();
                List<Evento> historialEvento = this.getHistorialEventos();
                if (!historialEvento.isEmpty()) {
                    writer.write("Historial de Eventos:");
                    writer.newLine();
                    for (Evento evento : historialEvento) {
                        writer.write("-> Fecha del inicio del evento: " + evento.getFechaInicio()+" .Fecha del fin del evento: " + evento.getFechaFin()+". Descripción: " + evento.getDescripcion()); 
                        writer.newLine();
                        }
                } else {
                    writer.write("Este vehículo no cuenta con historial de Eventos:");
                    writer.newLine();
                }
                writer.newLine();

                List<alquiler> historialAlquiler = this.getHistorialAlquileres();
                if (!historialAlquiler.isEmpty()) {
                    writer.write("Historial de Alquileres:");
                    writer.newLine();
                    for (alquiler alquiler : historialAlquiler) {
                        writer.write("->IDAlquiler: "+alquiler.getID()+". Pago Final: COP" + alquiler.getPagoFinal()+". Fecha inicio: "+alquiler.getReserva().getFechaRecoger()+". Fecha final: "+alquiler.getReserva().getFechaEntregar());
                        writer.newLine();
                        writer.write("      Sede de recogida: "+alquiler.getReserva().getSedeRecoger().getNombre()+". Sede de devolución: "+alquiler.getReserva().getSedeEntregar().getNombre());
                        writer.newLine();
                        writer.write("      Cliente: "+alquiler.getReserva().getCliente().getNombre()+". Cédula: "+alquiler.getReserva().getCliente().getNumeroCedula());
                        writer.newLine();
                        writer.write("  ->Conductores:");
                        writer.newLine();
                        for (Conductor conductor : alquiler.getConductores()) {
                            writer.write("      Nombre: " + conductor.getNombre()+". Número de cédula: "+ conductor.getCedula());
                            writer.newLine();
                        }
                        writer.write("  ->Pagos Excedentes:");
                        writer.newLine();
                        for (PagoExcedente pagoExcedente : alquiler.getPagosExcedentes()) {
                            writer.write("      Detalle del pago excedente: "+pagoExcedente.getMotivoPago()+". Valor pagado: "+ pagoExcedente.getValorPago());
                            writer.newLine();
                        }
                        writer.newLine();
                        }
                } else {
                    writer.write("Este vehículo no cuenta con historial de Alquileres:");
                    writer.newLine();
                }
                List<Reserva> reservasActivas = this.getReservasActivas();
                if (!reservasActivas.isEmpty()) {
                    writer.write("Reservas Activas:");
                    writer.newLine();
                    for (Reserva reserva : reservasActivas) {
                        writer.write("->IDreserva: "+reserva.getID()+". Pago 30%: COP" + reserva.getPagoReserva()+". Fecha inicio: "+reserva.getFechaRecoger()+". Fecha final: "+reserva.getFechaEntregar());
                        writer.newLine();
                        writer.write("      Sede de recogida: "+reserva.getSedeRecoger().getNombre()+". Sede de devolución: "+reserva.getSedeEntregar().getNombre());
                        writer.newLine();
                        writer.write("      Cliente: "+reserva.getCliente().getNombre()+". Cédula: "+reserva.getCliente().getNumeroCedula());
                        writer.newLine();
                    } 
                } else {
                    writer.write("Este vehículo no cuenta con Reservas Activas:");
                    writer.newLine();
                } 
                writer.close(); // Cerrar el BufferedWriter después de terminar de escribir.
             } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        }}

    public void resumenStatus(){
        String ubicacion="";
        int fechaActual= Integer.parseInt(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")));
        LocalTime hora = LocalTime.now();
        int horaActual = hora.getHour() * 100 + hora.getMinute();
        String estado= actualizarEstado(fechaActual,horaActual ,fechaActual,horaActual);
        if (estado.equals("Disponible")||estado.equals("EnLimpieza")||estado.equals("EnMantenimiento")){
            ubicacion= this.getSede().getNombre();
        }
        System.out.println("Estado: "+estado+". Ubicación: "+ubicacion);
    }
}
