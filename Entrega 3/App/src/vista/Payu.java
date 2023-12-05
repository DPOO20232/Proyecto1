package vista;

import javax.swing.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import modelo.Cliente;
import java.awt.*;
import java.awt.event.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Payu extends pasarelaPago implements ActionListener {

    public Payu(Cliente cliente,String motivoPago, double montoPago, int ID,String pathArchivo ) {
        super(cliente,motivoPago,montoPago,ID,pathArchivo);
        this.frame= new JFrame();
        this.frame.setTitle("Pasarela de Pago");
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize(450, 300);
        this.frame.setLocationRelativeTo(null);
        // Crear paneles de tarjetas
        cards = new JPanel(new CardLayout());
        cardLayout = (CardLayout) cards.getLayout();
        String nombrePasarela="PAYU";
        Font fuenteTitulo= new Font("Serif", Font.BOLD, 15);
        Font fuenteNormal= new Font("Serif", Font.CENTER_BASELINE, 12);
        Color color= new Color(152, 251, 152);// verde
// Panel 1: Bienvenida y botón "Continuar"
        JPanel panelBienvenida = new JPanel();
        panelBienvenida.setLayout(new BoxLayout(panelBienvenida, BoxLayout.Y_AXIS));
        JLabel bienvenidaLabel = new JLabel("Bienvenido a Pasarela "+nombrePasarela);
        JLabel motivoLabel = new JLabel("Motivo pago: "+motivoPago+". Monto a pagar(COP):"+montoPago);
        bienvenidaLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        motivoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        bienvenidaLabel.setFont(fuenteTitulo); // Fuente serif y tamaño 20
        motivoLabel.setFont(fuenteNormal);
        panelBienvenida.add(bienvenidaLabel);
        panelBienvenida.add(motivoLabel);
        JButton continuarButton = new JButton("Continuar");
        continuarButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        continuarButton.addActionListener(this);
        continuarButton.setActionCommand("Continuar");
        panelBienvenida.add(continuarButton);
        panelBienvenida.setBackground(color); // Color
        cards.add(panelBienvenida, "Bienvenida");
        
        // Panel 2: Formulario de tarjeta de crédito
        JPanel formularioPanel = new JPanel(new GridLayout(0, 2));

        JLabel nomTarjeta =new JLabel("Titular de tarjeta:");
        nomTarjeta.setFont(fuenteNormal);
        formularioPanel.add(nomTarjeta);

        titularTarjetaField = new JTextField();
        titularTarjetaField.setFont(fuenteNormal);
        formularioPanel.add(titularTarjetaField);

        JLabel numTarjeta =new JLabel("Número de tarjeta:");
        numTarjeta.setFont(fuenteNormal);
        formularioPanel.add(numTarjeta);

        numeroTarjetaField = new NumericOnlyTextField();
        numeroTarjetaField.setFont(fuenteNormal);
        formularioPanel.add(numeroTarjetaField);

        JLabel fechaVencimiento=new JLabel("Fecha vencimiento (yyyy/mm/dd):");
        fechaVencimiento.setFont(fuenteNormal);
        formularioPanel.add(fechaVencimiento);

        fechaVencimientoField = new NumericOnlyTextField();
        fechaVencimientoField.setFont(fuenteNormal);
        formularioPanel.add(fechaVencimientoField);

        JLabel codigoSeguridad=new JLabel("Código de seguridad:");
        codigoSeguridad.setFont(fuenteNormal);
        formularioPanel.add(codigoSeguridad);

        codigoSeguridadField = new NumericOnlyTextField();
        codigoSeguridadField.setFont(fuenteNormal);
        formularioPanel.add(codigoSeguridadField);

        JLabel marcaTarjeta=new JLabel("Marca de la tarjeta:");
        marcaTarjeta.setFont(fuenteNormal);
        formularioPanel.add(marcaTarjeta);

        String[] opciones = {"Visa", "Mastercard", "American Express", "Discover", "Chase Sapphire"};
        JComboBox<String> marcaTarjetaCB = new JComboBox<>(opciones);
        marcaTarjetaCB.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                label.setFont(fuenteNormal); // Establecer la fuente deseada
                return label;
            }
        });
        marcaTarjetaCB.setSelectedIndex(0);
        marcaTarjetaComboBox=marcaTarjetaCB;
        formularioPanel.add(marcaTarjetaCB);

        JButton siguienteButton = new JButton("Siguiente");
        siguienteButton.setFont(fuenteTitulo);
        siguienteButton.addActionListener(this);
        siguienteButton.setActionCommand("Siguiente");
        formularioPanel.setBackground(color); // Color
        formularioPanel.add(siguienteButton);
        cards.add(formularioPanel, "Formulario");

        // Panel 3: Mensaje de cobro exitoso
        JPanel cobroExitosoPanel = new JPanel();
        JLabel cobroExitosoLabel = new JLabel("Cobro exitoso!");
        cobroExitosoLabel.setFont(fuenteTitulo);
        cobroExitosoPanel.add(cobroExitosoLabel);
        JButton cerrarButton = new JButton("Continuar");
        cerrarButton.setFont(fuenteNormal);
        cerrarButton.addActionListener(this);
        cerrarButton.setActionCommand("Cerrar");
        cobroExitosoPanel.add(cerrarButton);
        cobroExitosoPanel.setBackground(color); // Color

        cards.add(cobroExitosoPanel, "CobroExitoso");

        // Panel 4: Mensaje de cobro NO exitoso
        JPanel errorPanel = new JPanel();
        JLabel errorLabel = new JLabel("Transacción no completada");
        errorLabel.setFont(fuenteTitulo);
        errorPanel.add(errorLabel);
        JButton cerrar1Button = new JButton("Continuar");
        cerrar1Button.setFont(fuenteNormal);
        cerrar1Button.addActionListener(this);
        cerrar1Button.setActionCommand("Cerrar");
        errorPanel.add(cerrar1Button);
        errorPanel.setBackground(color); // Color
        cards.add(errorPanel, "NoCompletado");
        
        this.frame.add(cards);
        this.frame.setVisible(true);
        }
        
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
        
            if (command.equals("Continuar")) {
                cardLayout.show(cards, "Formulario");
            } else if (command.equals("Siguiente")) {
                numeroTarjetaInput= numeroTarjetaField.getText();
                fechaVencimientoInput= fechaVencimientoField.getText();
                codigoSeguridadInput = codigoSeguridadField.getText();
                marcaTarjetaInput= (String) marcaTarjetaComboBox.getSelectedItem();
                titularTarjetaInput= titularTarjetaField.getText();

                boolean tarjetaValida= this.tarjetaValida();

                if(tarjetaValida){
                    this.setTarjeta();
                    this.completarTransferencia();
                    System.out.println(this.transferenciaCompletada);
                    if (this.transferenciaCompletada){
                    cardLayout.show(cards, "CobroExitoso");
                    crearEntrada();
                    }
                    else{
                    int respuesta = JOptionPane.showConfirmDialog(null, "La transferencia no se logro completar,¿Desea intentarlo de nuevo? (En caso de dar clic en no se cancelará la reserva)", "Confirmación", JOptionPane.YES_NO_OPTION);
                    if (respuesta == JOptionPane.YES_OPTION) {
                        // Si hace clic en "Sí"
                    } else if (respuesta == JOptionPane.NO_OPTION) {
                        transferenciaCompletada=false;
                        cardLayout.show(cards, "NoCompletado");
                        System.out.println("No");
                    }                        
                    }
                }
                else{
                    int respuesta = JOptionPane.showConfirmDialog(null, "Los datos ingresados no fueron correctos ¿Desea intentarlo de nuevo? (En caso de dar clic en no se cancelará la reserva)", "Confirmación", JOptionPane.YES_NO_OPTION);
                    if (respuesta == JOptionPane.YES_OPTION) {
                        // Si hace clic en "Sí"
                    } else if (respuesta == JOptionPane.NO_OPTION) {
                        // Si hace clic en "No"
                        cardLayout.show(cards, "NoCompletado");
                        System.out.println("No");
                    }
                    
                }

            } else if (command.equals("Cerrar")) {
                this.frame.dispose(); // Cerrar la ventana al hacer clic en "Continuar"
            }
        }

    public void crearEntrada(){
        JSONObject nuevaEntrada = new JSONObject();
        nuevaEntrada.put("fecha",Integer.toString(this.yyyymmddActual));
        nuevaEntrada.put("tipoDePago", this.motivoPago);
        nuevaEntrada.put("ID", Integer.toString(this.IDtransaccion));
        nuevaEntrada.put("cedulaCliente",Integer.toString(this.cliente.getNumeroCedula()));
        nuevaEntrada.put("numeroTarjeta",this.numeroTarjetaInput);
        nuevaEntrada.put("montoCobrado", Double.toString(this.montoPago));
        agregarEntrada(nuevaEntrada);
        }

    @SuppressWarnings("unchecked")

    public void agregarEntrada(Object nuevaEntrada) {
        JSONParser parser = new JSONParser();
        try {
            JSONArray datos;
            try (FileReader fileReader = new FileReader(pathArchivo)) {
                Object obj = parser.parse(fileReader);
                datos = (JSONArray) obj;
            } catch (IOException | ParseException | ClassCastException e) {
                datos = new JSONArray();
            }
    
            datos.add(nuevaEntrada);
    
            try (FileWriter fileWriter = new FileWriter(pathArchivo)) {
                fileWriter.write(datos.toJSONString());
                fileWriter.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
