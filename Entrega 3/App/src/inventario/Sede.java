package inventario;
import java.util.List;
import java.util.ArrayList;

public class Sede {
    private int idSede;
    private String nombre;
    private String ubicacion;
    private List<Integer> horarioAtencionEnSemana;
    private List<Integer> horarioAtencionFinSemana;
    //private List<Personal> personalSede; // Suponiendo que existe una clase Personal

    // Constructor
    public Sede(int idSede,String nombre, String ubicacion) {
        this.idSede=idSede;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.horarioAtencionEnSemana = new ArrayList<>();
        this.horarioAtencionFinSemana = new ArrayList<>();
        //this.personalSede = new ArrayList<>();
    }

    // Métodos getters
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

    //public List<Personal> getPersonalSede() {
        //return personalSede;}

    // Métodos setters
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

    //public void setPersonalSede(Personal personal) {
       // this.personalSede.add(personal);}
}
