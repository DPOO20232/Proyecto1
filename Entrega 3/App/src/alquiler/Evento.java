package alquiler;

public class Evento {
    
    private int fechaInicio;
    private int fechaFin;
    private int horaInicio;
    private int horaFin;
    private String descripcion;

    public Evento(int fechaInicio, int fechaFin, int horaInicio, int horaFin, String descripcion) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.descripcion = descripcion;
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

}
