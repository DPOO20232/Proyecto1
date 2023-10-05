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

    public int getfechaInicio() {
        return fechaInicio;
    }

    public int getfechaFin() {
        return fechaFin;
    }

    public int gethoraInicio() {
        return horaInicio;
    }

    public int gethoraFin() {
        return horaFin;
    }

    public String getDescripcion() {
        return descripcion;
    }


    public void setFechaInicio() {
    }

    public void setFechaFin() {
    }

    public void setHoraInicio() {
    }

    public void setHoraFin() {
    }

    public void setDescripcion() {
    }



}
