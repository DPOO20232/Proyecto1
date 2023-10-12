package inventario;
import java.util.List;
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
}
