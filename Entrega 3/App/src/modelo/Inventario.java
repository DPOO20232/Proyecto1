package modelo;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
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
    /**
     * Carga todos los datos del sistema, incluyendo información general, categorías, sedes, personal, seguros,
     * licencias, clientes, eventos, reservas, alquileres y vehículos.
     * Este método es estático y se utiliza para cargar todos los datos del sistema al iniciar la aplicación.
     */
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
        /**
         * Obtiene el nombre de la compañía.
         *
         * @return El nombre de la compañía.
         */
        return nombreCompania;}
    public static int getCostoPorConductorAdicional(){
        /**
         * Obtiene el costo por conductor adicional.
         *
         * @return El costo por conductor adicional.
         */
        return costoPorConductorAdicional;}
    public static int getCostoPorTrasladoSedes(){
        /**
         * Obtiene el costo por traslado entre sedes.
         *
         * @return El costo por traslado entre sedes.
         */
        return costoPorTrasladoSedes;}
    public static List<Integer> getPeriodoTemporadaAlta(){
        /**
         * Obtiene la lista de períodos de temporada alta.
         *
         * @return La lista de períodos de temporada alta.
         */
        return periodoTemporadaAlta;}
    public static List<Integer> getPeriodoTemporadaBaja(){
        /**
         * Obtiene la lista de períodos de temporada baja.
         *
         * @return La lista de períodos de temporada baja.
         */
        return periodoTemporadaBaja;}
    public static List<Categoria> getListaCategorias(){
        /**
         * Obtiene la lista de categorías disponibles en el sistema.
         *
         * @return La lista de categorías.
         */
        return listaCategorias;}
    public static List<Seguro> getListaSeguros(){
        /**
         * Obtiene la lista de seguros disponibles en el sistema.
         *
         * @return La lista de seguros.
         */
        return listaSeguros;}
    public static List<Sede> getListaSedes(){
        /**
         * Obtiene la lista de sedes disponibles en el sistema.
         *
         * @return La lista de sedes.
         */
        return listaSedes;}
    public static List<Evento> getListaEventos(){
        /**
         * Obtiene la lista de sedes disponibles en el sistema.
         *
         * @return La lista de sedes.
         */
        return listaEventos;}
    public static List<Vehiculo> getListaVehiculos(){
        /**
         * Obtiene la lista de sedes disponibles en el sistema.
         *
         * @return La lista de sedes.
         */
        return listaVehiculos;}

    public static boolean esTemporadaAlta(int mmdd1, int mmdd2){
        /**
         * Determina si un rango de fechas (mmdd1 y mmdd2) se encuentra dentro del período de temporada alta.
         *
         * @param mmdd1 El primer valor de fecha en formato MMDD (mes y día).
         * @param mmdd2 El segundo valor de fecha en formato MMDD (mes y día).
         * @return true si el rango de fechas se encuentra en temporada alta, de lo contrario, false.
         */
        int inicioTemp1=getPeriodoTemporadaAlta().get(0);
        int finTemp1=getPeriodoTemporadaAlta().get(1);
        int inicioTemp2=getPeriodoTemporadaAlta().get(2);
        int finTemp2=getPeriodoTemporadaAlta().get(3);
        boolean condicion1=(mmdd1 <= finTemp1) && (mmdd1 >= inicioTemp1)||(mmdd2 <= finTemp1) && (mmdd2 >= inicioTemp1);
        boolean condicion2=(mmdd1 <= finTemp2) && (mmdd1 >= inicioTemp2)||(mmdd2 <= finTemp2) && (mmdd2 >= inicioTemp2);
        return condicion1||condicion2;
        
    }
    public static boolean esTemporadaBaja(int mmdd1, int mmdd2){
        /**
         * Determina si un rango de fechas (mmdd1 y mmdd2) se encuentra dentro del período de temporada baja.
         *
         * @param mmdd1 El primer valor de fecha en formato MMDD (mes y día).
         * @param mmdd2 El segundo valor de fecha en formato MMDD (mes y día).
         * @return true si el rango de fechas se encuentra en temporada baja, de lo contrario, false.
         */
        int inicioTemp=getPeriodoTemporadaBaja().get(0);
        int finTemp=getPeriodoTemporadaBaja().get(1);
        return (mmdd1 <= finTemp) && (mmdd1 >= inicioTemp)||(mmdd2 <= finTemp) && (mmdd2 >= inicioTemp);
    }
    public static void setCostoPorConductorAdicional(int costo){
        /**
         * Establece el costo por conductor adicional.
         *
         * @param costo El costo por conductor adicional a establecer.
         */
        costoPorConductorAdicional=costo;}
    public static void setCostoPorTrasladoSedes(int costo){
        /**
         * Establece el costo por traslado entre sedes.
         *
         * @param costo El costo por traslado entre sedes a establecer.
         */
        costoPorTrasladoSedes=costo;}
    public static void setListaCategorias(Categoria categoria){
        /**
         * Agrega una categoría a la lista de categorías disponibles.
         *
         * @param categoria La categoría a agregar a la lista de categorías.
         */
        getListaCategorias().add(categoria);}
    public static void setListaSeguros(Seguro seguro){
        /**
         * Agrega un seguro a la lista de seguros disponibles.
         *
         * @param seguro El seguro a agregar a la lista de seguros.
         */
        getListaSeguros().add(seguro);}
    public static void setListaSedes(Sede sede){
        /**
         * Agrega un seguro a la lista de seguros disponibles.
         *
         * @param seguro El seguro a agregar a la lista de seguros.
         */
        getListaSedes().add(sede);}
    public static void setListaEventos(Evento evento){
        /**
         * Agrega un evento a la lista de eventos registrados.
         *
         * @param evento El evento a agregar a la lista de eventos.
         */
        getListaEventos().add(evento);}
    public static void setListaVehiculos(Vehiculo vehiculo){
        /**
         * Agrega un vehículo a la lista de vehículos disponibles.
         *
         * @param vehiculo El vehículo a agregar a la lista de vehículos.
         */
        getListaVehiculos().add(vehiculo);}

    public static void updateSistema() throws IOException{
    /**
     * Actualiza la información del sistema, incluyendo la información general, categorías, sedes, personal,
     * seguros, licencias, reservas, vehículos, eventos, alquileres y clientes.
     *
     * @throws IOException Si ocurre un error durante la actualización de datos, se lanza una excepción
     *                     de tipo IOException.
     */
    updateInfo();
    updateCategorias();
    updateSedes();
    updatePersonal();
    updateSeguros();
    updateLicencia();
    updateReserva();
    updateVehiculo();
    updateEvento();
    updateAlquiler();
    updateCliente();
    }
    private static void updateInfo() throws IOException{
        /**
         * Actualiza y guarda la información general de la empresa en el archivo de texto.
         *
         * @throws IOException Si ocurre un error al escribir en el archivo de información, se lanza una excepción de tipo IOException.
         */
        File archivo = new File("./data/info.txt");
        FileWriter escritor = new FileWriter(archivo);
        String nombrecompania=nombreCompania;
        String costoConductor=Integer.toString(costoPorConductorAdicional);
        String costoTraslado=Integer.toString(costoPorTrasladoSedes);
        List<Integer> periodoBaja=periodoTemporadaBaja;
        StringBuilder temporadaBaja=new StringBuilder();
        temporadaBaja.append("[");
        for (Integer i: periodoBaja){
            temporadaBaja.append(Integer.toString(i));
            temporadaBaja.append(",");
        }
        if (temporadaBaja.length()>=3){
                 temporadaBaja.setLength(temporadaBaja.length() - 1);
            }
        temporadaBaja.append("]");
        String PeriodoTempBaja=temporadaBaja.toString();
        List<Integer> periodoAlta=periodoTemporadaAlta;
        StringBuilder temporadaAlta=new StringBuilder();
        temporadaAlta.append("[");
        for (Integer i: periodoAlta){
            temporadaAlta.append(Integer.toString(i));
            temporadaAlta.append(",");
        }
        if (temporadaAlta.length()>=3){
                 temporadaAlta.setLength(temporadaAlta.length() - 1);
            }
        temporadaAlta.append("]");
        String PeriodoTempAlta= temporadaAlta.toString();
        String resultado = String.format("%s;%s%n%s;%s%n%s;%s%n%s;%s%n%s;%s%n", "nombreCompania", nombrecompania, "costoPorConductorAdicional", costoConductor, "costoPorTrasladoSedes", costoTraslado, "periodoTemporadaBaja", PeriodoTempBaja, "periodoTemporadaAlta", PeriodoTempAlta);
        escritor.write(resultado);
        escritor.close();

    }
    private static void updateCategorias() throws IOException{
        /**
         * Actualiza y guarda la información de las categorías de vehículos en el archivo de texto.
         *
         * @throws IOException Si ocurre un error al escribir en el archivo de categorías, se lanza una excepción de tipo IOException.
         */
        File archivo = new File("./data/categorias.txt");
        FileWriter escritor = new FileWriter(archivo);
        List <Categoria> lstcategorias=listaCategorias;
        Collections.sort(lstcategorias, new Comparator<Categoria>() {
		    public int compare(Categoria c1, Categoria c2) {
                String tipo1=c1.getnombreCategoria().split("_")[1];
                String tipo2=c2.getnombreCategoria().split("_")[1];
                return tipo2.compareTo(tipo1);
		    }
		});
            for(Categoria i: lstcategorias){
                //ordenarlo segun premium/intermedio/economico
                    String strid= Integer.toString(i.getID());
                    String strnombreCategoria=i.getnombreCategoria();
                    String strcapacidad= Integer.toString(i.getCapacidad());
                    String strpctg_temporadaAlta= Double.toString(i.getPctg_temporadaAlta());
                    String strpctg_temporadaBaja= Double.toString(i.getPctg_temporadaBaja());
                    String strcostoAveriaLeve= Integer.toString(i.getCostoAveriaLeve());
                    String strcostoAveriaModerada= Integer.toString(i.getCostoAveriaModerada());
                    String strcostoAveriaTotal= Integer.toString(i.getCostoAveriaTotal());
                    String strTarifaDiaria= Integer.toString(i.getTarifaDiaria());
                    String strid_padre=Integer.toString(i.getId_Padre());
                    String resultado = String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s;%s%n", strid, strnombreCategoria, strcapacidad, strpctg_temporadaAlta, strpctg_temporadaBaja, strcostoAveriaLeve, strcostoAveriaModerada, strcostoAveriaTotal, strTarifaDiaria, strid_padre);
                    escritor.write(resultado);
                    //escritor.write(System.lineSeparator());
                    
            }
            escritor.close();
        }
    private static void updateSedes() throws IOException{
        /**
         * Actualiza y guarda la información de las sedes en el archivo de texto.
         *
         * @throws IOException Si ocurre un error al escribir en el archivo de sedes, se lanza una excepción de tipo IOException.
         */
        File archivo = new File("./data/sedes.txt");
        FileWriter escritor = new FileWriter(archivo);
        List<Sede>lstsedes=listaSedes;
        for(Sede i: lstsedes){
            String strid = Integer.toString(i.getID());
            String nombreSede= i.getNombre();
            String ubicacionSede= i.getUbicacion();
            List<Integer> horarioSemana= i.getHorarioAtencionEnSemana();
            StringBuilder strhorarioSemana = new StringBuilder();
            strhorarioSemana.append("[");
            strhorarioSemana.append(horarioSemana.get(0));
            strhorarioSemana.append(",");
            strhorarioSemana.append(horarioSemana.get(1));
            strhorarioSemana.append("]");
            strhorarioSemana.toString();
            List<Integer> horarioFinSemana= i.getHorarioAtencionFinSemana();
            StringBuilder strhorarioFinSemana = new StringBuilder();
            strhorarioFinSemana.append("[");
            strhorarioFinSemana.append(horarioFinSemana.get(0));
            strhorarioFinSemana.append(",");
            strhorarioFinSemana.append(horarioFinSemana.get(1));
            strhorarioFinSemana.append("]");
            strhorarioFinSemana.toString();
            String resultado = String.format("%s;%s;%s;%s;%s%n", strid, nombreSede, ubicacionSede, strhorarioSemana, strhorarioFinSemana);
            escritor.write(resultado);
        }
        escritor.close();
      
    }
    private static void updatePersonal() throws IOException{
        /**
         * Actualiza y guarda la información del personal en el archivo de texto.
         *
         * @throws IOException Si ocurre un error al escribir en el archivo de personal, se lanza una excepción de tipo IOException.
         */
        File archivo = new File("./data/personal.txt");
        FileWriter escritor2= new FileWriter(archivo);
        List<personal> lstpersonal= personal.getCredencialesPersonal();
        Admin admin=personal.getCredendialAdmin();
        String loginA=admin.getLogin();
        String passwordA=admin.getPassword();
        String idSedeA="0";
        String tipoPersonalA="Admin";
        String resultadoA = String.format("%s;%s;%s;%s%n",loginA, passwordA, idSedeA, tipoPersonalA);
        escritor2.write(resultadoA);
        for (personal i: lstpersonal){
            String login=i.getLogin();
            String password=i.getPassword();
            Sede sede=i.getSede();
            String idSede=Integer.toString(sede.getID());
            String tipoPersonal=i.getTipoPersonal();
            String resultado = String.format("%s;%s;%s;%s%n",login, password, idSede, tipoPersonal);
            escritor2.write(resultado);

        }
        escritor2.close();
    }
    private static void updateSeguros() throws IOException{
        /**
         * Actualiza y guarda la información de los seguros en el archivo de texto.
         *
         * @throws IOException Si ocurre un error al escribir en el archivo de seguros, se lanza una excepción de tipo IOException.
         */
        File archivo = new File("./data/seguros.txt");
        FileWriter escritor2= new FileWriter(archivo);
        List<Seguro> lstseguros=listaSeguros;
        for (Seguro i: lstseguros){
            String strid = Integer.toString(i.getID());
            String strpctg_tarifadiaria = Double.toString(i.getPctg_TarifaDiaria());
            String descripcion=i.getDescripcion();
            String resultado = String.format("%s;%s;%s%n", strid, strpctg_tarifadiaria, descripcion);
            escritor2.write(resultado);
        }
        escritor2.close();
    }
    private static void updateLicencia() throws IOException{
        /**
         * Actualiza y guarda la información de las licencias en el archivo de texto.
         *
         * @throws IOException Si ocurre un error al escribir en el archivo de licencias, se lanza una excepción de tipo IOException.
         */
        File archivo = new File("./data/licencias.txt");
        FileWriter escritor= new FileWriter(archivo);
        List<Licencia> lstlicencia=Usuario.getListaLicencias();
        for (Licencia i: lstlicencia){
            String numLicencia=Integer.toString(i.getNumeroLicencia());
            String fechaExpedicion= Integer.toString(i.getFechaExpedicion());
            String fechaVencimiento=Integer.toString(i.getFechaVencimiento());
            String paisExpedicion=i.getPaisExpedicion();
            String resultado = String.format("%s;%s;%s;%s%n", numLicencia, fechaExpedicion, fechaVencimiento, paisExpedicion);
            escritor.write(resultado);
        }
        escritor.close();
    }
    private static void updateReserva() throws IOException{
        /**
         * Actualiza y guarda la información de las reservas en el archivo de texto.
         *
         * @throws IOException Si ocurre un error al escribir en el archivo de reservas, se lanza una excepción de tipo IOException.
         */
        File archivo = new File("./data/reservas.txt");
        FileWriter escritor= new FileWriter(archivo);
        List<Reserva> lstreserva= Reserva.getListaReservas();
        for(Reserva i: lstreserva){
            String stridReserva= Integer.toString(i.getID());
            String strfechaRecoger= Integer.toString(i.getFechaRecoger());
            String strfechaEntregar=Integer.toString(i.getFechaEntregar());
            String strhoraRecoger= Integer.toString(i.getHoraRecoger());
            String strhoraEntregar= Integer.toString(i.getHoraEntregar());
            String strreservaEnSede= Boolean.toString(i.getReservaEnSede());
            Sede sede=i.getSedeRecoger();
            String strid_sedeRecoger=Integer.toString(sede.getID());
            Sede sedeE=i.getSedeEntregar();
            String strid_sedeEntregar=Integer.toString(sedeE.getID());
            Categoria categoria=i.getCategoria();
            String strid_categoria=Integer.toString(categoria.getID());
            Cliente cliente= i.getCliente();
            String strcedula_cliente= Integer.toString(cliente.getNumeroCedula());
            String pagoReserva= Double.toString(i.getPagoReserva());
            String resultado = String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s%n", stridReserva, strfechaRecoger, strfechaEntregar, strhoraRecoger, strhoraEntregar, strreservaEnSede, strid_sedeRecoger, strid_sedeEntregar, strid_categoria, strcedula_cliente,pagoReserva);
            escritor.write(resultado);
        }
        escritor.close();
    }
    private static void updateVehiculo() throws IOException{
        /**
         * Actualiza y guarda la información de los vehículos en el archivo de texto.
         *
         * @throws IOException Si ocurre un error al escribir en el archivo de vehículos, se lanza una excepción de tipo IOException.
         */
        File archivo = new File("./data/vehiculos.txt");
        FileWriter escritor= new FileWriter(archivo);
        List<Vehiculo> lstvehiculo=listaVehiculos;
        for(Vehiculo i: lstvehiculo){
            String placa=i.getPlaca();
            String marca=i.getMarca();
            String modelo=i.getModelo();
            String color=i.getColor();
            String tipo_trasmicion=i.getTipoTransmicion();
            String estado= i.getEstado();
            String averiado= Boolean.toString(i.getAveriado());
            Categoria categoria=i.getCategoria();
            String strid_categoria=Integer.toString(categoria.getID());
            Sede sede=i.getSede();
            String id_sede=Integer.toString(sede.getID());
            List<Evento> stringIDsEventos=i.getHistorialEventos();
            StringBuilder idsevento = new StringBuilder();
            idsevento.append("[");
            for(Evento x: stringIDsEventos){ 
                String IdEvento=Integer.toString(x.getID());
                idsevento.append(IdEvento);
                idsevento.append(",");
            }
            if (idsevento.length()>=3){
                 idsevento.setLength(idsevento.length() - 1);
            }
           
            idsevento.append("]");
            String lstidEvento=idsevento.toString();
            List<Reserva> stringIDsReservasActivas=i.getReservasActivas();
            StringBuilder idsReserva = new StringBuilder();
            idsReserva.append("[");
            for(Reserva z:stringIDsReservasActivas){
                String IdReserva=Integer.toString(z.getID());
                idsReserva.append(IdReserva);
                idsReserva.append(",");
            }
            if (idsReserva.length()>=3){
                 idsReserva.setLength(idsReserva.length() - 1);
            }
            idsReserva.append("]");
            String lstidReserva=idsReserva.toString();
            List<alquiler> stringIDsAlquileres=i.getHistorialAlquileres();
            StringBuilder idsAlquiler = new StringBuilder();
            idsAlquiler.append("[");
            for(alquiler s: stringIDsAlquileres){
                String IdAlquiler=Integer.toString(s.getID());
                idsAlquiler.append(IdAlquiler);
                idsAlquiler.append(",");
            }
            if (idsAlquiler.length()>=3){
                 idsAlquiler.setLength(idsAlquiler.length() - 1);
            }
            idsAlquiler.append("]");
            String lstidAlquiler=idsAlquiler.toString();
            String resultado = String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s%n", placa, marca, modelo, color, tipo_trasmicion, estado, averiado,strid_categoria, id_sede, lstidEvento, lstidReserva,lstidAlquiler);
            escritor.write(resultado);

        }
        escritor.close();
    }
    private static void updateEvento() throws IOException{
        /**
         * Actualiza y guarda la información de los eventos en el archivo de texto.
         *
         * @throws IOException Si ocurre un error al escribir en el archivo de eventos, se lanza una excepción de tipo IOException.
         */
        File archivo = new File("./data/eventos.txt");
        FileWriter escritor= new FileWriter(archivo);
        List<Evento> lsteventos=listaEventos;
        List<String> lstrepetido= new ArrayList<String>();
        for(Evento i: lsteventos){
            String idEvento=Integer.toString(i.getID());
            String FechaInicio=Integer.toString(i.getFechaInicio());
            String HoraInicio=Integer.toString(i.getHoraInicio());
            String HoraFin=Integer.toString(i.getHoraFin());
            String FechaFin=Integer.toString(i.getFechaFin());
            String descripcion=i.getDescripcion();
            String resultado = String.format("%s;%s;%s;%s;%s;%s%n", idEvento, FechaInicio, FechaFin, HoraInicio, HoraFin, descripcion);
            boolean repetido=false;
            for (String j: lstrepetido){
                if (j.equals(resultado)){
                    repetido=true;
                    break;
                }
            }
            if (repetido==false){
                escritor.write(resultado);
                lstrepetido.add(resultado);

            }
        }
        escritor.close();
    }
    private static void updateAlquiler() throws IOException{
        /**
         * Actualiza y guarda la información de los alquileres en el archivo de texto.
         *
         * @throws IOException Si ocurre un error al escribir en el archivo de alquileres, se lanza una excepción de tipo IOException.
         */
        File archivo = new File("./data/alquileres.txt");
        FileWriter escritor= new FileWriter(archivo);
        List<alquiler> lstAlquiler=alquiler.getListaAlquileres();
        for (alquiler i: lstAlquiler){
            String idAlquiler=Integer.toString(i.getID());
            String PagoFinal=Double.toString(i.getPagoFinal());
            Reserva reserva= i.getReserva();
            String IdReserva=Integer.toString(reserva.getID());
            List<Conductor> lstconductor=i.getConductores();
            String activo=Boolean.toString(i.getActivo());
            StringBuilder conductores = new StringBuilder();
            conductores.append("[");
            for(Conductor x: lstconductor){
                conductores.append("[");
                conductores.append(x.getNombre());
                conductores.append("-");
                conductores.append(Integer.toString(x.getCedula()));
                conductores.append("-");
                Licencia licencia=x.getLicencia();
                conductores.append(Integer.toString(licencia.getNumeroLicencia()));
                conductores.append("]");
                conductores.append(",");

            }
            if (conductores.length()>=3){
                conductores.setLength(conductores.length() - 1);
            }
            conductores.append("]");
            String conductor=conductores.toString();
            List<Seguro> lstseguro=i.getSeguros();
            StringBuilder seguros = new StringBuilder();
            seguros.append("[");
            for(Seguro z: lstseguro){
                seguros.append(Integer.toString(z.getID()));
                seguros.append(",");
            }
            if (seguros.length()>=3){
                seguros.setLength(seguros.length() - 1);
            }
            seguros.append("]");
            String seguro=seguros.toString();
            List<PagoExcedente> lstpagosexcedentes=i.getPagosExcedentes();
            StringBuilder pagosExcedentes = new StringBuilder();
            pagosExcedentes.append("[");
            for(PagoExcedente s: lstpagosexcedentes){
                pagosExcedentes.append("[");
                pagosExcedentes.append(s.getMotivoPago());
                pagosExcedentes.append("-");
                pagosExcedentes.append(Double.toString(s.getValorPago()));
                pagosExcedentes.append("]");
                pagosExcedentes.append(",");
            }
            if (pagosExcedentes.length()>=3){
                pagosExcedentes.setLength(pagosExcedentes.length() - 1);
            }
            pagosExcedentes.append("]");
            String pagoExcedente=pagosExcedentes.toString();

            String resultado = String.format("%s;%s;%s;%s;%s;%s;%s%n", idAlquiler, PagoFinal, IdReserva, conductor, seguro, pagoExcedente, activo);
            escritor.write(resultado);


            }

            escritor.close();


        }

    private static void updateCliente() throws IOException{
        /**
         * Actualiza y guarda la información de los clientes en el archivo de texto.
         *
         * @throws IOException Si ocurre un error al escribir en el archivo de clientes, se lanza una excepción de tipo IOException.
         */
        File archivo = new File("./data/clientes.txt");
        FileWriter escritor= new FileWriter(archivo);
        List<Cliente> clientes=Usuario.getListaClientes();
        for(Cliente i: clientes){
            String login=i.getLogin();
            String password=i.getPassword();
            String cedula= Integer.toString(i.getNumeroCedula());
            String nombre= i.getNombre();
            String correo=i.getMail();
            String celular=Long.toString(i.getTelefono());
            String nacimiento=Integer.toString(i.getFechaNacimiento());
            String nacionalidad=i.getNacionalidad();
            Tarjeta tarjeta=i.getTarjeta();
            StringBuilder tarjet=new StringBuilder();
            tarjet.append("[");
            tarjet.append(Long.toString(tarjeta.getNumeroTarjeta()));
            tarjet.append(",");
            tarjet.append(Integer.toString(tarjeta.getFechaVencimiento()));
            tarjet.append(",");
            tarjet.append(tarjeta.getMarcaTarjeta());
            tarjet.append(",");
            tarjet.append(tarjeta.getNombreTitular());
            tarjet.append("]");
            String datosTarjeta=tarjet.toString();
            Licencia licencia=i.getLicencia();
            String NumLicencia=Integer.toString(licencia.getNumeroLicencia());
            String resultado = String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s;%s%n", login, password, cedula, nombre, correo, celular, nacimiento, nacionalidad, datosTarjeta, NumLicencia);
            escritor.write(resultado);

        }
        escritor.close();
    }
    
    private static void loadInfo(){
        /**
         * Carga la información inicial del sistema desde un archivo de texto. La información cargada incluye el nombre de la compañía,
         * el costo por conductor adicional, el costo por traslado entre sedes y los periodos de temporada baja y alta.
         * La información se almacena en las variables estáticas correspondientes.
         *
         * @throws IOException Si ocurre un error al leer el archivo de información, se lanza una excepción de tipo IOException.
         */
        try (BufferedReader br = new BufferedReader(new FileReader("./data/info.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                 if (partes.length >= 2){
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
                        String subString1= partes[1].substring(1,partes[1].length()-1);
                        String[] subPartes1=subString1.split(",");
                        int inicioTemp1 = Integer.parseInt(subPartes1[0]);
                        int finTemp1 = Integer.parseInt(subPartes1[1]);
                        periodoTemporadaAlta.add(inicioTemp1);
                        periodoTemporadaAlta.add(finTemp1);    
                    }
                 }
            }
        System.out.println("> información inicial cargada.");
        }catch (IOException e) {e.printStackTrace();}

    }
    private static void loadCategorias(){
        /**
         * Carga las categorías de vehículos desde un archivo de texto. Las categorías se almacenan en la lista estática listaCategorias.
         * Cada línea del archivo contiene la información de una categoría, que se parsea y se crea un objeto de la clase Categoria.
         * Las categorías se añaden a la lista y, si tienen una categoría padre, se establece la relación con la categoría padre.
         *
         * @throws IOException Si ocurre un error al leer el archivo de categorías, se lanza una excepción de tipo IOException.
         */
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
                    if (id_Padre!=0){
                        categoriaActual.setPadre(Inventario.assignCategoria(id_Padre));}
                    listaCategorias.add(categoriaActual);
                }
            }
            Collections.sort(listaCategorias, new Comparator<Categoria>() {
		    public int compare(Categoria c1, Categoria c2) {
                int tipo1=c1.getID();
                int tipo2=c2.getID();
                if (tipo1>tipo2){return 1;}
                else{return 2;}
		    }
		});
            System.out.println("> "+listaCategorias.size()+" categorías cargadas.");
        } catch (IOException e) {e.printStackTrace();}
    }
    private static void loadSedes(){
        /**
        * Carga la información de las sedes desde un archivo de texto. Cada línea del archivo contiene la información de una sede,
        * que se parsea y se crea un objeto de la clase Sede. Las sedes se agregan a la lista estática listaSedes.
        *
        * @throws IOException Si ocurre un error al leer el archivo de sedes, se lanza una excepción de tipo IOException.
        */
    //1;SedeChapinero;cra7 #70, Bogotá;[0630,1630];[0630,1230];[]
        try (BufferedReader br = new BufferedReader(new FileReader("./data/sedes.txt"))) {
            listaSedes= new ArrayList<Sede>();
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length == 5) {
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
        /**
         * Carga la información del personal de la compañía desde un archivo de texto. Cada línea del archivo contiene la información
         * de un miembro del personal, que se parsea y se crea un objeto de la clase Admin o Personal. Los miembros del personal se agregan
         * a las listas correspondientes (Admin o Personal) y se asignan a las sedes si es necesario.
         *
         * @throws IOException Si ocurre un error al leer el archivo de personal, se lanza una excepción de tipo IOException.
         */
        try (BufferedReader br = new BufferedReader(new FileReader("./data/personal.txt"))) {
            int contador=0;
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length == 4) {
                    String login=partes[0];
                    //CHECK
                    Usuario.addNombreLogin(login);
                    String password=partes[1];
                    int id_sede= Integer.parseInt(partes[2]);
                    Sede sede=null;
                    String tipoUsuario=partes[3];
                    
                    if (id_sede>0){
                        sede= Inventario.assignSede(id_sede);
                        personal personalActual= new personal(login, password, tipoUsuario, sede);
                        //CHECK
                        if (tipoUsuario.equals("AdminLocal")){sede.setAdminLocal(personalActual);}
                        else{sede.addPersonalSede(personalActual);}
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
        /**
         * Carga la información de los seguros desde un archivo de texto. Cada línea del archivo contiene la información
         * de un seguro, que se parsea y se crea un objeto de la clase Seguro. Los seguros se agregan a la lista de seguros.
         *
         * @throws IOException Si ocurre un error al leer el archivo de seguros, se lanza una excepción de tipo IOException.
         */
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
        /**
         * Carga la información de las licencias desde un archivo de texto. Cada línea del archivo contiene la información
         * de una licencia, que se parsea y se crea un objeto de la clase Licencia. Las licencias se agregan a la lista de licencias de usuarios.
         *
         * @throws IOException Si ocurre un error al leer el archivo de licencias, se lanza una excepción de tipo IOException.
         */
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
        /**
         * Carga la información de las licencias desde un archivo de texto. Cada línea del archivo contiene la información
         * de una licencia, que se parsea y se crea un objeto de la clase Licencia. Las licencias se agregan a la lista de licencias de usuarios.
         *
         * @throws IOException Si ocurre un error al leer el archivo de licencias, se lanza una excepción de tipo IOException.
         */
        int contador=0;
        try (BufferedReader br = new BufferedReader(new FileReader("./data/clientes.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
            if (partes.length == 10) {
            String login= partes[0];
            //CHECK

            String password= partes[1];
            int numeroCedula= Integer.parseInt(partes[2]);
            Usuario.addNombreLogin(login);
            Usuario.addNumCedulas(numeroCedula);
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
        /**
         * Carga la información de eventos desde un archivo de texto. Cada línea del archivo contiene la información
         * de un evento, que se parsea y se crea un objeto de la clase Evento. Los eventos se agregan a la lista de eventos.
         *
         * @throws IOException Si ocurre un error al leer el archivo de eventos, se lanza una excepción de tipo IOException.
         */
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
        /**
         * Carga la información de eventos desde un archivo de texto. Cada línea del archivo contiene la información
         * de un evento, que se parsea y se crea un objeto de la clase Evento. Los eventos se agregan a la lista de eventos.
         *
         * @throws IOException Si ocurre un error al leer el archivo de eventos, se lanza una excepción de tipo IOException.
         */
        int contador=0;
        try (BufferedReader br = new BufferedReader(new FileReader("./data/reservas.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
            if (partes.length == 11) {
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
            double pagoReserva=Double.parseDouble(partes[10]);
            Cliente cliente= Usuario.assignCliente(cedula_cliente);
            Reserva reservaActual= new Reserva(idReserva, fechaRecoger, fechaEntregar, horaRecoger, horaEntregar, reservaEnSede, sedeRecoger, sedeEntregar, categoria, cliente);
            reservaActual.setPagoReserva(pagoReserva);
            Reserva.addReserva(reservaActual);
            contador+=1;
            }else{System.out.println("Formato incorrecto en la línea: " + linea);}
            }  
            System.out.println("> " +Integer.toString(contador)+ " reservas cargadas.");

        }
        catch (IOException e) {e.printStackTrace();}
    }
    private static void loadAlquileres(){
        /**
         * Carga la información de alquileres desde un archivo de texto. Cada línea del archivo contiene la información de un alquiler,
         * que se parsea y se crea un objeto de la clase Alquiler. Los alquileres se agregan a la lista de alquileres.
         *
         * @throws IOException Si ocurre un error al leer el archivo de alquileres, se lanza una excepción de tipo IOException.
         */
        int contador=0;
        try (BufferedReader br = new BufferedReader(new FileReader("./data/alquileres.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
            if (partes.length == 7) {
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
                PagoExcedente i_PagoExcedente= new PagoExcedente(i_partes[0],Double.parseDouble(i_partes[1]));
                alquilerActual.addPagoExcedente(i_PagoExcedente);
            }}
            boolean activo= Boolean.parseBoolean(partes[6]);
            alquilerActual.setActivo(activo);
            alquiler.addAlquiler(alquilerActual);
            contador+=1;
            }else{System.out.println("Formato incorrecto en la línea: " + linea);}
            }  
            System.out.println("> " + Integer.toString(contador)+ " alquileres cargados.");

        }
        catch (IOException e) {e.printStackTrace();}
    }

    private static void loadVehiculos(){
        /**
         * Carga la información de vehículos desde un archivo de texto. Cada línea del archivo contiene la información de un vehículo,
         * que se parsea y se crea un objeto de la clase Vehiculo. Los vehículos se agregan a la lista de vehículos. Además, se asocian
         * eventos, alquileres y reservas activas a los vehículos correspondientes.
         */
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
                    String stringIDsAlquileres=partes[11].substring(1, partes[11].length() - 1);
                    String stringIDsReservasActivas=partes[10].substring(1, partes[10].length() - 1);

                    if (!stringIDsEventos.equals("")){
                    String [] listaIDsEventos=stringIDsEventos.split(",");
                    for (String i: listaIDsEventos){
                        Evento i_evento= Inventario.assignEvento(Integer.parseInt(i));
                        if (i_evento!=null){
                        vehiculoActual.addEvento(i_evento);
                        contadorEventos+=1;}
                        }}
                    if (!stringIDsAlquileres.equals("")){
                    String [] listaIDsAlquileres=stringIDsAlquileres.split(",");
                    for (String i: listaIDsAlquileres){
                        alquiler i_alquiler=  alquiler.assignAlquiler(Integer.parseInt(i));
                        if (i_alquiler!=null){
                        vehiculoActual.addAlquiler(i_alquiler);                       
                        contadorAlquileres+=1;}
                    }}
                    if (!stringIDsReservasActivas.equals("")){
                    String [] listaIDsReservasActivas=stringIDsReservasActivas.split(",");
                    for (String i: listaIDsReservasActivas){
                        Reserva i_Reserva=Reserva.assignReserva(Integer.parseInt(i));
                        if (i_Reserva!=null){
                        vehiculoActual.addReservaActiva(i_Reserva);
                        i_Reserva.setVehiculoAsignado(vehiculoActual);
                        contadorReservasActivas+=1;}
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
    public static Categoria assignCategoria(int id_categoria){
        /**
         * Busca y devuelve una instancia de la clase Categoria que coincide con el ID especificado.
         *
         * @param id_categoria El ID de la categoría que se desea encontrar.
         * @return La instancia de la categoría con el ID especificado, o null si no se encuentra ninguna coincidencia.
         */
        Categoria retorno = null;
        for(Categoria i: Inventario.getListaCategorias()){
            if(i.getID()==id_categoria){
            retorno= i;
            }}
        return retorno;
    }
    public static Seguro assignSeguro(int id_seguro){
        /**
         * Busca y devuelve una instancia de la clase Seguro que coincide con el ID especificado.
         *
         * @param id_seguro El ID del seguro que se desea encontrar.
         * @return La instancia del seguro con el ID especificado, o null si no se encuentra ninguna coincidencia.
         */
        Seguro retorno = null;
        for(Seguro i: Inventario.getListaSeguros()){
            if(i.getID()==id_seguro){
            retorno= i;
            }}
        return retorno;
    } 
    public static Sede assignSede(int id_sede){
        /**
         * Busca y devuelve una instancia de la clase Sede que coincide con el ID especificado.
         *
         * @param id_sede El ID de la sede que se desea encontrar.
         * @return La instancia de la sede con el ID especificado, o null si no se encuentra ninguna coincidencia.
         */
        Sede retorno = null;
        for(Sede i: Inventario.getListaSedes()){
            if(i.getID()==id_sede){
            retorno= i;
            break;
            }}
        return retorno;
    } 
    public static Evento assignEvento(int id_evento){
        /**
         * Busca y devuelve una instancia de la clase Evento que coincide con el ID especificado.
         *
         * @param id_evento El ID del evento que se desea encontrar.
         * @return La instancia del evento con el ID especificado, o null si no se encuentra ninguna coincidencia.
         */
        Evento retorno = null;
        for(Evento i: Inventario.getListaEventos()){
            if(i.getID()==id_evento){
            retorno= i;
            break;
            }}
        return retorno;
    }

    public static Cliente assignCliente(int cedula_cliente){
        /**
         * Busca y devuelve una instancia de la clase Cliente que coincide con el número de cédula especificado.
         *
         * @param cedula_cliente El número de cédula del cliente que se desea encontrar.
         * @return La instancia del cliente con el número de cédula especificado, o null si no se encuentra ninguna coincidencia.
         */
        Cliente retorno = null;
        for(Cliente i: Usuario.getListaClientes()){
            if(i.getNumeroCedula()==cedula_cliente){
            retorno= i;
            break;
            }}
        return retorno;
    } 
    public static Vehiculo assignVehiculo(String placa){
        /**
         * Busca y devuelve una instancia de la clase Vehiculo que coincide con la placa especificada.
         *
         * @param placa La placa del vehículo que se desea encontrar.
         * @return La instancia del vehículo con la placa especificada, o null si no se encuentra ninguna coincidencia.
         */
        Vehiculo retorno = null;
        for(Vehiculo i: Inventario.getListaVehiculos()){
            if(i.getPlaca().equals(placa)){
            retorno= i;
            break;
            }}
        return retorno;
    }
}




