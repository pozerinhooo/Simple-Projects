package com.company;

import javax.swing.*;
import javax.swing.plaf.BorderUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Timer implements ActionListener {
    private JFrame frame;
    private JLabel panel;
    private JButton startButton;
    private JButton stopButton;
    private JButton resetButton;
    private int seconds = 0;
    private int minutes = 0;
    private int hours = 0;
    private int elapsedTime = 0;
    private boolean started = true;
    private javax.swing.Timer timer;
    public Timer() {
        frame = new JFrame("Timer");
        frame.setIconImage(new ImageIcon("C:\\Users\\jkobm\\OneDrive\\Pulpit\\timer.jpg").getImage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.getContentPane().setBackground(Color.GRAY);
        frame.setLayout(null);

        panel = new JLabel();
        panel.setBounds(100, 50, 402, 100);
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        panel.setBackground(Color.CYAN);
        panel.setForeground(Color.GREEN);
        panel.setFont(new Font("Ink Free", Font.BOLD, 60));
        panel.setText("00:00:00");

        startButton = new JButton("START");
        startButton.setBounds(100, 150, 134, 50);
        startButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        startButton.setFont(new Font("Ink Free", Font.PLAIN, 30));
        startButton.setForeground(Color.BLACK);
        startButton.setBackground(Color.GRAY);
        startButton.addActionListener(this::actionPerformed);
        startButton.setFocusable(false);

        stopButton = new JButton("STOP");
        stopButton.setBounds(100 + 134, 150, 134, 50);
        stopButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        stopButton.setFont(new Font("Ink Free", Font.PLAIN, 30));
        stopButton.setForeground(Color.BLACK);
        stopButton.setBackground(Color.GRAY);
        stopButton.addActionListener(this::actionPerformed);
        stopButton.setFocusable(false);

        resetButton = new JButton("RESET");
        resetButton.setBounds(100 + 134 + 134, 150, 134, 50);
        resetButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        resetButton.setFont(new Font("Ink Free", Font.PLAIN, 30));
        resetButton.setForeground(Color.BLACK);
        resetButton.setBackground(Color.GRAY);
        resetButton.addActionListener(this::actionPerformed);
        resetButton.setFocusable(false);

//        panel.setVerticalTextPosition(SwingConstants.CENTER);
        panel.setHorizontalAlignment(SwingConstants.CENTER);

        timer = new javax.swing.Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                elapsedTime += 1000;
                hours = (elapsedTime / 3600000) % 60;
                minutes = (elapsedTime / 60000) % 60;
                seconds = (elapsedTime / 1000) % 60;
                panel.setText(String.format("%02d", hours ) + " : " + String.format("%02d", minutes ) + " : " + String.format("%02d", seconds ));
            }
        });

        frame.add(panel);
        frame.add(startButton);
        frame.add(stopButton);
        frame.add(resetButton);
        frame.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(startButton)) {
            start();
        }

        if (e.getSource().equals(stopButton)) {
            stop();
        }

        if (e.getSource().equals(resetButton)) {
            reset();
        }
    }

    private void start() {
        timer.start();
    }
    private void stop() {
        timer.stop();
    }
    private void reset() {
        timer.stop();
        elapsedTime = 0;
        minutes = 0;
        seconds = 0;
        hours = 0;
        panel.setText(String.format("%02d", hours ) + " : " + String.format("%02d", minutes ) + " : " + String.format("%02d", seconds ));
        timer.start();
    }

    public javax.swing.Timer getTimer() {
        return timer;
    }

    public static void main(String[] args) {
        new Timer();
    }
}
