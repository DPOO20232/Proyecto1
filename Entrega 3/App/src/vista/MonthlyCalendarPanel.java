package vista;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MonthlyCalendarPanel extends JPanel {
    private JTable table;

    public MonthlyCalendarPanel(String titulo) {
        setLayout(new BorderLayout());
        JLabel label = new JLabel("Vista alto nivel: "+titulo, SwingConstants.CENTER);
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

                int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);

                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM yyyy (E)");
                String dateStr = sdf.format(cal.getTime());
                ((JComponent) c).setToolTipText(dateStr);

                if (dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY) {
                    c.setBackground(Color.LIGHT_GRAY);
                } else {
                    c.setBackground(Color.WHITE);
                }

                c.setPreferredSize(new Dimension(120, 40));

                return c;
            }
        };

        // Elimina la fila de encabezado
        table.setTableHeader(null);

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
        JLabel notaLabel = new JLabel("         Nota: la fecha más actual se encuentra abajo a la derecha, y la más antigua arriba a la izquierda.");
        add(notaLabel,BorderLayout.SOUTH);
    }

    public void setMonthlyCalendarPanel() {
        JFrame frame = new JFrame("Vista de alto nivel");
        frame.add(this);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1600, 500);
        frame.setVisible(true);
    }
}
