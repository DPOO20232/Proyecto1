package alquiler;

public class PagoExcedente {

    private String motivoPago;
    private double pago;

    public PagoExcedente(String motivo, double pago) {
        
    
              
        //* Descripción del método: Crea una instancia de la clase PagoExcedente con el motivo de pago y el valor de pago especificados.
        //* :param motivo: String - El motivo del pago. Debe ser una cadena no nula.
        //* :param pago: double - El valor del pago. Debe ser un número decimal positivo o cero.
        //* :throws IllegalArgumentException: Se lanza una excepción si el motivo es nulo o vacío, o si el valor de pago es negativo.
        
            
        this.motivoPago=motivo;
        this.pago=pago;
        ;
                
                
    }

    public void setValorPago(double pago){
        /**
         * Descripción del método: Establece un nuevo valor de pago.
         *
         * @param pago: double - El nuevo valor del pago.
         * @type pago: double
         *
         * @throws IllegalArgumentException: Se lanza una excepción si el valor de pago es negativo.
         */
        this.pago=pago;
    }
    public void setMotivoPago(String motivo){
        /**
         * Descripción del método: Establece un nuevo motivo de pago.
         *
         * @param motivo: String - El nuevo motivo del pago. Debe ser una cadena no nula.
         * @type motivo: String
         *
         * @throws IllegalArgumentException: Se lanza una excepción si el motivo es nulo o vacío.
         */
        this.motivoPago=motivo;
    }
    public double getValorPago(){
        /**
         * Descripción del método: Obtiene el valor del pago.
         *
         * @return double - El valor actual del pago.
         */
        return this.pago;
    }
    public String getMotivoPago(){
        /**
         * Descripción del método: Obtiene el motivo d+el pago.
         *
         * @return String - El motivo actual del pago.
         */
        return this.motivoPago;
    }
    
}
