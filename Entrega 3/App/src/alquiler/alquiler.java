package alquiler;
import java.util.ArrayList;
import inventario.Seguro;
import inventario.Vehiculo;
import usuario.Conductor;
import usuario.Licencia;
import usuario.Cliente;

public class alquiler{
    private int idAlquiler;
    private double pagoFinal;
    private Reserva reserva;
    private ArrayList<Conductor> conductores;
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
    public double calcularTarifaFinal(){
        return 0.1;
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
    public String crearReciboAlquiler(){
        return "Recibo creado correctamente";
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

    public agregarConductores(Cliente cliente){
        conductores= new ArrayList<Conductor>();
        String nombre1 = cliente.getNombre();
        int cedula1 = cliente.getNumeroCedula();
        Licencia licencia1 = cliente.getLicencia();
        Conductor conductor1 = new Conductor(nombre1, cedula1, licencia1)
        conductores.add(conductor1);
        System.out.println("Conductor Registrado: " + nombre1);
        System.out.println("Número de Licencia: " + licencia1.getNumeroLicencia());
        continuarPersonal1 = true
        while (continuarPersonal1==true){
            System.out.println("¿Desea agregar un conductor adicional?");
            System.out.println("1. Sí");
            System.out.println("2. No");
            int opcion = Integer.parseInt(input("Por favor seleccione una opción"));
            try {
                if(opcion==1){
                    String nombre = input("Ingrese el nombre del conductor");
                    int cedula = Integer.parseInt(input("Por favor ingrese el número de cédula del conductor"));
                    //Licencia licencia = Cliente.setLicencia(); TODO
                    Conductor conductor = new Conductor(nombre, cedula, licencia)
                    conductores.add(conductor);
                    System.out.println("Conductor Registrado: " + nombre);
                    System.out.println("Número de Licencia: " + licencia);
                }
                else if(opcion==2){continuarPersonal1 = false}
            }
        }

    }

    



    public static crearAlquiler(List<Reserva>reservas){
        if (!reservas.isEmpty()){
            System.out.println("Reserva/s activa/s del cliente: ");
            for(Reserva i: reservas){
                //id,categoria,fechae,horae,sedeentregar,precio
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

            int id = Integer.parseInt(input("Por favor ingrese el ID de la reserva que desee utilizar: "));


            alquiler alquiler = new alquiler(reserva);
    
        }
    }

}
