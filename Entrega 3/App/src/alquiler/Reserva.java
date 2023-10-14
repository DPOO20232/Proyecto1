package alquiler;
import java.util.ArrayList;
import java.util.List;
import inventario.Categoria;
import inventario.Evento;
import inventario.Inventario;
import inventario.Vehiculo;
import usuario.Cliente;
import usuario.Licencia;
import usuario.Tarjeta;
import usuario.Usuario;
import inventario.Sede;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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


    public static void crearReserva(Cliente cliente, boolean reservaEnSede){
        //reservaEnSede es true cuando la hace el personal de atencion
            System.out.println("\n¡Bienvenido a nuestro sistema de reservas!\n");
        try { 
            boolean continuar=true;
            while(continuar){
            System.out.println(">Lista de Sedes Disponibles:");
                List<Sede> sedes = Inventario.getListaSedes();
                for (int i = 0; i < sedes.size(); i++) {
                    System.out.println((i + 1) + ". " + sedes.get(i).getNombre()+" ("+sedes.get(i).getUbicacion()+").");
                }
            Sede sedeRecoger=null;
            Sede sedeEntrega=null;

            int sedeRecogerIndex = Integer.parseInt(input("\nSeleccione una sede para recoger su vehículo(ingrese el número)"));
            int sedeEntregaIndex = Integer.parseInt(input("Seleccione una sede para la entrega de su vehículo(ingrese el número)"));
            if(sedeRecogerIndex<=sedes.size()&& sedeEntregaIndex<=sedes.size()){
            sedeRecoger = sedes.get(sedeRecogerIndex - 1);
            sedeEntrega = sedes.get(sedeEntregaIndex - 1);
            }
            if(sedeRecoger!=null&&sedeEntrega!=null){
            System.out.println("\n\t>Información Sede donde se recogerá el vehículo:");
            sedeRecoger.printInfo();
            int frecoger = Integer.parseInt(input("Por favor ingrese la fecha en la que desee recoger su vehículo(en formato aaaammdd)"));
            int hrecoger = Integer.parseInt(input("Considerando los horarios de atención de la sede, ingrese la hora en la que desee recoger su vehículo(en formato hhmm)"));
            System.out.println("\n\t>Información Sede donde se devolverá el vehículo:");
            sedeEntrega.printInfo();
            int fentregar = Integer.parseInt(input("Por favor ingrese la fecha en la que desee entregar su vehículo(en formato aaaammdd)"));
            int hentregar = Integer.parseInt(input("Considerando los horarios de atención de la sede, ingrese la hora en la que desee entregar su vehículo(en formato hhmm)"));
            boolean horaVrecoger = horaValida(hrecoger);
            boolean horaVdevolucion = horaValida(hentregar);
            boolean fVrecoger = fechaValidaReserva(frecoger);
            boolean fVdevolucion = fechaValidaReserva(fentregar);
            boolean posibleRecoger=sedeRecoger.estaAbierta(frecoger,hrecoger);
            boolean posibleEntregar=sedeEntrega.estaAbierta(fentregar,hentregar);
            if (horaVrecoger && horaVdevolucion && fVrecoger && fVdevolucion &&posibleEntregar&&posibleRecoger ){
                Licencia licencia_act= cliente.getLicencia();
                Tarjeta tarjeta_act= cliente.getTarjeta();
                if(Usuario.checkVencimientoLicencia(licencia_act, fentregar / 10000, (fentregar / 100) % 100, fentregar % 100)==false){
                if (tarjeta_act.checkVencimientoTarjeta(fentregar / 10000, (fentregar / 100) % 100, fentregar % 100)==false){
                System.out.println("\nLista de Categorías de Vehículos Disponibles:\n");
                List<Categoria> categorias = Inventario.getListaCategorias();
                for (int i = 0; i < categorias.size(); i++) {
                    Categoria i_categoria=categorias.get(i);
                    System.out.println((i + 1) + ". " + i_categoria.getnombreCategoria());
                    System.out.println("   - Costo Diario: $" + i_categoria.getTarifaDiaria());
                    System.out.println("   - Capacidad: " + i_categoria.getCapacidad() + " personas");
                }
                int categoriaElegidaIndex = Integer.parseInt(input("Seleccione una categoría (ingrese el número): "));
                boolean continuar1=true;
                while(continuar1){
                if (categoriaElegidaIndex>=1 && categoriaElegidaIndex<=(categorias.size())){
                    Categoria categoriaElegida = categorias.get(categoriaElegidaIndex - 1);
                    Reserva reserva_u = new Reserva(frecoger, fentregar, hrecoger, hentregar, reservaEnSede, sedeRecoger, sedeEntrega, categoriaElegida, cliente);
                    // AÑADIR SOLO SI ENCONTRAMOS CARRO 
                    reserva_u.setVehiculoAsignado();
                    if (reserva_u.getVehiculoAsignado()!=null){
                        boolean continuar2=true;
                        System.out.println("> Encontramos un vehículo para tí!");
                        while(continuar2){
                        int numTarjeta = Integer.parseInt(input("Para debitar el 30% del alquiler de su cuenta, por favor ingrese los últimos 4 dígitos de la tarjeta que tiene registrada"));
                        if (numTarjeta== (cliente.getTarjeta().getNumeroTarjeta())%10000){
                        addReserva(reserva_u);
                        reserva_u.setPagoReserva(frecoger,hrecoger,fentregar ,hentregar );
                        System.out.println(">Se debitaron $"+ Double.toString(reserva_u.getPagoReserva())+".");
                        System.out.println(">Reserva creada exitosamente, el id de su reserva es: "+Integer.toString(reserva_u.getID()));
                                continuar2=false;
                                continuar1=false;
                                continuar=false;
                                Reserva.addReserva(reserva_u);
                        }
                        else{System.out.println("\n>Los últimos 4 dígitos ingresados no corresponden a los últimos 4 dígitos de su tarjeta, desea intentarlo nuevamente?");
                            System.out.println("1.Sí");
                            System.out.println("2.No(ó cualquier otro número)");
                            int opcion = Integer.parseInt(input("Por favor seleccione una opción"));
                            if(opcion>1){continuar2=false;
                                continuar1=false;
                                continuar=false;}}
                    }}else{System.out.println(">No se encontraron vehículos disponibles para la categoría dada en el rango de fechas requerido.");}
                }else{System.out.println(">Elija una categoría de las opciones mostradas.");}
            }} 
            else{ System.out.println("\n>Su tarjeta caducará/caducó, desea actualizar su licencia?");
                System.out.println("1.Sí");
                System.out.println("2.No(ó cualquier otro número)");
                int opcion = Integer.parseInt(input("Por favor seleccione una opción"));
                if(opcion==1){cliente.setTarjeta();}
                else{continuar=false;}
                }} else{System.out.println("\n>Su licencia caducará/caducó, desea actualizar su licencia?");
                System.out.println("1.Sí");
                System.out.println("2.No(ó cualquier otro número)");
                int opcion = Integer.parseInt(input("Por favor seleccione una opción"));
                if(opcion==1){cliente.setLicencia();}
                else{continuar=false;}
            }} else {System.out.println(">Las fechas u horas ingresadas no son válidas. Por favor, inténtelo nuevamente.");}
            }
            else{System.out.println(">Elija opciones de sede válidas.\n");}}}
        catch (NumberFormatException e) {
            System.out.println(">Debe ingresar los datos requeridos para que la creación de su reserva sea exitosa.");
        }

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

    public static boolean fechaValidaReserva(int fecha) {
        Calendar fechaActual = Calendar.getInstance();
        int diaactual = fechaActual.get(Calendar.DAY_OF_MONTH);
        int mesactual = fechaActual.get(Calendar.MONTH) + 1;
        int anhoactual = fechaActual.get(Calendar.YEAR);
        int dia = fecha % 100;
        int mes = (fecha % 10000) / 100;
        int anio = fecha / 10000;
        boolean retorno=false;
        if (dia < 32 && mes < 13) {
            if ((anio == anhoactual+1)) {
                retorno= true;
            }
            else if ((anio == anhoactual && mes > mesactual)) {
                retorno= true;
            }
            else if ((anio == anhoactual && mes == mesactual && dia > diaactual)) {
                retorno= true;
            }
        } 
        return retorno;
    }
    public static boolean fechaValidaDevolucion(int entrega, int devolucion) {
        int diae = entrega % 100;
        int mese = (entrega % 10000) / 100;
        int anioe = entrega / 10000;

        int diad = devolucion % 100;
        int mesd = (devolucion % 10000) / 100;
        int aniod = devolucion / 10000;
        boolean retorno=false;
        if (diad < 32 && mesd < 13) {
            if ((aniod == anioe+1)) {
                retorno= true;
            }
            else if ((aniod == anioe && mesd > mese)) {
                retorno= true;
            }
            else if ((aniod == anioe && mesd == mese && diad > diae)) {
                retorno= true;
            }
        }
        return retorno;
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
    public Cliente getCliente(){
        return this.cliente;
    }
    public double getPagoReserva() {
        return this.pagoReserva;
    }
    public static List<Reserva> getListaReservas(){
        return listaReservas;
    }

    // Métodos setter
    public void setID() {
        this.idReserva=lastId+=1;
        lastId=this.getID();    }

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
    public void setPagoReserva(int fecha1, int hora1, int fecha2, int hora2) {
        // Lógica para estimar el pago de alquiler
        Categoria categoria=this.getCategoria();
        int tarifa=categoria.getTarifaDiaria();
        int dias=this.calcularDuracionRenta(fecha1,hora1,fecha2,hora2);
        int precio= dias*tarifa;
        this.pagoReserva= precio;
    }

    public void setVehiculoAsignado() {
        Vehiculo vehiculoAsignado=null;
        boolean esUpgrade=true;
        int categoria=this.getCategoria().getID();
        for(Vehiculo i: Inventario.getListaVehiculos()){
            if(i.getCategoria().getID()==categoria){
                if(i.estaDisponible(this.fechaRecoger,this.horaRecoger ,this.fechaEntregar ,this.horaEntregar )){
                    vehiculoAsignado=i;
                    esUpgrade=false;
                    break;
                }
            }}
        if (esUpgrade){
            int id_padre= this.getCategoria().getId_Padre();
            if (id_padre!=0){
            for(Vehiculo i: Inventario.getListaVehiculos()){
            if(i.getCategoria().getID()==id_padre){
                if(i.estaDisponible(this.fechaRecoger,this.horaRecoger ,this.fechaEntregar ,this.horaEntregar )){
                    vehiculoAsignado=i;
                    break;
                }
            }}}

        }
        
        if ((vehiculoAsignado!=null)&&(esUpgrade==false)){
            vehiculoAsignado.addReservaActiva(this);
            this.vehiculoAsignado=vehiculoAsignado;
        }
        else if((vehiculoAsignado!=null)&&(esUpgrade==true)){
            vehiculoAsignado.addReservaActiva(this);
            this.vehiculoAsignado=vehiculoAsignado;
            System.out.println("\n\t>Accederás a un Upgrade de vehiculo sin costo adicional!\n");

        }
        else{
            System.out.println("\n\t>No se encontraron vehículos disponibles para la categoría dada en el rango de fechas dado.");
        }
    }

    public void setPagoReserva(int pago) {
        pagoReserva = pago;
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

    public int calcularDuracionRenta(int fecha1, int hora1, int fecha2, int hora2) {
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
        long duracionEnDias = fechaT1.until(fechaT2).getDays();
        int valorInt=(int) duracionEnDias;
        return valorInt;
    }
        
    public static void eliminarReserva(Cliente cliente){
        Reserva reservaElejida=encontrarReserva(cliente);
        if(reservaElejida!=null){
        Reserva.getListaReservas().remove(reservaElejida);
        Vehiculo vehiculoReservaElejida= reservaElejida.getVehiculoAsignado();
        vehiculoReservaElejida.getReservasActivas().remove(reservaElejida);
        System.out.println("\n> La reserva ha sido cancelada, pronto se trasferirá de vuelta el pago del 30% ($ "+Double.toString(reservaElejida.getPagoReserva())+").");
    }}
        
    
    public static void modificarReserva(Cliente cliente){
        Reserva reservaElejida=encontrarReserva(cliente);
        if(reservaElejida!=null){
            System.out.println("\nDesea editar la descripción del seguro?\n");
            System.out.println("1.Sí");
            System.out.println("2.No(ó cualquier otro número)");
        }
        }
    public static List<Integer> desplegarReservasActivas(Cliente cliente){
        List<Reserva> reservas= getListaReservas();
        List<Integer> idsReservas= new ArrayList<Integer>();
        if (reservas.size()>0){
        System.out.println("Tiene las siguientes Reservas activas:");
        for (int i = 0; i < reservas.size(); i++) {
            if (reservas.get(i).getCliente().equals(cliente)){
            Reserva i_reserva=reservas.get(i);
            idsReservas.add(i_reserva.getID());
            System.out.println("IDreserva: " + Integer.toString(i_reserva.getID()));
            int f1= i_reserva.getFechaRecoger();
            int f2=i_reserva.getFechaEntregar();
            String categoria=i_reserva.getCategoria().getnombreCategoria();
            String fecha1 = String.format("%02d/%02d/%04d",f1 % 100,(f1/ 100) % 100, f1/ 10000);
            String fecha2 = String.format("%02d/%02d/%04d",f2 % 100,(f2/ 100) % 100, f2/ 10000);
            System.out.println(" Tipo de vehículo reservado: "+ categoria+ ". Fecha Inicio: " + fecha1+"-> Fecha Final: "+ fecha2);
            System.out.println("------------------------------------------------------------------------------------------------------------");
            }}}
            else{ System.out.println("\n>No tiene reservas activas ");}
            return idsReservas;
    }
    public static Reserva encontrarReserva(Cliente cliente){
        Reserva retorno=null;
        List<Integer> idsReservas= desplegarReservasActivas(cliente);
        if (idsReservas.size()>=1){
        boolean continuar=true;
        while (continuar){
        int intReservaElejida= Integer.parseInt(input("Porfavor ingrese el idReserva de la reserva que desea cancelar"));
        if (idsReservas.contains(intReservaElejida)){
            retorno=Reserva.assignReserva(intReservaElejida);
            System.out.println("\n> Reserva encontrada.");

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
    public static String input(String mensaje)
	{
		try
		{
			System.out.print(mensaje + ": ");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			return reader.readLine();
		}
		catch (IOException e)
		{
			System.out.println("Error leyendo de la consola");
			e.printStackTrace();
		}
		return null;
    }
}
    

