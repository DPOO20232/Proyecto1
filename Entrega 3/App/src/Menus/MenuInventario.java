package Menus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import inventario.Categoria;
import inventario.Inventario;
import inventario.Sede;
import inventario.Vehiculo;

public class MenuInventario {
    public static void statusVehiculo(){
            String placa = input("Ingrese la placa del vehículo que desee consultar");
            Vehiculo vehiculo= Inventario.assignVehiculo(placa);
            if (vehiculo!=null){
            vehiculo.resumenStatus();}                    
            else{
            System.out.println("\n>La placa ingresada no corresponde a ningún vehículo del inventario.\n");
            }
            }
    public static void logVehiculo(){
            String placa = input("Ingrese la placa del vehículo que desee consultar");
            Vehiculo vehiculo= Inventario.assignVehiculo(placa);
            if (vehiculo!=null){
            vehiculo.obtenerLog();}                    
            else{
            System.out.println("\n>La placa ingresada no corresponde a ningún vehículo del inventario.\n");
            }  
    }

    public static void crearCategoria(){
       
        try {
        boolean continuar=true;
        String nombre= input("Ingrese el nombre de la Categoria del Vehiculo: ");
        int capacidadPersonas = Integer.parseInt(input("Ingrese la capacidad de personas del Vehiculo"));
        double pctg_temporadaAlta= Double.parseDouble(input("Ingrese el porcentaje de incremento para la categoria en temporada alta"));
        double pctg_temporadaBaja= Double.parseDouble(input("Ingrese el porcentaje de descuento para la categoria en temporada baja"));
        int costoAveriaLeve = Integer.parseInt(input("Ingrese el costo de averia leve del Vehiculo"));
        int costoAveriaModerado = Integer.parseInt(input("Ingrese el costo de averia moderado del Vehiculo"));            
        int costoAveriaTotal = Integer.parseInt(input("Ingrese el costo de averia Total del Vehiculo"));
        int TarifaDiaria = Integer.parseInt(input("Ingrese el costo de tarifa diaria del Vehiculo"));
        while(continuar){
            int IdPadre = Integer.parseInt(input("Ingrese el Id padre del Vehiculo (En caso de que no tenga marque 0)"));
            if (((IdPadre>=0) &&(IdPadre<=Inventario.getListaCategorias().size()))){
            Categoria categoria = new Categoria(nombre, capacidadPersonas, pctg_temporadaAlta, pctg_temporadaBaja, costoAveriaLeve, costoAveriaModerado, costoAveriaTotal, TarifaDiaria, IdPadre);
            categoria.setPadre(Inventario.assignCategoria(IdPadre));
            System.out.println(">La nueva categoria se guardo con el id"+ Integer.toString(categoria.getID()));

            Inventario.getListaCategorias().add(categoria);
            continuar=false;}
            else{System.out.println(">Elija un ID padre del Vehículo válido");}
        }}
        catch(NumberFormatException e){
            System.out.println(">Ingrese solo números en los campos correspondientes");}
        }
    
    public static void crearVehiculo(){
        try{
        String placa= input("Ingrese la placa del Vehiculo: "); 
        boolean encontrado=false;
        for (Vehiculo i: Inventario.getListaVehiculos()){
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
        if ((IDcategoria>=1) && (IDcategoria<=Inventario.getListaCategorias().size())){
            categoria= Inventario.assignCategoria(IDcategoria);
        }
        else{
            System.out.println(">Ingresó un Id de Categoria inválido");
        }
        Sede sede=null;
        if (IDsede>=1&& IDsede<=Inventario.getListaSedes().size()){
            sede= Inventario.assignSede(IDsede);
        }
        else{
            System.out.println(">Ingresó un ID de sede inválido");
        }
        if(categoria!=null&&sede!=null){
                Vehiculo vehiculo = new Vehiculo(placa, marca, modelo, color, tipoTransmision, ubicacionGPS, estado, false, categoria, sede);
                Inventario.getListaVehiculos().add(vehiculo);
        }
        else{
            System.out.println(">La sede o categoria están vacías");
        }

        }
        else{
            System.out.println(">La placa ingresada ya existe en el inventario");
        }
        }
        catch(NumberFormatException e){
            System.out.println(">Ingrese solo números en los campos correspondientes");}
    }

    public static void eliminarVehiculo(){
        String placa = input("Ingrese la placa del vehículo"); 
        boolean encontrado=false;
        for (Vehiculo i: Inventario.getListaVehiculos()){
            if (i.getPlaca().equals(placa)){
                Inventario.getListaVehiculos().remove(i);
                encontrado=true;
                break;

            }
        }
        if (encontrado==true){
        System.out.println(">Se eliminó del inventario el vehículo con placa "+placa+".");
        }
        else{System.out.println(">No se halló ningun vehículo con la placa "+placa+".");
        }
    }
    public static void trasladoVehiculo(){
         String placa = input("Ingrese la placa del vehículo al que desee trasladar");
        Vehiculo vehiculo=Inventario.assignVehiculo(placa);
        if(vehiculo!=null){
            String sedeAct = input("Sede actual del vehículo: "+vehiculo.getSede().getNombre());
            System.out.println("\n>Lista de Sedes Disponibles:");
            List<Sede> sedes = Inventario.getListaSedes();
            for (int i = 0; i < sedes.size(); i++) {
                System.out.println((i + 1) + ". " + sedes.get(i).getNombre()+" ("+sedes.get(i).getUbicacion()+").");}
                int idSede=Integer.parseInt(input("Ingrese la opción de la sede a la que desea trasladar el vehículo"));
                if(idSede<=sedes.size()&& idSede<=sedes.size()){
                    Sede nuevaSede=Inventario.assignSede(idSede);
                    if(!nuevaSede.getNombre().equals(sedeAct)){
                    vehiculo.setTrasladoASede(nuevaSede);}
                else{
                    System.out.println("\n>Ingrese una opción de sede diferente.\n");
                    }
                }
                else{
                        System.out.println("\n>Ingrese una opción de sede válida.\n");
                    }
        }
        else{
                        System.out.println("\n>Ingrese una placa de vehículo válida.\n");

        }
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
}
