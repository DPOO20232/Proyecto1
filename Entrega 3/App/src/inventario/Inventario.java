package inventario;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import alquiler.Alquiler;
import alquiler.PagoExcedente;
import alquiler.Reserva;
import usuario.personal;
import usuario.Cliente;
import usuario.Conductor;
import usuario.Usuario;
import usuario.Admin;
import usuario.Licencia;
import usuario.Tarjeta;
public class Inventario {
    private static String nombreCompania;
    private static int costoPorConductorAdicional;
    private static int costoPorTrasladoSedes;
    private static List<Integer>  periodoTemporadaBaja;
    private static List<Integer>  periodoTemporadaAlta;
    private static List<Categoria> listaCategorias;
    private static List<Sede> listaSedes;
    private static List<Seguro> listaSeguros;
    private static List<Evento> listaEventos;
    private static List<Vehiculo> listaVehiculos;
    //Los usuarios guardarlos en una lista de usuario en clase Usuario
    public static void loadSistema(){
    loadInfo();
    loadCategorias();
    loadSedes();
    loadPersonal();
    loadSeguros(); 
    loadLicencias();
    loadClientes();
    loadEventos();
    loadReservas();
    loadAlquileres();
    loadVehiculos();
    }
    public static String getNombreCompania(){
        return nombreCompania;}
    public static int getCostoPorConductorAdicional(){
        return costoPorConductorAdicional;}
    public static int getCostoPorTrasladoSedes(){
        return costoPorTrasladoSedes;}
    public static List<Integer> getPeriodoTemporadaBaja(){
        return periodoTemporadaBaja;}
    public static List<Integer> getPeriodoTemporadaAlta(){
        return periodoTemporadaAlta;}
    public static List<Categoria> getListaCategorias(){
        return listaCategorias;}
    public static List<Seguro> getListaSeguros(){
        return listaSeguros;}
    public static List<Sede> getListaSedes(){
        return listaSedes;}
    public static List<Evento> getListaEventos(){
        return listaEventos;}
    public static List<Vehiculo> getListaVehiculos(){
        return listaVehiculos;}
    public static void closeSistema(){
    //updateCategorias();
    //updateSedes();
    //updateSeguros();
    //updateVehiculos();
    }
    private static void loadInfo(){
        try (BufferedReader br = new BufferedReader(new FileReader("./data/info.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                 if (partes.length == 2){
                    if (partes[0].equals("nombreCompania")){
                        nombreCompania=partes[1];
                    }
                    else if (partes[0].equals("costoPorConductorAdicional")){
                        costoPorConductorAdicional=Integer.parseInt(partes[1]);
                    }
                    else if(partes[0].equals("costoPorTrasladoSedes")){
                        costoPorTrasladoSedes=Integer.parseInt(partes[1]);
                    }
                    else if(partes[0].equals("periodoTemporadaBaja")){
                        periodoTemporadaBaja = new ArrayList<Integer>();
                        String subString= partes[1].substring(1,partes[1].length()-1);
                        String[] subPartes=subString.split(",");
                        int inicioTemp = Integer.parseInt(subPartes[0]);
                        int finTemp = Integer.parseInt(subPartes[1]);
                        periodoTemporadaBaja.add(inicioTemp);
                        periodoTemporadaBaja.add(finTemp);
                    }
                    else{
                        periodoTemporadaAlta = new ArrayList<Integer>();
                        String subString= partes[1].substring(1,partes[1].length()-1);
                        String[] subPartes=subString.split(",");
                        int inicioTemp = Integer.parseInt(subPartes[0]);
                        int finTemp = Integer.parseInt(subPartes[1]);
                        periodoTemporadaAlta.add(inicioTemp);
                        periodoTemporadaAlta.add(finTemp);   
                    }
                 }
            }
        System.out.println(">>> información inicial cargada.");
        }catch (IOException e) {e.printStackTrace();}

    }
    private static void loadCategorias(){
        try (BufferedReader br = new BufferedReader(new FileReader("./data/categorias.txt"))) {
            listaCategorias= new ArrayList<Categoria>();
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length == 10) {
                    int id = Integer.parseInt(partes[0]);
                    String nombreCategoria=partes[1];
                    int capacidad=Integer.parseInt(partes[2]);
                    double pctg_temporadaAlta=Double.parseDouble(partes[3]);
                    double pctg_temporadaBaja=Double.parseDouble(partes[4]);
                    int costoAveriaLeve=Integer.parseInt(partes[5]);
                    int costoAveriaModerada=Integer.parseInt(partes[6]);
                    int costoAveriaTotal= Integer.parseInt(partes[7]);
                    int tarifaDiaria= Integer.parseInt(partes[8]);
                    int id_Padre= Integer.parseInt(partes[9]);
                    Categoria categoriaActual= new Categoria(id,nombreCategoria,capacidad, pctg_temporadaAlta, pctg_temporadaBaja,costoAveriaLeve, costoAveriaModerada, costoAveriaTotal, tarifaDiaria,id_Padre);
                    if (id_Padre!=0){categoriaActual.setPadre(Inventario.assignCategoria(id_Padre));}
                    listaCategorias.add(categoriaActual);
                }
            }
            System.out.println(">>> "+listaCategorias.size()+" categorías cargadas.");
        } catch (IOException e) {e.printStackTrace();}
    }
    private static void loadSedes(){
    //1;SedeChapinero;cra7 #70, Bogotá;[0630,1630];[0630,1230];[]
        try (BufferedReader br = new BufferedReader(new FileReader("./data/sedes.txt"))) {
            listaSedes= new ArrayList<Sede>();
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length == 6) {
                    int id = Integer.parseInt(partes[0]);
                    String nombreSede= partes[1];
                    String ubicacionSede= partes[2];
                    List<Integer> horarioSemana= new ArrayList<Integer>();
                    List<Integer> horarioFinSemana= new ArrayList<Integer>();
                    String stringHorarioSemana=partes[3].substring(1, partes[3].length() - 1);
                    String[] horarios1= stringHorarioSemana.split(",");
                    String stringHorarioFinSemana=partes[4].substring(1, partes[4].length() - 1);;
                    String[] horarios2= stringHorarioFinSemana.split(",");
                    for(String i: horarios1){
                        int i_to_int=Integer.parseInt(i);
                        horarioSemana.add(i_to_int);
                    }
                    for(String i: horarios2){
                        int i_to_int=Integer.parseInt(i);
                        horarioFinSemana.add(i_to_int);
                    }
                    Sede sedeActual= new Sede(id, nombreSede, ubicacionSede,horarioSemana,horarioFinSemana);
                    listaSedes.add(sedeActual);
                } else {
                    System.out.println("Formato incorrecto en la línea: " + linea);
                }
            }
            System.out.println(">>> "+listaSedes.size()+" sedes cargadas.");

        } catch (IOException e) {e.printStackTrace();}
    }
    private static void loadPersonal(){
        try (BufferedReader br = new BufferedReader(new FileReader("./data/personal.txt"))) {
            int contador=0;
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length == 4) {
                    String login=partes[0];
                    String password=partes[1];
                    int id_sede= Integer.parseInt(partes[2]);
                    Sede sede=null;
                    String tipoUsuario=partes[3];
                    
                    if (id_sede>0){
                        sede= Inventario.assignSede(id_sede);
                        personal personalActual= new personal(login, password, tipoUsuario, sede);
                        sede.addPersonalSede(personalActual);
                        personal.addCredencialesPersonal(personalActual);
                        contador+=1;
                        }
                    else{
                        Admin adminActual=new Admin(login, password);
                        personal.setAdmin(adminActual);
                        contador+=1;
                    }
                    
                }
            }
            System.out.println(">>> "+Integer.toString(contador)+" perfiles de personal cargados.");
            } catch (IOException e) {e.printStackTrace();}
    }
    private static void loadSeguros(){
        try (BufferedReader br = new BufferedReader(new FileReader("./data/seguros.txt"))) {
            listaSeguros= new ArrayList<Seguro>();
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length == 3) {
                    int id = Integer.parseInt(partes[0]);
                    double pctg_tarifadiaria = Double.parseDouble(partes[1]);
                    String descripcion=partes[2];
                    Seguro seguroActual= new Seguro(id,pctg_tarifadiaria,descripcion);
                    listaSeguros.add(seguroActual);
                } else {
                    System.out.println("Formato incorrecto en la línea: " + linea);
                }
            }
        System.out.println(">>> "+listaSeguros.size()+" seguros cargados.");
        } catch (IOException e) {e.printStackTrace();}
	}
    private static void loadLicencias() {
        int contador = 0;
        try (BufferedReader br = new BufferedReader(new FileReader("./data/licencias.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length == 4) {
                    int numeroLicencia = Integer.parseInt(partes[0]);
                    int fechaExpedicion = Integer.parseInt(partes[1]);
                    int fechaVencimiento = Integer.parseInt(partes[2]);
                    String paisExpedicion = partes[3];
                    Licencia licenciaActual = new Licencia(numeroLicencia, fechaExpedicion, fechaVencimiento, paisExpedicion);
                    Usuario.addLicencia(licenciaActual);
                    contador += 1;
                } else {
                    System.out.println("Formato incorrecto en la línea: " + linea);
                }
            }
            System.out.println(">>> " + Integer.toString(contador) + " licencias cargadas.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static void loadClientes(){
        int contador=0;
        try (BufferedReader br = new BufferedReader(new FileReader("./data/clientes.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
            if (partes.length == 10) {
            String login= partes[0];
            String password= partes[1];
            int numeroCedula= Integer.parseInt(partes[2]);
            String nombre= partes[3];
            String correo = partes[4];
            long telefono= Long.parseLong(partes[5]);
            int fechaNacimiento= Integer.parseInt(partes[6]);
            String nacionalidad= partes[7];
            Cliente clienteActual= new Cliente(login,password,numeroCedula, nombre, correo, telefono, fechaNacimiento, nacionalidad);
            String partesTarjeta=partes[8].substring(1, partes[8].length() - 1);
            String [] listaTarjeta=partesTarjeta.split(",");
            clienteActual.setTarjeta(new Tarjeta(Long.parseLong(listaTarjeta[0]), Integer.parseInt(listaTarjeta[1]), listaTarjeta[2], listaTarjeta[3]));
            clienteActual.setLicencia(Usuario.assignLicencia(Integer.parseInt(partes[9])));
            Usuario.addCliente(clienteActual);
            contador+=1;
            }else{System.out.println("Formato incorrecto en la línea: " + linea);}
            }  
            System.out.println(">>> " + Integer.toString(contador) + " clientes cargados.");

        }
        catch (IOException e) {e.printStackTrace();}
    }
    private static void loadEventos(){
        listaEventos= new ArrayList<Evento>();
        try (BufferedReader br = new BufferedReader(new FileReader("./data/eventos.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
            if (partes.length == 6) {
            int idEvento= Integer.parseInt(partes[0]);
            int fechaInicio= Integer.parseInt(partes[1]);
            int fechaFin=Integer.parseInt(partes[2]);
            int horaInicio= Integer.parseInt(partes[3]);
            int horaFin= Integer.parseInt(partes[4]);
            String descripcion= partes[5];
            Evento eventoActual= new Evento(idEvento, fechaInicio, fechaFin, horaInicio, horaFin, descripcion);
            listaEventos.add(eventoActual);
            }else{System.out.println("Formato incorrecto en la línea: " + linea);}
            }  
            System.out.println(">>> " + listaEventos.size()+ " eventos cargados.");

        }
        catch (IOException e) {e.printStackTrace();}
    }
    private static void loadReservas(){
        //    public Reserva(int idReserva,int fechaRecoger, int fechaEntregar, int horaRecoger,
        // int horaEntregar, boolean reservaEnSede, Sede sedeRecoger, Sede sedeEntregar,
        //Categoria categoria, Cliente cliente) {
        int contador=0;
        try (BufferedReader br = new BufferedReader(new FileReader("./data/reservas.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
            if (partes.length == 10) {
            int idReserva= Integer.parseInt(partes[0]);
            int fechaRecoger= Integer.parseInt(partes[1]);
            int fechaEntregar=Integer.parseInt(partes[2]);
            int horaRecoger= Integer.parseInt(partes[3]);
            int horaEntregar= Integer.parseInt(partes[4]);
            boolean reservaEnSede= Boolean.parseBoolean(partes[5]);
            int id_sedeRecoger=Integer.parseInt(partes[6]);
            Sede sedeRecoger=Inventario.assignSede(id_sedeRecoger);
            int id_sedeEntregar=Integer.parseInt(partes[7]);
            Sede sedeEntregar= Inventario.assignSede(id_sedeEntregar);
            int id_categoria=Integer.parseInt(partes[8]);
            Categoria categoria= Inventario.assignCategoria(id_categoria);
            int cedula_cliente= Integer.parseInt(partes[9]);
            Cliente cliente= Usuario.assignCliente(cedula_cliente);
            Reserva reservaActual= new Reserva(idReserva, fechaRecoger, fechaEntregar, horaRecoger, horaEntregar, reservaEnSede, sedeRecoger, sedeEntregar, categoria, cliente);
            Reserva.addReserva(reservaActual);
            contador+=1;
            }else{System.out.println("Formato incorrecto en la línea: " + linea);}
            }  
            System.out.println(">>> " +Integer.toString(contador)+ " reservas cargadas.");

        }
        catch (IOException e) {e.printStackTrace();}
    }
    private static void loadAlquileres(){
        int contador=0;
        try (BufferedReader br = new BufferedReader(new FileReader("./data/alquileres.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
            if (partes.length == 6) {
            //int idAlquiler= Integer.parseInt(partes[0]);
            double pagoFinal= Double.parseDouble(partes[1]);
            int id_reserva= Integer.parseInt(partes[2]);
            Reserva reserva= Reserva.assignReserva(id_reserva);
            Alquiler alquilerActual= new Alquiler(reserva);
            alquilerActual.setPagoFinal(pagoFinal);
            String stringConductores= partes[3].substring(1,partes[3].length()-1);
            String [] conductores= stringConductores.split(",");
            if(!stringConductores.equals("")){
            for (String i: conductores){
                String [] i_partes= i.substring(1,i.length()-1).split("-");
                Conductor i_conductor= new Conductor(i_partes[0],Integer.parseInt(i_partes[1]), Usuario.assignLicencia(Integer.parseInt(i_partes[2])));
                alquilerActual.addConductor(i_conductor);
            }}
            String stringIdsSeguros= partes[4].substring(1, partes[4].length()-1);
            String [] IdsSeguros= stringIdsSeguros.split(",");
            if(!stringIdsSeguros.equals("")){
            for(String i: IdsSeguros){
                alquilerActual.addSeguro(Inventario.assignSeguro(Integer.parseInt(i)));
            }}
            String stringPagosExcedentes= partes[5].substring(1, partes[5].length()-1);
            String [] pagosExcedentes= stringPagosExcedentes.split(",");
            if(!stringPagosExcedentes.equals("")){
            for (String i: pagosExcedentes){
                String [] i_partes= i.substring(1,i.length()-1).split("-");
                PagoExcedente i_PagoExcedente= new PagoExcedente();
                i_PagoExcedente.agregarPagoAdicional(i_partes[0],Double.parseDouble(i_partes[1]));
                alquilerActual.addPagoExcedente(i_PagoExcedente);
            }}
            Alquiler.addAlquiler(alquilerActual);
            contador+=1;
            }else{System.out.println("Formato incorrecto en la línea: " + linea);}
            }  
            System.out.println(">>> " + Integer.toString(contador)+ " alquileres cargados.");

        }
        catch (IOException e) {e.printStackTrace();}
    }

    private static void loadVehiculos(){
        int contadorEventos=0;
        int contadorAlquileres=0;
        int contadorReservasActivas=0;
        listaVehiculos= new ArrayList<Vehiculo>();

        try (BufferedReader br = new BufferedReader(new FileReader("./data/vehiculos.txt"))) {
            //PBM158;chevrolet;aveo;gris;manual;disponible;false;1;1;[[20240321,20240322,0800,1200,descripcion],[],[]];[];[]
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length == 12) {
                    String placa=partes[0];
                    String marca=partes[1];
                    String modelo=partes[2];
                    String color=partes[3];
                    String tipo_trasmicion=partes[4];
                    String ubicacionGPS="No disponible :c";
                    String estado= partes[5];
                    boolean averiado= Boolean.parseBoolean(partes[6]);
                    int id_categoria=Integer.parseInt(partes[7]);
                    int id_sede=Integer.parseInt(partes[8]);
                    Categoria categoria=Inventario.assignCategoria(id_categoria);
                    Sede sede= Inventario.assignSede(id_sede);
                    Vehiculo vehiculoActual= new Vehiculo(placa, marca, modelo, color, tipo_trasmicion, ubicacionGPS, estado, averiado,categoria,sede);
                    String stringIDsEventos=partes[9].substring(1, partes[9].length() - 1);
                    String stringIDsReservasActivas=partes[10].substring(1, partes[11].length() - 1);
                    String stringIDsAlquileres=partes[11].substring(1, partes[10].length() - 1);
                    if (!stringIDsEventos.equals("")){
                    String [] listaIDsEventos=stringIDsEventos.split(",");
                    for (String i: listaIDsEventos){
                        vehiculoActual.addEvento(Inventario.assignEvento(Integer.parseInt(i)));
                        contadorEventos+=1;
                        }}
                    if (!stringIDsAlquileres.equals("")){
                    String [] listaIDsAlquileres=stringIDsAlquileres.split(",");
                    for (String i: listaIDsAlquileres){
                        vehiculoActual.addAlquiler(Alquiler.assignAlquiler(Integer.parseInt(i)));
                        contadorAlquileres+=1;
                    }}
                    if (!stringIDsReservasActivas.equals("")){
                    String [] listaIDsReservasActivas=stringIDsReservasActivas.split(",");
                    for (String i: listaIDsReservasActivas){
                        vehiculoActual.addReservaActiva(Reserva.assignReserva(Integer.parseInt(i)));
                        contadorReservasActivas+=1;
                    }}
                    listaVehiculos.add(vehiculoActual);


                } else {
                    System.out.println("Formato incorrecto en la línea: " + linea);
                }
            }
        System.out.println(">>> "+listaVehiculos.size()+" vehículos cargados.("+ Integer.toString(contadorEventos)+" eventos encontrados, "+Integer.toString(contadorAlquileres)+" alquileres registrados, "+Integer.toString(contadorReservasActivas)+" reservas activas.)");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static Categoria assignCategoria(int id_categoria){
        Categoria retorno = null;
        for(Categoria i: Inventario.getListaCategorias()){
            if(i.getID()==id_categoria){
            retorno= i;
            }}
        return retorno;
    }
    private static Seguro assignSeguro(int id_seguro){
        Seguro retorno = null;
        for(Seguro i: Inventario.getListaSeguros()){
            if(i.getID()==id_seguro){
            retorno= i;
            }}
        return retorno;
    } 
    private static Sede assignSede(int id_sede){
        Sede retorno = null;
        for(Sede i: Inventario.getListaSedes()){
            if(i.getID()==id_sede){
            retorno= i;
            break;
            }}
        return retorno;
    } 
    private static Evento assignEvento(int id_evento){
        Evento retorno = null;
        for(Evento i: Inventario.getListaEventos()){
            if(i.getID()==id_evento){
            retorno= i;
            break;
            }}
        return retorno;
    }

    private static Cliente assignCliente(int cedula_cliente){
        Cliente retorno = null;
        for(Cliente i: Usuario.getListaClientes()){
            if(i.getNumeroCedula()==cedula_cliente){
            retorno= i;
            break;
            }}
        return retorno;
    } 

}
