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
    public void setVehiculoAsignado() {
        Vehiculo vehiculoAsignado=null;
        boolean encontreUpgrade=false;
        boolean esUpgrade=true;
        int categoria=this.getCategoria().getID();
        int categoriaPadre=this.getCategoria().getId_Padre();
        for(Vehiculo i: Inventario.getListaVehiculos()){
            if(i.getCategoria().getID()==categoria && i.getSede().getID()==this.getSedeRecoger().getID()){
                if(i.estaDisponible(this.fechaRecoger,this.horaRecoger ,this.fechaEntregar ,this.horaEntregar )){
                    vehiculoAsignado=i;
                    esUpgrade=false;
                    break;}}
                else if(encontreUpgrade==false && i.getCategoria().getID()==categoriaPadre && i.getSede().getID()==this.getSedeRecoger().getID()){
                    vehiculoAsignado=i;
                    encontreUpgrade=true;
                }
            }
        if ((vehiculoAsignado!=null)&&(esUpgrade==false)){
            vehiculoAsignado.addReservaActiva(this);
            this.vehiculoAsignado=vehiculoAsignado;}
        else if((vehiculoAsignado!=null)&&(esUpgrade==true)){
            vehiculoAsignado.addReservaActiva(this);
            this.vehiculoAsignado=vehiculoAsignado;
            System.out.println("\n\t>Accederás a un Upgrade de vehiculo sin costo adicional!\n");}
        else{System.out.println("\n\t>No se encontraron vehículos disponibles para la categoría dada en el rango de fechas dado.");}
    }
    public void setPagoReserva(int pago) {
        pagoReserva = pago;
    }
        public void setPagoReserva(int fecha1, int hora1, int fecha2, int hora2) {
        Categoria categoria=this.getCategoria();
        int tarifa=categoria.getTarifaDiaria();
        int dias=this.calcularDuracionRenta(fecha1,hora1,fecha2,hora2);
        double precio= dias*tarifa*0.3;
        this.pagoReserva= precio;
    }

    public static void crearReserva(Cliente cliente, boolean reservaEnSede){
        //reservaEnSede es true cuando la hace el personal de atencion
        System.out.println("\n¡Bienvenido a nuestro sistema de reservas!\n");
        try { 
            boolean continuar=true;
            while(continuar){
            System.out.println("\n>Lista de Sedes Disponibles:");
                List<Sede> sedes = Inventario.getListaSedes();
                for (int i = 0; i < sedes.size(); i++) {
                    System.out.println((i + 1) + ". " + sedes.get(i).getNombre()+" ("+sedes.get(i).getUbicacion()+").");
                }
            Sede sedeRecoger=null;
            Sede sedeEntrega=null;

            int sedeRecogerIndex = Integer.parseInt(input("\nSeleccione una sede para recoger el vehículo(ingrese el número)"));
            int sedeEntregaIndex = Integer.parseInt(input("Seleccione una sede para la devolución del vehículo(ingrese el número)"));
            if(sedeRecogerIndex<=sedes.size()&& sedeEntregaIndex<=sedes.size()){
            sedeRecoger = sedes.get(sedeRecogerIndex - 1);
            sedeEntrega = sedes.get(sedeEntregaIndex - 1);
            }
            if(sedeRecoger!=null&&sedeEntrega!=null){
            System.out.println("\n\t>Información Sede donde se recogerá el vehículo:");
            sedeRecoger.printInfo();
            int frecoger = Integer.parseInt(input("Por favor ingrese la fecha en la que desee recoger su vehículo(en formato aaaammdd)"));
            int hrecoger = Integer.parseInt(input("Considerando los horarios de atención de la sede, ingrese la hora en la que desee recoger su vehículo(en formato 24h de tipo hhmm)"));
            System.out.println("\n\t>Información Sede donde se devolverá el vehículo:");
            sedeEntrega.printInfo();
            int fentregar = Integer.parseInt(input("Por favor ingrese la fecha en la que desee devolver su vehículo(en formato aaaammdd)"));
            int hentregar = Integer.parseInt(input("Considerando los horarios de atención de la sede, ingrese la hora en la que desee devolver su vehículo(en formato 24h de tipo hhmm)"));
            boolean horaVrecoger = horaValida(hrecoger);
            boolean horaVdevolucion = horaValida(hentregar);
            boolean fVrecoger = fechaValidaReserva(frecoger,hrecoger);
            boolean fVdevolucion = fechaValidaDevolucion(frecoger,fentregar,hrecoger,hentregar);
            boolean posibleRecoger=sedeRecoger.estaAbierta(frecoger,hrecoger);
            boolean posibleEntregar=sedeEntrega.estaAbierta(fentregar,hentregar);
            if (horaVrecoger && horaVdevolucion && fVrecoger && fVdevolucion &&posibleEntregar&&posibleRecoger ){
                boolean continuar2=true;
                while (continuar2){
                Licencia licencia_act= cliente.getLicencia();
                if(Usuario.checkVencimientoLicencia(licencia_act,fentregar % 100, (fentregar / 100) % 100, fentregar / 10000)==false){
                boolean continuar3=true;
                while(continuar3){
                Tarjeta tarjeta_act= cliente.getTarjeta();
                if (tarjeta_act.checkVencimientoTarjeta( fentregar % 100, (fentregar / 100) % 100,fentregar / 10000)==false){
                System.out.println("\nLista de Categorías de Vehículos Disponibles:\n");
                List<Categoria> categorias = Inventario.getListaCategorias();
                for (int i = 0; i < categorias.size(); i++) {
                    Categoria i_categoria=categorias.get(i);
                    System.out.println((i + 1) + ". " + i_categoria.getnombreCategoria());
                    System.out.println("   - Costo Diario: $" + i_categoria.getTarifaDiaria());
                    System.out.println("   - Capacidad: " + i_categoria.getCapacidad() + " personas");
                }
                int categoriaElegidaIndex = Integer.parseInt(input("Seleccione una categoría (ingrese el número): "));
                boolean continuar4=true;
                while(continuar4){
                if (categoriaElegidaIndex>=1 && categoriaElegidaIndex<=(categorias.size())){
                    Categoria categoriaElegida = categorias.get(categoriaElegidaIndex - 1);
                    Reserva reserva_u = new Reserva(frecoger, fentregar, hrecoger, hentregar, reservaEnSede, sedeRecoger, sedeEntrega, categoriaElegida, cliente);
                    // AÑADIR SOLO SI ENCONTRAMOS CARRO 
                    reserva_u.setVehiculoAsignado();
                    if (reserva_u.getVehiculoAsignado()!=null){
                        boolean continuar5=true;
                        System.out.println("> Encontramos un vehículo para tí!");
                        while(continuar5){
                        int numTarjeta = Integer.parseInt(input("Para debitar el 30% del alquiler de su cuenta, por favor ingrese los últimos 4 dígitos de la tarjeta que tiene registrada"));
                        if (numTarjeta== (cliente.getTarjeta().getNumeroTarjeta())%10000){
                        addReserva(reserva_u);
                        reserva_u.setPagoReserva(frecoger,hrecoger,fentregar ,hentregar );
                        System.out.println(">Se debitaron $"+ Double.toString(reserva_u.getPagoReserva())+".");
                        System.out.println(">Reserva creada exitosamente, el id de su reserva es: "+Integer.toString(reserva_u.getID()));
                                continuar5=false;
                                continuar4=false;
                                continuar3=false;
                                continuar2=false;
                                continuar=false;
                                Reserva.addReserva(reserva_u);
                        }
                        else{System.out.println("\n>Los últimos 4 dígitos ingresados no corresponden a los últimos 4 dígitos de su tarjeta, desea intentarlo nuevamente?(En caso de no reintentar se cancelará el proceso de reserva)");
                            System.out.println("1.Sí");
                            System.out.println("2.No(ó cualquier otro número)");
                            int opcion = Integer.parseInt(input("Por favor seleccione una opción"));
                            if(opcion>1){continuar2=false;
                                continuar=false;}}
                    }}else{System.out.println(">No se encontraron vehículos disponibles para la categoría dada en el rango de fechas requerido.");
                        continuar4=false;
                        continuar3=false;
                        continuar2=false;
                        continuar=false;}
                }else{System.out.println(">Elija una categoría de las opciones mostradas.");}
            }} 
            else{ System.out.println("\n>Su tarjeta caducará/caducó, desea actualizar su método de pago?(En caso de no actualizarla se cancelará el proceso de reserva)");
                System.out.println("1.Sí");
                System.out.println("2.No(ó cualquier otro número)");
                int opcion = Integer.parseInt(input("Por favor seleccione una opción"));
                if(opcion==1){cliente.setTarjeta();}
                else{continuar3=false;continuar2=false;continuar=false;}
                }}} else{System.out.println("\n>Su licencia caducará/caducó, desea actualizar su licencia?(En caso de no actualizarla se cancelará el proceso de reserva)");
                System.out.println("1.Sí");
                System.out.println("2.No(ó cualquier otro número)");
                int opcion = Integer.parseInt(input("Por favor seleccione una opción"));
                if(opcion==1){cliente.setLicencia();}
                else{continuar2=false;
                continuar=false;}
            }
            }} else {System.out.println(">Las fechas u horas ingresadas no son válidas. Por favor, inténtelo nuevamente.");}
            }
            else{System.out.println(">Elija opciones de sede válidas.\n");}}}
        catch (NumberFormatException e) {
            System.out.println(">Debe ingresar los datos requeridos para que la creación de su reserva sea exitosa.");
        }

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
    public static void eliminarReserva(Cliente cliente){
        Reserva reservaElejida=encontrarReserva(cliente);
        if(reservaElejida!=null){
        Reserva.getListaReservas().remove(reservaElejida);
        Vehiculo vehiculoReservaElejida= reservaElejida.getVehiculoAsignado();
        vehiculoReservaElejida.eliminarReservaActiva(reservaElejida.getID());
        System.out.println("\n> La reserva ha sido cancelada, pronto se trasferirá de vuelta el pago del 30% ($ "+Double.toString(reservaElejida.getPagoReserva())+").");
    }}
        
    
    public static void modificarReserva(Cliente cliente){
        Reserva reservaPorModificar=encontrarReserva(cliente);
        Reserva copiaReserva= new Reserva(reservaPorModificar.getID(),reservaPorModificar.getFechaRecoger(),reservaPorModificar.getFechaEntregar(),reservaPorModificar.getHoraRecoger(),reservaPorModificar.getHoraEntregar(),reservaPorModificar.getReservaEnSede(),reservaPorModificar.getSedeRecoger(),reservaPorModificar.getSedeEntregar(),reservaPorModificar.getCategoria(),reservaPorModificar.getCliente());
        boolean encontroNuevoCarro=false;
        try{
        if(reservaPorModificar!=null){
            boolean continuar=true;
            while(continuar){
            Sede sedeRecoger=reservaPorModificar.getSedeRecoger();
            Sede sedeEntrega=reservaPorModificar.getSedeEntregar();
            double pagoReservaInicial=reservaPorModificar.getPagoReserva();
            System.out.println("\nDesea cambiar las sedes de recogida y/o entrega del vehículo? \n");
            System.out.println("1.Sí");
            System.out.println("2.No(ó cualquier otro número)");
            int opcion= Integer.parseInt(input("Porfavor elija una opción"));
            if (opcion==1){
            boolean continuar2=true;
            while (continuar2){
                System.out.println(">Lista de Sedes Disponibles:");
                List<Sede> sedes = Inventario.getListaSedes();
                for (int i = 0; i < sedes.size(); i++) {
                    System.out.println((i + 1) + ". " + sedes.get(i).getNombre()+" ("+sedes.get(i).getUbicacion()+").");}
                int sedeRecogerIndex = Integer.parseInt(input("\nSeleccione una sede para recoger su vehículo(ingrese el número)"));
                int sedeEntregaIndex = Integer.parseInt(input("Seleccione una sede para la entrega de su vehículo(ingrese el número)"));
                if(sedeRecogerIndex<=sedes.size()&& sedeEntregaIndex<=sedes.size()){
                sedeRecoger = sedes.get(sedeRecogerIndex - 1);
                sedeEntrega = sedes.get(sedeEntregaIndex - 1);
                System.out.println("\n>Sedes actualizadas.");
                }else{
                System.out.println("\n>No ingresó opciones de sede válidas. Desea intentarlo de nuevo?\n");
                System.out.println("1.Sí");
                System.out.println("2.No(ó cualquier otro número)");
                int opcion_1= Integer.parseInt(input("Porfavor elija una opción"));
                if(opcion_1>1){continuar2=false;}
                }
            }}
            System.out.println("\nDesea actualizar las fechas de reserva?\n");
            System.out.println("1.Sí");
            System.out.println("2.No(ó cualquier otro número)");
            int opcion2= Integer.parseInt(input("Porfavor elija una opción"));
            if (opcion2==1){
            boolean continuar2=true;
            while(continuar2){
            System.out.println("\n\t>Información Sede donde se recogerá el vehículo:");
            sedeRecoger.printInfo();
            System.out.println("\n\t>Información Sede donde se devolverá el vehículo:");
            sedeEntrega.printInfo();
            int frecoger= Integer.parseInt(input("\nIngrese la nueva fecha en la que desea recoger el vehículo (formato aaaammdd)"));
            int fentregar = Integer.parseInt("Ingrese la nueva fecha en la que desea devolver el vehículo (formato aaaammdd)");
            int hrecoger= Integer.parseInt("Ingrese la nueva hora en la que desea recoger el vehículo (formato hhmm)");
            int hentregar= Integer.parseInt("Ingrese la nueva hora en la que desea devolver el vehículo (formato hhmm)");
            boolean horaVrecoger = horaValida(hrecoger);
            boolean horaVdevolucion = horaValida(hentregar);
            boolean fVrecoger = fechaValidaReserva(frecoger,hrecoger);
            boolean fVdevolucion = fechaValidaDevolucion(frecoger,fentregar,hrecoger,hentregar);;
            boolean posibleRecoger=sedeRecoger.estaAbierta(frecoger,hrecoger);
            boolean posibleEntregar=sedeEntrega.estaAbierta(fentregar,hentregar);
            if(horaVrecoger&&horaVdevolucion&&fVrecoger&&fVdevolucion&&posibleRecoger&&posibleEntregar){
                boolean continuar3=true;
                while (continuar3){
                if(Usuario.checkVencimientoLicencia((reservaPorModificar.getCliente().getLicencia()), fentregar / 10000, (fentregar / 100) % 100, fentregar % 100)==false){
                boolean continuar4=true;
                while (continuar4){
                if ((reservaPorModificar.getCliente().getTarjeta()).checkVencimientoTarjeta(fentregar / 10000, (fentregar / 100) % 100, fentregar % 100)==false){
                reservaPorModificar.setFechaRecoger(frecoger);
                reservaPorModificar.setHoraRecoger(hrecoger);
                reservaPorModificar.setFechaEntregar(fentregar);
                reservaPorModificar.setHoraEntregar(hentregar);
                System.out.println("\n>Fechas de reserva actualizadas.");
                }
                else{
                System.out.println("\n>Su método de pago no es válido para el rango de fechas dado, desea actulizarlo?\n");
                System.out.println("1.Sí");
                System.out.println("2.No(ó cualquier otro número)");
                int opcion2_3= Integer.parseInt(input("Porfavor elija una opción"));
                if(opcion2_3==1){reservaPorModificar.getCliente().setTarjeta();}
                else{continuar4=false;continuar3=false;continuar2=false;}                    
                }
                
                }}
                else{
                System.out.println("\n>Su licencia no es válida para el rango de fechas seleccionado, desea actualizar la información de su licencia?\n");
                System.out.println("1.Sí");
                System.out.println("2.No(ó cualquier otro número)");
                int opcion2_3= Integer.parseInt(input("Porfavor elija una opción"));
                if(opcion2_3==1){reservaPorModificar.getCliente().setLicencia();}
                else{continuar3=false;continuar2=false;}
                }
                }

            }else{
                System.out.println("\n>No ingresó fechas y/o horas válidas. Desea intentarlo de nuevo?\n");
                System.out.println("1.Sí");
                System.out.println("2.No(ó cualquier otro número)");
                int opcion2_2= Integer.parseInt(input("Porfavor elija una opción"));
                if(opcion2_2>1){continuar2=false;}
                }
            }           
            }
            System.out.println("\nDesea actualizar el tipo de vehículo?\n");
            System.out.println("1.Sí");
            System.out.println("2.No(ó cualquier otro número)");
            int opcion3= Integer.parseInt(input("Porfavor elija una opción"));
            if (opcion3==1){        
            boolean continuar2=true;
            while(continuar2){
            System.out.println("\nLista de Categorías de Vehículos Disponibles:\n");
            List<Categoria> categorias = Inventario.getListaCategorias();
            for (int i = 0; i < categorias.size(); i++) {
                Categoria i_categoria=categorias.get(i);
                System.out.println((i + 1) + ". " + i_categoria.getnombreCategoria());
                System.out.println("   - Costo Diario: $" + i_categoria.getTarifaDiaria());
                System.out.println("   - Capacidad: " + i_categoria.getCapacidad() + " personas");
            }
            int categoriaElegidaIndex = Integer.parseInt(input("Seleccione una categoría (ingrese el número): "));
            if (categoriaElegidaIndex>=1 && categoriaElegidaIndex<=(categorias.size())){
                Categoria categoriaElegida = categorias.get(categoriaElegidaIndex - 1);
                reservaPorModificar.setCategoria(categoriaElegida);
                reservaPorModificar.setVehiculoAsignado();
                System.out.println("\n>Categoría actualizada.");
                if(reservaPorModificar.getVehiculoAsignado()!=null){
                    encontroNuevoCarro=true;
                }

            }
            else{
                System.out.println("\n>No seleccionó una categoría válida. Desea intentarlo de nuevo?\n");
                System.out.println("1.Sí");
                System.out.println("2.No(ó cualquier otro número)");
                int opcion3_2= Integer.parseInt(input("Porfavor elija una opción"));
                if(opcion3_2>1){continuar2=false;}
            }

        }
        }
        if (encontroNuevoCarro==true){
        boolean continuar2=true;
        while(continuar2){
        int numTarjeta = Integer.parseInt(input("Para debitar el 30% del alquiler de su cuenta, por favor ingrese los últimos 4 dígitos de la tarjeta que tiene registrada"));
        if (numTarjeta== (cliente.getTarjeta().getNumeroTarjeta())%10000){
        reservaPorModificar.setPagoReserva(reservaPorModificar.getFechaRecoger(),reservaPorModificar.getHoraRecoger(),reservaPorModificar.getFechaEntregar(),reservaPorModificar.getHoraEntregar());
        double newPago=reservaPorModificar.getPagoReserva();
        double debito= newPago-pagoReservaInicial;
        if(debito>0){
        System.out.println(">Considerando el pago de reserva inicial +($"+Double.toString(pagoReservaInicial)+"). Se debitarán $"+Double.toString(debito)+".");
        }
        else{
        System.out.println(">Considerando el pago de reserva inicial +($"+Double.toString(pagoReservaInicial)+"). Se le devolverán  $"+Double.toString(-debito)+".");
        }
        }
        else{
        System.out.println("\n>Los últimos 4 dígitos ingresados no corresponden a los últimos 4 dígitos de su tarjeta, desea intentarlo nuevamente?");
        System.out.println("1.Sí");
        System.out.println("2.No(ó cualquier otro número)");
        int opcion3_3 = Integer.parseInt(input("Por favor seleccione una opción"));
        if(opcion3_3>1){
            continuar2=false;
            reservaPorModificar=new Reserva(copiaReserva.getID(),copiaReserva.getFechaRecoger(),copiaReserva.getFechaEntregar(),copiaReserva.getHoraRecoger(),copiaReserva.getHoraEntregar(),copiaReserva.getReservaEnSede(),copiaReserva.getSedeRecoger(),copiaReserva.getSedeEntregar(),copiaReserva.getCategoria(),copiaReserva.getCliente());;
        System.out.println("\n>No se pudo completar la modificación de la reserva, por lo que los cambios realizados no se reflejarán en la reserva.\n");
        }
        }
        }}
        else{
        System.out.println("\n>No se encontró un vehículo disponible para las nuevas modificaciones, intente modificar la reserva con modificaciones disntintas.\n");
        reservaPorModificar=new Reserva(copiaReserva.getID(),copiaReserva.getFechaRecoger(),copiaReserva.getFechaEntregar(),copiaReserva.getHoraRecoger(),copiaReserva.getHoraEntregar(),copiaReserva.getReservaEnSede(),copiaReserva.getSedeRecoger(),copiaReserva.getSedeEntregar(),copiaReserva.getCategoria(),copiaReserva.getCliente());;
        }

            
        }}}catch(NumberFormatException e){System.out.println("\n>Debe ingresar los datos en el formato solicitado.\n");
}
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
        if(hora2>hora1){
            valorInt+=1;
        }
        return valorInt;
    }

    public static String input(String mensaje)
	{
		try{System.out.print(mensaje + ": ");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			return reader.readLine();}
		catch (IOException e){
			System.out.println("Error leyendo de la consola");
			e.printStackTrace();}
		return null;
    }
}
    

