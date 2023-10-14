package inventario;

public class Evento {
    
    private int idEvento;
    private int fechaInicio;
    private int fechaFin;
    private int horaInicio;
    private int horaFin;
    private String descripcion;
    private static int lastId;
    public Evento(int id,int fechaInicio, int fechaFin, int horaInicio, int horaFin, String descripcion) {
        this.idEvento=id;
        if (idEvento>lastId){lastId=idEvento;}
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.descripcion = descripcion;
    }

    public Evento(int fechaInicio, int fechaFin, int horaInicio, int horaFin, String descripcion) {
        this.setID();
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.descripcion = descripcion;
    }
    public int getID(){
        return this.idEvento;
    }
    public int getFechaInicio() {
        return this.fechaInicio;
    }

    public int getFechaFin() {
        return this.fechaFin;
    }

    public int getHoraInicio() {
        return this.horaInicio;
    }

    public int getHoraFin() {
        return this.horaFin;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setID(){
        this.idEvento=lastId+=1;
        lastId=this.getID();
    }
    public void setFechaInicio(int fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
    public void setFechaFin(int fechaFin) {
        this.fechaFin = fechaFin;
    }

    public void setHoraInicio(int horaInicio) {
        this.horaInicio = horaInicio;
    }

    public void setHoraFin(int horaFin) {
        this.horaFin = horaFin;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public static crearEvento(String descripcion, String placa, List<Vehiculo> vehiculos) {
        System.out.println("\n¡Bienvenido a nuestro sistema de reservas!\n");
        try { 
            boolean continuar=true;
            while(continuar){
                int finicio = Integer.parseInt(input("Por favor ingrese la fecha en la que tendrá inicio el evento(en formato aaaammdd)"));
                int ffinal = Integer.parseInt(input("Por favor ingrese la fecha en la que finalizará el evento(en formato aaaammdd)"));
                int hinicio = Integer.parseInt(input("Ingrese la hora en la que tendrá inicio el evento(en formato hhmm)"));
                int hfinal = Integer.parseInt(input("Ingrese la hora en la que finalizará el evento(en formato hhmm)"));
                boolean horaVinicio= Reserva.horaValida(hinicio);
                boolean horaVfin = Reserva.horaValida(hfinal);
                if(horaVinicio&&horaVfin){
                    Evento evento = new Evento(finicio, ffinal, hinicio, hfinal, descripcion)
                    for (Vehiculo vehiculo : vehiculos) {
                        if (vehiculo.getPlaca().equals(placa)) {
                            vehiculo.addEvento(evento)
                        } else {System.out.println(">No se pudo encontrar el vehículo que buscas.");}
                    }
                } else {System.out.println(">Las horas ingresadas no son válidas. Por favor, inténtelo nuevamente.");}
            }
        }
    }

}
