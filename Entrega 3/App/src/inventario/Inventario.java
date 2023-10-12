package inventario;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import inventario.Vehiculo;
import alquiler.alquiler;
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
    public static String getNombreCompania(){return nombreCompania;}
    public static int getCostoPorConductorAdicional(){return costoPorConductorAdicional;}
    public static int getCostoPorTrasladoSedes(){return costoPorTrasladoSedes;}
    public static List<Integer> getPeriodoTemporadaAlta(){return periodoTemporadaAlta;}
    public static List<Integer> getPeriodoTemporadaBaja(){return periodoTemporadaBaja;}
    public static void setCostoPorConductorAdicional(){
        try{
            int costo= Integer.parseInt(input("Ingrese el nuevo costo diario por conductor adicional"));
            costoPorConductorAdicional=costo;
            System.out.println(">>> Costo actualizado"); 
            }catch(NumberFormatException e){System.out.println("Ingrese solo números en los campos correspondientes");}
    }
    public static void setCostoPorTrasladoSedes(){        
        try{
            int costo= Integer.parseInt(input("Ingrese el nuevo costo por traslado de un vehículo entre sedes tras alquiler"));
            costoPorTrasladoSedes=costo;
            System.out.println(">>> Costo actualizado"); 
            }catch(NumberFormatException e){System.out.println("Ingrese solo números en los campos correspondientes");}
    }
    public static void setPeriodoTemporadaAlta(){
    int fechaInicio= Integer.parseInt(input("Intrese el nuevo inicio de temporada alta en formato mmdd(Ej: 31 de marzo->0331)"));
    int fechaFin= Integer.parseInt(input("Intrese el nuevo cierre de temporada alta en formato mmdd(Ej: 31 de marzo->0331)"));
    List<Integer> periodo=getPeriodoTemporadaAlta();
        periodo.clear();
        periodo.add(fechaInicio);
        periodo.add(fechaFin);
        System.out.println(">>> Costo actualizado");}

    public static void setPeriodoTemporadaBaja(){
    int fechaInicio= Integer.parseInt(input("Intrese el nuevo inicio de temporada baja en formato mmdd(Ej: 31 de marzo->0331)"));
    int fechaFin= Integer.parseInt(input("Intrese el nuevo cierre de temporada baja en formato mmdd(Ej: 31 de marzo->0331)"));
    List<Integer> periodo=getPeriodoTemporadaBaja();
        periodo.clear();
        periodo.add(fechaInicio);
        periodo.add(fechaFin);
        System.out.println(">>> Costo actualizado");}
    public static List<Categoria> getListaCategorias(){return listaCategorias;}
    public static List<Seguro> getListaSeguros(){return listaSeguros;}
    public static List<Sede> getListaSedes(){return listaSedes;}
    public static List<Evento> getListaEventos(){return listaEventos;}
    public static List<Vehiculo> getListaVehiculos(){return listaVehiculos;}
    public static void updateSistema(){
    //updateCategorias();
    //updateSedes();
    //updateSeguros();
    //updateVehiculos();
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
        System.out.println("> información inicial cargada.");
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
            System.out.println("> "+listaCategorias.size()+" categorías cargadas.");
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
            System.out.println("> "+listaSedes.size()+" sedes cargadas.");

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
            System.out.println("> "+Integer.toString(contador)+" perfiles de personal cargados.");
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
        System.out.println("> "+listaSeguros.size()+" seguros cargados.");
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
            System.out.println("> " + Integer.toString(contador) + " licencias cargadas.");
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
            System.out.println("> " + Integer.toString(contador) + " clientes cargados.");

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
            System.out.println("> " + listaEventos.size()+ " eventos cargados.");

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
            System.out.println("> " +Integer.toString(contador)+ " reservas cargadas.");

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
            alquiler alquilerActual= new alquiler(reserva);
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
            alquiler.addAlquiler(alquilerActual);
            contador+=1;
            }else{System.out.println("Formato incorrecto en la línea: " + linea);}
            }  
            System.out.println("> " + Integer.toString(contador)+ " alquileres cargados.");

        }
        catch (IOException e) {e.printStackTrace();}
    }

    private static void loadVehiculos(){
        int contadorEventos=0;
        int contadorAlquileres=0;
        int contadorReservasActivas=0;
        listaVehiculos= new ArrayList<Vehiculo>();
        try (BufferedReader br = new BufferedReader(new FileReader("./data/vehiculos.txt"))) {
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
                        vehiculoActual.addAlquiler(alquiler.assignAlquiler(Integer.parseInt(i)));
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
        System.out.println("> "+listaVehiculos.size()+" vehículos cargados.("+ Integer.toString(contadorEventos)+" eventos encontrados, "+Integer.toString(contadorAlquileres)+" alquileres registrados, "+Integer.toString(contadorReservasActivas)+" reservas activas.)");
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
    public static Seguro assignSeguro(int id_seguro){
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
     public static void nuevoVehiculo(){
        try{
        String placa= input("Ingrese la placa del Vehiculo: "); 
        boolean encontrado=false;
        for (Vehiculo i: getListaVehiculos()){
            if (i.getPlaca().equals(placa)){
                encontrado=true;
                break;
            }
        }  
        if (encontrado==false){
        String marca =input("Ingrese la marca del Vehiculo: ");
        String modelo =input("Ingrese el modelo del Vehiculo: ");
        String color = input("Ingrese el color del Vehiculo: ");
        String tipoTransmision =input( "Ingrese el tipo de transmision del Vehiculo: ");
        String ubicacionGPS = input("Ingrese la ubicacion del Vehiculo");
        String estado = input("Ingrese el estado del Vehiculo");
        int IDcategoria = Integer.parseInt(input("Ingrese el ID categoria del Vehiculo"));
        int IDsede = Integer.parseInt(input("Ingrese el  ID sede del Vehiculo"));
        Categoria categoria=null;
        if ((IDcategoria>=1) && (IDcategoria<=listaCategorias.size())){
            categoria= Inventario.assignCategoria(IDcategoria);
        }
        else{
            System.out.println("Ingreso un Id de Categoria inválido");
        }
        Sede sede=null;
        if (IDsede>=1&& IDsede<=listaSedes.size()){
            sede= Inventario.assignSede(IDsede);
        }
        else{
            System.out.println("Ingresó un ID de sede inválido");
        }
        if(categoria!=null&&sede!=null){
                Vehiculo vehiculo = new Vehiculo(placa, marca, modelo, color, tipoTransmision, ubicacionGPS, estado, false, categoria, sede);
                listaVehiculos.add(vehiculo);
        }
        else{
            System.out.println("La sede o categoria están vacías");
        }

        }
        else{
            System.out.println("La placa ingresada ya existe en el inventario");
        }
        }
        catch(NumberFormatException e){
            System.out.println("Ingrese solo números en los campos correspondientes");}
    }
    public static void closeSistema() {
    }
    public static void eliminarVehiculo(){
            String placa = input("Ingrese la placa del vehículo"); 
            boolean encontrado=false;
            for (Vehiculo i: getListaVehiculos()){
                if (i.getPlaca().equals(placa)){
                    listaVehiculos.remove(i);
                    encontrado=true;
                    break;

                }
            }
            if (encontrado==true){
            System.out.println("Se eliminó del inventario el vehículo con placa "+placa+".");
            }
            else{System.out.println("No se halló ningun vehículo con la placa "+placa+".");
            }
    }
    public static void NuevaCategoria(){
       
        try {
            String nombre= input("Ingrese el nombre de la Ctaegoria del Vehiculo: ");
            int capacidadPersonas = Integer.parseInt(input("Ingrese el ID categoria del Vehiculo"));
            double pctg_temporadaAlta= Double.parseDouble(input("Ingrese el porcentaje de incremento para la categoria en temporada alta"));
            double pctg_temporadaBaja= Double.parseDouble(input("Ingrese el porcentaje de descuento para la categoria en temporada baja"));
            int costoAveriaLeve = Integer.parseInt(input("Ingrese el costo de averia leve del Vehiculo"));
            int costoAveriaModerado = Integer.parseInt(input("Ingrese el costo de averia moderado del Vehiculo"));
            int costoAveriaTotal = Integer.parseInt(input("Ingrese el costo de averia Total del Vehiculo"));
            int TarifaDiaria = Integer.parseInt(input("Ingrese el costo de tarifa diaria del Vehiculo"));
            int IdPadre = Integer.parseInt(input("Ingrese el Id padre del Vehiculo (En caso de que no tenga marque 0)"));
            Categoria categoria = new Categoria(nombre, capacidadPersonas, pctg_temporadaAlta, pctg_temporadaBaja, costoAveriaLeve, costoAveriaModerado, costoAveriaTotal, TarifaDiaria, IdPadre);
            listaCategorias.add(categoria);
        } 
        catch(NumberFormatException e){
            System.out.println("Ingrese solo números en los campos correspondientes");}
        }




    
    public static void nuevoSeguro(){
    try{
        String desc = input("Ingrese una descripción del seguro");
        int pctg_tarifadiaria= Integer.parseInt(input("Ingrese el porcentaje de la tarifa diaria que el seguro cuesta (ej: 90%->0.9)"));
        Seguro seguro= new Seguro(pctg_tarifadiaria,desc );
        listaSeguros.add(seguro);
        System.out.println("El nuevo seguro se guardo con el id"+ Integer.toString(seguro.getID()));
    }
    catch(NumberFormatException e){
        System.out.println("Ingrese solo números en los campos correspondientes");}}
    
    public static void editarSeguro(){
        try{
            int id_seguro= Integer.parseInt(input("Ingrese el ID del seguro que desea modificar"));
            if ((id_seguro>0) &&(id_seguro<=Inventario.getListaSeguros().size())){
            System.out.println("\nDesea editar el porcentaje de tarifa diaria?\n");
            System.out.println("1.Sí");
            System.out.println("2.No(ó cualquier otro número)");
            int opcion_pctg = Integer.parseInt(input("Por favor seleccione una opción"));    
            if (opcion_pctg==1){
                int pctg=Integer.parseInt(input("Ingrese el nuevo porcentage de tarifa diaria designado al seguro"));
                Inventario
                .assignSeguro(id_seguro).setPctg_TarifaDiaria(pctg);
            }
            System.out.println("\nDesea editar la descripción del seguro?\n");
            System.out.println("1.Sí");
            System.out.println("2.No(ó cualquier otro número)");
            int opcion_desc = Integer.parseInt(input("Por favor seleccione una opción"));    
            if (opcion_desc==1){
                String desc=input("Ingrese la nueva descripcion");
                Inventario.assignSeguro(id_seguro).setDescripcion(desc);
            }
            }
            else{System.out.println("Ingrese el id de un seguro válido ");}

        }
        catch(NumberFormatException e){
            System.out.println("Ingrese solo números en los campos correspondientes");}}
    
    public static void eliminarSeguro(){
        try{
            int id = Integer.parseInt(input("Ingrese el ID del seguro")); 
            boolean encontrado=false;
            for (Seguro i: getListaSeguros()){
                if (i.getID()==id){
                    listaSeguros.remove(i);
                    encontrado=true;
                    break;}}
            if (encontrado==true){System.out.println("Se eliminó del inventario el vehículo con placa "+Integer.toString(id)+".");}
            else{System.out.println("No se halló ningun vehículo con la placa "+Integer.toString(id)+".");}
                }
        catch(NumberFormatException e){System.out.println("Ingrese solo números en los campos correspondientes");}}
    
    public static void nuevaSede(){
        try{
            String nombreSede = input("Ingrese el nombre de la nueva sede");
            String ubicacion = input("Ingrese la ubicación de la nueva sede");
            List<Integer> horarioSemana= new ArrayList<Integer>();
            List<Integer> horarioFinSemana= new ArrayList<Integer>();
            int horaAperturaSemana= Integer.parseInt(input("Ingrese la hora de apertura entre semana"));
            int horaCierreSemana= Integer.parseInt(input("Ingrese la hora de cierre entre semana"));    
            int horaAperturaFinSemana= Integer.parseInt(input("Ingrese la hora de apertura para fin de semana"));
            int horaCierreFinSemana= Integer.parseInt(input("Ingrese la hora de cierre para fin de semana")); 
            horarioSemana.add(horaAperturaSemana);
            horarioSemana.add(horaCierreSemana);
            horarioFinSemana.add(horaAperturaFinSemana);
            horarioFinSemana.add(horaCierreFinSemana);
            Sede sede= new Sede(nombreSede, ubicacion, horarioSemana, horarioFinSemana);
            listaSedes.add(sede);
            System.out.println("La nueva sede se guardo con el id"+ Integer.toString(sede.getID()));
        }
        catch(NumberFormatException e){
            System.out.println("Ingrese solo números en los campos correspondientes");}}

    public static void registrarAdminLocal(){
        try {
            String login=input("Ingrese el login del usuario");
            String password=input("Ingrese la contraseña del usuario");
            int Idsede=Integer.parseInt(input("ingrese el Id de la sede"));
            Sede sede=null;
            if (Idsede>=1&& Idsede<=listaSedes.size()){
                sede= Inventario.assignSede(Idsede);
            }
            else{
                System.out.println("Ingresó un ID de sede inválido");
            }
            if(sede!=null){
                personal adminlocal=new personal(login, password, "AdminLocal", sede);
            }
            else{
                System.out.println("La sede se encuentra vacia");
            }

            
        } catch (Exception e) {
            // TODO: handle exception
             System.out.println("Ingrese solo números en los campos correspondientes");
        }
    }
    public static void actualizarAdminLocal(){
        try {
            String login=input("Ingrese el login del administrador");
            List<personal> listpersonal= personal.getCredencialesPersonal();
            for(personal i:listpersonal){
                if (i.getLogin()==login && i.getTipoPersonal()=="AdminLocal"){
                    int NidSede=Integer.parseInt(input("Ingrese el Id de la nueva sede"));
                    Sede sede=null;
                    if (NidSede>=1&& NidSede<=listaSedes.size()){
                        sede= Inventario.assignSede(NidSede);
                    }
                    else{
                        System.out.println("Ingresó un ID de sede inválido");

                    }
                    i.setSede(sede);
                    break;
                    }
            }
        } 
        catch (Exception e) {
            System.out.println("Ingrese solo números en los campos correspondientes");
        }
    }

    public static void editarSede(){
    //SedeSur;cra58 #2, Bogotá;[0730,1430];[0730,1530];[]
    try{
        int id_sede= Integer.parseInt(input("Ingrese el ID de la sede que desea modificar"));
        if ((id_sede>0) &&(id_sede<=Inventario.getListaSedes().size())){
        System.out.println("\nDesea editar el nombre de la sede?\n");
        System.out.println("1.Sí");
        System.out.println("2.No(ó cualquier otro número)");
        int opcion_nombre = Integer.parseInt(input("Por favor seleccione una opción"));    
        if (opcion_nombre==1){
            String nombre=input("Ingrese el nuevo nombre para la sede");
            Inventario.assignSede(id_sede).setNombre(nombre);
        }
        System.out.println("\nDesea editar la ubicación de la sede?\n");
        System.out.println("1.Sí");
        System.out.println("2.No(ó cualquier otro número)");
        int opcion_ubi = Integer.parseInt(input("Por favor seleccione una opción"));    
        if (opcion_ubi==1){
            String ubi=input("Ingrese la nueva ubicación");
            Inventario.assignSede(id_sede).setUbicacion(ubi);;
        }
        System.out.println("\nDesea modificar el horario entre semana de la sede?\n");
        System.out.println("1.Sí");
        System.out.println("2.No(ó cualquier otro número)");
        int opcion_hsemana = Integer.parseInt(input("Por favor seleccione una opción"));   
        if (opcion_hsemana==1){
            List<Integer> horario= new ArrayList<Integer>();
            int hapertura=Integer.parseInt(input("Ingrese la nueva hora de entrada"));
            int hcierre=Integer.parseInt(input("Ingrese la nueva hora de cierre"));
            horario.add(hapertura);
            horario.add(hcierre);
            Inventario.assignSede(id_sede).setHorarioAtencionEnSemana(horario);;
        }
        System.out.println("\nDesea modificar el horario de fin de semana de la sede?\n");
        System.out.println("1.Sí");
        System.out.println("2.No(ó cualquier otro número)");
        int opcion_hFinsemana = Integer.parseInt(input("Por favor seleccione una opción"));   
        if (opcion_hFinsemana==1){
            List<Integer> horario= new ArrayList<Integer>();
            int hapertura=Integer.parseInt(input("Ingrese la nueva hora de entrada"));
            int hcierre=Integer.parseInt(input("Ingrese la nueva hora de cierre"));
            horario.add(hapertura);
            horario.add(hcierre);
            Inventario.assignSede(id_sede).setHorarioAtencionFinSemana(horario);
        } 
        }
        else{System.out.println("Ingrese el id de un seguro válido ");}

    }
    catch(NumberFormatException e){
        System.out.println("Ingrese solo números en los campos correspondientes");}}
 
}




