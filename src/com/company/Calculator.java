package com.company;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator implements ActionListener {
    private Border border;
    private Font myFont;
    private ImageIcon imageIcon;
    private JFrame frame;
    private JTextField textField;
    private JPanel panel;
    private JButton[] numButtons;
    private JButton[] funButtons;
    private JButton sumButton;
    private JButton subButton;
    private JButton mulButton;
    private JButton decButton;
    private JButton equButton;
    private JButton divButton;
    private JButton delButton;
    private JButton negButton;
    private JButton clearScreenButton;
    private JButton ticTacToeGameButton;

    double firstNumber;
    double secondNumber;
    char operator;

    public Calculator () {
        imageIcon = new ImageIcon("C:\\Users\\jkobm\\OneDrive\\Pulpit\\malik.jpg");

        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setIconImage(imageIcon.getImage());
        frame.getContentPane().setBackground(Color.LIGHT_GRAY);
        frame.setSize(400,600);

        border = BorderFactory.createLineBorder(Color.BLACK, 5); // border

        myFont = new Font("Dialog", Font.BOLD, 20); // font

        panel = new JPanel(); // panel
        panel.setLayout(new GridLayout(4,4,10,10));
        panel.setBounds(50, 125, 300, 350);
        panel.setBackground(Color.DARK_GRAY);

        textField = new JTextField(); // text field
        textField.setBounds(50, 50, 300, 50);
        textField.setFocusable(false);
        textField.setBorder(border);
        textField.setFont(myFont);

        sumButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("*");
        decButton = new JButton(".");
        equButton = new JButton("=");
        divButton = new JButton("/");
        delButton = new JButton("Del");
        negButton = new JButton("(-)");
        clearScreenButton = new JButton("Cls");

        funButtons = new JButton[9];
        funButtons[0] = sumButton;
        funButtons[1] = subButton;
        funButtons[2] = mulButton;
        funButtons[3] = decButton;
        funButtons[4] = equButton;
        funButtons[5] = divButton;
        funButtons[6] = delButton;
        funButtons[7] = negButton;
        funButtons[8] = clearScreenButton;

        for (int i=0; i<funButtons.length; i++) {
            funButtons[i].setFont(myFont);
            funButtons[i].setBorder(border);
            funButtons[i].addActionListener(this::actionPerformed);
            funButtons[i].setFocusable(false);
        }

        numButtons = new JButton[10];
        for (int i=0; i<numButtons.length; i++) {
            numButtons[i] = new JButton(String.valueOf(i));
            numButtons[i].setFont(myFont);
            numButtons[i].addActionListener(this::actionPerformed);
            numButtons[i].setFocusable(false);
            numButtons[i].setBorder(border);
        }

        panel.add(numButtons[1]);
        panel.add(numButtons[2]);
        panel.add(numButtons[3]);
        panel.add(sumButton);
        panel.add(numButtons[4]);
        panel.add(numButtons[5]);
        panel.add(numButtons[6]);
        panel.add(subButton);
        panel.add(numButtons[7]);
        panel.add(numButtons[8]);
        panel.add(numButtons[9]);
        panel.add(mulButton);
        panel.add(decButton);
        panel.add(numButtons[0]);
        panel.add(equButton);
        panel.add(divButton);

        delButton.setBorder(border);
        delButton.setBounds(50, 500, 100, 50);

        clearScreenButton.setBorder(border);
        clearScreenButton.setBounds(150, 500, 100, 50);

        negButton.setBorder(border);
        negButton.setBounds(250, 500, 100, 50);

        ticTacToeGameButton = new JButton("Tic Tac Toe Game Button");
        ticTacToeGameButton.setBounds(50, 5, 300, 30);
        ticTacToeGameButton.setFocusable(false);
        ticTacToeGameButton.setFont(myFont);
        ticTacToeGameButton.setBorder(border);
        ticTacToeGameButton.addActionListener(this::actionPerformed);

        frame.add(textField);
        frame.add(panel);
        frame.add(delButton);
        frame.add(clearScreenButton);
        frame.add(negButton);
        frame.add(ticTacToeGameButton);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i=0; i<numButtons.length; i++) {
            if (e.getSource().equals(numButtons[i])) {
                textField.setText(textField.getText().concat(String.valueOf(i)));
            }
        }

        if (e.getSource().equals(sumButton)) {
            firstNumber = Double.parseDouble(textField.getText());
            operator = '+';
            textField.setText("");
        }

        if (e.getSource().equals(subButton)) {
            firstNumber = Double.parseDouble(textField.getText());
            operator = '-';
            textField.setText("");
        }

        if (e.getSource().equals(mulButton)) {
            firstNumber = Double.parseDouble(textField.getText());
            operator = '*';
            textField.setText("");
        }

        if (e.getSource().equals(decButton)) {
            textField.setText(textField.getText().concat("."));
        }

        if (e.getSource().equals(divButton)) {
            firstNumber = Double.parseDouble(textField.getText());
            operator = '/';
            textField.setText("");
        }

        if (e.getSource().equals(equButton)) {
            secondNumber = Double.parseDouble(textField.getText());
            switch (operator) {

                case '+' -> {
                    textField.setText(String.valueOf(firstNumber + secondNumber));
                    firstNumber = firstNumber + secondNumber;
                }

                case '-' -> {
                    textField.setText(String.valueOf(firstNumber - secondNumber));
                    firstNumber = firstNumber - secondNumber;
                }

                case '*' -> {
                    textField.setText(String.valueOf(firstNumber * secondNumber));
                    firstNumber = firstNumber * secondNumber;
                }

                case '/' -> { // ADD EXCEPTION SYNTAX
                    try {
                        textField.setText(String.valueOf(firstNumber / secondNumber));
                        firstNumber = firstNumber / secondNumber;
                    } catch (ArithmeticException exception) {
                        textField.setText("Can not div by 0");
                        try {
                            Thread.sleep(1000);
                            textField.setText("");
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }
        }

        if (e.getSource().equals(clearScreenButton)) {
            textField.setText("");
        }

        if (e.getSource().equals(negButton)) {
            textField.setText(String.valueOf(Double.parseDouble(textField.getText()) * (-1)));
        }

        if (e.getSource().equals(delButton)) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i=0; i<textField.getText().length() - 1; i++) {
                stringBuilder.append(String.valueOf(textField.getText().charAt(i)));
            }
            textField.setText(stringBuilder.toString());
        }

        if (e.getSource().equals(ticTacToeGameButton)) {
            frame.dispose();
            TicTacToe ticTacToe = new TicTacToe();
        }
    }

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
    }
}
