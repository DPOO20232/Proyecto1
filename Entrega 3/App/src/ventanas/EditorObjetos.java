
package ventanas;
import javax.swing.*;
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

    public void editorPersonal(JPanel mainPanel) {
        this.mainPanel = mainPanel;
        this.cardLayout = new CardLayout();
        this.cardPanel = new JPanel(cardLayout);
        String [] pasosSeguro ={"PreguntaDescripcion", "InputDescripcion", "PreguntaDescripcion2", "InputDescripcion2","Fin"};
        this.pasos=pasosSeguro ;
        mainPanel.add(cardPanel);
        crearPasosPersonal();
    }
    public void editar() {
        // Comienza con el primer paso
        mostrarPasoActual();
    }

    private void crearPasosPersonal() {
        crearPasoPregunta("PreguntaDescripcion", "¿Desea modificar la clave del empleado?", "InputDescripcion", "PreguntaDescripcion2");
        crearPasoInput("InputDescripcion", "Ingrese la nueva contraseña", "PreguntaDescripcion2");
        crearPasoPregunta("PreguntaDescripcion2", "¿Desea modificar la sede?", "InputDescripcion2", "Fin");
        crearPasoInput("InputDescripcion2", "Ingrese el ID de la sede", "Fin");
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

    private void crearPasoInput(String pasoKey, String nombreCampo, String siguientePasoKey) {
        JPanel panel = new JPanel();
        JLabel label = new JLabel(nombreCampo + ":");
        JTextField textField = new JTextField(20);
        JButton avanzarButton = new JButton("Avanzar");

        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.add(label);
        panel.add(textField);
        panel.add(avanzarButton);

        avanzarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Realiza la operación correspondiente con el nuevo valor
                System.out.println("Nuevo " + nombreCampo + ": " + textField.getText());

                // Avanza al siguiente paso
                avanzarAlSiguientePaso(siguientePasoKey);
            }
        });

        cardPanel.add(panel, pasoKey);
    }

    
    private void crearPasoFin(String pasoKey) {
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Cambios registrados");
        JButton finalizarButton = new JButton("Finalizar");
        
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.add(label);
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Editor de Personal");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 300);

            JPanel mainPanel = new JPanel(new BorderLayout());
            frame.add(mainPanel);

            EditorObjetos editor = new EditorObjetos();
            editor.editorPersonal(mainPanel);
            editor.editar();

            frame.setVisible(true);
        });
    }
}

