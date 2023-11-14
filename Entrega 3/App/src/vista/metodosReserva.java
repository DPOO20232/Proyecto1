package vista;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.TextStyle;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.TextStyle;

import javax.swing.DefaultComboBoxModel;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentListener;

import modelo.Categoria;
import modelo.Cliente;
import modelo.Inventario;
import modelo.Reserva;
import modelo.Sede;

import javax.swing.event.DocumentEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.List;
import java.util.Locale;
import java.io.IOException;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

public class metodosReserva extends JFrame {
    private JTabbedPane tabbedPane;
    JPanel panelSuperior;
    private JButton botonContinuar;
    
    public JTabbedPane menuReserva(Cliente cliente, boolean reservaEnSede) {

        //super("Reserva de Vehículo");
        tabbedPane = new JTabbedPane(); //Creación de la ventana
        
        // Pestaña 1: Fecha y hora de recogida y sede de recogida -----------------------------------------------------------------------------------------------------------------------------------------
        
        JPanel panel1 = new JPanel(new GridLayout(0,2));
        
        JLabel nombreEmpresa = new JLabel("<NombreEmpresa> \n");
        JLabel textoBienvenida = new JLabel("¡Bienvenido a nuestro sistema de Reserva! \n");
        
        
        
        botonContinuar = new JButton("Continuar");

        
        panel1.add(nombreEmpresa);
        panel1.add(textoBienvenida);
        
        
       
        // Fecha de recogida 
        JPanel panelFechaRec = new JPanel();       
        panelFechaRec.setLayout(new FlowLayout());
        DefaultComboBoxModel<String> opcionesAnio = new DefaultComboBoxModel<>(); //Se crean y se agregan las opciones disponibles para el año de recogida
        int anioActual = Calendar.getInstance().get(Calendar.YEAR);
        opcionesAnio.addElement(Integer.toString(anioActual));
        opcionesAnio.addElement(Integer.toString(anioActual+1));
        
        JComboBox<String> anioBox = new JComboBox<String>(opcionesAnio);
        anioBox.setSelectedIndex(0);

        String anio = anioBox.getSelectedItem().toString();
        DateComboBoxPanel date1 = new DateComboBoxPanel(Integer.parseInt(anio));
        date1.setDefaulDayComboBox();
        date1.setDefaultMonthComboBox();
        
        anioBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                
                String anio = anioBox.getSelectedItem().toString();
                VentanaMain.refresh(panelFechaRec);
                panelFechaRec.add(anioBox);
                anioBox.setEnabled(false);

                DateComboBoxPanel date1 = new DateComboBoxPanel(Integer.parseInt(anio));
                date1.setDefaulDayComboBox();
                date1.setDefaultMonthComboBox();
                panelFechaRec.add(date1);
                JButton updateDatebutton = new JButton("Cambiar Fecha");
                panelFechaRec.add(updateDatebutton);
                JButton saveDatebutton = new JButton("Guardar Fecha");
                panelFechaRec.add(saveDatebutton);
                saveDatebutton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e){
                        VentanaMain.refresh(panelFechaRec);
                        panelFechaRec.add(anioBox);
                        panelFechaRec.add(updateDatebutton);
                    }
                });
                updateDatebutton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e){
                        
                        VentanaMain.refresh(panelFechaRec);
                        panelFechaRec.add(anioBox);
                        anioBox.setEnabled(true);
                    }
                });
            }
        });
        panelFechaRec.add(anioBox); // Año agregado
        panel1.add(new JLabel("Fecha de recogida"));
        panel1.add(new JLabel("Fecha de recogida"));
        panel1.add(panelFechaRec);
        
        // Sede de recogida
        JPanel panelSedeRec = new JPanel();
        
        JComboBox<String> sedesRec = new JComboBox<>(); // El JComboBox se coloca en el centro del panel
        for (Sede i : Inventario.getListaSedes()) {
            sedesRec.addItem(Integer.toString(i.getID()) + ": " + i.getNombre());
        }
        sedesRec.setSelectedIndex(0);
        panelSedeRec.add(sedesRec, BorderLayout.CENTER);
        panel1.add(new JLabel("Sede de recogida"));
        panel1.add(panelSedeRec);
        int idSede = Integer.parseInt(sedesRec.getSelectedItem().toString().split(":")[0]);
        Sede eleccionSedeRec = Inventario.assignSede(idSede);
        
        // Hora de recogida
        JPanel panelHoraRec = new JPanel();
            //En este pedazo de código se saca el día de la semana...
        String fechaRecSelected = anioBox.getSelectedItem().toString() + date1.getText();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate fecha = LocalDate.parse(fechaRecSelected, formatter);
        DayOfWeek diaSemana = fecha.getDayOfWeek(); // Obtiene el día de la semana (enum DayOfWeek)
        String nombreDia = diaSemana.getDisplayName(TextStyle.FULL, Locale.getDefault()); // Convierte el enum a una cadena (Lunes, Martes, ..., Domingo)
            // Hasta aquí.
        
        List<Integer> horarioAtencionRec = new ArrayList<>(); //Aquí se define el horario de atención correcto
        
        if (!nombreDia.toLowerCase().equals("domingo") && !nombreDia.toLowerCase().equals("sabado") && !nombreDia.toLowerCase().equals("sábado")){
            horarioAtencionRec = eleccionSedeRec.getHorarioAtencionEnSemana();}
        else {
            horarioAtencionRec = eleccionSedeRec.getHorarioAtencionFinSemana();}

        DefaultComboBoxModel<String> opcionesHoraRec = new DefaultComboBoxModel<String>();
        int horaInicio = (int) Math.floor(horarioAtencionRec.get(0)/100);
        int horaCierre = (int) Math.floor(horarioAtencionRec.get(1)/100);
        for(int i = horaInicio; i <= horaCierre; i++){
            opcionesHoraRec.addElement(Integer.toString(i));};

        DefaultComboBoxModel<String> opcionesMinutosRec = new DefaultComboBoxModel<String>();
        opcionesMinutosRec.addElement("00");

        JComboBox<String> horaRecBox = new JComboBox<>(opcionesHoraRec);
        JComboBox<String> minRecBox = new JComboBox<>(opcionesMinutosRec);
    
    
        horaRecBox.setSelectedIndex(0);
        minRecBox.setSelectedIndex(0);

        panelHoraRec.add(horaRecBox);
        panelHoraRec.add(minRecBox);
        panel1.add(new JLabel("Hora de recogida"));
        panel1.add(new JLabel("Hora de recogida"));
        panel1.add(panelHoraRec);

        botonContinuar.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
                System.out.println(fechaRecSelected);
                if (!fechaRecSelected.equals("")){
                tabbedPane.setSelectedIndex(1);
                }
                else{
                    VentanaMain.errorDialog("Ingrese una fecha");
                }
            }
        });

        
        //Continuar
        panel1.add(botonContinuar);
        
        // ----------
        // Pestaña 2: Fecha y hora de devolucion -----------------------------------------------------------------------------------------------------------------------------------------
        
        JPanel panel2 = new JPanel(new GridLayout(0,2));
        
        JButton botonContinuar2 = new JButton("Continuar");

        
        // Fecha devolución

        JPanel panelFechaDev = new JPanel();       
        panelFechaRec.setLayout(new FlowLayout());
        DefaultComboBoxModel<String> opcionesAnio2 = new DefaultComboBoxModel<>(); //Se crean y se agregan las opciones disponibles para el año de recogida
        
        opcionesAnio2.addElement(Integer.toString(anioActual));
        opcionesAnio2.addElement(Integer.toString(anioActual+1));
        DateComboBoxPanel date2 = new DateComboBoxPanel(Integer.parseInt(anio));
        date2.setDefaulDayComboBox();
        date2.setDefaultMonthComboBox();
        
        JComboBox<String> anioBox2 = new JComboBox<String>(opcionesAnio);
        anioBox2.setSelectedIndex(0);
        anioBox2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                
                String anio = anioBox2.getSelectedItem().toString();
                VentanaMain.refresh(panelFechaDev);
                panelFechaDev.add(anioBox2);
                anioBox2.setEnabled(false);

                DateComboBoxPanel date2 = new DateComboBoxPanel(Integer.parseInt(anio));
                date2.setDefaulDayComboBox();
                date2.setDefaultMonthComboBox();
                panelFechaDev.add(date2);

                JButton updateDatebutton = new JButton("Cambiar Fecha");
                panelFechaDev.add(updateDatebutton);
                JButton saveDatebutton = new JButton("Guardar Fecha");
                panelFechaDev.add(saveDatebutton);
                saveDatebutton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e){
                        VentanaMain.refresh(panelFechaDev);
                        panelFechaDev.add(anioBox2);
                        panelFechaDev.add(updateDatebutton);
                    }
                });
                updateDatebutton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e){
                        
                        VentanaMain.refresh(panelFechaDev);
                        panelFechaDev.add(anioBox2);
                        anioBox2.setEnabled(true);
                    }
                });
            }
        });
        panelFechaDev.add(anioBox2); // Año agregado
        panel2.add(new JLabel("Fecha de devolución"));
        panel2.add(new JLabel("Fecha de devolución"));
        panel2.add(panelFechaDev);
        
        
        // Sede de devolución
        JPanel panelSedeDev = new JPanel();
        
        JComboBox<String> sedesDev = new JComboBox<>(); // El JComboBox se coloca en el centro del panel
        for (Sede i : Inventario.getListaSedes()) {
            sedesDev.addItem(Integer.toString(i.getID()) + ": " + i.getNombre());
        }
        sedesDev.setSelectedIndex(0);
        panelSedeDev.add(sedesDev, BorderLayout.CENTER);
        panel2.add(new JLabel("Sede de devolución"));
        panel2.add(new JLabel("Sede de devolución"));
        panel2.add(panelSedeDev);

        int idSede2 = Integer.parseInt(sedesDev.getSelectedItem().toString().split(":")[0]);
        Sede eleccionSedeDev = Inventario.assignSede(idSede2);
        
        // Hora de devolución
        JPanel panelHoraDev = new JPanel();
            //En este pedazo de código se saca el día de la semana...
        String fechaDevSelected = anioBox2.getSelectedItem().toString() + date2.getText();
        DateTimeFormatter formater = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate fecha2 = LocalDate.parse(fechaDevSelected, formater);
        DayOfWeek diaSemana2 = fecha2.getDayOfWeek(); // Obtiene el día de la semana (enum DayOfWeek)
        String nombreDia2 = diaSemana2.getDisplayName(TextStyle.FULL, Locale.getDefault()); // Convierte el enum a una cadena (Lunes, Martes, ..., Domingo)
            // Hasta aquí.
        
        List<Integer> horarioAtencionDev = new ArrayList<>(); //Aquí se define el horario de atención correcto
        
        if (!nombreDia2.toLowerCase().equals("domingo") && !nombreDia.toLowerCase().equals("sabado") && !nombreDia.toLowerCase().equals("sábado")){
            horarioAtencionDev = eleccionSedeDev.getHorarioAtencionEnSemana();}
        else {
            horarioAtencionDev = eleccionSedeDev.getHorarioAtencionFinSemana();}

        DefaultComboBoxModel<String> opcionesHoraDev = new DefaultComboBoxModel<String>();
        int horaInicio2 = (int) Math.floor(horarioAtencionDev.get(0)/100);
        int horaCierre2 = (int) Math.floor(horarioAtencionDev.get(1)/100);
        for(int i = horaInicio2; i <= horaCierre2; i++){
            opcionesHoraDev.addElement(Integer.toString(i));};

        DefaultComboBoxModel<String> opcionesMinutosDev = new DefaultComboBoxModel<String>();
        opcionesMinutosDev.addElement("00");

        JComboBox<String> horaDevBox = new JComboBox<>(opcionesHoraDev);
        JComboBox<String> minDevBox = new JComboBox<>(opcionesMinutosDev);
    
        horaDevBox.setSelectedIndex(0);
        minDevBox.setSelectedIndex(0);

        panelHoraDev.add(horaDevBox);
        panelHoraDev.add(minDevBox);
        panel2.add(new JLabel("Hora de devolución"));
        panel2.add(panelHoraDev);

        botonContinuar2.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
                System.out.println(fechaDevSelected);
                System.out.println();
                if (!fechaDevSelected.equals("")&&Integer.parseInt(fechaDevSelected)>Integer.parseInt(fechaRecSelected)){
                tabbedPane.setSelectedIndex(2);
                }
                else{
                    VentanaMain.errorDialog("Ingrese una fecha válida.");
                }
            }
        });
        
        //Continuar
        JButton botonContinuar3 = new JButton("Continuar");
        panel2.add(botonContinuar3);
        
        // ----------
        // Pestaña 3: Seleccionar categoría -----------------------------------------------------------------------------------------------------------------------------------------
        JPanel panel3 = new JPanel();
        panel3.setPreferredSize(new Dimension(200, 400)); // Ajustar las dimensiones 
        panel3.setLayout(new BorderLayout(20, 0));
        panel3.add(new JLabel("Seleccione la categoría"), BorderLayout.NORTH);// El JLabel se coloca en la parte superior del panel
        JButton finalizar= new JButton("Registrar reserva");
        JPanel panelCategorias = new JPanel();
        JComboBox<String> categBox = new JComboBox<>(); // El JComboBox se coloca en el centro del panel
        for (Categoria i : Inventario.getListaCategorias()) {
            categBox.addItem(Integer.toString(i.getID()) + ": " + i.getnombreCategoria());
        }
        categBox.setSelectedIndex(0);
        panelCategorias.add(categBox, BorderLayout.CENTER);
        panelCategorias.add(finalizar, BorderLayout.SOUTH);
        panel3.add(panelCategorias);
        finalizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                try {
                    Categoria categoria= Inventario.assignCategoria(Integer.parseInt(categBox.getSelectedItem().toString().split(":")[0]));
                    int horaRec=Integer.parseInt(horaRecBox.getSelectedItem().toString()+minRecBox.getSelectedItem().toString());
                    int horaDev=Integer.parseInt(horaDevBox.getSelectedItem().toString()+minDevBox.getSelectedItem().toString());
                    System.out.println(fechaRecSelected);
                    System.out.println(horaRec);
                    System.out.println(fechaDevSelected);
                    System.out.println(horaDev);
                    System.out.println(eleccionSedeRec.getID());
                    System.out.println(eleccionSedeDev.getID());
                    System.out.println(categoria.getID());
                    Reserva reserva_u= new Reserva(Integer.parseInt(fechaRecSelected),Integer.parseInt(fechaDevSelected) ,horaRec ,horaDev ,reservaEnSede , eleccionSedeRec, eleccionSedeDev,categoria , cliente);
                    reserva_u.setVehiculoAsignado();
                    if (reserva_u.getVehiculoAsignado()!=null){
                        reserva_u.setPagoReserva(Integer.parseInt(fechaRecSelected),Integer.parseInt(fechaDevSelected) ,horaRec ,horaDev);
                        Reserva.addReserva(reserva_u);
                        VentanaMain.Dialog("Se debitaron COP "+ Double.toString(reserva_u.getPagoReserva())+".");
                        VentanaMain.Dialog("Reserva creada exitosamente, el id de su reserva es: "+Integer.toString(reserva_u.getID()));
                        try{Inventario.updateSistema();}catch(IOException e1) {e1.printStackTrace();}

                    }
                    else{

                    }


                } catch (Exception e2) {

                }
            }
        });
        tabbedPane.add("Información de recogida", panel1);
        tabbedPane.add("Información de devolucion", panel2);
        tabbedPane.add("Selección de categoría", panel3);

        return tabbedPane;
    }
    
    public static void main(String[] args) {
        Inventario.loadSistema();
        JFrame frame = new JFrame();
        frame.setSize(400,400);
        metodosReserva metodos = new metodosReserva();
        //frame.add(metodos.menuReserva());
        frame.setVisible(true);
        
    }
    
}