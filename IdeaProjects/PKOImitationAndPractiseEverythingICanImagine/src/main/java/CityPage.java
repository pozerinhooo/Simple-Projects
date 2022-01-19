import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class CityPage implements ActionListener {
    private Customer customer;
    private DataMap dataMap;
    private Map<String, String> loginAndPasswords;
    private BackButton backButton;
    private JFrame frame;
    private JPanel panel;
    private JLabel[] labels;
    private Font myFont = new Font("MV Boli", Font.BOLD, 40);
    List<Brunch> brunchList;
    String cityName;
    int numberOfBrunches;
    private ImageIcon imageIcon = new ImageIcon("C:\\Users\\jkobm\\OneDrive\\Pulpit\\SIMPLE JAVA SWING PROJECT DATA\\pko.jpg");
    public CityPage(List<Brunch> brunchList, Customer customer, DataMap dataMap, Map<String, String> loginAndPasswords) {
        this.customer = customer;
        this.dataMap = dataMap;
        this.loginAndPasswords = loginAndPasswords;

        this.brunchList = brunchList;
        cityName = brunchList.get(0).getCityLocation();
        numberOfBrunches = brunchList.size();

        frame = new JFrame(cityName + " brunch list");
        frame.setLayout(null);
        frame.setIconImage(imageIcon.getImage());
        frame.setSize(new Dimension(1200, 1000));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBackground(Color.GRAY);

        panel = new JPanel(new GridLayout(numberOfBrunches, 1, 5, 5));
        panel.setBounds(0, 120, 1150, 600);

        labels = new JLabel[numberOfBrunches];
        for (int i=0; i<numberOfBrunches; i++) {
            labels[i] = new JLabel();
            labels[i].setText("Adres --> " + brunchList.get(i).getAdress() + "   " + "Brunch number --> " +  brunchList.get(i).getBrunchNumber()
                    + "   " + "Phone number -->" +  brunchList.get(i).getPhoneContactNumber());
            labels[i].setFont(new Font("MV Boli", Font.BOLD, 20));
            labels[i].setForeground(Color.BLACK);
            panel.add(labels[i]);
        }
        backButton = new BackButton();
        backButton.addActionListener(this::actionPerformed);
        backButton.setBounds(0, 0, 100, 100);

        frame.setVisible(true);
        frame.add(panel);
        frame.add(backButton);
    }

    public List<Brunch> getBrunchList() {
        return brunchList;
    }

    public String getCityName() {
        return cityName;
    }

    public int getNumberOfBrunches() {
        return numberOfBrunches;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(backButton)) {
            try {
                frame.dispose();
                new SeePossibleBranches(customer, dataMap, loginAndPasswords);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }


}
