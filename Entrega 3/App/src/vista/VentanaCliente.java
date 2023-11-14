package vista;
import javax.swing.*;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import modelo.Cliente;
import modelo.Inventario;
import modelo.Reserva;
import modelo.Vehiculo;
import modelo.alquiler;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class VentanaCliente {
    static JFrame frame;
    JPanel panelSuperior;
    JTabbedPane panelInferior;
    static boolean inAction;
    static boolean boolModificar;
    static JComboBox<String> comboBoxGeneral1Vehi;
    static JComboBox<String> comboBoxGeneral2Vehi;
    static JComboBox<String> comboBoxGeneral3Vehi;
    static JComboBox<String> comboBoxGeneral1Sede;
    static JComboBox<String> comboBoxGeneral2Sede;
    static JComboBox<String> comboBoxGeneral3Sede;
    static JComboBox<String> comboBoxGeneral4Sede;
    static JComboBox<String> comboBoxGeneral5Sede;
    static JComboBox<String> comboBoxGeneral6Sede;
    static JComboBox<String> comboBoxGeneral7Sede;
    static JComboBox<String> comboBoxGeneral8Sede;
    static JComboBox<String> comboBoxGeneral9Sede;
    static JComboBox<String> comboBoxGeneral1Cate;
    static JComboBox<String> comboBoxGeneral2Cate;
    static JComboBox<String> comboBoxGeneral3Cate;
    static JComboBox<String> comboBoxGeneral4Cate;
    static JComboBox<String> comboBoxGeneral5Cate;
    static JComboBox<String> comboBoxGeneral6Cate;
    static JComboBox<String> comboBoxGeneral7Cate;
    static JComboBox<String> comboBoxGeneral8Cate;
    static JComboBox<String> comboBoxGeneral1Seg;
    static Cliente cliente;
    public VentanaCliente(Cliente cliente_u) {
        cliente=cliente_u;
        frame = new JFrame("Menu Cliente");
        this.panelSuperior= VentanaMain.setPanelSuperior(frame);
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
        metodosReserva metodos= new metodosReserva();
        JTabbedPane panel1= new JTabbedPane();
        panel1.add(cambiarClave());
        JTabbedPane panel2= new JTabbedPane();

        JTabbedPane panel3= new JTabbedPane();
        //panel3.add(metodos.menuReserva());

        JTabbedPane panel4= new JTabbedPane();

        JTabbedPane panel5= new JTabbedPane();
        panel5.add(cancelarReserva());

        JTabbedPane panelInferior = new JTabbedPane(JTabbedPane.TOP);

        panelInferior.add("Cambiar Contraseña",panel1);
        panelInferior.add("Actualizar información personal",panel2);
        panelInferior.add("Crear reserva",panel3);
        panelInferior.add("Modificar reserva",panel4);
        panelInferior.add("Cancelar reserva",panel5);

        panelInferior.setSelectedIndex(-1);
        panelInferior.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                // Obtener el índice de la pestaña seleccionada
                int selectedIndex = panelInferior.getSelectedIndex();
                if (selectedIndex==1){
                    VentanaMain.refresh(panel1);
                    panel1.add(cambiarClave());
                }
                else if (selectedIndex==2){
                    VentanaMain.refresh(panel2);
                }
                else if (selectedIndex==3){
                    VentanaMain.refresh(panel3);
                    //panel3.add(metodos.menuReserva());
                }
                else if (selectedIndex==4){
                    VentanaMain.refresh(panel4);
                }
                else if (selectedIndex==5){
                    VentanaMain.refresh(panel5);
                    panel5.add(cancelarReserva());
                }

        }});
        return panelInferior;
    }
        private static JPanel cambiarClave(){
        JPanel panel = new JPanel();
        panel.add(Box.createRigidArea(new Dimension(0, 200)));
        JPanel menu = new JPanel(new GridLayout(0, 3,40,100));

        menu.add(new JLabel("Ingrese la nueva contraseña:"));
        PlaceHolderTextField password= new PlaceHolderTextField("Ej: esteban12");
        JButton avanzar= new JButton("Actualizar contraseña");
        menu.add(password);
        avanzar.setVisible(false);
        menu.add(avanzar);
        panel.add(menu);
        DocumentListener documentListener = new DocumentListener() {
            @Override
                public void insertUpdate(DocumentEvent e) {
                    avanzar.setVisible(!password.getText().trim().isEmpty());
                }
                @Override
                public void removeUpdate(DocumentEvent e) {
                    avanzar.setVisible(!password.getText().trim().isEmpty());
                }
                @Override
                public void changedUpdate(DocumentEvent e) {
                    avanzar.setVisible(!password.getText().trim().isEmpty());
                }
            };
        password.getDocument().addDocumentListener(documentListener);
        avanzar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                cliente.setPassword(password.getText());
                VentanaMain.CambioGuardadoDialog();
                try{Inventario.updateSistema();}catch(IOException e1) {e1.printStackTrace();}
            }
        });
        panel.add(Box.createRigidArea(new Dimension(0, 200)));
        return panel;
    }

     private static JPanel cancelarReserva(){
        JPanel panel = new JPanel();

        DefaultComboBoxModel<String> modeloReservas= new DefaultComboBoxModel<>();
        int numReservas=0;
        for(Reserva i: Reserva.getListaReservas()){
            if (alquiler.assignAlquiler(i.getID())==null){
            if(i.getFechaRecoger()==fechaActual&&i.getCliente().getNumeroCedula()==cliente_i.getNumeroCedula()&& sede_personal.getID()==i.getSedeRecoger().getID()){
            numReservas+=1;
            String idreseva = Integer.toString(i.getID());
            String categoria = i.getCategoria().getnombreCategoria();
            String fechaRecoger = Integer.toString(i.getFechaRecoger());
            String fechaEntregar = Integer.toString(i.getFechaEntregar());
            modeloReservas.addElement("id: "+idreseva+" / Categoría:" +categoria+" ("+fechaRecoger+"->"+fechaEntregar+")" );
        }}
        }
        if (numReservas>0){
        JComboBox<String> comboBoxReservas= new JComboBox<>(modeloReservas);
        comboBoxReservas.setSelectedIndex(0);












        panel.add(Box.createRigidArea(new Dimension(0, 200)));
        JPanel menu = new JPanel(new GridLayout(0, 3,40,100));

        menu.add(new JLabel("Seleccione la reserva que desea cancelar:"));
        JButton avanzar= new JButton("Actualizar contraseña");
        avanzar.setVisible(true);
        menu.add(avanzar);
        panel.add(menu);
        avanzar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                
                int id= reservaElejida.getID();
                Reserva.getListaReservas().remove(reservaElejida);
                Vehiculo vehiculoReservaElejida= reservaElejida.getVehiculoAsignado();
                vehiculoReservaElejida.eliminarReservaActiva(reservaElejida.getID());
                VentanaMain.CambioGuardadoDialog();
                try{Inventario.updateSistema();}catch(IOException e1) {e1.printStackTrace();}
            }
        });
        panel.add(Box.createRigidArea(new Dimension(0, 200)));
        return panel;
    }
        

    
             
}

