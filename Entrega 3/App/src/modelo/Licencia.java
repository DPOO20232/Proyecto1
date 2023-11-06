package modelo;

public class Licencia {
    private int numeroLicencia;
    private int fechaExpedicion;
    private int fechaVencimiento;
    private String paisExpedicion;
    private String imagenLicencia;
    
    public Licencia(int numeroLicencia, int fechaExpedicion, int fechaVencimiento, String paisExpedicion) {
        /**
         * Crea una nueva licencia con el número de licencia, fecha de expedición, fecha de vencimiento y país de expedición especificados.
         *
         * @param numeroLicencia    El número de licencia del conductor.
         * @param fechaExpedicion    La fecha en la que se expedida la licencia en formato AAAAMMDD.
         * @param fechaVencimiento   La fecha de vencimiento de la licencia en formato AAAAMMDD.
         * @param paisExpedicion     El país en el que se expidió la licencia.
         */
        this.numeroLicencia = numeroLicencia;
        this.fechaExpedicion = fechaExpedicion;
        this.fechaVencimiento = fechaVencimiento;
        this.paisExpedicion = paisExpedicion;
        this.imagenLicencia="No disponible :c";
    }
    
    public int getNumeroLicencia() {
        /**
         * Obtiene el número de licencia del conductor.
         *
         * @return El número de licencia del conductor.
         */
        return this.numeroLicencia;
    }

    public int getFechaExpedicion() {
        /**
         * Obtiene la fecha de expedición de la licencia en formato AAAAMMDD.
         *
         * @return La fecha de expedición de la licencia en formato AAAAMMDD.
         */
        return this.fechaExpedicion;
    }

    public int getFechaVencimiento() {
        /**
         * Obtiene la fecha de vencimiento de la licencia en formato AAAAMMDD.
         *
         * @return La fecha de vencimiento de la licencia en formato AAAAMMDD.
         */
        return this.fechaVencimiento;
    }

    public String getPaisExpedicion() {
        /**
         * Obtiene el país en el que se expidió la licencia.
         *
         * @return El país en el que se expidió la licencia.
         */
        return this.paisExpedicion;
    }
    public String getImagenLicencia(){
        /**
         * Obtiene la imagen de la licencia del conductor.
         *
         * @return La imagen de la licencia del conductor.
         */
        return this.imagenLicencia; 
    }
}
