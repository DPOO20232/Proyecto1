package controlador;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import modelo.Categoria;
import modelo.Evento;
import modelo.Inventario;
import modelo.Reserva;
import modelo.Sede;
import modelo.Seguro;
import modelo.Vehiculo;

public class MenuInventario {
    public static void statusVehiculo(){
            String placa = input("Ingrese la placa del vehículo que desee consultar");
            Vehiculo vehiculo= Inventario.assignVehiculo(placa);
            if (vehiculo!=null){
            String resumen = vehiculo.resumenStatus();}                    
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
    public static void nuevoSeguro(){
        try{
        String desc = input("Ingrese una descripción del seguro");
        double pctg_tarifadiaria= Double.parseDouble(input("Ingrese el porcentaje de la tarifa diaria que el seguro cuesta (ej: 90%->0.9)"));
        Seguro seguro= new Seguro(pctg_tarifadiaria,desc );
        Inventario.setListaSeguros(seguro);
        System.out.println(">El nuevo seguro se guardo con el id "+ Integer.toString(seguro.getID()));
        }
    catch(NumberFormatException e){
        System.out.println(">Ingrese solo números en los campos correspondientes");}}
    
    public static void editarSeguro(){
    try{
        boolean continuar=true;
        while(continuar){
            int id_seguro= Integer.parseInt(input("Ingrese el ID del seguro que desea modificar"));
            if ((id_seguro>0) &&(id_seguro<=Inventario.getListaSeguros().size())){
                System.out.println("\nDesea editar el porcentaje de tarifa diaria?\n");
                System.out.println("1.Sí");
                System.out.println("2.No(ó cualquier otro número)");
                int opcion_pctg = Integer.parseInt(input("Por favor seleccione una opción"));    
                if (opcion_pctg==1){
                    double pctg=Double.parseDouble(input("Ingrese el nuevo porcentage de tarifa diaria designado al seguro"));
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
                continuar=false;
            }
            else{System.out.println(">Ingrese el id de un seguro válido ");}}}
    catch(NumberFormatException e){System.out.println(">Ingrese solo números en los campos correspondientes");}}
    
    public static void eliminarSeguro(){
        try{
        int id = Integer.parseInt(input("Ingrese el ID del seguro")); 
        boolean encontrado=false;
        for (Seguro i: Inventario.getListaSeguros()){
            if (i.getID()==id){
                Inventario.getListaSeguros().remove(i);
                encontrado=true;
                break;}}
        if (encontrado==true){System.out.println(">Se eliminó del inventario el seguro con ID "+Integer.toString(id)+".");}
        else{System.out.println(">No se halló ningun vehículo con la placa "+Integer.toString(id)+".");}
            }
        catch(NumberFormatException e){System.out.println("Ingrese solo números en los campos correspondientes");}}
    
    public static void nuevaSede(){
    try{
    String nombreSede = input("Ingrese el nombre de la nueva sede");
    String ubicacion = input("Ingrese la ubicación de la nueva sede");
    List<Integer> horarioSemana= new ArrayList<Integer>();
    List<Integer> horarioFinSemana= new ArrayList<Integer>();
    int horaAperturaSemana= Integer.parseInt(input("Ingrese la hora de apertura entre semana en formato hhmm"));
    int horaCierreSemana= Integer.parseInt(input("Ingrese la hora de cierre entre semana en formato hhmm"));    
    int horaAperturaFinSemana= Integer.parseInt(input("Ingrese la hora de apertura para fin de semana en formato hhmm"));
    int horaCierreFinSemana= Integer.parseInt(input("Ingrese la hora de cierre para fin de semana en formato hhmm")); 
    horarioSemana.add(horaAperturaSemana);
    horarioSemana.add(horaCierreSemana);
    horarioFinSemana.add(horaAperturaFinSemana);
    horarioFinSemana.add(horaCierreFinSemana);
    Sede sede= new Sede(nombreSede, ubicacion, horarioSemana, horarioFinSemana);
    Inventario.setListaSedes(sede);
    System.out.println(">La nueva sede se guardo con el id "+ Integer.toString(sede.getID()));
    }
    catch(NumberFormatException e){System.out.println(">Ingrese solo números en los campos correspondientes");}}

    public static void editarSede(){
    //SedeSur;cra58 #2, Bogotá;[0730,1430];[0730,1530];[]
    try{
    for (Sede i:Inventario.getListaSedes()){i.printInfo();}
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
        boolean hvalida1= Reserva.horaValida(hapertura);
        boolean hvalida2= Reserva.horaValida(hcierre);
        if (hvalida1&&hvalida2){
            horario.add(hapertura);
            horario.add(hcierre);
            Inventario.assignSede(id_sede).setHorarioAtencionEnSemana(horario);
}
        else{System.out.println("\n>No se pudieron modificar los horarios dado que no se ingresaron valores válidos.");}

    }
    System.out.println("\nDesea modificar el horario de fin de semana de la sede?\n");
    System.out.println("1.Sí");
    System.out.println("2.No(ó cualquier otro número)");
    int opcion_hFinsemana = Integer.parseInt(input("Por favor seleccione una opción"));   
    if (opcion_hFinsemana==1){
        List<Integer> horario= new ArrayList<Integer>();
        int hapertura=Integer.parseInt(input("Ingrese la nueva hora de entrada"));
        int hcierre=Integer.parseInt(input("Ingrese la nueva hora de cierre"));
        boolean hvalida1= Reserva.horaValida(hapertura);
        boolean hvalida2= Reserva.horaValida(hcierre);
        if (hvalida1&&hvalida2){
            horario.add(hapertura);
            horario.add(hcierre);
            Inventario.assignSede(id_sede).setHorarioAtencionFinSemana(horario);}
        else{System.out.println("\n>No se pudieron modificar los horarios dado que no se ingresaron valores válidos.");}
        
    }
    System.out.println("\n> Información actualizada.");
 
    }
    else{System.out.println("Ingrese el id de un seguro válido ");}}

    catch(NumberFormatException e){
        System.out.println("Ingrese solo números en los campos correspondientes");}}
    
    public static void editarCostoPorConductorAdicional(){
            try{
                int costo= Integer.parseInt(input("Ingrese el nuevo costo diario por conductor adicional"));
                Inventario.setCostoPorConductorAdicional(costo);
                System.out.println(">>> Costo actualizado"); 
                }catch(NumberFormatException e){System.out.println("Ingrese solo números en los campos correspondientes");}
        }
        public static void editarCostoPorTrasladoSedes(){        
            try{
                int costo= Integer.parseInt(input("Ingrese el nuevo costo por traslado de un vehículo entre sedes tras alquiler"));
                Inventario.setCostoPorTrasladoSedes(costo);
                System.out.println(">>> Costo actualizado"); 
                }catch(NumberFormatException e){System.out.println("Ingrese solo números en los campos correspondientes");}
        }
        public static void editarPeriodoTemporadaAlta(){
            int fechaInicio= Integer.parseInt(input("Intrese el nuevo inicio de temporada alta en formato mmdd(Ej: 31 de marzo->0331)"));
            int fechaFin= Integer.parseInt(input("Intrese el nuevo cierre de temporada alta en formato mmdd(Ej: 31 de marzo->0331)"));
            List<Integer> periodo=Inventario.getPeriodoTemporadaAlta();
                periodo.clear();
                periodo.add(fechaInicio);
                periodo.add(fechaFin);
                System.out.println(">>> Periodo actualizado");}
    
        public static void editarPeriodoTemporadaBaja(){
            int fechaInicio= Integer.parseInt(input("Intrese el nuevo inicio de temporada baja en formato mmdd(Ej: 31 de marzo->0331)"));
            int fechaFin= Integer.parseInt(input("Intrese el nuevo cierre de temporada baja en formato mmdd(Ej: 31 de marzo->0331)"));
            List<Integer> periodo=Inventario.getPeriodoTemporadaBaja();
                periodo.clear();
                periodo.add(fechaInicio);
                periodo.add(fechaFin);
                System.out.println(">>> Periodo actualizado");}

        public static void crearEvento() {

            try { 
            String descripcion="";
            Vehiculo vehiculo=null;
            String placa = input("\nIngrese la placa del vehículo al que le asignará un nuevo estado");
            System.out.println("Ingrese que se va a realizar en el vehículo\n");
            System.out.println("1. Lavado");
            System.out.println("2. Mantenimiento");
            int opcion = Integer.parseInt(input("Por favor seleccione una opción"));
            if (opcion==1){descripcion="EnLimpieza";}
            else if (opcion==2){descripcion="EnMantenimiento";}
            vehiculo=Inventario.assignVehiculo(placa);
            if((vehiculo!=null)&&(!descripcion.equals(""))){
            boolean continuar=true;
            while(continuar){
                int finicio = Integer.parseInt(input("Por favor ingrese la fecha en la que tendrá inicio el evento(en formato aaaammdd)"));
                int ffinal = Integer.parseInt(input("Por favor ingrese la fecha en la que finalizará el evento(en formato aaaammdd)"));
                int hinicio = Integer.parseInt(input("Ingrese la hora en la que tendrá inicio el evento(en formato 24h de tipo hhmm)"));
                int hfinal = Integer.parseInt(input("Ingrese la hora en la que finalizará el evento(en formato 24h de tipo hhmm)"));
                boolean horaVinicio= Reserva.horaValida(hinicio);
                boolean horaVfin = Reserva.horaValida(hfinal);
                int fechaActual= Integer.parseInt(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")));
                if(horaVinicio&&horaVfin&&finicio>=fechaActual){
                    Evento evento = new Evento(finicio, ffinal, hinicio, hfinal, descripcion);
                    vehiculo.addEvento(evento);
                    continuar=false;
                } else {
                    System.out.println(">Las horas ingresadas no son válidas. Desea reintentar?");
                    System.out.println("1.Si");
                    System.out.println("2. No(o cualquier otro número)");
                    int opcion2 =Integer.parseInt(input("Porfavor elija una opción"));
                    if (opcion2!=1){
                        continuar=false;
                    }
                }
            }}
            else{System.out.println("\n>Ingrese una placa de vehículo y/o una opción de nuevo estado del vehículo válida.");}
        }catch(NumberFormatException e){System.out.println("\n>Ingrese los datos requeridos en el formato especificado.");}
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
