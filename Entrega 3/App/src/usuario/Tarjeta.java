package usuario;

import java.util.Calendar;

public class Tarjeta {
    private long numeroTarjeta;
    private int fechaVencimiento;
    private String marcaTarjeta;
    private String nombreTitular;

    public Tarjeta(long numeroTarjeta, int fechaVencimiento,String marcaTarjeta, String nombreTitular) {
        this.numeroTarjeta = numeroTarjeta;
        this.fechaVencimiento = fechaVencimiento;
        this.marcaTarjeta= marcaTarjeta;
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
    public boolean checkVencimientoTarjeta(int dia,int mes,int anio){
        boolean vence=true;
        if (dia==0 && mes==0 && anio==0){
        Calendar fechaActual = Calendar.getInstance();
        dia = fechaActual.get(Calendar.DAY_OF_MONTH);
        mes = fechaActual.get(Calendar.MONTH) + 1;
        anio = fechaActual.get(Calendar.YEAR);}
        String fechaString = String.format("%04d%02d%02d", anio, mes, dia);
        int fechaTope = Integer.parseInt(fechaString);
        if (this.getNumeroTarjeta()>= fechaTope) {
            vence= false;
    }
        return vence;
    }
}
