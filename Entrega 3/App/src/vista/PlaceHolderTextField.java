package vista;
import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class PlaceHolderTextField extends JTextField {
    private String placeholder;
    private boolean isPlaceholderActive;

    public PlaceHolderTextField(String placeholder) {
        this.placeholder = placeholder;
        isPlaceholderActive = true;
        setForeground(Color.GRAY);
        setText(placeholder);
        //setPreferredSize(new Dimension(15 * getFontMetrics(getFont()).charWidth('W'), 15));
        addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (isPlaceholderActive) {
                    setText("");
                    setForeground(Color.BLACK);
                    isPlaceholderActive = false;
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (getText().isEmpty()) {
                    setText(placeholder);
                    setForeground(Color.GRAY);
                    isPlaceholderActive = true;
                }
            }
        });
    }

    @Override
    public String getText() {
        return isPlaceholderActive ? "" : super.getText();
    }
}
