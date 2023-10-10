package alquiler;

import java.util.HashMap;
import java.util.Map;

public class PagoExcedente {

    private Map<String, Double> pagoAdicional;

    public PagoExcedente() {
        pagoAdicional = new HashMap<>();
    }

    public void agregarPagoAdicional(String motivo, double valor) {
        pagoAdicional.put(motivo, valor);
    }

    public double getValorPagoAdicional(String motivo) {
        return pagoAdicional.get(motivo);
    }
    
}
