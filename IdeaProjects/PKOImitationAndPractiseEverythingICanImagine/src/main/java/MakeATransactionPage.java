import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class MakeATransactionPage implements ActionListener, MouseListener {
    private DataMap dataMap;
    private Map<String, String> passwordAndLogins;
    private Customer customer;
    private JLabel introduceLabel;
    private JButton calcButton;
    private JFrame frame;
    private JLabel logoLabel;
    private ImageIcon imageIcon = new ImageIcon("C:\\Users\\jkobm\\OneDrive\\Pulpit\\SIMPLE JAVA SWING PROJECT DATA\\pko.jpg");
    private JButton addTrans;
    private JButton withdrawal;
    private JButton backButton;
    private JButton seeTransactions;
    private JTextField textField;
    private JLabel currentBalanceLabel;

    public MakeATransactionPage(Customer customer, DataMap dataMap, Map<String, String> passwordAndLogins) {
        this.passwordAndLogins = passwordAndLogins;
        this.dataMap = dataMap;
        this.customer = customer;
        frame = new JFrame("Make a transaction");

        introduceLabel = new JLabel(customer.getName() + " " + customer.getSurname() + ", please enter money amount: ");
        introduceLabel.setForeground(Color.BLACK);
        introduceLabel.setFont(new Font("Ink Free", Font.BOLD, 30));
        introduceLabel.setBounds(50, 50, 900, 200);

        textField = new JTextField();
        textField.setBounds(50, 250, 500, 50);
        textField.setBorder(BorderFactory.createLineBorder(Color.BLUE, 3));
        textField.setFont(new Font("Ink Free", Font.BOLD, 30));
        textField.addMouseListener(this);

        addTrans = new JButton("Input");
        addTrans.setBounds(600, 250, 100, 50);
        addTrans.setBorder(BorderFactory.createLineBorder(Color.BLUE, 3));
        addTrans.setForeground(Color.BLUE);
        addTrans.setFont(new Font("MV Boli", Font.BOLD, 30));
        addTrans.setFocusable(false);
        addTrans.addActionListener(this::actionPerformed);
        addTrans.setBackground(Color.LIGHT_GRAY);
        addTrans.addMouseListener(this);

        withdrawal = new JButton("Withdrawal");
        withdrawal.setBounds(700, 250, 200, 50);
        withdrawal.setBorder(BorderFactory.createLineBorder(Color.BLUE, 3));
        withdrawal.setFont(new Font("MV Boli", Font.BOLD, 30));
        withdrawal.setForeground(Color.BLUE);
        withdrawal.setFocusable(false);
        withdrawal.setBackground(Color.lightGray);
        withdrawal.addActionListener(this::actionPerformed);
        withdrawal.addMouseListener(this);

        backButton = new BackButton();
        backButton.addActionListener(this::actionPerformed);

        seeTransactions = new JButton("See trans");
        seeTransactions.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        seeTransactions.setBounds(700, 350, 200, 50);
        seeTransactions.setFont(new Font("MV Boli", Font.BOLD, 30));
        seeTransactions.setBackground(Color.DARK_GRAY);
        seeTransactions.setForeground(Color.BLUE);
        seeTransactions.addActionListener(this::actionPerformed);
        seeTransactions.setFocusable(false);
        seeTransactions.addMouseListener(this);

        currentBalanceLabel = new JLabel(String.valueOf(customer.getBalance()));
        currentBalanceLabel.setFont(new Font("Ink Free", Font.BOLD, 30));
        currentBalanceLabel.setBorder(BorderFactory.createLineBorder(Color.BLUE, 3));
        currentBalanceLabel.setBounds(50, 350, 200, 50);

        logoLabel = new JLabel();
        logoLabel.setBounds(400, 500, 200 ,200);
        logoLabel.setIcon(imageIcon);

        calcButton = new JButton("Calc");
        calcButton.addActionListener(this::actionPerformed);
        calcButton.addMouseListener(this);
        calcButton.setBounds(800, 100, 100, 100);
        calcButton.setBorder(BorderFactory.createLineBorder(Color.BLUE, 3));
        calcButton.setFont(new Font("MV Boli", Font.BOLD, 30));
        calcButton.setForeground(Color.BLUE);
        calcButton.setBackground(Color.CYAN);
        calcButton.setFocusable(false);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(calcButton);
        frame.add(logoLabel);
        frame.add(currentBalanceLabel);
        frame.setSize(new Dimension(1000, 800));
        frame.add(textField);
        frame.add(withdrawal);
        frame.add(addTrans);
        frame.add(backButton);
        frame.setIconImage(imageIcon.getImage());
        frame.add(introduceLabel);
        frame.add(seeTransactions);
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.LIGHT_GRAY);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(backButton)) {
            frame.dispose();
            new MainValidPage(customer, dataMap, passwordAndLogins);
        }

        if (e.getSource().equals(seeTransactions)) {
            frame.dispose();
            new TransactionsPage(customer, dataMap, passwordAndLogins, this.frame);
        }
        if(e.getSource().equals(addTrans)) {
            BufferedWriter bufferedWriter = null;
            try {
                customer.addTransaction(Double.parseDouble(textField.getText()));

                try {
                  bufferedWriter = new BufferedWriter(new FileWriter("C:\\Users\\jkobm\\OneDrive\\Pulpit\\SIMPLE JAVA SWING PROJECT DATA\\transactions.txt", true));
                  if (Double.parseDouble(textField.getText()) >= 0) {
                      bufferedWriter.append("INPUT, ").append(customer.getName()).append(", ").append(customer.getSurname()).append(", ").append(customer.getPeselID()).append(",").append(textField.getText()).append("\n");

                      currentBalanceLabel.setText(String.valueOf(customer.getBalance()));
                      textField.setText("");
                  } else {
                      textField.setText("Invalid value :(");
                  }
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            } catch (NumberFormatException exception) {
                textField.setText("Invalid number :(");
            } finally {
                try {
                    bufferedWriter.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }

        if(e.getSource().equals(withdrawal)) {
            BufferedWriter bufferedWriter = null;
            try {
                customer.addTransaction(Double.parseDouble(textField.getText()) * (-1));

                try {
                    bufferedWriter = new BufferedWriter(new FileWriter("C:\\Users\\jkobm\\OneDrive\\Pulpit\\SIMPLE JAVA SWING PROJECT DATA\\transactions.txt", true));
                    if (Double.parseDouble(textField.getText()) >= 0) {
                        bufferedWriter.append("WITHDRAWAL, ").append(customer.getName()).append(", ").append(customer.getSurname()).append(", ").append(customer.getPeselID()).append(",").append(textField.getText()).append("\n");

                        currentBalanceLabel.setText(String.valueOf(customer.getBalance()));
                        textField.setText("");
                    } else {
                        textField.setText("Invalid value :(");
                    }
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            } catch (NumberFormatException exception) {
                textField.setText("Invalid number :(");
            } finally {
                try {
                    bufferedWriter.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }

        if (e.getSource().equals(calcButton)) {
            frame.dispose();
            new Calculator(customer, dataMap, passwordAndLogins);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource().equals(addTrans)) {
            addTrans.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
            addTrans.setForeground(Color.GREEN);
            
        }

        if (e.getSource().equals(withdrawal)) {
            withdrawal.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
            withdrawal.setForeground(Color.GREEN);
        }

        if (e.getSource().equals(calcButton)) {
            calcButton.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
            calcButton.setForeground(Color.BLACK);
        }
        
        if (e.getSource().equals(seeTransactions)) {
            seeTransactions.setForeground(Color.CYAN);
            seeTransactions.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource().equals(addTrans)) {
            addTrans.setBorder(BorderFactory.createLineBorder(Color.BLUE, 3));
            addTrans.setForeground(Color.BLUE);

        }

        if (e.getSource().equals(withdrawal)) {
            withdrawal.setBorder(BorderFactory.createLineBorder(Color.BLUE, 3));
            withdrawal.setForeground(Color.BLUE);
        }

        if (e.getSource().equals(calcButton)) {
            calcButton.setBorder(BorderFactory.createLineBorder(Color.BLUE, 3));
            calcButton.setForeground(Color.BLUE);
        }

        if (e.getSource().equals(seeTransactions)) {
            seeTransactions.setForeground(Color.BLUE);
            seeTransactions.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        }
    }
}
