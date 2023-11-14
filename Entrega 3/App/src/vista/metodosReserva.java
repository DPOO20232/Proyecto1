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

import modelo.Categoria;
import modelo.Inventario;
import modelo.Sede;

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

public class metodosReserva extends JFrame {
    private JTabbedPane tabbedPane;
    JPanel panelSuperior;
    private JButton botonContinuar;
    
    public JTabbedPane menuReserva() {

        //super("Reserva de Vehículo");
        tabbedPane = new JTabbedPane(); //Creación de la ventana
        
        // Pestaña 1: Fecha y hora de recogida y sede de recogida -----------------------------------------------------------------------------------------------------------------------------------------
        
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
       
        // Fecha de recogida 
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
        
        // Hora de recogida
        JPanel panelHoraRec = new JPanel();

        String[] opcionesMinutosRec = {"00"};
        DefaultComboBoxModel<String> opcionesHoraRec = new DefaultComboBoxModel<>();
        
        JComboBox<String> horaRecBox = new JComboBox<>(opcionesHoraRec);
        JComboBox<String> minRecBox = new JComboBox<>(opcionesMinutosRec);
                
        horaRecBox.setSelectedIndex(0);
        minRecBox.setSelectedIndex(0);

        panelHoraRec.add(horaRecBox);
        panelHoraRec.add(minRecBox);
        panel1.add(panelHoraRec);

        // Sede de recogida
        JPanel panelSedeRec = new JPanel();
        JComboBox<String> sedesRec = new JComboBox<>(); // El JComboBox se coloca en el centro del panel
        for (Sede i : Inventario.getListaSedes()) {
            sedesRec.addItem(Integer.toString(i.getID()) + ": " + i.getNombre());
        }
        sedesRec.setSelectedIndex(0);
        panelSedeRec.add(sedesRec, BorderLayout.CENTER);
        panel1.add(panelSedeRec);
        
        //Continuar
        panel1.add(botonContinuar);
        
        // ----------
        // Pestaña 2: Fecha y hora de devolucion -----------------------------------------------------------------------------------------------------------------------------------------
        
        JPanel panel2 = new JPanel(new GridLayout(0,2));
        
        panel2.add(new JLabel("Fecha de recogida del vehículo"));
        botonContinuar = new JButton("Continuar");
        botonContinuar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tabbedPane.setSelectedIndex(1);
            }
        });
        
        // Fecha devolución

        JPanel panelFechaDev = new JPanel();       
        panelFechaRec.setLayout(new FlowLayout());
        DefaultComboBoxModel<String> opcionesAnio2 = new DefaultComboBoxModel<>(); //Se crean y se agregan las opciones disponibles para el año de recogida
        
        opcionesAnio2.addElement(Integer.toString(anioActual));
        opcionesAnio2.addElement(Integer.toString(anioActual+1));
        
        JComboBox<String> anioBox2 = new JComboBox<String>(opcionesAnio);
        anioBox2.setSelectedIndex(0);
        anioBox2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                
                String anio = anioBox2.getSelectedItem().toString();
                VentanaMain.refresh(panelFechaDev);
                panelFechaDev.add(anioBox2);
                anioBox2.setEnabled(false);

                DateComboBoxPanel date1 = new DateComboBoxPanel(Integer.parseInt(anio));
                date1.setDefaulDayComboBox();
                date1.setDefaultMonthComboBox();
                panelFechaRec.add(date1);

                JButton updateDatebutton = new JButton("Cambiar Fecha");
                panelFechaDev.add(updateDatebutton);
                JButton saveDatebutton = new JButton("Guardar Fecha");
                panelFechaDev.add(saveDatebutton);
                saveDatebutton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e){
                        VentanaMain.refresh(panelFechaDev);
                        panelFechaDev.add(anioBox2);
                        panelFechaDev.add(updateDatebutton);
                    }
                });
                updateDatebutton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e){
                        
                        VentanaMain.refresh(panelFechaDev);
                        panelFechaDev.add(anioBox2);
                        anioBox2.setEnabled(true);
                    }
                });
            }
        });
        panelFechaDev.add(anioBox2); // Año agregado
        panel2.add(panelFechaDev);

        //Hora de devolución
        JPanel panelHoraDev = new JPanel();

        String[] opcionesMinutosDev = {"00"};
        DefaultComboBoxModel<String> opcionesHoraDev = new DefaultComboBoxModel<>();
        
        JComboBox<String> horaDevBox = new JComboBox<>(opcionesHoraDev);
        JComboBox<String> minDevBox = new JComboBox<>(opcionesMinutosDev);
                
        horaDevBox.setSelectedIndex(0);
        minDevBox.setSelectedIndex(0);
        panelHoraDev.add(horaDevBox);
        panelHoraDev.add(minDevBox);
        panel2.add(panelHoraDev);

        // Sede de devolución
        JPanel panelSedeDev = new JPanel();
        JComboBox<String> sedesDev = new JComboBox<>(); // El JComboBox se coloca en el centro del panel
        for (Sede i : Inventario.getListaSedes()) {
            sedesDev.addItem(Integer.toString(i.getID()) + ": " + i.getNombre());
        }
        sedesDev.setSelectedIndex(0);
        panelSedeDev.add(sedesDev, BorderLayout.CENTER);
        panel2.add(panelSedeDev);
        
        //Continuar
        panel2.add(botonContinuar);
        
        // ----------
        // Pestaña 3: Seleccionar categoría -----------------------------------------------------------------------------------------------------------------------------------------
        JPanel panel3 = new JPanel();
        panel3.setPreferredSize(new Dimension(200, 400)); // Ajustar las dimensiones 
        panel3.setLayout(new BorderLayout(20, 0));
        panel3.add(new JLabel("Seleccione la categoría"), BorderLayout.NORTH);// El JLabel se coloca en la parte superior del panel
        
        JPanel panelCategorias = new JPanel();
        JComboBox<String> categBox = new JComboBox<>(); // El JComboBox se coloca en el centro del panel
        for (Categoria i : Inventario.getListaCategorias()) {
            categBox.addItem(Integer.toString(i.getID()) + ": " + i);
        }
        categBox.setSelectedIndex(0);
        panelCategorias.add(categBox, BorderLayout.CENTER);
        
        panel3.add(panelCategorias);
        tabbedPane.add("Información de recogida", panel1);
        tabbedPane.add("Información de devolucion", panel2);
        tabbedPane.add("Selección de categoría", panel3);

        return tabbedPane;
    }
    /*
    public static void main(String[] args) {
        Inventario.loadSistema();
        JFrame frame = new JFrame();
        frame.setSize(400,400);
        metodosReserva metodos = new metodosReserva();
        frame.add(metodos.menuReserva());
        frame.setVisible(true);
        
    }
    */
}