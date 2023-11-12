package vista;

import javax.swing.DefaultComboBoxModel;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentListener;
import javax.swing.event.DocumentEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.io.IOException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

public class VentanaReserva extends JFrame {
    private JTabbedPane tabbedPane;
    JPanel panelSuperior;
    private JLabel nombreEmpresa;
    private JLabel textoBienvenida;
    private JLabel instrucciones;
    private JLabel etiquetaDatos;
    private JButton botonContinuar;
    private PlaceHolderTextField campoNombre;
    private String inputFechaNacimiento;
    
    
    
    public VentanaReserva() {

        super("Registro de Usuario");

        tabbedPane = new JTabbedPane();
        JPanel panelDatos = new JPanel(new GridLayout(0,2));

        nombreEmpresa = new JLabel("<NombreEmpresa> \n");
        textoBienvenida = new JLabel("¡Bienvenido a nuestro sistema de Reserva! \n");
        

        panelDatos.add(nombreEmpresa);
        panelDatos.add(textoBienvenida);

        
        JLabel fechaRecogida = new JLabel(" Fecha y hora de recogida del vehículo");
        panelDatos.add(fechaRecogida);
        
        // FECHA RECOGIDA ------------------------------------------------------------------------------------------------------------------------------------------
        JPanel panelFecha = new JPanel();       
        panelFecha.setLayout(new FlowLayout());

        DefaultComboBoxModel<String> opcionesAnio = new DefaultComboBoxModel<>(); //Se crean y se agregan las opciones disponibles para el año de recogida
        int anioActual = Calendar.getInstance().get(Calendar.YEAR);
        opcionesAnio.addElement(Integer.toString(anioActual));
        opcionesAnio.addElement(Integer.toString(anioActual+1));
        opcionesAnio.addElement(Integer.toString(anioActual+2));
        
        JComboBox<String> anioBox = new JComboBox<String>(opcionesAnio);
        anioBox.setSelectedIndex(0);
        panelFecha.add(anioBox); // Año agregado
        
        anioBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
            String anio = anioBox.getSelectedItem().toString();
            VentanaMain.refresh(panelFecha);
            panelFecha.add(anioBox);
            anioBox.setEnabled(false);
            DateComboBoxPanel date1= new DateComboBoxPanel(Integer.parseInt(anio));
            date1.setDefaulDayComboBox();
            date1.setDefaultMonthComboBox();
            panelFecha.add(date1);

            JButton updateDatebutton = new JButton("Cambiar Fecha");
            panelFecha.add(updateDatebutton);
            JButton saveDatebutton = new JButton("Guardar Fecha");
            panelFecha.add(saveDatebutton);
            saveDatebutton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e){
                    inputFechaNacimiento=anio+date1.getText();
                    VentanaMain.refresh(panelFecha);
                    panelFecha.add(anioBox);
                    panelFecha.add(updateDatebutton);
                    System.out.println(inputFechaNacimiento);
                }    
            });
            updateDatebutton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e){
                    inputFechaNacimiento="";
                    VentanaMain.refresh(panelFecha);
                    panelFecha.add(anioBox);
                    anioBox.setEnabled(true);
                }
            });}});
        //------------------------------------------------------------------------------------------------------------------------------------------
        //TODO: falta la hora de recogida

        JPanel panelHora = new JPanel();
        panelHora.setLayout(new FlowLayout());

        DefaultComboBoxModel<String> opcionesHora = new DefaultComboBoxModel<>(); //Se crean y se agregan las opciones disponibles para el año de recogida
        int horaApertura = 800;
        for (int i = horaApertura;i < 1700;i+=100){
            opcionesHora.addElement(Integer.toString(i));
        }
        JComboBox<String> horaBox = new JComboBox<String>(opcionesHora);
        horaBox.setSelectedIndex(0);
        panelHora.add(horaBox); // Hora agregada

        // --------
        botonContinuar = new JButton("Continuar");
        botonContinuar.setPreferredSize(new Dimension(50, 30));
        botonContinuar.setEnabled(false);
        DocumentListener documentListener = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                habilitarBotonContinuar();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                habilitarBotonContinuar();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                habilitarBotonContinuar();
            }
            public void habilitarBotonContinuar() {
                boolean habilitar = true;

                botonContinuar.setEnabled(habilitar);
            }
        };

   

        botonContinuar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tabbedPane.remove(panelDatos);
                crearUsuario();
                botonContinuar.setEnabled(false);
            }
        });

        panelDatos.add(botonContinuar);
        tabbedPane.add("Fecha recogida", panelDatos);
        add(tabbedPane);
        setLocationRelativeTo(null);
        setSize(840, 600);
        setVisible(true);
    }
        
        
    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaReserva());
    }
}