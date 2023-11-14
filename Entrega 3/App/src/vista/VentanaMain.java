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
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class VentanaMain {
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


                boolean found=false;
                if (personal.checkLoginAdmin(username,password)==true){
                    found=true;
                    frame.setEnabled(false);
                    new VentanaAdmin();

                }
                else if (personal.checkLoginPersonal(username, password)!=null){
                    found=true;
                    perfil=(personal.checkLoginPersonal(username, password)).getTipoPersonal();
                    Sede sedePersonal= personal.checkLoginPersonal(username, password).getSede();
                    if (perfil.equals("AdminLocal")){
                        new VentanaAdminLocal(sedePersonal);
                    }
                    else if (perfil.equals("EmpleadoAtencion")){
                        new VentanaAtencion(sedePersonal);}

                        
                    else if (perfil.equals("EmpleadoTecnico")){
                        new VentanaEmpleadoTecnico();
                    }
                    else{}
                    }
              
                else if (Usuario.checkLoginCliente(username, password)!=null){
                    found=true;
                    Cliente cliente= Usuario.checkLoginCliente(username, password);
                    new VentanaCliente(cliente);
                }
                if (found){
                    frame.setEnabled(true);

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
                JOptionPane.showMessageDialog(frame, "La ventana de registro se abrirá aquí.", "Registro", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Centra la ventana en la pantalla
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
    }
    public static void CambioGuardadoDialog() {
        JDialog dialog = new JDialog();
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setTitle("Notificación");
        dialog.setModalityType(ModalityType.APPLICATION_MODAL);
        dialog.setSize(300, 150);
        dialog.setLocationRelativeTo(null);
        dialog.setLayout(new BorderLayout());

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel label = new JLabel("Cambio(s) guardado(s)");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setFont(new Font("Arial", Font.BOLD, 12));
        contentPanel.add(label);

        JButton okButton = new JButton("OK");
        okButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        okButton.addActionListener(e -> {
            dialog.dispose();
        });
        contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        contentPanel.add(okButton);

        dialog.add(contentPanel, BorderLayout.CENTER);
        dialog.setVisible(true);
    }
    public static void logDialog() {
        JDialog dialog = new JDialog();
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setTitle("Notificación");
        dialog.setModalityType(ModalityType.APPLICATION_MODAL);
        dialog.setSize(300, 150);
        dialog.setLocationRelativeTo(null);
        dialog.setLayout(new BorderLayout());

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel label = new JLabel("Log guardado en la carpeta \"historiales\"");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setFont(new Font("Arial", Font.BOLD, 12));
        contentPanel.add(label);

        JButton okButton = new JButton("OK");
        okButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        okButton.addActionListener(e -> {
            dialog.dispose();
        });
        contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        contentPanel.add(okButton);

        dialog.add(contentPanel, BorderLayout.CENTER);
        dialog.setVisible(true);
    }
    public static void errorDialog(String labelText2) {
        JDialog dialog = new JDialog();
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        dialog.setTitle("Notificación");
        dialog.setModalityType(ModalityType.APPLICATION_MODAL);
        dialog.setSize(450, 200);
        dialog.setLocationRelativeTo(null);
    
        // Cambia el fondo del panel
        panel.setBackground(Color.WHITE);
    
        // Crea un icono para el diálogo (reemplaza "icon.png" con la ubicación de tu propio archivo de imagen)
        ImageIcon icon = new ImageIcon("icon.png");
    
        // Cambia el icono del diálogo
        dialog.setIconImage(icon.getImage());
    
        JLabel label = new JLabel("No se pudieron guardar los cambios:");
        JLabel label2 = new JLabel(labelText2);
    
        // Cambia el color del texto a negro, establece el estilo negrita y el tamaño de fuente
        label.setForeground(Color.BLACK);
        label.setFont(new Font("Arial", Font.BOLD, 12));
    
        label2.setForeground(Color.BLACK);
        label2.setFont(new Font("Arial", Font.PLAIN, 12));
    
        // Agrega el JLabel al panel para que se autoajuste al contenido
        JPanel textPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        textPanel.add(label2);
    
        panel.add(label);
        panel.add(textPanel);
    
        JButton okButton = new JButton("OK");
        okButton.addActionListener(e -> {
            dialog.dispose();
        });
    
        panel.add(okButton);
    
        dialog.add(panel);
        dialog.setVisible(true);
    }
        public static void Dialog(String labelText2) {
        JDialog dialog = new JDialog();
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        dialog.setTitle("Notificación");
        dialog.setModalityType(ModalityType.APPLICATION_MODAL);
        dialog.setSize(450, 200);
        dialog.setLocationRelativeTo(null);
    
        // Cambia el fondo del panel
        panel.setBackground(Color.WHITE);
    
        // Crea un icono para el diálogo (reemplaza "icon.png" con la ubicación de tu propio archivo de imagen)
        ImageIcon icon = new ImageIcon("icon.png");
    
        // Cambia el icono del diálogo
        dialog.setIconImage(icon.getImage());
    
        JLabel label = new JLabel("Ten en cuenta:");
        JLabel label2 = new JLabel(labelText2);
    
        // Cambia el color del texto a negro, establece el estilo negrita y el tamaño de fuente
        label.setForeground(Color.BLACK);
        label.setFont(new Font("Arial", Font.BOLD, 12));
    
        label2.setForeground(Color.BLACK);
        label2.setFont(new Font("Arial", Font.PLAIN, 12));
    
        // Agrega el JLabel al panel para que se autoajuste al contenido
        JPanel textPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        textPanel.add(label2);
    
        panel.add(label);
        panel.add(textPanel);
    
        JButton okButton = new JButton("OK");
        okButton.addActionListener(e -> {
            dialog.dispose();
        });
    
        panel.add(okButton);
    
        dialog.add(panel);
        dialog.setVisible(true);
    }
    public static JPanel setPanelSuperior(JFrame frame){
        JPanel panelSuperior= new JPanel();
        panelSuperior.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

        JPanel panelSupIzq= new JPanel();
        JLabel nomEmpresa= new JLabel(Inventario.getNombreCompania());

        JLabel imagenEmpresa= new JLabel();
        Icon icon = new ImageIcon("./imagenes/logo2.png");
        imagenEmpresa.setIcon(icon);

        panelSupIzq.add(imagenEmpresa);
        panelSupIzq.add(nomEmpresa);
        panelSuperior.add(panelSupIzq, BorderLayout.WEST);
        JPanel panelSupDere= new JPanel();
        JButton cerrarSesionButton = new JButton("Cerrar Sesión");
        cerrarSesionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                try {Inventario.updateSistema();} catch (IOException e1) {e1.printStackTrace();}
                frame.dispose();
                try {
                    Inventario.updateSistema();
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });
        panelSupDere.add(cerrarSesionButton);
        panelSuperior.add(panelSupDere, BorderLayout.EAST);
        return panelSuperior;
    }

    public static boolean checkFields1Sede(PlaceHolderTextField nomSede,PlaceHolderTextField  ubiSede, JComboBox<String> hora1,JComboBox<String> min1,JComboBox<String> hora2,JComboBox<String> min2) {
        // Verificar si todos los campos están llenos
        String nomSedeText = nomSede.getText().trim();
        String ubiSedeText = ubiSede.getText().trim();
        boolean hora1Selected = hora1.getSelectedItem()!=null;
        boolean min1Selected = min1.getSelectedItem() != null;
        boolean hora2Selected = hora2.getSelectedItem() != null;
        boolean min2Selected = min2.getSelectedItem() != null;
    
        // Habilitar o deshabilitar el botón según el estado de los campos
        return (!nomSedeText.isEmpty() && !ubiSedeText.isEmpty() &&
            hora1Selected && min1Selected && hora2Selected && min2Selected);
    }

    public static void refresh(JPanel panel){
        panel.removeAll();
        panel.repaint();
        panel.validate();
    }
    public static void refresh(JTabbedPane panel){
        panel.removeAll();
        panel.repaint();
        panel.validate();
    }

}
