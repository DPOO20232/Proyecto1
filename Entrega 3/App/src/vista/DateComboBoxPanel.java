package vista;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

        populateMonthComboBox();
        monthComboBox.setSelectedItem("01");
        monthComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    populateDayComboBox(year);
                    }});
        panelInf.add(monthComboBox);
        panelInf.add(dayComboBox);
        this.add(panelSup);
        this.add(panelInf);
    }
    private void populateMonthComboBox() {
        monthComboBox.removeAllItems();
        for (int i = 1; i <= 12; i++) {
            String s="";
            if (i<10){s=s+"0";}
            s=s+Integer.toString(i);
            monthComboBox.addItem(s);
        }
    }
    private void populateDayComboBox(int year) {
        dayComboBox.removeAllItems();
        String strdMonth=(String) monthComboBox.getSelectedItem();
        int selectedMonth = Integer.parseInt(strdMonth);
        int maxDay = getMaxDayOfMonth(selectedMonth,year);
        for (int i = 1; i <= maxDay; i++) {
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Custom ComboBox Panel");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 400);
            //Implementacion
            DateComboBoxPanel customPanel = new DateComboBoxPanel(2023);
            customPanel.setDefaulDayComboBox();
            customPanel.setDefaultMonthComboBox();
            //
            frame.add(customPanel,BorderLayout.NORTH );
            JPanel panelinf= new JPanel();
            
            JLabel resultLabel = new JLabel("Result: ");
            JButton getTextButton = new JButton("Get Text");

            getTextButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String text = customPanel.getText();
                    resultLabel.setText("Result: " + text);
                }
            });
            panelinf.add(getTextButton);
            panelinf.add(resultLabel);
            frame.add(panelinf, BorderLayout.SOUTH);

            frame.setVisible(true);
        });
    }
}
