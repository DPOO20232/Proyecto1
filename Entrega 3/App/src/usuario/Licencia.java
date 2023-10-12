package usuario;


public class Licencia {
    private int numeroLicencia;
    private int fechaExpedicion;
    private int fechaVencimiento;
    private String paisExpedicion;
    private String imagenLicencia;
    
    public Licencia(int numeroLicencia, int fechaExpedicion, int fechaVencimiento, String paisExpedicion) {
        this.numeroLicencia = numeroLicencia;
        this.fechaExpedicion = fechaExpedicion;
        this.fechaVencimiento = fechaVencimiento;
        this.paisExpedicion = paisExpedicion;
        this.imagenLicencia="No disponible :c";
    }
    
    public int getNumeroLicencia() {
        return this.numeroLicencia;
    }

    public int getFechaExpedicion() {
        return this.fechaExpedicion;
    }

    public int getFechaVencimiento() {
        return this.fechaVencimiento;
    }

    public String getPaisExpedicion() {
        return this.paisExpedicion;
    }
    public String getImagenLicencia(){
        return this.imagenLicencia; 
    }

}
