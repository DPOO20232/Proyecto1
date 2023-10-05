package usuario;

public class Licencia {
    private int numeroLicencia;
    private int fechaExpedicion;
    private int fechaVencimiento;
    private String paisExpedicion;
    
    public Licencia(int numeroLicencia, int fechaExpedicion, int fechaVencimiento, String paisExpedicion) {
        this.numeroLicencia = numeroLicencia;
        this.fechaExpedicion = fechaExpedicion;
        this.fechaVencimiento = fechaVencimiento;
        this.paisExpedicion = paisExpedicion;
    }
    
    public int getNumeroLicencia() {
        return numeroLicencia;
    }

    public int getFechaExpedicion() {
        return fechaExpedicion;
    }

    public int getFechaVencimiento() {
        return fechaVencimiento;
    }

    public String getPaisExpedicion() {
        return paisExpedicion;
    }

}
