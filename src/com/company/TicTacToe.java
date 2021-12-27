package com.company;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToe implements ActionListener, PossibleEnds {
    private Font myFont = new Font("Dialog", Font.BOLD, 50);
    private ImageIcon imageIcon = new ImageIcon("C:\\Users\\jkobm\\OneDrive\\Pulpit\\malik.jpg");
    private Border border = BorderFactory.createLineBorder(Color.BLACK, 5);
    private JFrame frame;
    private JTextField textField;
    private JButton[] buttons;
    private JPanel panel;
    private boolean isXTurn = true;

    public TicTacToe() {
        textField = new JTextField();
        textField.setFont(myFont);
        textField.setBorder(border);
        textField.setBounds(0, 0, 785, 150);
        textField.setHorizontalAlignment(JTextField.CENTER);
        textField.setFocusable(false);
        textField.setBackground(Color.GRAY);
        textField.setText("Tic-Tac-Toe Game X - FIRST");
        textField.setForeground(Color.RED);
        panel = new JPanel(new GridLayout(3, 3, 10, 10));
        panel.setBounds(5, 150, 780, 600);
        panel.setBackground(Color.RED);
        buttons = new JButton[9];
        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            buttons[i].setFont(myFont);
            buttons[i].setBackground(Color.DARK_GRAY);
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this::actionPerformed);
            panel.add(buttons[i]);
        }


        frame = new JFrame("Tic-Tac-Toe Game");
        frame.setIconImage(imageIcon.getImage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.add(textField);
        frame.add(panel);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    @Override
    public void check() {

        boolean isEveryPlaceTaken = true;
        for (int i=0; i<buttons.length; i++) {
            if (buttons[i].getText().isEmpty()) {
                isEveryPlaceTaken = false;
            }
        }
        if (isEveryPlaceTaken) {
            drawNoOneWins();
        }

        if (
                        buttons[0].getText().equals("X") &&
                        buttons[1].getText().equals("X") &&
                        buttons[2].getText().equals("X")
        ) xWins(0, 1, 2);

        if (
                        buttons[3].getText().equals("X") &&
                        buttons[4].getText().equals("X") &&
                        buttons[5].getText().equals("X")
        ) xWins(3, 4, 5);

        if (
                        buttons[6].getText().equals("X") &&
                        buttons[7].getText().equals("X") &&
                        buttons[8].getText().equals("X")
        ) xWins(6, 7, 8);

        if (
                        buttons[0].getText().equals("X") &&
                        buttons[3].getText().equals("X") &&
                        buttons[6].getText().equals("X")
        ) xWins(0, 3, 6);

        if (
                        buttons[1].getText().equals("X") &&
                        buttons[4].getText().equals("X") &&
                        buttons[7].getText().equals("X")
        ) xWins(1, 4, 7);

        if (
                        buttons[2].getText().equals("X") &&
                        buttons[5].getText().equals("X") &&
                        buttons[8].getText().equals("X")
        ) xWins(2, 5, 8);

        if (
                        buttons[0].getText().equals("X") &&
                        buttons[4].getText().equals("X") &&
                        buttons[8].getText().equals("X")
        ) xWins(0, 4, 8);

        if (
                        buttons[2].getText().equals("X") &&
                        buttons[4].getText().equals("X") &&
                        buttons[6].getText().equals("X")
        ) xWins(2, 4, 6);

        if (
                        buttons[0].getText().equals("O") &&
                        buttons[1].getText().equals("O") &&
                        buttons[2].getText().equals("O")
        ) oWins(0, 1, 2);

        if (
                        buttons[3].getText().equals("O") &&
                        buttons[4].getText().equals("O") &&
                        buttons[5].getText().equals("O")
        ) oWins(3, 4, 5);

        if (
                        buttons[6].getText().equals("O") &&
                        buttons[7].getText().equals("O") &&
                        buttons[8].getText().equals("O")
        ) oWins(6, 7, 8);

        if (
                        buttons[0].getText().equals("O") &&
                        buttons[3].getText().equals("O") &&
                        buttons[6].getText().equals("O")
        ) oWins(0, 3, 6);

        if (
                        buttons[1].getText().equals("O") &&
                        buttons[4].getText().equals("O") &&
                        buttons[7].getText().equals("O")
        ) oWins(1, 4, 7);

        if (
                        buttons[2].getText().equals("O") &&
                        buttons[5].getText().equals("O") &&
                        buttons[8].getText().equals("O")
        ) oWins(2, 5, 8);

        if (
                        buttons[0].getText().equals("O") &&
                        buttons[4].getText().equals("O") &&
                        buttons[8].getText().equals("O")
        ) oWins(0, 4, 8);

        if (
                        buttons[2].getText().equals("O") &&
                        buttons[4].getText().equals("O") &&
                        buttons[6].getText().equals("O")
        ) oWins(2, 4, 6);

    }

    @Override
    public void xWins(int x, int y, int z) {
        frame.dispose();
        WinningPage winningPage = new WinningPage(x, y, z, 'X');
    }

    @Override
    public void oWins(int x, int y, int z) {
        frame.dispose();
       WinningPage winningPage = new WinningPage(x, y, z, 'O');

    }

    @Override
    public void drawNoOneWins() {
        frame.dispose();
        System.out.println("DRAW");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        for (int i = 0; i < this.buttons.length; i++) {

            if (e.getSource().equals(buttons[i])) {
                if (buttons[i].getText().equals("")) {
                    if (isXTurn) {
                        panel.setBackground(Color.YELLOW);
                        buttons[i].setText("X");
                        textField.setForeground(Color.YELLOW);
                        buttons[i].setForeground(Color.RED);
                        textField.setText("O - Turn");
                        isXTurn = false;
                    } else {
                        panel.setBackground(Color.RED);
                        buttons[i].setText("O");
                        textField.setForeground(Color.RED);
                        buttons[i].setForeground(Color.YELLOW);
                        textField.setText("X - Turn");
                        isXTurn = true;
                    }
                }
            }
        }
        check();
    }

    public static void main(String[] args) {
        TicTacToe ticTacToe = new TicTacToe();

    }

}
