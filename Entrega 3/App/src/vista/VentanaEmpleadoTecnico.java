package vista;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JTabbedPane;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;

import modelo.Evento;
import modelo.Inventario;
import modelo.Vehiculo;

public class VentanaEmpleadoTecnico {
    protected static int inputFechaFin;
    protected static int inputFechaInicio;
    protected static int inputHoraFin;
    protected static int inputHoraInicio;
    protected static String accionSeleccionada;
    protected static Vehiculo vehiculo;
    public VentanaEmpleadoTecnico() {
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
                    vehiculo=Inventario.assignVehiculo(placa);
                    if (placa.isEmpty()||vehiculo==null) {
                        JOptionPane.showMessageDialog(frame, "Debe ingresar la placa del vehículo.", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        // Realizar la acción si el campo no está vacío

                        // Crear el nuevo panel para la selección de acción
                        JPanel panelSeleccionAccion = new JPanel();
                        JLabel labelSeleccion = new JLabel("Seleccione la acción a realizar:");

                        JRadioButton lavadoRadioButton = new JRadioButton("EnLavado");
                        JRadioButton mantenimientoRadioButton = new JRadioButton("EnMantenimiento");

                        ButtonGroup buttonGroup = new ButtonGroup();
                        buttonGroup.add(lavadoRadioButton);
                        buttonGroup.add(mantenimientoRadioButton);

                        JButton realizarAccionButton = new JButton("Realizar Acción");
                        realizarAccionButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                // Verificar qué opción fue seleccionada
                                accionSeleccionada = lavadoRadioButton.isSelected() ? "EnLavado" :
                                        (mantenimientoRadioButton.isSelected() ? "EnMantenimiento" : "");

                                if (!accionSeleccionada.isEmpty()) {

                                     // Pedir fechas de inicio y fin con JDateChooser
        

                                    // Preguntar al usuario las fechas de inicio y fin
                                    pedirFechas(pestaña2);
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
    
    private static void pedirFechas(JPanel pestaña2) {
        // Crear Spinners para las fechas de inicio y fin
        JSpinner fechaInicioSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner fechaFinSpinner = new JSpinner(new SpinnerDateModel());

        // Establecer el formato de fecha para los Spinners
        JSpinner.DateEditor dateEditorInicio = new JSpinner.DateEditor(fechaInicioSpinner, "dd/MM/yyyy");
        JSpinner.DateEditor dateEditorFin = new JSpinner.DateEditor(fechaFinSpinner, "dd/MM/yyyy");
        fechaInicioSpinner.setEditor(dateEditorInicio);
        fechaFinSpinner.setEditor(dateEditorFin);

        // Crear un panel para mostrar los Spinners
        JPanel panelFechas = new JPanel(new GridLayout(3, 2));
        panelFechas.add(new JLabel("Fecha de Inicio:"));
        panelFechas.add(fechaInicioSpinner);
        panelFechas.add(new JLabel("Fecha de Fin:"));
        panelFechas.add(fechaFinSpinner);

        // Crear el botón para continuar
        JButton continuarButton = new JButton("Continuar");
        panelFechas.add(continuarButton);

        continuarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener las fechas seleccionadas
                Date fechaInicio = (Date) fechaInicioSpinner.getValue();
                Date fechaFin = (Date) fechaFinSpinner.getValue();

                // Validar las fechas ingresadas
                if (validarFechas(fechaInicio, fechaFin)) {
                    int fechaActual= Integer.parseInt(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")));
                    SimpleDateFormat formato = new SimpleDateFormat("yyyyMMdd");
                    inputFechaInicio = Integer.parseInt(formato.format(fechaInicio));
                    inputFechaFin = Integer.parseInt(formato.format(fechaFin));
                    
                    if (inputFechaInicio<inputFechaFin&&inputFechaInicio>=fechaActual){
                        // Actualizar la información en el panel pestaña2
                        pedirHoras(pestaña2);
                    }
                    
                
                } else {
                    JOptionPane.showMessageDialog(null, "La fecha de inicio debe ser anterior a la fecha de fin y mayor o igual a la fecha actual.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Agregar el panel de fechas al panel principal
        pestaña2.removeAll();
        pestaña2.add(panelFechas);
        pestaña2.revalidate();
        pestaña2.repaint();
    }

    private static boolean validarFechas(Date fechaInicio, Date fechaFin) {
        return fechaInicio.before(fechaFin);
    }
     private static void pedirHoras(JPanel pestaña2) {
        // Crear ComboBoxes para las horas de inicio y fin
        JComboBox<String> horaInicioComboBox = new JComboBox<>(obtenerOpcionesHoras());
        JComboBox<String> horaFinComboBox = new JComboBox<>(obtenerOpcionesHoras());

        // Crear Spinners para los minutos de inicio y fin
        JSpinner minutosInicioSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 59, 1));
        JSpinner minutosFinSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 59, 1));

        // Crear un panel para mostrar los componentes
        JPanel panelHoras = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);  // Espaciado entre componentes

        panelHoras.add(new JLabel("Hora de Inicio:"), gbc);
        gbc.gridx = 1;
        panelHoras.add(horaInicioComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panelHoras.add(new JLabel("Minutos de Inicio:"), gbc);
        gbc.gridx = 1;
        panelHoras.add(minutosInicioSpinner, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panelHoras.add(new JLabel("Hora de Fin:"), gbc);
        gbc.gridx = 1;
        panelHoras.add(horaFinComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panelHoras.add(new JLabel("Minutos de Fin:"), gbc);
        gbc.gridx = 1;
        panelHoras.add(minutosFinSpinner, gbc);

        // Crear el botón para continuar
        JButton continuarButton = new JButton("Continuar");
        
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;  // Ocupar las dos columnas
        gbc.anchor = GridBagConstraints.PAGE_END;  // Colocar al final
        panelHoras.add(continuarButton, gbc);

        continuarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener las horas y minutos seleccionados
                String horaInicio = horaInicioComboBox.getSelectedItem().toString();
                String minutosInicio = minutosInicioSpinner.getValue().toString();
                String horaFin = horaFinComboBox.getSelectedItem().toString();
                String minutosFin = minutosFinSpinner.getValue().toString();

                // Validar las horas y minutos ingresados
                if (validarHoras(horaInicio, minutosInicio, horaFin, minutosFin)) {
                    inputHoraInicio = Integer.parseInt(horaInicio + minutosInicio);
                    inputHoraFin = Integer.parseInt(horaFin + minutosFin);
                    Evento evento = new Evento(inputFechaInicio, inputFechaFin, inputHoraInicio, inputHoraFin, accionSeleccionada);
                    vehiculo.addEvento(evento);
                    try {
                        Inventario.updateSistema();
                    } catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                    
                } else {
                    JOptionPane.showMessageDialog(null, "La hora de inicio debe ser anterior a la hora de fin.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Agregar el panel de horas al panel principal
        pestaña2.removeAll();
        pestaña2.add(panelHoras);
        pestaña2.revalidate();
        pestaña2.repaint();
    }

    private static String[] obtenerOpcionesHoras() {
        String[] opcionesHoras = new String[24];
        for (int i = 0; i < 24; i++) {
            opcionesHoras[i] = String.format("%02d", i);
        }
        return opcionesHoras;
    }

    private static boolean validarHoras(String horaInicio, String minutosInicio, String horaFin, String minutosFin) {
        if (minutosInicio.length() == 1) {
            minutosInicio = "0" + minutosInicio;
        }
    
        if (minutosFin.length() == 1) {
            minutosFin = "0" + minutosFin;
        }
    
        int minutosInicioInt = Integer.parseInt(minutosInicio);
        int minutosFinInt = Integer.parseInt(minutosFin);
    
        if (minutosInicioInt > 59 || minutosFinInt > 59) {
            return false;
        }
    
        return true;
    }
}
 



    
