package alquiler;
import java.util.ArrayList;
import java.util.List;
import inventario.Categoria;
import inventario.Vehiculo;
import usuario.Cliente;
import inventario.Sede;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Reserva {
    private int idReserva;
    private int fechaRecoger;
    private int fechaEntregar;
    private int horaRecoger;
    private int horaEntregar;
    private boolean reservaEnSede;
    private Sede sedeRecoger;
    private Sede sedeEntregar;
    private Cliente cliente;
    private Categoria categoria;
    private Vehiculo vehiculoAsignado;
    private int pagoReserva;
    private static int idCounter=0;


    // Constructor
    public Reserva(int fechaRecoger, int fechaEntregar, int horaRecoger, int horaEntregar, boolean reservaEnSede, Sede sedeRecoger, Sede sedeEntregar,Categoria categoria, Cliente cliente) {
        idCounter+=1;
        this.idReserva=idCounter;
        this.fechaRecoger = fechaRecoger;
        this.fechaEntregar = fechaEntregar;
        this.horaRecoger = horaRecoger;
        this.horaEntregar = horaEntregar;
        this.reservaEnSede = reservaEnSede;
        this.sedeRecoger = sedeRecoger;
        this.sedeEntregar = sedeEntregar;
        this.cliente = cliente;
    }
    public Reserva(int idReserva,int fechaRecoger, int fechaEntregar, int horaRecoger, int horaEntregar, boolean reservaEnSede, Sede sedeRecoger, Sede sedeEntregar,Categoria categoria, Cliente cliente) {
        this.idReserva=idReserva;
        this.fechaRecoger = fechaRecoger;
        this.fechaEntregar = fechaEntregar;
        this.horaRecoger = horaRecoger;
        this.horaEntregar = horaEntregar;
        this.reservaEnSede = reservaEnSede;
        this.sedeRecoger = sedeRecoger;
        this.sedeEntregar = sedeEntregar;
        this.cliente = cliente;
    }


    // Métodos getter
    public int getID() {
        return this.idReserva;
    }

    public int getFechaRecoger() {
        return this.fechaRecoger;
    }

    public int getFechaEntregar() {
        return this.fechaEntregar;
    }

    public int getHoraRecoger() {
        return this.horaRecoger;
    }

    public int getHoraEntregar() {
        return this.horaEntregar;
    }

    public boolean getReservaEnSede() {
        return this.reservaEnSede;
    }

    public Sede getSedeRecoger() {
        return this.sedeRecoger;
    }

    public Sede getSedeEntregar() {
        return this.sedeEntregar;
    }

    public Categoria getCategoria() {
        return this.categoria;
    }

    public Vehiculo getVehiculoAsignado() {
        return this.vehiculoAsignado;
    }

    public int getPagoReserva() {
        return this.pagoReserva;
    }

    // Métodos setter
    public void setID(int id) {
        idReserva = id;
    }

    public void setFechaRecoger(int fecha) {
        fechaRecoger = fecha;
    }

    public void setFechaEntregar(int fecha) {
        fechaEntregar = fecha;
    }

    public void setHoraRecoger(int hora) {
        horaRecoger = hora;
    }

    public void setHoraEntregar(int hora) {
        horaEntregar = hora;
    }

    public void setReservaEnSede(boolean reservaCliente) {
        reservaEnSede = reservaCliente;
    }

    public void setSedeRecoger(Sede sede) {
        sedeRecoger = sede;
    }

    public void setSedeEntregar(Sede sede) {
        sedeEntregar = sede;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public void setVehiculoAsignado(Vehiculo vehiculo) {
        vehiculoAsignado = vehiculo;
    }

    public void setPagoReserva(int pago) {
        pagoReserva = pago;
    }

    public int calcularDuracionRenta(int fecha1, int hora1, int fecha2, int hora2) {
        // Lógica para calcular la duración de la renta
        String fechaStr1=Integer.toString(fecha1);
        String fechaStr2=Integer.toString(fecha2);
        String fechaString1="";
        if (fechaStr1.length() == 8) {
            // Extrae el día, mes y año de la cadena
            String dia = fechaStr1.substring(0, 2);
            String mes = fechaStr1.substring(2, 4);
            String año = fechaStr1.substring(4);

            // Formatea la fecha en el formato deseado (DD/MM/YYYY)
            StringBuilder fechaFormateada1 = new StringBuilder();
            fechaFormateada1.append(dia).append("/").append(mes).append("/").append(año);
            fechaString1=fechaFormateada1.toString();
           
        }
        else{
            String dia = fechaStr1.substring(0, 1);
            String mes = fechaStr1.substring(1, 3);
            String año = fechaStr1.substring(3);

            // Formatea la fecha en el formato deseado (DD/MM/YYYY)
            StringBuilder fechaFormateada1 = new StringBuilder();
            fechaFormateada1.append(dia).append("/").append(mes).append("/").append(año);
            fechaString1=fechaFormateada1.toString();
            fechaString1="0"+fechaString1;
        }
        String fechaString2="";
        if (fechaStr2.length() == 8) {
            // Extrae el día, mes y año de la cadena
            String dia2 = fechaStr2.substring(0, 2);
            String mes2 = fechaStr2.substring(2, 4);
            String año2 = fechaStr2.substring(4);

            // Formatea la fecha en el formato deseado (DD/MM/YYYY)
            StringBuilder fechaFormateada2 = new StringBuilder();
            fechaFormateada2.append(dia2).append("/").append(mes2).append("/").append(año2);
            fechaString2=fechaFormateada2.toString();
        }
        else{
                // Extrae el día, mes y año de la cadena
                String dia2 = fechaStr2.substring(0, 1);
                String mes2 = fechaStr2.substring(1, 3);
                String año2 = fechaStr2.substring(3);
    
                // Formatea la fecha en el formato deseado (DD/MM/YYYY)
                StringBuilder fechaFormateada2 = new StringBuilder();
                fechaFormateada2.append(dia2).append("/").append(mes2).append("/").append(año2);
                fechaString2=fechaFormateada2.toString();
                fechaString2="0"+fechaString2;
        }
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fechaT1 = LocalDate.parse(fechaString1, formato);
        LocalDate fechaT2 = LocalDate.parse(fechaString2, formato);
        long duracionEnDias = fechaT1.until(fechaT2).getDays();
        int valorInt=(int) duracionEnDias;
        return valorInt;
        
    }

    public int estimarPagoAlquiler(int fecha1, int hora1, int fecha2, int hora2) {
        // Lógica para estimar el pago de alquiler
        Categoria categoria=this.getCategoria();
        int tarifa=categoria.getTarifaDiaria();
        int dias=this.calcularDuracionRenta(fecha1,hora1,fecha2,hora2);
        int precio= dias*tarifa;
        return precio;
    }

    public String crearMensajeConfirmacionReserva() {
        // Lógica para crear un mensaje de confirmación de reserva
        String str="Su reserva fue realizada existosamente";
        return str;
    }
}
    

