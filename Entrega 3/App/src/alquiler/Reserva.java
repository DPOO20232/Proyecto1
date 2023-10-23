package alquiler;
import java.util.ArrayList;
import java.util.List;

import Controller.MenuAlquiler;
import inventario.Categoria;
import inventario.Inventario;
import inventario.Vehiculo;
import usuario.Cliente;
import inventario.Sede;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;


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
    private double pagoReserva;
    private static int lastId;
    private static List<Reserva> listaReservas;

    // Constructor
    public Reserva(int fechaRecoger, int fechaEntregar, int horaRecoger, int horaEntregar, boolean reservaEnSede, Sede sedeRecoger, Sede sedeEntregar,Categoria categoria, Cliente cliente) {
        this.setID();
        this.fechaRecoger = fechaRecoger;
        this.fechaEntregar = fechaEntregar;
        this.horaRecoger = horaRecoger;
        this.horaEntregar = horaEntregar;
        this.reservaEnSede = reservaEnSede;
        this.sedeRecoger = sedeRecoger;
        this.sedeEntregar = sedeEntregar;
        this.categoria=categoria;
        this.cliente = cliente;
    }
    public Reserva(int idReserva,int fechaRecoger, int fechaEntregar, int horaRecoger, int horaEntregar, boolean reservaEnSede, Sede sedeRecoger, Sede sedeEntregar,Categoria categoria, Cliente cliente) {
        this.idReserva=idReserva;
        if (idReserva>lastId){lastId=idReserva;}
        this.fechaRecoger = fechaRecoger;
        this.fechaEntregar = fechaEntregar;
        this.horaRecoger = horaRecoger;
        this.horaEntregar = horaEntregar;
        this.reservaEnSede = reservaEnSede;
        this.sedeRecoger = sedeRecoger;
        this.sedeEntregar = sedeEntregar;
        this.categoria=categoria;
        this.cliente = cliente;
    }
    // Métodos getter
    public int getID() {return this.idReserva;}
    public int getFechaRecoger() {return this.fechaRecoger;}
    public int getFechaEntregar() {return this.fechaEntregar;}
    public int getHoraRecoger() {return this.horaRecoger;}
    public int getHoraEntregar() {return this.horaEntregar;}
    public boolean getReservaEnSede() {return this.reservaEnSede;}
    public Sede getSedeRecoger() {return this.sedeRecoger;}
    public Sede getSedeEntregar() {return this.sedeEntregar;}
    public Categoria getCategoria() {return this.categoria;}
    public Vehiculo getVehiculoAsignado() {return this.vehiculoAsignado;}
    public Cliente getCliente(){return this.cliente;}
    public double getPagoReserva() {return this.pagoReserva;}
    public static List<Reserva> getListaReservas(){return listaReservas;}
    // Métodos setter
    public void setID() {this.idReserva=lastId+=1;lastId=this.getID();}
    public void setFechaRecoger(int fecha) {this.fechaRecoger = fecha;}
    public void setFechaEntregar(int fecha) {this.fechaEntregar = fecha;}
    public void setHoraRecoger(int hora) {this.horaRecoger = hora;}
    public void setHoraEntregar(int hora) {this.horaEntregar = hora;}
    public void setReservaEnSede(boolean reservaCliente) {this.reservaEnSede = reservaCliente;}
    public void setSedeRecoger(Sede sede) {this.sedeRecoger = sede;}
    public void setSedeEntregar(Sede sede) {this.sedeEntregar = sede;}
    public void setCategoria(Categoria categoria) {this.categoria = categoria;}
    public void setVehiculoAsignado(Vehiculo vehiculo){this.vehiculoAsignado=vehiculo;}
    public void setVehiculoAsignado() {
        Vehiculo vehiculoAsignado=null;
        boolean encontreUpgrade=false;
        boolean esUpgrade=true;
        int categoria=this.getCategoria().getID();
        int categoriaPadre=this.getCategoria().getId_Padre();
        for(Vehiculo i: Inventario.getListaVehiculos()){
            if (i.getAveriado()==false){
            if(i.getCategoria().getID()==categoria && i.getSede().getID()==this.getSedeRecoger().getID()){
                if(i.actualizarEstado(this.fechaRecoger,this.horaRecoger ,this.fechaEntregar ,this.horaEntregar ).equals("Disponible")){
                    vehiculoAsignado=i;
                    esUpgrade=false;
                    break;}}
                else if(encontreUpgrade==false && i.getCategoria().getID()==categoriaPadre && i.getSede().getID()==this.getSedeRecoger().getID()){
                    if(i.actualizarEstado(this.fechaRecoger,this.horaRecoger ,this.fechaEntregar ,this.horaEntregar).equals("Disponible")){
                    vehiculoAsignado=i;
                    encontreUpgrade=true;
                }}}
            }
        if ((vehiculoAsignado!=null)&&(esUpgrade==false)){
            vehiculoAsignado.addReservaActiva(this);
            this.setVehiculoAsignado(vehiculoAsignado);}
        else if((vehiculoAsignado!=null)&&(esUpgrade==true)){
            System.out.println("\n\t>Accederás a un Upgrade de vehiculo sin costo adicional!\n");
            vehiculoAsignado.addReservaActiva(this);
            this.setVehiculoAsignado(vehiculoAsignado);
            }
        else{System.out.println("\n\t>No se encontraron vehículos disponibles para la categoría dada en el rango de fechas dado.");}
    }
    public void setPagoReserva(double pago) {
        pagoReserva = pago;
    }
    public void setPagoReserva(int fecha1, int hora1, int fecha2, int hora2) {
        Categoria categoria=this.getCategoria();
        int tarifa=categoria.getTarifaDiaria();
        int dias=this.calcularDuracionRenta(fecha1,hora1,fecha2,hora2);
        double precio_inicial= dias*tarifa*0.3;
        double pctg_temporada=1;
        int mmdd1=fecha1%10000;
        int mmdd2=fecha2%10000;
        boolean esTempAlta=Inventario.esTemporadaAlta(mmdd1,mmdd2);
        boolean esTempBaja=Inventario.esTemporadaBaja(mmdd1,mmdd2);
        if(esTempAlta){pctg_temporada=categoria.getPctg_temporadaAlta();}
        else if(esTempBaja){pctg_temporada=categoria.getPctg_temporadaBaja();}
        double precio_final=precio_inicial*pctg_temporada;
        this.pagoReserva= precio_final;
    }

    public static void eliminarReserva(Cliente cliente){
        Reserva reservaElejida=MenuAlquiler.encontrarReserva(cliente);
        if(reservaElejida!=null){
        int id= reservaElejida.getID();
        getListaReservas().remove(reservaElejida);
        Vehiculo vehiculoReservaElejida= reservaElejida.getVehiculoAsignado();
        vehiculoReservaElejida.eliminarReservaActiva(reservaElejida.getID());
        System.out.println("\n> La reserva con IDreserva "+Integer.toString(id)+" ha sido cancelada, pronto se trasferirá de vuelta el pago del 30% (COP "+Double.toString(reservaElejida.getPagoReserva())+").");
    }}

    public static void addReserva(Reserva reserva){
        if (listaReservas==null){
        listaReservas= new ArrayList<Reserva>();
        }
        listaReservas.add(reserva);
    }
    public static Reserva assignReserva(int idReserva){
        Reserva retorno = null;
        for(Reserva i: Reserva.getListaReservas()){
            if(i.getID()==idReserva){
            retorno= i;
            break;
            }}
        return retorno;
    }       

        public static boolean horaValida(int hora) {
        int horas = hora / 100; 
        int minutos = hora % 100;
        if (horas >= 0 && horas <= 23 && minutos >= 0 && minutos <= 59) {
            return true;
        } else {
            return false;
        }
    }
    public static boolean fechaValidaReserva(int fecha,int hora) {
        Calendar fechaActual = Calendar.getInstance();
        int diaactual = fechaActual.get(Calendar.DAY_OF_MONTH);
        int mesactual = fechaActual.get(Calendar.MONTH) + 1;
        int anhoactual = fechaActual.get(Calendar.YEAR);
        int houractual = fechaActual.get(Calendar.HOUR_OF_DAY);
        int minutoactual =fechaActual.get(Calendar.MINUTE);
        int dia = fecha % 100;
        int mes = (fecha % 10000) / 100;
        int anio = fecha / 10000;
        boolean retorno=false;
        if (dia < 32 && mes < 13) {
            if ((anio == anhoactual+1)) {
                retorno= true;
            }
            else if ((mes > mesactual)) {
                retorno= true;
            }
            else if ((dia > diaactual)) {
                retorno= true;
            }
            else if(hora>(houractual*100+minutoactual)){
                retorno=true;
            }
        } 
        return retorno;
    }
    public static boolean fechaValidaDevolucion(int recoger, int devolucion, int hrecoger, int hdevolver) {
        int diae = recoger % 100;
        int mese = (recoger % 10000) / 100;
        int anioe = recoger / 10000;

        int diad = devolucion % 100;
        int mesd = (devolucion % 10000) / 100;
        int aniod = devolucion / 10000;
        boolean retorno=false;
        if (diad < 32 && mesd < 13) {
            if ((aniod == anioe+1)) {
                retorno= true;
            }
            else if ((mesd > mese)) {
                retorno= true;
            }
            else if ((diad > diae)) {
                retorno= true;
            }
            else if((hdevolver> hrecoger)) {
                retorno= true;
            } 
        }
        return retorno;
    }
    private int calcularDuracionRenta(int fecha1, int hora1, int fecha2, int hora2) {
        // Lógica para calcular la duración de la renta
        String fechaStr1=Integer.toString(fecha1);
        String fechaStr2=Integer.toString(fecha2);
        String fechaString1="";
        if (fechaStr1.length() == 8) {
            // Extrae el día, mes y año de la cadena
            String dia = fechaStr1.substring(6 );
            String mes = fechaStr1.substring(4, 6);
            String año = fechaStr1.substring(0,4);
            // Formatea la fecha en el formato deseado (DD/MM/YYYY)
            StringBuilder fechaFormateada1 = new StringBuilder();
            fechaFormateada1.append(dia).append("/").append(mes).append("/").append(año);
            fechaString1=fechaFormateada1.toString();
        }
        else{
            System.out.println("Ingrese una fecha válida");
        }
        
        String fechaString2="";
        if (fechaStr2.length() == 8) {
            // Extrae el día, mes y año de la cadena
            String dia2 = fechaStr2.substring(6);
            String mes2 = fechaStr2.substring(4, 6);
            String año2 = fechaStr2.substring(0,4);

            // Formatea la fecha en el formato deseado (DD/MM/YYYY)
            StringBuilder fechaFormateada2 = new StringBuilder();
            fechaFormateada2.append(dia2).append("/").append(mes2).append("/").append(año2);
            fechaString2=fechaFormateada2.toString();
        }
        else{
            System.out.println("Ingrese una fecha válida");
        }
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fechaT1 = LocalDate.parse(fechaString1, formato);
        LocalDate fechaT2 = LocalDate.parse(fechaString2, formato);
        long duracionEnDias = fechaT1.until(fechaT2, ChronoUnit.DAYS);
        int valorInt=(int) duracionEnDias;
        if(hora2>hora1){
            valorInt+=1;
        }
        return valorInt;
    }
}
    

