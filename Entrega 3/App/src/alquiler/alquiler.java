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
        this.pagoFinal=-1;
        this.reserva=reserva;
        this.conductores= new ArrayList<Conductor>();
        this.seguros= new ArrayList<Seguro>();
        this.pagosExcedentes= new ArrayList<PagoExcedente>();
    }

    public int getID(){
        return this.idAlquiler;
    }
    public double getPagoFinal(){
        return this.pagoFinal;
    }
    public ArrayList<Conductor> getConductores(){
        return this.conductores;
    }
    public ArrayList<Seguro> getSeguros(){
        return this.seguros;
    }
    public ArrayList<PagoExcedente> getPagosExcedentes(){
        return this.pagosExcedentes;
    }
    public Reserva getReserva(){
        return this.reserva;
    }
    public static ArrayList<alquiler> getListaAlquileres(){
        return listaAlquileres;
    }
    public void setPagoFinal(double valor){
        this.pagoFinal=valor;
    }
    public void addConductor(Conductor conductor){
        this.conductores.add(conductor);
    }
    public void addSeguro(Seguro seguro){
        this.seguros.add(seguro);
    }
    public void addPagoExcedente(PagoExcedente pago){
        this.pagosExcedentes.add(pago);
    }
    public static void addAlquiler(alquiler alquiler){
        if (listaAlquileres==null){ listaAlquileres= new ArrayList<alquiler>();}
        listaAlquileres.add(alquiler);
    }
    public static alquiler assignAlquiler(int id_alquiler){
        alquiler retorno = null;
        for(alquiler i: alquiler.getListaAlquileres()){
            if(i.getID()==id_alquiler){
            retorno= i;
            break;
            }}
        return retorno;
    }

    public void agregarConductores() {
        boolean continuarPersonal1 = true;
        while (continuarPersonal1==true){
            System.out.println("¿Desea agregar un conductor adicional?");
            System.out.println("1. Sí");
            System.out.println("2. No");
            int opcion = Integer.parseInt(input("Por favor seleccione una opción"));
            try {
                if(opcion==1){
                    String nombre = input("Ingrese el nombre del conductor");
                    int cedula = Integer.parseInt(input("Por favor ingrese el número de cédula del conductor"));
                    Licencia licencia = Licencia.crearLicencia(); 
                    if (licencia != null){
                        Conductor conductor = new Conductor(nombre, cedula, licencia);
                        addConductor(conductor);
                        System.out.println("Conductor Registrado: " + nombre);
                        System.out.println("Número de Licencia: " + licencia.getNumeroLicencia());
                    }
                    else{
                        System.out.println("No se pudo registrar");
                    }
                    
                }
                else if(opcion==2){continuarPersonal1 = false;}
            
        
    }catch (NumberFormatException e){System.out.println("\n>Ingrese los valores requeridos en el formato solicitado");}}}

    public void agregarSeguros (){
        boolean continuar = true;
        
        while (continuar){
             for(Seguro i: Inventario.getListaSeguros()){
            System.out.println("ID del seguro: " + i.getID()+"Descripción del seguro: " + i.getDescripcion() + "Tarifa diaria del seguro(en porcentage): " + i.getPctg_TarifaDiaria());
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

    public Double calcularPagoInicial(){
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

    public Double calcularPagoFinal(Sede sedeActual){
        int fechaActual= Integer.parseInt(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")));
        int fechaMas5=Integer.parseInt((LocalDate.now()).plusDays(5).format(DateTimeFormatter.ofPattern("yyyyMMdd")));
        int fechaMas10=Integer.parseInt((LocalDate.now()).plusDays(10).format(DateTimeFormatter.ofPattern("yyyyMMdd")));
        int fechaMas30=Integer.parseInt((LocalDate.now()).plusDays(30).format(DateTimeFormatter.ofPattern("yyyyMMdd")));

        LocalTime hora = LocalTime.now();
        int horaActual = hora.getHour() * 100 + hora.getMinute();
        double pago30=this.reserva.getPagoReserva();
        double pago70=this.calcularPagoInicial();
        this.reserva.setFechaEntregar(fechaActual);
        double sPago30= this.reserva.getPagoReserva();
        double sPago70=this.calcularPagoInicial();
        double saldoFinal=(sPago30+sPago70)-(pago30+pago70);
        System.out.println("¿El vehiculo tiene algun tipo de daño?");
        System.out.println("1. Averia leve");
        System.out.println("2. Averia moderada");
        System.out.println("3. Averia total");
        System.out.println("4. No");
        Categoria categoria=this.reserva.getCategoria();
        int opcion = Integer.parseInt(input("Por favor seleccione una opción")); 
        if (opcion==1){
            saldoFinal+=categoria.getCostoAveriaLeve();
            Evento mantenimiento= new Evento(fechaActual,fechaMas5 ,horaActual ,horaActual , "MantenimientoAveriaLeve");
            this.getReserva().getVehiculoAsignado().addEvento(mantenimiento);
            Inventario.getListaEventos().add(mantenimiento);
        }
        else if (opcion==2){
            saldoFinal+=categoria.getCostoAveriaModerada();
            saldoFinal+=categoria.getCostoAveriaLeve();
            Evento mantenimiento= new Evento(fechaActual,fechaMas10 ,horaActual ,horaActual , "MantenimientoAveriaLeve");
            this.getReserva().getVehiculoAsignado().addEvento(mantenimiento);
            Inventario.getListaEventos().add(mantenimiento);
        }
        else if (opcion==3){
            saldoFinal+=categoria.getCostoAveriaTotal();
            Evento mantenimiento= new Evento(fechaActual,fechaMas30 ,horaActual ,horaActual , "MantenimientoAveriaLeve");
            this.getReserva().getVehiculoAsignado().addEvento(mantenimiento);
            Inventario.getListaEventos().add(mantenimiento);
            this.getReserva().getVehiculoAsignado().setAveriado(true);
        }
        else{

        }
        int idsedeAntigua=this.reserva.getSedeEntregar().getID();
        int idSedeActual=sedeActual.getID();
        if (idSedeActual!=idsedeAntigua){
            saldoFinal+=Inventario.getCostoPorTrasladoSedes();
            Vehiculo vehiculo =this.reserva.getVehiculoAsignado();
            vehiculo.setTrasladoASede(sedeActual);
            //evento hecho
        }
      

        return saldoFinal;
    }


    public static void crearAlquiler(List<Reserva>reservas,Cliente cliente, Sede sedePersonal){
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
            System.out.println("ID de la reserva: " + idreseva);
            System.out.println("Categoría: " + categoria);
            System.out.println("Fecha y Hora de entrega: " + fechaRecoger + ", " + horaRecoger);
            System.out.println("Fecha y Hora de devolución: "+ fechaEntregar + ", " + horaEntregar);
            System.out.println("Sede de entrega: " + sedeRecoger) ;
            System.out.println("Sede de devolución: " + sedeEntrega);
            System.out.println("Pago Realizado por la reserva: " + pago);
        }
        }

        int id = Integer.parseInt(input("Por favor ingrese el ID de la reserva que desee completar: "));
        Reserva reserva = Reserva.assignReserva(id);
        if (reserva != null && reserva.getCliente().getNumeroCedula()==cliente.getNumeroCedula()) {
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
            System.out.println("\n>Se debitaron COP " +Double.toString(pagoInicial) + "de su tarjeta terminada en "+ Long.toString(ultimos_digitos)); 
            System.out.println("(Pago correspondiente al 70% del alquiler + pago por seguros + pago por conductores adicionales)"); 
            addAlquiler(alquiler);
            alquiler.setPagoFinal(pagoInicial); 
            }
            else{
            System.out.println("\n> Lastimosamente, el vehículo reservado actualmente se encuentra "+estadoActualVehiculo+", y no hay más vehiculos disponibles.");
            System.out.println("> Reserva cancelada, Alquiler cancelado. Prontamente se retornará el pago de la reserva (COP"+Double.toString(pagoReserva)+")." ); 
            }
            
            }
        
        else {System.out.println("Reserva no encontrada/disponible. Por favor, ingrese un ID válido.");}
    }

    public static void completarAlquiler(Cliente cliente,Sede sedePersonal){
        int fechaActual= Integer.parseInt(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")));
        LocalTime hora = LocalTime.now();
        int horaActual = hora.getHour() * 100 + hora.getMinute();
        alquiler alquiler_u=null;
        for (alquiler i: getListaAlquileres()){
            if (i.getReserva().getCliente().getNumeroCedula()==cliente.getNumeroCedula()&&i.activo==true){
                alquiler_u=i;
                break;
            }}
        int id = Integer.parseInt(input("Por favor ingrese el ID del alquiler que desee completar: "));
        alquiler_u = assignAlquiler(id);
        if(alquiler_u.getReserva().getCliente().getNumeroCedula()==cliente.getNumeroCedula()){
        alquiler_u.activo=false;
        Reserva reserva= alquiler_u.getReserva();
        reserva.setSedeRecoger(sedePersonal);
        reserva.setFechaEntregar(fechaActual);
        reserva.setHoraEntregar(horaActual);
        double costoFinal=alquiler_u.calcularPagoFinal(sedePersonal);
        alquiler_u.setPagoFinal(costoFinal);
        System.out.println("\n>El vehículo se ha devuelto correctamente y se han debitado COP"+"de su tarjeta terminada en "+ Long.toString(cliente.getTarjeta().getNumeroTarjeta()% 100003));
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
