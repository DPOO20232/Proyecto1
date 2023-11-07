package vista;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import modelo.Inventario;
import modelo.Sede;
import modelo.Vehiculo;
import modelo.alquiler;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MonthlyCalendarPanel extends JPanel {
    private JTable table;

    public MonthlyCalendarPanel(Sede sede) {
        setLayout(new BorderLayout());
        JLabel label = new JLabel("Vista alto nivel: "+sede.getNombre(), SwingConstants.CENTER);
        ArrayList<Vehiculo> vehiculosSede= new ArrayList<Vehiculo>();
        ArrayList<alquiler> alquileresSede= new ArrayList<alquiler>();
        for (Vehiculo i : Inventario.getListaVehiculos()){
            if (i.getSede().getID()==sede.getID()){
                vehiculosSede.add(i);
            }
        }
        for  (alquiler i: alquiler.getListaAlquileres()){
            if (i.getReserva().getSedeRecoger().getID()==sede.getID()){
                alquileresSede.add(i);
            }
        }
        int numVehiculos= vehiculosSede.size();
        int cincuentaPct=numVehiculos/2;
        int veintiCincoPct=numVehiculos/4;
        /*
        verde: en promedio, la disponibilidad de carros para la sede en el día dado es mayor o igual al 50%
        amarillo: en promedio, la disponibilidad de carros para la sede en el día dado es mayor o igual al 25%
        rojo: en promedio, la disponibilidad de carros para la sede en el día dado es menor al 25%.
        2.  Al pasar el mouse por cada casilla, se especificará el día de la semana al que corresponde,
        número de alquileres registrados y número de vehículos disponibles.

        3.  Al haber 30 casillas, la última casilla tendrá un promedio (desde el día 30 hasta el último día del mes 
        correspondiente) del número de alquileres registrados y número de vehículos, que también se podrán visualizar
        al pasar el mouse por la casilla.
         */
        add(label, BorderLayout.NORTH);

        DefaultTableModel model = new DefaultTableModel(10, 37) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(model) {
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);
                Date currentDate = new Date();
                Calendar cal = Calendar.getInstance();
                cal.setTime(currentDate);
                int dayDiff = ((10 - row - 1) * 37 + (36 - column));
                cal.add(Calendar.DAY_OF_YEAR, -dayDiff);



                SimpleDateFormat fecha = new SimpleDateFormat("ddMMyyyy");
                int fechaInt = Integer.parseInt(fecha.format(cal.getTime()));

                int numAlquileres=0;

                for (alquiler i: alquileresSede){
                    int fechaInicio= i.getReserva().getFechaRecoger();
                    int fechaFin=i.getReserva().getFechaEntregar();
                    if (fechaInt>= fechaInicio && fechaInt<=fechaFin){
                        numAlquileres+=1;
                    }
                }

                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM yyyy (E)");
                String dateStr = sdf.format(cal.getTime());
                String info=" .Alquileres Registrados: "+Integer.toString(numAlquileres)+ ". Vehiculos disponibles: "+Integer.toString(numVehiculos-numAlquileres)+".";

                ((JComponent) c).setToolTipText(dateStr+info);

                if (numAlquileres>= cincuentaPct) {
                    c.setBackground(Color.decode("#FA9887"));
                } else if (numAlquileres>= veintiCincoPct){
                    c.setBackground(Color.decode("#FAF987"));
                }
                else{
                    c.setBackground(Color.decode("#87FAA6"));


                }
                c.setPreferredSize(new Dimension(120, 40));

                return c;
            }
        };

        // Elimina la fila de encabezado
        table.setTableHeader(null);

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
        JLabel formato= new JLabel();
        Icon icon = new ImageIcon("./imagenes/flecha.png");
        formato.setIcon(icon);
        formato.setText("            Nota1: Lea los datos desde la fecha mas reciente a la mas antigua en el sentido de la flecha:");
        formato.setVerticalTextPosition(JLabel.TOP);
        formato.setHorizontalTextPosition(JLabel.CENTER);
        JLabel colores= new JLabel("            Nota2:Código de colores: verde-> +50% de disponibilidad, amarillo-> +25% de disponibilidad, rojo-> -25% disponibilidad");
        JLabel notaLabel = new JLabel("            Nota3: la fecha más actual se encuentra abajo a la derecha, y la más antigua arriba a la izquierda.");
        JPanel panelInf= new JPanel(new GridLayout(0, 1));
        panelInf.add(formato);
        panelInf.add(colores);
        panelInf.add(notaLabel);
        add(panelInf,BorderLayout.SOUTH);
    }

    public void setMonthlyCalendarPanel() {
        JFrame frame = new JFrame("Vista de alto nivel");
        frame.add(this);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1600, 500);
        frame.setVisible(true);
    }
}
