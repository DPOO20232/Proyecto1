package modelo;
import java.util.List;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;;
public class Sede {
    private int idSede;
    private String nombre;
    private String ubicacion;
    private List<Integer> horarioAtencionEnSemana;
    private List<Integer> horarioAtencionFinSemana;
    private List<personal> personalSede;
    private personal AdminLocal;
    private static int lastId;
    // Constructor
    public Sede(int idSede,String nombre, String ubicacion,List<Integer> horario1, List<Integer> horario2) {
        /**
         * Constructor de la clase Sede.
         * @param idSede El identificador único de la sede.
         * @param nombre El nombre de la sede.
         * @param ubicación La ubicación de la sede.
         * @param horario1 Lista de horarios de atención en días de semana.
         * @param horario2 Lista de horarios de atención en fin de semana.
         */
        this.idSede=idSede;
        if (idSede>lastId){lastId=idSede;}
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.horarioAtencionEnSemana=horario1;
        this.horarioAtencionFinSemana=horario2;
        this.personalSede=new ArrayList<personal>();
        this.AdminLocal=null;
    }
    public Sede(String nombre, String ubicacion,List<Integer> horario1, List<Integer> horario2) {
        /**
         * Constructor de la clase Sede.
         * @param nombre El nombre de la sede.
         * @param ubicacion La ubicación de la sede.
         * @param horario1 Lista de horarios de atención en días de semana.
         * @param horario2 Lista de horarios de atención en fin de semana.
         */
        this.setID();
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.horarioAtencionEnSemana=horario1;
        this.horarioAtencionFinSemana=horario2;
        this.personalSede=new ArrayList<personal>();
        this.AdminLocal=null;
    }
    // Métodos getters
    public int getID(){
        /**
         * Obtiene el ID de la sede.
         * @return El ID de la sede.
         */
        return this.idSede;
    }
    public String getNombre() {
        /**
         * Obtiene el nombre de la sede.
         * @return El nombre de la sede.
         */
        return this.nombre;
    }

    public String getUbicacion() {
        /**
         * Obtiene la ubicación de la sede.
         * @return La ubicación de la sede.
         */
        return this.ubicacion;
    }

    public List<Integer> getHorarioAtencionEnSemana() {
        /**
         * Obtiene la lista de horarios de atención en días de semana.
         * @return La lista de horarios de atención en días de semana.
         */
        return this.horarioAtencionEnSemana;
    }

    public List<Integer> getHorarioAtencionFinSemana() {
        /**
         * Obtiene la lista de horarios de atención en días de semana.
         * @return La lista de horarios de atención en días de semana.
         */
        return this.horarioAtencionFinSemana;
    }

    public List<personal> getPersonalSede() {
        /**
         * Obtiene la lista de personal asignado a la sede.
         * @return La lista de personal asignado a la sede.
         */
        return this.personalSede;}
    public personal getAdminLocal(){
        /**
         * Obtiene el administrador local de la sede.
         * @return El administrador local de la sede.
         */
        return this.AdminLocal;
    }
    // Métodos setters
    public void setID(){
        /**
         * Establece el ID de la sede.
         */
        this.idSede=lastId+=1;
        lastId=this.getID();
    }
    public void setNombre(String nombre) {
        /**
         * Establece el nombre de la sede.
         * @param nombre El nombre de la sede.
         */
        this.nombre = nombre;
    }

    public void setUbicacion(String ubicacion) {
        /**
         * Establece el nombre de la sede.
         * @param nombre El nombre de la sede.
         */
        this.ubicacion = ubicacion;
    }

    public void setHorarioAtencionEnSemana(List<Integer> horario) {
        /**
         * Establece los horarios de atención en días de semana.
         * @param horario Los horarios de atención en días de semana.
         */
        this.horarioAtencionEnSemana = horario;
    }

    public void setHorarioAtencionFinSemana(List<Integer> horario) {
        /**
         * Establece los horarios de atención en fin de semana.
         * @param horario Los horarios de atención en fin de semana.
         */
        this.horarioAtencionFinSemana = horario;
    }
    public  void setAdminLocal(personal personal){
        /**
         * Establece el administrador local de la sede.
         * @param personal El administrador local de la sede.
         */
        this.AdminLocal=personal;
    }

    public void addPersonalSede(personal personal) {
        /**
         * Agrega un miembro del personal a la sede.
         * @param personal El miembro del personal a agregar.
         */
       this.personalSede.add(personal);
    }
    public void printInfo(){
        /**
         * Imprime la información de la sede, incluyendo nombre, ubicación y horarios de atención.
         */
    List <Integer> horario1= this.getHorarioAtencionEnSemana();
    List <Integer> horario2= this.getHorarioAtencionFinSemana();
    String snum1=Integer.toString((horario1.get(0))/100);
    String snum2=Integer.toString((horario1.get(0))%100);
    String snum3=Integer.toString((horario1.get(1))/100);
    String snum4=Integer.toString((horario1.get(1))%100);
    String snum5=Integer.toString((horario2.get(0))/100);
    String snum6=Integer.toString((horario2.get(0))%100);
    String snum7=Integer.toString((horario2.get(1))/100);
    String snum8=Integer.toString((horario2.get(1))%100);
    if(snum2.equals("0")){snum2="00";}
    if(snum4.equals("0")){snum4="00";}
    if(snum6.equals("0")){snum6="00";}
    if(snum8.equals("0")){snum8="00";}
    String horarioAtencion1="Hora de apertura de lunes a viernes: "+snum1+":"+snum2+" - "+snum3+":"+snum4+".";
    String horarioAtencion2="Hora de apertura de sabado a domingo: "+snum5+":"+snum6+" - "+snum7+":"+snum8+".";
    
    System.out.println("Información Sede: "+this.getNombre()+" ("+this.getUbicacion()+").");
    System.out.println(horarioAtencion1+" "+horarioAtencion2+"\n");
    }
    public boolean estaAbierta(int fecha, int hora){
     /**
     * Verifica si la sede está abierta en una fecha y hora específicas.
     *
     * @param fecha La fecha en formato "yyyyMMdd" (por ejemplo, 20230101 para el 1 de enero de 2023).
     * @param hora La hora en formato "HHmm" (por ejemplo, 0800 para las 8:00 AM).
     * @return `true` si la sede está abierta en la fecha y hora especificadas, `false` en caso contrario.
     * @throws DateTimeParseException Si el formato de la fecha o la hora es incorrecto.
     */
    boolean retorno=false;
    try{
    DateTimeFormatter formatter =DateTimeFormatter.ofPattern("yyyyMMddHHmm");
    LocalDateTime fecha_u = LocalDateTime.parse(String.format("%08d%04d", fecha, hora), formatter);
    DayOfWeek diaSemana = fecha_u.getDayOfWeek();
    boolean esDiaSemana = ((diaSemana != DayOfWeek.SATURDAY) && (diaSemana != DayOfWeek.SUNDAY));
    if (esDiaSemana){
        List <Integer> horario1= this.getHorarioAtencionEnSemana();
        if(horario1.get(0) <= hora && hora <= horario1.get(1)){
            retorno=true;
        }}
    else{
        List <Integer> horario1= this.getHorarioAtencionFinSemana();
        if(horario1.get(0) <= hora && hora <= horario1.get(1)){
            retorno=true;
    }
    }
        return retorno;
    }catch(DateTimeParseException  e){System.out.println("\n>Ingrese las fechas y horas en el formato solicitado.");
    return retorno;}
}
}
