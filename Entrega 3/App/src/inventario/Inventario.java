package inventario;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import alquiler.Alquiler;

public class Inventario {
    private static List<Seguro> listaSeguros;
    private static List<Sede> listaSedes;
    private static List<Vehiculo> listaVehiculos;
    private static List<Categoria> listaCategorias;
    //Los usuarios guardarlos en una lista de usuario en clase Usuario
    public void loadSistema(){
    loadCategorias();
    loadSedes();
    loadSeguros();
    loadVehiculos();
    }
    public void updateSistema(){
    //updateCategorias();
    //updateSedes();
    //updateSeguros();
    //updateVehiculos();
    }
        private void loadCategorias(){
        try (BufferedReader br = new BufferedReader(new FileReader("./data/categorias.txt"))) {
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
                    Categoria categoriaActual= new Categoria(nombreCategoria, pctg_temporadaAlta, pctg_temporadaBaja,costoAveriaLeve, costoAveriaModerada, costoAveriaTotal, tarifaDiaria);
                    if (id_Padre!=0){
                        categoriaActual.setid_Padre(id_Padre);
                    }
                    listaCategorias.add(categoriaActual);
                } else {
                    System.out.println("Formato incorrecto en la línea: " + linea);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void loadSedes(){
    //1;SedeChapinero;cra7 #70, Bogotá;[0630,1630];[0630,1230];[]
        try (BufferedReader br = new BufferedReader(new FileReader("./data/sedes.txt"))) {
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
                    List<Personal> personal= new ArrayList<Personal>();
                    Sede sedeActual= new Sede(id, nombreSede, ubicacionSede,horarioSemana,horarioFinSemana,personal);
                    sedeActual.setHorarioAtencionEnSemana(horarioSemana);
                    sedeActual.setHorarioAtencionFinSemana(horarioFinSemana);
                    listaSedes.add(id, sedeActual);
                } else {
                    System.out.println("Formato incorrecto en la línea: " + linea);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadSeguros(){
        try (BufferedReader br = new BufferedReader(new FileReader("./data/seguros.txt"))) {
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
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
    private void loadVehiculos(){
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
                    String id_categoria=partes[7];
                    String id_sede=partes[8];
                    List<Evento> historialEvento= new ArrayList<Evento>();
                    String eventos=partes[9];
                    if(eventos!="[]"){
                        eventos= eventos.substring(1, eventos.length() - 1);
                        String[] listaEventos=eventos.split(",");
                        for (String i:listaEventos){
                            String i_sinCorchetes=i.substring(1, eventos.length() - 1);
                            String[] i_partes=i_sinCorchetes.split(",");
                            int fechaInicio=Integer.parseInt(i_partes[0]);
                            int fechaFin=Integer.parseInt(i_partes[1]);
                            int horaInicio=Integer.parseInt(i_partes[2]);
                            int horaFin=Integer.parseInt(i_partes[3]);
                            String descripcion=i_partes[4];
                            Evento i_evento= new Evento(fechaInicio,fechaFin,horaInicio,horaFin,descripcion);
                            historialEvento.add(i_evento);
                        }
                    }
                    List<Alquiler> historialAlquiler= new ArrayList<Alquiler>();
                    String alquileres=partes[10];
                    if (alquileres!="[]"){
                        alquileres=alquileres.substring(1, alquileres.length() - 1);
                        String[] listaAlquileres=alquileres.split(",");
                        for (String i:listaAlquileres){
                            //TODO
                        }
                    }
                    Vehiculo vehiculoActual= new Vehiculo(placa, marca, modelo, color, alquileres, ubicacionGPS, estado, averiado, historialEvento, historialAlquiler);
                    listaVehiculos.add(vehiculoActual);
                } else {
                    System.out.println("Formato incorrecto en la línea: " + linea);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
