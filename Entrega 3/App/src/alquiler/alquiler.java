package alquiler;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import inventario.Categoria;
import inventario.Evento;
import inventario.Inventario;
import inventario.Sede;
import inventario.Seguro;
import inventario.Vehiculo;
import usuario.Cliente;
import usuario.Conductor;
import usuario.Licencia;

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

    private void agregarConductores() {
        /**
         * Permite agregar conductores adicionales al alquiler, brindando la opción al cliente de registrar nuevos conductores.
         *
         * Muestra un menú que permite al cliente agregar o no conductores adicionales al alquiler. Si se selecciona "Sí," solicita información del conductor, incluyendo nombre, número de cédula y licencia. Luego, registra al conductor en el alquiler.
         *
         * @throws NumberFormatException Si se ingresan valores no válidos o en un formato incorrecto.
         *
         */
        boolean continuarPersonal1 = true;
        while (continuarPersonal1==true){
            System.out.println("\n¿Desea agregar un conductor adicional?");
            System.out.println("1. Sí");
            System.out.println("2. No\n");
            int opcion = Integer.parseInt(input("Por favor seleccione una opción"));
            try {
                if(opcion==1){
                    String nombre = input("Ingrese el nombre del conductor");
                    int cedula = Integer.parseInt(input("Por favor ingrese el número de cédula del conductor"));
                    Licencia licencia = Licencia.crearLicencia(); 
                    if (licencia != null){
                        Conductor conductor = new Conductor(nombre, cedula, licencia);
                        this.addConductor(conductor);
                        System.out.println(">Conductor registrado");
                    }
                    else{
                        System.out.println(">No se pudo registrar");
                    }
                    
                }
                else if(opcion==2){continuarPersonal1 = false;}
            
        
    }
    catch (NumberFormatException e){System.out.println("\n>Ingrese los valores requeridos en el formato solicitado");}}}

    private void agregarSeguros (){
        /**
         * Agrega seguros al alquiler, permitiendo al cliente seleccionar entre opciones disponibles.
         *
         * Muestra una lista de seguros disponibles con sus detalles, permite al cliente elegir uno o más seguros y los agrega al alquiler.
         *
         * @throws NumberFormatException Si se ingresa un ID de seguro no válido.
         *
         */
        boolean continuar = true;
        System.out.println();
        while (continuar){
             for(Seguro i: Inventario.getListaSeguros()){
            System.out.println("ID del seguro: " + i.getID()+" .Descripción del seguro: " + i.getDescripcion() + ". Porcentaje diario (se multiplica por la tarifa de la renta) a pagar: " + Double.toString(i.getPctg_TarifaDiaria()*100)+"%.");
            }
            System.out.println("¿Desea agregar un seguro al alquiler?");
            System.out.println("1. Sí");
            System.out.println("2. No");
            int opcion = Integer.parseInt(input("Por favor seleccione una opción"));
            if (opcion==1) {
                try{
                    int idseguro = Integer.parseInt(input("Ingrese el ID del seguro que desee añadir"));
                    Seguro seguro = Inventario.assignSeguro(idseguro);
                    addSeguro(seguro);
                    System.out.println("Seguro agregado al alquiler.");
                } catch(NumberFormatException e){System.out.println("ID de seguro no válido. Intente nuevamente.");}
            }
            else if(opcion==2) { continuar = false;}
            else {System.out.println("Por favor seleccione una opción válida.");}
        }
    }

    private Double calcularPagoInicial(){
        /**
         * Calcula el pago inicial de un alquiler, teniendo en cuenta la reserva, conductores, seguros y excedentes.
         *
         * @return Double: El costo total del pago inicial del alquiler.
         * @throws IllegalArgumentException Si no se pueden calcular los costos iniciales debido a valores no válidos.
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

    private Double calcularPagoFinal(Sede sedeActual){
        /**
         * Descripción del método: Calcula el pago final de un alquiler considerando seguros, daños y eventos agendados.
         *
         * @param sedeActual: Sede - La sede actual del alquiler.
         * @type sedeActual: Sede
         *
         * @throws NumberFormatException: Si no se puede convertir la fecha actual o la hora actual a números enteros.
         * @throws IllegalArgumentException: Si no se puede completar la reserva debido a la disponibilidad de vehículos.
         *
         * @returns Double - El saldo final del alquiler, teniendo en cuenta todos los factores.
         * @rtype Double
         */
        /*
         seguro id1: no cobramos ningun daño, 
         seguro id2 ó id3: solo cobramos daños graves, seguro4: no cobramos daños leves.
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

        System.out.println("¿El vehiculo tiene algun tipo de daño?");
        System.out.println("1. Averia leve");
        System.out.println("2. Averia moderada");
        System.out.println("3. Averia total");
        System.out.println("4. No");
        Categoria categoria=this.reserva.getCategoria();
        int opcion = Integer.parseInt(input("Por favor seleccione una opción")); 
        if (opcion==1){
            if (noPagaLeve==false){saldoFinal+=categoria.getCostoAveriaLeve();}
            if(trasladoAgendado==false){
            Evento mantenimiento= new Evento(fechaActual,fechaMas5 ,horaActual ,horaActual , "EnMantenimiento");
            this.getReserva().getVehiculoAsignado().addEvento(mantenimiento);
            Inventario.getListaEventos().add(mantenimiento);
            

            int finLimpieza=Integer.parseInt((LocalDate.now()).plusDays(6).format(DateTimeFormatter.ofPattern("yyyyMMdd")));            
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
        else if (opcion==2){
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
        else if (opcion==3){
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


    public static void crearAlquiler(List<Reserva>reservas,Cliente cliente, Sede sedePersonal){
        /**
         * Descripción del método: Crea un nuevo alquiler a partir de una lista de reservas, un cliente y una sede personalizada.
         *
         * @param reservas: List<Reserva> - La lista de reservas disponibles.
         * @type reservas: List<Reserva>
         * @param cliente: Cliente - El cliente que realiza el alquiler.
         * @type cliente: Cliente
         * @param sedePersonal: Sede - La sede en la que se crea el alquiler.
         * @type sedePersonal: Sede
         *
         * @throws NumberFormatException: Si no se puede convertir la fecha u hora actual a números enteros.
         * @throws IllegalArgumentException: Si no se puede completar la reserva debido a la disponibilidad de vehículos.
         */
        System.out.println("Reserva/s activa/s del cliente: ");
        int fechaActual= Integer.parseInt(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")));
        LocalTime hora = LocalTime.now();
        int horaActual = hora.getHour() * 100 + hora.getMinute();
        for(Reserva i: reservas){
            if(i.getFechaRecoger()==fechaActual&&i.getCliente().getNumeroCedula()==cliente.getNumeroCedula()&& sedePersonal.getID()==i.getSedeRecoger().getID()){
            int idreseva = i.getID();
            String categoria = i.getCategoria().getnombreCategoria();
            int fechaRecoger = i.getFechaRecoger();
            int fechaEntregar = i.getFechaEntregar();
            int horaRecoger = i.getHoraRecoger();
            int horaEntregar = i.getHoraEntregar();
            String sedeEntrega = i.getSedeEntregar().getNombre();
            String sedeRecoger = i.getSedeRecoger().getNombre();
            double pago = i.getPagoReserva();
            System.out.println("    ID de la reserva: " + idreseva);
            System.out.println("    Categoría: " + categoria);
            System.out.println("    Fecha y Hora de entrega: " + fechaRecoger + ", " + horaRecoger);
            System.out.println("    Fecha y Hora de devolución: "+ fechaEntregar + ", " + horaEntregar);
            System.out.println("    Sede de entrega: " + sedeRecoger) ;
            System.out.println("    Sede de devolución: " + sedeEntrega);
            System.out.println("    Pago Realizado por la reserva: " + pago+"\n");
        }
        }

        int id = Integer.parseInt(input("Por favor ingrese el ID de la reserva que desee completar"));
        Reserva reserva = Reserva.assignReserva(id);
        if (reserva != null && reserva.getCliente().getNumeroCedula()==cliente.getNumeroCedula()&& sedePersonal.getID()==reserva.getSedeRecoger().getID()) {
            //quitar reserva
            Vehiculo vehiculo=reserva.getVehiculoAsignado();
            vehiculo.eliminarReservaActiva(id);
            Reserva.getListaReservas().remove(reserva);
            boolean sePuedeCompletarReserva=false;
            String estadoActualVehiculo=vehiculo.actualizarEstado(fechaActual, horaActual,reserva.getFechaEntregar(),reserva.getHoraEntregar());
            long ultimos_digitos=(reserva.getCliente().getTarjeta().getNumeroTarjeta()% 10000);
            double pagoReserva=reserva.getPagoReserva();

            if (estadoActualVehiculo.equals("Disponible")){
                vehiculo.addReservaActiva(reserva);
                sePuedeCompletarReserva=true;
            }
            else{
                reserva.setVehiculoAsignado();
                if (reserva.getVehiculoAsignado()!=null)
                {
                    sePuedeCompletarReserva=true;
                }
            }
            if (sePuedeCompletarReserva){
            Reserva.addReserva(reserva);
            alquiler alquiler = new alquiler(reserva);
            alquiler.agregarConductores();
            alquiler.agregarSeguros();
            double pagoInicial=alquiler.calcularPagoInicial();
            System.out.println("\n>Se debitaron COP " +Double.toString(pagoInicial) + " de su tarjeta terminada en "+ Long.toString(ultimos_digitos)+"."); 
            System.out.println("(Pago correspondiente al 70% del alquiler + pago por seguros + pago por conductores adicionales)"); 
            System.out.println("\n>En este momento se puede entregar el vehículo al cliente."); 
            vehiculo.addAlquiler(alquiler);
            addAlquiler(alquiler);
            alquiler.setPagoFinal(pagoInicial);
            alquiler.setActivo(true);
            }
            else{
            System.out.println("\n> Lastimosamente, el vehículo reservado actualmente se encuentra "+estadoActualVehiculo+", y no hay más vehiculos disponibles.");
            System.out.println("> Reserva cancelada, Alquiler cancelado. Prontamente se retornará el pago de la reserva (COP"+Double.toString(pagoReserva)+")." );
            System.out.println("> Si el cliente requiere, se puede planificar otra reserva para el día de hoy con una categoría de vehículo diferente (opción 3 del menú)." ); 
 
            }
            
            }
        
        else {System.out.println("Reserva no encontrada/disponible. Por favor, ingrese un ID válido.");}
    }

    public static void completarAlquiler(Cliente cliente,Sede sedePersonal){
        /**
         * Descripción del método: Completa un alquiler para un cliente en una sede personalizada.
         *
         * @param cliente: Cliente - El cliente que realiza el alquiler.
         * @type cliente: Cliente
         * @param sedePersonal: Sede - La sede en la que se completa el alquiler.
         * @type sedePersonal: Sede
         *
         * @throws NumberFormatException: Si no se puede convertir la fecha actual o la hora actual a números enteros.
         * @throws IllegalArgumentException: Si se intenta completar un alquiler inexistente o no activo.
         */
        int fechaActual= Integer.parseInt(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")));
        LocalTime hora = LocalTime.now();
        int horaActual = hora.getHour() * 100 + hora.getMinute();
        alquiler alquiler_u=null;
        System.out.println("Alquiler/es activo/s del cliente: ");

        for (alquiler i: getListaAlquileres()){
            if (i.getReserva().getCliente().getNumeroCedula()==cliente.getNumeroCedula()&&i.getActivo()==true){
            int id = i.getID();
            String placa = i.getReserva().getVehiculoAsignado().getPlaca();
            int fechaRecoger = i.getReserva().getFechaRecoger();
            int fechaEntregar = i.getReserva().getFechaEntregar();
            System.out.println("ID de la reserva: " + id+". Placa vehículo: "+placa+".");
            System.out.println("    Fecha de recogida: " +fechaRecoger+". Fecha de devolucion: "+fechaEntregar+".\n");
            }}
        //mostrar los alquileres que pueda completar usando el for anterior TODO
        int id = Integer.parseInt(input("Por favor ingrese el ID del alquiler que desee completar"));
        alquiler_u = assignAlquiler(id);
        if(alquiler_u!=null && alquiler_u.getReserva().getCliente().getNumeroCedula()==cliente.getNumeroCedula() && alquiler_u.getActivo()==true){
        alquiler_u.activo=false;
        Reserva reserva= alquiler_u.getReserva();
        double pago70=alquiler_u.getPagoFinal();
        Vehiculo vehiculo=reserva.getVehiculoAsignado();
        reserva.setSedeEntregar(sedePersonal);
        reserva.setFechaEntregar(fechaActual);
        reserva.setHoraEntregar(horaActual);
        double newPago=alquiler_u.calcularPagoFinal(sedePersonal);
        alquiler_u.setPagoFinal(newPago+pago70);
        vehiculo.eliminarReservaActiva(id);

        if (newPago>0){
        System.out.println("\n>El vehículo se ha devuelto correctamente y se han debitado COP "+Double.toString(newPago)+" de su tarjeta terminada en "+ Long.toString(cliente.getTarjeta().getNumeroTarjeta()% 10000)+".");
        }
        else{
        System.out.println("\n>El vehículo se ha devuelto correctamente y el cliente tiene un saldo a favor de COP "+Double.toString(Math.abs(newPago))+" que se transferirán a su tarjeta terminada en "+ Long.toString(cliente.getTarjeta().getNumeroTarjeta()% 10000)+".");

        }
        }
        else{
        System.out.println("Alquiler no encontrada/disponible. Por favor, ingrese un ID válido.");}           
    }

    public static String input(String mensaje) {
		try {
			System.out.print(mensaje + ": ");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			return reader.readLine();
		}
		catch (IOException e) {
			System.out.println("Error leyendo de la consola");
			e.printStackTrace();
		}
		return null;
    }

}
