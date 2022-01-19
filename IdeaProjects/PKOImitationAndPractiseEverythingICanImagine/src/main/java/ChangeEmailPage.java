import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.Map;

public class ChangeEmailPage implements ActionListener, MouseListener {
    private Customer customer;
    private DataMap dataMap;
    private Map<String, String> passwordsAndLogins;
    private JFrame frame;
    private ImageIcon imageIcon = new ImageIcon("C:\\Users\\jkobm\\OneDrive\\Pulpit\\SIMPLE JAVA SWING PROJECT DATA\\pko.jpg");
    private JLabel logoLabel;
    private JLabel introduceLabel;

    private JTextField oldEmail;
    private JLabel oldEmailIntroduceLabel;
    private JLabel messageLabel;
    private JTextField newFirstEmail;
    private JLabel newFirstEmailIntroduceLabel;

    private JTextField newSecondEmail;
    private JLabel newSecondEmailIntroduceLabel;

    private JButton changeButton;
    private BackButton backButton;

    public ChangeEmailPage(Customer customer, DataMap dataMap, Map<String, String> passwordsAndLogins) {
        this.customer = customer;
        this.dataMap = dataMap;
        this.passwordsAndLogins = passwordsAndLogins;

        backButton = new BackButton();
        backButton.setBounds(1050, 100, 100, 100);
        backButton.addActionListener(this::actionPerformed);
        backButton.addMouseListener(this);

        changeButton = new JButton("Change");
        changeButton.setFont(new Font("MV Boli", Font.BOLD, 40));
        changeButton.setBounds(50, 600, 200, 50);
        changeButton.addActionListener(this::actionPerformed);
        changeButton.addMouseListener(this);
        changeButton.setForeground(Color.BLUE);
        changeButton.setBackground(Color.lightGray);
        changeButton.setBorder(BorderFactory.createLineBorder(Color.BLUE, 3));

        frame = new JFrame("Change e-mail page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(1200, 1000));
        frame.setIconImage(imageIcon.getImage());
        frame.getContentPane().setBackground(Color.GRAY);

        introduceLabel = new JLabel("Change email");
        introduceLabel.setFont(new Font("MV Boli", Font.BOLD, 50));
        introduceLabel.setForeground(Color.CYAN);
        introduceLabel.setBounds(50, 100, 700, 100);

        oldEmail = new JTextField();
        oldEmail.setFont(new Font("Ink Free", Font.BOLD, 20));
        oldEmail.setBorder(BorderFactory.createLineBorder(Color.BLUE, 3));
        oldEmail.setForeground(Color.BLUE);
        oldEmail.setBounds(50, 250, 350, 50);
        oldEmail.addMouseListener(this);

        oldEmailIntroduceLabel = new JLabel("Old email");
        oldEmailIntroduceLabel.setFont(new Font("MV Boli", Font.BOLD, 20));
        oldEmailIntroduceLabel.setForeground(Color.BLUE);
        oldEmailIntroduceLabel.setBounds(420, 250, 200, 50);
        oldEmailIntroduceLabel.addMouseListener(this);

        newFirstEmail = new JTextField();
        newFirstEmail.setFont(new Font("Ink Free", Font.BOLD, 20));
        newFirstEmail.setBorder(BorderFactory.createLineBorder(Color.BLUE, 3));
        newFirstEmail.setForeground(Color.BLUE);
        newFirstEmail.setBounds(50, 400, 350, 50);
        newFirstEmail.addMouseListener(this);

        newFirstEmailIntroduceLabel = new JLabel("New email");
        newFirstEmailIntroduceLabel.setFont(new Font("MV Boli", Font.BOLD, 20));
        newFirstEmailIntroduceLabel.setForeground(Color.BLUE);
        newFirstEmailIntroduceLabel.setBounds(420, 400, 200, 50);
        newFirstEmailIntroduceLabel.addMouseListener(this);

        newSecondEmail = new JTextField();
        newSecondEmail.setFont(new Font("Ink Free", Font.BOLD, 20));
        newSecondEmail.setBorder(BorderFactory.createLineBorder(Color.BLUE, 3));
        newSecondEmail.setForeground(Color.BLUE);
        newSecondEmail.setBounds(50, 500, 350, 50);
        newSecondEmail.addMouseListener(this);

        newSecondEmailIntroduceLabel = new JLabel("New email again");
        newSecondEmailIntroduceLabel.setFont(new Font("MV Boli", Font.BOLD, 20));
        newSecondEmailIntroduceLabel.setForeground(Color.BLUE);
        newSecondEmailIntroduceLabel.setBounds(420, 500, 200, 50);
        newSecondEmailIntroduceLabel.addMouseListener(this);

        logoLabel = new JLabel();
        logoLabel.setIcon(imageIcon);
        logoLabel.setBounds(800, 100, 300, 300);

        messageLabel = new JLabel();
        messageLabel.setFont(new Font("MV Boli", Font.BOLD, 40));
        messageLabel.setBounds(50, 600, 800, 200);

        frame.add(messageLabel);
        frame.add(backButton);
        frame.add(changeButton);
        frame.add(oldEmail);
        frame.add(oldEmailIntroduceLabel);
        frame.add(newFirstEmail);
        frame.add(newFirstEmailIntroduceLabel);
        frame.add(newSecondEmail);
        frame.add(newSecondEmailIntroduceLabel);
        frame.add(logoLabel);
        frame.add(introduceLabel);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    private boolean changeEmail(String oldEmail, String newEmailFirst, String newEmailSecond) {
     if (customer.getEmailAdress().equals(oldEmail)) {
         if (newEmailFirst.equals(newEmailSecond)) {
             customer.setEmailAdress(newEmailSecond);
             messageLabel.setText("Email changed :)");
             messageLabel.setForeground(Color.GREEN);
             return true;
         } else {
             messageLabel.setText("New email's are different");
             messageLabel.setForeground(Color.RED);
             return false;
         }
     } else {
         messageLabel.setText("Old email is incorrect");
         messageLabel.setForeground(Color.RED);
         return false;
     }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(backButton)) {
            frame.dispose();
            new MainValidPage(customer, dataMap, passwordsAndLogins);
        }
        if (e.getSource().equals(changeButton)) {
            changeEmail(oldEmail.getText(), newFirstEmail.getText(), newSecondEmail.getText());
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
        if (e.getSource().equals(oldEmail)) {
            oldEmail.setForeground(Color.GREEN);
            oldEmail.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
            oldEmailIntroduceLabel.setForeground(Color.GREEN);
        }

        if (e.getSource().equals(newFirstEmail)) {
            newFirstEmail.setForeground(Color.GREEN);
            newFirstEmail.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
            newFirstEmailIntroduceLabel.setForeground(Color.GREEN);
        }

        if (e.getSource().equals(newSecondEmail)) {
            newSecondEmail.setForeground(Color.GREEN);
            newSecondEmail.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
            newSecondEmailIntroduceLabel.setForeground(Color.GREEN);
        }

        if (e.getSource().equals(changeButton)) {
            changeButton.setForeground(Color.GREEN);
            changeButton.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource().equals(oldEmail)) {
            oldEmail.setForeground(Color.BLUE);
            oldEmail.setBorder(BorderFactory.createLineBorder(Color.BLUE, 3));
            oldEmailIntroduceLabel.setForeground(Color.BLUE);
        }

        if (e.getSource().equals(newFirstEmail)) {
            newFirstEmail.setForeground(Color.BLUE);
            newFirstEmail.setBorder(BorderFactory.createLineBorder(Color.BLUE, 3));
            newFirstEmailIntroduceLabel.setForeground(Color.BLUE);
        }

        if (e.getSource().equals(newSecondEmail)) {
            newSecondEmail.setForeground(Color.BLUE);
            newSecondEmail.setBorder(BorderFactory.createLineBorder(Color.BLUE, 3));
            newSecondEmailIntroduceLabel.setForeground(Color.BLUE);
        }

        if (e.getSource().equals(changeButton)) {
            changeButton.setForeground(Color.BLUE);
            changeButton.setBorder(BorderFactory.createLineBorder(Color.BLUE, 3));
        }
    }
}
