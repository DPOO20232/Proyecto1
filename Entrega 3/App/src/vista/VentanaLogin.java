package vista;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import modelo.Cliente;
import modelo.Inventario;
import modelo.Sede;
import modelo.Usuario;
import modelo.personal;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaLogin {
    public static void main(String[] args) {
        Inventario.loadSistema();
        JFrame frame = new JFrame("Inicio de Sesión");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        // Panel para el formulario
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(3, 2));
        frame.add(formPanel, BorderLayout.CENTER);

        JLabel nameLabel = new JLabel("Login:");
        JTextField nameField = new JTextField(15);
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField(15);

        formPanel.add(nameLabel);
        formPanel.add(nameField);
        formPanel.add(passwordLabel);
        formPanel.add(passwordField);

        // botones

        JButton loginButton = new JButton("Iniciar Sesión");
        loginButton.setEnabled(false); // Establecer el botón como deshabilitado inicialmente
        JButton registerButton = new JButton("Registrarme");

        // Agrega un DocumentListener para habilitar/deshabilitar el botón según el contenido de los campos
        DocumentListener documentListener = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateButtonState();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateButtonState();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateButtonState();
            }

            private void updateButtonState() {
                String username = nameField.getText();
                String password = new String(passwordField.getPassword());
                loginButton.setEnabled(!username.trim().isEmpty() && !password.trim().isEmpty());
            }
        };

        nameField.getDocument().addDocumentListener(documentListener);
        passwordField.getDocument().addDocumentListener(documentListener);

        // Acción para el botón de inicio de sesión
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String perfil="";
                String username = nameField.getText();
                String password = new String(passwordField.getPassword());
                System.out.println(username);
                System.out.println(password);

                
                if (personal.checkLoginAdmin(username,password)==true){
                    frame.setEnabled(false);
                    VentanaAdmin ventanaAdmin= new VentanaAdmin();

                }
                else if (personal.checkLoginPersonal(username, password)!=null){
                    perfil=(personal.checkLoginPersonal(username, password)).getTipoPersonal();
                    Sede sedePersonal= personal.checkLoginPersonal(username, password).getSede();
                    personal adminlocal= personal.checkLoginPersonal(username, password);
                    if (perfil.equals("AdminLocal")){}
                    else if (perfil.equals("EmpleadoAtencion")){}
                    else{}
                    }
                else if (Usuario.checkLoginCliente(username, password)!=null){
                        Cliente cliente= Usuario.checkLoginCliente(password, password);
                }
                else{

                }
            }
        });

        //Acción para el botón de registrarse
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                VentanaRegistro ventanaR = new VentanaRegistro();
                ventanaR.setVisible(true);
            }
        });

        // Panel para el texto y el botón "Registrarme"
     // Crear un panel contenedor con un BoxLayout horizontal
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.X_AXIS));

        // Crear el panel vertical (textPanel) y agregar componentes a él
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.add(loginButton);
        //textPanel.add(Box.createVerticalStrut(10));
        textPanel.add(new JLabel("\n"));
        textPanel.add(new JLabel("¿No tienes cuenta?"));
        textPanel.add(registerButton);

        // Agregar el panel vertical al panel horizontal (centroPanel)
        centerPanel.add(Box.createHorizontalGlue()); // Espaciado a la izquierda
        centerPanel.add(textPanel);
        centerPanel.add(Box.createHorizontalGlue()); // Espaciado a la derecha

        // Agregar el panel horizontal centrado al contenedor principal
        frame.add(centerPanel, BorderLayout.SOUTH);


        // Acción para el botón "Registrarme"
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Agrega la lógica para abrir una ventana de registro o realizar la acción correspondiente.
                JOptionPane.showMessageDialog(frame, "Abre la ventana de registro aquí.", "Registro", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Centra la ventana en la pantalla
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
    }
}
