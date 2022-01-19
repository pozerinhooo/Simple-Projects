import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Map;
import java.util.Scanner;

public class LoginPage implements ActionListener, MouseListener {
    private JFrame frame;
    private JTextField loginTextField;
    private JPasswordField passwordTextField;
    private JLabel loginLabel;
    private JLabel passwordLabel;
    private JLabel logoLabel;
    private Map<String ,String> passwordsAndLogins;
    private DataMap allDataMap;
    private ImageIcon imageIcon = new ImageIcon("C:\\Users\\jkobm\\OneDrive\\Pulpit\\SIMPLE JAVA SWING PROJECT DATA\\pko.jpg");
    private Font myFont = new Font("Ink Free", Font.BOLD, 30);
    private Border myBorder = BorderFactory.createLineBorder(Color.BLACK, 3);
    private JButton loginButton;
    private JButton resetButton; 
    private JButton quitButton; 
    private JLabel messageLabel;


    private int loginTries = 0;

    public LoginPage(DataMap allDataMap, Map<String, String> passwordsAndLogins) {
        this.allDataMap = allDataMap;
        this.passwordsAndLogins = passwordsAndLogins;
        System.out.println();
        frame = new JFrame("Login Page");
        frame.setIconImage(this.imageIcon.getImage());
        frame.setSize(new Dimension(800, 800));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.GRAY);

        logoLabel = new JLabel();
        logoLabel.setBounds(100, 100, 300, 500);
        logoLabel.setIcon(new ImageIcon("C:\\Users\\jkobm\\OneDrive\\Pulpit\\SIMPLE JAVA SWING PROJECT DATA\\pko.jpg"));

        loginLabel = new JLabel("Login:");
        loginLabel.setFont(myFont);
        loginLabel.setForeground(Color.BLUE);
        loginLabel.setBounds(350, 250, 100, 50);
        loginLabel.addMouseListener(this);

        passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(myFont);
        passwordLabel.setForeground(Color.BLUE);
        passwordLabel.setBounds(350, 350, 150, 50);
        passwordLabel.addMouseListener(this);

        loginTextField = new JTextField();
        loginTextField.setBounds(500, 250, 250, 50);
        loginTextField.setBorder(myBorder);
        loginTextField.setFont(myFont);
        loginTextField.setForeground(Color.BLUE);
        loginTextField.addMouseListener(this);

        passwordTextField = new JPasswordField();
        passwordTextField.setBounds(500, 350, 250, 50);
        passwordTextField.setBorder(myBorder);
        passwordTextField.setFont(myFont);
        passwordTextField.setForeground(Color.BLUE);
        passwordTextField.addMouseListener(this);

        loginButton = new JButton("Login");
        loginButton.setBorder(myBorder);
        loginButton.setFont(myFont);
        loginButton.setBounds(400, 420, 100, 50);
        loginButton.setFocusable(false);
        loginButton.setBackground(Color.WHITE);
        loginButton.setForeground(Color.BLUE);
        loginButton.addActionListener(this::actionPerformed);
        loginButton.addMouseListener(this);
        
        resetButton = new JButton("Reset");
        resetButton.setBorder(myBorder);
        resetButton.setFont(myFont);
        resetButton.setBounds(500, 420, 100, 50);
        resetButton.setFocusable(false);
        resetButton.setBackground(Color.WHITE);
        resetButton.setForeground(Color.BLUE);
        resetButton.addActionListener(this::actionPerformed);
        resetButton.addMouseListener(this);

        quitButton = new JButton("Quit");
        quitButton.setBorder(myBorder);
        quitButton.setFont(myFont);
        quitButton.setBounds(600, 420, 100, 50);
        quitButton.setFocusable(false);
        quitButton.setBackground(Color.WHITE);
        quitButton.setForeground(Color.BLUE);
        quitButton.addActionListener(this::actionPerformed);
        quitButton.addMouseListener(this);
        
        messageLabel = new JLabel("Enter data");
        messageLabel.setBounds(100, 600, 700, 200);
        messageLabel.setFont(new Font("MV Boli", Font.BOLD, 80));
        messageLabel.setForeground(Color.BLUE);

        frame.add(messageLabel);
        frame.add(loginButton);
        frame.add(resetButton);
        frame.add(quitButton);
        frame.add(passwordLabel);
        frame.add(loginLabel);
        frame.add(loginTextField);
        frame.add(passwordTextField);
        frame.add(logoLabel);
        frame.setLayout(null);
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(loginButton)) {
            if (allDataMap.getDataMap().containsKey(loginTextField.getText())) {
                if (allDataMap.getDataMap().get(loginTextField.getText()).equals(passwordTextField.getText())) {

                    messageLabel.setText("Logging.."); // idk why does not work
                    messageLabel.setForeground(Color.GREEN);
                    Customer customer = findCustomer(loginTextField.getText(), passwordTextField.getText());
                    frame.dispose();
                    new MainValidPage(customer, allDataMap, passwordsAndLogins);

                } else {
                    messageLabel.setText("Wrong password!");
                    messageLabel.setForeground(Color.RED);
                }
            } else {
                messageLabel.setText("Wrong LOGIN!");
                messageLabel.setForeground(Color.RED);
            }
            this.loginTries++;
            if (this.loginTries == 3) {
                frame.dispose();
                new InvalidDataPage(allDataMap, passwordsAndLogins);
            }
        }

        if (e.getSource().equals(resetButton)) {
            loginTextField.setText("");
            passwordTextField.setText("");
        }

        if (e.getSource().equals(quitButton)) {
            System.exit(0);
        }
    }

    public Customer findCustomer(String login, String password) {
        for (Map.Entry<String, Customer> customersMap : allDataMap.getCustomerMap().entrySet()) {
            if (customersMap.getValue().getLogin().equals(login) && customersMap.getValue().getPassword().equals(password)) {
                return customersMap.getValue();
            }
        }
        return null;
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
        if (e.getSource().equals(loginTextField)) {
            loginTextField.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
            loginTextField.setForeground(Color.GREEN);
            loginLabel.setForeground(Color.GREEN);
        }

        if (e.getSource().equals(passwordTextField)) {
            passwordTextField.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
            passwordTextField.setForeground(Color.GREEN);
            passwordLabel.setForeground(Color.GREEN);
        }

        if (e.getSource().equals(loginButton)) {
            loginButton.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
            loginButton.setForeground(Color.GREEN);
        }
        
        if (e.getSource().equals(resetButton)) {
            resetButton.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
            resetButton.setForeground(Color.GREEN);
        }

        if (e.getSource().equals(quitButton)) {
            quitButton.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
            quitButton.setForeground(Color.GREEN);
        }
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource().equals(loginTextField)){
            loginTextField.setBorder(BorderFactory.createLineBorder(Color.BLUE, 3));
            loginTextField.setForeground(Color.BLUE);
            loginLabel.setForeground(Color.BLUE);

        }

        if (e.getSource().equals(passwordTextField)) {
            passwordLabel.setForeground(Color.BLUE);
            passwordTextField.setForeground(Color.BLUE);
            passwordTextField.setBorder(BorderFactory.createLineBorder(Color.BLUE, 3));
        }

        if (e.getSource().equals(loginButton)) {
            loginButton.setForeground(Color.BLUE);
            loginButton.setBorder(BorderFactory.createLineBorder(Color.BLUE, 3));
        }

        if (e.getSource().equals(resetButton)) {
            resetButton.setForeground(Color.BLUE);
            resetButton.setBorder(BorderFactory.createLineBorder(Color.BLUE, 3));
        }

        if (e.getSource().equals(quitButton)) {
            quitButton.setForeground(Color.BLUE);
            quitButton.setBorder(BorderFactory.createLineBorder(Color.BLUE, 3));
        }
    }
}
