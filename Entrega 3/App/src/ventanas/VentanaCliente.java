package ventanas;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaCliente {
        private JFrame frame; // Declarar frame como variable miembro
        private JTabbedPane tabbedPane; // Declarar tabbedPane como variable miembro
        private  JPanel mainPanel;
        private  CardLayout cardLayout;
        private  JPanel cardPanel;
        private  String[] pasos;
        private int pasoActual = 0;
        private static EditorObjetos editorObjetos;
        public VentanaCliente() {
            frame = new JFrame("Aplicación de la Empresa");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);
    
            // Crear un panel superior para el nombre de la empresa
            JPanel nombreEmpresaPanel = new JPanel();
            nombreEmpresaPanel.setBackground(Color.LIGHT_GRAY);
            JLabel nombreEmpresaLabel = new JLabel("Nombre de la Empresa");
            nombreEmpresaPanel.add(nombreEmpresaLabel);
    
            // Botón de "Cerrar Sesión"
            JButton cerrarSesionButton = new JButton("Cerrar Sesión");
            cerrarSesionButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Agregar el código para cerrar la sesión del administrador local
                    // Por ejemplo, puedes cerrar la ventana actual y mostrar una ventana de inicio de sesión.
                }
            });
            nombreEmpresaPanel.add(cerrarSesionButton);
    
            // Crear el panel de pestañas
            tabbedPane = new JTabbedPane();
    
            // Agregar la pestaña 1
            JPanel pestaña1 = new JPanel();
            tabbedPane.addTab("agregar personal", null, pestaña1, "Agregar Personal");
    
            // Crear y agregar el contenido de "Agregar Personal" al panel de la pestaña 1
            JPanel panelAgregarPersonal = new JPanel(new GridLayout(5, 2));
            JLabel loginLabel = new JLabel("Login:");
            JTextField loginField = new JTextField();
            JLabel passwordLabel = new JLabel("Contraseña:");
            JPasswordField passwordField = new JPasswordField();
            JLabel tipoLabel = new JLabel("Tipo de Personal:");
            JCheckBox empleadoAtencionCheckBox = new JCheckBox("Empleado de Atención");
            JCheckBox empleadoTecnicoCheckBox = new JCheckBox("Empleado Técnico");
           


    
            JButton crearUsuarioButton = new JButton("Crear Usuario");
            crearUsuarioButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Agregar lógica para crear el usuario aquí
                    String login = loginField.getText();
                    String password = new String(passwordField.getPassword());
                    boolean esEmpleadoAtencion = empleadoAtencionCheckBox.isSelected();
                    boolean esEmpleadoTecnico = empleadoTecnicoCheckBox.isSelected();
                    if (!esEmpleadoAtencion && !esEmpleadoTecnico) {
                        JOptionPane.showMessageDialog(frame, "Debe seleccionar al menos un tipo de personal.", "Error", JOptionPane.ERROR_MESSAGE);
                        return; // Salir del ActionListener si no se cumple la validación
                    }
                     // Validación: Asegurarse de que solo uno de los JCheckBox esté seleccionado
                    if ((esEmpleadoAtencion && esEmpleadoTecnico) || (!esEmpleadoAtencion && !esEmpleadoTecnico)) {
                        JOptionPane.showMessageDialog(frame, "Debe seleccionar uno y solo uno de los tipos de personal.", "Error", JOptionPane.ERROR_MESSAGE);
                        return; // Salir del ActionListener si no se cumple la validación
                    }
                    
                    // Validación: Asegurarse de que los campos de login y contraseña no estén vacíos
                    if (login.isEmpty() || password.isEmpty()) {
                        JOptionPane.showMessageDialog(frame, "Los campos de login y contraseña son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
                        return; // Salir del ActionListener si no se cumple la validación
                    }
                    // Limpieza de campos después de crear el usuario
                    loginField.setText("");
                    passwordField.setText("");
                    empleadoAtencionCheckBox.setSelected(false);
                    empleadoTecnicoCheckBox.setSelected(false);
   
    
                    // Realiza las acciones necesarias con los datos ingresados
                    // Puedes agregar lógica para crear el usuario y guardar los datos
                }
            });
            panelAgregarPersonal.add(loginLabel);
            panelAgregarPersonal.add(loginField);
            panelAgregarPersonal.add(passwordLabel);
            panelAgregarPersonal.add(passwordField);
            panelAgregarPersonal.add(tipoLabel);
            panelAgregarPersonal.add(new JPanel()); // Espacio vacío
            panelAgregarPersonal.add(empleadoAtencionCheckBox);
            panelAgregarPersonal.add(empleadoTecnicoCheckBox);
            panelAgregarPersonal.add(new JPanel()); // Espacio vacío
            panelAgregarPersonal.add(crearUsuarioButton);
            pestaña1.add(panelAgregarPersonal); // Agrega el panel de "Agregar Personal" a la pestaña 1
            // Crear pestaña 2
            JPanel pestaña2 = new JPanel();
            tabbedPane.addTab("actualizar personal", null, pestaña2, "Actualizar Personal");
            JPanel panelActualizarPersonal = new JPanel(new GridLayout(3, 2));
            JLabel loginActualizarLabel = new JLabel("Login del Empleado a Actualizar:");
            JTextField loginActualizarField = new JTextField();
            
            JButton actualizarUsuarioButton = new JButton("Actualizar Usuario");
            actualizarUsuarioButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Agregar lógica para actualizar el usuario aquí
                    String loginActualizar = loginActualizarField.getText();
                    
                    // Validación: Asegurarse de que el campo de login no esté vacío
                    if (loginActualizar.isEmpty()) {
                        JOptionPane.showMessageDialog(frame, "El campo de login es obligatorio.", "Error", JOptionPane.ERROR_MESSAGE);
                        return; // Salir del ActionListener si no se cumple la validación
                    }

                    // Aquí puedes agregar la lógica para buscar al empleado con el login proporcionado y actualizar su información
                    // Crear un objeto EditorObjetos y configurarlo
                        pestaña2.removeAll();
                        pestaña2.revalidate();
                        pestaña2.repaint();
                    
                    editorObjetos = new EditorObjetos();
                    editorObjetos.editorPersonal(pestaña2);
                    editorObjetos.editar();
                    
                   
                    // Limpieza del campo después de la actualización
                    loginActualizarField.setText("");
                   
                }
            });

            JButton volverAtrasButton = new JButton("Volver Atrás");
            volverAtrasButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Cierra la ventana "Actualizar Personal"
                    panelActualizarPersonal.setVisible(false);
                }
            });
            
            panelActualizarPersonal.add(loginActualizarLabel);
            panelActualizarPersonal.add(loginActualizarField);
            panelActualizarPersonal.add(new JPanel()); // Espacio vacío
            panelActualizarPersonal.add(new JPanel()); // Espacio vacío
            panelActualizarPersonal.add(actualizarUsuarioButton);
            panelActualizarPersonal.add(volverAtrasButton);

            pestaña2.add(panelActualizarPersonal);
            
            // Crear pestaña 3
            JPanel pestaña3 = new JPanel();
            tabbedPane.addTab("acceder al personal", null, pestaña3, "Acceder al Personal");
            // Crear espacios para mostrar al administrador local, empleados técnicos y empleados de atención
            JPanel panelAdminLocal = new JPanel();
            panelAdminLocal.setLayout(new BoxLayout(panelAdminLocal, BoxLayout.Y_AXIS));
            JLabel adminLocalLabel = new JLabel("Administrador Local:");
            JTextField adminLocalField = new JTextField();
            panelAdminLocal.add(adminLocalLabel);
            panelAdminLocal.add(adminLocalField);

            JPanel panelEmpleadosTecnicos = new JPanel();
            panelEmpleadosTecnicos.setLayout(new BoxLayout(panelEmpleadosTecnicos, BoxLayout.Y_AXIS));
            JLabel empleadosTecnicosLabel = new JLabel("Empleados Técnicos:");
            JTextField empleadosTecnicosField = new JTextField();
            panelEmpleadosTecnicos.add(empleadosTecnicosLabel);
            panelEmpleadosTecnicos.add(empleadosTecnicosField);


            JPanel panelEmpleadosAtencion = new JPanel();
            panelEmpleadosAtencion.setLayout(new BoxLayout(panelEmpleadosAtencion, BoxLayout.Y_AXIS));
            JLabel empleadosAtencionLabel = new JLabel("Empleados de Atención:");
            JTextField empleadosAtencionField = new JTextField();
            panelEmpleadosAtencion.add(empleadosAtencionLabel);
            panelEmpleadosAtencion.add(empleadosAtencionField);


            
            pestaña3.add(panelAdminLocal);
            pestaña3.setLayout(new GridLayout(0, 1));
            pestaña3.add(panelEmpleadosTecnicos);
            pestaña3.setLayout(new GridLayout(0, 1));
            pestaña3.add(panelEmpleadosAtencion);

            frame.add(nombreEmpresaPanel, BorderLayout.NORTH);
            frame.add(tabbedPane, BorderLayout.CENTER);

            // Hacer visible la ventana
            frame.setVisible(true);
    
           
        }
        
        
        public static void main(String[] args) {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    new VentanaCliente();
                }
            });
        }
    
    
}
