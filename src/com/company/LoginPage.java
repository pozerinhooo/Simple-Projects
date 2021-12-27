package com.company;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class LoginPage implements ActionListener {

    private JFrame frame;
    private JTextField textField;
    private JPasswordField passwordTextField;
    private JLabel loginLabel;
    private JLabel passwordLabel;
    private JLabel messageLabel;
    private ImageIcon imageIcon;
    private Font myFont;
    private Border border;
    private JButton resetButton;
    private JButton logInButton;
    private Map<String, String> loginAndPasswordDataMap;
    public LoginPage(Map<String, String> loginAndPasswordDataMap) {
        this.loginAndPasswordDataMap = loginAndPasswordDataMap;
        imageIcon = new ImageIcon("C:\\Users\\jkobm\\OneDrive\\Pulpit\\malik.jpg");
        myFont = new Font("MV Boli", Font.BOLD, 20);
        border = BorderFactory.createLineBorder(Color.BLACK, 5);

        textField = new JTextField();
        textField.setBounds(300, 200, 400, 50);
        textField.setBorder(border);
        textField.setFont(myFont);
        textField.setForeground(Color.CYAN);

        passwordTextField = new JPasswordField();
        passwordTextField.setBounds(300, 300, 400, 50);
        passwordTextField.setBorder(border);
        passwordTextField.setFont(myFont);
        passwordTextField.setForeground(Color.RED);

        loginLabel = new JLabel("Login");
        loginLabel.setFont(myFont);
        loginLabel.setForeground(Color.CYAN);
        loginLabel.setBounds(200, 200, 90, 50);

        passwordLabel = new JLabel("Password");
        passwordLabel.setFont(myFont);
        passwordLabel.setForeground(Color.RED);
        passwordLabel.setBounds(200, 300, 90, 50);

        messageLabel = new JLabel("");
        messageLabel.setFont(myFont);
        messageLabel.setBorder(border);
        messageLabel.setForeground(Color.GREEN);
        messageLabel.setBackground(Color.GRAY);
        messageLabel.setFont(new Font("MV Boli", Font.BOLD, 40));
        messageLabel.setBounds(100, 650, 600, 100);
        messageLabel.setHorizontalAlignment(JLabel.CENTER);
        messageLabel.setOpaque(true);

        resetButton = new JButton("RESET");
        resetButton.setBorder(border);
        resetButton.setFont(myFont);
        resetButton.setFocusable(false);
        resetButton.setBounds(200, 500, 200, 100);
        resetButton.addActionListener(this::actionPerformed);

        logInButton = new JButton("LOG IN");
        logInButton.setBorder(border);
        logInButton.setFont(myFont);
        logInButton.setFocusable(false);
        logInButton.setBounds(400, 500, 200, 100);
        logInButton.addActionListener(this::actionPerformed);

        frame = new JFrame("Login PAGE --> First Page");
        frame.add(textField);
        frame.add(passwordTextField);
        frame.setIconImage(imageIcon.getImage());
        frame.add(loginLabel);
        frame.add(passwordLabel);
        frame.add(messageLabel);
        frame.add(resetButton);
        frame.add(logInButton);
        frame.setSize(800 ,800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.DARK_GRAY);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(resetButton)) {
            textField.setText("");
            passwordTextField.setText("");
        }

        if (e.getSource().equals(logInButton)) {
            if (this.loginAndPasswordDataMap.containsKey(textField.getText())) {
                if (this.loginAndPasswordDataMap.get(textField.getText()).equals(passwordTextField.getText())) {
                    frame.dispose();
                    LoggedInPage loggedInPage = new LoggedInPage();
                } else {
                    messageLabel.setText("WRONG PASSWORD");
                }
            } else {
                messageLabel.setText("WRONG LOGIN");
            }
        }
    }

    public static void main(String[] args) {
        DataMap dataMap = new DataMap();
        LoginPage loginPage = new LoginPage(dataMap.getLoginAndPasswordDataMap());
    }
}
