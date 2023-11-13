package vista;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentListener;
import modelo.Inventario;
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
import java.awt.Window;

public class VentanaRegistro extends JFrame {
    private JTabbedPane tabbedPane;
    JPanel panelSuperior;
    private JLabel textoBienvenida;
    private JLabel instrucciones;
    private JLabel etiquetaDatos;
    private JButton botonContinuar;
    private PlaceHolderTextField campoNombre;
    private String inputFechaNacimiento;
    private boolean guardarLicencia;
    private boolean guardarTarjeta;
    

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
            panelFecha.add(anioBox);
            anioBox.setEnabled(false);
            DateComboBoxPanel date1= new DateComboBoxPanel(Integer.parseInt(anio));
            date1.setDefaulDayComboBox();
            date1.setDefaultMonthComboBox();
            panelFecha.add(date1);
            JButton updateDatebutton= new JButton("Cambiar Fecha");
            panelFecha.add(updateDatebutton);
            JButton saveDatebutton= new JButton("Guardar Fecha");
            panelFecha.add(saveDatebutton);
            inputFechaNacimiento="";
            System.out.println(":"+inputFechaNacimiento);

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
                    System.out.println(":"+inputFechaNacimiento);
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
                System.out.println("input fecha actual:"+inputFechaNacimiento);
                boolean habilitar = !campoDocumento.getText().isEmpty() &&
                        !campoNombre.getText().isEmpty() &&
                        !campoCorreo.getText().isEmpty() &&
                        !campoTelefono.getText().isEmpty() && !campoNacionalidad.getText().isEmpty();
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
        setSize(840, 600);
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

        JButton botonLicencia = new JButton("Añadir Licencia de Conducción");
        JButton botonTarjeta = new JButton("Añadir Método de Pago");
        botonLicencia.setPreferredSize(new Dimension(50, 30));
        botonTarjeta.setPreferredSize(new Dimension(50, 30));
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
                habilitarBotones(usuarioNoVacio && contraseñaNoVacia);
            }
            private void mostrarContraseña(boolean mostrar) {
                labelContraseña.setVisible(mostrar);
                campoContraseña.setVisible(mostrar);
            }
            private void habilitarBotones(boolean habilitar) {
                botonLicencia.setEnabled(habilitar);
                botonTarjeta.setEnabled(habilitar);
            }
        };
        campoUsuario.getDocument().addDocumentListener(documentListener);
        campoContraseña.getDocument().addDocumentListener(documentListener);
        
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
        
        panelLicencia.add(labelNumeroL);
        panelLicencia.add(campoNumeroL);
        panelLicencia.add(labelPais);
        panelLicencia.add(campoPais);
        panelLicencia.add(labelFechaE);
        panelLicencia.add(labelFechaV);
        
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
                guardarLicencia = true;
                tabbedPane.remove(panelC);
                cerrarAlGuardar();
            }
        });
    }
    public void crearTarjeta() {
        guardarTarjeta = false;
        JPanel panelD = new JPanel(new GridLayout(0,1));
        JPanel panelTarjeta = new JPanel(new GridLayout(0, 2));
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
        panelFechaT.add(monthComboBox);
        panelFechaT.add(yearComboBox);
        String input;
        
        
        panelTarjeta.add(labelNombreT);
        panelTarjeta.add(campoNombreT);
        panelTarjeta.add(labelNumeroT);
        panelTarjeta.add(campoNumeroT);
        panelTarjeta.add(labelMarca);
        panelTarjeta.add(campoMarca);
        panelTarjeta.add(labelFechaV);
        panelTarjeta.add(panelFechaT);
        
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
                        !campoMarca.getText().isEmpty();
                botonGuardar.setEnabled(habilitar);
            }            
        };
        campoNombreT.getDocument().addDocumentListener(documentListener);
        campoNumeroT.getDocument().addDocumentListener(documentListener);
        campoMarca.getDocument().addDocumentListener(documentListener);

        panelD.add(labelMensajeU);
        panelD.add(labelAdvertencia);
        panelD.add(panelTarjeta);
        panelD.add(botonGuardar);

        tabbedPane.add("Medio de Pago", panelD);
        botonGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                guardarTarjeta = true;
                tabbedPane.remove(panelD);
                cerrarAlGuardar();
            }
        });
    }
    private void cerrarAlGuardar() {
        if (guardarTarjeta  && guardarLicencia) {
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

    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaRegistro());
    }
}

    



