package usuario;

public class Tarjeta {
    private long numeroTarjeta;
    private int fechaVencimiento;
    private String marcaTarjeta;
    private String nombreTitular;

    public Tarjeta(long numeroTarjeta, int fechaVencimiento, String nombreTitular) {
        this.numeroTarjeta = numeroTarjeta;
        this.fechaVencimiento = fechaVencimiento;
        this.nombreTitular = nombreTitular;
    }
    public long getNumeroTarjeta() {
        return this.numeroTarjeta;
    }

    public int getFechaVencimiento() {
        return this.fechaVencimiento;
    }

    public String getMarcaTarjeta() {
        return this.marcaTarjeta;
    }

    public String getNombreTitular() {
        return this.nombreTitular;
    }
}
