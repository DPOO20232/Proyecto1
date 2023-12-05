package vista;

import java.awt.CardLayout;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import modelo.Cliente;
import modelo.Tarjeta;

public abstract class pasarelaPago {
    protected static String pathArchivo;
    protected Cliente cliente;
    protected int IDtransaccion;
    protected JPanel cards;
    protected JFrame frame;
    protected CardLayout cardLayout;
    protected JTextField numeroTarjetaField, fechaVencimientoField, codigoSeguridadField,titularTarjetaField;
    protected JComboBox<String> marcaTarjetaComboBox;
    protected String motivoPago;
    protected double montoPago;
    protected boolean transferenciaCompletada;
    protected String numeroTarjetaInput,fechaVencimientoInput,codigoSeguridadInput,marcaTarjetaInput,titularTarjetaInput;
    protected Tarjeta tarjetaCliente;
    protected int yyyymmddActual;
    public pasarelaPago(Cliente cliente, String motivoPago, double montoPago, int ID, String pathArchivo_u){
        this.cliente=cliente;
        this.transferenciaCompletada=false;
        this.motivoPago=motivoPago;
        this.montoPago=montoPago;
        this.IDtransaccion=ID;
        pathArchivo=pathArchivo_u;
    }
    
    public boolean getTransferenciaCompletada(){
        return this.transferenciaCompletada;
    }
    public void setTransferenciaCompletada(boolean completada){
        this.transferenciaCompletada=completada;
    }
    public static void setPathArchivo(String path){
        pathArchivo=path;
    }
    public static String getPathArchivo(){
        return pathArchivo;
    }
    public void setTarjeta(){
        this.tarjetaCliente= new Tarjeta(Long.parseLong(this.numeroTarjetaInput),false,0L, Integer.parseInt(fechaVencimientoInput),this.marcaTarjetaInput, this.titularTarjetaInput);
        this.cliente.setTarjeta(tarjetaCliente);
    }
    public void completarTransferencia(){
        this.setTransferenciaCompletada(this.tarjetaCliente.realizarCobro(this.montoPago));
    }
    public void crearEntrada(){
    }
    public void agregarEntrada(Object nuevaEntrada){
    }

    public boolean tarjetaValida(){
        // Crear una instancia de Calendar
        Calendar calendar = Calendar.getInstance();
        // Obtener la fecha actual
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1; // Los meses en Calendar van de 0 a 11
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        // Formatear la fecha en formato yyyymmdd
        String fechaYYYYMMDD = String.format("%04d%02d%02d", year, month, day);
        // Convertir la fecha formateada en un entero si se desea
        this.yyyymmddActual = Integer.parseInt(fechaYYYYMMDD);
        boolean retorno =false;
        try{
        boolean numTarjetaBoolean= Long.parseLong(numeroTarjetaInput)>=1000000000000000L&&numeroTarjetaInput.length()>15 && numeroTarjetaInput.length()<17;
        boolean codBoolean= codigoSeguridadInput.length()>2 && codigoSeguridadInput.length()<4 && Integer.parseInt(codigoSeguridadInput)>99;
        boolean fechaBooleanMayor = Integer.parseInt(fechaVencimientoInput)>=this.yyyymmddActual;
        boolean fechaBooleanValida=false;
        //Chequeo fecha
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyyMMdd");
        formatoFecha.setLenient(false); // Establece el modo estricto para la validación de fechas
        try {
            formatoFecha.parse(fechaVencimientoInput);
            fechaBooleanValida= true;
        } catch (ParseException e) {
            fechaBooleanValida=false;
        }
        String patron = "^[^0-9]*$";
        boolean titularTarjetaBoolean= !titularTarjetaInput.equals("")&&titularTarjetaInput.matches(patron);
        if (numTarjetaBoolean&&codBoolean&&fechaBooleanMayor&&fechaBooleanValida&&titularTarjetaBoolean){
            retorno=true;
        }
            return retorno;
        }
        catch (NumberFormatException e){
            return false;
        }        
    }
} 


