package vista;

import java.awt.GridLayout;

import javax.swing.*;
import javax.swing.event.DocumentListener;
import javax.swing.event.DocumentEvent;
import java.util.Calendar;
import java.awt.*;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaRegistro extends JFrame {
    private JLabel nombreEmpresa;
    private JLabel textoBienvenida;
    private JLabel instrucciones;
    private JLabel etiquetaDatos;
    private JLabel etiquetaDocumento;
    private PlaceHolderTextField campoDocumento;
    private JLabel etiquetaNombre;
    private PlaceHolderTextField campoNombre;
    private JLabel etiquetaCorreo;
    private PlaceHolderTextField campoCorreo;
    private JLabel etiquetaTelefono;
    private PlaceHolderTextField campoTelefono;
    private JLabel etiquetaFecha;
    private JComboBox<String> listaDia;
    private JComboBox<String> listaMes;
    private JComboBox<String> listaAnho;
    private int limite;
    private JLabel etiquetaNacionalidad;
    private PlaceHolderTextField campoNacionalidad;
    private JButton botonContinuar;
    private JTabbedPane tab1;


    public VentanaRegistro() {
        super("Registro de Usuario");
        JPanel panel = new JPanel(new GridLayout(0, 1));
        JPanel panelInfo = new JPanel(new GridLayout(0, 1));

        nombreEmpresa = new JLabel("<Nombre Empresa>");
        textoBienvenida = new JLabel("¡Bienvenido a nuestro sistema!");
        instrucciones = new JLabel("Necesitamos que por favor ingrese los siguientes datos para crear su cuenta.");
        etiquetaDatos = new JLabel("Datos Personales");

        panelInfo.add(nombreEmpresa);
        panelInfo.add(textoBienvenida);
        panelInfo.add(instrucciones);
        panelInfo.add(etiquetaDatos);

        JPanel panelDatos = new JPanel(new GridLayout(0, 2));
        etiquetaDocumento = new JLabel("Documento de Identidad: ");
        campoDocumento = new PlaceHolderTextField("Ej: 1000000000");
        etiquetaNombre = new JLabel("Nombre Completo: ");
        campoNombre = new PlaceHolderTextField("Ej: Juan López");
        etiquetaCorreo = new JLabel("Correo Electrónico: ");
        campoCorreo = new PlaceHolderTextField("Ej: ejemplo@correo.com");
        etiquetaTelefono = new JLabel("Número de Teléfono Celular: ");
        campoTelefono = new PlaceHolderTextField("Ej: 3000000000");
        etiquetaFecha = new JLabel("Fecha de Nacimiento: ");
        
    
        
        etiquetaNacionalidad = new JLabel("Nacionalidad: ");
        campoNacionalidad = new PlaceHolderTextField("Ej: Colombia");

        JPanel panelFecha = new JPanel(new GridLayout(0, 3));

        panelDatos.add(etiquetaDocumento);
        panelDatos.add(campoDocumento);
        panelDatos.add(etiquetaNombre);
        panelDatos.add(campoNombre);
        panelDatos.add(etiquetaCorreo);
        panelDatos.add(campoCorreo);
        panelDatos.add(etiquetaTelefono);
        panelDatos.add(campoTelefono);
        panelDatos.add(etiquetaFecha);
        //panelFecha.add(listaAnho);
        //panelFecha.add(listaMes);
        //panelFecha.add(listaDia);
        //panelDatos.add(panelFecha);
        panelDatos.add(etiquetaNacionalidad);
        panelDatos.add(campoNacionalidad);

        panel.add(panelInfo);
        panel.add(panelDatos);
        botonContinuar = new JButton("Continuar");
        botonContinuar.setPreferredSize(new Dimension(50,30));
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

            public void habilitarBotonContinuar(){
                boolean habilitar = !campoDocumento.getText().isEmpty() &&
                            !campoNombre.getText().isEmpty() &&
                            !campoCorreo.getText().isEmpty() &&
                            !campoTelefono.getText().isEmpty() &&
                            !campoNacionalidad.getText().isEmpty();
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
                tab1 = new JTabbedPane();
                tab1.addTab("Usuario y Contraseña", new crearUsuario());
                add(tab1);
                revalidate();
            }
        });

        panel.add(botonContinuar);
        add(panel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(500, 600);
        setVisible(true);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaRegistro());
    }

}

/*
 *  public static String nuevoPanelFecha(int anio, int dia, int mes){
 * //si pasan por parametro 0,0,0 -> se puede elegir cualquier fecha
 * sino los parametros son el limite
 * }
 * 
 * 
 * 
 */
