package com.company;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;


public class Calculator implements ActionListener {
    private JFrame frame;
    private JTextField textField;
    private JButton[] numberButtons;
    private JButton[] funButtons;
    private JButton sumButton;
    private JButton subButton;
    private JButton mulButton;
    private JButton divButton;
    private JButton equButton;
    private JButton decButton;
    private JButton percentButton;
    private JButton bracketButton;
    private JButton delButton;
    private JButton negButton;

    private JPanel panel;
    private Font myFont = new Font("Serif", Font.BOLD, 30);
    private Border border = BorderFactory.createLineBorder(Color.BLACK, 5);
    private ImageIcon imageIcon;
    double firstNumber, secondNumber;
    char operator;
    public Calculator() {
        frame = new JFrame("Calculator");
        frame.setLayout(null);
        imageIcon = new ImageIcon("C:\\Users\\jkobm\\OneDrive\\Pulpit\\kalk.jpg");

        frame.setBounds(0, 0, 414, 637);
        frame.setIconImage(imageIcon.getImage());
        frame.add(new JTextField());
        textField = new JTextField();
        textField.setBounds(50, 50, 300, 50);
        textField.setBorder(border);
        textField.setFont(myFont);
        textField.setFocusable(false);

        panel = new JPanel(new GridLayout(5, 4));
        panel.setBounds(0, 150, 400, 450);
        panel.setBackground(Color.GRAY);

        numberButtons = new JButton[10];
        for (int i=0; i< numberButtons.length; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
            numberButtons[i].setBackground(Color.cyan);
            numberButtons[i].addActionListener(this::actionPerformed);
            numberButtons[i].setFocusable(false);
            numberButtons[i].setFont(myFont);
        }
        sumButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("*");
        divButton = new JButton("/");
        equButton = new JButton("=");
        decButton = new JButton(".");
        percentButton = new JButton("%");
        bracketButton = new JButton("()");
        delButton = new JButton("Del");
        negButton = new JButton("(-)");

        funButtons = new JButton[10];
        funButtons[0] = sumButton;
        funButtons[1] = subButton;
        funButtons[2] = mulButton;
        funButtons[3] = divButton;
        funButtons[4] = equButton;
        funButtons[5] = decButton;
        funButtons[6] = percentButton;
        funButtons[7] = bracketButton;
        funButtons[8] = delButton;
        funButtons[9] = negButton;

        for (int i=0; i< funButtons.length; i++) {
            funButtons[i].setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
            funButtons[i].setBackground(Color.cyan);
            funButtons[i].addActionListener(this::actionPerformed);
            funButtons[i].setFocusable(false);
            funButtons[i].setFont(myFont);
        }

        panel.add(delButton);
        panel.add(bracketButton);
        panel.add(percentButton);
        panel.add(divButton);
        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(mulButton);
        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(subButton);
        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(sumButton);
        panel.add(negButton);
        panel.add(numberButtons[0]);
        panel.add(decButton);
        panel.add(equButton);

        frame.add(textField);
        frame.add(panel);
        frame.getContentPane().setBackground(Color.DARK_GRAY);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);


    }

    private String rightString(String stringValue) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i=0; i<textField.getText().length(); i++) {
            assert stringBuilder != null;
            if (!(textField.getText().charAt(i) >= '0' && textField.getText().charAt(i) <= '9') && textField.getText().charAt(i) != '.' && textField.getText().charAt(i) != '-') {
                stringBuilder.append("");
            }
            else stringBuilder.append(textField.getText().charAt(i));
        }
        return stringBuilder.toString();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i=0; i< numberButtons.length; i++) {
            if (e.getSource().equals(numberButtons[i])) {
                textField.setText(textField.getText().concat(String.valueOf(i)));
            }
        }

        if (e.getSource().equals(decButton)) {
            textField.setText(textField.getText().concat("."));
        }

        if (e.getSource().equals(sumButton)) {
            operator = '+';
            String stringBuilder = rightString(textField.getText());
            firstNumber = Double.parseDouble(stringBuilder.toString());
            textField.setText("");

        }

        if (e.getSource().equals(subButton)) {
            operator = '-';
            String stringBuilder = rightString(textField.getText());
            firstNumber = Double.parseDouble(stringBuilder.toString());
            textField.setText("");
        }

        if (e.getSource().equals(mulButton)) {
            operator = '*';
            String stringBuilder = rightString(textField.getText());
            firstNumber = Double.parseDouble(stringBuilder.toString());
            textField.setText("");
        }

        if (e.getSource().equals(divButton)) {
            operator = '/';
            String stringBuilder = rightString(textField.getText());
            firstNumber = Double.parseDouble(stringBuilder.toString());
            textField.setText("");
        }

        if (e.getSource().equals(bracketButton)) {
            try{
            String stringValue = textField.getText();
            if (stringValue.charAt(stringValue.length() - 1) >= '0' && stringValue.charAt(stringValue.length() - 1) <= '9') {
                textField.setText(textField.getText().concat(")"));
            } else {
                textField.setText(textField.getText().concat("("));
            }
            }catch (StringIndexOutOfBoundsException exception) {
                textField.setText(textField.getText().concat("("));
            }
        }

        if (e.getSource().equals(delButton)) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i=0; i<textField.getText().length() - 1; i++) {
                stringBuilder.append(textField.getText().charAt(i));
            }
            textField.setText(stringBuilder.toString());
        }

        if (e.getSource().equals(percentButton)) {
            String stringBuilder = rightString(textField.getText());
            textField.setText(String.valueOf(Double.parseDouble(stringBuilder) * 0.01));
        }

        if (e.getSource().equals(negButton)) {
            String stringBuilder = rightString(textField.getText());
            textField.setText(String.valueOf(Double.parseDouble(stringBuilder.toString()) * (-1.0)));

        }

        if (e.getSource().equals(equButton)) {
            String stringBuilder = rightString(textField.getText());
            secondNumber = Double.parseDouble(stringBuilder.toString());

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

                case '/' -> {
                    try {
                        textField.setText(String.valueOf(firstNumber / secondNumber));
                        firstNumber = firstNumber / secondNumber;
                    } catch (ArithmeticException exception) {
                        textField.setText("XD");
                    }
                }
            }

        }
    }

    public static void main(String[] args) {
        new Calculator();
    }
}
