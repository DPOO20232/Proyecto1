package vista;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import modelo.EmpleadoAtencion;
import modelo.EmpleadoTecnico;
import modelo.Inventario;
import modelo.Sede;
import modelo.Usuario;
import modelo.personal;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class VentanaAdminLocal {
        private JFrame frame; // Declarar frame como variable miembro
        private JTabbedPane tabbedPane; // Declarar tabbedPane como variable miembro
        JPanel panelSuperior;
        JTabbedPane panelInferior;
        private static EditorObjetos editorObjetos;
        public VentanaAdminLocal(Sede sede) {
            frame = new JFrame("Menu Admin Local");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);
            frame.setLocationRelativeTo(null);
            
            // Crear un panel superior para el nombre de la empresa
            JPanel nombreEmpresaPanel = new JPanel();
            this.panelSuperior= VentanaMain.setPanelSuperior(frame);

            nombreEmpresaPanel.add(panelSuperior);
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
                    boolean puedoRegistrar=true;
                    if (!esEmpleadoAtencion && !esEmpleadoTecnico) {
                        JOptionPane.showMessageDialog(frame, "Debe seleccionar al menos un tipo de personal.", "Error", JOptionPane.ERROR_MESSAGE);
                        puedoRegistrar=false;
                        return; // Salir del ActionListener si no se cumple la validación
                    }
                     // Validación: Asegurarse de que solo uno de los JCheckBox esté seleccionado
                    if ((esEmpleadoAtencion && esEmpleadoTecnico) || (!esEmpleadoAtencion && !esEmpleadoTecnico)) {
                        JOptionPane.showMessageDialog(frame, "Debe seleccionar uno y solo uno de los tipos de personal.", "Error", JOptionPane.ERROR_MESSAGE);
                        puedoRegistrar=false;
                        return; // Salir del ActionListener si no se cumple la validación
                    }
                    
                    // Validación: Asegurarse de que los campos de login y contraseña no estén vacíos
                    if (login.isEmpty() || password.isEmpty()) {
                        JOptionPane.showMessageDialog(frame, "Los campos de login y contraseña son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
                        puedoRegistrar=false;
                        return; // Salir del ActionListener si no se cumple la validación
                    }
                    if(Usuario.checkNombresLogins(login)==true){
                        JOptionPane.showMessageDialog(frame, "Ese login ya existe.", "Error", JOptionPane.ERROR_MESSAGE);
                        puedoRegistrar=false;
                        return;
                    }
                    if (puedoRegistrar){
                        Usuario.addNombreLogin(password);
                        if (esEmpleadoAtencion){
                            EmpleadoAtencion empleadoAtencion=new EmpleadoAtencion(login, password, "EmpleadoAtencion", sede);
                            sede.addPersonalSede(empleadoAtencion);
                            personal.addCredencialesPersonal(empleadoAtencion);
                            try {
                                Inventario.updateSistema();
                            } catch (IOException e1) {
                                // TODO Auto-generated catch block
                                e1.printStackTrace();
                            }
                        }
                        else if (esEmpleadoTecnico){
                            EmpleadoTecnico empleadoTecnico=new EmpleadoTecnico(login, password, "EmpleadoTecnico", sede);
                            sede.addPersonalSede(empleadoTecnico);
                            personal.addCredencialesPersonal(empleadoTecnico);
                            try {
                                Inventario.updateSistema();
                            } catch (IOException e1) {
                                // TODO Auto-generated catch block
                                e1.printStackTrace();
                            }
                        }

                    }
                    VentanaMain.CambioGuardadoDialog();
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
                    if(Usuario.checkNombresLogins(loginActualizar)==true){
                        personal empleado=null;
                        boolean esEpleado=false;
                        for(personal i: personal.getCredencialesPersonal()){
                            if ((i.getLogin().equals(loginActualizar))&&(i.getSede().equals(sede))){
                                empleado=i;
                                esEpleado=true;
                                break;
                            }
                            
                        }
                        if( esEpleado){
                           
                            VentanaMain.refresh(pestaña2);
                            editorObjetos = new EditorObjetos();
                            editorObjetos.editorPersonal(pestaña2,empleado,panelActualizarPersonal);
                            editorObjetos.editar();
                           
                           
                        }
                        else{
                            VentanaMain.errorDialog("el usuario ingresado no existe o no pertenece a la sede del administrador");
                       
                        }
                   
                        
                    }
                    
                    

                   
                    // Limpieza del campo después de la actualización
                    loginActualizarField.setText("");
                   
                }
            });

            JButton volverAtrasButton = new JButton("Volver Atrás");
            volverAtrasButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
              
                    panelActualizarPersonal.repaint();
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
            String empleados=personal.printRegistroEmpleados(sede);
            String administradorLocal = EditorObjetos.extraerInformacion("Administrador Local", empleados);
            System.out.println(administradorLocal);
            String empleadosAtencion = EditorObjetos.extraerInformacion("Empleado(s) de atención", empleados);
            String empleadosTecnicos = EditorObjetos.extraerInformacion("Empleado(s) técnico(s)", empleados);
            
            JPanel panelAdminLocal = new JPanel();
            panelAdminLocal.setLayout(new BoxLayout(panelAdminLocal, BoxLayout.Y_AXIS));
            JLabel adminLocalLabel = new JLabel("Administrador Local:");
            JTextField adminLocalField = new JTextField();
            adminLocalField.setText(administradorLocal);
            panelAdminLocal.add(adminLocalLabel);
            panelAdminLocal.add(adminLocalField);

            JPanel panelEmpleadosTecnicos = new JPanel();
            panelEmpleadosTecnicos.setLayout(new BoxLayout(panelEmpleadosTecnicos, BoxLayout.Y_AXIS));
            JLabel empleadosTecnicosLabel = new JLabel("Empleados Técnicos:");
            JTextField empleadosTecnicosField = new JTextField();
            empleadosTecnicosField.setText(empleadosTecnicos);
            panelEmpleadosTecnicos.add(empleadosTecnicosLabel);
            panelEmpleadosTecnicos.add(empleadosTecnicosField);


            JPanel panelEmpleadosAtencion = new JPanel();
            panelEmpleadosAtencion.setLayout(new BoxLayout(panelEmpleadosAtencion, BoxLayout.Y_AXIS));
            JLabel empleadosAtencionLabel = new JLabel("Empleados de Atención:");
            JTextField empleadosAtencionField = new JTextField();
            empleadosAtencionField.setText(empleadosAtencion);
            panelEmpleadosAtencion.add(empleadosAtencionLabel);
            panelEmpleadosAtencion.add(empleadosAtencionField);
            JLabel AtencionLabel = new JLabel("Estos son los empleados antes de iniciar sesion si hubo cambios cierre sesion:");

            pestaña3.add(AtencionLabel);
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
    
}
