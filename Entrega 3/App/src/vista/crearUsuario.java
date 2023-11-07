package vista;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentListener;
import javax.swing.event.DocumentEvent;


public class crearUsuario extends JFrame {
    private JPasswordField campoContraseña;
    private JLabel labelContraseña;
    private JTabbedPane tabbedPane;
    
    public crearUsuario() {
        setLayout(new GridLayout(0, 1));
        tabbedPane = new JTabbedPane();
        JLabel labelMensaje = new JLabel("¡Ya falta poco! Ahora toca crear un login y su contraseña.");
        JLabel labelUsuario = new JLabel("Nombre de Usuario (login): ");
        PlaceHolderTextField campoUsuario = new PlaceHolderTextField("Ej: jlopez123");
        labelContraseña = new JLabel("Contraseña: ");
        campoContraseña = new JPasswordField();
        labelContraseña.setVisible(false);
        campoContraseña.setVisible(false);

        campoUsuario.getDocument().addDocumentListener((DocumentListener) new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                mostrarCampoContraseña(!campoUsuario.getText().isEmpty());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                mostrarCampoContraseña(!campoUsuario.getText().isEmpty());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                mostrarCampoContraseña(!campoUsuario.getText().isEmpty());
            }
        });

        tabbedPane.add("Usuario y Contraseña", this);

        add(labelMensaje);
        add(labelUsuario);
        add(campoUsuario);

        add(labelContraseña);
        add(campoContraseña);
        add(tabbedPane);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(null);
        setSize(500, 600);
        setVisible(true);

    }
    private void mostrarCampoContraseña(boolean mostrar) {
        labelContraseña.setVisible(mostrar);
        campoContraseña.setVisible(mostrar);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new crearUsuario());
    }
    
}
