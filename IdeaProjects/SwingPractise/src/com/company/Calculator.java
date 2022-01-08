package com.company;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Calculator implements ActionListener {
    JFrame frame;
    JButton addButton, subButton, mulButton, divButton;
    JButton decButton, equButton, clearScreenButton, delButton, negButton, sqrtButton, quitButton;
    JPanel jPanel;
    JButton[] buttonsNumber = new JButton[10];
    JButton[] functionsButtons = new JButton[11];
    Font myFont = new Font("Ink Free", Font.BOLD, 30);

    JTextField jTextField;
    double firstNumber = 0, secondNumber = 0;
    char operator;

    public Calculator() {
        frame = new JFrame("Calculator");
        frame.setIconImage(new ImageIcon("C:\\Users\\jkobm\\OneDrive\\Dokumenty\\calc.jpg").getImage());
        frame.setSize(420, 550);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.BLACK);

        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("*");
        divButton = new JButton("/");
        decButton = new JButton(",");
        equButton = new JButton("=");
        clearScreenButton = new JButton("Clear");
        delButton = new JButton("Delete");
        negButton = new JButton("(-)");
        sqrtButton = new JButton("Sq");
        quitButton = new JButton("Quit");

        functionsButtons[0] = addButton;
        functionsButtons[1] = subButton;
        functionsButtons[2] = mulButton;
        functionsButtons[3] = divButton;
        functionsButtons[4] = decButton;
        functionsButtons[5] = equButton;
        functionsButtons[6] = clearScreenButton;
        functionsButtons[7] = delButton;
        functionsButtons[8] = negButton;
        functionsButtons[9] = sqrtButton;
        functionsButtons[10] = quitButton;

        delButton.setBounds(50, 430, 145, 25);
        clearScreenButton.setBounds(205, 430, 145, 25);
        negButton.setBounds(50, 460, 145, 25);
        quitButton.setBounds(205, 460, 145, 25);

        for (int i = 0; i < functionsButtons.length; i++) {
            functionsButtons[i].setFont(myFont);
            functionsButtons[i].addActionListener(this);
            functionsButtons[i].setFocusable(false);
        }
        for (int i = 0; i < buttonsNumber.length; i++) {
            buttonsNumber[i] = new JButton(String.valueOf(i));
            buttonsNumber[i].addActionListener(this);
            buttonsNumber[i].setFont(myFont);
            buttonsNumber[i].setFocusable(false);
        }

        jPanel = new JPanel();
        jPanel.setBounds(50, 100, 300, 300);
//        jPanel.setBackground(Color.GRAY);
        jPanel.setLayout(new GridLayout(5, 5, 10, 10));
        jPanel.add(buttonsNumber[1]);
        jPanel.add(buttonsNumber[2]);
        jPanel.add(buttonsNumber[3]);
        jPanel.add(addButton);
        jPanel.add(buttonsNumber[4]);
        jPanel.add(buttonsNumber[5]);
        jPanel.add(buttonsNumber[6]);
        jPanel.add(subButton);
        jPanel.add(buttonsNumber[7]);
        jPanel.add(buttonsNumber[8]);
        jPanel.add(buttonsNumber[9]);
        jPanel.add(mulButton);
        jPanel.add(decButton);
        jPanel.add(buttonsNumber[0]);
        jPanel.add(equButton);
        jPanel.add(divButton);
        jPanel.add(sqrtButton);


        jTextField = new JTextField();
        jTextField.setFont(myFont);
        jTextField.setBounds(50, 25, 300, 50);
        frame.add(jTextField);
        frame.add(delButton);
        frame.add(clearScreenButton);
        frame.add(negButton);
        frame.add(quitButton);
        frame.add(jPanel);
        frame.setVisible(true);

    }

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 10; i++) {
            if (e.getSource() == buttonsNumber[i]) {
                jTextField.setText(jTextField.getText().concat(String.valueOf(i)));
            }
        }
        if (e.getSource() == decButton) {
            jTextField.setText(jTextField.getText().concat("."));
        }

        if (e.getSource() == addButton) {
            firstNumber = Double.parseDouble(jTextField.getText());
            operator = '+';
            jTextField.setText("");
        }

        if (e.getSource() == subButton) {
            firstNumber = Double.parseDouble(jTextField.getText());
            operator = '-';
            jTextField.setText("");
        }

        if (e.getSource() == mulButton) {
            firstNumber = Double.parseDouble(jTextField.getText());
            operator = '*';
            jTextField.setText("");
        }
        if (e.getSource() == divButton) {
            firstNumber = Double.parseDouble(jTextField.getText());
            operator = '/';
            jTextField.setText("");
        }
        if (e.getSource() == equButton) {
            secondNumber = Double.parseDouble(jTextField.getText());
            switch (operator) {
                case '+' -> {
                    jTextField.setText(String.valueOf(firstNumber + secondNumber));
                    firstNumber = firstNumber + secondNumber;
                }
                case '-' -> {
                    jTextField.setText(String.valueOf(firstNumber - secondNumber));
                    firstNumber = firstNumber - secondNumber;
                }
                case '*' -> {
                    jTextField.setText(String.valueOf(firstNumber * secondNumber));
                    firstNumber = firstNumber * secondNumber;
                }
                case '/' -> {
                    if (secondNumber == 0) System.exit(0);
                    else {
                        jTextField.setText(String.valueOf(firstNumber / secondNumber));
                        firstNumber = firstNumber / secondNumber;
                    }
                }

            }
        }
        if (e.getSource() == delButton) {
            String currentStringValue = jTextField.getText();
            StringBuilder stringBuilder = new StringBuilder();
            for (int i=0; i<currentStringValue.length() - 1; i++) {
                stringBuilder.append(String.valueOf(currentStringValue.charAt(i))).toString();
            }
            jTextField.setText(stringBuilder.toString());
        }
        if (e.getSource() == clearScreenButton) {
            jTextField.setText("");
        }
        if (e.getSource() == quitButton) {
            System.exit(0);
        }
        if (e.getSource() == sqrtButton) {
            jTextField.setText(String.valueOf((double) Math.sqrt(firstNumber)));
        }
    }
}
