package alquiler;
import java.util.ArrayList;
import java.util.List;
import inventario.Categoria;
import inventario.Vehiculo;
import usuario.Cliente;
import inventario.Sede;

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


    // Constructor
    public Reserva(int fechaRecoger, int fechaEntregar, int horaRecoger, int horaEntregar, boolean reservaEnSede, Sede sedeRecoger, Sede sedeEntregar, Cliente cliente) {
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
        int dias= fecha2-fecha1;
        int horas=hora2-hora1;
        if(horas<0){
            dias=dias+1;
        }
        return dias;
    
    }

    public int estimarPagoAlquiler(int fecha1, int hora1, int fecha2, int hora2) {
        // Lógica para estimar el pago de alquiler
        Categoria categoria=this.getCategoria();
        int tarifa=categoria.gettarifaDiaria();
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
    

