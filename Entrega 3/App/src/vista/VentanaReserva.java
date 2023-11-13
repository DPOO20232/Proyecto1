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
    private JButton botonContinuar;
    
    public VentanaReserva() {

        super("Reserva de Vehículo");
        tabbedPane = new JTabbedPane(); //Creación de la ventana
        
        // Pestaña 1: Fecha y hora de recogida
        
        JPanel panel1 = new JPanel(new GridLayout(0,2));
        
        JLabel nombreEmpresa = new JLabel("<NombreEmpresa> \n");
        JLabel textoBienvenida = new JLabel("¡Bienvenido a nuestro sistema de Reserva! \n");
        JLabel fechaRecTitle = new JLabel("Fecha de recogida del vehículo");
        
        botonContinuar = new JButton("Continuar");
        botonContinuar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tabbedPane.setSelectedIndex(1);
            }
        });
        
        panel1.add(nombreEmpresa);
        panel1.add(textoBienvenida);
        panel1.add(fechaRecTitle);
       
        // FECHA RECOGIDA ------------------------------------------------------------------------------------------------------------------------------------------
        JPanel panelFechaRec = new JPanel();       
        panelFechaRec.setLayout(new FlowLayout());
        DefaultComboBoxModel<String> opcionesAnio = new DefaultComboBoxModel<>(); //Se crean y se agregan las opciones disponibles para el año de recogida
        int anioActual = Calendar.getInstance().get(Calendar.YEAR);
        opcionesAnio.addElement(Integer.toString(anioActual));
        opcionesAnio.addElement(Integer.toString(anioActual+1));
        
        JComboBox<String> anioBox = new JComboBox<String>(opcionesAnio);
        anioBox.setSelectedIndex(0);
        anioBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                
                String anio = anioBox.getSelectedItem().toString();
                VentanaMain.refresh(panelFechaRec);
                panelFechaRec.add(anioBox);
                anioBox.setEnabled(false);

                DateComboBoxPanel date1 = new DateComboBoxPanel(Integer.parseInt(anio));
                date1.setDefaulDayComboBox();
                date1.setDefaultMonthComboBox();
                panelFechaRec.add(date1);

                JButton updateDatebutton = new JButton("Cambiar Fecha");
                panelFechaRec.add(updateDatebutton);
                JButton saveDatebutton = new JButton("Guardar Fecha");
                panelFechaRec.add(saveDatebutton);
                saveDatebutton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e){
                        
                        VentanaMain.refresh(panelFechaRec);
                        panelFechaRec.add(anioBox);
                        panelFechaRec.add(updateDatebutton);
                       
                    }
                });
                updateDatebutton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e){
                        
                        VentanaMain.refresh(panelFechaRec);
                        panelFechaRec.add(anioBox);
                        anioBox.setEnabled(true);
                    }
                });
            }
        });
        panelFechaRec.add(anioBox); // Año agregado
        panel1.add(panelFechaRec);
        //
        //TODO: falta la hora de recogida
        // ----------
        
        panel1.add(botonContinuar);
        tabbedPane.add("Fecha recogida", panel1);
        add(tabbedPane);
        setLocationRelativeTo(null);
        setSize(840, 600);
        setVisible(true);

       
        // Pestaña 2: Fecha y hora de devolucion
        JPanel panel2 = new JPanel(new GridLayout(0,2)); 
        JLabel fechaDevolucion = new JLabel("Fecha de recogida del vehículo");
        panel2.add(fechaDevolucion);

        // FECHA DEVOLUCION ------------------------------------------------------------------------------------------------------------------------------------------
        JPanel panelFechaDev = new JPanel();       
        panelFechaDev.setLayout(new FlowLayout());

        DefaultComboBoxModel<String> opcionesAnio2 = new DefaultComboBoxModel<>(); //Se crean y se agregan las opciones disponibles para el año de recogida
        opcionesAnio2.addElement(Integer.toString(anioActual));
        opcionesAnio2.addElement(Integer.toString(anioActual+1));
        
        
        JComboBox<String> anioBox2 = new JComboBox<String>(opcionesAnio2);
        anioBox2.setSelectedIndex(0);
        panelFechaDev.add(anioBox2); // Año agregado
        
        anioBox2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                
                String anio = anioBox2.getSelectedItem().toString();
                VentanaMain.refresh(panelFechaDev);
                panelFechaDev.add(anioBox2);
                anioBox2.setEnabled(false);

                DateComboBoxPanel date2 = new DateComboBoxPanel(Integer.parseInt(anio));
                date2.setDefaulDayComboBox();
                date2.setDefaultMonthComboBox();
                panelFechaDev.add(date2);

                JButton updateDatebutton = new JButton("Cambiar Fecha");
                panelFechaDev.add(updateDatebutton);
                JButton saveDatebutton = new JButton("Guardar Fecha");
                panelFechaDev.add(saveDatebutton);
                saveDatebutton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e){
                        
                        VentanaMain.refresh(panelFechaRec);
                        panelFechaRec.add(anioBox);
                        panelFechaRec.add(updateDatebutton);
                        
                    }
                });
                updateDatebutton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e){
                        
                        VentanaMain.refresh(panelFechaDev);
                        panelFechaDev.add(anioBox2);
                        anioBox2.setEnabled(true);
                    }
            });}});
        //------------------------------------------------------------------------------------------------------------------------------------------

        

        
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaReserva());
    }
}