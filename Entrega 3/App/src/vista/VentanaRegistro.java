package vista;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentListener;
import modelo.Cliente;
import modelo.Inventario;
import modelo.Licencia;
import modelo.Tarjeta;
import modelo.Usuario;
import javax.swing.event.DocumentEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;


public class VentanaRegistro extends JFrame {
    private JTabbedPane tabbedPane;
    JPanel panelSuperior;
    private JLabel textoBienvenida;
    private JLabel instrucciones;
    private JLabel etiquetaDatos;
    private JButton botonContinuar;
    private PlaceHolderTextField campoNombre;
    private String inputFechaNacimiento;
    private int fnacimiento;
    private boolean guardarLicencia;
    private boolean guardarTarjeta;
    private String inputFechaL1;
    private String inputFechaL2;
    private String inputFechaT;
    private String login;
    private String password;
    private int numeroCedula;
    private String nombre;
    private String mail;
    private long telefono;
    private String nacionalidad;
    private int numerolicencia; 
    private String pais;
    private long numeroT; 
    private String titular;
    private String marca;
    private Cliente clienteNuevo;

    

    public VentanaRegistro(){
        super("Registro de Usuario");

        tabbedPane = new JTabbedPane();
        JPanel panelDatos = new JPanel(new GridLayout(0,2));
        panelSuperior = setPanelSuperior(tabbedPane);

        textoBienvenida = new JLabel("¡Bienvenido a nuestro sistema!");
        instrucciones = new JLabel("Necesitamos que por favor ingrese los siguientes datos para crear ");
        etiquetaDatos = new JLabel("Datos Personales:");

        panelDatos.add(textoBienvenida);
        panelDatos.add(new JLabel("\n"));
        panelDatos.add(instrucciones);
        panelDatos.add(new JLabel(" su cuenta."));
        panelDatos.add(etiquetaDatos);
        panelDatos.add(new JLabel("\n"));

        inputFechaNacimiento="";

        JLabel etiquetaDocumento = new JLabel("Documento de Identidad: ");
        NumericOnlyTextField campoDocumento = new NumericOnlyTextField();
        JLabel etiquetaNombre = new JLabel("Nombre Completo: ");
        campoNombre = new PlaceHolderTextField("Ej: Juan López");
        JLabel etiquetaCorreo = new JLabel("Correo Electrónico: ");
        PlaceHolderTextField campoCorreo = new PlaceHolderTextField("Ej: correo@ejemplo.com");
        JLabel etiquetaTelefono = new JLabel("Número de Teléfono Celular: ");
        NumericOnlyTextField campoTelefono = new NumericOnlyTextField();
        JLabel fechaNacimiento = new JLabel("Fecha de Nacimiento: ");
        botonContinuar = new JButton("Continuar");
        botonContinuar.setPreferredSize(new Dimension(50, 30));
        botonContinuar.setEnabled(false);

        //DESDE AQUI VA LO DE FECHA
        
        JPanel panelFecha= new JPanel();
        panelFecha.setLayout(new FlowLayout());
        DefaultComboBoxModel<String> opcionesAnio = new DefaultComboBoxModel<>();

        int anioActual= Calendar.getInstance().get(Calendar.YEAR);
        for (int i = anioActual-90; i <= anioActual-19; i++){
            opcionesAnio.addElement(Integer.toString(i));
        }
        JComboBox<String> anioBox= new JComboBox<String>(opcionesAnio);
        anioBox.setSelectedIndex(0);
        panelFecha.add(anioBox);
        anioBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
            String anio=anioBox.getSelectedItem().toString();
            VentanaMain.refresh(panelFecha);
            anioBox.setEnabled(false);
            DateComboBoxPanel date1= new DateComboBoxPanel(Integer.parseInt(anio));
            date1.setDefaulDayComboBox();
            date1.setDefaultMonthComboBox();
            VentanaMain.refresh(panelFecha);
            panelFecha.add(anioBox);
            panelFecha.add(date1);
            JButton updateDatebutton= new JButton("Cambiar Fecha");
            panelFecha.add(updateDatebutton);
            JButton saveDatebutton= new JButton("Guardar Fecha");
            panelFecha.add(saveDatebutton);
            inputFechaNacimiento="";

            saveDatebutton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e){
                    inputFechaNacimiento=anio+date1.getText();
                    VentanaMain.refresh(panelFecha);
                    panelFecha.add(anioBox);
                    panelFecha.add(updateDatebutton);
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
            });
            }
        });
        
        //hasta aca va -> para verificar si la fecha se guardo preguntar si !inputFechaNacimiento.equals("")
        

        JLabel etiquetaNacionalidad = new JLabel("Nacionalidad");
        PlaceHolderTextField campoNacionalidad = new PlaceHolderTextField("Ej: Colombia");
        

        panelDatos.add(etiquetaDocumento);
        panelDatos.add(campoDocumento);
        panelDatos.add(etiquetaNombre);
        panelDatos.add(campoNombre);
        panelDatos.add(etiquetaCorreo);
        panelDatos.add(campoCorreo);
        panelDatos.add(etiquetaTelefono);
        panelDatos.add(campoTelefono);
        panelDatos.add(fechaNacimiento);
        panelDatos.add(panelFecha);
        panelDatos.add(etiquetaNacionalidad);
        panelDatos.add(campoNacionalidad);

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
                boolean habilitar = !campoDocumento.getText().isEmpty() &&
                        !campoNombre.getText().isEmpty() &&
                        !campoCorreo.getText().isEmpty() &&
                        !campoTelefono.getText().isEmpty() && !campoNacionalidad.getText().isEmpty() && !inputFechaNacimiento.isEmpty();
                botonContinuar.setEnabled(habilitar);
            }
        };

        campoDocumento.getDocument().addDocumentListener(documentListener);
        campoNombre.getDocument().addDocumentListener(documentListener);
        campoCorreo.getDocument().addDocumentListener(documentListener);
        campoTelefono.getDocument().addDocumentListener(documentListener);
        campoNacionalidad.getDocument().addDocumentListener(documentListener);

        botonContinuar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (inputFechaNacimiento.equals("")){
                    VentanaMain.errorDialog("Ingrese la fecha de nacimiento");
                }
                else{
                tabbedPane.remove(panelDatos);
                nombre = campoNombre.getText();
                numeroCedula = Integer.parseInt(campoDocumento.getText());
                mail = campoCorreo.getText();
                telefono = Long.parseLong(campoTelefono.getText());
                nacionalidad = campoNacionalidad.getText();
                System.out.println(nombre);
                System.out.println(numeroCedula);
                System.out.println(mail);
                System.out.println(telefono);
                System.out.println(inputFechaNacimiento);
                System.out.println(nacionalidad);

                crearUsuario();
                botonContinuar.setEnabled(false);
                }
            }
        });
        panelDatos.add(new JLabel("\n"));
        panelDatos.add(botonContinuar);

        add(this.panelSuperior,BorderLayout.NORTH);
        tabbedPane.add("Datos Personales", panelDatos);
        add(tabbedPane);
        setLocationRelativeTo(null);
        setSize(840, 700);
        setVisible(true);
    }
    
    public void crearUsuario(){
        JPanel panelB = new JPanel(new GridLayout(0,1));
        JPanel panelUsuarioContraseña = new JPanel(new GridLayout(0, 2));
        
        JLabel labelUsuario = new JLabel("Nombre de Usuario (login): ");
        PlaceHolderTextField campoUsuario = new PlaceHolderTextField("Ej: jlopez123");
        JLabel labelContraseña = new JLabel("Contraseña: ");
        JPasswordField campoContraseña = new JPasswordField();       
        labelContraseña.setVisible(false);
        campoContraseña.setVisible(false);

        JButton botonCrearUsuario = new JButton("Crear Usuario");
        JButton botonLicencia = new JButton("Añadir Licencia de Conducción");
        JButton botonTarjeta = new JButton("Añadir Método de Pago");
        botonLicencia.setPreferredSize(new Dimension(50, 30));
        botonTarjeta.setPreferredSize(new Dimension(50, 30));
        botonCrearUsuario.setEnabled(false);
        botonLicencia.setEnabled(false);
        botonTarjeta.setEnabled(false);

        DocumentListener documentListener = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                actualizarCampos();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                actualizarCampos();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                actualizarCampos();
            }

            private void actualizarCampos() {
                boolean usuarioNoVacio = !campoUsuario.getText().isEmpty();
                boolean contraseñaNoVacia = campoContraseña.getPassword().length > 0;

                mostrarContraseña(usuarioNoVacio);
                habilitarBoton(usuarioNoVacio && contraseñaNoVacia);

            }
            private void mostrarContraseña(boolean mostrar) {
                labelContraseña.setVisible(mostrar);
                campoContraseña.setVisible(mostrar);
            }
            private void habilitarBoton(boolean habilitar) {
                botonCrearUsuario.setEnabled(habilitar);
            }
        };
        campoUsuario.getDocument().addDocumentListener(documentListener);
        campoContraseña.getDocument().addDocumentListener(documentListener);
        
        botonCrearUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                boolean usuarioYaExiste = Usuario.checkNombresLogins(campoUsuario.getText().toString());
        
                botonLicencia.setEnabled(!usuarioYaExiste);
                botonTarjeta.setEnabled(!usuarioYaExiste);
        
                // Mostrar mensaje si el usuario ya existe
                if (usuarioYaExiste) {
                    JOptionPane.showMessageDialog(null, "El nombre de usuario ya ha sido utilizado. Por favor, elija otro.", "Registro", JOptionPane.INFORMATION_MESSAGE);
                    campoUsuario.setText("");
                } else {
                    login = campoUsuario.getText();
                    password = new String(campoContraseña.getPassword());
                    System.out.println(login);
                    System.out.println(password);
                    clienteNuevo = new Cliente(login, password, numeroCedula, nombre, mail, telefono, fnacimiento, nacionalidad);
                }
            }
        });
        
        ActionListener botonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                campoUsuario.setEnabled(false);
                campoContraseña.setEnabled(false);
                if (e.getSource() == botonLicencia) {
                    crearLicencia();
                    
                } else if (e.getSource() == botonTarjeta) {
                    crearTarjeta();
                    
                }
            }
        };
        botonLicencia.addActionListener(botonListener);
        botonTarjeta.addActionListener(botonListener);

        panelUsuarioContraseña.add(labelUsuario);
        panelUsuarioContraseña.add(campoUsuario);
        panelUsuarioContraseña.add(labelContraseña);
        panelUsuarioContraseña.add(campoContraseña);

        JLabel labelbienvenida = new JLabel("Bienvenido " + campoNombre.getText());
        panelB.add(labelbienvenida);
        panelB.add(panelUsuarioContraseña);
        panelB.add(botonCrearUsuario);
        panelB.add(botonLicencia);
        panelB.add(botonTarjeta);

        

        tabbedPane.add("Usuario y Contraseña", panelB);
    
    }
    public void crearLicencia() {
        guardarLicencia = false;
        JPanel panelC = new JPanel(new GridLayout(0,1));
        JPanel panelLicencia = new JPanel(new GridLayout(0, 2));
        JLabel labelMensaje = new JLabel("Ahora, ingrese la información de su Licencia");

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

        panelLicencia.add(labelNumeroL);
        panelLicencia.add(campoNumeroL);
        panelLicencia.add(labelPais);
        panelLicencia.add(campoPais);
        panelLicencia.add(labelFechaE);
        panelLicencia.add(panelFecha1);
        panelLicencia.add(labelFechaV);
        panelLicencia.add(panelFecha2);
        
        JButton botonGuardar = new JButton("Guardar");
        botonGuardar.setEnabled(false);

        DocumentListener documentListener = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                habilitarGuardar();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                habilitarGuardar();
            }
    
            @Override
            public void changedUpdate(DocumentEvent e) {
                habilitarGuardar();
            }
    
            private void habilitarGuardar() {
                boolean habilitar = !campoNumeroL.getText().isEmpty() &&
                        !campoPais.getText().isEmpty();
                botonGuardar.setEnabled(habilitar);
            }            
        };
        campoNumeroL.getDocument().addDocumentListener(documentListener);
        campoPais.getDocument().addDocumentListener(documentListener);

        panelC.add(labelMensaje);
        panelC.add(panelLicencia);
        panelC.add(botonGuardar);

        tabbedPane.add("Licencia de Conducción", panelC);
        botonGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                try{
                numerolicencia = Integer.parseInt(campoNumeroL.getText());
                pais = campoPais.getText();
                int expedicionL = Integer.parseInt(inputFechaL1);
                int vencimientoL = Integer.parseInt(inputFechaL2);
                System.out.println(numerolicencia);
                System.out.println(pais);
                System.out.println(expedicionL);
                System.out.println(vencimientoL);
                Licencia licenciaNueva = new Licencia(numerolicencia, expedicionL, vencimientoL, pais);
                clienteNuevo.setLicencia(licenciaNueva);
                guardarLicencia = true;
                tabbedPane.remove(panelC);
                cerrarAlGuardar();
                }catch(NumberFormatException e2){
                    VentanaMain.errorDialog("Guarde fechas");

                }
            }
        });
    }
    public void crearTarjeta() {
        inputFechaT = "";
        guardarTarjeta = false;
        JPanel panelD = new JPanel(new GridLayout(0,2));
        JLabel labelMensajeU = new JLabel("Por último, ingrese la informacion de su Método de Pago");
        JLabel labelAdvertencia = new JLabel("Nuestro sistema solamente acepta Tarjetas de Crédito");

        JLabel labelNombreT = new JLabel("Nombre de la persona o entidad Titular: ");
        PlaceHolderTextField campoNombreT = new PlaceHolderTextField("Ej: Juan López");
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
        botonGuardar.setEnabled(false);

        DocumentListener documentListener = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                habilitarGuardar();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                habilitarGuardar();
            }
    
            @Override
            public void changedUpdate(DocumentEvent e) {
                habilitarGuardar();
            }
    
            private void habilitarGuardar() {
                boolean habilitar = !campoNombreT.getText().isEmpty() &&
                        !campoNumeroT.getText().isEmpty() &&
                        !campoMarca.getText().isEmpty() &&
                        !inputFechaT.isEmpty();
                botonGuardar.setEnabled(habilitar);
            }            
        };
        campoNombreT.getDocument().addDocumentListener(documentListener);
        campoNumeroT.getDocument().addDocumentListener(documentListener);
        campoMarca.getDocument().addDocumentListener(documentListener);

        panelD.add(labelMensajeU);
        panelD.add(new JLabel("\n"));
        panelD.add(labelAdvertencia);
        panelD.add(new JLabel("\n"));
        panelD.add(labelNombreT);
        panelD.add(campoNombreT);
        panelD.add(labelNumeroT);
        panelD.add(campoNumeroT);
        panelD.add(labelMarca);
        panelD.add(campoMarca);
        panelD.add(labelFechaV);
        panelD.add(panelFechaT);
        panelD.add(new JLabel("\n"));
        panelD.add(botonGuardar);

        tabbedPane.add("Medio de Pago", panelD);
        botonGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                numeroT = Long.parseLong(campoNumeroT.getText());
                marca = campoMarca.getText();
                titular = campoNombreT.getText();
                int vencimientoT = Integer.parseInt(inputFechaT);
                System.out.println(numeroT);
                System.out.println(marca);
                System.out.println(titular);
                System.out.println(vencimientoT);
                Tarjeta tarjetaNueva = new Tarjeta(numeroT, vencimientoT, marca, titular);
                clienteNuevo.setTarjeta(tarjetaNueva);
                guardarTarjeta = true;
                tabbedPane.remove(panelD);
                cerrarAlGuardar();
            }
        });
    }
    private void cerrarAlGuardar() {
        if (guardarTarjeta  && guardarLicencia) {
            Usuario.addNombreLogin(login);
            Usuario.addNumCedulas(numeroCedula);
            Usuario.addCliente(clienteNuevo);
            dispose();
            
        }
    }
    private static JPanel setPanelSuperior(JTabbedPane pane) {
        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
    
        JPanel panelSupIzq = new JPanel();
        JLabel nomEmpresa = new JLabel(Inventario.getNombreCompania());
    
        JLabel imagenEmpresa = new JLabel();
        Icon icon = new ImageIcon("./imagenes/logo2.png");
        imagenEmpresa.setIcon(icon);
    
        panelSupIzq.add(imagenEmpresa);
        panelSupIzq.add(nomEmpresa);
        panelSuperior.add(panelSupIzq, BorderLayout.WEST);
    
        JPanel panelSupDere = new JPanel();
        JButton cerrarButton = new JButton("Cancelar");
        cerrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Inventario.updateSistema();
                } catch (IOException e1) {
                e1.printStackTrace();
                }   
            
            }   
        });
        panelSupDere.add(cerrarButton);
        panelSuperior.add(panelSupDere, BorderLayout.EAST);

        return panelSuperior;
    }



    
    //public static void main(String[] args) {
    //    SwingUtilities.invokeLater(() -> new VentanaRegistro());
    //}
}

    



