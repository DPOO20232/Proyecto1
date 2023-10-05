package inventario;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Inventario {
    private static List<Seguro> seguros;
    private static List<Vehiculo> listaVehiculos;
    //Los usuarios guardarlos en una lista de usuario en clase Usuario
    public void loadSistema(){
    loadCategorias();
    loadSedes();
    loadSeguros();
    loadVehiculos();
    }
    public void updateSistema(){
    updateCategorias();
    updateSedes();
    updateSeguros();
    updateVehiculos();
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
                    seguros.add(seguroActual);
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
                if (partes.length == 3) {
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
                    List<Evento> historialEvento= new ArrayList<Evento>;
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

                        }
                    }

                    int id = Integer.parseInt(partes[0]);
                    int pctg_tarifadiaria = Integer.parseInt(partes[1]);
                    String descripcion=partes[2];
                    Seguro seguroActual= new Seguro(id,pctg_tarifadiaria,descripcion);
                    seguros.add(seguroActual);
                } else {
                    System.out.println("Formato incorrecto en la línea: " + linea);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void loadCategorias(){

    }
    private void loadSedes(){}
}
