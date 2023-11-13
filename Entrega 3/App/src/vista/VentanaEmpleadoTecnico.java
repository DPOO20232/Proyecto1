package vista;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;


import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTabbedPane;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class VentanaEmpleadoTecnico {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Actualizar Estado de Vehículo");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 300);

            // Crear el panel de la pestaña
            JPanel pestaña2 = new JPanel(new FlowLayout());

            // Crear el label para ingresar la placa del vehículo
            JLabel labelPlaca = new JLabel("Ingrese la placa del vehículo:");
            pestaña2.add(labelPlaca);

            // Crear el campo para ingresar la placa del vehículo
            JTextField placaField = new JTextField(10); // 10 es el ancho del campo
            pestaña2.add(placaField);

            // Crear el botón para continuar
            JButton continuarButton = new JButton("Continuar");
            continuarButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // Verificar si el campo está vacío
                    String placa = placaField.getText();
                    if (placa.isEmpty()) {
                        JOptionPane.showMessageDialog(frame, "Debe ingresar la placa del vehículo.", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        // Realizar la acción si el campo no está vacío

                        // Crear el nuevo panel para la selección de acción
                        JPanel panelSeleccionAccion = new JPanel();
                        JLabel labelSeleccion = new JLabel("Seleccione la acción a realizar:");

                        JRadioButton lavadoRadioButton = new JRadioButton("Lavado");
                        JRadioButton mantenimientoRadioButton = new JRadioButton("Mantenimiento");

                        ButtonGroup buttonGroup = new ButtonGroup();
                        buttonGroup.add(lavadoRadioButton);
                        buttonGroup.add(mantenimientoRadioButton);

                        JButton realizarAccionButton = new JButton("Realizar Acción");
                        realizarAccionButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                // Verificar qué opción fue seleccionada
                                String accionSeleccionada = lavadoRadioButton.isSelected() ? "Lavado" :
                                        (mantenimientoRadioButton.isSelected() ? "Mantenimiento" : "");

                                if (!accionSeleccionada.isEmpty()) {
                                    // Realizar la acción correspondiente
                                    JOptionPane.showMessageDialog(frame, "Se realizará " + accionSeleccionada +
                                            " para el vehículo con placa: " + placa);
                                     // Pedir fechas de inicio y fin con JDateChooser
                                     Date fechaInicio = pedirFecha("Fecha de Inicio:");
                                     Date fechaFin = pedirFecha("Fecha de Fin:");
                                    // Limpiar el campo de la placa después de realizar la acción
                                    placaField.setText("");

                                    // Cambiar el contenido del JTabbedPane
                                    actualizarPestaña(frame, pestaña2);
                                } else {
                                    JOptionPane.showMessageDialog(frame, "Seleccione una acción.", "Error", JOptionPane.ERROR_MESSAGE);
                                }
                            }
                        });

                        panelSeleccionAccion.setLayout(new BoxLayout(panelSeleccionAccion, BoxLayout.PAGE_AXIS));
                        panelSeleccionAccion.add(labelSeleccion);
                        panelSeleccionAccion.add(lavadoRadioButton);
                        panelSeleccionAccion.add(mantenimientoRadioButton);
                        panelSeleccionAccion.add(realizarAccionButton);

                        // Crear un nuevo panel de pestaña2
                        JPanel nuevoPestana2 = new JPanel(new FlowLayout());

                        nuevoPestana2.add(panelSeleccionAccion);

                        // Cambiar el contenido del JTabbedPane
                        actualizarPestaña(frame, nuevoPestana2);
                    }
                }
            });

            pestaña2.add(continuarButton);

            // Agregar la pestaña al marco
            JTabbedPane tabbedPane = new JTabbedPane();
            tabbedPane.addTab("Actualizar Estado de Vehículo", null, pestaña2, "Actualizar Estado");
            frame.add(tabbedPane);

            // Hacer visible la ventana
            frame.setVisible(true);
        });
    }

    private static void actualizarPestaña(JFrame frame, JPanel nuevoPanel) {
        JTabbedPane tabbedPane = (JTabbedPane) frame.getContentPane().getComponent(0);
        tabbedPane.setComponentAt(0, nuevoPanel);
        frame.repaint();
        frame.revalidate();
    }
    
   
    
    
    } 



    
