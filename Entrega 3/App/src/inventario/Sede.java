package inventario;
import java.util.List;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import usuario.personal;;
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
        return this.idSede;
    }
    public String getNombre() {
        return this.nombre;
    }

    public String getUbicacion() {
        return this.ubicacion;
    }

    public List<Integer> getHorarioAtencionEnSemana() {
        return this.horarioAtencionEnSemana;
    }

    public List<Integer> getHorarioAtencionFinSemana() {
        return this.horarioAtencionFinSemana;
    }

    public List<personal> getPersonalSede() {
        return this.personalSede;}
    public personal getAdminLocal(){
        return this.AdminLocal;
    }
    // Métodos setters
    public void setID(){
        this.idSede=lastId+=1;
        lastId=this.getID();
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public void setHorarioAtencionEnSemana(List<Integer> horario) {
        this.horarioAtencionEnSemana = horario;
    }

    public void setHorarioAtencionFinSemana(List<Integer> horario) {
        this.horarioAtencionFinSemana = horario;
    }
    public  void setAdminLocal(personal personal){
        this.AdminLocal=personal;
    }

    public void addPersonalSede(personal personal) {
       this.personalSede.add(personal);
    }
    public void printInfo(){
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
