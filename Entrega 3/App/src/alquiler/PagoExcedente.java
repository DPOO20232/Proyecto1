package alquiler;
import java.util.HashMap;
import java.util.Map;

public class PagoExcedente {
    private Map<String, Double> pagoAdicional;

    // Constructor
    public PagoExcedente() {
        pagoAdicional = new HashMap<>();
    }

    // Método para agregar un pago adicional
    public void agregarPagoAdicional(String motivo, double valor) {
        pagoAdicional.put(motivo, valor);
    }

    // Método para obtener el motivo de un pago adicional
    public String getMotivoPagoAdicional(double valor) {
        for (Map.Entry<String, Double> entry : pagoAdicional.entrySet()) {
            if (entry.getValue() == valor) {
                return entry.getKey(); // Retorna la clave asociada al valor
            }
        }
        return null; // Retorna null si no se encuentra el valor
    }

    // Método para obtener el valor de un pago adicional
    public double getValorPagoAdicional(String motivo) {
        return pagoAdicional.get(motivo);
    }
}
    

