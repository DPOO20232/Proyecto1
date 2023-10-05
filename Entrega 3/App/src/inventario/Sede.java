package inventario;
import java.util.List;
import java.util.ArrayList;
import usuario.Personal;;
public class Sede {
    private int idSede;
    private String nombre;
    private String ubicacion;
    private List<Integer> horarioAtencionEnSemana;
    private List<Integer> horarioAtencionFinSemana;
    private List<Personal> personalSede;

    // Constructor
    public Sede(int idSede,String nombre, String ubicacion,List<Integer> horario1, List<Integer> horario2, List<Personal> personal) {
        this.idSede=idSede;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.horarioAtencionEnSemana=horario1;
        this.horarioAtencionFinSemana=horario2;
        this.personalSede=personal;
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

    public List<Personal> getPersonalSede() {
        return this.personalSede;}

    // Métodos setters
    public void setID(int id){
        this.idSede=id;
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

    public void addPersonalSede(Personal personal) {
       this.personalSede.add(personal);
    }
}
