package alquiler;

public class PagoExcedente {

    private String motivoPago;
    private double pago;

    public PagoExcedente(String motivo, double pago) {
        this.motivoPago=motivo;
        this.pago=pago;
    }

    public void setValorPago(double pago){
        this.pago=pago;
    }
    public void setMotivoPago(String motivo){
        this.motivoPago=motivo;
    }
    public double getValorPago(){
        return this.pago;
    }
    public String getMotivoPago(){
        return this.motivoPago;
    }
    
}
