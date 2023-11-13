package modelo;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class alquiler{
    private int idAlquiler;
    private double pagoFinal;
    private Reserva reserva;
    private ArrayList<Conductor> conductores;
    private boolean activo;
    private ArrayList<Seguro> seguros;
    private ArrayList<PagoExcedente> pagosExcedentes;
    private static ArrayList<alquiler> listaAlquileres;

    public alquiler(Reserva reserva){
        this.idAlquiler= reserva.getID();
        this.pagoFinal=0;
        this.reserva=reserva;
        this.conductores= new ArrayList<Conductor>();
        this.seguros= new ArrayList<Seguro>();
        this.pagosExcedentes= new ArrayList<PagoExcedente>();
    }
    public boolean getActivo(){
        /**
         * Obtiene el estado de activación del alquiler.
         *
         * @return boolean: true si el alquiler está activo, false si no lo está.
         */
        return this.activo;
    }
    public int getID(){
        /**
         * Obtiene el identificador (ID) del alquiler.
         *
         * @return int: El ID del alquiler.
         */
        return this.idAlquiler;
    }
    public double getPagoFinal(){
        /**
         * Obtiene el monto total del pago del alquiler.
         *
         * @return double: El monto total del pago del alquiler.
         */
        return this.pagoFinal;
    }
    public ArrayList<Conductor> getConductores(){
        /**
         * Obtiene la lista de conductores adicionales asociados al alquiler.
         *
         * @return ArrayList<Conductor>: La lista de conductores adicionales del alquiler.
         */
        return this.conductores;
    }
    public ArrayList<Seguro> getSeguros(){
        /**
         * Obtiene la lista de seguros asociados al alquiler.
         *
         * @return ArrayList<Seguro>: La lista de seguros asociados al alquiler.
         */
        return this.seguros;
    }
    public ArrayList<PagoExcedente> getPagosExcedentes(){
        /**
         * Obtiene la lista de pagos excedentes asociados al alquiler.
         *
         * @return ArrayList<PagoExcedente>: La lista de pagos excedentes asociados al alquiler.
         */
        return this.pagosExcedentes;
    }
    public Reserva getReserva(){
        /**
         * Obtiene la reserva asociada al alquiler.
         *
         * @return Reserva: La reserva asociada al alquiler.
         */
        return this.reserva;
    }
    public static ArrayList<alquiler> getListaAlquileres(){
        /**
         * Obtiene la lista de alquileres activos.
         *
         * @return ArrayList<alquiler>: La lista de alquileres activos.
         */
        return listaAlquileres;
    }
    public void setActivo(boolean activo){
        /**
         * Establece el estado de activo/inactivo del alquiler.
         *
         * @param activo: Un valor booleano que indica si el alquiler está activo (true) o inactivo (false).
         */
        this.activo=activo;
    }
    public void setPagoFinal(double valor){
        /**
         * Establece el monto total del pago del alquiler.
         *
         * @param valor: El monto total del pago del alquiler a establecer.
         */
        this.pagoFinal=valor;
    }
    public void addConductor(Conductor conductor){
        /**
         * Agrega un conductor adicional a la lista de conductores adicionales asociados al alquiler.
         *
         * @param conductor: El conductor adicional que se desea agregar a la lista.
         */
        this.conductores.add(conductor);
    }
    public void addSeguro(Seguro seguro){
        /**
         * Agrega un seguro a la lista de seguros asociados al alquiler.
         *
         * @param seguro: El seguro que se desea agregar a la lista.
         */
        this.seguros.add(seguro);
    }
    public void addPagoExcedente(PagoExcedente pago){
        /**
         * Agrega un pago excedente a la lista de pagos excedentes asociados al alquiler.
         *
         * @param pago: El pago excedente que se desea agregar a la lista.
         */
        this.pagosExcedentes.add(pago);
    }
    public static void addAlquiler(alquiler alquiler){
        /**
         * Agrega un alquiler a la lista de alquileres activos.
         *
         * @param alquiler: El alquiler que se desea agregar a la lista.
         */
        if (listaAlquileres==null){ listaAlquileres= new ArrayList<alquiler>();}
        listaAlquileres.add(alquiler);
    }
    public static alquiler assignAlquiler(int id_alquiler){
        /**
         * Busca un alquiler específico en la lista de alquileres activos por su ID y lo asigna.
         *
         * @param id_alquiler: El ID del alquiler que se desea buscar y asignar.
         * @return alquiler: El alquiler encontrado o null si no se encuentra un alquiler con el ID especificado.
         */
        alquiler retorno = null;
        for(alquiler i: alquiler.getListaAlquileres()){
            if(i.getID()==id_alquiler){
            retorno= i;
            break;
            }}
        return retorno;
    }

    public Double calcularPagoInicial(){
        /**
         * Calcula el monto del pago inicial de la reserva, teniendo en cuenta varios factores, como el costo base de la reserva,
         * conductores adicionales, seguros y pagos por excedentes.
         *
         * @return El monto del pago inicial de la reserva.
         */
        double pagoReserva=this.reserva.getPagoReserva();
        double costo70=(pagoReserva*7/3);
        double costo100=costo70+pagoReserva;
        int conductores=this.getConductores().size();
        int costo_conductores=(Inventario.getCostoPorConductorAdicional())*conductores;
        List<Seguro>lstseguro=seguros;
        int costo_seguros=0;
        for(Seguro i: lstseguro){
            double costoT=(i.getPctg_TarifaDiaria())*costo100;
            costo_seguros+=costoT;
        }
        double costo_excedentes=0;
        for(PagoExcedente i :this.getPagosExcedentes()){
            costo_excedentes+= i.getValorPago();
        }

        Double costo_T=costo70+costo_conductores+costo_seguros+costo_excedentes;
        return costo_T;
    }

    public Double calcularPagoFinal(Sede sedeActual, int averia){
        /**
         * Calcula el monto del pago final de la reserva, teniendo en cuenta múltiples factores, como seguros, averías, limpieza, traslado, y otros eventos programados.
         *
         * @param sedeActual La sede actual donde se realizará la devolución del vehículo.
         * @param averia El nivel de avería del vehículo (1 para leve, 2 para moderada, 3 para total).
         * @return El monto del pago final de la reserva.
         */
        boolean noPagaLeve=false;
        boolean noPagaModerado=false;
        boolean noPagaTotal=false;
        boolean limpiezaAgendada=false;
        boolean trasladoAgendado=false;
        for (Seguro i: this.getSeguros()){
            if(i.getID()==1){
                noPagaLeve=true;noPagaModerado=true;noPagaTotal=true;
            }
            else if(i.getID()==2||i.getID()==3){
                noPagaLeve=true;noPagaModerado=true;
            }
            else if(i.getID()==4){
                noPagaLeve=true;
            }
        }
        int fechaActual= Integer.parseInt(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")));
        int fechaMas1=Integer.parseInt((LocalDate.now()).plusDays(1).format(DateTimeFormatter.ofPattern("yyyyMMdd")));            
        int fechaMas5=Integer.parseInt((LocalDate.now()).plusDays(5).format(DateTimeFormatter.ofPattern("yyyyMMdd")));
        int fechaMas6=Integer.parseInt((LocalDate.now()).plusDays(6).format(DateTimeFormatter.ofPattern("yyyyMMdd")));
        int fechaMas10=Integer.parseInt((LocalDate.now()).plusDays(10).format(DateTimeFormatter.ofPattern("yyyyMMdd")));
        int fechaMas11=Integer.parseInt((LocalDate.now()).plusDays(11).format(DateTimeFormatter.ofPattern("yyyyMMdd")));
        int fechaMas30=Integer.parseInt((LocalDate.now()).plusDays(30).format(DateTimeFormatter.ofPattern("yyyyMMdd")));
        int fechaMas31=Integer.parseInt((LocalDate.now()).plusDays(31).format(DateTimeFormatter.ofPattern("yyyyMMdd")));

        LocalTime hora = LocalTime.now();
        int horaActual = hora.getHour() * 100 + hora.getMinute();
        double pago30=this.reserva.getPagoReserva();
        double pago70=this.calcularPagoInicial();
        this.reserva.setFechaEntregar(fechaActual);
        double sPago30= this.reserva.getPagoReserva();
        double sPago70=this.calcularPagoInicial();
        double saldoFinal=(sPago30+sPago70)-(pago30+pago70);
        //1. Revisar tema sedes
        int idSedeActual=sedeActual.getID();
        Vehiculo vehiculo=this.getReserva().getVehiculoAsignado();
        if (idSedeActual!=vehiculo.getSede().getID()){
            saldoFinal+=Inventario.getCostoPorTrasladoSedes();
            Evento traslado= new Evento(fechaActual, fechaMas1, horaActual, horaActual, "EnTraslado");
            vehiculo.addEvento(traslado);
            trasladoAgendado=true;
            
        }  
        Categoria categoria=this.reserva.getCategoria();
        if (averia==1){
            if (noPagaLeve==false){saldoFinal+=categoria.getCostoAveriaLeve();}
            if(trasladoAgendado==false){
            Evento mantenimiento= new Evento(fechaActual,fechaMas5 ,horaActual ,horaActual , "EnMantenimiento");
            this.getReserva().getVehiculoAsignado().addEvento(mantenimiento);
            Inventario.getListaEventos().add(mantenimiento);
            

            int finLimpieza=Integer.parseInt((LocalDate.now()).plusDays(6).format(DateTimeFormatter.ofPattern("yyyyMMdd")));  
            //TODO fuera de los if meter mantenimiento en listas          
            Evento limpieza= new Evento(fechaMas5, finLimpieza, horaActual, horaActual, "EnLimpieza");
            this.getReserva().getVehiculoAsignado().addEvento(limpieza);
            Inventario.getListaEventos().add(limpieza);
            limpiezaAgendada=true;
            }
            else{
            Evento mantenimiento= new Evento(fechaMas1,fechaMas6,horaActual ,horaActual , "EnMantenimiento");
            this.getReserva().getVehiculoAsignado().addEvento(mantenimiento);
            Inventario.getListaEventos().add(mantenimiento);
            

            int finLimpieza=Integer.parseInt((LocalDate.now()).plusDays(7).format(DateTimeFormatter.ofPattern("yyyyMMdd")));            
            Evento limpieza= new Evento(fechaMas6, finLimpieza, horaActual, horaActual, "EnLimpieza");
            this.getReserva().getVehiculoAsignado().addEvento(limpieza);
            Inventario.getListaEventos().add(limpieza);
            limpiezaAgendada=true;
            }

        }
        else if (averia==2){
            if (noPagaModerado==false){saldoFinal+=categoria.getCostoAveriaModerada();}
            if (trasladoAgendado==false){
            Evento mantenimiento= new Evento(fechaActual,fechaMas10 ,horaActual ,horaActual , "EnMantenimiento");
            this.getReserva().getVehiculoAsignado().addEvento(mantenimiento);
            Inventario.getListaEventos().add(mantenimiento);

            int finLimpieza=Integer.parseInt((LocalDate.now()).plusDays(11).format(DateTimeFormatter.ofPattern("yyyyMMdd")));            
            Evento limpieza= new Evento(fechaMas10, finLimpieza, horaActual, horaActual, "EnLimpieza");
            this.getReserva().getVehiculoAsignado().addEvento(limpieza);
            Inventario.getListaEventos().add(limpieza);
            limpiezaAgendada=true;
            }
            else{
            Evento mantenimiento= new Evento(fechaMas1,fechaMas11 ,horaActual ,horaActual , "EnMantenimiento");
            this.getReserva().getVehiculoAsignado().addEvento(mantenimiento);
            Inventario.getListaEventos().add(mantenimiento);

            int finLimpieza=Integer.parseInt((LocalDate.now()).plusDays(12).format(DateTimeFormatter.ofPattern("yyyyMMdd")));            
            Evento limpieza= new Evento(fechaMas11, finLimpieza, horaActual, horaActual, "EnLimpieza");
            this.getReserva().getVehiculoAsignado().addEvento(limpieza);
            Inventario.getListaEventos().add(limpieza);
            limpiezaAgendada=true;

            }
        }
        else if (averia==3){
            if (noPagaTotal==false){saldoFinal+=categoria.getCostoAveriaTotal();}
            if (trasladoAgendado==false){
            Evento mantenimiento= new Evento(fechaActual,fechaMas30 ,horaActual ,horaActual , "EnMantenimiento");
            this.getReserva().getVehiculoAsignado().addEvento(mantenimiento);
            Inventario.getListaEventos().add(mantenimiento);

            int finLimpieza=Integer.parseInt((LocalDate.now()).plusDays(31).format(DateTimeFormatter.ofPattern("yyyyMMdd")));            
            Evento limpieza= new Evento(fechaMas30, finLimpieza, horaActual, horaActual, "EnLimpieza");
            this.getReserva().getVehiculoAsignado().addEvento(limpieza);
            Inventario.getListaEventos().add(limpieza);
            limpiezaAgendada=true;
            }
            else{
            Evento mantenimiento= new Evento(fechaMas1,fechaMas31 ,horaActual ,horaActual , "EnMantenimiento");
            this.getReserva().getVehiculoAsignado().addEvento(mantenimiento);
            Inventario.getListaEventos().add(mantenimiento);

            int finLimpieza=Integer.parseInt((LocalDate.now()).plusDays(32).format(DateTimeFormatter.ofPattern("yyyyMMdd")));            
            Evento limpieza= new Evento(fechaMas31, finLimpieza, horaActual, horaActual, "EnLimpieza");
            this.getReserva().getVehiculoAsignado().addEvento(limpieza);
            Inventario.getListaEventos().add(limpieza);
            limpiezaAgendada=true;

            }
        }

        if(limpiezaAgendada==false){
            if(trasladoAgendado==false){
            int finLimpieza=Integer.parseInt((LocalDate.now()).plusDays(1).format(DateTimeFormatter.ofPattern("yyyyMMdd")));            
            Evento limpieza= new Evento(fechaActual, finLimpieza, horaActual, horaActual, "EnLimpieza");
            this.getReserva().getVehiculoAsignado().addEvento(limpieza);
            Inventario.getListaEventos().add(limpieza);
            }
            else{
            int finLimpieza=Integer.parseInt((LocalDate.now()).plusDays(2).format(DateTimeFormatter.ofPattern("yyyyMMdd")));            
            Evento limpieza= new Evento(fechaMas1, finLimpieza, horaActual, horaActual, "EnLimpieza");
            this.getReserva().getVehiculoAsignado().addEvento(limpieza);
            Inventario.getListaEventos().add(limpieza);
            }
        }
      
        System.out.println("\n\t>Se programaron los eventos correspondientes");
        return saldoFinal;
    }
}
