package usuario;

import java.util.Calendar;

public class Tarjeta {
    private long numeroTarjeta;
    private int fechaVencimiento;
    private String marcaTarjeta;
    private String nombreTitular;

    public Tarjeta(long numeroTarjeta, int fechaVencimiento,String marcaTarjeta, String nombreTitular) {
        /**
         * Constructor de la clase Tarjeta que crea una instancia de tarjeta de crédito con la información proporcionada.
         *
         * @param numeroTarjeta El número de la tarjeta de crédito.
         * @param fechaVencimiento La fecha de vencimiento de la tarjeta.
         * @param marcaTarjeta La marca o emisor de la tarjeta de crédito (por ejemplo, Visa, MasterCard).
         * @param nombreTitular El nombre del titular de la tarjeta de crédito.
         */
        this.numeroTarjeta = numeroTarjeta;
        this.fechaVencimiento = fechaVencimiento;
        this.marcaTarjeta= marcaTarjeta;
        this.nombreTitular = nombreTitular;
    }
    public long getNumeroTarjeta() {
        /**
         * Obtiene el número de la tarjeta de crédito.
         *
         * @return El número de la tarjeta de crédito.
         */
        return this.numeroTarjeta;
    }

    public int getFechaVencimiento() {
        /**
         * Obtiene la fecha de vencimiento de la tarjeta de crédito.
         *
         * @return La fecha de vencimiento de la tarjeta de crédito.
         */
        return this.fechaVencimiento;
    }
    public String getMarcaTarjeta() {
        /**
         * Obtiene la marca o emisor de la tarjeta de crédito.
         *
         * @return La marca o emisor de la tarjeta de crédito.
         */
        return this.marcaTarjeta;
    }

    public String getNombreTitular() {
        /**
         * Obtiene el nombre del titular de la tarjeta de crédito.
         *
         * @return El nombre del titular de la tarjeta de crédito.
         */
        return this.nombreTitular;
    }
    public boolean checkVencimientoTarjeta(int dia,int mes,int anio){
        /**
         * Comprueba si la tarjeta de crédito ha vencido en una fecha específica.
         *
         * @param dia El día para la comprobación. Si se proporciona como 0, se utiliza el día actual.
         * @param mes El mes para la comprobación. Si se proporciona como 0, se utiliza el mes actual.
         * @param anio El año para la comprobación. Si se proporciona como 0, se utiliza el año actual.
         * @return `true` si la tarjeta ha vencido en la fecha proporcionada; de lo contrario, `false`.
         */
        boolean vence=true;
        if (dia==0 && mes==0 && anio==0){
        Calendar fechaActual = Calendar.getInstance();
        dia = fechaActual.get(Calendar.DAY_OF_MONTH);
        mes = fechaActual.get(Calendar.MONTH) + 1;
        anio = fechaActual.get(Calendar.YEAR);}
        String fechaString = String.format("%04d%02d%02d", anio, mes, dia);
        int fechaTope = Integer.parseInt(fechaString);
        if (this.getFechaVencimiento()>= fechaTope) {
            vence= false;
    }
        return vence;
    }
}
