package vista;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentListener;
import javax.swing.event.DocumentEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

public class VentanaRegistro extends JFrame {
    private JTabbedPane tabbedPane;
    private JLabel nombreEmpresa;
    private JLabel textoBienvenida;
    private JLabel instrucciones;
    private JLabel etiquetaDatos;
    private JButton botonContinuar;
    private PlaceHolderTextField campoNombre;
    private String inputFechaNacimiento;
    

    public VentanaRegistro(){
        super("Registro de Usuario");

        tabbedPane = new JTabbedPane();
        JPanel panelDatos = new JPanel(new GridLayout(0,2));

        nombreEmpresa = new JLabel("<NombreEmpresa>");
        textoBienvenida = new JLabel("¡Bienvenido a nuestro sistema!");
        instrucciones = new JLabel("Necesitamos que por favor ingrese los siguientes datos para crear su cuenta.");
        etiquetaDatos = new JLabel("Datos Personales:");

        panelDatos.add(nombreEmpresa);
        panelDatos.add(new JLabel("\n"));
        panelDatos.add(textoBienvenida);
        panelDatos.add(new JLabel("\n"));
        panelDatos.add(instrucciones);
        panelDatos.add(new JLabel("\n"));
        panelDatos.add(etiquetaDatos);
        panelDatos.add(new JLabel("\n"));

        JLabel etiquetaDocumento = new JLabel("Documento de Identidad: ");
        NumericOnlyTextField campoDocumento = new NumericOnlyTextField();
        JLabel etiquetaNombre = new JLabel("Nombre Completo: ");
        campoNombre = new PlaceHolderTextField("Ej: Juan López");
        JLabel etiquetaCorreo = new JLabel("Correo Electrónico: ");
        PlaceHolderTextField campoCorreo = new PlaceHolderTextField("Ej: correo@ejemplo.com");
        JLabel etiquetaTelefono = new JLabel("Número de Teléfono Celular: ");
        NumericOnlyTextField campoTelefono = new NumericOnlyTextField();
        JLabel fechaNacimiento = new JLabel("Fecha de Nacimiento: ");

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

        botonContinuar = new JButton("Continuar");
        botonContinuar.setPreferredSize(new Dimension(50, 30));
        botonContinuar.setEnabled(false);
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
                tabbedPane.remove(panelDatos);
                crearUsuario();
                botonContinuar.setEnabled(false);
            }
        });

        panelDatos.add(botonContinuar);
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

        DocumentListener documentListener = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                mostrarCampoContraseña(!campoUsuario.getText().isEmpty());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                mostrarCampoContraseña(!campoUsuario.getText().isEmpty());
            }
    
            @Override
            public void changedUpdate(DocumentEvent e) {
                mostrarCampoContraseña(!campoUsuario.getText().isEmpty());
            }
    
            private void mostrarCampoContraseña(boolean mostrar) {
                labelContraseña.setVisible(mostrar);
                campoContraseña.setVisible(mostrar);
            }            
        };
        campoUsuario.getDocument().addDocumentListener(documentListener);
        campoContraseña.getDocument().addDocumentListener(documentListener);
        JButton botonLicencia = new JButton("Añadir Licencia de Conducción");
        JButton botonTarjeta = new JButton("Añadir Método de Pago");
        botonLicencia.setPreferredSize(new Dimension(50, 30));
        botonTarjeta.setPreferredSize(new Dimension(50, 30));
        botonLicencia.setEnabled(false);
        botonTarjeta.setEnabled(false);
        
        campoContraseña.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                habilitarBotones((campoContraseña.getPassword().length > 0)&&!campoUsuario.getText().isEmpty());
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                habilitarBotones((campoContraseña.getPassword().length > 0)&&!campoUsuario.getText().isEmpty());
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                habilitarBotones((campoContraseña.getPassword().length > 0)&&!campoUsuario.getText().isEmpty());
            }
            private void habilitarBotones(boolean habilitar) {
                if (!campoUsuario.getText().isEmpty()){
                botonLicencia.setEnabled(habilitar);
                botonTarjeta.setEnabled(habilitar);
            }
            }
        });

        campoUsuario.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                habilitarBotones((campoContraseña.getPassword().length > 0)&&!campoUsuario.getText().isEmpty());
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                habilitarBotones((campoContraseña.getPassword().length > 0)&&!campoUsuario.getText().isEmpty());
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                habilitarBotones((campoContraseña.getPassword().length > 0)&&!campoUsuario.getText().isEmpty());
            }
            private void habilitarBotones(boolean habilitar) {
                if (!campoUsuario.getText().isEmpty()){
                botonLicencia.setEnabled(habilitar);
                botonTarjeta.setEnabled(habilitar);
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
        panelB.add(botonLicencia);
        panelB.add(botonTarjeta);

        tabbedPane.add("Usuario y Contraseña", panelB);
    
    }
    public void crearLicencia() {
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
                //TODO verificar fechas y guardar, usar booleans
                tabbedPane.remove(panelC);
            }
        });
    }
    public void crearTarjeta() {
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
        
        panelTarjeta.add(labelNombreT);
        panelTarjeta.add(campoNombreT);
        panelTarjeta.add(labelNumeroT);
        panelTarjeta.add(campoNumeroT);
        panelTarjeta.add(labelMarca);
        panelTarjeta.add(campoMarca);
        panelTarjeta.add(labelFechaV);
        
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
                //TODO verificar fechas y guardar
                tabbedPane.remove(panelD);
            }
        });
    }

    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaRegistro());
    }
}

    



