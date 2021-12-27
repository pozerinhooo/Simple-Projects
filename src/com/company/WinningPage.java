package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WinningPage implements ActionListener {
    private int x;
    private int y;
    private int z;
    protected JFrame frame;
    protected ImageIcon imageIcon;
    protected JLabel label;
    protected JButton returningButton;
    protected Font myFont = new Font("MV Boli", Font.BOLD, 100);

    public WinningPage(int x, int y, int z, char whoWins) {
        this.x = x;
        this.y = y;
        this.z = z;
        imageIcon = new ImageIcon("C:\\Users\\jkobm\\OneDrive\\Pulpit\\m.jpg");
        frame = new JFrame();
        frame.setIconImage(imageIcon.getImage());
        frame.setSize(800,800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        label = new JLabel();
        label.setBounds(0,0,800,800);
        label.setFont(myFont);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.TOP);

        returningButton = new JButton("Login Page");
        returningButton.setFocusable(false);
        returningButton.setBackground(Color.BLUE);
        returningButton.setBounds(100, 100, 600, 200);
        returningButton.setFont(myFont);
        returningButton.addActionListener(this::actionPerformed);

        if (whoWins == 'X') {
            frame.setTitle("X wins");
            frame.getContentPane().setBackground(Color.RED);
            label.setForeground(Color.black);
            label.setText("X wins");

        } else {
            frame.setTitle("O wins");
            frame.getContentPane().setBackground(Color.YELLOW);
            label.setForeground(Color.black);
            label.setText("O wins");
        }
        frame.add(label);
        frame.add(returningButton);
        frame.setLayout(null);
        frame.setVisible(true);

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public JFrame getFrame() {
        return frame;
    }

    public ImageIcon getImageIcon() {
        return imageIcon;
    }

    public JLabel getLabel() {
        return label;
    }

    public Font getMyFont() {
        return myFont;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(returningButton)) {
            frame.dispose();
            LoginPage loginPage = new LoginPage(new DataMap().getLoginAndPasswordDataMap());
        }
    }
}
