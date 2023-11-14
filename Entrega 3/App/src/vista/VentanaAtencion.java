package vista;
import javax.swing.*;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import modelo.Cliente;
import modelo.Inventario;
import modelo.Reserva;
import modelo.Sede;
import modelo.Seguro;
import modelo.Usuario;
import modelo.Vehiculo;
import modelo.alquiler;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class VentanaAtencion {
    private static JFrame frame;
    private static JPanel panelSuperior;
    private static JTabbedPane panelInferior;
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
    static Sede sede_personal;
    static Cliente cliente_i;
    public VentanaAtencion(Sede sede_u) {
        sede_personal=sede_u;
        frame = new JFrame("Menu Personal de Atención");
        panelSuperior= VentanaMain.setPanelSuperior(frame);
        panelInferior= hallarCliente();
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setSize(1100, 600);
        frame.setLayout(new BorderLayout());
        frame.add(panelSuperior,BorderLayout.NORTH);
        frame.add(panelInferior);
        JScrollPane scrollPane= new JScrollPane(panelInferior);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        frame.add(scrollPane);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    private static JTabbedPane setPanelInferior(){
        JTabbedPane panel1= new JTabbedPane();
        panel1.add(crearAlquiler());
        JTabbedPane panel2= new JTabbedPane();
        panel2.add(completarAlquiler());
        JTabbedPane panel3= new JTabbedPane();
        panel3.add(VentanaCliente.crearReserva(true));
        JTabbedPane panel4= new JTabbedPane();
        panel4.add(VentanaCliente.modificarReserva(cliente_i));


        JTabbedPane panelInferior = new JTabbedPane(JTabbedPane.TOP);
        panelInferior.add("Iniciar Alquiler",panel1);
        panelInferior.add("Finalizar Alquiler",panel2);
        panelInferior.add("Crear reserva",panel3);
        panelInferior.add("Modificar reserva",panel4);

        panelInferior.setSelectedIndex(-1);
        panelInferior.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                // Obtener el índice de la pestaña seleccionada
                int selectedIndex = panelInferior.getSelectedIndex();
                if (selectedIndex==1){
                    VentanaMain.refresh(panel1);
                    panel1.add(crearAlquiler());
                }
                else if (selectedIndex==2){
                    VentanaMain.refresh(panel2);
                    panel2.add(completarAlquiler());
                }
                else if (selectedIndex==3){
                    VentanaMain.refresh(panel3);
                    panel3.add(VentanaCliente.crearReserva(true));

                }
                else if (selectedIndex==4){
                    VentanaMain.refresh(panel4);
                    panel4.add(VentanaCliente.modificarReserva(cliente_i));
                }

        }});
        return panelInferior;
    }
        private static JTabbedPane hallarCliente(){
        JTabbedPane tabbedPane= new JTabbedPane();        
        JPanel panel= new JPanel();
        cliente_i=null;
        panel.add(Box.createRigidArea(new Dimension(0, 200)));
        JPanel menu = new JPanel(new GridLayout(0, 3,40,100));
        
        menu.add(new JLabel("Ingrese la cédula del cliente:"));
        NumericOnlyTextField cedula= new NumericOnlyTextField();
        JButton avanzar= new JButton("Buscar");
        menu.add(cedula);
        avanzar.setVisible(false);
        menu.add(avanzar);
        panel.add(menu);
        DocumentListener documentListener = new DocumentListener() {
            @Override
                public void insertUpdate(DocumentEvent e) {
                    avanzar.setVisible(!cedula.getText().trim().isEmpty());
                }
                @Override
                public void removeUpdate(DocumentEvent e) {
                    avanzar.setVisible(!cedula.getText().trim().isEmpty());
                }
                @Override
                public void changedUpdate(DocumentEvent e) {
                    avanzar.setVisible(!cedula.getText().trim().isEmpty());
                }
            };
        cedula.getDocument().addDocumentListener(documentListener);
        avanzar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                int cedulaCliente = Integer.parseInt(cedula.getText().trim());
                cliente_i=Usuario.assignCliente(cedulaCliente);
                if(cliente_i==null){
                    VentanaMain.errorDialog("Ingrese una cédula válida");
                }
                else{
                    frame.setTitle("Menu Personal de atención. Cliente en revisión: "+ cliente_i.getNombre());
                    VentanaMain.refresh(panelInferior);
                    panelInferior.add(setPanelInferior());
                    inAction= false;
                    panelInferior.setEnabled(!inAction);
                }
            }
        });
        panel.add(Box.createRigidArea(new Dimension(0, 200)));
        tabbedPane.add(panel);
        return tabbedPane;
    }

        private static JPanel crearAlquiler(){
        JPanel panel= new JPanel();
        JPanel panel_1= new JPanel(new FlowLayout());
        panel.add(Box.createRigidArea(new Dimension(0, 200)));
        int fechaActual= Integer.parseInt(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")));
        LocalTime hora = LocalTime.now();
        int horaActual = hora.getHour() * 100 + hora.getMinute();
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
        panel_1.add(new JLabel("Elija la reserva que desea completar"));
        panel_1.add(comboBoxReservas);
        JButton avanzar= new JButton("Completar Reserva");
        panel_1.add(avanzar);
        panel.add(panel_1);
        panel.add(Box.createRigidArea(new Dimension(0, 200)));

        avanzar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                String opcion= comboBoxReservas.getSelectedItem().toString();
                String[] partes=opcion.split("/");
                String[] idreserva= partes[0].split(":");
                int id= Integer.parseInt(idreserva[1].trim());
                Reserva reserva= Reserva.assignReserva(id);
                //Verificación
                Vehiculo vehiculo=reserva.getVehiculoAsignado();
                vehiculo.eliminarReservaActiva(id);
                Reserva.getListaReservas().remove(reserva);
                boolean sePuedeCompletarReserva=false;
                String estadoActualVehiculo=vehiculo.actualizarEstado(fechaActual, horaActual,reserva.getFechaEntregar(),reserva.getHoraEntregar());
                double pagoReserva=reserva.getPagoReserva();
                //termina verificación
                if (estadoActualVehiculo.equals("Disponible")){
                    sePuedeCompletarReserva=true;
                }
                else{
                    reserva.setVehiculoAsignado();
                    if (reserva.getVehiculoAsignado()!=null)
                    {
                        sePuedeCompletarReserva=true;
                    }
                }
                VentanaMain.refresh(panel);
                if (!sePuedeCompletarReserva){
                    VentanaMain.errorDialog("> Si el cliente requiere, se puede planificar otra reserva para el día de hoy con una categoría de vehículo diferente (opción 3 del menú)." );
                    VentanaMain.errorDialog("Reserva cancelada, Alquiler cancelado. Prontamente se retornará el pago de la reserva (COP"+Double.toString(pagoReserva)+")."); 
                    VentanaMain.errorDialog("Lastimosamente, el vehículo reservado actualmente se encuentra "+estadoActualVehiculo+", y no hay más vehiculos disponibles.");
                }
                else{
                    Reserva.addReserva(reserva);
                    vehiculo.addReservaActiva(reserva);
                    alquiler alquiler_u = new alquiler(reserva);
                    vehiculo.addAlquiler(alquiler_u);
                    alquiler.addAlquiler(alquiler_u);

                    panel.add(agregarSeguros(alquiler_u));
                    panel.repaint();
                    panel.validate();


                    try{Inventario.updateSistema();}catch(IOException e1) {e1.printStackTrace();}
                }

        }});}
        else{
            panel.add(new JLabel("El usuario no tiene reservas activas disponibles para completar el día de hoy"));
        }
        panel.add(Box.createRigidArea(new Dimension(0, 200)));
        return panel;
    }
    private static JPanel agregarSeguros(alquiler alquiler_u){
        JPanel panel= new JPanel();
        panel.add(Box.createRigidArea(new Dimension(0, 100)));                    
        panel.add(new JLabel("Seleccione los seguros que desee agregar al alquiler"));
        JPanel subPanel= new JPanel(new GridLayout(0,1));
        panel.add(subPanel);
        for (Seguro i: Inventario.getListaSeguros()){
            JCheckBox i_CheckBox= new JCheckBox(i.getID()+":"+i.getDescripcion().toString(),false);
            subPanel.add(i_CheckBox);
        }
        panel.add(Box.createRigidArea(new Dimension(0, 100)));
        JButton avanzar= new JButton("Avanzar");
        panel.add(avanzar);
        avanzar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                Component[] componentes = subPanel.getComponents();
                for (Component componente : componentes) {
                    if (componente instanceof JCheckBox) {
                        JCheckBox checkBox = (JCheckBox) componente;
                        if (checkBox.isSelected()) {
                            // Obtener el ID del seguro del texto del CheckBox y agregarlo a la lista
                            String[] partes = checkBox.getText().split(":");
                            if (partes.length == 2) {
                                int idseguro=Integer.parseInt(partes[0]);
                                Seguro seguro = Inventario.assignSeguro(idseguro);
                                alquiler_u.addSeguro(seguro);

                            }
                        }
                    }
                }
                try{Inventario.updateSistema();}catch(IOException e1) {e1.printStackTrace();}
                VentanaMain.CambioGuardadoDialog();
                VentanaMain.refresh(panel);
                panel.add(agregarConductores(alquiler_u));
                panel.repaint();
                panel.revalidate();
            }
        });                   
        return panel;
    }

    private static JPanel agregarConductores(alquiler alquiler_u){
        JPanel panel= new JPanel();
        panel.add(Box.createRigidArea(new Dimension(0, 100)));                    
        panel.add(new JLabel("El cliente va a agregar conductores al registro del alquiler?"));
        ButtonGroup opcion= new ButtonGroup();
        JRadioButton si=new JRadioButton("Si");
        JRadioButton no= new JRadioButton("No");
        opcion.add(si);
        opcion.add(no);
        si.setActionCommand("si");
        no.setActionCommand("no");
        JButton avanzarButton= new JButton("Siguiente");
        panel.add(si);
        panel.add(no);
        panel.add(avanzarButton);
        avanzarButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                ButtonModel selectedItem = opcion.getSelection();
                if (selectedItem!=null){
                    VentanaMain.refresh(panel);
                    double pagoInicial=0;
                    String itemStr = selectedItem.getActionCommand(); // Obtener el texto de la opción seleccionada
                    if (itemStr.equals("si")){
                        panel.add(Box.createRigidArea(new Dimension(0, 100)));                    
                        JPanel panelConductor= new JPanel();
                        panel.add(panelConductor);
                        EditorObjetos editor = new EditorObjetos();
                        SwingUtilities.invokeLater(() -> {
                            editor.agregarConductores(panel, alquiler_u);
                            editor.editar();});
                        pagoInicial=alquiler_u.calcularPagoInicial();
                        SwingUtilities.invokeLater(() -> {
                            VentanaMain.Dialog("Se debitaron COP " +Double.toString(alquiler_u.getPagoFinal()) + " de su tarjeta terminada en "+ Long.toString(alquiler_u.getReserva().getCliente().getTarjeta().getNumeroTarjeta()% 10000)+". En este momento se puede entregar el vehículo al cliente");                    
                        });
                        
                    }
                    else{
                            pagoInicial=alquiler_u.calcularPagoInicial();
                            VentanaMain.Dialog("Se debitaron COP " +Double.toString(alquiler_u.getPagoFinal()) + " de su tarjeta terminada en "+ Long.toString(alquiler_u.getReserva().getCliente().getTarjeta().getNumeroTarjeta()% 10000)+". En este momento se puede entregar el vehículo al cliente");                    
                    }

                    alquiler_u.setPagoFinal(pagoInicial);
                    alquiler_u.setActivo(true);      
                    try{Inventario.updateSistema();}catch(IOException e1) {e1.printStackTrace();}
                }
                else{
                    VentanaMain.errorDialog("Seleccione una opción.");
                }
            }
        });
        panel.add(Box.createRigidArea(new Dimension(0, 100)));                  
        return panel;
    }
    private static JPanel completarAlquiler(){
        JPanel panel= new JPanel();
        JPanel panel_1= new JPanel(new FlowLayout());
        panel.add(panel_1);
        panel.add(Box.createRigidArea(new Dimension(0, 200)));
        int fechaActual= Integer.parseInt(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")));
        LocalTime hora = LocalTime.now();
        int horaActual = hora.getHour() * 100 + hora.getMinute();
        DefaultComboBoxModel<String> modeloAlquileres= new DefaultComboBoxModel<>();
        int numAlquileres=0;
        for(alquiler i: alquiler.getListaAlquileres()){
            if (i.getReserva().getCliente().getNumeroCedula()==cliente_i.getNumeroCedula()&&i.getActivo()==true){
            numAlquileres+=1;
            int id = i.getID();
            String categoria= i.getReserva().getCategoria().getnombreCategoria();
            String placa = i.getReserva().getVehiculoAsignado().getPlaca();
            int fechaRecoger = i.getReserva().getFechaRecoger();
            int fechaEntregar = i.getReserva().getFechaEntregar();
            modeloAlquileres.addElement("id: "+id+" / Placa:" +placa +" / Categoría:" +categoria+" ("+fechaRecoger+"->"+fechaEntregar+")" );
        }
        }
        if (numAlquileres>0){
            JComboBox<String> comboBoxAlquileres= new JComboBox<>(modeloAlquileres);
            comboBoxAlquileres.setSelectedIndex(0);
            panel_1.add(new JLabel("Elija el alquiler que desea completar"));
            panel_1.add(comboBoxAlquileres);
            JButton avanzar= new JButton("Completar Alquiler");
            panel_1.add(avanzar);
            panel.add(panel_1);
            panel.add(Box.createRigidArea(new Dimension(0, 200)));

            avanzar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e){
                    String opcion= comboBoxAlquileres.getSelectedItem().toString();
                    String[] partes=opcion.split("/");
                    String[] idreserva= partes[0].split(":");
                    int id= Integer.parseInt(idreserva[1].trim());
                    alquiler alquiler_u = alquiler.assignAlquiler(id);
                    alquiler_u.setActivo(false);
                    Reserva reserva= alquiler_u.getReserva();
                    double pago70=alquiler_u.getPagoFinal();
                    Vehiculo vehiculo=reserva.getVehiculoAsignado();
                    reserva.setSedeEntregar(sede_personal);
                    reserva.setFechaEntregar(fechaActual);
                    reserva.setHoraEntregar(horaActual);
                    VentanaMain.refresh(panel_1);
                    panel_1.add(new JLabel("Seleccione los daños que tenga el vehículo"));
                    panel_1.add(Box.createRigidArea(new Dimension(0,100)));
                    ButtonGroup averias= new ButtonGroup();
                    JRadioButton cero= new JRadioButton("0. Ningún daño ");
                    JRadioButton uno= new JRadioButton("1. Daño Leve ");
                    JRadioButton dos= new JRadioButton("2. Daño Moderado ");                        
                    JRadioButton tres= new JRadioButton("3. Daño Grave ");
                    cero.setActionCommand("0");
                    uno.setActionCommand("1");
                    dos.setActionCommand("2");
                    tres.setActionCommand("3");
                    averias.add(cero);
                    averias.add(uno);
                    averias.add(dos);
                    averias.add(tres);
                    panel_1.add(cero);
                    panel_1.add(uno);
                    panel_1.add(dos);
                    panel_1.add(tres);
                    JButton avanzar2= new JButton("Concluir alquiler");
                    panel_1.add(avanzar2);
                    panel_1.repaint();
                    avanzar2.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e){
                            ButtonModel selectedButtonModel = averias.getSelection();
                            if (selectedButtonModel != null) {
                                String opcionElegida = selectedButtonModel.getActionCommand();
                                int averia= Integer.parseInt(opcionElegida);
                                double newPago=alquiler_u.calcularPagoFinal(sede_personal,averia);
                                alquiler_u.setPagoFinal(newPago+pago70);
                                vehiculo.eliminarReservaActiva(id);
                                alquiler_u.setActivo(false);
                                if (newPago>0){
                                    VentanaMain.Dialog("El vehículo se ha devuelto correctamente y se han debitado COP "+Double.toString(newPago)+" de su tarjeta terminada en "+ Long.toString(cliente_i.getTarjeta().getNumeroTarjeta()% 10000)+".");
                                    VentanaMain.refresh(panel_1);
                                    }
                                    else{
                                    VentanaMain.Dialog("El vehículo se ha devuelto correctamente y el cliente tiene un saldo a favor de COP "+Double.toString(Math.abs(newPago))+" que se transferirán a su tarjeta terminada en "+ Long.toString(cliente_i.getTarjeta().getNumeroTarjeta()% 10000)+".");
                                    VentanaMain.refresh(panel_1);
                                    }
                                    try {
                                        Inventario.updateSistema();
                                    } catch (IOException e1) {
                                        // TODO Auto-generated catch block
                                        e1.printStackTrace();
                                    }
                            }                            
                            else{
                               VentanaMain.errorDialog("Seleccione una opción.");
                            }
                        }}); 
                        


            }});
        }
        else{
            panel.add(new JLabel("El usuario no tiene alquileres activos"));
            VentanaMain.refresh(panel_1);
        }
        panel.add(Box.createRigidArea(new Dimension(0, 200)));
        return panel;
    }    
}

