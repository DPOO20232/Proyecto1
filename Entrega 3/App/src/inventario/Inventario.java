package inventario;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import alquiler.Alquiler;
import usuario.personal;
import usuario.Usuario;
public class Inventario {
    private static List<Categoria> listaCategorias;
    private static List<Sede> listaSedes;
    private static List<Seguro> listaSeguros;
    private static List<Vehiculo> listaVehiculos;
    //Los usuarios guardarlos en una lista de usuario en clase Usuario
    public static void loadSistema(){
    loadCategorias();
    loadSedes();
    loadPersonal();
    loadSeguros();
    loadVehiculos();
    }
    public static List<Categoria> getListaCategorias(){
        return listaCategorias;}
    public static List<Seguro> getListaSeguros(){
        return listaSeguros;}
    public static List<Sede> getListaSedes(){
        return listaSedes;}
    public static List<Vehiculo> getListaVehiculos(){
        return listaVehiculos;}
    public static void updateSistema(){
    //updateCategorias();
    //updateSedes();
    //updateSeguros();
    //updateVehiculos();
    }
        private static void loadCategorias(){
        try (BufferedReader br = new BufferedReader(new FileReader("./data/categorias.txt"))) {
            listaCategorias= new ArrayList<Categoria>();
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length == 9) {
                    int id = Integer.parseInt(partes[0]);
                    String nombreCategoria=partes[1];
                    double pctg_temporadaAlta=Double.parseDouble(partes[2]);
                    double pctg_temporadaBaja=Double.parseDouble(partes[3]);
                    int costoAveriaLeve=Integer.parseInt(partes[4]);
                    int costoAveriaModerada=Integer.parseInt(partes[5]);
                    int costoAveriaTotal= Integer.parseInt(partes[6]);
                    int tarifaDiaria= Integer.parseInt(partes[7]);
                    int id_Padre= Integer.parseInt(partes[8]);
                    Categoria categoriaActual= new Categoria(id,nombreCategoria, pctg_temporadaAlta, pctg_temporadaBaja,costoAveriaLeve, costoAveriaModerada, costoAveriaTotal, tarifaDiaria,id_Padre);
                    if (id_Padre!=0){
                        for(Categoria i: listaCategorias){
                            if (id_Padre==i.getID()){
                                categoriaActual.setPadre(i);
                                break;
                            }
                        }
                    }
                    listaCategorias.add(categoriaActual);
                
                }
            }
            System.out.println(">>> "+listaCategorias.size()+" categorías cargadas.");
        } catch (IOException e) {
            e.printStackTrace();
        }
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

        } catch (IOException e) {
            e.printStackTrace();
        }
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
                    Sede i_sede=null;
                    String tipoUsuario=partes[3];
                    
                    if (id_sede>0){
                        for (Sede i: listaSedes){
                        if (i.getID()==id_sede){
                            i_sede=i;
                            personal personalActual= new personal(login, password, tipoUsuario, i_sede);
                            i.addPersonalSede(personalActual);
                            contador+=1;
                            break;
                        }}}
                    else{
                        Usuario adminActual=new Usuario(login, password);
                        personal.setAdmin(adminActual);
                        contador+=1;
                    }
                    
                }
            }
            System.out.println(">>> "+Integer.toString(contador)+" perfiles de personal cargados.");
            } catch (IOException e) {
            e.printStackTrace();
        }
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
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
    private static void loadVehiculos(){
        try (BufferedReader br = new BufferedReader(new FileReader("./data/vehiculos.txt"))) {
            //PBM158;chevrolet;aveo;gris;manual;disponible;false;1;1;[[20240321,20240322,0800,1200,descripcion],[],[]];[];[]
            listaVehiculos= new ArrayList<Vehiculo>();
            String linea;
            int contadorEventos=0;
            int contadorAlquileres=0;
            int contadorReservasActivas=0;
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
                    Categoria categoria=null;
                    for(Categoria i: listaCategorias){
                        if(i.getID()==id_categoria){
                        categoria=i;
                        break;
                        }
                    }
                    int id_sede=Integer.parseInt(partes[8]);
                    Sede sede=null;
                    for(Sede i: listaSedes){
                        if(i.getID()==id_sede){
                        sede=i;
                        break;
                        }
                    }
                    Vehiculo vehiculoActual= new Vehiculo(placa, marca, modelo, color, tipo_trasmicion, ubicacionGPS, estado, averiado,categoria,sede);
                    listaVehiculos.add(vehiculoActual);
                    String stringEventos=partes[9].substring(1, partes[9].length() - 1);
                    String [] listaEventos=stringEventos.split(",");
                    String stringAlquileres=partes[10].substring(1, partes[10].length() - 1);
                    String [] listaAlquileres=stringAlquileres.split(",");
                    String stringReservasActivas=partes[11].substring(1, partes[11].length() - 1);
                    String [] listaReservasActivas=stringReservasActivas.split(",");
                    //[[20240321;20240322;0800;1200;descripcion],[],[]]
                    if (stringEventos!=""){
                    for (String i: listaEventos){
                        String i_substring=i.substring(1,i.length()-1);
                        String [] i_partes=i_substring.split(";");
                        if(i_partes.length==5){
                        int i_fechaInicio=Integer.parseInt(i_partes[0]);
                        int i_fechaFin=Integer.parseInt(i_partes[1]);
                        int i_horaInicio=Integer.parseInt(i_partes[2]);
                        int i_horaFin=Integer.parseInt(i_partes[3]);
                        String descripcion= i_partes[4];
                        Evento i_evento=new Evento(i_fechaInicio, i_fechaFin, i_horaInicio, i_horaFin, descripcion);
                        contadorEventos+=1;
                        vehiculoActual.addEvento(i_evento);
                        }}}
                    if (stringAlquileres!=""){
                    for (String i: listaAlquileres){
                        String i_subString=i.substring(1, i.length()-1);
                    }
                        
                    }
                    if (stringReservasActivas!=""){
                    for (String i: listaReservasActivas){
                        String i_substring=i.substring(1,i.length()-1);

                    }
                } else {
                    System.out.println("Formato incorrecto en la línea: " + linea);
                }
            }
        System.out.println(">>> "+listaVehiculos.size()+" vehículos cargados.("+ Integer.toString(contadorEventos)+" eventos encontrados, "+Integer.toString(contadorAlquileres)+" alquileres registrados, "+Integer.toString(contadorReservasActivas)+" reservas activas.)");
        }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
