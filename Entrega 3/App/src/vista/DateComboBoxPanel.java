package vista;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

public class DateComboBoxPanel extends JPanel {
    private JComboBox<String> monthComboBox;
    private JComboBox<String> dayComboBox;

    public DateComboBoxPanel(int year) {

        setSize(new Dimension(80,100));
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        JPanel panelSup= new JPanel();
        panelSup.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelSup.add(new JLabel(" Mes "));
        panelSup.add(new JLabel(" Dia "));
        JPanel panelInf= new JPanel();
        panelInf.setLayout(new GridLayout(0, 2));  
        panelInf.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        monthComboBox= new JComboBox<>();
        dayComboBox = new JComboBox<>();

        int anioActual=Calendar.getInstance().get(Calendar.YEAR);
        int mesActual=Calendar.getInstance().get(Calendar.MONTH)+1;

        boolean mismoAnio= anioActual==year;
        
        int mes_iteracion=populateMonthComboBox(anioActual,year);
        monthComboBox.setSelectedItem("01");
        monthComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    boolean mismoMes= mesActual==mes_iteracion;
                    populateDayComboBox(year, mismoMes&&mismoAnio);
                    }});
        panelInf.add(monthComboBox);
        panelInf.add(dayComboBox);
        this.add(panelSup);
        this.add(panelInf);
    }
    private int populateMonthComboBox(int anio_actual, int anio_dado) {
        monthComboBox.removeAllItems();
        int mes_inicio=1;
        if (anio_actual==anio_dado){
            mes_inicio=Calendar.getInstance().get(Calendar.MONTH)+1;
        }
        for (int i = mes_inicio; i <= 12; i++) {
            String s="";
            if (i<10){s=s+"0";}
            s=s+Integer.toString(i);
            monthComboBox.addItem(s);
        }
        return mes_inicio;
    }
    private void populateDayComboBox(int year,boolean mismoMesAnio) {
        dayComboBox.removeAllItems();
        String strdMonth=(String) monthComboBox.getSelectedItem();
        int selectedMonth = Integer.parseInt(strdMonth);
        int maxDay = getMaxDayOfMonth(selectedMonth,year);
        int diaInicio=1;
        if (mismoMesAnio){
            diaInicio=Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        }
        for (int i = diaInicio; i <= maxDay; i++) {
            String s="";
            if (i<10){s=s+"0";}
            s=s+Integer.toString(i);
            dayComboBox.addItem(s);
        }
    }
    private int getMaxDayOfMonth(int month, int currentYear) {
        int maxDay;
        if (month == 2) { // February
            maxDay = 28; // Default to 28 days
            //int currentYear = Calendar.getInstance().get(Calendar.YEAR);
            if (currentYear % 4 == 0 && (currentYear % 100 != 0 || currentYear % 400 == 0)) {
                maxDay = 29; // Leap year: 29 days
            }
        } else if (month == 4 || month == 6 || month == 9 || month == 11) {
            maxDay = 30; // April, June, September, November: 30 days
        } else {
            maxDay = 31; // All other months: 31 days
        }
        return maxDay;
    
    }

    public String getText() {
        String text1 = monthComboBox.getSelectedItem().toString();
        String text2 = dayComboBox.getSelectedItem().toString();
        return text1 + text2;
    }
    public void setDefaultMonthComboBox(){
        this.monthComboBox.setSelectedIndex(0);
    }
    public void setDefaulDayComboBox(){
        this.monthComboBox.setSelectedIndex(0);
    }
}
