package vista;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.JTextComponent;
import java.util.ArrayList;
import java.util.List;
import modelo.Categoria;
import modelo.Cliente;
import modelo.Inventario;
import modelo.Licencia;
import modelo.Sede;
import modelo.Seguro;
import modelo.Tarjeta;
import modelo.Usuario;
import modelo.Vehiculo;
import modelo.personal;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
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
    static Cliente cliente;
    static String inputFechaL1;
    static String inputFechaL2;
    static String inputFechaT;

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
        JTabbedPane panel1= new JTabbedPane();
        panel1.add(cambiarClave());
        JTabbedPane panel2= new JTabbedPane();
        panel2.add(cambiar_datos());
        JTabbedPane panel3= new JTabbedPane();
        
        JTabbedPane panel4= new JTabbedPane();

        JTabbedPane panel5= new JTabbedPane();

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
                }
                else if (selectedIndex==4){
                    VentanaMain.refresh(panel4);
                }
                else if (selectedIndex==5){
                    VentanaMain.refresh(panel5);
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
                    cliente.setTelefono(telefonoN);
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
                    cliente.setMail(correoN);
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
                    if (Usuario.checkLicencia(Integer.parseInt(campoNumeroL.getText()))){
                        JOptionPane.showMessageDialog(null, "Este número de licencia ya fue utilizado. Por favor, ingrese otro.", "Registro", JOptionPane.INFORMATION_MESSAGE);
                        campoNumeroL.setText("");
                        campoPais.setText("");
                    } else {
                        try{
                            int numeroLicencia = Integer.parseInt(campoNumeroL.getText());
                            pais = campoPais.getText();
                            int expedicionL = Integer.parseInt(inputFechaL1);
                            int vencimientoL = Integer.parseInt(inputFechaL2);
                            Licencia licenciaNueva = new Licencia(numeroLicencia, expedicionL, vencimientoL, pais);
                            cliente.setLicencia(licenciaNueva);
                            campoNumeroL.setText("");
                            campoPais.setText("");
                            JOptionPane.showMessageDialog(null, "Su Licencia se ha guardado con éxito.", "¡Éxito!", JOptionPane.INFORMATION_MESSAGE);
                        }catch(NumberFormatException e2){
                            VentanaMain.errorDialog("Guarde fechas");
                        } 
                    }
                }
            }
        });
        
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
                    
                    Tarjeta tarjetaNueva = new Tarjeta(numeroT, vencimientoT, marca, titular);
                    cliente.setTarjeta(tarjetaNueva);
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
    private static JTabbedPane menuSedes(){
        JTabbedPane menu = new JTabbedPane(JTabbedPane.LEFT);
        //Opcion 1
        JPanel panel1= new JPanel();
        nuevoPanel1Sedes(panel1);
        menu.add("Registrar Sede",panel1);
        //Opcion 2
        JPanel panel2= new JPanel();
        nuevoPanel2Sedes(panel2);
        menu.add("Modificar Sede",panel2);
        menu.setSelectedIndex(-1);
        menu.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                // Obtener el índice de la pestaña seleccionada
                int selectedIndex = menu.getSelectedIndex();
                if (selectedIndex==1){
                    nuevoPanel1Sedes(panel1);
                }
                else {nuevoPanel2Sedes(panel2);}
        }});
        return menu;
    }
    private static JTabbedPane menuSeguros(){
        JTabbedPane menu = new JTabbedPane(JTabbedPane.LEFT);
        //Opcion 1
        JPanel panel1= new JPanel();
        nuevoPanel1Seguros(panel1);
        menu.add("Registrar Seguro",panel1);
        //Opcion 2
        JPanel panel2= new JPanel();
        nuevoPanel2Seguros(panel2);
        menu.add("Modificar Seguro",panel2);
        //Opcion 3
        JPanel panel3= new JPanel();
        nuevoPanel3Seguros(panel3);
        menu.add("Eliminar Seguro",panel3);
        menu.setSelectedIndex(-1);
        menu.addChangeListener(new ChangeListener() {
        public void stateChanged(ChangeEvent e) {
        // Obtener el índice de la pestaña seleccionada
            int selectedIndex = menu.getSelectedIndex();
                if (selectedIndex==1){
                    nuevoPanel1Seguros(panel1);
                }
                else if (selectedIndex==2) {
                    nuevoPanel2Seguros(panel2);}
                else {nuevoPanel3Seguros(panel3);}
        }});
        return menu;
    }
    private static JTabbedPane menuPersonal(){
        JTabbedPane menu = new JTabbedPane(JTabbedPane.LEFT);
        JPanel panel1 = new JPanel();
        nuevoPanel1Personal(panel1);
        menu.add("Registrar administrador local",panel1);
        JPanel panel2= new JPanel();
        nuevoPanel2Personal(panel2);
        menu.add("Actualizar sede de un administrador local",panel2);
        menu.setSelectedIndex(-1);
        menu.addChangeListener(new ChangeListener() {
        public void stateChanged(ChangeEvent e) {
        // Obtener el índice de la pestaña seleccionada
            int selectedIndex = menu.getSelectedIndex();
                if (selectedIndex==1){
                    nuevoPanel1Personal(panel1);
                }
                else{nuevoPanel2Personal(panel2);}
        }});
        return menu;
    }
    private static JTabbedPane menuTarifasPeriodos(){
        JTabbedPane menu = new JTabbedPane(JTabbedPane.LEFT);
        JPanel panel1= new JPanel();
        nuevoPanelTarifas(panel1,1);
        menu.add("Actualizar costo por conductor adicional",panel1);
        JPanel panel2= new JPanel();
        nuevoPanelTarifas(panel2,2);
        menu.add("Actualizar costo por traslado de vehículo",panel2);
        JPanel panel3= new JPanel();
        nuevoPanelPeriodos(panel3,1);
        menu.add("Actualizar periodo de temporada alta",panel3);
        JPanel panel4= new JPanel();
        nuevoPanelPeriodos(panel4,2);
        menu.add("Actualizar periodo de temporada baja",panel4);
        menu.setSelectedIndex(-1);
        menu.addChangeListener(new ChangeListener() {
        public void stateChanged(ChangeEvent e) {
        // Obtener el índice de la pestaña seleccionada
            int selectedIndex = menu.getSelectedIndex();
                if (selectedIndex==1){
                    nuevoPanelTarifas(panel1,1);
                }
                else if (selectedIndex==2){nuevoPanelTarifas(panel2,2);
                }
                else if (selectedIndex==3){nuevoPanelPeriodos(panel3,1);}
                else {nuevoPanelPeriodos(panel4,2);}
        }});
        return menu;
    }
    private static JTabbedPane menuAltoNivel(){
        JTabbedPane menu = new JTabbedPane(JTabbedPane.LEFT);
        JPanel panel1= new JPanel();
        nuevoPanelAltoNivel(panel1);
        menu.add("Visualizacion de alto nivel",panel1);
        menu.setSelectedIndex(-1);
        menu.addChangeListener(new ChangeListener() {
        public void stateChanged(ChangeEvent e) {
        // Obtener el índice de la pestaña seleccionada
            int selectedIndex = menu.getSelectedIndex();
                if (selectedIndex==1){
                    nuevoPanelAltoNivel(panel1);
                }

        }});
        return menu;
    }
    
    private static void nuevoPanelAltoNivel(JPanel panel1){
        VentanaMain.refresh(panel1);
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
        panel1.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 0)); // Establece un FlowLayout sin relleno
        panel1.add(new JLabel("Elija la sede de la que desea visualizar la gráfica de alto nivel"));
        JComboBox<String> sedes = new JComboBox<>();
        for (Sede i: Inventario.getListaSedes()){
            sedes.addItem(Integer.toString(i.getID())+": "+i.getNombre());
        }
        sedes.setSelectedIndex(0);
        sedes.setPreferredSize(new Dimension(200, 30));
        panel1.add(sedes);
        JButton avanzarButton= new JButton("Continuar");
        panel1.add(avanzarButton);
        comboBoxGeneral3Vehi=sedes;
        avanzarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                int idSede= Integer.parseInt(comboBoxGeneral3Vehi.getSelectedItem().toString().split(":")[0]);
                Sede sedeElegida= Inventario.assignSede(idSede);
                MonthlyCalendarPanel vista= new MonthlyCalendarPanel(sedeElegida);
                vista.setMonthlyCalendarPanel();
                //VentanaMain.refresh(panel1);
            }
        });
    }
    private static void nuevoPanel1Vehiculo(JPanel panel1){
        VentanaMain.refresh(panel1);
        //
        PlaceHolderTextField placa=new PlaceHolderTextField("Ej: ABC123");
        PlaceHolderTextField marca= new PlaceHolderTextField("Ej: Chevrolet");
        PlaceHolderTextField modelo = new PlaceHolderTextField("Ej: Aveo");
        PlaceHolderTextField color= new PlaceHolderTextField("Ej: Azul");
        ButtonGroup opcionTransmision= new ButtonGroup();
        JComboBox <String>categorias = new JComboBox<String>();
        JComboBox <String>sedes= new JComboBox<String>();
        //
        panel1.setPreferredSize(new Dimension(0, 400));
        panel1.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0)); // Establece un FlowLayout sin relleno
        GridLayout gridLayout1 = new GridLayout(0, 2);
        gridLayout1.setVgap(10);
        panel1.add(new JLabel("Ingrese la información del nuevo vehículo"));
        panel1.add(new JLabel(""));
        panel1.setLayout(gridLayout1);
        panel1.add(new JLabel("Placa:"));
        panel1.add(placa);
        panel1.add(new JLabel("Marca:"));
        panel1.add(marca);
        panel1.add(new JLabel("Modelo:"));
        panel1.add(modelo);
        panel1.add(new JLabel("Color:"));
        panel1.add(color);
        panel1.add(new JLabel("Transmisión:"));
        panel1.add(new JLabel(""));
        JRadioButton manual=new JRadioButton("Manual");
        JRadioButton automatica= new JRadioButton("Automática");
        opcionTransmision.add(manual);
        opcionTransmision.add(automatica);
        manual.setActionCommand("manual");
        automatica.setActionCommand("automatico");

        panel1.add(manual);
        panel1.add(automatica);
        panel1.add(new JLabel("Categoria:"));
        for (Categoria i: Inventario.getListaCategorias()){
            categorias.addItem(i.getID()+": "+i.getnombreCategoria());
        }
        categorias.setPreferredSize(new Dimension(100, 20));

        categorias.setSelectedIndex(0);
        panel1.add(categorias);
        panel1.add(new JLabel("Sedes:"));
        sedes = new JComboBox<>();
            for (Sede i: Inventario.getListaSedes()){
                sedes.addItem(Integer.toString(i.getID())+": "+i.getNombre());
            }
        sedes.setSelectedIndex(0);
        sedes.setPreferredSize(new Dimension(200, 30));
        panel1.add(sedes);

        comboBoxGeneral1Vehi=categorias;
        comboBoxGeneral2Vehi=sedes;
        JButton avanzar1= new JButton("Registrar vehículo");
        panel1.add(avanzar1);
        avanzar1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                String placaStr= placa.getText().toString();
                String marcaStr= marca.getText().toString();
                String modeloStr= modelo.getText().toString();
                String colorStr= color.getText().toString();
                ButtonModel selectedTransmision = opcionTransmision.getSelection();
                if (!placaStr.equals("")&&!marcaStr.equals("")&&!modeloStr.equals("")&&!colorStr.equals("")&& selectedTransmision!=null){
                    boolean encontrado=false;
                    for (Vehiculo i: Inventario.getListaVehiculos()){
                    if (i.getPlaca().equals(placaStr)){
                        encontrado=true;
                        break;
                        }
                    }  
                    if (encontrado==false){
                        String transmisionStr = selectedTransmision.getActionCommand(); // Obtener el texto de la opción seleccionada

                        int IDcategoria= Integer.parseInt(comboBoxGeneral1Vehi.getSelectedItem().toString().split(":")[0]);
                        Categoria categoriaElejida= Inventario.assignCategoria(IDcategoria);

                        int idSede= Integer.parseInt(comboBoxGeneral2Vehi.getSelectedItem().toString().split(":")[0]);
                        Sede sedeElejida= Inventario.assignSede(idSede);

                        Vehiculo vehiculo = new Vehiculo(placaStr, marcaStr, modeloStr, colorStr, transmisionStr, "No disponible :c", "disponible", false, categoriaElejida, sedeElejida);
                        Inventario.getListaVehiculos().add(vehiculo);
                        
                        try{Inventario.updateSistema();}catch(IOException e1) {e1.printStackTrace();}


                        VentanaMain.CambioGuardadoDialog();
                        VentanaMain.refresh(panel1);
                    }
                    else{
                    VentanaMain.errorDialog("El vehículo con la placa ingresada ya existe.");

                    }
                }
                else{
                    VentanaMain.errorDialog("Complete todos los campos requeridos.");
                }

            }
        });
    }
    private static void nuevoPanel2Vehiculo(JPanel panel2){
        VentanaMain.refresh(panel2);
        //
        PlaceHolderTextField placa=new PlaceHolderTextField("Placa");
        //
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
            //Busqueda Carro
        JPanel panel2a= new JPanel();
        panel2a.setPreferredSize(new Dimension(0, 80));
        panel2a.setLayout(new BoxLayout(panel2a, BoxLayout.Y_AXIS));
        panel2a.add(new JLabel("Ingrese la placa del vehículo que desea monitorear/actualizar"));
        panel2a.add(new JLabel("\n"));
        placa.setPreferredSize(new Dimension(30, 20)); // Establece el tamaño preferido a 200x30 píxeles
        panel2a.add(placa);
        panel2a.add(new JLabel("\n"));
        JButton avanzar2a= new JButton("Buscar");
        avanzar2a.setVisible(false);
        panel2a.add(avanzar2a);

        DocumentListener documentListener = new DocumentListener() {
            @Override
                public void insertUpdate(DocumentEvent e) {
                    avanzar2a.setVisible(!placa.getText().trim().isEmpty());
                }
                @Override
                public void removeUpdate(DocumentEvent e) {
                    avanzar2a.setVisible(!placa.getText().trim().isEmpty());
                }
                @Override
                public void changedUpdate(DocumentEvent e) {
                    avanzar2a.setVisible(!placa.getText().trim().isEmpty());
                }
            };
        placa.getDocument().addDocumentListener(documentListener);



        panel2.add(panel2a);
        JPanel panel2b= new JPanel();
        panel2b.add(Box.createRigidArea(new Dimension(0,320)));
        panel2.add(panel2b);

        avanzar2a.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                if (!placa.getText().toString().equals("")){
                    Vehiculo vehiculo= Inventario.assignVehiculo(placa.getText().toString());
                    if (vehiculo!=null){
                    nuevoPanel2AuxVehiculo(vehiculo,panel2,panel2b);
                    avanzar2a.setVisible(false);}
                else{
                    VentanaMain.errorDialog("No existen vehículos en el inventario con la placa ingresada.");
                }
                    
                }
                
            }});
        }
        private static void nuevoPanel2AuxVehiculo(Vehiculo vehiculo,JPanel panel2, JPanel panel2b){
            panel2.revalidate();
            panel2.repaint();
                //Resumen Carro
            panel2b.removeAll();
            panel2b.setPreferredSize(new Dimension(0, 120));
            String resumen = vehiculo.resumenStatus();
            JLabel labelResumen = new JLabel(resumen); 
            panel2b.add(labelResumen);
                //Menu interno
            JTabbedPane menuInterno = new JTabbedPane();
            menuInterno.setPreferredSize(new Dimension(0, 200));
                //Panel dar de baja
            JPanel panelBaja= new JPanel();
            JButton darBajaButton= new JButton("Dar de baja");
            panelBaja.add(darBajaButton);
            menuInterno.add("Dar de baja", panelBaja);
            darBajaButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e){
                    Inventario.getListaVehiculos().remove(vehiculo);
                    
                    try{Inventario.updateSistema();}catch(IOException e1) {e1.printStackTrace();}

                    VentanaMain.CambioGuardadoDialog();
                    VentanaMain.refresh(panel2);
                }
            });
                //Panel archivo log
            JPanel panelLog= new JPanel();
            JButton logButton= new JButton("Obtener archivo");
            panelLog.add(logButton);
            menuInterno.add("Obtener archivo log", panelLog);
            logButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e){
                    vehiculo.obtenerLog();
                    VentanaMain.logDialog();
                    VentanaMain.refresh(panel2);
                }
            });

                //Traslado
            JPanel panel2c= new JPanel();
            panel2c.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 0)); // Establece un FlowLayout sin relleno
            panel2c.add(new JLabel("Elija la sede a la que se trasladará el vehículo"));
            JComboBox<String> sedes = new JComboBox<>();
            for (Sede i: Inventario.getListaSedes()){
                if (vehiculo.getSede().getID() !=i.getID()){
                sedes.addItem(Integer.toString(i.getID())+": "+i.getNombre());}
            }
            sedes.setSelectedIndex(0);
            sedes.setPreferredSize(new Dimension(200, 30));
            panel2c.add(sedes);
            panel2c.add( Box.createRigidArea(new Dimension(0,100)));
            JButton trasladorButton= new JButton("Trasladar");
            panel2c.add(trasladorButton);
            comboBoxGeneral3Vehi= sedes;
            trasladorButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e){
                    int idSede= Integer.parseInt(comboBoxGeneral3Vehi.getSelectedItem().toString().split(":")[0]);
                    Sede sedeElegida= Inventario.assignSede(idSede);
                    boolean trasladado=vehiculo.setTrasladoASede(sedeElegida);
                    if (trasladado==true){
                        try{Inventario.updateSistema();}catch(IOException e1) {e1.printStackTrace();}
                        VentanaMain.CambioGuardadoDialog();
                    }
                    else{
                        VentanaMain.errorDialog("El vehículo actualmente no puede trasladarse, intentelo otro día.");
                    }
                    VentanaMain.refresh(panel2);
                }
            });

            menuInterno.add("Trasladar", panel2c);
            panel2.add(menuInterno);
            panel2.revalidate();
            panel2.repaint();

        }
        
    

        private static void nuevoPanel1Categorias(JPanel panel1){
        VentanaMain.refresh(panel1);
        //
        PlaceHolderTextField tipoVehiculo= new PlaceHolderTextField("Ej: PickUp");
        JComboBox<String> nivelesLujo = new JComboBox<>();
        JComboBox<String> capacidad = new JComboBox<>();
        JComboBox<String> temp1 = new JComboBox<>();
        JComboBox<String> temp2 = new JComboBox<>();
        NumericOnlyTextField costoLeve= new NumericOnlyTextField();
        NumericOnlyTextField costoModerado= new NumericOnlyTextField();
        NumericOnlyTextField costoGrave= new NumericOnlyTextField();
        NumericOnlyTextField tarifa= new NumericOnlyTextField();
        JComboBox<String> categorias= new JComboBox<>();
        //
        //
        panel1.setPreferredSize(new Dimension(0, 400));
        panel1.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0)); // Establece un FlowLayout sin relleno
        GridLayout gridLayout1 = new GridLayout(0, 2);
        gridLayout1.setVgap(10);
        panel1.add(new JLabel("Ingrese la información de la nueva categoría"));
        panel1.add(new JLabel(""));
        panel1.setLayout(gridLayout1);
        panel1.add(new JLabel("Tipo de vehículo:"));
        panel1.add(tipoVehiculo);
        panel1.add(new JLabel("Nivel de lujo del vehículo:"));
        String[] opcionesLujo = {"Premium", "Intermedio", "Económico"};
        nivelesLujo= new JComboBox<>(opcionesLujo);
        nivelesLujo.setSelectedIndex(0);
        panel1.add(nivelesLujo);
        panel1.add(new JLabel("Capacidad:"));
        DefaultComboBoxModel<String> opcionesCapacidad = new DefaultComboBoxModel<>();
        for (int i = 1; i <= 20; i++) {
            opcionesCapacidad.addElement(Integer.toString(i));
        }
        capacidad = new JComboBox<>(opcionesCapacidad);
        capacidad.setSelectedIndex(0);
        panel1.add(capacidad);
        panel1.add(new JLabel("% a pagar por Temporada Alta:"));
        DefaultComboBoxModel<String> opcionesTemp1 = new DefaultComboBoxModel<>();
        DefaultComboBoxModel<String> opcionesTemp2 = new DefaultComboBoxModel<>();
        for (double i = 0.1; i <= 2.1; i += 0.1) {
            double numeroRedondeado = (double) Math.round(i * Math.pow(10, 1)) / Math.pow(10, 1);
            opcionesTemp1.addElement(Double.toString(numeroRedondeado));
            opcionesTemp2.addElement(Double.toString(numeroRedondeado));

        }
        temp1 = new JComboBox<>(opcionesTemp1);
        temp1.setSelectedIndex(0);
        panel1.add(temp1);
        panel1.add(new JLabel("% a pagar por Temporada Baja:"));
        temp2 = new JComboBox<>(opcionesTemp2);
        temp2.setSelectedIndex(0);
        panel1.add(temp2);
        panel1.add(new JLabel("Costo por avería leve (COP):"));
        panel1.add(costoLeve);
        panel1.add(new JLabel("Costo por avería moderada:"));
        panel1.add(costoModerado);
        panel1.add(new JLabel("Costo por avería total:"));
        panel1.add(costoGrave);
        panel1.add(new JLabel("Tarifa diaria:"));
        panel1.add(tarifa);
        panel1.add(new JLabel("Categoria superior/padre:"));
        categorias.addItem("0: Ninguna");
        for (Categoria i: Inventario.getListaCategorias()){
            categorias.addItem(i.getID()+": "+i.getnombreCategoria());
        }
        categorias.setPreferredSize(new Dimension(100, 20));

        categorias.setSelectedIndex(0);
        panel1.add(categorias);
        comboBoxGeneral1Cate=nivelesLujo;
        comboBoxGeneral2Cate= capacidad;
        comboBoxGeneral3Cate=temp1;
        comboBoxGeneral4Cate=temp2;
        comboBoxGeneral5Cate=categorias;
        JButton avanzar= new JButton("Registrar Categoría");
        avanzar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                String tipoStr= tipoVehiculo.getText().toString();
                String lujoStr= comboBoxGeneral1Cate.getSelectedItem().toString().toUpperCase();
                String capacidadStr= comboBoxGeneral2Cate.getSelectedItem().toString();
                String temp1Str= comboBoxGeneral3Cate.getSelectedItem().toString();
                String temp2Str= comboBoxGeneral4Cate.getSelectedItem().toString();
                String costoLeveStr= costoLeve.getText().toString();
                String costoModeradoStr= costoModerado.getText().toString();
                String costoGraveStr= costoGrave.getText().toString();
                String tarifaStr= tarifa.getText().toString();
                String categoriaStr= comboBoxGeneral5Cate.getSelectedItem().toString();
                String idStr= categoriaStr.split(":")[0];
                if (!tipoStr.equals("")&&!costoLeveStr.equals("")&&!costoModeradoStr.equals("")&&!costoGraveStr.equals("")&&!tarifaStr.equals("")){
                    Categoria categoria = new Categoria(tipoStr+"_"+lujoStr, Integer.parseInt(capacidadStr), Double.parseDouble(temp1Str),  Double.parseDouble(temp2Str), Integer.parseInt(costoLeveStr), Integer.parseInt(costoModeradoStr), Integer.parseInt(costoGraveStr), Integer.parseInt(tarifaStr), Integer.parseInt(idStr));
                    categoria.setPadre(Inventario.assignCategoria(Integer.parseInt(idStr)));
                    Inventario.getListaCategorias().add(categoria);
                    VentanaMain.CambioGuardadoDialog();
                    try {Inventario.updateSistema();} catch (IOException e1) {e1.printStackTrace();}

                    VentanaMain.refresh(panel1);
                }
                else{
                    VentanaMain.errorDialog("Verifique que todos los campos de texto estén llenos.");
                }
          }
        });
        panel1.add(avanzar);
    }
    private static void nuevoPanel1Sedes(JPanel panel1){
        VentanaMain.refresh(panel1);
        //
        PlaceHolderTextField nomSede= new PlaceHolderTextField("Ej: Sede Bosa");
        PlaceHolderTextField ubiSede= new PlaceHolderTextField("Ej: Cl. 57c Sur #87-21");
        DefaultComboBoxModel<String> opcionesHora1 = new DefaultComboBoxModel<>();
        DefaultComboBoxModel<String> opcionesHora2 = new DefaultComboBoxModel<>();
        DefaultComboBoxModel<String> opcionesHora3 = new DefaultComboBoxModel<>();
        DefaultComboBoxModel<String> opcionesHora4 = new DefaultComboBoxModel<>();
        JComboBox<String> hora1 = new JComboBox<>();
        JComboBox<String> min1 = new JComboBox<>();
        JComboBox<String> hora2 = new JComboBox<>();
        JComboBox<String> min2 = new JComboBox<>();
        JComboBox<String> hora3 = new JComboBox<>();
        JComboBox<String> min3 = new JComboBox<>();
        JComboBox<String> hora4 = new JComboBox<>();
        JComboBox<String> min4 = new JComboBox<>();
        //
        GridLayout gridLayout1 = new GridLayout(0, 3);
        gridLayout1.setVgap(10);
        panel1.setPreferredSize(new Dimension(0, 400));
        panel1.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 0)); // Establece un FlowLayout sin relleno
        panel1.add(new JLabel("Ingrese la información de la nueva sede"));
        panel1.add(new JLabel(""));
        panel1.add(new JLabel(""));
        panel1.setLayout(gridLayout1);
        panel1.add(new JLabel("Nombre de la sede:"));
        panel1.add(nomSede);
        panel1.add(new JLabel(""));
        panel1.add(new JLabel("Ubicación de la sede:"));
        panel1.add(ubiSede);
        panel1.add(new JLabel(""));
        panel1.add(new JLabel("Horario de apertura entre semana (hh mm):"));
        for (int i = 1; i <= 23; i++) {
            String s="";
            if(i<10){s=s+"0";}
            s=s+Integer.toString(i);
            opcionesHora1.addElement(s);
            opcionesHora2.addElement(s);
            opcionesHora3.addElement(s);
            opcionesHora4.addElement(s);

        }
        String[] opcionesMinutos1 = {"00"};
        String[] opcionesMinutos2 = {"00"};
        String[] opcionesMinutos3 = {"00"};
        String[] opcionesMinutos4 = {"00"};

        hora1 = new JComboBox<>(opcionesHora1);
        min1 = new JComboBox<>(opcionesMinutos1);
        hora1.setSelectedIndex(0);
        min1.setSelectedIndex(0);
        panel1.add(hora1);
        panel1.add(min1);
        panel1.add(new JLabel("Horario de cierre entre semana (hh mm):"));        
        hora2 = new JComboBox<>(opcionesHora2);
        min2 = new JComboBox<>(opcionesMinutos2);
        hora2.setSelectedIndex(0);
        min2.setSelectedIndex(0);
        panel1.add(hora2);
        panel1.add(min2);
        panel1.add(new JLabel("Horario de apertura para fin de semana (hh mm):"));        
        hora3 = new JComboBox<>(opcionesHora3);
        min3 = new JComboBox<>(opcionesMinutos3);
        hora3.setSelectedIndex(0);
        min3.setSelectedIndex(0);
        panel1.add(hora3);
        panel1.add(min3);
        panel1.add(new JLabel("Horario de cierre para fin de semana (hh mm):"));        
        hora4 = new JComboBox<>(opcionesHora4);
        min4 = new JComboBox<>(opcionesMinutos4);
        hora4.setSelectedIndex(0);
        min4.setSelectedIndex(0);
        panel1.add(hora4);
        panel1.add(min4);
        
        JButton imag= new JButton();
        imag.setBackground(Color.WHITE);

        Font font = imag.getFont().deriveFont(Font.ITALIC | Font.BOLD);
        imag.setFont(font);
        imag.setText("Cargue una foto de la sede aquí");
        panel1.add(imag);
        panel1.add(new JLabel("")); 
        JButton avanzar= new JButton("Registrar Sede");
        panel1.add(avanzar);
        avanzar.setVisible(false);

        comboBoxGeneral1Sede=hora1;
        comboBoxGeneral2Sede=min1;
        comboBoxGeneral3Sede=hora2;
        comboBoxGeneral4Sede=min2;
        comboBoxGeneral5Sede=hora3;
        comboBoxGeneral6Sede=min3;
        comboBoxGeneral7Sede=hora4;
        comboBoxGeneral8Sede=min4;
        DocumentListener documentListener = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                avanzar.setVisible(VentanaMain.checkFields1Sede(nomSede, ubiSede, comboBoxGeneral1Sede, comboBoxGeneral2Sede, comboBoxGeneral3Sede, comboBoxGeneral4Sede));
            }
    
            @Override
            public void removeUpdate(DocumentEvent e) {
                avanzar.setVisible(VentanaMain.checkFields1Sede(nomSede, ubiSede, comboBoxGeneral1Sede, comboBoxGeneral2Sede, comboBoxGeneral3Sede, comboBoxGeneral4Sede));
            }
    
            @Override
            public void changedUpdate(DocumentEvent e) {
                avanzar.setVisible(VentanaMain.checkFields1Sede(nomSede, ubiSede, comboBoxGeneral1Sede, comboBoxGeneral2Sede, comboBoxGeneral3Sede, comboBoxGeneral4Sede));
            }
        };
        nomSede.getDocument().addDocumentListener(documentListener);
        ubiSede.getDocument().addDocumentListener(documentListener);

        avanzar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                    String hora1 = comboBoxGeneral1Sede.getSelectedItem().toString();
                    String minutos1 =comboBoxGeneral2Sede.getSelectedItem().toString();
                    String hora2 = comboBoxGeneral3Sede.getSelectedItem().toString();
                    String minutos2 = comboBoxGeneral4Sede.getSelectedItem().toString();
                    String inicio1=(hora1+minutos1);
                    String fin1=(hora2+minutos2);
                    String hora3 = comboBoxGeneral5Sede.getSelectedItem().toString();
                    String minutos3=comboBoxGeneral6Sede.getSelectedItem().toString();
                    String hora4 = comboBoxGeneral7Sede.getSelectedItem().toString();
                    String minutos4 = comboBoxGeneral8Sede.getSelectedItem().toString();
                    String inicio2=(hora3+minutos3);
                    String fin2=(hora4+minutos4);

                    if(Integer.parseInt(fin1)> Integer.parseInt(inicio1)&&Integer.parseInt(fin2)> Integer.parseInt(inicio2)){
                        if (!nomSede.getText().trim().equals("")&&!ubiSede.getText().trim().equals("")){

                            List<Integer> horarioSemana= new ArrayList<Integer>();
                            List<Integer> horarioFinSemana= new ArrayList<Integer>();
                            horarioSemana.add(Integer.parseInt(inicio1));
                            horarioSemana.add(Integer.parseInt(fin1));
                            horarioFinSemana.add(Integer.parseInt(inicio2));
                            horarioFinSemana.add(Integer.parseInt(fin2));
                            Sede sede= new Sede(nomSede.getText().trim(), ubiSede.getText().trim(), horarioSemana, horarioFinSemana);
                            Inventario.setListaSedes(sede);
                            
                            try {Inventario.updateSistema();} catch (IOException e1) {e1.printStackTrace();}
                            
                            VentanaMain.refresh(panel1);
                            VentanaMain.CambioGuardadoDialog();
                        }
                        else{
                        VentanaMain.errorDialog("Verifique que no haya campos de texto vacios.");
                        }
                    }
                    else{
                        VentanaMain.errorDialog("Verifique que la fecha/periodo inicial sea previa a la fecha/periodo final.");
                    }  

            }
        });

    }
    public static void nuevoPanel2Sedes(JPanel panel2){
        VentanaMain.refresh(panel2);
        panel2.setPreferredSize(new Dimension(0, 400));
        panel2.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 0));
        panel2.add(new JLabel("Seleccione la sede que desea modificar"));
        
        JComboBox<String> sedes = new JComboBox<>();
        for (Sede i: Inventario.getListaSedes()){
            sedes.addItem(Integer.toString(i.getID())+": "+i.getNombre());
        }
        sedes.setSelectedIndex(0);
        panel2.add(sedes);

        JButton avanzar2 = new JButton("Modificar");
        panel2.add(avanzar2);
        avanzar2.setEnabled(false);  // Inicialmente deshabilita el botón
        sedes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {updateButtonState();}
            private void updateButtonState() {
            if (sedes.getSelectedItem() != null && !sedes.getSelectedItem().toString().isEmpty()) {
                avanzar2.setEnabled(true);} else {avanzar2.setEnabled(false);}}});
        ((JTextComponent) sedes.getEditor().getEditorComponent()).getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {updateButtonState();}
            @Override
            public void removeUpdate(DocumentEvent e) {updateButtonState();}
            @Override
            public void changedUpdate(DocumentEvent e) {updateButtonState();}
            private void updateButtonState() {
                if (sedes.getSelectedItem() != null) {
                    avanzar2.setEnabled(true);  // Habilita el botón "avanzar2" si se ha seleccionado una opción o se ha ingresado texto.
                } else {
                    avanzar2.setEnabled(false); // Deshabilita el botón "avanzar2" si no hay una opción seleccionada o no hay texto ingresado.
                }
            }
        });
        JPanel panel2a= new JPanel();
        JPanel panel2b= new JPanel();
        avanzar2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idSedeEditar= Integer.parseInt(sedes.getSelectedItem().toString().split(":")[0]);
                Sede sedeEditar= Inventario.assignSede(idSedeEditar);
                panel2.removeAll();
                panel2.setLayout(new GridLayout(0, 1));
                // Agrega un cuadro de código de sede
                JLabel label = new JLabel("\tCódigo de Sede: "+ idSedeEditar);
                Border border = new LineBorder(Color.decode("#99A0A2"), 1);
                label.setBorder(border);
                label.setBackground(Color.decode("#CCEDF7"));
                label.setOpaque(true);
                label.setPreferredSize(new Dimension(170, 25));
                panel2a.add(label);
                // Agrega panel2a a panel2
                panel2.add(panel2a);
                // Agrega panel2b a panel2
                EditorObjetos editor = new EditorObjetos();
                editor.editorSede(panel2b,sedeEditar);
                editor.editar();

                try {Inventario.updateSistema();} catch (IOException e1) {e1.printStackTrace();}

                panel2.add(panel2b);
                frame.revalidate();
                frame.repaint();}});
    }
        public static void nuevoPanel1Seguros(JPanel panel1){
            VentanaMain.refresh(panel1);
            //
            PlaceHolderTextField descripcion= new PlaceHolderTextField("Ej: Seguro ante robos");
            JComboBox<String> pctg = new JComboBox<>();
            //
            GridLayout gridLayout1 = new GridLayout(0, 2);
            gridLayout1.setVgap(10);
            panel1.setPreferredSize(new Dimension(0, 400));
            panel1.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 0)); // Establece un FlowLayout sin relleno
            panel1.add(new JLabel("Ingrese la información del nuevo seguro"));
            panel1.add(new JLabel(""));
            panel1.setLayout(gridLayout1);
            panel1.add(new JLabel("Descripción del seguro:"));
            panel1.add(descripcion);
            panel1.add(new JLabel("Porcentaje de la tarifa diaria a cobrar:"));
            DefaultComboBoxModel<String> opciones = new DefaultComboBoxModel<>();
            for (double i = 0.1; i <= 2.0; i += 0.1) {
                double numeroRedondeado = Math.round(i * 10.0) / 10.0;
                opciones.addElement(Double.toString(numeroRedondeado));
            }
            pctg = new JComboBox<>(opciones);
            comboBoxGeneral1Seg=pctg;
            panel1.add(pctg);
            panel1.add(Box.createRigidArea(new Dimension(0,150)));
            panel1.add(Box.createRigidArea(new Dimension(0,150)));
            JButton avanzar = new JButton("Registrar Seguro");
            avanzar.setVisible(false);
            panel1.add(avanzar);
            DocumentListener documentListener = new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    avanzar.setVisible(!descripcion.getText().trim().isEmpty());
                }
                @Override
                public void removeUpdate(DocumentEvent e) {
                    avanzar.setVisible(!descripcion.getText().trim().isEmpty());
                }
                @Override
                public void changedUpdate(DocumentEvent e) {
                    avanzar.setVisible(!descripcion.getText().trim().isEmpty());
                }
            };
            descripcion.getDocument().addDocumentListener(documentListener);
            avanzar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e){
                    String pctgStr=comboBoxGeneral1Seg.getSelectedItem().toString();
                    Seguro seguro= new Seguro(Double.parseDouble(pctgStr),descripcion.getText());
                    Inventario.setListaSeguros(seguro);
                    
                    try {Inventario.updateSistema();} catch (IOException e1) {e1.printStackTrace();}

                    VentanaMain.CambioGuardadoDialog();
                    VentanaMain.refresh(panel1);
                }
            });

         

        }
        public static void nuevoPanel2Seguros(JPanel panel2){
            VentanaMain.refresh(panel2);
            GridLayout gridLayout1 = new GridLayout(0, 2);
            gridLayout1.setVgap(10);
            panel2.setPreferredSize(new Dimension(0, 400));
            panel2.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 0)); // Establece un FlowLayout sin relleno
            panel2.add(new JLabel("Seleccione el seguro que desea modificar"));
            panel2.add(new JLabel(""));
            panel2.setLayout(gridLayout1);
            JComboBox<String> seguros = new JComboBox<>();

            for (Seguro i: Inventario.getListaSeguros()){
                seguros.addItem(Integer.toString(i.getID())+": "+i.getDescripcion());
            }
            seguros.setSelectedIndex(0);
            panel2.add(seguros);
            JButton avanzar = new JButton("Modificar Seguro");
            panel2.add(Box.createRigidArea(new Dimension(0,200)));
            panel2.add(Box.createRigidArea(new Dimension(0,200)));
            panel2.add(Box.createRigidArea(new Dimension(0,200)));
            panel2.add(avanzar);
            JPanel panel2a= new JPanel();
            JPanel panel2b= new JPanel();
            avanzar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel2.removeAll();
                panel2.setLayout(new GridLayout(0, 1));
                // Agrega un cuadro de código de sede
                int idSeguroEditar= Integer.parseInt(seguros.getSelectedItem().toString().split(":")[0]);
                Seguro seguroEditar= Inventario.assignSeguro(idSeguroEditar);
                JLabel label = new JLabel("\tCódigo de Seguro: "+idSeguroEditar);
                Border border = new LineBorder(Color.decode("#99A0A2"), 1);
                label.setBorder(border);
                label.setBackground(Color.decode("#CCEDF7"));
                label.setOpaque(true);
                label.setPreferredSize(new Dimension(170, 25));
                panel2a.add(label);
                // Agrega panel2a a panel2
                panel2.add(panel2a);
                // Agrega panel2b a panel2
                EditorObjetos editor = new EditorObjetos();
                editor.editorSeguro(panel2b,seguroEditar);
                editor.editar();
                panel2.add(panel2b);

                try{Inventario.updateSistema();}catch(IOException e1) {e1.printStackTrace();}

                frame.revalidate();
                frame.repaint();}});


        }
        public static void nuevoPanel3Seguros(JPanel panel3){
            VentanaMain.refresh(panel3);
            //
            JComboBox<String> seguros = new JComboBox<>();

            //
            GridLayout gridLayout1 = new GridLayout(0, 2);
            gridLayout1.setVgap(10);
            panel3.setPreferredSize(new Dimension(0, 400));
            panel3.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 0)); // Establece un FlowLayout sin relleno
            panel3.add(new JLabel("Seleccione el seguro que desea eliminar"));
            panel3.add(new JLabel(""));
            panel3.setLayout(gridLayout1);
            for (Seguro i: Inventario.getListaSeguros()){
                seguros.addItem(Integer.toString(i.getID())+": "+i.getDescripcion());
            }
            seguros.setSelectedIndex(0);
            panel3.add(seguros);
            panel3.add(Box.createRigidArea(new Dimension(0,200)));
            panel3.add(Box.createRigidArea(new Dimension(0,200)));
            panel3.add(Box.createRigidArea(new Dimension(0,200)));
            JButton avanzar = new JButton("Eliminar Seguro");
            avanzar.setVisible(false);
            panel3.add(avanzar);
            comboBoxGeneral9Sede= seguros;
            seguros.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    boolean showButton = !comboBoxGeneral9Sede.getSelectedItem().equals(null);
                    avanzar.setVisible(showButton);
                }
            });
            
            avanzar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int idSeguroEditar= Integer.parseInt(seguros.getSelectedItem().toString().split(":")[0]);
                    Seguro seguroEditar= Inventario.assignSeguro(idSeguroEditar);
                    Inventario.getListaSeguros().remove(seguroEditar);

                    try{Inventario.updateSistema();}catch(IOException e1) {e1.printStackTrace();}

                    VentanaMain.CambioGuardadoDialog();
                    VentanaMain.refresh(panel3);
                
                }
            });

        }



            public static void nuevoPanel1Personal(JPanel panel1){
            VentanaMain.refresh(panel1);
            //
            PlaceHolderTextField login= new PlaceHolderTextField("Ej: m.acosta");
            PlaceHolderTextField password = new PlaceHolderTextField("acosta123");
            //FALTA SEDES
            //
            GridLayout gridLayout1 = new GridLayout(0, 2);
            gridLayout1.setVgap(10);
            panel1.setPreferredSize(new Dimension(0, 400));
            panel1.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 0)); // Establece un FlowLayout sin relleno
            panel1.add(new JLabel("Ingrese la información del nuevo administrador local"));
            panel1.add(new JLabel(""));
            panel1.setLayout(gridLayout1);
            panel1.add(new JLabel("Login"));
            panel1.add(login);
            JButton avanzar = new JButton("Siguiente");
            panel1.add(Box.createRigidArea(new Dimension(0,200)));
            panel1.add(Box.createRigidArea(new Dimension(0,200)));
            panel1.add(avanzar);
            avanzar.setVisible(false);
            DocumentListener documentListener1 = new DocumentListener() {
                @Override
                    public void insertUpdate(DocumentEvent e) {
                        avanzar.setVisible(!login.getText().trim().isEmpty());
                    }
                    @Override
                    public void removeUpdate(DocumentEvent e) {
                        avanzar.setVisible(!login.getText().trim().isEmpty());
                    }
                    @Override
                    public void changedUpdate(DocumentEvent e) {
                        avanzar.setVisible(!login.getText().trim().isEmpty());
                    }
                };
            login.getDocument().addDocumentListener(documentListener1);
            avanzar.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){

                    if (((Usuario.checkNombresLogins(login.getText().trim()))==false)){
                        VentanaMain.refresh(panel1);
                        login.setEditable(false);
                        panel1.add(new JLabel("Complete el registro del usuario"));
                        panel1.add(new JLabel(""));

                        panel1.add(new JLabel("Sede que administrará"));

                        JComboBox<String> sedes = new JComboBox<>();

                        for (Sede i: Inventario.getListaSedes()){
                            sedes.addItem(Integer.toString(i.getID())+": "+i.getNombre());
                        }
                        sedes.setSelectedIndex(0);
                        panel1.add(sedes);
                        panel1.add(new JLabel("Password"));
                        panel1.add(password);
                        JButton avanzar2 = new JButton("Agregar administrador local");
                        panel1.add(avanzar2);
                        avanzar2.setVisible(false);
                        DocumentListener documentListener2 = new DocumentListener() {
                            @Override
                        public void insertUpdate(DocumentEvent e) {
                            avanzar2.setVisible(!password.getText().trim().isEmpty());
                        }
                        @Override
                        public void removeUpdate(DocumentEvent e) {
                            avanzar2.setVisible(!password.getText().trim().isEmpty());
                        }
                        @Override
                        public void changedUpdate(DocumentEvent e) {
                            avanzar2.setVisible(!password.getText().trim().isEmpty());
                        }};
                        password.getDocument().addDocumentListener(documentListener2);                  
                        avanzar2.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                int idSede= Integer.parseInt(sedes.getSelectedItem().toString().split(":")[0]);
                                Sede sedeElegida= Inventario.assignSede(idSede);
                                if(sedeElegida.getAdminLocal()==null){

                                personal adminlocal=new personal(login.getText(), password.getText(), "AdminLocal", sedeElegida);
                                personal.addCredencialesPersonal(adminlocal);
                                sedeElegida.setAdminLocal(adminlocal);

                                try {Inventario.updateSistema();} catch (IOException e1) {e1.printStackTrace();}


                                VentanaMain.CambioGuardadoDialog();
                                VentanaMain.refresh(panel1);
                            }
                            else{
                                VentanaMain.errorDialog("La sede seleccionada ya tiene un admin local asignado.");                                
                            }
                            }
                        }); 
                    }else{
                    VentanaMain.errorDialog("Ya existen usuarios con el login dado.");
                    }
                }});}
                        
        public static void nuevoPanel2Personal(JPanel panel2){
            VentanaMain.refresh(panel2);
            //
            PlaceHolderTextField login= new PlaceHolderTextField("Ej: m.acosta");
            //FALTA PASSWORD
            //FALTA SEDE
            //
            GridLayout gridLayout1 = new GridLayout(0, 2);
            gridLayout1.setVgap(10);
            panel2.setPreferredSize(new Dimension(0, 400));
            panel2.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 0)); // Establece un FlowLayout sin relleno
            panel2.add(new JLabel("Ingrese el login del administrador local"));
            panel2.add(new JLabel(""));
            panel2.setLayout(gridLayout1);
            panel2.add(new JLabel("Login"));
            panel2.add(login);
            JButton avanzar = new JButton("Siguiente");
            panel2.add(Box.createRigidArea(new Dimension(0,200)));
            panel2.add(Box.createRigidArea(new Dimension(0,200)));
            panel2.add(avanzar);
            avanzar.setVisible(false);
            DocumentListener documentListener = new DocumentListener() {
                @Override
                    public void insertUpdate(DocumentEvent e) {
                        avanzar.setVisible(!login.getText().trim().isEmpty());
                    }
                    @Override
                    public void removeUpdate(DocumentEvent e) {
                        avanzar.setVisible(!login.getText().trim().isEmpty());
                    }
                    @Override
                    public void changedUpdate(DocumentEvent e) {
                        avanzar.setVisible(!login.getText().trim().isEmpty());
                    }
                };
            login.getDocument().addDocumentListener(documentListener);
            avanzar.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    boolean found=false;

                    for(personal i:personal.getCredencialesPersonal()){
                         if ((i.getLogin().equals(login.getText()) && i.getTipoPersonal().equals("AdminLocal"))){
                            found=true;
                            VentanaMain.refresh(panel2);
                            login.setEditable(false);
                            panel2.add(new JLabel("Complete la actualización del usuario"));
                            panel2.add(new JLabel(""));
                            
                            panel2.add(new JLabel("Nueva sede que administrará"));

                            JComboBox<String> sedes = new JComboBox<>();

                            for (Sede j: Inventario.getListaSedes()){
                                sedes.addItem(Integer.toString(j.getID())+": "+j.getNombre());
                            }
                            sedes.setSelectedIndex(0);
                            panel2.add(sedes);
                            panel2.add(new JLabel("Password"));
                            PlaceHolderTextField password = new PlaceHolderTextField("acosta123");
                            panel2.add(password);
                            JButton avanzar2 = new JButton("Actualizar administrador local");
                            panel2.add(avanzar2);
                            avanzar2.setVisible(false);
                            DocumentListener documentListener2 = new DocumentListener() {
                                @Override
                            public void insertUpdate(DocumentEvent e) {
                                avanzar2.setVisible(!password.getText().trim().isEmpty());
                            }
                                @Override
                            public void removeUpdate(DocumentEvent e) {
                                avanzar2.setVisible(!password.getText().trim().isEmpty());
                            }
                                @Override
                            public void changedUpdate(DocumentEvent e) {
                                avanzar2.setVisible(!password.getText().trim().isEmpty());
                            }};
                            password.getDocument().addDocumentListener(documentListener2);         
                            avanzar2.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                int idSede= Integer.parseInt(sedes.getSelectedItem().toString().split(":")[0]);
                                Sede sedeElegida= Inventario.assignSede(idSede);
                                if (sedeElegida.getAdminLocal()!=null){
                                    VentanaMain.errorDialog("La sede elegida ya tiene un admin local asignado.");
                                }
                                else{
                                    i.setSede(sedeElegida);
                                    i.setPassword(password.getText());
                                    VentanaMain.CambioGuardadoDialog();
                                    VentanaMain.refresh(panel2);

                                    try {Inventario.updateSistema();} catch (IOException e1) {e1.printStackTrace();}

                                }
                            }
                            });
                            }
                        }
                        if (found==false){
                        VentanaMain.errorDialog("Ingrese el login de un admin local existente.");
                        }
                    }});}

            public static void nuevoPanelTarifas(JPanel panel,int opcion) {
                VentanaMain.refresh(panel);
                //
                NumericOnlyTextField precio = new NumericOnlyTextField();
                //
                panel.setLayout(new GridLayout(0, 1));
                //panel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 80));
                JLabel label = new JLabel("Nuevo Precio (COP) ");
                precio.setSize(new Dimension(100,50));
                JButton actualizarPrecio = new JButton("Actualizar precio");  
                actualizarPrecio.setSize(new Dimension(100,50));
                    
                panel.add(label);
                panel.add(precio);
                panel.add(Box.createRigidArea(new Dimension(0, 100)));
                panel.add(actualizarPrecio);
                    
                actualizarPrecio.setVisible(false);

                DocumentListener documentListener = new DocumentListener() {
                    @Override
                        public void insertUpdate(DocumentEvent e) {
                            actualizarPrecio.setVisible(!precio.getText().trim().isEmpty());
                        }
                        @Override
                        public void removeUpdate(DocumentEvent e) {
                            actualizarPrecio.setVisible(!precio.getText().trim().isEmpty());
                        }
                        @Override
                        public void changedUpdate(DocumentEvent e) {
                            actualizarPrecio.setVisible(!precio.getText().trim().isEmpty());
                        }
                    };
                    precio.getDocument().addDocumentListener(documentListener);

                actualizarPrecio.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (opcion==1){
                            Inventario.setCostoPorConductorAdicional(Integer.parseInt(precio.getText()));
                        }
                        else if (opcion==2){
                            Inventario.setCostoPorTrasladoSedes(Integer.parseInt(precio.getText()));
                        }
                        try {Inventario.updateSistema();} catch (IOException e1) {e1.printStackTrace();}
                        VentanaMain.CambioGuardadoDialog();
                        VentanaMain.refresh(panel);
                    }
            });
         }
                    
        public static void nuevoPanelPeriodos(JPanel panel,int opcion){
                VentanaMain.refresh(panel);

                panel.setLayout(new GridLayout(0, 1));
                panel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 80));
                JLabel label = new JLabel("Inicio Periodo ");
                panel.add(label);
                //Inicialización
                DateComboBoxPanel date1= new DateComboBoxPanel(Calendar.getInstance().get(Calendar.YEAR)+1);
                date1.setDefaulDayComboBox();
                date1.setDefaultMonthComboBox();
                //Fin inicialización
                panel.add(date1);
                JLabel label2 = new JLabel("Fin Periodo ");
                panel.add(label2);
                DateComboBoxPanel date2= new DateComboBoxPanel(Calendar.getInstance().get(Calendar.YEAR)+1);
                date2.setDefaulDayComboBox();
                date2.setDefaultMonthComboBox();
                panel.add(date2);
                panel.add(Box.createRigidArea(new Dimension(0, 100)));
                panel.add(Box.createRigidArea(new Dimension(0, 100)));
                JButton actualizar = new JButton("Actualizar periodo");  
                actualizar.setSize(new Dimension(100,50));
                panel.add(actualizar);


                actualizar.addActionListener(new ActionListener() {  
                @Override
                public void actionPerformed(ActionEvent e) {
                    String text1 = date1.getText();
                    String text2 = date2.getText();
                    if (!text1.equals("")&& !text2.equals("")){
                        if(Integer.parseInt(text2)>Integer.parseInt(text1)){
                            if (opcion==1){
                                List<Integer> periodo=Inventario.getPeriodoTemporadaAlta();
                                periodo.clear();
                                periodo.add(Integer.parseInt(text1));
                                periodo.add(Integer.parseInt(text2));
                            }
                            else if (opcion==2){
                                List<Integer> periodo=Inventario.getPeriodoTemporadaBaja();
                                periodo.clear();
                                periodo.add(Integer.parseInt(text1));
                                periodo.add(Integer.parseInt(text2));
                            }
                            try {Inventario.updateSistema();} catch (IOException e1) {e1.printStackTrace();}
                            VentanaMain.CambioGuardadoDialog();
                            VentanaMain.refresh(panel);
                        }
                        else{
                        VentanaMain.errorDialog("Verifique que ningún campo este vacío.");
                        }

                    }
                    else{
                        VentanaMain.errorDialog("Verifique que la fecha/periodo inicial sea previa a la fecha/periodo final.");
                    }
                }
                });
        }     
}

