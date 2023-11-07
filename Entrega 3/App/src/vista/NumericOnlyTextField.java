package vista;
import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class NumericOnlyTextField extends JTextField {
    public NumericOnlyTextField() {
        setToolTipText("Ej: 10000000");
        setBounds(0, 0, 80, 30);    
        // Obtiene el Document asociado al JTextField
        AbstractDocument doc = (AbstractDocument) getDocument();

        // Agrega un DocumentFilter para permitir solo números
        doc.setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
                // Permite la inserción solo si la cadena contiene números
                if (string.matches("\\d*")) {
                    super.insertString(fb, offset, string, attr);
                }
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                // Permite la sustitución solo si el texto contiene números
                if (text.matches("\\d*")) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        });
    }
}

