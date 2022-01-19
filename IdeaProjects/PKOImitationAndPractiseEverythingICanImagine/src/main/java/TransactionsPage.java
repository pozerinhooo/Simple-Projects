import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

public class TransactionsPage implements ActionListener { // tu może wejśc customer po makeAnTransaction i zobaczyć swoja listę transakcji
    private Customer customer;
    private DataMap dataMap;
    private Map<String, String> loginAndPasswords;
    private JPanel transactionsPanel;
    private JLabel mainLabel;
    private JLabel[] transactionsLabels;
    private JFrame frame;
    private ImageIcon imageIcon = new ImageIcon("C:\\Users\\jkobm\\OneDrive\\Pulpit\\SIMPLE JAVA SWING PROJECT DATA\\money.jpg");
    private BackButton backButton;
    private List<Double> transactionsList;
    private String previousPageTitle;
    public TransactionsPage(Customer customer, DataMap dataMap, Map<String, String> loginAndPasswords, JFrame frame) {
        this.customer = customer;
        this.dataMap = dataMap;
        this.loginAndPasswords = loginAndPasswords;
        this.transactionsList = customer.getTransactions();
        previousPageTitle = frame.getTitle();


        backButton = new BackButton();
        backButton.setBounds(0,0,100,100);
        backButton.addActionListener(this::actionPerformed);

        this.frame = new JFrame("Transaction Page");
        this.frame.setIconImage(imageIcon.getImage());
        this.frame.setLayout(null);
        this.frame.getContentPane().setBackground(Color.LIGHT_GRAY);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize(new Dimension(1200, 1000));

        mainLabel = new JLabel("Transactions");
        mainLabel.setBounds(0, 120, 600, 50);
        mainLabel.setFont(new Font("MV Boli", Font.BOLD, 70));
        mainLabel.setForeground(Color.BLACK);

        transactionsPanel = new JPanel(new GridLayout(transactionsList.size(), 1, 10, 10));
        transactionsPanel.setBounds(0, 180, 1100, 600);
        transactionsPanel.setBackground(Color.LIGHT_GRAY);


        transactionsLabels = new JLabel[transactionsList.size()];
        int i = 0;
        for (Double transactionValue : this.transactionsList) {
            transactionsLabels[i] = new JLabel();
            if (transactionValue >= 0) {
                transactionsLabels[i].setText((i+1) + " transaction --> " + String.valueOf(transactionValue) + "\t INPUT");
            } else {
                transactionsLabels[i].setText((i+1) + " transaction --> " + String.valueOf(transactionValue) + "\t OUTPUT");
            }
            transactionsLabels[i].setFont(new Font("Times new Roman", Font.BOLD, 20));
            transactionsLabels[i].setForeground(Color.BLACK);
            transactionsPanel.add(transactionsLabels[i]);
            i++;
        }

        this.frame.add(backButton);
        this.frame.add(mainLabel);
        this.frame.add(transactionsPanel);
        this.frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(backButton)) {
            if (previousPageTitle.equals("Personal information page")) {
                frame.dispose();
                new PersonalInformationPage(customer, dataMap, loginAndPasswords);
            } else if (previousPageTitle.equals("Make a transaction")) {
                frame.dispose();
                new MakeATransactionPage(customer, dataMap, loginAndPasswords);
            }
        }


    }
}
