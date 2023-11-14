package vista;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import modelo.Categoria;
import modelo.Cliente;
import modelo.Inventario;
import modelo.Reserva;
import modelo.Sede;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.io.IOException;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;



public class metodosReserva extends JFrame {
    private JTabbedPane tabbedPane;
    JPanel panelSuperior;
    private JButton botonContinuar;
    private JButton botonContinuar2;
    
    public JTabbedPane menuReserva(Cliente cliente, boolean reservaEnSede) {

        //super("Reserva de Vehículo");
        tabbedPane = new JTabbedPane(); //Creación de la ventana
        
        // Pestaña 1: Fecha y hora de recogida y sede de recogida -----------------------------------------------------------------------------------------------------------------------------------------
        
        JPanel panel1 = new JPanel(new GridLayout(0,2));
        
        JLabel textoBienvenida = new JLabel("¡Bienvenido a nuestro sistema de Reserva! \n");
        
        botonContinuar = new JButton("Continuar");
        panel1.add(textoBienvenida);
        
        
       
        // Fecha de recogida 
        JPanel panelFechaRec = new JPanel();       
        panelFechaRec.setLayout(new GridLayout(0,1));
        
    
        panelFechaRec.add(new JLabel("Ingrese la fecha de recogida del vehículo en formato aaaammdd"));
        NumericOnlyTextField fecha1= new NumericOnlyTextField(); 
        panel1.add(panelFechaRec);
        panel1.add(fecha1);
        
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            // Hasta aquí.
        
        List<Integer> horarioAtencionRec = new ArrayList<>(); //Aquí se define el horario de atención correcto
        
        horarioAtencionRec = eleccionSedeRec.getHorarioAtencionEnSemana();

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
            tabbedPane.setSelectedIndex(1);
            }
        });

        
        //Continuar
        panel1.add(botonContinuar);
        
        // ----------
        // Pestaña 2: Fecha y hora de devolucion -----------------------------------------------------------------------------------------------------------------------------------------
        
        JPanel panel2 = new JPanel(new GridLayout(0,2));
        
        botonContinuar2 = new JButton("Continuar");

        
        // Fecha devolución
        JPanel panelFechaDev = new JPanel();       
        panelFechaDev.setLayout(new GridLayout(0,1));
        panelFechaDev.add(new JLabel("Ingrese la fecha de devolución del vehículo en formato aaaammdd"));
        NumericOnlyTextField fecha2= new NumericOnlyTextField(); 
        panel2.add(panelFechaDev);
        panel2.add(fecha2);

        
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
            // Hasta aquí.
        
        List<Integer> horarioAtencionDev = new ArrayList<>(); //Aquí se define el horario de atención correcto
        horarioAtencionDev = eleccionSedeDev.getHorarioAtencionEnSemana();

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
                tabbedPane.setSelectedIndex(2);

            }
        });
        
        //Continuar
        
        panel2.add(botonContinuar2);
        
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
                    System.out.println(categoria.getnombreCategoria());
                    System.out.println(fecha1.getText());
                    System.out.println(horaRec);
                    System.out.println(fecha2.getText());
                    System.out.println(horaDev);
                    System.out.println(eleccionSedeRec.getID());
                    System.out.println(eleccionSedeDev.getID());
                    System.out.println(categoria.getID());
                    Reserva reserva_u= new Reserva(Integer.parseInt(fecha1.getText()),Integer.parseInt(fecha2.getText()) ,horaRec ,horaDev ,reservaEnSede , eleccionSedeRec, eleccionSedeDev,categoria , cliente);
                    reserva_u.setVehiculoAsignado();
                    if (reserva_u.getVehiculoAsignado()!=null){
                        reserva_u.setPagoReserva(Integer.parseInt(fecha1.getText()),Integer.parseInt(fecha2.getText()) ,horaRec ,horaDev);
                        Reserva.addReserva(reserva_u);
                        VentanaMain.Dialog("Se debitaron COP "+ Double.toString(reserva_u.getPagoReserva())+".");
                        VentanaMain.Dialog("Reserva creada exitosamente, el id de su reserva es: "+Integer.toString(reserva_u.getID()));
                        try{Inventario.updateSistema();}catch(IOException e1) {e1.printStackTrace();}

                    }
                    else{
                        VentanaMain.errorDialog("No se encontraron vehículos disponibles para la categoría dada");

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
    
}