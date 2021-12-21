package com.company;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Calculator implements ActionListener {
    JButton[] numberButtons = new JButton[10];
    JButton[] functionsButtons = new JButton[9];
    JButton addButton, subButton, mulButton, divButton;
    JButton decButton, equButton, delButton, clrButton, negButton;
    JPanel panel;
    JTextField textField;
    double num1 = 0, num2 = 0, result = 0;
    char operator;
    public Calculator() {
        Font myFont = new Font("Ink Free", Font.BOLD, 30);
        JFrame frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 550);
        frame.setLayout(null);


        textField = new JTextField();
        textField.setBounds(50, 25, 300, 50);
        textField.setFont(myFont);
        frame.add(textField);



        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("*");
        divButton = new JButton("/");
        decButton = new JButton(".");
        equButton = new JButton("=");
        delButton = new JButton("Del");
        clrButton = new JButton("Cls");
        negButton = new JButton("(-)");
        functionsButtons[0] = addButton;
        functionsButtons[1] = subButton;
        functionsButtons[2] = mulButton;
        functionsButtons[3] = divButton;
        functionsButtons[4] = decButton;
        functionsButtons[5] = equButton;
        functionsButtons[6] = delButton;
        functionsButtons[7] = clrButton;
        functionsButtons[8] = negButton;
        for (JButton jButton : functionsButtons) {
            jButton.addActionListener(this::actionPerformed);
            jButton.setFont(myFont);
            jButton.setFocusable(false);
        }
        for (int i=0; i<numberButtons.length; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this::actionPerformed);
            numberButtons[i].setFont(myFont);
            numberButtons[i].setFocusable(false);
        }
        negButton.setBounds(50,430, 100,50);
        delButton.setBounds(150, 430, 100, 50);
        clrButton.setBounds(250,430,100,50);
        panel = new JPanel();
        panel.setBounds(50, 100, 300, 300);
        panel.setLayout(new GridLayout(4,4,10,10));
        //panel.setBackground(Color.darkGray);
        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(addButton);
        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(subButton);
        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(mulButton);
        panel.add(decButton);
        panel.add(numberButtons[0]);
        panel.add(equButton);
        panel.add(divButton);
        panel.add(negButton);
        panel.add(delButton);
        panel.add(clrButton);




        frame.add(panel);
        frame.add(delButton);
        frame.add(clrButton);
        frame.add(negButton);
        frame.setVisible(true);
        frame.setResizable(false);

    }
    public static void main(String[] args) {
        Calculator calculator = new Calculator();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 10; i++) {
            if (e.getSource() == numberButtons[i]) {
                textField.setText(textField.getText().concat(String.valueOf(i)));
            }
        }
        if (e.getSource() == functionsButtons[4]) {
            textField.setText(textField.getText().concat("."));
        }
        if (e.getSource() == functionsButtons[0]) {
            num1 = Double.parseDouble(textField.getText());
            operator = '+';
            textField.setText("");

        }

        if (e.getSource() == functionsButtons[1]) {
            num1 = Double.parseDouble(textField.getText());
            operator = '-';
            textField.setText("");
        }

        if (e.getSource() == functionsButtons[2]) {
            num1 = Double.parseDouble(textField.getText());
            operator = '*';
            textField.setText("");
        }
        if (e.getSource() == functionsButtons[3]) {
            num1 = Double.parseDouble(textField.getText());
            operator = '/';
            textField.setText("");
        }
        if (e.getSource() == equButton) {
            num2 = Double.parseDouble(textField.getText());
            switch (operator) {
                case '+' -> {
                    textField.setText(String.valueOf(num1 + num2));
                    num2 = num2 + num1;
                }
                case '-' -> {
                    textField.setText(String.valueOf(num1 - num2));
                    num2 = num2 - num1;
                }
                case '*' -> {
                    textField.setText(String.valueOf(num1 * num2));
                    num2 = num1*num2;
                }
                case '/' -> {
                    if (num2 == 0) break;
                    else {
                        textField.setText(String.valueOf(num1 / num2));
                        num2 = num1/num2;
                    }
                }
            }

        }
        if (e.getSource() == clrButton) {
            textField.setText("");
        }
        if (e.getSource() == delButton) {
            String stringValue = textField.getText();
            String newStringValue = "";
            for (int i = 0; i < stringValue.length() - 1; i++) {
                newStringValue += stringValue.charAt(i);
            }
            textField.setText(newStringValue);
        }
        if (e.getSource() == negButton) {
            double newNegValue = Double.parseDouble(textField.getText());
            textField.setText(String.valueOf((-1)*newNegValue)); 
        }
    }
}
