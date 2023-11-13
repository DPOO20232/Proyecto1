package vista;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.util.ArrayList;
import java.util.List;

import modelo.Inventario;
import modelo.Sede;
import modelo.Seguro;
import modelo.Vehiculo;
import modelo.personal;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class EditorObjetos {
    private  JPanel mainPanel;
    private  CardLayout cardLayout;
    private  JPanel cardPanel;
    private  String[] pasos;
    private int pasoActual = 0;

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
    public void editorPersonal(JPanel mainPanel,personal personal) {
        this.mainPanel = mainPanel;
        this.cardLayout = new CardLayout();
        this.cardPanel = new JPanel(cardLayout);
        String [] pasosSeguro ={"PreguntaDescripcion", "InputDescripcion", "PreguntaDescripcion2", "InputDescripcion2","Fin"};
        this.pasos=pasosSeguro ;
        mainPanel.add(cardPanel);
        crearPasosPersonal(personal);
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
    private void crearPasosPersonal(personal personal) {
        crearPasoPregunta("PreguntaPassword", "¿Desea modificar la clave del empleado?", "InputPassword", "PreguntaSede");
        crearPasoInput("InputPassword", "Ingrese la nueva contraseña", "PreguntaSede",personal);
        crearPasoPregunta("PreguntaSede", "¿Desea modificar la sede?", "InputSede", "Fin");
        crearPasoInput("InputSede", "Ingrese el ID de la sede", "Fin",personal);
        crearPasoFin("Fin");
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

        for (int i = 0; i <= 23; i++) {
            String s="";
            if (i<10){
                s=s+"0";
            }
            opcionesHora1.addElement(s+Integer.toString(i));
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
                System.out.println("inicio:"+inicio);
                System.out.println("fin:"+fin);
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
                    avanzarAlSiguientePaso(siguientePasoKey);
                }
                else{
                    VentanaMain.errorDialog("Verifique que la fecha/periodo inicial sea previa a la fecha/periodo final.");
                }  
            }
        });
    
        cardPanel.add(panel, pasoKey);
    }
    private void crearPasoFecha(String pasoKey, String nombreCampo, String siguientePasoKey, Object O) {
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Nueva " + nombreCampo + ":");
        JLabel labela = new JLabel("\t");
    
        JLabel labelDia1 = new JLabel("Día inicio ");
        JLabel labelMes1 = new JLabel("Mes inicio ");
        JLabel labelAnio1 = new JLabel("Año inicio ");
        JPanel fechaPanel1 = new JPanel();
    
        JLabel labelDia2 = new JLabel("Día fin ");
        JLabel labelMes2 = new JLabel("Mes fin ");
        JLabel labelAnio2 = new JLabel("Año fin ");
        JPanel fechaPanel2 = new JPanel();
    
        DefaultComboBoxModel<String> opcionesDia1 = new DefaultComboBoxModel<>();
        DefaultComboBoxModel<String> opcionesDia2 = new DefaultComboBoxModel<>();
    
        DefaultComboBoxModel<String> opcionesMes1 = new DefaultComboBoxModel<>();
        DefaultComboBoxModel<String> opcionesMes2 = new DefaultComboBoxModel<>();
    
        DefaultComboBoxModel<String> opcionesAnio1 = new DefaultComboBoxModel<>();
        DefaultComboBoxModel<String> opcionesAnio2 = new DefaultComboBoxModel<>();
    
        // Llenar las opciones para días, meses y años
        for (int i = 1; i <= 31; i++) {
            opcionesDia1.addElement(Integer.toString(i));
            opcionesDia2.addElement(Integer.toString(i));
        }
    
        String[] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
        for (String mes : meses) {
            opcionesMes1.addElement(mes);
            opcionesMes2.addElement(mes);
        }
    
        for (int i = 2022; i <= 2030; i++) {
            opcionesAnio1.addElement(Integer.toString(i));
            opcionesAnio2.addElement(Integer.toString(i));
        }
    
        JComboBox<String> diaComboBox1 = new JComboBox<>(opcionesDia1);
        JComboBox<String> mesComboBox1 = new JComboBox<>(opcionesMes1);
        JComboBox<String> anioComboBox1 = new JComboBox<>(opcionesAnio1);
    
        JComboBox<String> diaComboBox2 = new JComboBox<>(opcionesDia2);
        JComboBox<String> mesComboBox2 = new JComboBox<>(opcionesMes2);
        JComboBox<String> anioComboBox2 = new JComboBox<>(opcionesAnio2);
    
        JButton avanzarButton = new JButton("Avanzar");
    
        fechaPanel1.add(diaComboBox1);
        fechaPanel1.add(mesComboBox1);
        fechaPanel1.add(anioComboBox1);
    
        fechaPanel2.add(diaComboBox2);
        fechaPanel2.add(mesComboBox2);
        fechaPanel2.add(anioComboBox2);
    
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.add(label);
        panel.add(labela);
        panel.add(labelDia1);
        panel.add(fechaPanel1);
        panel.add(labelDia2);
        panel.add(fechaPanel2);
        panel.add(avanzarButton);
    
        avanzarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener la fecha seleccionada
                String dia1 = diaComboBox1.getSelectedItem().toString();
                String mes1 = String.valueOf(mesComboBox1.getSelectedIndex() + 1); // Sumar 1 porque los meses en Java comienzan desde 0
                String anio1 = anioComboBox1.getSelectedItem().toString();
    
                String dia2 = diaComboBox2.getSelectedItem().toString();
                String mes2 = String.valueOf(mesComboBox2.getSelectedIndex() + 1);
                String anio2 = anioComboBox2.getSelectedItem().toString();
    
                String fechaInicio = anio1 + "-" + mes1 + "-" + dia1;
                String fechaFin = anio2 + "-" + mes2 + "-" + dia2;
    
                System.out.println("Fecha inicio: " + fechaInicio);
                System.out.println("Fecha fin: " + fechaFin);
    
                
    
                // Avanzar al siguiente paso
                avanzarAlSiguientePaso(siguientePasoKey);
            }
        });
    
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
}
