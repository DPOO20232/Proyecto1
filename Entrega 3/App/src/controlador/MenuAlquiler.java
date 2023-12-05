package controlador;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import modelo.Categoria;
import modelo.Cliente;
import modelo.Conductor;
import modelo.Inventario;
import modelo.Licencia;
import modelo.Reserva;
import modelo.Sede;
import modelo.Seguro;
import modelo.Tarjeta;
import modelo.Usuario;
import modelo.Vehiculo;
import modelo.alquiler;

public class MenuAlquiler {
    public static void crearReserva(Cliente cliente,boolean reservaEnSede,boolean boolDto){
        if (cliente==null){
            int cedulaCliente = Integer.parseInt(input("Ingrese la cédula del cliente"));
            cliente=Usuario.assignCliente(cedulaCliente);}
            if(cliente!=null){
            System.out.println("\n¡Bienvenido a nuestro sistema de reservas!\n");
            try {
                Licencia licenciaInicial=cliente.getLicencia();
                Tarjeta tarjetaInicial=cliente.getTarjeta(); 
                boolean continuar=true;
                while(continuar){
                System.out.println("\n>Lista de Sedes Disponibles:");
                    List<Sede> sedes = Inventario.getListaSedes();
                    for (int i = 0; i < sedes.size(); i++) {
                        System.out.println((Integer.toString(i + 1) + ". "));
                        sedes.get(i).printInfo() ;}
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
                boolean horaVrecoger = Reserva.horaValida(hrecoger);
                boolean horaVdevolucion = Reserva.horaValida(hentregar);
                boolean fVrecoger = Reserva.fechaValidaReserva(frecoger,hrecoger);
                boolean fVdevolucion = Reserva.fechaValidaDevolucion(frecoger,fentregar,hrecoger,hentregar);
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
                    boolean continuar4=true;
                    while(continuar4){
                    System.out.println("\nLista de Categorías de Vehículos Disponibles:\n");
                    List<Categoria> categorias = Inventario.getListaCategorias();
                    for (int i = 0; i < categorias.size(); i++) {
                        Categoria i_categoria=categorias.get(i);
                        System.out.println((i + 1) + ". " + i_categoria.getnombreCategoria());
                        System.out.println("   - Costo Diario: COP" + i_categoria.getTarifaDiaria());
                        System.out.println("   - Capacidad: " + i_categoria.getCapacidad() + " personas");
                    }
                    int categoriaElegidaIndex = Integer.parseInt(input("Seleccione una categoría (ingrese el número)"));

                    if (categoriaElegidaIndex>=1 && categoriaElegidaIndex<=(categorias.size())){
                        Categoria categoriaElegida = categorias.get(categoriaElegidaIndex - 1);
                        Reserva reserva_u = new Reserva(frecoger, fentregar, hrecoger, hentregar, reservaEnSede, sedeRecoger, sedeEntrega, categoriaElegida, cliente);
                        // AÑADIR SOLO SI ENCONTRAMOS CARRO 
                        reserva_u.setVehiculoAsignado();
                        if (reserva_u.getVehiculoAsignado()!=null){
                            boolean continuar5=true;
                            System.out.println("\n>Encontramos un vehículo para tí!");
                            while(continuar5){
                            int numTarjeta = Integer.parseInt(input("Para debitar el 30% del alquiler de su cuenta, por favor ingrese los últimos 4 dígitos de la tarjeta que tiene registrada"));
                            if (numTarjeta== (cliente.getTarjeta().getNumeroTarjeta())%10000){
                            if (boolDto){System.out.println(">Se aplicó un 10% de descuento sobre la reserva.");}
                            reserva_u.setPagoReserva(frecoger,hrecoger,fentregar ,hentregar,boolDto);
                            System.out.println(">Se debitaron COP "+ Double.toString(reserva_u.getPagoReserva())+".");
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
                        }}else{
                            continuar4=false;
                            continuar3=false;
                            continuar2=false;
                            continuar=false;}
                    }else{System.out.println("\n>Elija una categoría de las opciones mostradas.");}
                }} 
                else{ System.out.println("\n>Su tarjeta caducará/caducó, desea actualizar su método de pago?(En caso de no actualizarla se cancelará el proceso de reserva)");
                    System.out.println("1.Sí");
                    System.out.println("2.No(ó cualquier otro número)");
                    int opcion = Integer.parseInt(input("Por favor seleccione una opción"));
                    if(opcion==1){MenuUsuario.setTarjeta(cliente);}
                    else{continuar3=false;continuar2=false;continuar=false;}
                    }}} else{System.out.println("\n>Su licencia caducará/caducó, desea actualizar su licencia?(En caso de no actualizarla se cancelará el proceso de reserva)");
                    System.out.println("1.Sí");
                    System.out.println("2.No(ó cualquier otro número)");
                    int opcion = Integer.parseInt(input("Por favor seleccione una opción"));
                    if(opcion==1){
                        Cliente.getListaLicencias().remove(cliente.getLicencia());
                        cliente.setLicencia(null);
                        Licencia licencia= MenuUsuario.crearLicencia();
                        cliente.setLicencia(licencia);}
                    else{continuar2=false;
                    continuar=false;}
                }
                }} else {System.out.println(">Las fechas u horas ingresadas no son válidas. Por favor, inténtelo nuevamente.");}
                }
                else{System.out.println(">Elija opciones de sede válidas.\n");}}
                if (cliente.getLicencia()==null){cliente.setLicencia(licenciaInicial);}
                if (cliente.getTarjeta()==null){cliente.setTarjeta(tarjetaInicial);}
                }
            catch (NumberFormatException e) {
                System.out.println(">Debe ingresar los datos requeridos para que la creación de su reserva sea exitosa.");
            }
        }}
    
        public static void modificarReserva(Cliente cliente){
            if (cliente==null){
            int cedulaCliente = Integer.parseInt(input("Ingrese la cédula del cliente"));
            cliente=Usuario.assignCliente(cedulaCliente);}
            if(cliente!=null){
            System.out.println("\n¡Bienvenido a nuestro sistema de reservas!\n");
            Reserva reservaPorModificar=encontrarReserva(cliente);

                if(reservaPorModificar!=null){
                    Licencia licenciaOriginal= cliente.getLicencia();
                    Vehiculo vehiculoActual= reservaPorModificar.getVehiculoAsignado();
                    reservaPorModificar.setVehiculoAsignado(null);
                    vehiculoActual.eliminarReservaActiva(reservaPorModificar.getID());
                    Reserva.getListaReservas().remove(reservaPorModificar);
                    //Se realiza una copia de la reserva en caso de que el usuario no complete la modificación de la reserva
                    Reserva copiaReserva= new Reserva(reservaPorModificar.getID(),reservaPorModificar.getFechaRecoger(),reservaPorModificar.getFechaEntregar(),reservaPorModificar.getHoraRecoger(),reservaPorModificar.getHoraEntregar(),reservaPorModificar.getReservaEnSede(),reservaPorModificar.getSedeRecoger(),reservaPorModificar.getSedeEntregar(),reservaPorModificar.getCategoria(),reservaPorModificar.getCliente());
                    boolean encontroNuevoCarro=false;
                    try{
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
                            System.out.println((Integer.toString(i + 1) + ". "));
                            sedes.get(i).printInfo() ;}
                        int sedeRecogerIndex = Integer.parseInt(input("\nSeleccione una sede para recoger su vehículo(ingrese el número)"));
                        int sedeEntregaIndex = Integer.parseInt(input("Seleccione una sede para la entrega de su vehículo(ingrese el número)"));
                        if(sedeRecogerIndex<=sedes.size()&& sedeEntregaIndex<=sedes.size()){
                        sedeRecoger = sedes.get(sedeRecogerIndex - 1);
                        sedeEntrega = sedes.get(sedeEntregaIndex - 1);
                        reservaPorModificar.setSedeEntregar(sedeEntrega);
                        reservaPorModificar.setSedeRecoger(sedeRecoger);
                        continuar2=false;
                        System.out.println("\n>Sedes actualizadas.\n");
                        }else{
                        System.out.println("\n>No ingresó opciones de sede válidas. Desea intentarlo de nuevo?\n");
                        System.out.println("1.Sí");
                        System.out.println("2.No(ó cualquier otro número)");
                        int opcion_1= Integer.parseInt(input("Porfavor elija una opción"));
                        if(opcion_1>1){continuar2=false;
                        System.out.println("\n>Se mantienen las sedes previas.\n");}}}}
                
                    System.out.println("\nDesea actualizar las fechas de reserva?\n");
                    System.out.println("1.Sí");
                    System.out.println("2.No(ó cualquier otro número)");
                    int opcion2= Integer.parseInt(input("Porfavor elija una opción"));
                    if (opcion2==1){
                    boolean continuar2=true;
                    while(continuar2){
                    System.out.println("\n\t>Información Sede donde se recogerá el vehículo:");
                    sedeRecoger.printInfo();
                    int frecoger= Integer.parseInt(input("\nIngrese la nueva fecha en la que desea recoger el vehículo (formato aaaammdd)"));
                    int hrecoger= Integer.parseInt(input("Ingrese la nueva hora en la que desea recoger el vehículo (formato hhmm)"));
                    System.out.println("\n\t>Información Sede donde se devolverá el vehículo:");
                    sedeEntrega.printInfo();
                    int fentregar = Integer.parseInt(input("Ingrese la nueva fecha en la que desea devolver el vehículo (formato aaaammdd)"));
                    int hentregar= Integer.parseInt(input("Ingrese la nueva hora en la que desea devolver el vehículo (formato hhmm)"));
                    boolean horaVrecoger = Reserva.horaValida(hrecoger);
                    boolean horaVdevolucion = Reserva.horaValida(hentregar);
                    boolean fVrecoger = Reserva.fechaValidaReserva(frecoger,hrecoger);
                    boolean fVdevolucion = Reserva.fechaValidaDevolucion(frecoger,fentregar,hrecoger,hentregar);
                    boolean posibleRecoger=sedeRecoger.estaAbierta(frecoger,hrecoger);
                    boolean posibleEntregar=sedeEntrega.estaAbierta(fentregar,hentregar);
                    if(horaVrecoger&&horaVdevolucion&&fVrecoger&&fVdevolucion&&posibleRecoger&&posibleEntregar){
                        boolean continuar3=true;
                        while (continuar3){
                        if(Usuario.checkVencimientoLicencia((reservaPorModificar.getCliente().getLicencia()), fentregar % 100 , (fentregar / 100) % 100,fentregar / 10000)==false){
                        boolean continuar4=true;
                        while (continuar4){
                        if ((reservaPorModificar.getCliente().getTarjeta()).checkVencimientoTarjeta(fentregar % 100, (fentregar / 100) % 100, fentregar / 10000)==false){
                        reservaPorModificar.setFechaRecoger(frecoger);
                        reservaPorModificar.setHoraRecoger(hrecoger);
                        reservaPorModificar.setFechaEntregar(fentregar);
                        reservaPorModificar.setHoraEntregar(hentregar);
                        System.out.println("\n>Fechas de reserva actualizadas.");
                        continuar4=false;continuar3=false;continuar2=false;
                        }
                        else{
                        System.out.println("\n>Su método de pago no es válido para el rango de fechas dado, desea actulizarlo?\n");
                        System.out.println("1.Sí");
                        System.out.println("2.No(ó cualquier otro número)");
                        int opcion2_3= Integer.parseInt(input("Porfavor elija una opción"));
                        if(opcion2_3==1){MenuUsuario.setTarjeta(reservaPorModificar.getCliente());}
                        else{continuar4=false;continuar3=false;continuar2=false;}}}}
                        else{
                        System.out.println("\n>Su licencia no es válida para el rango de fechas seleccionado, desea actualizar la información de su licencia?\n");
                        System.out.println("1.Sí");
                        System.out.println("2.No(ó cualquier otro número)");
                        int opcion2_3= Integer.parseInt(input("Porfavor elija una opción"));
                        if(opcion2_3==1){
                            Usuario.getListaLicencias().remove(licenciaOriginal); 
                            Licencia licencia=MenuUsuario.crearLicencia();
                            reservaPorModificar.getCliente().setLicencia(licencia);}
                        else{continuar3=false;continuar2=false;
                        System.out.println("\n> Se mantienen las fechas previas.");
                        }}}}
                        else{
                        System.out.println("\n>No ingresó fechas y/o horas válidas. Desea intentarlo de nuevo?");
                        System.out.println("1.Sí");
                        System.out.println("2.No(ó cualquier otro número)");
                        int opcion2_2= Integer.parseInt(input("Porfavor elija una opción"));
                        if(opcion2_2>1){continuar2=false;
                        System.out.println("\n> Se mantienen las fechas previas.");
                        }}}}   
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
                        System.out.println("   - Costo Diario: COP" + i_categoria.getTarifaDiaria());
                        System.out.println("   - Capacidad: " + i_categoria.getCapacidad() + " personas");
                    }
                    int categoriaElegidaIndex = Integer.parseInt(input("Seleccione una categoría (ingrese el número)"));
                    if (categoriaElegidaIndex>=1 && categoriaElegidaIndex<=(categorias.size())){
                        Categoria categoriaElegida = categorias.get(categoriaElegidaIndex - 1);
                        reservaPorModificar.setCategoria(categoriaElegida);
                        reservaPorModificar.setVehiculoAsignado();
                        System.out.println("\n>Categoría actualizada.");
                        if(reservaPorModificar.getVehiculoAsignado()!=null){
                            encontroNuevoCarro=true;
                            continuar2=false;}}
                    else{
                        System.out.println("\n>No seleccionó una categoría válida. Desea intentarlo de nuevo?\n");
                        System.out.println("1.Sí");
                        System.out.println("2.No(ó cualquier otro número)");
                        int opcion3_2= Integer.parseInt(input("Porfavor elija una opción"));
                        if(opcion3_2>1){continuar2=false;
                        System.out.println("\n> Se mantiene la categoría anterior.");
                        reservaPorModificar.setVehiculoAsignado();
                        if(reservaPorModificar.getVehiculoAsignado()!=null){
                        encontroNuevoCarro=true;
                        continuar2=false;
                        }}}}}
                        else{
                        reservaPorModificar.setVehiculoAsignado();
                        if(reservaPorModificar.getVehiculoAsignado()!=null){
                        encontroNuevoCarro=true;
                        }
                }
                if (encontroNuevoCarro==true){
                boolean continuar2=true;
                while(continuar2){
                int numTarjeta = Integer.parseInt(input("Para debitar el 30% del alquiler de su cuenta, por favor ingrese los últimos 4 dígitos de la tarjeta que tiene registrada"));
                if (numTarjeta== (cliente.getTarjeta().getNumeroTarjeta())%10000){
                Reserva.addReserva(reservaPorModificar);
                reservaPorModificar.setPagoReserva(reservaPorModificar.getFechaRecoger(),reservaPorModificar.getHoraRecoger(),reservaPorModificar.getFechaEntregar(),reservaPorModificar.getHoraEntregar(),false);
                double newPago=reservaPorModificar.getPagoReserva();
                double debito= newPago-pagoReservaInicial;
                if(debito>0){
                System.out.println("\n>Considerando el pago de reserva inicial +(COP"+Double.toString(pagoReservaInicial)+"). Se debitarán COP"+Double.toString(debito)+".");
                System.out.println("(Pago 30%: "+Double.toString(newPago)+").");
                }
                else{
                System.out.println("\n>Considerando el pago de reserva inicial +(COP"+Double.toString(pagoReservaInicial)+"). Se le devolverán  COP"+Double.toString(Math.abs(debito))+".");
                System.out.println("(Pago 30%: "+Double.toString(newPago)+").");
                }
                continuar2=false;
                }
                else{
                System.out.println("\n>Los últimos 4 dígitos ingresados no corresponden a los últimos 4 dígitos de su tarjeta, desea intentarlo nuevamente?");
                System.out.println("1.Sí");
                System.out.println("2.No(ó cualquier otro número)");
                int opcion3_3 = Integer.parseInt(input("Por favor seleccione una opción"));
                if(opcion3_3>1){
                    continuar2=false;
                    reservaPorModificar=new Reserva(copiaReserva.getID(),copiaReserva.getFechaRecoger(),copiaReserva.getFechaEntregar(),copiaReserva.getHoraRecoger(),copiaReserva.getHoraEntregar(),copiaReserva.getReservaEnSede(),copiaReserva.getSedeRecoger(),copiaReserva.getSedeEntregar(),copiaReserva.getCategoria(),copiaReserva.getCliente());;
                    Reserva.addReserva(reservaPorModificar);
                    reservaPorModificar.setVehiculoAsignado(vehiculoActual);
                    vehiculoActual.addReservaActiva(reservaPorModificar);
                    System.out.println("\n>No se pudo completar la modificación de la reserva, por lo que los cambios realizados no se reflejarán en la reserva.\n");
        
                }}}}
                else{
                System.out.println("\n>No se encontró un vehículo disponible para las nuevas modificaciones, intente modificar la reserva con modificaciones distintas.\n");
                    reservaPorModificar=new Reserva(copiaReserva.getID(),copiaReserva.getFechaRecoger(),copiaReserva.getFechaEntregar(),copiaReserva.getHoraRecoger(),copiaReserva.getHoraEntregar(),copiaReserva.getReservaEnSede(),copiaReserva.getSedeRecoger(),copiaReserva.getSedeEntregar(),copiaReserva.getCategoria(),copiaReserva.getCliente());;
                    Reserva.addReserva(reservaPorModificar);
                    reservaPorModificar.setVehiculoAsignado(vehiculoActual);
                    vehiculoActual.addReservaActiva(reservaPorModificar);
                }
                }catch(NumberFormatException e){
                }}}
    }

    public static Reserva encontrarReserva(Cliente cliente){
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
        List<Reserva> reservas= Reserva.getListaReservas();
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
    public static void eliminarReserva(Cliente cliente){
        Reserva reservaElejida=encontrarReserva(cliente);
        if(reservaElejida!=null){
        int id= reservaElejida.getID();
        Reserva.getListaReservas().remove(reservaElejida);
        Vehiculo vehiculoReservaElejida= reservaElejida.getVehiculoAsignado();
        vehiculoReservaElejida.eliminarReservaActiva(reservaElejida.getID());
        System.out.println("\n> La reserva con IDreserva "+Integer.toString(id)+" ha sido cancelada, pronto se trasferirá de vuelta el pago del 30% (COP "+Double.toString(reservaElejida.getPagoReserva())+").");
    }}

    public static void crearAlquiler(Sede sedePersonal){
        try{
        System.out.println("Reserva/s activa/s del cliente: ");
        int fechaActual= Integer.parseInt(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")));
        LocalTime hora = LocalTime.now();
        int horaActual = hora.getHour() * 100 + hora.getMinute();
        List<Reserva> reservas= Reserva.getListaReservas();
        int cedulaCliente = Integer.parseInt(input("Ingrese la cédula del cliente"));
        Cliente cliente=Usuario.assignCliente(cedulaCliente);
        if(cliente!=null){
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
            Reserva.addReserva(reserva);//ya
            alquiler alquiler_u = new alquiler(reserva);//ya
            agregarConductores(alquiler_u);//ya?
            agregarSeguros(alquiler_u);//ya
            double pagoInicial=alquiler_u.calcularPagoInicial();//ya
            System.out.println("\n>Se debitaron COP " +Double.toString(pagoInicial) + " de su tarjeta terminada en "+ Long.toString(ultimos_digitos)+"."); 
            System.out.println("(Pago correspondiente al 70% del alquiler + pago por seguros + pago por conductores adicionales)"); 
            System.out.println("\n>En este momento se puede entregar el vehículo al cliente."); 
            vehiculo.addAlquiler(alquiler_u);//ya
            alquiler.addAlquiler(alquiler_u);
            alquiler_u.setPagoFinal(pagoInicial);
            alquiler_u.setActivo(true);
            }
            else{
            System.out.println("\n> Lastimosamente, el vehículo reservado actualmente se encuentra "+estadoActualVehiculo+", y no hay más vehiculos disponibles.");
            System.out.println("> Reserva cancelada, Alquiler cancelado. Prontamente se retornará el pago de la reserva (COP"+Double.toString(pagoReserva)+")." );
            System.out.println("> Si el cliente requiere, se puede planificar otra reserva para el día de hoy con una categoría de vehículo diferente (opción 3 del menú)." ); 
 
            }
            
            }
        
        else {System.out.println("Reserva no encontrada/disponible. Por favor, ingrese un ID válido.");}
        }
        else{
            System.out.println("\n>Ingrese una cédula de cliente válida.");
        }}
        catch(NumberFormatException e){System.out.println("\n>Ingrese los valores solicitados en el formato indicado.");}}

    private static void agregarConductores(alquiler alquiler) {
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
                    Licencia licencia = MenuUsuario.crearLicencia(); 
                    if (licencia != null){
                        Conductor conductor = new Conductor(nombre, cedula, licencia);
                        alquiler.addConductor(conductor);
                        System.out.println(">Conductor registrado");
                    }
                    else{
                        System.out.println(">No se pudo registrar");
                    }
                    
                }
                else if(opcion==2){continuarPersonal1 = false;}
            
        
    }catch (NumberFormatException e){System.out.println("\n>Ingrese los valores requeridos en el formato solicitado");}}}

    private static void agregarSeguros (alquiler alquiler){
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
                    alquiler.addSeguro(seguro);
                    System.out.println("Seguro agregado al alquiler.");
                } catch(NumberFormatException e){System.out.println("ID de seguro no válido. Intente nuevamente.");}
            }
            else if(opcion==2) { continuar = false;}
            else {System.out.println("Por favor seleccione una opción válida.");}
        }
    }
    public static void completarAlquiler(Sede sedePersonal){
        try{
        int cedulaCliente = Integer.parseInt(input("Ingrese la cédula del cliente"));
        Cliente cliente=Usuario.assignCliente(cedulaCliente);
        if (cliente!=null){
        int fechaActual= Integer.parseInt(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")));
        LocalTime hora = LocalTime.now();
        int horaActual = hora.getHour() * 100 + hora.getMinute();
        alquiler alquiler_u=null;
        System.out.println("Alquiler/es activo/s del cliente: ");

        for (alquiler i: alquiler.getListaAlquileres()){
            if (i.getReserva().getCliente().getNumeroCedula()==cliente.getNumeroCedula()&&i.getActivo()==true){
            int id = i.getID();
            String placa = i.getReserva().getVehiculoAsignado().getPlaca();
            int fechaRecoger = i.getReserva().getFechaRecoger();
            int fechaEntregar = i.getReserva().getFechaEntregar();
            System.out.println("ID de la reserva: " + id+". Placa vehículo: "+placa+".");
            System.out.println("    Fecha de recogida: " +fechaRecoger+". Fecha de devolucion: "+fechaEntregar+".\n");
            }}
        int id = Integer.parseInt(input("Por favor ingrese el ID del alquiler que desee completar"));
        alquiler_u = alquiler.assignAlquiler(id);
        if(alquiler_u!=null && alquiler_u.getReserva().getCliente().getNumeroCedula()==cliente.getNumeroCedula() && alquiler_u.getActivo()==true){
        alquiler_u.setActivo(false);
        Reserva reserva= alquiler_u.getReserva();
        double pago70=alquiler_u.getPagoFinal();
        Vehiculo vehiculo=reserva.getVehiculoAsignado();
        reserva.setSedeEntregar(sedePersonal);
        reserva.setFechaEntregar(fechaActual);
        reserva.setHoraEntregar(horaActual);
        System.out.println("\n¿El vehiculo tiene algun tipo de daño?");
        System.out.println("1. Averia leve");
        System.out.println("2. Averia moderada");
        System.out.println("3. Averia total");
        System.out.println("4. No");
        int averia= Integer.parseInt(input("Porfavor seleccione una opción"));
        double newPago=alquiler_u.calcularPagoFinal(sedePersonal,averia);
        alquiler_u.setPagoFinal(newPago+pago70);
        vehiculo.eliminarReservaActiva(id);

        if (newPago>0){
        System.out.println("\n>El vehículo se ha devuelto correctamente y se han debitado COP "+Double.toString(newPago)+" de su tarjeta terminada en "+ Long.toString(cliente.getTarjeta().getNumeroTarjeta()% 10000)+".");
        }
        //TODO check hay pagos negativos?
        else{
        System.out.println("\n>El vehículo se ha devuelto correctamente y el cliente tiene un saldo a favor de COP "+Double.toString(Math.abs(newPago))+" que se transferirán a su tarjeta terminada en "+ Long.toString(cliente.getTarjeta().getNumeroTarjeta()% 10000)+".");
        }}else{System.out.println("\n>Ingrese un número de cédula de cliente válido");}
        }
        else{
        System.out.println("Alquiler no encontrada/disponible. Por favor, ingrese un ID válido.");}           
    }catch(NumberFormatException e){System.out.println("\n>Ingrese los valores solicitados en el formato indicado");}}

        
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
