import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.util.Map;
import java.util.Scanner;

public class ChangePasswordPage implements ActionListener, MouseListener {
    private JFrame frame;
    private Customer customer;
    private JPasswordField oldPasswordTextField;
    private JPasswordField[] newPasswordTextField = new JPasswordField[2];
    private JLabel mainLabel;
    private String actualPassword;
    private JButton backButton;
    private ImageIcon imageIcon = new ImageIcon("C:\\Users\\jkobm\\OneDrive\\Pulpit\\SIMPLE JAVA SWING PROJECT DATA\\pko.jpg");
    private DataMap dataMap;
    private Map<String, String> passwordsAndLogins;
    private JLabel oldPasswordIntroduceLabel;
    private JLabel newFirstPasswordIntroduceLabel;
    private JLabel newSecondPasswordIntroduceLabel;
    private JLabel messageLabel;
    private Map<String, Customer> customersMap;
    private JLabel logoLabel;
    private JButton changeButton;

    public ChangePasswordPage(Customer customer, DataMap dataMap, Map<String, String> passwordsAndLogins) throws IOException {
        this.passwordsAndLogins = passwordsAndLogins;
        this.customer = customer;
        this.dataMap = dataMap;
        this.customersMap = dataMap.getCustomerMap();
        this.actualPassword = customer.getPassword();

        mainLabel = new JLabel("Change password");
        mainLabel.setFont(new Font("MV Boli", Font.BOLD, 40));
        mainLabel.setForeground(Color.GREEN);
        mainLabel.setBounds(100, 250, 400, 100);

        frame = new JFrame("Changing password page");

        frame.setSize(new Dimension(1200, 1000));
        frame.getContentPane().setBackground(Color.DARK_GRAY);
        frame.setIconImage(imageIcon.getImage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        logoLabel = new JLabel(imageIcon);
        logoLabel.setBounds(600, 200, 200, 200);

        backButton = new BackButton();
        backButton.setBounds(100, 100, 100, 100);
        backButton.addActionListener(this::actionPerformed);

        oldPasswordTextField = new JPasswordField();
        oldPasswordTextField.setFont(new Font("MV Boli", Font.BOLD, 20));
        oldPasswordTextField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        oldPasswordTextField.setBounds(100, 400, 200, 50);
        oldPasswordTextField.addMouseListener(this);

        newPasswordTextField[0] = new JPasswordField();
        newPasswordTextField[0].setFont(new Font("MV Boli", Font.BOLD, 20));
        newPasswordTextField[0].setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        newPasswordTextField[0].setBounds(100, 600, 200, 50);
        newPasswordTextField[0].addMouseListener(this);

        newPasswordTextField[1] = new JPasswordField();
        newPasswordTextField[1].setFont(new Font("MV Boli", Font.BOLD, 20));
        newPasswordTextField[1].setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        newPasswordTextField[1].setBounds(100, 700, 200, 50);
        newPasswordTextField[1].addMouseListener(this);

        oldPasswordIntroduceLabel = new JLabel("Old password");
        oldPasswordIntroduceLabel.setFont(new Font("MV Boli", Font.BOLD, 20));
        oldPasswordIntroduceLabel.setBounds(350, 400, 200, 50);
        oldPasswordIntroduceLabel.setForeground(Color.BLUE);

        newFirstPasswordIntroduceLabel = new JLabel("New Password");
        newFirstPasswordIntroduceLabel.setFont(new Font("MV Boli", Font.BOLD, 20));
        newFirstPasswordIntroduceLabel.setBounds(350, 600, 200, 50);
        newFirstPasswordIntroduceLabel.setForeground(Color.BLUE);

        newSecondPasswordIntroduceLabel = new JLabel("New Password");
        newSecondPasswordIntroduceLabel.setForeground(Color.BLUE);
        newSecondPasswordIntroduceLabel.setFont(new Font("MV Boli", Font.BOLD, 20));
        newSecondPasswordIntroduceLabel.setBounds(350, 700, 200, 50);

        changeButton = new JButton("Change");
        changeButton.setFont(new Font("MV Boli", Font.BOLD, 30));
        changeButton.addActionListener(this::actionPerformed);
        changeButton.setForeground(Color.BLUE);
        changeButton.setBackground(Color.DARK_GRAY);
        changeButton.setBorder(BorderFactory.createLineBorder(Color.BLUE, 3));
        changeButton.addMouseListener(this);
        changeButton.setBounds(600, 650, 200, 50);
        changeButton.setFocusable(false);

        messageLabel = new JLabel();
        messageLabel.setBounds(600, 550, 450, 50);
        messageLabel.setFont(new Font("MV Boli", Font.BOLD, 30));


        frame.add(backButton);
        frame.add(mainLabel);
        frame.add(logoLabel);
        frame.add(messageLabel);
        frame.add(changeButton);
        frame.add(oldPasswordIntroduceLabel);
        frame.add(newFirstPasswordIntroduceLabel);
        frame.add(newSecondPasswordIntroduceLabel);
        frame.add(oldPasswordTextField);
        frame.add(newPasswordTextField[0]);
        frame.add(newPasswordTextField[1]);


        frame.setLayout(null);
        frame.setVisible(true);

    }

    private boolean changePassword(String oldPassword, String newPasswordFirst, String newPasswordSecond) throws IOException {
        if (customer.getPassword().equals(oldPassword)) {
            if (newPasswordFirst.equals(newPasswordSecond)) {
                File file = new File("C:\\Users\\jkobm\\OneDrive\\Pulpit\\SIMPLE JAVA SWING PROJECT DATA\\data.txt");


                Scanner scanner = new Scanner(new BufferedReader(new FileReader(file)));
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
                String oldContent = "";
                while (scanner.hasNextLine()) {
                    oldContent += (scanner.nextLine() + "\n");
                }

                String newContent = oldContent.replaceAll(oldPassword, newPasswordFirst);
                bufferedWriter.write(newContent);

                bufferedWriter.close();
                scanner.close();
                // here is the most important higher for practise

                this.passwordsAndLogins.replace(customer.getLogin(), oldPassword, newPasswordFirst);
                customer.setPassword(newPasswordFirst);

                return true;
            } return false;
        } return false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(backButton)) {
            frame.dispose();
            new MainValidPage(customer, dataMap, passwordsAndLogins);
        }
        if (e.getSource().equals(changeButton)) {
            try {
                if (changePassword(oldPasswordTextField.getText(), newPasswordTextField[0].getText(), newPasswordTextField[1].getText())) {
                        messageLabel.setText("Password changed");
                        messageLabel.setForeground(Color.GREEN);

                }else if (!customer.getPassword().equals(oldPasswordTextField.getText())){
                    messageLabel.setText("Old password is invalid");
                    messageLabel.setForeground(Color.RED);
                }
                else if (!newPasswordTextField[0].equals(newPasswordTextField[1])) {
                    messageLabel.setText("Passwords are not same");
                    messageLabel.setForeground(Color.RED);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
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
        if (e.getSource().equals(oldPasswordTextField)) {
            oldPasswordIntroduceLabel.setForeground(Color.GREEN);
            oldPasswordTextField.setForeground(Color.GREEN);
            oldPasswordTextField.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
        }

        if (e.getSource().equals(newPasswordTextField[0])) {
            newFirstPasswordIntroduceLabel.setForeground(Color.GREEN);
            newPasswordTextField[0].setForeground(Color.GREEN);
            newPasswordTextField[0].setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
        }

        if (e.getSource().equals(newPasswordTextField[1])) {
            newSecondPasswordIntroduceLabel.setForeground(Color.GREEN);
            newPasswordTextField[1].setForeground(Color.GREEN);
            newPasswordTextField[1].setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
        }

        if (e.getSource().equals(changeButton)) {
            changeButton.setForeground(Color.GREEN);
            changeButton.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource().equals(oldPasswordTextField)) {
            oldPasswordIntroduceLabel.setForeground(Color.BLUE);
            oldPasswordTextField.setForeground(Color.BLUE);
            oldPasswordTextField.setBorder(BorderFactory.createLineBorder(Color.BLUE, 3));
        }

        if (e.getSource().equals(newPasswordTextField[0])) {
            newFirstPasswordIntroduceLabel.setForeground(Color.BLUE);
            newPasswordTextField[0].setForeground(Color.BLUE);
            newPasswordTextField[0].setBorder(BorderFactory.createLineBorder(Color.BLUE, 3));
        }

        if (e.getSource().equals(newPasswordTextField[1])) {
            newSecondPasswordIntroduceLabel.setForeground(Color.BLUE);
            newPasswordTextField[1].setForeground(Color.BLUE);
            newPasswordTextField[1].setBorder(BorderFactory.createLineBorder(Color.BLUE, 3));
        }

        if (e.getSource().equals(changeButton)) {
            changeButton.setForeground(Color.BLUE);
            changeButton.setBorder(BorderFactory.createLineBorder(Color.BLUE, 3));
        }
    }

    public static void main(String[] args) throws IOException {


    }
}
