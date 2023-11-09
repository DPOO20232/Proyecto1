package vista;
import javax.swing.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelesModificaciones {
    private static boolean booleanEleccion;
    private static boolean booleanAccion;

    public static boolean preguntaModificaciones(JPanel mainPanel,String s) {
        booleanEleccion=false;
        booleanAccion=false;
        JPanel panel= new JPanel();
        panel.setBounds(0, 0, 0, 0);

        ButtonGroup buttonGroup = new ButtonGroup();
        JRadioButton opcion1 = new JRadioButton("Opción 1");
        JRadioButton opcion2 = new JRadioButton("Opción 2");
        buttonGroup.add(opcion1);
        buttonGroup.add(opcion2);
        JButton avanzar = new JButton("Avanzar");
        avanzar.setEnabled(false);

        opcion1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if (opcion1.isSelected()) {
                    avanzar.setEnabled(true);
            	}}});
        opcion2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if (opcion2.isSelected()) {
                    avanzar.setEnabled(true);
            	}}});
        avanzar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if (opcion1.isSelected()) {
                    booleanEleccion=true;
                }
                else{
                    booleanEleccion=false;
                }
                booleanAccion=true;
                panel.setVisible(false); // Oculta el panel después de la selección.

                }});
        panel.add(new JLabel("¿Desea modificar "+s+"?"));
        panel.add(opcion1);
        panel.add(opcion2);
        panel.add(avanzar);
        panel.setVisible(true);
        mainPanel.add(panel);
        mainPanel.revalidate();
        mainPanel.repaint();
        // Espera a que el usuario haga una selección antes de retornar.
        while (!booleanAccion) {
            try {
                Thread.sleep(100); // Pequeña pausa para evitar bucle de CPU.
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        return booleanEleccion;
    }

        public static String modificacionString(JPanel mainPanel, String nom, String ejem) throws InterruptedException, ExecutionException {
            JPanel panel = new JPanel();
            panel.setBounds(0, 0, 0, 0);
            panel.add(new JLabel("Después de ingresar el texto presione Enter."));
            panel.add(new JLabel("\n"));
            PlaceHolderTextField input = new PlaceHolderTextField(ejem);
            JButton avanzar = new JButton("Avanzar");
            avanzar.setEnabled(false);

            CompletableFuture<String> result = new CompletableFuture<>();
            // Evento para el botón "Avanzar"
            avanzar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!input.getText().trim().isEmpty()) {
                        result.complete(input.getText());
                        panel.setVisible(false); // Oculta el panel después de la selección.
                    }
                }
            });

            input.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    booleanAccion = !input.getText().trim().isEmpty();
                    avanzar.setEnabled(booleanAccion);
                }
            });

            panel.add(new JLabel(nom));
            panel.add(input);
            panel.add(avanzar);
            mainPanel.add(panel);
            mainPanel.revalidate();
            mainPanel.repaint();

            return result.get();
    }

    public String modificacionMMDD(JPanel mainPanel, String nom) {
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 0, 0);
        panel.add(new JLabel("Después de seleccionar la fecha, presione Enter."));
        panel.add(new JLabel("\n"));
    
        // Crear botones y componentes necesarios
        JButton avanzar = new JButton("Avanzar");
        avanzar.setEnabled(false);
        DateComboBoxPanel dateSelector= new DateComboBoxPanel(2023);
    
        CompletableFuture<String> result = new CompletableFuture<>();
    
        // Evento para el botón "Avanzar"
        avanzar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String resultado= "";
                result.complete(resultado); // Obtener la fecha seleccionada
                panel.setVisible(false); // Oculta el panel después de la selección.
            }
        });
    
        panel.add(new JLabel(nom));
        panel.add(dateSelector); // Agregar el componente DateSelectorPanel
        panel.add(avanzar);
        mainPanel.add(panel);
        mainPanel.revalidate();
        mainPanel.repaint();
    
        return "";
    }
        
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        // Tu código va aquí
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        frame.add(panel);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        System.out.println(preguntaModificaciones(panel, "nombre"));
    }
}
