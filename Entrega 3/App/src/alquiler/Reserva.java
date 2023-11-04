package alquiler;
import java.util.ArrayList;
import java.util.List;

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
        /**
         * Crea una nueva reserva de vehículo con la información especificada.
         *
         * @param fechaRecoger La fecha en la que se recogerá el vehículo (en formato numérico, por ejemplo, YYYYMMDD).
         * @param fechaEntregar La fecha en la que se entregará el vehículo (en formato numérico, por ejemplo, YYYYMMDD).
         * @param horaRecoger La hora en la que se recogerá el vehículo (en formato 24 horas, por ejemplo, 1500 para las 3:00 PM).
         * @param horaEntregar La hora en la que se entregará el vehículo (en formato 24 horas, por ejemplo, 1500 para las 3:00 PM).
         * @param reservaEnSede Indica si la reserva se realiza en una sede (true) o no (false).
         * @param sedeRecoger La sede donde se recogerá el vehículo.
         * @param sedeEntregar La sede donde se entregará el vehículo.
         * @param categoria La categoría del vehículo que se va a reservar.
         * @param cliente El cliente que realiza la reserva.
         */
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
        /**
         * Crea una nueva reserva de vehículo con la información especificada, incluyendo un identificador de reserva personalizado.
         *
         * @param idReserva El identificador único de la reserva.
         * @param fechaRecoger La fecha en la que se recogerá el vehículo (en formato numérico, por ejemplo, YYYYMMDD).
         * @param fechaEntregar La fecha en la que se entregará el vehículo (en formato numérico, por ejemplo, YYYYMMDD).
         * @param horaRecoger La hora en la que se recogerá el vehículo (en formato 24 horas, por ejemplo, 1500 para las 3:00 PM).
         * @param horaEntregar La hora en la que se entregará el vehículo (en formato 24 horas, por ejemplo, 1500 para las 3:00 PM).
         * @param reservaEnSede Indica si la reserva se realiza en una sede (true) o no (false).
         * @param sedeRecoger La sede donde se recogerá el vehículo.
         * @param sedeEntregar La sede donde se entregará el vehículo.
         * @param categoria La categoría del vehículo que se va a reservar.
         * @param cliente El cliente que realiza la reserva.
         */
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
    public int getID() {
        /**
         * Obtiene el identificador único de la reserva.
         *
         * @return El identificador único de la reserva.
         */
        return this.idReserva;}
    public int getFechaRecoger() {
        /**
         * Obtiene la fecha en la que se recogerá el vehículo (en formato numérico, por ejemplo, YYYYMMDD).
         *
         * @return La fecha de recogida del vehículo.
         */
        return this.fechaRecoger;}
    public int getFechaEntregar() {
        /**
         * Obtiene la fecha en la que se entregará el vehículo (en formato numérico, por ejemplo, YYYYMMDD).
         *
         * @return La fecha de entrega del vehículo.
         */
        return this.fechaEntregar;}
    public int getHoraRecoger() {
        /**
         * Obtiene la hora en la que se recogerá el vehículo (en formato 24 horas, por ejemplo, 1500 para las 3:00 PM).
         *
         * @return La hora de recogida del vehículo.
         */
        return this.horaRecoger;}
    public int getHoraEntregar() {
        /**
         * Obtiene la hora en la que se entregará el vehículo (en formato 24 horas, por ejemplo, 1500 para las 3:00 PM).
         *
         * @return La hora de entrega del vehículo.
         */
        return this.horaEntregar;}
    public boolean getReservaEnSede() { 
        /**
         * Indica si la reserva se realiza en una sede (true) o no (false).
         *
         * @return true si la reserva se realiza en una sede, false en caso contrario.
         */
        return this.reservaEnSede;}
    public Sede getSedeRecoger() {
        /**
         * Obtiene la sede donde se recogerá el vehículo.
         *
         * @return La sede de recogida del vehículo.
         */
        return this.sedeRecoger;}
    public Sede getSedeEntregar() {
        /**
         * Obtiene la sede donde se entregará el vehículo.
         *
         * @return La sede de entrega del vehículo.
         */
        return this.sedeEntregar;}
    public Categoria getCategoria() {
        /**
         * Obtiene la categoría del vehículo reservado.
         *
         * @return La categoría del vehículo reservado.
         */
        return this.categoria;} 
    public Vehiculo getVehiculoAsignado() {
        /**
         * Obtiene el vehículo asignado a la reserva.
         *
         * @return El vehículo asignado a la reserva, o null si no hay vehículo asignado.
         */
        return this.vehiculoAsignado;}
    public Cliente getCliente(){
        /**
         * Obtiene el cliente que realizó la reserva.
         *
         * @return El cliente que realizó la reserva.
         */
        return this.cliente;}
    public double getPagoReserva() {
        /**
         * Obtiene el monto del pago de la reserva.
         *
         * @return El monto del pago de la reserva.
         */
        return this.pagoReserva;}
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
<<<<<<< HEAD
    private static Reserva encontrarReserva(Cliente cliente){
        /**
         * Encuentra y devuelve una reserva activa de un cliente después de mostrar las reservas activas.
         *
         * @param cliente: El cliente para el que se desea encontrar una reserva activa.
         * @return Reserva: La reserva activa encontrada o null si no se encontró una reserva válida.
         */
        Reserva retorno=null;
        List<Integer> idsReservas= desplegarReservasActivas(cliente);
        if (idsReservas.size()>=1){
        boolean continuar=true;
        while (continuar){
        int intReservaElejida= Integer.parseInt(input("Porfavor ingrese el idReserva de la reserva que desea modificar/cancelar"));
        if (idsReservas.contains(intReservaElejida)){
            retorno=Reserva.assignReserva(intReservaElejida);
            System.out.println("\n> Reserva encontrada.\n");
            continuar=false;

        }
        else{
            System.out.println("\n> El idReserva ingresado no es válido, desea intentarlo de nuevo?\n");
            System.out.println("1.Sí");
            System.out.println("2.No(ó cualquier otro número)");
            int opcion= Integer.parseInt(input("Porfavor elija una opción"));
            if(opcion==1){
                idsReservas=desplegarReservasActivas(cliente);
            }
            else{continuar=false;}
        }}}
        return retorno;    
    }
    private static List<Integer> desplegarReservasActivas(Cliente cliente){
        /**
         * Muestra las reservas activas de un cliente y devuelve una lista de los ID de las reservas activas.
         *
         * @param cliente: El cliente para el que se desean mostrar las reservas activas.
         * @return List<Integer>: Una lista de los ID de las reservas activas del cliente.
         */
        List<Reserva> reservas= getListaReservas();
        List<Integer> idsReservas= new ArrayList<Integer>();
        boolean inicio=false;
        if (reservas.size()>0){
        for (int i = 0; i < reservas.size(); i++) {
            if (reservas.get(i).getCliente().equals(cliente)){
            if (inicio==false){
                System.out.println("\nTiene las siguientes Reservas activas:");
                inicio=true;
            }
            Reserva i_reserva=reservas.get(i);
            idsReservas.add(i_reserva.getID());
            System.out.println("IDreserva: " + Integer.toString(i_reserva.getID()));
            int f1= i_reserva.getFechaRecoger();
            int f2=i_reserva.getFechaEntregar();
            String categoria=i_reserva.getCategoria().getnombreCategoria();
            Sede sedeRecoger= i_reserva.getSedeRecoger();
            Sede sedeDevolver=i_reserva.getSedeEntregar();
            String fecha1 = String.format("%02d/%02d/%04d",f1 % 100,(f1/ 100) % 100, f1/ 10000);
            String fecha2 = String.format("%02d/%02d/%04d",f2 % 100,(f2/ 100) % 100, f2/ 10000);
            System.out.println("    Tipo de vehículo reservado: "+ categoria+ ". Fecha Inicio: " + fecha1+"-> Fecha Final: "+ fecha2);
            System.out.println("    Sede de recogida del vehículo: "+ sedeRecoger.getNombre()+" ("+sedeRecoger.getUbicacion()+")"+ ". -> Sede de devolución del vehículon: "+ sedeDevolver.getNombre()+" ("+sedeDevolver.getUbicacion()+")");

            System.out.println("------------------------------------------------------------------------------------------------------------------------");
            }}}
            if (inicio==false){ System.out.println("\n>No tienes reservas activas. ");}
            return idsReservas;
    }
=======
>>>>>>> main

    public static boolean horaValida(int hora) {
        /**
         * Verifica si una hora en formato "HHMM" es válida.
         *
         * @param hora: La hora en formato "HHMM".
         * @return boolean: Devuelve true si la hora es válida, de lo contrario, devuelve false.
         */
        int horas = hora / 100; 
        int minutos = hora % 100;
        if (horas >= 0 && horas <= 23 && minutos >= 0 && minutos <= 59) {
            return true;
        } else {
            return false;
        }
    }
<<<<<<< HEAD
    private static boolean fechaValidaReserva(int fecha,int hora) {
        /**
         * Verifica si la fecha y hora de reserva son válidas en relación a la fecha y hora actual.
         *
         * @param fecha: La fecha de reserva en formato "AAAAMMDD".
         * @param hora: La hora de reserva en formato "HHMM".
         * @return boolean: Devuelve true si la fecha y hora de reserva son válidas, de lo contrario, devuelve false.
         */
=======
    public static boolean fechaValidaReserva(int fecha,int hora) {
>>>>>>> main
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
<<<<<<< HEAD
    private static boolean fechaValidaDevolucion(int recoger, int devolucion, int hrecoger, int hdevolver) {
        /**
         * Verifica si la fecha y hora de devolución son válidas en relación a la fecha y hora de recogida.
         *
         * @param recoger: La fecha de recogida en formato "AAAAMMDD".
         * @param devolucion: La fecha de devolución en formato "AAAAMMDD".
         * @param hrecoger: La hora de recogida en formato "HHMM".
         * @param hdevolver: La hora de devolución en formato "HHMM".
         * @return boolean: Devuelve true si la fecha y hora de devolución son válidas, de lo contrario, devuelve false.
         */
=======
    public static boolean fechaValidaDevolucion(int recoger, int devolucion, int hrecoger, int hdevolver) {
>>>>>>> main
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
        /**
         * Calcula la duración de una renta en días, considerando las fechas y horas de inicio y fin.
         *
         * @param fecha1: La fecha de inicio de la renta en formato "YYYYMMDD".
         * @param hora1: La hora de inicio de la renta en formato "HHMM".
         * @param fecha2: La fecha de fin de la renta en formato "YYYYMMDD".
         * @param hora2: La hora de fin de la renta en formato "HHMM".
         * @return int: La duración de la renta en días.
         * @throws DateTimeParseException: Si las fechas no están en el formato correcto.
         */
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
    

