package vista2;
import javax.swing.*;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import modelo.Cliente;
import modelo.Inventario;
import modelo.Reserva;
import modelo.Sede;
import vista.CardsPanels;
import vista.VentanaCliente;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class VentanaCliente2 {
    static JFrame frame;
    JPanel panelSuperior;
    JTabbedPane panelInferior;
    static boolean inAction;
    static boolean boolModificar;
    static JComboBox<String> comboBoxSede;

    static Cliente cliente_i;
    static String inputFechaL1;
    static String inputFechaL2;
    static String inputFechaT;
    public VentanaCliente2(Cliente cliente_u) {
        cliente_i=cliente_u;
        frame = new JFrame("Menu Cliente");
        this.panelSuperior= VentanaMain2.setPanelSuperior(frame);
        this.panelInferior= setPanelInferior();
        inAction= false;

        panelInferior.setEnabled(!inAction);

        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setSize(1100, 600);
        frame.setLayout(new BorderLayout());

        // Panel superior
        

        // Panel inferior
        

        frame.add(this.panelSuperior,BorderLayout.NORTH);
        frame.add(this.panelInferior);
        JScrollPane scrollPane= new JScrollPane(panelInferior);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        frame.add(scrollPane);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    private static JTabbedPane setPanelInferior(){

        JTabbedPane panel1= new JTabbedPane();
        panel1.add(VentanaCliente.crearReserva(false,cliente_i,true));
        


        JTabbedPane panel2= new JTabbedPane();
        panel2.add(disponibilidadVehiculos());

        JTabbedPane panelInferior = new JTabbedPane(JTabbedPane.TOP);
        panelInferior.add("Crear reserva",panel1);
        panelInferior.add("Consultar disponibilidad de vehículos",panel2);

        panelInferior.setSelectedIndex(-1);
        panelInferior.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                // Obtener el índice de la pestaña seleccionada
                int selectedIndex = panelInferior.getSelectedIndex();
                if (selectedIndex==1){
                    VentanaMain2.refresh(panel1);
                    panel1.add(VentanaCliente.crearReserva(false,cliente_i,true));
                }
                else if (selectedIndex==2){
                    VentanaMain2.refresh(panel2);
                    panel2.add(disponibilidadVehiculos());
                }
        }});
        return panelInferior;
    }

    private static JPanel disponibilidadVehiculos(){
    JPanel panel= new JPanel();
    panel.add(Box.createRigidArea(new Dimension(0,100)));
    panel.add(new JLabel("Elija la sede en la que desea ver la disponibilidad de vehículos"));
    JComboBox <String>sedes= new JComboBox<String>();
    for (Sede i: Inventario.getListaSedes()){
        sedes.addItem(Integer.toString(i.getID())+": "+i.getNombre());
        }
    sedes.setSelectedIndex(0);
    sedes.setPreferredSize(new Dimension(200, 30));
    panel.add(sedes);
    comboBoxSede=sedes;
    panel.add(comboBoxSede);




    JButton avanzar= new JButton("Siguiente");
    panel.add(avanzar);
    return panel;
    }

             
}


