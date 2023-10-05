package usuario;

public class Tarjeta {
    private int numeroTarjeta;
    private int fechaVencimiento;
    private String nombreTitular;

    public Tarjeta(int numeroTarjeta, int fechaVencimiento, String nombreTitular) {
        this.numeroTarjeta = numeroTarjeta;
        this.fechaVencimiento = fechaVencimiento;
        this.nombreTitular = nombreTitular;
    }
}
