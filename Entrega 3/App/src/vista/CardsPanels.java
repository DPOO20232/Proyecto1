package vista;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import modelo.Categoria;
import modelo.Cliente;
import modelo.Conductor;
import modelo.Inventario;
import modelo.Licencia;
import modelo.Sede;
import modelo.Seguro;
import modelo.Usuario;

import modelo.Reserva;

import modelo.Vehiculo;
import modelo.alquiler;
import modelo.personal;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;

public class CardsPanels {
    private  JPanel mainPanel;
    private  CardLayout cardLayout;
    private  JPanel cardPanel;
    private  String[] pasos;
    private int pasoActual = 0;
    private String inputFechaL1;
    private String inputFechaL2;
    private Reserva reserva_i;
    private Reserva copiaReserva_i;
    JComboBox<String> comboBox1;
    JComboBox<String> comboBox2;
    protected static int inputFechaFin;
    protected static int inputFechaInicio;
    protected static int inputHoraFin;
    protected static int inputHoraInicio;
    private static JComboBox <String> sede1;
    private static JComboBox <String> sede2;
    private static JComboBox <String> pasarelas;
    private Vehiculo vehiculoAnterior;
    private double diferenciaPago;
    private double pagoFinalAlquiler;
    private double montoPago;
    private int averia;

    public void editorSede(JPanel mainPanel,Sede sedeEditar) {
        this.mainPanel = mainPanel;
        this.cardLayout = new CardLayout();
        this.cardPanel = new JPanel(cardLayout);
        String [] pasosSede ={"PreguntaNombre", "InputNombre", "PreguntaUbicacion", "InputUbicacion", "Fin"};
        this.pasos=pasosSede ;
        mainPanel.add(cardPanel);
        crearPasosSede(sedeEditar);
    }
    public void editorSeguro(JPanel mainPanel, Seguro seguro) {
        this.mainPanel = mainPanel;
        this.cardLayout = new CardLayout();
        this.cardPanel = new JPanel(cardLayout);
        String [] pasosSeguro ={"PreguntaDescripcion", "InputDescripcion", "PreguntaPctg", "InputPctg","Fin"};
        this.pasos=pasosSeguro ;
        mainPanel.add(cardPanel);
        crearPasosSeguro(seguro);
    }
    public void editorPersonal(JPanel mainPanel,personal personal, JPanel panel) {
        this.mainPanel = mainPanel;
        this.cardLayout = new CardLayout();
        this.cardPanel = new JPanel(cardLayout);
        String [] pasosSeguro ={"PreguntaDescripcion", "InputDescripcion", "PreguntaDescripcion2", "InputDescripcion2","Fin"};
        this.pasos=pasosSeguro ;
        mainPanel.add(cardPanel);
        crearPasosPersonal(personal, panel);
    }
    public void editorReserva(JPanel mainPanel,Reserva reserva, Boolean boolDTO) {
        this.mainPanel = mainPanel;
        this.cardLayout = new CardLayout();
        this.cardPanel = new JPanel(cardLayout);
        String [] pasosSeguro ={"PreguntaSede", "InputSedes", "InputFechas", "InputHoras","PreguntaCategoria","InputCategoria","Fin"};
        this.pasos=pasosSeguro ;
        mainPanel.add(cardPanel);
        reserva_i=reserva;

        Reserva copiaReserva= new Reserva();
        copiaReserva_i=copiaReserva;  
        if(reserva.getVehiculoAsignado()!=null){
            vehiculoAnterior= reserva_i.getVehiculoAsignado();
            reserva_i.setVehiculoAsignado(null);
            vehiculoAnterior.eliminarReservaActiva(reserva_i.getID());
            copiaReserva_i= new Reserva(reserva_i.getID(),reserva_i.getFechaRecoger(),reserva_i.getFechaEntregar(),reserva_i.getHoraRecoger(),reserva_i.getHoraEntregar(),reserva_i.getReservaEnSede(),reserva_i.getSedeRecoger(),reserva_i.getSedeEntregar(),reserva_i.getCategoria(),reserva_i.getCliente());
            copiaReserva_i.setPagoReserva(reserva_i.getPagoReserva());
            copiaReserva_i.setVehiculoAsignado(vehiculoAnterior);
            try{Inventario.updateSistema();}catch(IOException e) {e.printStackTrace();}

            SwingUtilities.invokeLater(() -> {
            try {
                crearPasosReserva(true,boolDTO);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            });
        }
        else{
            copiaReserva_i.setReservaEnSede(reserva_i.getReservaEnSede());
            SwingUtilities.invokeLater(() -> {
            try {
                crearPasosReserva(false,boolDTO);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            });

        }

    }
    public void crearAlquiler(JPanel mainPanel,alquiler alquiler_u) throws FileNotFoundException, IOException {
        this.mainPanel = mainPanel;
        this.cardLayout = new CardLayout();
        this.cardPanel = new JPanel(cardLayout);
        String [] pasosConductor ={"PreguntaConductor", "InputConductor","InputPasarelas2","Fin"};
        this.pasos=pasosConductor ;
        mainPanel.add(cardPanel);
        crearPasosCrearAlquiler(alquiler_u);
    }

    public void completarAlquiler(JPanel mainPanel,Sede sedeEmpleado,alquiler alquiler_u) throws FileNotFoundException, IOException {
        this.mainPanel = mainPanel;
        this.cardLayout = new CardLayout();
        this.cardPanel = new JPanel(cardLayout);
        String [] pasosConductor ={"InputAverias","InputPasarelas2","Fin"};
        this.pasos=pasosConductor ;
        mainPanel.add(cardPanel);
        crearPasosCompletarAlquiler(sedeEmpleado,alquiler_u);
    }

    public void editar() {
        // Comienza con el primer paso
        mostrarPasoActual();
    }

    private void crearPasosSede(Sede sede) {
        crearPasoPregunta("PreguntaNombre", "¿Desea modificar el Nombre?", "InputNombre", "PreguntaUbicacion");
        crearPasoInput("InputNombre", "Nombre", "PreguntaUbicacion",sede);
        crearPasoPregunta("PreguntaUbicacion", "¿Desea modificar la Ubicación?", "InputUbicacion", "PreguntaHora1");
        crearPasoInput("InputUbicacion", "Ubicación", "PreguntaHora1",sede);
        crearPasoPregunta("PreguntaHora1", "¿Desea modificar el horario entre semana?", "InputHora1", "PreguntaHora2");
        crearPasoHorario("InputHora1", "Horario entre semana(hhmm)", "PreguntaHora2",sede);
        crearPasoPregunta("PreguntaHora2", "¿Desea modificar el horario para fin de semana?", "InputHora2", "Fin");
        crearPasoHorario("InputHora2", "Horario para fin de semana (hhmm)", "Fin",sede);
        crearPasoFin("Fin");
    }

    private void crearPasosSeguro(Seguro seguro) {
        crearPasoPregunta("PreguntaDescripcion", "¿Desea modificar la descripción del seguro?", "InputDescripcion", "PreguntaPctg");
        crearPasoInput("InputDescripcion", "Descripción", "PreguntaPctg",seguro);
        crearPasoPregunta("PreguntaPctg", "¿Desea modificar el porcentaje de la tarifa diaria a cobrar?", "InputPctg", "Fin");
        crearPasoDecimales("InputPctg", "Porcentaje de la tarifa diaria a cobrar", "Fin",seguro);
        crearPasoFin("Fin");
    }
    private void crearPasosPersonal(personal personal, JPanel panel) {
        crearPasoPregunta("PreguntaPassword", "¿Desea modificar la clave del empleado?", "InputPassword", "PreguntaSede");
        crearPasoInput("InputPassword", "Ingrese la nueva contraseña", "PreguntaSede",personal);
        crearPasoPregunta("PreguntaSede", "¿Desea modificar la sede?", "InputSede", "Fin");
        crearPasoInput("InputSede", "Ingrese el ID de la sede", "Fin",personal);
        crearPasoFin2("Fin", panel);
    }
    private void crearPasosCrearAlquiler(alquiler alquiler_u) throws FileNotFoundException, IOException {
        crearPasoPregunta("PreguntaConductor", "¿Desea agregar un conductor?", "InputConductor", "InputPasarelas2");
        crearLicencia(new JPanel(),"InputConductor",alquiler_u,"PreguntaConductor");
        crearPasoPasarelas2("InputPasarelas2","Fin","Pago de alquiler inicial",alquiler_u,null);
        crearPasoFin("Fin");
    }

    private void crearPasosCompletarAlquiler(Sede sede,alquiler alquiler_u) throws FileNotFoundException, IOException {
        crearPasoAveria("InputAverias", "InputPasarelas2",sede,alquiler_u);
        crearPasoPasarelas2("InputPasarelas2","Fin","Pago de alquiler final",alquiler_u,sede);
        crearPasoFin("Fin");
    }

    private void crearPasosReserva(boolean esModificacion, boolean boolDTO) throws FileNotFoundException, IOException{
        if (esModificacion){
            crearPasoSede("InputSedes", "sedes para la reserva","InputFechas",reserva_i);
            crearPasoFecha("InputFechas","Fechas para la reserva","InputHoras",reserva_i);
            crearPasoHora("InputHoras", "Horarios para la reserva", "InputCategoria",reserva_i);
            crearPasoCategoria("InputCategoria", "categorias","InputPasarelas", "Fin", reserva_i,copiaReserva_i,boolDTO);
            crearPasoPasarelas("InputPasarelas","modificación de Reserva","Fin",boolDTO,esModificacion);
            crearPasoFin("Fin");

        }
        else{
            crearPasoSede("InputSedes", "sedes para la reserva","InputFechas",reserva_i);
            crearPasoFecha("InputFechas","Fechas para la reserva","InputHoras",reserva_i);
            crearPasoHora("InputHoras", "Horarios para la reserva", "InputCategoria",reserva_i);
            crearPasoCategoria("InputCategoria", "categorias","InputPasarelas", "Fin", reserva_i,copiaReserva_i,boolDTO);
            crearPasoPasarelas("InputPasarelas","Reserva","Fin",boolDTO,esModificacion);
            crearPasoFin("Fin");
        }

    }

    private void crearPasoAveria(String nombreCampo,String pasoKey,Sede sede_personal,alquiler alquiler_u){
        int fechaActual= Integer.parseInt(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")));
        LocalTime hora = LocalTime.now();
        int horaActual = hora.getHour() * 100 + hora.getMinute();
        alquiler_u.getReserva().setSedeEntregar(sede_personal);
        alquiler_u.getReserva().setFechaEntregar(fechaActual);
        alquiler_u.getReserva().setHoraEntregar(horaActual);
        JPanel panel_1= new JPanel();
        panel_1.add(new JLabel("Seleccione los daños que tenga el vehículo"));
        panel_1.add(Box.createRigidArea(new Dimension(0,100)));
        ButtonGroup averias= new ButtonGroup();
        JRadioButton cero= new JRadioButton("0. Ningún daño ");
        JRadioButton uno= new JRadioButton("1. Daño Leve ");
        JRadioButton dos= new JRadioButton("2. Daño Moderado ");                        
        JRadioButton tres= new JRadioButton("3. Daño Grave ");
        cero.setActionCommand("0");
        uno.setActionCommand("1");
        dos.setActionCommand("2");
        tres.setActionCommand("3");
        averias.add(cero);
        averias.add(uno);
        averias.add(dos);
        averias.add(tres);
        panel_1.add(cero);
        panel_1.add(uno);
        panel_1.add(dos);
        panel_1.add(tres);
        panel_1.repaint();
        JButton avanzar2= new JButton("Concluir alquiler");
        panel_1.add(avanzar2);
        panel_1.repaint();
        avanzar2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                ButtonModel selectedButtonModel = averias.getSelection();
                    if (selectedButtonModel != null) {
                        String opcionElegida = selectedButtonModel.getActionCommand();
                        averia= Integer.parseInt(opcionElegida);
                        avanzarAlSiguientePaso(pasoKey);
                    }}});
        cardPanel.add(panel_1,nombreCampo);

    }

    private void crearPasoPregunta(String preguntaKey, String pregunta, String siguientePasoKeySi, String siguientePasoKeyNo) {
        JPanel panel = new JPanel();
        JLabel label = new JLabel(pregunta);
        JButton siButton = new JButton("Sí");
        JButton noButton = new JButton("No");

        panel.setLayout(new FlowLayout());
        panel.add(label);
        panel.add(siButton);
        panel.add(noButton);

        siButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Si el usuario selecciona "Sí", avanza al siguiente paso "si"
                avanzarAlSiguientePaso(siguientePasoKeySi);
            }
        });

        noButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Si el usuario selecciona "No", avanza al siguiente paso "no"
                avanzarAlSiguientePaso(siguientePasoKeyNo);
            }
        });

        cardPanel.add(panel, preguntaKey);
    }
    private void crearPasoSede(String preguntaKey, String nombreCampo, String siguientePasoKey, Reserva reserva){
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Nuevo " + nombreCampo + ":");
        panel.add(label);
        JButton siButton = new JButton("Avanzar");
        panel.setLayout(new FlowLayout());
        panel.add(new JLabel("Sede entrega:"));
        JComboBox <String>sedeEntrega= new JComboBox<String>();
        sedeEntrega = new JComboBox<>();
        for (Sede i: Inventario.getListaSedes()){
            sedeEntrega.addItem(Integer.toString(i.getID())+": "+i.getNombre());
                    }
        sedeEntrega.setSelectedIndex(0);
        sedeEntrega.setPreferredSize(new Dimension(200, 30));
        panel.add(sedeEntrega);
       
        panel.add(new JLabel("Sede devolucion:"));
        JComboBox <String>sedeDevolucion= new JComboBox<String>();
        sedeDevolucion= new JComboBox<>();
            for (Sede i: Inventario.getListaSedes()){
                sedeDevolucion.addItem(Integer.toString(i.getID())+": "+i.getNombre());
                }
        sedeDevolucion.setSelectedIndex(0);
        sedeDevolucion.setPreferredSize(new Dimension(200, 30));
        panel.add(sedeDevolucion);
        panel.add(siButton);
        sede1=sedeEntrega;
        sede2=sedeDevolucion;
               
        siButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sedeEntregaS= sede1.getSelectedItem().toString().split(":")[0];
                String sedeDevolucionS = sede2.getSelectedItem().toString().split(":")[0];
                Sede nuevaSedeEntrega=Inventario.assignSede(Integer.parseInt(sedeEntregaS));
                reserva_i.setSedeRecoger(nuevaSedeEntrega);

                Sede nuevaSedeDevolucion=Inventario.assignSede(Integer.parseInt(sedeDevolucionS));
                reserva_i.setSedeEntregar(nuevaSedeDevolucion);
                avanzarAlSiguientePaso(siguientePasoKey);
            }
        });
        cardPanel.add(panel, preguntaKey);
    }
    private void crearPasoCategoria(String pasoKey, String nombreCampo, String siguientePasoKeySi,String siguientePasoKeyNo, Reserva reserva,Reserva copiaReserva,boolean dto){
        
        JPanel panel = new JPanel();
        JLabel label = new JLabel(nombreCampo);
        panel.add(label);
        JButton siButton = new JButton("Avanzar");
        panel.setLayout(new FlowLayout());
        panel.add(siButton);
   
        JComboBox<String> categBox = new JComboBox<>(); // El JComboBox se coloca en el centro del panel
        for (Categoria i : Inventario.getListaCategorias()) {
            categBox.addItem(Integer.toString(i.getID()) + ": " + i.getnombreCategoria());
        }
        panel.add(categBox);
        siButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            Categoria categoria = Inventario.assignCategoria(Integer.parseInt(categBox.getSelectedItem().toString().split(":")[0]));
            reserva_i.setCategoria(categoria);
            reserva_i.setVehiculoAsignado();
            if(reserva.getVehiculoAsignado()==null){
                if (copiaReserva_i.getVehiculoAsignado()!=null){
                    VentanaMain.errorDialog("No se logró asignar un nuevo vehículo, los cambios no se guardaron");
                    reserva_i=new Reserva(copiaReserva_i.getID(),copiaReserva_i.getFechaRecoger(),copiaReserva_i.getFechaEntregar(),copiaReserva_i.getHoraRecoger(),copiaReserva_i.getHoraEntregar(),copiaReserva_i.getReservaEnSede(),copiaReserva_i.getSedeRecoger(),copiaReserva_i.getSedeEntregar(),copiaReserva_i.getCategoria(),copiaReserva_i.getCliente());
                    reserva_i.setVehiculoAsignado(copiaReserva_i.getVehiculoAsignado());
                    vehiculoAnterior.addReservaActiva(reserva_i);
                    Reserva.addReserva(reserva_i);

                    }
                    else{
                        VentanaMain.errorDialog("No se encontraron vehículos disponibles para la configuración de reserva dada");
                    }
                    avanzarAlSiguientePaso(siguientePasoKeyNo);

            }
            else{
                    double pagoAnterior=0;
                    double nuevoPago=0;
                    try{
                        pagoAnterior=reserva_i.getPagoReserva();
                    }
                    catch (NullPointerException e2){}
                    reserva_i.setPagoReserva(reserva_i.getFechaRecoger(),reserva_i.getFechaEntregar(),reserva_i.getFechaEntregar(),reserva_i.getHoraEntregar(),dto);                 
                    nuevoPago=reserva_i.getPagoReserva();
                    double pagoTotal= nuevoPago-pagoAnterior;
                    if (pagoTotal>0){
                        diferenciaPago=pagoTotal;
                        avanzarAlSiguientePaso(siguientePasoKeySi);
                    }
                    else{
                    
                    VentanaMain.Dialog("Pronto se retornaran COP"+(pagoTotal*-1)+ " a su método de cuenta.");
                    avanzarAlSiguientePaso(siguientePasoKeyNo);
                    }
            }
        }});          
        cardPanel.add(panel, pasoKey);

        
    }
    private void crearPasoFecha(String pasoKey, String nombreCampo, String siguientePasoKey, Reserva reserva){
        JPanel panel = new JPanel(new GridLayout(0, 2));
        JLabel label = new JLabel(nombreCampo);
        panel.add(label);
        JSpinner fechaInicioSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner fechaFinSpinner = new JSpinner(new SpinnerDateModel());

        // Establecer el formato de fecha para los Spinners
        JSpinner.DateEditor dateEditorInicio = new JSpinner.DateEditor(fechaInicioSpinner, "dd/MM/yyyy");
        JSpinner.DateEditor dateEditorFin = new JSpinner.DateEditor(fechaFinSpinner, "dd/MM/yyyy");
        fechaInicioSpinner.setEditor(dateEditorInicio);
        fechaFinSpinner.setEditor(dateEditorFin);

        // Crear un panel para mostrar los Spinners
        JPanel panelFechas = new JPanel(new GridLayout(3, 2));
        panelFechas.add(new JLabel("Fecha de Inicio:"));
        panelFechas.add(fechaInicioSpinner);
        panelFechas.add(new JLabel("Fecha de Fin:"));
        panelFechas.add(fechaFinSpinner);

        // Crear el botón para continuar
        JButton continuarButton = new JButton("Avanzar");
        panelFechas.add(continuarButton);
        continuarButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                Date fechaInicio = (Date) fechaInicioSpinner.getValue();
                Date fechaFin = (Date) fechaFinSpinner.getValue();

                // Validar las fechas ingresadas
                int fechaActual= Integer.parseInt(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")));
                SimpleDateFormat formato = new SimpleDateFormat("yyyyMMdd");
                inputFechaInicio = Integer.parseInt(formato.format(fechaInicio));
                inputFechaFin = Integer.parseInt(formato.format(fechaFin));
                 if (inputFechaInicio<inputFechaFin&&inputFechaInicio>=fechaActual){
                        reserva.setFechaRecoger(inputFechaInicio);
                        reserva.setFechaEntregar(inputFechaFin);
                        avanzarAlSiguientePaso(siguientePasoKey);

                    }   
                else {
                    JOptionPane.showMessageDialog(null, "La fecha de inicio debe ser anterior a la fecha de fin y mayor o igual a la fecha actual.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            
        });
        panel.add(panelFechas);
        cardPanel.add(panel, pasoKey);
    }
    private static String[] obtenerOpcionesHoras(Sede sede,int fecha) {
        int inicio =0;
        int fin=24;
        String[] opcionesHoras = new String[fin-inicio];
        for (int i = inicio; i < fin; i++) {
            opcionesHoras[i-inicio] = String.format("%02d", i);
        }
        return opcionesHoras;
    }
    private void crearPasoHora(String pasoKey, String nombreCampo, String siguientePasoKey, Reserva reserva){
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Nuevo " + nombreCampo + ":");
        panel.add(label);
        JComboBox<String> horaFinComboBox = new JComboBox<>(obtenerOpcionesHoras(null,0));
        JComboBox<String> horaInicioComboBox = new JComboBox<>(obtenerOpcionesHoras(null,0));
        comboBox1=horaInicioComboBox;
        comboBox2=horaFinComboBox;
        // Crear Spinners para los minutos de inicio y fin
        DefaultComboBoxModel<String> opcionesmin1 = new DefaultComboBoxModel<>();
        DefaultComboBoxModel<String> opcionesmin2 = new DefaultComboBoxModel<>();
        
        for (int i = 0; i <= 59; i++) {
            String s="";
            if (i<10){
                s=s+"0";
            }
            opcionesmin1.addElement(s+Integer.toString(i));
            opcionesmin2.addElement(s+Integer.toString(i));
        }
        JComboBox<String> minComboBox1 = new JComboBox<>(opcionesmin1);
        JComboBox<String> minComboBox2 = new JComboBox<>(opcionesmin2);

        // Crear un panel para mostrar los componentes
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);  // Espaciado entre componentes

        panel.add(new JLabel("Hora de Inicio:"), gbc);

        gbc.gridx = 1;
        panel.add(horaInicioComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;

        panel.add(new JLabel("Minutos de Inicio:"), gbc);

        gbc.gridx = 1;
        panel.add(minComboBox1, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;

        panel.add(new JLabel("Hora de Fin:"), gbc);
        gbc.gridx = 1;
        panel.add(horaFinComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("Minutos de Fin:"), gbc);
        gbc.gridx = 1;
        panel.add(minComboBox2, gbc);

        // Crear el botón para continuar
        JButton continuarButton = new JButton("Continuar");
        
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;  // Ocupar las dos columnas
        gbc.anchor = GridBagConstraints.PAGE_END;  // Colocar al final
        panel.add(continuarButton, gbc);
        continuarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener las horas y minutos seleccionados
                String horaInicio = comboBox1.getSelectedItem().toString();
                String minutosInicio = minComboBox1.getSelectedItem().toString();
                String horaFin = comboBox2.getSelectedItem().toString();
                String minutosFin = minComboBox2.getSelectedItem().toString();
                inputHoraInicio = Integer.parseInt(horaInicio + minutosInicio);
                inputHoraFin = Integer.parseInt(horaFin + minutosFin);
                boolean posibleRecoger=reserva_i.getSedeRecoger().estaAbierta(reserva_i.getFechaRecoger(),inputHoraInicio);
                boolean posibleEntregar=reserva_i.getSedeEntregar().estaAbierta(reserva_i.getFechaEntregar(),inputHoraFin);
                if (posibleEntregar&&posibleRecoger){
                reserva_i.setHoraRecoger(inputHoraInicio);
                reserva_i.setHoraEntregar(inputHoraFin);
                avanzarAlSiguientePaso(siguientePasoKey);
                }
                else{
                    DialogHora(reserva_i.getFechaRecoger(),reserva_i.getFechaEntregar(), reserva_i.getSedeRecoger(),reserva_i.getSedeEntregar());
                }
            }
        });
        cardPanel.add(panel, pasoKey);


    }
    private void crearPasoInput(String pasoKey, String nombreCampo, String siguientePasoKey, Object O) {
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Nuevo " + nombreCampo + ":");
        JTextField textField = new JTextField(20);
        JButton avanzar = new JButton("Avanzar");

        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.add(label);
        panel.add(textField);
        panel.add(avanzar);
        avanzar.setVisible(false);
        DocumentListener documentListener = new DocumentListener() {
        @Override
            public void insertUpdate(DocumentEvent e) {
                avanzar.setVisible(!textField.getText().trim().isEmpty());
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                avanzar.setVisible(!textField.getText().trim().isEmpty());
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                avanzar.setVisible(!textField.getText().trim().isEmpty());
            }
        };
        textField.getDocument().addDocumentListener(documentListener);
        avanzar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Realiza la operación correspondiente con el nuevo valor
                if (O instanceof Sede){
                    Sede sede= (Sede) O;
                    if (nombreCampo.contains("Nombre")){
                        sede.setNombre(textField.getText());
                    }
                    else if (nombreCampo.contains("Ubicación")){
                        sede.setUbicacion(textField.getText());
                    }
                }
                else if (O instanceof Seguro){
                    Seguro seguro= (Seguro) O;
                    seguro.setDescripcion(textField.getText());
                }
                else if (O instanceof personal){
                    personal personal= (personal) O;
                    if (pasoKey.equals("InputPassword")){
                        personal.setPassword(textField.getText());
                    }
                    
                    else
                    {
                        try{
                            int num= Integer.parseInt(textField.getText());
                            if (num>0 && num<Inventario.getListaSedes().size()){
                                Sede sedeAntigua=personal.getSede();
                                sedeAntigua.getPersonalSede().remove(personal);
                                personal.setSede(Inventario.assignSede(num));

                            }
                            else{
                                VentanaMain.errorDialog("Elija un id de sede válido");
                            }

                        }
                        catch(NumberFormatException f){

                        }
                    }
                    
                }
                avanzarAlSiguientePaso(siguientePasoKey);
            }
        });
        cardPanel.add(panel, pasoKey);
    }
    private void crearPasoHorario(String pasoKey, String nombreCampo, String siguientePasoKey, Object O) {
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Nuevo " + nombreCampo + ":");
        JLabel labela = new JLabel("\t");

        JLabel label1 = new JLabel("Hora inicio ");
        JLabel label2 = new JLabel("Hora fin ");
        JPanel horaPanel1 = new JPanel();
        JPanel horaPanel2 = new JPanel();

        DefaultComboBoxModel<String> opcionesHora1 = new DefaultComboBoxModel<>();
        DefaultComboBoxModel<String> opcionesHora2 = new DefaultComboBoxModel<>();
        int inicio1=0;
        int fin1=23;
        int inicio2=0;
        int fin2=23;
        if (O instanceof Reserva){
            Reserva reserva= (Reserva) O;
            int fechafin= reserva.getFechaEntregar();
            int fechainicio = reserva.getFechaRecoger();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            LocalDate localDatefin = LocalDate.parse(Integer.toString(fechafin), formatter);
            LocalDate localDateinicio = LocalDate.parse(Integer.toString(fechainicio), formatter);
            DayOfWeek dayOfWeekfin = localDatefin.getDayOfWeek();
            DayOfWeek dayofWeekinicio = localDateinicio.getDayOfWeek();
            boolean esDiaLaboralfin = (dayOfWeekfin != DayOfWeek.SATURDAY && dayOfWeekfin != DayOfWeek.SUNDAY);
            boolean esDiaLaboralinicio = (dayofWeekinicio != DayOfWeek.SATURDAY && dayofWeekinicio != DayOfWeek.SUNDAY);
            if (esDiaLaboralfin){
                inicio1=reserva.getSedeEntregar().getHorarioAtencionEnSemana().get(0)/100;
                fin1=reserva.getSedeEntregar().getHorarioAtencionEnSemana().get(1)/100;
            }
            else{
                inicio1=reserva.getSedeEntregar().getHorarioAtencionFinSemana().get(0)/100;
                fin1=reserva.getSedeEntregar().getHorarioAtencionFinSemana().get(1)/100;
            }
            if (esDiaLaboralinicio){
                inicio2=reserva.getSedeRecoger().getHorarioAtencionEnSemana().get(0)/100;
                fin2=reserva.getSedeRecoger().getHorarioAtencionEnSemana().get(1)/100;
            }
            else{
                inicio2=reserva.getSedeRecoger().getHorarioAtencionFinSemana().get(0)/100;
                fin2=reserva.getSedeRecoger().getHorarioAtencionFinSemana().get(1)/100;
            }

        }

        for (int i = inicio1; i <= fin1; i++) {
            String s="";
            if (i<10){
                s=s+"0";
            }
            opcionesHora1.addElement(s+Integer.toString(i));
        }
        for (int i = inicio2; i <= fin2; i++) {
            String s="";
            if (i<10){
                s=s+"0";
            }
            opcionesHora2.addElement(s+Integer.toString(i));
        }
        JComboBox<String> horaComboBox1 = new JComboBox<>(opcionesHora1);
        JComboBox<String> horaComboBox2 = new JComboBox<>(opcionesHora2);
    
        DefaultComboBoxModel<String> opcionesMinutos1 = new DefaultComboBoxModel<>();
        opcionesMinutos1.addElement("00");
        DefaultComboBoxModel<String> opcionesMinutos2 = new DefaultComboBoxModel<>();
        opcionesMinutos2.addElement("00");

        JComboBox<String> minutosComboBox1 = new JComboBox<>(opcionesMinutos1);
        JComboBox<String> minutosComboBox2 = new JComboBox<>(opcionesMinutos2);
        opcionesHora1.setSelectedItem("01");
        opcionesHora2.setSelectedItem("01");
        opcionesMinutos1.setSelectedItem("00");
        opcionesMinutos2.setSelectedItem("00");

        JButton avanzarButton = new JButton("Avanzar");
    
        horaPanel1.add(horaComboBox1);
        horaPanel1.add(new JLabel(":"));
        horaPanel1.add(minutosComboBox1);
        horaPanel2.add(horaComboBox2);
        horaPanel2.add(new JLabel(":"));
        horaPanel2.add(minutosComboBox2);
    
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.add(label);
        panel.add(labela);
        panel.add(label1);
        panel.add(horaPanel1);
        panel.add(label2);
        panel.add(horaPanel2);
        panel.add(avanzarButton);
    
        avanzarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String hora1 = horaComboBox1.getSelectedItem().toString();
                String minutos1 =minutosComboBox1.getSelectedItem().toString();
                String hora2 = horaComboBox2.getSelectedItem().toString();
                String minutos2 = minutosComboBox2.getSelectedItem().toString();
                String inicio=(hora1+minutos1);
                String fin=(hora2+minutos2);
                if(Integer.parseInt(fin)> Integer.parseInt(inicio)){

                    if (O instanceof Sede){
                        Sede sede= (Sede) O;
                        List <Integer> horario= new ArrayList<Integer>();
                        horario.add(Integer.parseInt(inicio));
                        horario.add(Integer.parseInt(fin));
                        if (nombreCampo.contains("entre")){
                            sede.setHorarioAtencionEnSemana(horario);
                        }
                        else if (nombreCampo.contains("fin")){
                            sede.setHorarioAtencionFinSemana(horario);
                        }
                    }
                    else if (O instanceof Reserva){
                        Reserva reserva= (Reserva) O;
                        reserva.setHoraRecoger(Integer.parseInt(inicio));
                        reserva.setHoraEntregar(Integer.parseInt(fin));
                    }
                avanzarAlSiguientePaso(siguientePasoKey);
                }
                else{
                    VentanaMain.errorDialog("Verifique que la fecha/periodo inicial sea previa a la fecha/periodo final.");
                }  
            
        }});
    
        cardPanel.add(panel, pasoKey);
    }
    
    private void crearPasoDecimales(String pasoKey, String nombreCampo, String siguientePasoKey,Object O) {
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Nuevo " + nombreCampo + ":");
        
        DefaultComboBoxModel<Double> opcionesDecimales = new DefaultComboBoxModel<>();
        for (double i = 0.1; i <= 2.0; i += 0.1) {
            double numeroRedondeado = Math.round(i * 10.0) / 10.0;
            opcionesDecimales.addElement(numeroRedondeado);
        }
        
        JComboBox<Double> decimalesComboBox = new JComboBox<>(opcionesDecimales);
        JButton avanzarButton = new JButton("Avanzar");
        avanzarButton.setEnabled(false); // Inicialmente, deshabilitar el botón "Avanzar"
    
        // Agregar un ItemListener para habilitar el botón cuando se seleccionen valores en el JComboBox
        decimalesComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (decimalesComboBox.getSelectedIndex() != -1) {
                    avanzarButton.setEnabled(true);
                } else {
                    avanzarButton.setEnabled(false);
                }
            }
        });
    
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.add(label);
        panel.add(decimalesComboBox);
        panel.add(avanzarButton);
    
        avanzarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (O instanceof Seguro){
                    Seguro seguro = (Seguro) O;
                    seguro.setPctg_TarifaDiaria(Double.parseDouble(decimalesComboBox.getSelectedItem().toString()));
                }

                avanzarAlSiguientePaso(siguientePasoKey);
            }
        });
    
        cardPanel.add(panel, pasoKey);
    }
    
    private void crearPasoFin(String pasoKey) {
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Cambios registrados");
        JLabel label1 = new JLabel("\t\t\t\t");

        JLabel label2 = new JLabel("Los cambios estarán disponibles tras cerrar sesión");

        JButton finalizarButton = new JButton("Finalizar");

        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.add(label);
        panel.add(label1);
        panel.add(label2);

        panel.add(finalizarButton);

        finalizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cerrar el frame y dejarlo vacío
                mainPanel.removeAll();
                mainPanel.revalidate();
                mainPanel.repaint();
                try{Inventario.updateSistema();}catch(IOException e1) {e1.printStackTrace();}
            }
        });

        cardPanel.add(panel, pasoKey);
    }
      private void crearPasoFin2(String pasoKey, JPanel panelActualizarPersonal) {
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Cambios registrados");
        JLabel label1 = new JLabel("\t\t\t\t");

        JLabel label2 = new JLabel("Los cambios estarán disponibles tras cerrar sesión");

        JButton finalizarButton = new JButton("Finalizar");

        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.add(label);
        panel.add(label1);
        panel.add(label2);

        panel.add(finalizarButton);

        finalizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cerrar el frame y dejarlo vacío
                // Oculta el panel actual
                cardPanel.removeAll();
                // Muestra el panel inicial
                cardPanel.add(panelActualizarPersonal, "PreguntaPassword");
                cardLayout.show(cardPanel, "PreguntaPassword");
            }
        });

        cardPanel.add(panel, pasoKey);
    }
       
    private void avanzarAlSiguientePaso(String siguientePasoKey) {
        cardLayout.show(cardPanel, siguientePasoKey);
    }

    private void mostrarPasoActual() {
        cardLayout.show(cardPanel, pasos[pasoActual]);
    }
    static String extraerInformacion(String tipo, String informacion) {
        // Buscamos la posición del tipo en el string
        int startIndex = informacion.indexOf(tipo);

        // Si no se encuentra, retornamos una cadena vacía
        if (startIndex == -1) {
            return "";
        }

        // Avanzamos hasta el primer carácter después de los dos puntos
        startIndex = startIndex + tipo.length() + 2;

        // Buscamos la posición del punto
        int endIndex = informacion.indexOf(";", startIndex);

        // Si no se encuentra el punto, retornamos la cadena desde startIndex hasta el final
        if (endIndex == -1) {
            return informacion.substring(startIndex);
        }

        // Retornamos la subcadena desde startIndex hasta endIndex
        return informacion.substring(startIndex, endIndex);
    }
    public void DialogHora(int fechaI, int fechaF, Sede sedeI, Sede sedeF){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate localDate1 = LocalDate.parse( String.valueOf(fechaI), formatter);
        LocalDate localDate2 = LocalDate.parse( String.valueOf(fechaF), formatter);

        DayOfWeek dayOfWeek1 = localDate1.getDayOfWeek();
        DayOfWeek dayOfWeek2 = localDate2.getDayOfWeek();

        boolean esDiaLaboralfin1 = (dayOfWeek1 != DayOfWeek.SATURDAY && dayOfWeek1 != DayOfWeek.SUNDAY);
        boolean esDiaLaboralfin2 = (dayOfWeek2 != DayOfWeek.SATURDAY && dayOfWeek2 != DayOfWeek.SUNDAY);
        String inicio1="";
        String inicio2="";
        String fin1="";
        String fin2="";
        if (esDiaLaboralfin1){
            String  inicio= String.format("%04d",sedeI.getHorarioAtencionEnSemana().get(0)/100);
            String  fin= String.format("%04d",sedeI.getHorarioAtencionEnSemana().get(1)/100);
            inicio1= inicio.substring(inicio.length()-2)+":"+inicio.substring(0,inicio.length()-2);
            fin1= fin.substring(fin.length()-2)+":"+fin.substring(0,fin.length()-2);
        }
        else{

            String  inicio= String.format("%04d",sedeI.getHorarioAtencionFinSemana().get(0)/100);
            String  fin= String.format("%04d",sedeI.getHorarioAtencionFinSemana().get(1)/100);
            inicio1= inicio.substring(inicio.length()-2)+":"+inicio.substring(0,inicio.length()-2);
            fin1=    fin.substring(fin.length()-2)+":"+fin.substring(0,fin.length()-2);
        }
        if (esDiaLaboralfin2){
            String  inicio= String.format("%04d",sedeF.getHorarioAtencionEnSemana().get(0)/100);
            String  fin= String.format("%04d",sedeF.getHorarioAtencionEnSemana().get(1)/100);
            inicio2= inicio.substring(inicio.length()-2)+":"+inicio.substring(0,inicio.length()-2);
            fin2= fin.substring(fin.length()-2)+":"+fin.substring(0,fin.length()-2);
        }
        else{

            String  inicio= String.format("%04d",sedeF.getHorarioAtencionFinSemana().get(0)/100);
            String  fin= String.format("%04d",sedeF.getHorarioAtencionFinSemana().get(1)/100);
            inicio2= inicio.substring(inicio.length()-2)+":"+inicio.substring(0,inicio.length()-2);
            fin2= fin.substring(fin.length()-2)+":"+fin.substring(0,fin.length()-2);
        }
        String horarios1="Horario disponible para recogida vehículo: "+inicio1+" -> "+fin1;
        String horarios2="Horario disponible para devolución vehículo: "+inicio2+" -> "+fin2;
        
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        // Agregar etiquetas al panel
        JLabel label1 = new JLabel(horarios1);
        JLabel label2 = new JLabel(horarios2);
        panel.add(label1, gbc);
        
        gbc.gridy++;
        panel.add(label2, gbc);
        JDialog dialog= new JDialog();
        // Agregar el panel al diálogo
        dialog.add(panel);
        
        // Ajustes adicionales del diálogo
        dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
        
    }



    


    public void crearLicencia(JPanel panel,String pasoKey,alquiler alquiler_u,String siguientePasoKey) {
        String a="";
        String b="";
        inputFechaL1=a;
        inputFechaL2=b;
        JPanel panelC = new JPanel(new GridLayout(0,1));
        JPanel panelLicencia = new JPanel(new GridLayout(0, 2));
        JLabel labelMensaje = new JLabel("Ahora, ingrese la información de su Licencia");
        JLabel label1 = new JLabel("Nombre:");
        JTextField nombre = new JTextField(20);
        JLabel label2 = new JLabel("Cédula:");
        NumericOnlyTextField cedula= new NumericOnlyTextField();
        JLabel labelNumeroL = new JLabel("Número de Licencia: ");
        NumericOnlyTextField campoNumeroL = new NumericOnlyTextField();
        JLabel labelPais = new JLabel("País de Expedición: ");
        PlaceHolderTextField campoPais = new PlaceHolderTextField("Ej: Colombia");
        JLabel labelFechaE = new JLabel("Fecha de Expedición: ");
        JLabel labelFechaV = new JLabel("Fecha de Vencimiento: ");

        JPanel panelFecha1= new JPanel();
        panelFecha1.setLayout(new GridLayout(1,0));
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
            panelC.repaint();
            String anio=anioBox.getSelectedItem().toString();
            VentanaMain.refresh(panelFecha1);
            panelFecha1.add(anioBox);
            anioBox.setEnabled(false);
            DateComboBoxPanel date1= new DateComboBoxPanel(Integer.parseInt(anio));
            date1.setDefaulDayComboBox();
            date1.setDefaultMonthComboBox();
            panelFecha1.add(date1);
            panelFecha1.repaint();
            JButton updateDatebutton= new JButton("Cambiar Fecha");
            panelFecha1.add(updateDatebutton);
            JButton saveDatebutton= new JButton("Guardar Fecha");
            panelFecha1.add(saveDatebutton);
            inputFechaL1="";

            saveDatebutton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e){
                    if (!date1.getText().trim().isEmpty()) {
                        inputFechaL1 = anio + date1.getText();
                    }
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

        JPanel panelFecha2= new JPanel();
        panelFecha2.setLayout(new GridLayout(1,0));
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
            panelC.repaint();
            String anio=anioBox2.getSelectedItem().toString();
            VentanaMain.refresh(panelFecha2);
            panelFecha2.add(anioBox2);
            anioBox2.setEnabled(false);
            DateComboBoxPanel date2= new DateComboBoxPanel(Integer.parseInt(anio));
            date2.setDefaulDayComboBox();
            date2.setDefaultMonthComboBox();
            panelFecha2.add(date2);
            panelFecha2.repaint();
            JButton updateDatebutton= new JButton("Cambiar Fecha");
            panelFecha2.add(updateDatebutton);
            JButton saveDatebutton= new JButton("Guardar Fecha");
            panelFecha2.add(saveDatebutton);
            inputFechaL2="";
            saveDatebutton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e){
                    if (!date2.getText().trim().isEmpty()) {
                        inputFechaL2 = anio + date2.getText();
                    }
                    VentanaMain.refresh(panelFecha2);
                    panelFecha2.add(anioBox2);
                    panelFecha2.add(updateDatebutton);
                } 
            });
            updateDatebutton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e){
                    inputFechaL2="";
                    VentanaMain.refresh(panelFecha2);
                    panelFecha2.add(anioBox2);
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
        JPanel panel1= new JPanel(new GridLayout(0,2));
        panel1.add(label1);
        panel1.add(nombre);
        panel1.add(label2);
        panel1.add(cedula);
        panelC.add(labelMensaje);
        panelC.add(panel1);
        panelC.add(Box.createRigidArea(new Dimension(0,20)));
        panelC.add(panelLicencia);
        panelC.add(botonGuardar);

        botonGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                try{
                int numerolicencia = Integer.parseInt(campoNumeroL.getText());
                String pais = campoPais.getText();
                int expedicionL = Integer.parseInt(inputFechaL1);
                int vencimientoL = Integer.parseInt(inputFechaL2);
                Calendar fechaActual = Calendar.getInstance();
                int dia = fechaActual.get(Calendar.DAY_OF_MONTH);
                int mes = fechaActual.get(Calendar.MONTH) + 1;
                int anio = fechaActual.get(Calendar.YEAR);
                String fechaString = String.format("%04d%02d%02d", anio, mes, dia);
                int fechaActualInt= Integer.parseInt(fechaString);

                int fechaFinAlquiler=alquiler_u.getReserva().getFechaEntregar();

                if (expedicionL<vencimientoL&&vencimientoL>fechaActualInt&&vencimientoL>=fechaFinAlquiler){
                    if(!nombre.getText().trim().equals("")&&!cedula.getText().trim().equals("")){
                    Licencia licenciaNueva = new Licencia(numerolicencia, expedicionL, vencimientoL, pais);
                    Usuario.addLicencia(licenciaNueva);
                    Conductor conductor = new Conductor(nombre.getText().trim(),Integer.parseInt(cedula.getText().trim()), licenciaNueva);
                    alquiler_u.addConductor(conductor);
                    avanzarAlSiguientePaso(siguientePasoKey);
                    }
                    else{
                        VentanaMain.errorDialog("No deje campos vacios");

                    }
                    

                }
                else{
                    VentanaMain.errorDialog("Ingrese una licencia que no caduque durante el alquiler");
                }
                }catch(NumberFormatException e2){
                    VentanaMain.errorDialog("Guarde fechas");

                }
            }
        });
        panel.add(panelC);
        cardPanel.add(panelC, pasoKey);

    }
    public void crearPasoPasarelas(String preguntaKey,String motivoPago,String pasoKey,Boolean dto,Boolean esModificacion ) throws FileNotFoundException, IOException{
        JPanel panel= new JPanel();
        VentanaMain.refresh(panel);
        panel.setLayout(new GridLayout(0, 2));
        panel.add(Box.createRigidArea(new Dimension(10,50)));
        panel.add(Box.createRigidArea(new Dimension(10,50)));
        panel.add(new JLabel("Seleccione con que pasarela de pagos desea realizar el pago"));
        JComboBox<String> opcionesPasarelas= new JComboBox<>();
        try (BufferedReader br = new BufferedReader(new FileReader("./registroPagos/config.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split("->");
                 if (partes.length >= 2){
                    opcionesPasarelas.addItem(partes[0]);
                 }}};
        opcionesPasarelas.setSelectedIndex(0);
        pasarelas=opcionesPasarelas;
        JButton continuar= new JButton("Continuar");
        panel.add(opcionesPasarelas);
        panel.add(continuar);
        panel.repaint();
        panel.revalidate();
        continuar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombreClase= pasarelas.getSelectedItem().toString();
                try {
                    Class<?> claseElejida= Class.forName("vista."+nombreClase);
                    String pathRegistro="";
                    String pathRegistroEsperado="registro"+nombreClase;
                    //Encontramos path del archivo sobre el que escribir transferencia
                    try (BufferedReader br = new BufferedReader(new FileReader("./registroPagos/config.txt"))) {
                    String linea;
                    while ((linea = br.readLine()) != null) {
                        String[] partes = linea.split("->");
                        if (partes.length >= 2){
                            String rutaCompleta=partes[1];
                            String rutaDeseada = rutaCompleta.substring(0, rutaCompleta.lastIndexOf("."));
                            if (rutaDeseada.equals(pathRegistroEsperado)){
                                pathRegistro=rutaCompleta;
                                break;
                            }                           
                        }}} catch (IOException e1) {
                            e1.printStackTrace();
                        };
                    
                    Constructor<?> constructor = claseElejida.getDeclaredConstructor(Cliente.class, String.class,double.class,int.class,String.class);
                    if (reserva_i.getID()==-1){
                        reserva_i.setID();
                    }
                    String motivoPagoStr=motivoPago;
                    if (dto){
                        motivoPagoStr=motivoPagoStr+" con 10% de dto";
                    }
                    Object instanciaClase = constructor.newInstance(reserva_i.getCliente(),motivoPagoStr,diferenciaPago,reserva_i.getID(),"registroPagos\\"+pathRegistro);
                    pasarelaPago pasarela= (pasarelaPago)instanciaClase;
                    VentanaMain.refresh(panel);
                    
                    pasarela.frame.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosed(WindowEvent e) {    

                            if (pasarela.getTransferenciaCompletada()==true){
                                VentanaMain.Dialog("El id de su reserva es: "+Integer.toString(reserva_i.getID()));
                                boolean yaRegistrado= false;
                                if (Reserva.getListaReservas()!=null){
                                for (Reserva i: Reserva.getListaReservas()){
                                    if (i.getID()==reserva_i.getID())
                                    {
                                        yaRegistrado=true;
                                        break;
                                    }
                                }
                                if (!yaRegistrado){
                                Reserva.addReserva(reserva_i);
                                }
                                }
                                else{
                                Reserva.addReserva(reserva_i);
                                }
                                }
                                else{
                                    VentanaMain.errorDialog("No se logró completar el pago, los cambios no se guardaron");
                                    if (esModificacion){
                                    try{
                                        reserva_i.getVehiculoAsignado().eliminarReservaActiva(reserva_i.getID());
                                        Reserva.getListaReservas().remove(reserva_i);
                                    }
                                    catch(IndexOutOfBoundsException e3){
                                    }
                                    VentanaMain.errorDialog("No se logró asignar un nuevo vehículo, los cambios no se guardaron");


                                    reserva_i=new Reserva(copiaReserva_i.getID(),copiaReserva_i.getFechaRecoger(),copiaReserva_i.getFechaEntregar(),copiaReserva_i.getHoraRecoger(),copiaReserva_i.getHoraEntregar(),copiaReserva_i.getReservaEnSede(),copiaReserva_i.getSedeRecoger(),copiaReserva_i.getSedeEntregar(),copiaReserva_i.getCategoria(),copiaReserva_i.getCliente());
                                    Reserva.addReserva(reserva_i);
                                    reserva_i.setPagoReserva(copiaReserva_i.getPagoReserva());
                                    reserva_i.setVehiculoAsignado(copiaReserva_i.getVehiculoAsignado());
                                    vehiculoAnterior.addReservaActiva(reserva_i);
                                    }
                                    else{
                                        reserva_i.getVehiculoAsignado().eliminarReservaActiva(reserva_i.getID());
                                    }
                                }
                                avanzarAlSiguientePaso(pasoKey);
                        }});
                        

                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e1) {
                    e1.printStackTrace();
                }


            }});
    


    cardPanel.add(panel, preguntaKey);
    }

    public void crearPasoPasarelas2(String preguntaKey, String pasoKey,String motivoPago,alquiler alquiler_u, Sede sede_personal) throws FileNotFoundException, IOException{
        JPanel panel= new JPanel();
        VentanaMain.refresh(panel);
        panel.setLayout(new GridLayout(0, 2));
        panel.add(Box.createRigidArea(new Dimension(10,50)));
        panel.add(Box.createRigidArea(new Dimension(10,50)));
        panel.add(new JLabel("Seleccione con que pasarela de pagos desea realizar el pago"));
        JComboBox<String> opcionesPasarelas= new JComboBox<>();
        try (BufferedReader br = new BufferedReader(new FileReader("./registroPagos/config.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split("->");
                 if (partes.length >= 2){
                    opcionesPasarelas.addItem(partes[0]);
                 }}};
        opcionesPasarelas.setSelectedIndex(0);
        
        JButton continuar= new JButton("Continuar");
        panel.add(opcionesPasarelas);
        panel.add(continuar);
        panel.repaint();
        panel.revalidate();
        continuar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombreClase= opcionesPasarelas.getSelectedItem().toString();
                try {
                    Class<?> claseElejida= Class.forName("vista."+nombreClase);
                    String pathRegistro="";
                    String pathRegistroEsperado="registro"+nombreClase;
                    //Encontramos path del archivo sobre el que escribir transferencia
                    try (BufferedReader br = new BufferedReader(new FileReader("./registroPagos/config.txt"))) {
                    String linea;
                    while ((linea = br.readLine()) != null) {
                        String[] partes = linea.split("->");
                        if (partes.length >= 2){
                            String rutaCompleta=partes[1];
                            String rutaDeseada = rutaCompleta.substring(0, rutaCompleta.lastIndexOf("."));
                            if (rutaDeseada.equals(pathRegistroEsperado)){
                                pathRegistro=rutaCompleta;
                                break;
                            }                           
                        }}} catch (IOException e1) {
                            e1.printStackTrace();
                        };
                    montoPago=0;
                    if (motivoPago.equals("Pago de alquiler inicial")){  
                    
                    montoPago=alquiler_u.calcularPagoInicial();
                    Constructor<?> constructor = claseElejida.getDeclaredConstructor(Cliente.class, String.class,double.class,int.class,String.class);
                    Object instanciaClase = constructor.newInstance(alquiler_u.getReserva().getCliente(),motivoPago,montoPago,alquiler_u.getReserva().getID(),"registroPagos\\"+pathRegistro);
                    pasarelaPago pasarela= (pasarelaPago)instanciaClase;
                    VentanaMain.refresh(panel);
                    
                    pasarela.frame.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosed(WindowEvent e) {
                            if (pasarela.getTransferenciaCompletada()==true){                                
                                alquiler_u.setPagoFinal(montoPago);
                                alquiler_u.setActivo(true);  
                                VentanaMain.Dialog("Alquiler iniciado correctamente. En este momento se puede entregar el vehículo al cliente");
                                avanzarAlSiguientePaso(pasoKey);
                            }
                            else{
                                alquiler_u.getReserva().getVehiculoAsignado().getHistorialAlquileres().remove(alquiler_u);
                                alquiler.getListaAlquileres().remove(alquiler_u);
                                VentanaMain.errorDialog("No se pudo iniciar el alquiler. Intentelo nuevamente");
                                avanzarAlSiguientePaso(pasoKey);
                            }
                        }});
                    }
                    else{
                        pagoFinalAlquiler=alquiler_u.calcularPagoFinal(sede_personal,averia);
                        if (pagoFinalAlquiler>0){
                        Constructor<?> constructor = claseElejida.getDeclaredConstructor(Cliente.class, String.class,double.class,int.class,String.class);
                        Object instanciaClase = constructor.newInstance(alquiler_u.getReserva().getCliente(),motivoPago,pagoFinalAlquiler,alquiler_u.getReserva().getID(),"registroPagos\\"+pathRegistro);
                        pasarelaPago pasarela= (pasarelaPago)instanciaClase;
                        VentanaMain.refresh(panel);
                        pasarela.frame.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosed(WindowEvent e) {
                            if (pasarela.getTransferenciaCompletada()==true){    
                                alquiler_u.eventosFinal(sede_personal, averia);                          
                                alquiler_u.setPagoFinal(pagoFinalAlquiler+alquiler_u.getPagoFinal());
                                alquiler_u.getReserva().getVehiculoAsignado().eliminarReservaActiva(alquiler_u.getID());
                                alquiler_u.setActivo(false);
                                VentanaMain.Dialog("El alquiler ha concluido. En este momento puede solicitar el vehículo al cliente.");
                                //TODO implementar metodo factura
                                VentanaMain.Dialog("Factura con el nombre: "+Integer.toString(alquiler_u.getID())+".pdf guardada en la carpeta \"facturas\".");


                            }
                            else{
                                VentanaMain.errorDialog("No se pudo concluir el alquiler. Intentelo nuevamente");
                                avanzarAlSiguientePaso(pasoKey);

                            }
                        }});

                        }
                        else{
                            VentanaMain.Dialog("El vehículo se ha devuelto correctamente y el cliente tiene un saldo a favor de COP "+Double.toString(pagoFinalAlquiler*-1)+" que se transferirán a su tarjeta terminada en "+ Long.toString(alquiler_u.getReserva().getCliente().getTarjeta().getNumeroTarjeta()% 10000)+".");
                            alquiler_u.eventosFinal(sede_personal, averia);                          
                            alquiler_u.setPagoFinal(pagoFinalAlquiler+alquiler_u.getPagoFinal());
                            alquiler_u.getReserva().getVehiculoAsignado().eliminarReservaActiva(alquiler_u.getID());
                            alquiler_u.setActivo(false);
                            avanzarAlSiguientePaso(pasoKey);

                        }

                    }
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e1) {
                    e1.printStackTrace();
                }
            }});
    cardPanel.add(panel, preguntaKey);
    }

}


