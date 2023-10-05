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
    public Sede(String nombre, String ubicacion) {
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.horarioAtencionEnSemana = new ArrayList<>();
        this.horarioAtencionFinSemana = new ArrayList<>();
        //this.personalSede = new ArrayList<>();
    }

    // Métodos getters
    public String getNombre() {
        return nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public List<Integer> getHorarioAtencionEnSemana() {
        return horarioAtencionEnSemana;
    }

    public List<Integer> getHorarioAtencionFinSemana() {
        return horarioAtencionFinSemana;
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
