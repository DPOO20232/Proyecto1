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
        /**
         * Crea un nuevo evento con los siguientes parámetros.
         *
         * @param id El ID del evento.
         * @param fechaInicio La fecha de inicio del evento en formato YYYYMMDD.
         * @param fechaFin La fecha de fin del evento en formato YYYYMMDD.
         * @param horaInicio La hora de inicio del evento en formato HHMM.
         * @param horaFin La hora de fin del evento en formato HHMM.
         * @param descripcion La descripción del evento.
         */
        this.idEvento=id;
        if (idEvento>lastId){lastId=idEvento;}
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.descripcion = descripcion;
    }

    public Evento(int fechaInicio, int fechaFin, int horaInicio, int horaFin, String descripcion) {
        /**
         * Crea un nuevo evento con los siguientes parámetros y establece un ID autoincremental.
         *
         * @param fechaInicio La fecha de inicio del evento en formato YYYYMMDD.
         * @param fechaFin La fecha de fin del evento en formato YYYYMMDD.
         * @param horaInicio La hora de inicio del evento en formato HHMM.
         * @param horaFin La hora de fin del evento en formato HHMM.
         * @param descripcion La descripción del evento.
         */
        this.setID();
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.descripcion = descripcion;
    }
    public int getID(){
        /**
         * Obtiene el ID del evento.
         *
         * @return El ID del evento.
         */
        return this.idEvento;
    }
    public int getFechaInicio() {
        /**
         * Obtiene el ID del evento.
         *
         * @return El ID del evento.
         */
        return this.fechaInicio;
    }

    public int getFechaFin() {
        /**
         * Obtiene la fecha de fin del evento en formato YYYYMMDD.
         *
         * @return La fecha de fin del evento.
         */
        return this.fechaFin;
    }

    public int getHoraInicio() {
        /**
         * Obtiene la hora de inicio del evento en formato HHMM.
         *
         * @return La hora de inicio del evento.
         */
        return this.horaInicio;
    }

    public int getHoraFin() {
        /**
         * Obtiene la hora de inicio del evento en formato HHMM.
         *
         * @return La hora de inicio del evento.
         */
        return this.horaFin;
    }

    public String getDescripcion() {
        /**
         * Obtiene la descripción del evento.
         *
         * @return La descripción del evento.
         */
        return this.descripcion;
    }

    public void setID(){
        /**
         * Establece un nuevo ID autoincremental para el evento.
         */
        this.idEvento=lastId+=1;
        lastId=this.getID();
    }
    public void setFechaInicio(int fechaInicio) {
        /**
         * Establece la fecha de inicio del evento en formato YYYYMMDD.
         *
         * @param fechaInicio La nueva fecha de inicio del evento.
         */
        this.fechaInicio = fechaInicio;
    }
    public void setFechaFin(int fechaFin) {
        /**
         * Establece la fecha de fin del evento en formato YYYYMMDD.
         *
         * @param fechaFin La nueva fecha de fin del evento.
         */
        this.fechaFin = fechaFin;
    }

    public void setHoraInicio(int horaInicio) {
        /**
         * Establece la hora de inicio del evento en formato HHMM.
         *
         * @param horaInicio La nueva hora de inicio del evento.
         */
        this.horaInicio = horaInicio;
    }

    public void setHoraFin(int horaFin) {   
        /**
         * Establece la hora de inicio del evento en formato HHMM.
         *
         * @param horaInicio La nueva hora de inicio del evento.
         */
        this.horaFin = horaFin;
    }

    public void setDescripcion(String descripcion) {
        /**
         * Establece una nueva descripción para el evento.
         *
         * @param descripcion La nueva descripción del evento.
         */
        this.descripcion = descripcion;
    }
}
