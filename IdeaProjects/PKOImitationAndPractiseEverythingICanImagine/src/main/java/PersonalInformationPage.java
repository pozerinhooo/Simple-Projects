import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Map;

public class PersonalInformationPage implements MouseListener, ActionListener {
    private JFrame frame;
    private Customer customer;
    private ImageIcon imageIcon = new ImageIcon("C:\\Users\\jkobm\\OneDrive\\Pulpit\\SIMPLE JAVA SWING PROJECT DATA\\pko.jpg");
    private JLabel mainLabel;
    private JLabel[] infoLabels = new JLabel[7];
    private JButton backButton;
    private JPanel labelsPanel = new JPanel(new GridLayout(7, 1, 10, 10));
    private JLabel logoIcon = new JLabel();
    private DataMap dataMap;
    private Map<String, String> passwordsAndLogins;
    public PersonalInformationPage(Customer customer, DataMap dataMap, Map<String, String> passwordsAndLogins) {
        this.passwordsAndLogins = passwordsAndLogins;
        this.dataMap = dataMap;

        backButton = new BackButton();
        backButton.addActionListener(this::actionPerformed);
        backButton.setBounds(1000, 200, 100, 100);

        try {
        this.customer = customer;
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        infoLabels[0] = new JLabel(customer.getName());
        infoLabels[1] = new JLabel(customer.getSurname());
        infoLabels[2] = new JLabel(customer.getPeselID());
        infoLabels[3] = new JLabel(customer.getEmailAdress());
        infoLabels[4] = new JLabel(customer.getRealAdress());
        infoLabels[5] = new JLabel(String.valueOf(customer.getInitialValue()));
        infoLabels[6] = new JLabel("Transactions");
        infoLabels[6].addMouseListener(this);

        labelsPanel.setBounds(50, 150, 400, 600);
        labelsPanel.setBackground(Color.DARK_GRAY);

        for (JLabel label : infoLabels) {
            label.setFont(new Font("Ink Free", Font.BOLD, 20));
            label.setForeground(Color.GREEN);
            label.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
            labelsPanel.add(label);
        }



        logoIcon.setIcon(imageIcon);
        logoIcon.setBounds(700, 300, 300, 300);

        frame = new JFrame("Personal information page");
        frame.add(backButton);
        frame.setLayout(null);
        frame.setIconImage(imageIcon.getImage());
        frame.getContentPane().setBackground(Color.LIGHT_GRAY);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(1200, 800));
        frame.getContentPane().setBackground(Color.CYAN);

        mainLabel = new JLabel("Data");
        mainLabel.setFont(new Font("MV Boli", Font.BOLD, 100 ));
        mainLabel.setBounds(50,50, 600, 100);

        frame.add(logoIcon);
        frame.add(labelsPanel);
        frame.add(mainLabel);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(backButton)) {
            frame.dispose();
            new MainValidPage(customer,dataMap, passwordsAndLogins);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource().equals(infoLabels[6])) {
            frame.dispose();
            new TransactionsPage(customer, dataMap, passwordsAndLogins, this.frame); // tutaj chce mieć liste transacki tego customera
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource().equals(infoLabels[6])) {
            infoLabels[6].setForeground(Color.BLUE);
            infoLabels[6].setBorder(BorderFactory.createLineBorder(Color.BLUE, 3));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource().equals(infoLabels[6])) {
            infoLabels[6].setForeground(Color.GREEN);
            infoLabels[6].setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        }
    }
}
