package modelo;
import java.util.ArrayList;
import java.util.List;
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
        /**
         * Crea una instancia de Vehículo con la información proporcionada.
         *
         * @param placa La placa del vehículo.
         * @param marca La marca del vehículo.
         * @param modelo El modelo del vehículo.
         * @param color El color del vehículo.
         * @param tipoTransmision El tipo de transmisión del vehículo.
         * @param ubicacionGPS La ubicación GPS del vehículo (puede ser "No disponible").
         * @param estado El estado del vehículo.
         * @param averiado Indica si el vehículo está averiado (verdadero o falso).
         * @param categoria La categoría a la que pertenece el vehículo.
         * @param sede La sede a la que está asignado el vehículo.
         */
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
    public String getPlaca(){
        /**
         * Obtiene la placa del vehículo.
         *
         * @return La placa del vehículo.
         */
        return this.placa;}
    public String getMarca(){
        /**
         * Obtiene la marca del vehículo.
         *
         * @return La marca del vehículo.
         */
        return this.marca;}
    public String getModelo(){
        /**
         * Obtiene el modelo del vehículo.
         *
         * @return El modelo del vehículo.
         */
        return this.modelo;}
    public String getColor(){
        /**
         * Obtiene el color del vehículo.
         *
         * @return El color del vehículo.
         */
        return this.color;}
    public String getTipoTransmicion(){
        /**
         * Obtiene el tipo de transmisión del vehículo.
         *
         * @return El tipo de transmisión del vehículo.
         */
        return this.tipoTransmision;}
    public String getUbicacionGPS(){
        /**
         * Obtiene la ubicación GPS del vehículo.
         *
         * @return La ubicación GPS del vehículo.
         */
        return this.ubicacionGPS;}
    public String getEstado(){
        /**
         * Obtiene la ubicación GPS del vehículo.
         *
         * @return La ubicación GPS del vehículo.
         */
        return this.estado;}
    public boolean getAveriado(){
        /**
         * Indica si el vehículo está averiado.
         *
         * @return Verdadero si el vehículo está averiado, falso en caso contrario.
         */
        return this.averiado;}
    public Sede getSede(){
        /**
         * Obtiene la sede a la que está asignado el vehículo.
         *
         * @return La sede a la que está asignado el vehículo.
         */
        return this.sede;}
    public Categoria getCategoria(){
        /**
         * Obtiene la sede a la que está asignado el vehículo.
         *
         * @return La sede a la que está asignado el vehículo.
         */
        return this.categoria;}
    public List<Evento> getHistorialEventos(){
        /**
         * Obtiene el historial de eventos del vehículo.
         *
         * @return Una lista de eventos relacionados con el vehículo.
         */
        return this.historialEvento;}
    public Evento getUltimoEvento(){
        /**
         * Obtiene el último evento registrado en el historial del vehículo.
         *
         * @return El último evento registrado o null si no hay eventos en el historial.
         */
        return this.historialEvento.get(historialEvento.size()-1);}
    public List<alquiler> getHistorialAlquileres(){
        /**
         * Obtiene el historial de alquileres del vehículo.
         *
         * @return Una lista de alquileres relacionados con el vehículo.
         */
        return this.historialAlquiler;}
    public List<Reserva> getReservasActivas(){
        /**
         * Obtiene la lista de reservas activas del vehículo.
         *
         * @return Una lista de reservas activas relacionadas con el vehículo.
         */
        return this.reservasActivas;}

    public void setPlaca(String placa){
        /**
         * Establece la placa del vehículo.
         *
         * @param placa La nueva placa del vehículo.
         */
        this.placa=placa;}
    public void setColor(String color){
        /**
         * Establece la placa del vehículo.
         *
         * @param placa La nueva placa del vehículo.
         */
        this.color=color;}
    public void setEstado(String estado){
        /**
         * Establece el estado del vehículo.
         *
         * @param estado El nuevo estado del vehículo.
         */
        this.estado=estado;}
    public void setAveriado(boolean averiado){
        /**
         * Establece si el vehículo está averiado o no.
         *
         * @param averiado Verdadero si el vehículo está averiado, falso si no lo está.
         */
        this.averiado=averiado;}
    public boolean setTrasladoASede(Sede nuevaSede){
        /**
         * Realiza un traslado del vehículo a una nueva sede y registra un evento asociado.
         *
         * @param nuevaSede La nueva sede a la que se trasladará el vehículo.
        * @return true si se pudo completar el traslado, false si no se pudo.
         */
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
        return true;

        }
        else{
        return false;
        }
    }
    public void addEvento(Evento evento){
        /**
         * Agrega un evento al historial de eventos del vehículo y al inventario de eventos.
         *
         * @param evento El evento a agregar.
         */
        this.historialEvento.add(evento);
        Inventario.getListaEventos().add(evento);
        }
    public void addReservaActiva(Reserva reserva){
        /**
         * Agrega una reserva activa al vehículo.
         *
         * @param reserva La reserva activa a agregar.
         */
        boolean yaIngresado=false;
        for (Reserva i:this.reservasActivas){
            if (i.getID()==reserva.getID()){
                yaIngresado=true;
            }
        }
        if (!yaIngresado){
        this.reservasActivas.add(reserva);}
    }
    public void addAlquiler(alquiler alquiler){
        /**
         * Agrega una reserva activa al vehículo.
         *
         * @param reserva La reserva activa a agregar.
         */
        this.historialAlquiler.add(alquiler);}
    public void eliminarReservaActiva(int idReserva){
        /**
         * Agrega una reserva activa al vehículo.
         *
         * @param reserva La reserva activa a agregar.
         */
        List<Reserva> reservas_activas= this.getReservasActivas();
        for (int i = 0; i < reservas_activas.size(); i++){
            Reserva i_reserva= reservas_activas.get(i);
            if (i_reserva.getID()==(idReserva)){
                reservas_activas.remove(i);
                break;
            }
        }}
    public String actualizarEstado(int fecha1, int hora1, int fecha2, int hora2){
        /**
         * Actualiza el estado del vehículo según el intervalo de tiempo especificado.
         *
         * @param fecha1 La fecha de inicio en formato "yyyyMMdd".
         * @param hora1 La hora de inicio en formato "HHmm".
         * @param fecha2 La fecha de finalización en formato "yyyyMMdd".
         * @param hora2 La hora de finalización en formato "HHmm".
         * @return El estado actual del vehículo ("NoDisponible", "EnReserva", "EnLimpieza" u otro).
         *
         * @throws DateTimeParseException Si se produce un error al analizar las fechas y horas en el formato especificado.
         */
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
        /**
         * Genera un archivo de registro (log) que contiene información detallada sobre el vehículo.
         * El archivo de registro se guarda en la carpeta "historiales" con el nombre de archivo igual a la placa del vehículo.
         *
         * @throws IOException Si ocurre un error al escribir en el archivo de registro.
         */
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("./historiales/"+placa+".txt"));
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
                    writer.write("Este vehículo no cuenta con historial de Eventos.");
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
                    writer.write("Este vehículo no cuenta con historial de Alquileres.");
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
                    writer.write("Este vehículo no cuenta con Reservas Activas.");
                    writer.newLine();
                } 
                writer.close(); // Cerrar el BufferedWriter después de terminar de escribir.
             } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        }}

    public String resumenStatus(){
        /**
         * Muestra un resumen del estado actual del vehículo, incluyendo su ubicación y disponibilidad.
         * La información se muestra en la consola.
         *
         * @throws DateTimeParseException Si ocurre un error al parsear fechas o horas.
         */
        String ubicacion="";
        String infoCliente="";
        String disponibilidad= "";
        String retorno="";
        int fechaActual= Integer.parseInt(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")));
        LocalTime hora = LocalTime.now();
        int horaActual = hora.getHour() * 100 + hora.getMinute();
        String estado= actualizarEstado(fechaActual,horaActual ,fechaActual,horaActual);
        if (estado.equals("Disponible")){
            ubicacion= this.getSede().getNombre();
            disponibilidad=LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
            
            retorno=("Estado: "+estado+". Ubicación: "+ubicacion+". Disponible a partir de: "+disponibilidad+".");

        }
        else if (estado.equals("EnLimpieza")||estado.equals("EnMantenimiento")){
            ubicacion= this.getSede().getNombre();
            for(Evento i: this.getHistorialEventos()){
                if (i.getFechaFin()>=fechaActual&&i.getFechaInicio()<=fechaActual){
                    disponibilidad = String.valueOf(i.getFechaFin() / 10000) + "/" + String.valueOf((i.getFechaFin() / 100) % 100) + "/" + String.valueOf(i.getFechaFin() % 100);
                    break;
                }            
            }
            retorno=("Estado: "+estado+". Ubicación: "+ubicacion+". Disponible a partir de: "+disponibilidad+".");
        }
        else if(estado.equals("EnReserva")) {
            ubicacion=this.getUbicacionGPS();
            for (Reserva i: this.getReservasActivas()){
                if (i.getFechaEntregar()>=fechaActual&&i.getFechaRecoger()<=fechaActual){
                    disponibilidad = String.valueOf(i.getFechaRecoger() / 10000) + "/" + String.valueOf((i.getFechaRecoger() / 100) % 100) + "/" + String.valueOf(i.getFechaRecoger() % 100);
                    infoCliente="Cliente en posesión del vehículo: "+i.getCliente().getNombre()+". Cédula: "+Integer.toString(i.getCliente().getNumeroCedula())+".";
                    break;
            }
        }
            retorno=("Estado: "+"EnAlquiler"+". Ubicación: "+ubicacion+". Disponible a partir de: "+disponibilidad+".\n"+infoCliente);
    }
    return retorno;
    }
}
