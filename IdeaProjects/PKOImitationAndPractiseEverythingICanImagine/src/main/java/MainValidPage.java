import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MainValidPage implements ActionListener, MouseListener {
    private DataMap dataMap;
    private Map<String, String> passwordsAndLogins;
    private JFrame frame;
    private ImageIcon imageIcon = new ImageIcon("C:\\Users\\jkobm\\OneDrive\\Pulpit\\SIMPLE JAVA SWING PROJECT DATA\\pko.jpg");
    private JLabel mainLabel;
    private JButton showAccountInformation;
    private JButton makeATransaction;
    private JButton changePassword;
    private JButton changeEmail;
    private JButton seePossibleBranches;
    private JButton seeHowToGetContact;
    private JButton writeAComplaint;
    private JPanel buttonsPanel;
    private JLabel logoLabel;
    private JButton ticTacToeGameButton;
    private JButton backButton;
    private Customer currentCustomer;
    public static final double DEFAULT_VALUE = 0.0;
    private JButton[] funButtons = new JButton[7];
    private Font myFont = new Font("Ink Free", Font.BOLD, 80);

    public MainValidPage(Customer customer, DataMap dataMap, Map<String, String> passwordsAndLogins) {
        this.passwordsAndLogins = passwordsAndLogins;
        this.currentCustomer = customer;
        this.dataMap = dataMap;
        this.backButton = new BackButton();
        backButton.addActionListener(this::actionPerformed);
        backButton.setBounds(1000, 200, 100, 100);

        frame = new JFrame("Main Page");
        frame.setLayout(null);
        frame.setIconImage(imageIcon.getImage());
        frame.getContentPane().setBackground(Color.LIGHT_GRAY);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(1200, 800));

        buttonsPanel = new JPanel(new GridLayout(7, 1, 10 , 10));
        buttonsPanel.setBounds(50, 170, 400, 500);

        mainLabel = new JLabel("Choose options: ");
        mainLabel.setFont(myFont);
        mainLabel.setForeground(Color.BLUE);
        mainLabel.setBounds(50, 50, 800, 100);

        showAccountInformation = new JButton("Show Account Info");
        makeATransaction = new JButton("Make a transaction");
        changePassword = new JButton("Change a password");
        changeEmail = new JButton("Change an e-mail");
        seePossibleBranches = new JButton("See possible branches");
        seeHowToGetContact = new JButton("Find contact");
        writeAComplaint = new JButton("Write a complaint");

        funButtons[0] = showAccountInformation;
        funButtons[1] = makeATransaction;
        funButtons[2] = changePassword;
        funButtons[3] = changeEmail;
        funButtons[4] = seePossibleBranches;
        funButtons[5] = seeHowToGetContact;
        funButtons[6] = writeAComplaint;

        for (int i=0; i<funButtons.length; i++) {
            funButtons[i].setBorder(BorderFactory.createLineBorder(Color.BLUE, 3));
            funButtons[i].setFont(new Font("MV Boli", Font.BOLD, 20));
            funButtons[i].setForeground(Color.BLUE);
            funButtons[i].setFocusable(false);
            funButtons[i].addActionListener(this::actionPerformed);
            funButtons[i].addMouseListener(this);
            funButtons[i].setForeground(Color.BLUE);
            funButtons[i].setBackground(Color.lightGray);
            buttonsPanel.add(funButtons[i]);
        }

        ticTacToeGameButton = new JButton("Click me :)");
        ticTacToeGameButton.setBounds(800, 200, 180 ,100);
        ticTacToeGameButton.setBackground(Color.cyan);
        ticTacToeGameButton.setForeground(Color.BLACK);
        ticTacToeGameButton.setBorder(BorderFactory.createLineBorder(Color.BLACK ,3));
        ticTacToeGameButton.addMouseListener(this);
        ticTacToeGameButton.setFocusable(false);
        ticTacToeGameButton.setFont(new Font("MV Boli", Font.BOLD, 30));
        ticTacToeGameButton.addActionListener(this::actionPerformed);

        logoLabel = new JLabel();
        logoLabel.setIcon(imageIcon);
        logoLabel.setBounds(550, 200, 400 ,400);

        frame.add(backButton);
        frame.add(ticTacToeGameButton);
        frame.add(logoLabel);
        frame.add(buttonsPanel);
        frame.add(mainLabel);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(ticTacToeGameButton)) {
            frame.dispose();
            new TicTacToeGame(currentCustomer, dataMap, passwordsAndLogins);
        }

        if (e.getSource().equals(showAccountInformation)) {
            frame.dispose();
            new PersonalInformationPage(currentCustomer, dataMap, passwordsAndLogins);
        }

        if (e.getSource().equals(backButton)) {
            frame.dispose();
            new LoginPage(dataMap, passwordsAndLogins);
        }

        if (e.getSource().equals(changePassword)) {
            frame.dispose();
            try {
                new ChangePasswordPage(currentCustomer, dataMap, passwordsAndLogins);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        if (e.getSource().equals(changeEmail)) {
            frame.dispose();
            try {
                new ChangeEmailPage(currentCustomer, dataMap, passwordsAndLogins);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        if (e.getSource().equals(seePossibleBranches)) {
            frame.dispose();
            try {
                new SeePossibleBranches(currentCustomer, dataMap, passwordsAndLogins);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource().equals(funButtons[1])) {
            frame.dispose();
            new MakeATransactionPage(currentCustomer, dataMap, passwordsAndLogins);
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
        if (e.getSource().equals(ticTacToeGameButton)) {
            ticTacToeGameButton.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
            ticTacToeGameButton.setForeground(Color.GREEN);
        }
        
        for (int i=0; i<funButtons.length; i++) {
            if (e.getSource().equals(funButtons[i])) {
                funButtons[i].setForeground(Color.GREEN);
                funButtons[i].setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
            }
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource().equals(ticTacToeGameButton)) {
            ticTacToeGameButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
            ticTacToeGameButton.setForeground(Color.BLACK);
        }
        
        for (int i=0; i< funButtons.length; i++) {
            if (e.getSource().equals(funButtons[i])) {
            funButtons[i].setForeground(Color.BLUE);
            funButtons[i].setBorder(BorderFactory.createLineBorder(Color.BLUE, 3));
            }
        }
    }

}
