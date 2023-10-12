package alquiler;
import java.util.ArrayList;
import java.util.List;
import inventario.Categoria;
import inventario.Vehiculo;
import usuario.Cliente;
import inventario.Sede;
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

    //reservaEnSede es true cuando la hace el personal de atencion

    public static void crearReserva(boolean reservaEnSede){
        try { 
            System.out.println("\n¡Bienvenido a nuestro sistema de reservas!\n");
            int frecoger = Integer.parseInt(input("Por favor ingrese la fecha en la que desee recoger su vehículo(en formato ddmmaaaa o dmmaaaa)"));
            int fentregar = Integer.parseInt(input("Por favor ingrese la fecha en la que desee entregar su vehículo(en formato ddmmaaaa o dmmaaaa)"));
            int hrecoger = Integer.parseInt(input("Por favor ingrese la hora en la que desee recoger su vehículo(en formato hhmm)"));
            int hentregar = Integer.parseInt(input("Por favor ingrese la hora en la que desee entregar su vehículo(en formato hhmm)"));
            System.out.println("Lista de Sedes Disponibles:");
            List<Sede> sedes = Inventario.getListaSedes();
            for (int i = 0; i < sedes.size(); i++) {
                System.out.println((i + 1) + ". " + sedes.get(i).getNombre());
            }
            int sedeRecogerIndex = Integer.parseInt(input("Seleccione una sede para recoger su vehículo(ingrese el número)"));
            Sede sedeRecoger = sedes.get(sedeRecogerIndex - 1);
            int sedeEntregaIndex = Integer.parseInt(input("Seleccione una sede para la entrega de su vehículo(ingrese el número)"));
            Sede sedeEntrega = sedes.get(sedeEntregaIndex - 1);

            System.out.println("\nLista de Categorías de Vehículos Disponibles:");
            List<Categoria> categorias = Inventario.getListaCategorias();
            for (int i = 0; i < categorias.size(); i++) {
                System.out.println((i + 1) + ". " + categorias.get(i).getnombreCategoria());
                System.out.println("   - Costo Diario: $" + categoria.getTarifaDiaria());
                System.out.println("   - Capacidad: " + categoria.getCapacidad() + " personas");
            }
            int categoriaElegidaIndex = Integer.parseInt(input("Seleccione una categoría (ingrese el número): "));
            Categoria categoriaElegida = categorias.get(categoriaElegidaIndex - 1);
            //Clientee
            boolean horaVrecoger = horaValida(hrecoger)
            boolean horaVentrega = horaValida(hentregar)
            boolean freserva = fechaValidaReserva(frecoger)
            boolean fdevolucion = fechaValidaReserva(fentregar)
            //if (){}


            

        } catch (NumberFormatException e) {
            System.out.println("Debe ingresar los datos requeridos para que la creación de su reserva sea exitosa.");
        }

    }

    public boolean horaValida(int hora) {
        int horas = hora / 100; 
        int minutos = hora % 100;
        if (horas >= 0 && horas <= 23 && minutos >= 0 && minutos <= 59) {
            return true;
        } else {
            return false;
        }
    }

    public boolean fechaValidaReserva(int fecha) {
        Calendar fechaActual = Calendar.getInstance();
        int diaactual = fechaActual.get(Calendar.DAY_OF_MONTH);
        int mesactual = fechaActual.get(Calendar.MONTH) + 1;
        int anhoactual = fechaActual.get(Calendar.YEAR);

        int dia = fecha / 1000000; 
        int diames = fecha / 10000;
        int mes = diames % 100;
        int anio = fecha % 10000;
        if (dia < 32 && mes < 13) {
            if ((anio == anhoactual+1)) {
                return true;
            }
            if ((anio == anhoactual && mes > mesactual)) {
                return true;
            }
            if ((anio == anhoactual && mes == mesactual && dia > diaactual)) {
                return true;
            }
        } else {
            return false;
        }
    }
    public boolean fechaValidaDevolucion(int entrega, int devolucion) {
        int diae = entrega / 1000000; 
        int diamese = entrega / 10000;
        int mese = diamese % 100;
        int anioe = entrega % 10000;

        int diad = devolucion / 1000000; 
        int diamesd = devolucion / 10000;
        int mesd = diamesd % 100;
        int aniod = devolucion % 10000;
        if (diad < 32 && mesd < 13) {
            if ((aniod == anioe+1)) {
                return true;
            }
            if ((aniod == anioe && mesd > mese)) {
                return true;
            }
            if ((aniod == anioe && mesd == mese && diad > diae)) {
                return true;
            }
        } else {
            return false;
        }
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

    public void setVehiculoAsignado(int IDcategoria) {
        //TODO;
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
    

