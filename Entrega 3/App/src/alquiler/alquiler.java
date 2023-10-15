package alquiler;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import inventario.Inventario;
import inventario.Seguro;
import usuario.Conductor;
import usuario.Licencia;

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

    public Double setPagoAlquiler(){
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

        Double costo_T=costo70+costo_conductores+costo_seguros;
        return costo_T;
    }



    public static void crearAlquiler(List<Reserva>reservas){
        System.out.println("Reserva/s activa/s del cliente: ");
        for(Reserva i: reservas){
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
        Reserva reserva = Reserva.assignReserva(id);

        if (reserva != null) {
            alquiler alquiler = new alquiler(reserva);
            alquiler.agregarConductores();
            alquiler.agregarSeguros();
            System.out.println("El valor a pagar es de " + alquiler.setPagoAlquiler()); 
            addAlquiler(alquiler);
                         
            }
        
        else {System.out.println("Reserva no encontrada. Por favor, ingrese un ID válido.");}
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
