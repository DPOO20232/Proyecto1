package vista2;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.util.Date;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import modelo.Categoria;
import modelo.Cliente;
import modelo.Inventario;
import modelo.Sede;


import modelo.Vehiculo;
import vista.VentanaCliente;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

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
    protected static int inputFechaFin;
    protected static int inputFechaInicio;
    protected static Categoria categoriaElejida;
    protected static Sede sedeElejida;

    public VentanaCliente2(Cliente cliente_u) {
        cliente_i=cliente_u;
        frame = new JFrame("Menu ClienteV2");
        this.panelSuperior= VentanaMain2.setPanelSuperior(frame);
        this.panelInferior= setPanelInferior();
        inAction= false;

        panelInferior.setEnabled(!inAction);

        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setSize(1100, 600);
        frame.setLayout(new BorderLayout());
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
                    panel1.revalidate();
                }
                else if (selectedIndex==2){
                    VentanaMain2.refresh(panel2);
                    panel2.add(disponibilidadVehiculos());
                    panel2.revalidate();
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
    avanzar.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            VentanaMain2.refresh(panel);
            panel.setLayout(new GridLayout(0,3));
            panel.add(new JLabel("Complete los siguientes campos:"));
            panel.add(new JLabel());
            panel.add(new JLabel());
            panel.add(new JLabel("Categoría"));
            JComboBox<String> categorias= new JComboBox<String>();
            for (Categoria i: Inventario.getListaCategorias()){
                categorias.addItem(Integer.toString(i.getID())+": "+i.getnombreCategoria());
            }
            categorias.setSelectedIndex(0);
            categorias.setPreferredSize(new Dimension(200, 30));
            panel.add(categorias);
            panel.add(new JLabel());
            panel.add(new JLabel("Intervalo de fechas"));
            JSpinner fechaInicioSpinner = new JSpinner(new SpinnerDateModel());
            JSpinner fechaFinSpinner = new JSpinner(new SpinnerDateModel());
    
            // Establecer el formato de fecha para los Spinners
            JSpinner.DateEditor dateEditorInicio = new JSpinner.DateEditor(fechaInicioSpinner, "dd/MM/yyyy");
            JSpinner.DateEditor dateEditorFin = new JSpinner.DateEditor(fechaFinSpinner, "dd/MM/yyyy");
            fechaInicioSpinner.setEditor(dateEditorInicio);
            fechaFinSpinner.setEditor(dateEditorFin);
    
            // Crear un panel para mostrar los Spinners
            JPanel panelFechas = new JPanel(new GridLayout(3, 2));
            panel.add(panelFechas);
            panelFechas.add(new JLabel("Fecha de Inicio:"));
            panelFechas.add(fechaInicioSpinner);
            panelFechas.add(new JLabel("Fecha de Fin:"));
            panelFechas.add(fechaFinSpinner);
            panelFechas.repaint();
            panel.repaint();
            panel.revalidate();
            // Crear el botón para continuar
            JButton continuarButton = new JButton("Avanzar");
            panelFechas.add(continuarButton);
            continuarButton.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    Date fechaInicio = (Date) fechaInicioSpinner.getValue();
                    Date fechaFin = (Date) fechaFinSpinner.getValue();
    
                    // Validar las fechas ingresadas
                    int fechaActual= Integer.parseInt(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")));
                    SimpleDateFormat formato = new SimpleDateFormat("yyyyMMdd");
                    inputFechaInicio = Integer.parseInt(formato.format(fechaInicio));
                    inputFechaFin = Integer.parseInt(formato.format(fechaFin));
                     if (inputFechaInicio<inputFechaFin&&inputFechaInicio>=fechaActual){
                        
                        categoriaElejida = Inventario.assignCategoria(Integer.parseInt(categorias.getSelectedItem().toString().split(":")[0]));
                        sedeElejida= Inventario.assignSede(Integer.parseInt(sedes.getSelectedItem().toString().split(":")[0]));
                        int cantidadDisponible=0;
                        for(Vehiculo i: Inventario.getListaVehiculos()){
                            if (i.getAveriado()==false){
                            if(i.getCategoria().getID()==categoriaElejida.getID() && i.getSede().getID()==sedeElejida.getID()){
                                if(i.actualizarEstado(inputFechaFin,0000 ,inputFechaFin ,0000 ).equals("Disponible")){
                                    cantidadDisponible+=1;
                        }}}}
                        VentanaMain2.Dialog("Contamos con "+cantidadDisponible+" vehículo(s) disponible(s) en la sede "+sedeElejida.getNombre());
                        VentanaMain2.refresh(panel);
                        panel.revalidate();
    
                        }   
                    else {
                        JOptionPane.showMessageDialog(null, "La fecha de inicio debe ser anterior a la fecha de fin y mayor o igual a la fecha actual.", "Error", JOptionPane.ERROR_MESSAGE);
                    }}
            });
        }});
    return panel;
    }

             
}


