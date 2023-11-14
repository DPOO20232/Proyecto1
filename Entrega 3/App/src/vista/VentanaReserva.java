package vista;

import javax.swing.DefaultComboBoxModel;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.JTabbedPane;

import modelo.Categoria;
import modelo.Inventario;
import modelo.Sede;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

public class VentanaReserva extends JFrame {
    private JTabbedPane tabbedPane;
    JPanel panelSuperior;
    private JButton botonContinuar;
    
    public VentanaReserva() {

        super("Reserva de Vehículo");
        tabbedPane = new JTabbedPane(); //Creación de la ventana
        
        // Pestaña 1: Fecha y hora de recogida y sede de recogida -----------------------------------------------------------------------------------------------------------------------------------------
        
        JPanel panel1 = new JPanel(new GridLayout(0,2));
        
        JLabel nombreEmpresa = new JLabel("<NombreEmpresa> \n");
        JLabel textoBienvenida = new JLabel("¡Bienvenido a nuestro sistema de Reserva! \n");
        JLabel fechaRecTitle = new JLabel("Fecha de recogida del vehículo");
        
        botonContinuar = new JButton("Continuar");
        botonContinuar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tabbedPane.setSelectedIndex(1);
            }
        });
        
        panel1.add(nombreEmpresa);
        panel1.add(textoBienvenida);
        panel1.add(fechaRecTitle);
       
        // Fecha de recogida 
        JPanel panelFechaRec = new JPanel();       
        panelFechaRec.setLayout(new GridLayout(0,1));
        
    
        panelFechaRec.add(new JLabel("Ingrese la fecha de recogida del vehículo en formato aaaammdd"));
        NumericOnlyTextField fecha1= new NumericOnlyTextField(); 
        panel1.add(panelFechaRec);
        panel1.add(fecha1);
        
        // Hora de recogida
        JPanel panelHoraRec = new JPanel();

        String[] opcionesMinutosRec = {"00"};
        DefaultComboBoxModel<String> opcionesHoraRec = new DefaultComboBoxModel<>();
        
        JComboBox<String> horaRecBox = new JComboBox<>(opcionesHoraRec);
        JComboBox<String> minRecBox = new JComboBox<>(opcionesMinutosRec);
                
        horaRecBox.setSelectedIndex(0);
        minRecBox.setSelectedIndex(0);

        panelHoraRec.add(horaRecBox);
        panelHoraRec.add(minRecBox);
        panel1.add(panelHoraRec);

        // Sede de recogida
        JPanel panelSedeRec = new JPanel();
        JComboBox<String> sedesRec = new JComboBox<>(); // El JComboBox se coloca en el centro del panel
        for (Sede i : Inventario.getListaSedes()) {
            sedesRec.addItem(Integer.toString(i.getID()) + ": " + i.getNombre());
        }
        sedesRec.setSelectedIndex(0);
        panelSedeRec.add(sedesRec, BorderLayout.CENTER);
        panel1.add(panelSedeRec);
        
        //Continuar
        panel1.add(botonContinuar);
        
        // ----------
        // Pestaña 2: Fecha y hora de devolucion -----------------------------------------------------------------------------------------------------------------------------------------
        
        JPanel panel2 = new JPanel(new GridLayout(0,2));
        
        panel2.add(new JLabel("Fecha de recogida del vehículo"));
        botonContinuar = new JButton("Continuar");
        botonContinuar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tabbedPane.setSelectedIndex(1);
            }
        });
        
        // Fecha devolución
        JPanel panelFechaDev = new JPanel();       
        panelFechaDev.setLayout(new GridLayout(0,1));
        panelFechaDev.add(new JLabel("Ingrese la fecha de devolución del vehículo en formato aaaammdd"));
        NumericOnlyTextField fecha2= new NumericOnlyTextField(); 
        panel2.add(panelFechaDev);
        panel2.add(fecha2);

        //Hora de devolución
        JPanel panelHoraDev = new JPanel();


        String[] opcionesMinutosDev = {"00"};
        DefaultComboBoxModel<String> opcionesHoraDev = new DefaultComboBoxModel<>();
        
        JComboBox<String> horaDevBox = new JComboBox<>(opcionesHoraDev);
        JComboBox<String> minDevBox = new JComboBox<>(opcionesMinutosDev);
                
        horaDevBox.setSelectedIndex(0);
        minDevBox.setSelectedIndex(0);
        panelHoraDev.add(horaDevBox);
        panelHoraDev.add(minDevBox);
        panel2.add(panelHoraDev);

        // Sede de devolución
        JPanel panelSedeDev = new JPanel();
        JComboBox<String> sedesDev = new JComboBox<>(); // El JComboBox se coloca en el centro del panel
        for (Sede i : Inventario.getListaSedes()) {
            sedesDev.addItem(Integer.toString(i.getID()) + ": " + i.getNombre());
        }
        sedesDev.setSelectedIndex(0);
        panelSedeDev.add(sedesDev, BorderLayout.CENTER);
        panel2.add(panelSedeDev);
        
        //Continuar
        panel2.add(botonContinuar);
        
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
            categBox.addItem(Integer.toString(i.getID()) + ": " + i);
        }
        categBox.setSelectedIndex(0);
        panelCategorias.add(categBox, BorderLayout.CENTER);
        panelCategorias.add(finalizar,BorderLayout.SOUTH);
        finalizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                try {
                    Categoria categoria= Inventario.assignCategoria(Integer.parseInt(categBox.getSelectedItem().toString().split(":")[0]));
                    int horaRec=Integer.parseInt(horaRecBox.getSelectedItem().toString()+minRecBox.getSelectedItem().toString());
                    int horaDev=Integer.parseInt(horaDevBox.getSelectedItem().toString()+minDevBox.getSelectedItem().toString());
                    //System.out.println(fechaRecSelected);
                    System.out.println(horaRec);
                    //System.out.println(fechaDevSelected);
                    System.out.println(horaDev);
                    //System.out.println(eleccionSedeRec.getID());
                    //System.out.println(eleccionSedeDev.getID());
                    System.out.println(categoria.getID());
                    //Reserva reserva_u= new Reserva(Integer.parseInt(fechaRecSelected),Integer.parseInt(fechaDevSelected) ,horaRec ,horaDev ,reservaEnSede , eleccionSedeRec, eleccionSedeDev,categoria , cliente);
                    //reserva_u.setVehiculoAsignado();
                    //if (reserva_u.getVehiculoAsignado()!=null){
                    //    reserva_u.setPagoReserva(Integer.parseInt(fechaRecSelected),Integer.parseInt(fechaDevSelected) ,horaRec ,horaDev);
                    //    Reserva.addReserva(reserva_u);
                    //    VentanaMain.Dialog("Se debitaron COP "+ Double.toString(reserva_u.getPagoReserva())+".");
                    //    VentanaMain.Dialog("Reserva creada exitosamente, el id de su reserva es: "+Integer.toString(reserva_u.getID()));
                    //    try{Inventario.updateSistema();}catch(IOException e1) {e1.printStackTrace();}

                    //}
                    //else{

                    //}


                } catch (Exception e2) {

                }
            }
        });
        
        panel3.add(panelCategorias);
        tabbedPane.add("Información de recogida", panel1);
        tabbedPane.add("Información de devolucion", panel2);
        tabbedPane.add("Selección de categoría", panel3);

        add(tabbedPane);
        setLocationRelativeTo(null);
        setSize(840, 600);
        setVisible(true);
        
    }

}