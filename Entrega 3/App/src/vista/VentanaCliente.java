package vista;
import javax.swing.*;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import modelo.Cliente;
import modelo.Inventario;
import modelo.Licencia;
import modelo.Tarjeta;
import modelo.Usuario;
import modelo.Reserva;
import modelo.Vehiculo;
import modelo.alquiler;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;

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
    static Cliente cliente_i;
    static String inputFechaL1;
    static String inputFechaL2;
    static String inputFechaT;
    public VentanaCliente(Cliente cliente_u) {
        cliente_i=cliente_u;
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
        JTabbedPane panel1= new JTabbedPane();
        panel1.add(cambiarClave());
        JTabbedPane panel2= new JTabbedPane();
        panel2.add(cambiar_datos());
        JTabbedPane panel3= new JTabbedPane();
        panel3.add(crearReserva(false,cliente_i,false));
        
        JTabbedPane panel4= new JTabbedPane();
        panel4.add(modificarReserva(cliente_i));

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
                    panel2.add(cambiar_datos());

                }
                else if (selectedIndex==3){
                    VentanaMain.refresh(panel3);
                    panel3.add(crearReserva(false,cliente_i,false));
                }
                else if (selectedIndex==4){
                    VentanaMain.refresh(panel4);
                    panel4.add(modificarReserva(cliente_i));
                }
                else if (selectedIndex==5){
                    VentanaMain.refresh(panel5);
                    panel5.add(cancelarReserva());
                }

        }});
        return panelInferior;
    }
    public static JPanel modificarReserva(Cliente cliente_i){
        JPanel panel = new JPanel();
        int fechaActual= Integer.parseInt(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")));
        DefaultComboBoxModel<String> modeloReservas= new DefaultComboBoxModel<>();
        int numReservas=0;
        if (Reserva.getListaReservas()!=null){
        for(Reserva i: Reserva.getListaReservas()){
            if (alquiler.assignAlquiler(i.getID())==null){
            if(i.getCliente().equals(cliente_i)&& i.getFechaRecoger()>=fechaActual){
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
        panel.add(Box.createRigidArea(new Dimension(0,100)));
        panel.add(new JLabel("Elija la reserva que desea modificar"));
        panel.add(comboBoxReservas);
        JButton avanzar= new JButton("Modificar reserva");
        panel.add(avanzar);
        avanzar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                VentanaMain.refresh(panel);
                String opcion= comboBoxReservas.getSelectedItem().toString();
                String[] partes=opcion.split("/");
                String[] idreserva= partes[0].split(":");
                int id= Integer.parseInt(idreserva[1].trim());
                Reserva reservaElejida= Reserva.assignReserva(id);
                CardsPanels editor= new CardsPanels();
                editor.editorReserva(panel, reservaElejida,false);
                }});
        try{Inventario.updateSistema();}catch(IOException e1) {e1.printStackTrace();}
        }
    else{
        panel.add(new JLabel("No se encontraron reservas para editar."));
    }
    }
    else{
        panel.add(new JLabel("No se encontraron reservas para editar."));
    }
    panel.add(Box.createRigidArea(new Dimension(0,100)));
    return panel;
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
                cliente_i.setPassword(password.getText());
                VentanaMain.CambioGuardadoDialog();
                try{Inventario.updateSistema();}catch(IOException e1) {e1.printStackTrace();}
            }
        });
        panel.add(Box.createRigidArea(new Dimension(0, 200)));
        return panel;
    }
    public static JPanel crearReserva(boolean reservaEnSede,Cliente cliente,boolean tieneDescuento){
        JPanel panel = new JPanel(new GridLayout(0, 1,0,50));
        panel.add(new JLabel(">>>Bienvenido al sistema de reservas"));
        JButton avanzar= new JButton("Crear reserva");
        panel.add(avanzar);
        avanzar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                VentanaMain.refresh(panel);
                Reserva reserva= new Reserva();
                reserva.setCliente(cliente);
                reserva.setReservaEnSede(reservaEnSede);
                CardsPanels editor= new CardsPanels();
                editor.editorReserva(panel, reserva,tieneDescuento);
            }
        });
        try{Inventario.updateSistema();}catch(IOException e1) {e1.printStackTrace();}
        panel.add(Box.createRigidArea(new Dimension(0,100)));
        return panel;
        
    }
    private static JTabbedPane cambiar_datos(){
        JTabbedPane menu = new JTabbedPane(JTabbedPane.LEFT);
        JPanel panelContacto = cambiarContacto();
        menu.add("Datos de Contacto", panelContacto);
        JPanel panelLicencia = cambiarLicencia();
        menu.add("Licencia de Conducción", panelLicencia);
        JPanel panelTarjeta = cambiarTarjeta();
        menu.add("Método de Pago", panelTarjeta);
        
        return menu;
    }
    public static JPanel cambiarContacto() {
        JPanel panelContacto = new JPanel(new GridLayout(2,0));
        JButton btnActualizarTelefono = new JButton("Actualizar Teléfono");
        JButton btnActualizarCorreo = new JButton("Actualizar Correo");
        JButton btnGuardarTelefono = new JButton("Actualizar Teléfono");
        JButton btnGuardarCorreo = new JButton("Actualizar Correo");
        JTextField campoTelefono = new NumericOnlyTextField();
        JTextField campoCorreo = new PlaceHolderTextField("Ej: correo@ejemplo.com");
        btnGuardarTelefono.setEnabled(false);
        btnGuardarCorreo.setEnabled(false);
        campoTelefono.setVisible(false);
        campoCorreo.setVisible(false);
        btnActualizarTelefono.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                campoTelefono.setVisible(true);
                btnActualizarTelefono.setEnabled(false);
                btnActualizarCorreo.setEnabled(false);
                btnGuardarTelefono.setEnabled(true);
            }
        });
        btnActualizarCorreo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                campoCorreo.setVisible(true);
                btnActualizarTelefono.setEnabled(false);
                btnActualizarCorreo.setEnabled(false);
                btnGuardarCorreo.setEnabled(true);
            }
        });
        btnGuardarTelefono.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                campoTelefono.setVisible(false);
                try {
                    Long telefonoN = Long.parseLong(campoTelefono.getText());
                    cliente_i.setTelefono(telefonoN);
                    btnGuardarTelefono.setEnabled(false);
                    btnActualizarTelefono.setEnabled(true);
                    btnActualizarCorreo.setEnabled(true);
                    campoTelefono.setText("");
                    JOptionPane.showMessageDialog(null, "Su número de teléfono fue guardado con éxito.", "¡Éxito!", JOptionPane.INFORMATION_MESSAGE);

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese un número de teléfono válido", "Error", JOptionPane.ERROR_MESSAGE);
                } 
            }
        });
        btnGuardarCorreo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                campoTelefono.setVisible(false);
                String correoN = campoCorreo.getText();
                if (correoN.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "El campo de correo no puede estar vacío", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    cliente_i.setMail(correoN);
                    btnGuardarCorreo.setEnabled(false);
                    btnActualizarTelefono.setEnabled(true);
                    btnActualizarCorreo.setEnabled(true);
                    campoCorreo.setText("");
                    JOptionPane.showMessageDialog(null, "Su correo électrónico fue guardado con éxito.", "¡Éxito!", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        panelContacto.add(btnActualizarTelefono);
        panelContacto.add(campoTelefono);
        panelContacto.add(btnGuardarTelefono);
        panelContacto.add(btnActualizarCorreo);
        panelContacto.add(campoCorreo);
        panelContacto.add(btnGuardarCorreo);
        try {
            Inventario.updateSistema();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return panelContacto;
    }
    public static JPanel cambiarLicencia() {
        JPanel panelLicencia = new JPanel(new GridLayout(0, 2));
        
        JLabel labelNumeroL = new JLabel("Número de Licencia: ");
        NumericOnlyTextField campoNumeroL = new NumericOnlyTextField();
        JLabel labelPais = new JLabel("País de Expedición: ");
        PlaceHolderTextField campoPais = new PlaceHolderTextField("Ej: Colombia");
        JLabel labelFechaE = new JLabel("Fecha de Expedición: ");
        JLabel labelFechaV = new JLabel("Fecha de Vencimiento: ");

        inputFechaL1 = "";
        JPanel panelFecha1= new JPanel();
        panelFecha1.setLayout(new FlowLayout());
        DefaultComboBoxModel<String> opcionesAnio = new DefaultComboBoxModel<>();
        int anioActual= Calendar.getInstance().get(Calendar.YEAR);
        for (int i = anioActual-20; i <= anioActual; i++){
            opcionesAnio.addElement(Integer.toString(i));
        }
        JComboBox<String> anioBox= new JComboBox<String>(opcionesAnio);
        anioBox.setSelectedIndex(0);
        panelFecha1.add(anioBox);
        anioBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
            String anio=anioBox.getSelectedItem().toString();
            VentanaMain.refresh(panelFecha1);
            panelFecha1.add(anioBox);
            anioBox.setEnabled(false);
            DateComboBoxPanel date2= new DateComboBoxPanel(Integer.parseInt(anio));
            date2.setDefaulDayComboBox();
            date2.setDefaultMonthComboBox();
            panelFecha1.add(date2);
            JButton updateDatebutton= new JButton("Cambiar Fecha");
            panelFecha1.add(updateDatebutton);
            JButton saveDatebutton= new JButton("Guardar Fecha");
            panelFecha1.add(saveDatebutton);
            inputFechaL1="";

            saveDatebutton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e){
                    inputFechaL1=anio+date2.getText();
                    VentanaMain.refresh(panelFecha1);
                    panelFecha1.add(anioBox);
                    panelFecha1.add(updateDatebutton);
                } 
            });
            updateDatebutton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e){
                    inputFechaL1="";
                    VentanaMain.refresh(panelFecha1);
                    panelFecha1.add(anioBox);
                    anioBox.setEnabled(true);
                }
            });
            }
        });

        inputFechaL2 = "";
        JPanel panelFecha2= new JPanel();
        panelFecha2.setLayout(new FlowLayout());
        DefaultComboBoxModel<String> opcionesAnio2 = new DefaultComboBoxModel<>();
        for (int j = anioActual; j <= anioActual+20; j++){
            opcionesAnio2.addElement(Integer.toString(j));
        }
        JComboBox<String> anioBox2= new JComboBox<String>(opcionesAnio2);
        anioBox2.setSelectedIndex(0);
        panelFecha2.add(anioBox2);
        anioBox2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
            String anio=anioBox2.getSelectedItem().toString();
            VentanaMain.refresh(panelFecha2);
            panelFecha2.add(anioBox2);
            anioBox2.setEnabled(false);
            DateComboBoxPanel date2= new DateComboBoxPanel(Integer.parseInt(anio));
            date2.setDefaulDayComboBox();
            date2.setDefaultMonthComboBox();
            panelFecha2.add(date2);
            JButton updateDatebutton= new JButton("Cambiar Fecha");
            panelFecha2.add(updateDatebutton);
            JButton saveDatebutton= new JButton("Guardar Fecha");
            panelFecha2.add(saveDatebutton);
            inputFechaL2="";

            saveDatebutton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e){
                    inputFechaL2=anio+date2.getText();
                    VentanaMain.refresh(panelFecha2);
                    panelFecha2.add(anioBox2);
                    panelFecha2.add(updateDatebutton);
                } 
            });
            updateDatebutton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e){
                    inputFechaL2="";
                    VentanaMain.refresh(panelFecha1);
                    panelFecha1.add(anioBox2);
                    anioBox2.setEnabled(true);
                }
            });
            }
        });
        
       JButton botonGuardar = new JButton("Guardar");

        panelLicencia.add(labelNumeroL);
        panelLicencia.add(campoNumeroL);
        panelLicencia.add(labelPais);
        panelLicencia.add(campoPais);
        panelLicencia.add(labelFechaE);
        panelLicencia.add(panelFecha1);
        panelLicencia.add(labelFechaV);
        panelLicencia.add(panelFecha2);
        panelLicencia.add(new JLabel("\n"));
        panelLicencia.add(botonGuardar);

        botonGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String numerolicencia = campoNumeroL.getText();
                String pais = campoPais.getText();
                if (numerolicencia.isEmpty() || pais.isEmpty() || inputFechaL1.isEmpty() || inputFechaL2.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                        try{
                            int numeroLicencia = Integer.parseInt(campoNumeroL.getText());
                            pais = campoPais.getText();
                            int expedicionL = Integer.parseInt(inputFechaL1);
                            int vencimientoL = Integer.parseInt(inputFechaL2);
                            Licencia licenciaNueva = new Licencia(numeroLicencia, expedicionL, vencimientoL, pais);
                            Cliente.getListaLicencias().remove(cliente_i.getLicencia());
                            cliente_i.setTarjeta(null);
                            cliente_i.setLicencia(licenciaNueva);
                            campoNumeroL.setText("");
                            campoPais.setText("");


                            Usuario.addLicencia(licenciaNueva);

                            try {
                                Inventario.updateSistema();
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                            JOptionPane.showMessageDialog(null, "Su Licencia se ha guardado con éxito.", "¡Éxito!", JOptionPane.INFORMATION_MESSAGE);
                        }catch(NumberFormatException e2){
                            VentanaMain.errorDialog("Guarde fechas");
                        } 
                    }
                }
            }
        );
        
        return panelLicencia;
    }
    public static JPanel cambiarTarjeta() {
        JPanel panelTarjeta = new JPanel(new GridLayout(0, 2));
        
        JLabel labelNombreT = new JLabel("Nombre de la persona o entidad Titular: ");
        PlaceHolderTextField campoTitular = new PlaceHolderTextField("Ej: Juan López");
        JLabel labelNumeroT = new JLabel("Número de la Tarjeta: ");
        NumericOnlyTextField campoNumeroT = new NumericOnlyTextField();
        JLabel labelMarca = new JLabel("Marca de la Tarjeta: ");
        PlaceHolderTextField campoMarca = new PlaceHolderTextField("Ej: Visa/Mastercard");
        JLabel labelFechaV = new JLabel("Fecha de Vencimiento: ");
        JPanel panelFechaT = new JPanel(new GridLayout(0, 2));
        String[] meses = {"01", "02", "03", "04", "05", "06", 
                            "07", "08", "09", "10", "11", "12", };
        ArrayList<String> años = new ArrayList<>();
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = currentYear; i < currentYear +16; i++){
            años.add(Integer.toString(i));
        }
        JComboBox<String> monthComboBox = new JComboBox<>(meses);
        JComboBox<String> yearComboBox = new JComboBox<>(años.toArray(new String[0]));
        JButton guardarFechaT = new JButton("Guardar Fecha");
        JButton cambiarFechaT = new JButton("Cambiar Fecha");
        cambiarFechaT.setEnabled(false);
        guardarFechaT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inputFechaT = monthComboBox.getSelectedItem().toString() + yearComboBox.getSelectedItem();
                cambiarFechaT.setEnabled(true);
                monthComboBox.setEnabled(false);
                yearComboBox.setEnabled(false);
            }
        });

        cambiarFechaT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inputFechaT = "";
                monthComboBox.setEnabled(true);
                yearComboBox.setEnabled(true);
            }
        });
        panelFechaT.add(monthComboBox);
        panelFechaT.add(yearComboBox);
        panelFechaT.add(guardarFechaT);
        panelFechaT.add(cambiarFechaT);
        JButton botonGuardar = new JButton("Guardar");

        panelTarjeta.add(labelNombreT);
        panelTarjeta.add(campoTitular);
        panelTarjeta.add(labelNumeroT);
        panelTarjeta.add(campoNumeroT);
        panelTarjeta.add(labelMarca);
        panelTarjeta.add(campoMarca);
        panelTarjeta.add(labelFechaV);
        panelTarjeta.add(panelFechaT);
        panelTarjeta.add(new JLabel("\n"));
        panelTarjeta.add(botonGuardar);

        botonGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String titular = campoTitular.getText();
                Long numeroT = Long.parseLong(campoNumeroT.getText());
                String marca = campoMarca.getText();
                int vencimientoT = Integer.parseInt(inputFechaT);

                if (titular.isEmpty() || campoNumeroT.getText().isEmpty() || marca.isEmpty() || inputFechaT.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
                } else {

                    Tarjeta tarjetaNueva = new Tarjeta(numeroT,false,0L, vencimientoT, marca, titular);
                    cliente_i.setTarjeta(tarjetaNueva);




                    JOptionPane.showMessageDialog(null, "Su tarjeta fue guardada con éxito.", "¡Éxito!", JOptionPane.INFORMATION_MESSAGE);
                    campoTitular.setText("");
                    campoNumeroT.setText("");
                    campoMarca.setText("");
                }
            }
        });

        return panelTarjeta;
    }

    private static JPanel cancelarReserva(){
        JPanel panel = new JPanel();
        int fechaActual= Integer.parseInt(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")));
        DefaultComboBoxModel<String> modeloReservas= new DefaultComboBoxModel<>();
        int numReservas=0;
        if (Reserva.getListaReservas()!=null){
        for(Reserva i: Reserva.getListaReservas()){
            if (alquiler.assignAlquiler(i.getID())==null){
            if(i.getCliente().equals(cliente_i)&& i.getFechaRecoger()>=fechaActual){
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
        panel.add(Box.createRigidArea(new Dimension(0,100)));
        panel.add(new JLabel("Elija la reserva que desea cancelar"));
        panel.add(comboBoxReservas);
        JButton avanzar= new JButton("Cancelar reserva");
        panel.add(avanzar);
        avanzar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                String opcion= comboBoxReservas.getSelectedItem().toString();
                String[] partes=opcion.split("/");
                String[] idreserva= partes[0].split(":");
                int id= Integer.parseInt(idreserva[1].trim());
                Reserva reservaElejida= Reserva.assignReserva(id);
                Reserva.getListaReservas().remove(reservaElejida);
                Vehiculo vehiculoReservaElejida= reservaElejida.getVehiculoAsignado();
                vehiculoReservaElejida.eliminarReservaActiva(reservaElejida.getID());
                VentanaMain.Dialog("La reserva con IDreserva "+Integer.toString(id)+" ha sido cancelada, pronto se trasferirá de vuelta el pago del 30% (COP "+Double.toString(reservaElejida.getPagoReserva())+").");
                VentanaMain.Dialog("Se recomienda reiniciar la APP para tener una buena experiencia de nuestro servicio!");                
                try{Inventario.updateSistema();}catch(IOException e1) {e1.printStackTrace();}
            }
        });
        panel.add(Box.createRigidArea(new Dimension(0, 200)));
    }
    else{
        panel.add(new JLabel("No se encontraron reservas para editar."));
    }
    }
    else{
        panel.add(new JLabel("No se encontraron reservas para editar."));
    }
    panel.add(Box.createRigidArea(new Dimension(0,100)));
    return panel;
    }
        

    
             
}

