import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.Map;

public class Calculator implements ActionListener, MouseListener {
    private final Customer customer; // add final
    private  final DataMap dataMap; // add fina;
    private  final Map<String, String> passwordsAndLogins; // add final
    private JFrame frame;
    private JTextField textField;
    private JButton[] numberButtons = new JButton[10];
    private JButton[] funButtons = new JButton[10];
    private JButton sumButton;
    private JButton subButton;
    private JButton mulButton;
    private JButton divButton;
    private JButton equButton;
    private JButton decButton;
    private JButton percentButton;
    private JButton delButton;
    private JButton resetButton;
    private JButton negButton;
    private BackButton backButton;
    private ImageIcon imageIcon  = new ImageIcon("C:\\Users\\jkobm\\OneDrive\\Pulpit\\SIMPLE JAVA SWING PROJECT DATA\\kalk.jpg");
    private JPanel buttonsPanel;
    private Font mvBoliFont = new Font("MV Boli", Font.BOLD, 20);
    private Font textFieldFont = new Font("Arial", Font.BOLD, 30);
    private Border border = BorderFactory.createLineBorder(Color.BLACK, 3);

    private char operator;
    private double firstValue;
    private double secondValue;




    public Calculator(Customer customer, DataMap dataMap, Map<String, String> passwordsAndLogins){
        this.customer = customer;
        this.dataMap = dataMap;
        this.passwordsAndLogins = passwordsAndLogins;
        this.backButton = new BackButton();
        backButton.setBounds(10, 5, 80, 30);
        backButton.addActionListener(this::actionPerformed);
        backButton.addMouseListener(this);

        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setIconImage(imageIcon.getImage());
        frame.setLayout(null);
        frame.setSize(new Dimension(400, 640));
        frame.getContentPane().setBackground(Color.GRAY);

        textField = new JTextField();
        textField.setFont(textFieldFont);
        textField.setBorder(border);
        textField.setBackground(Color.LIGHT_GRAY);
        textField.setForeground(Color.BLACK);
        textField.addMouseListener(this);
        textField.setFocusable(false);
        textField.setBounds(20, 50, 320, 50);

        buttonsPanel = new JPanel(new GridLayout(5, 4, 10 ,10));
        buttonsPanel.setBounds(0, 150, 385, 450);
        buttonsPanel.setBackground(Color.GRAY);

        sumButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("*");
        divButton = new JButton("/");
        equButton = new JButton("=");
        decButton = new JButton(".");
        percentButton = new JButton("%");
        delButton = new JButton("Del");
        resetButton = new JButton("Res");
        negButton = new JButton("(-)");

        funButtons[0] = sumButton;
        funButtons[1] = subButton;
        funButtons[2] = mulButton;
        funButtons[3] = divButton;
        funButtons[4] = equButton;
        funButtons[5] = decButton;
        funButtons[6] = percentButton;
        funButtons[7] = delButton;
        funButtons[8] = resetButton;
        funButtons[9] = negButton;

        for (int i=0; i < funButtons.length; i++) {
            funButtons[i].setBorder(border);
            funButtons[i].addActionListener(this::actionPerformed);
            funButtons[i].addMouseListener(this);
            funButtons[i].setFont(mvBoliFont);
            funButtons[i].setFocusable(false);
            funButtons[i].setBackground(Color.GRAY);
            funButtons[i].setForeground(Color.BLACK);
        }
        for (int i=0; i<numberButtons.length; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].setBorder(border);
            numberButtons[i].addActionListener(this::actionPerformed);
            numberButtons[i].addMouseListener(this);
            numberButtons[i].setFont(mvBoliFont);
            numberButtons[i].setFocusable(false);
            numberButtons[i].setBackground(Color.GRAY);
            numberButtons[i].setForeground(Color.BLACK);
        }
        buttonsPanel.add(resetButton);
        buttonsPanel.add(delButton);
        buttonsPanel.add(percentButton);
        buttonsPanel.add(divButton);
        buttonsPanel.add(numberButtons[7]);
        buttonsPanel.add(numberButtons[8]);
        buttonsPanel.add(numberButtons[9]);
        buttonsPanel.add(mulButton);
        buttonsPanel.add(numberButtons[4]);
        buttonsPanel.add(numberButtons[5]);
        buttonsPanel.add(numberButtons[6]);
        buttonsPanel.add(subButton);
        buttonsPanel.add(numberButtons[1]);
        buttonsPanel.add(numberButtons[2]);
        buttonsPanel.add(numberButtons[3]);
        buttonsPanel.add(sumButton);
        buttonsPanel.add(negButton);
        buttonsPanel.add(numberButtons[0]);
        buttonsPanel.add(decButton);
        buttonsPanel.add(equButton);

        frame.add(backButton);
        frame.add(buttonsPanel);
        frame.add(textField);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(backButton)) {
            frame.dispose();
            new MakeATransactionPage(customer, dataMap, passwordsAndLogins);
        }

        for (int i=0; i<numberButtons.length; i++) {
            if (e.getSource().equals(numberButtons[i])) {
                textField.setText(textField.getText().concat(numberButtons[i].getText()));
            }
        }

        if (e.getSource().equals(sumButton)) {
            try {
                firstValue = Double.parseDouble(textField.getText());
                operator = '+';
                textField.setText("");
            } catch (NumberFormatException ex) {
                ex.printStackTrace();
            }
        }

        if (e.getSource().equals(subButton)) {
            try {
                firstValue = Double.parseDouble(textField.getText());
                operator = '-';
                textField.setText("");
            } catch (NumberFormatException ex) {
                ex.printStackTrace();
            }
        }

        if (e.getSource().equals(mulButton)) {
            try {
                firstValue = Double.parseDouble(textField.getText());
                operator = '*';
                textField.setText("");
            } catch (NumberFormatException ex) {
                ex.printStackTrace();
            }
        }

        if (e.getSource().equals(divButton)) {
            try {
                firstValue = Double.parseDouble(textField.getText());
                operator = '/';
                textField.setText("");
            } catch (NumberFormatException ex) {
                ex.printStackTrace();
            }
        }

        if (e.getSource().equals(equButton)) {
            try {
                secondValue = Double.parseDouble(textField.getText());
            } catch (NumberFormatException numberFormatException) {
                numberFormatException.printStackTrace();
            }

            switch (operator) {
                case '+' -> {
                    textField.setText(String.valueOf(firstValue + secondValue));
                    firstValue = firstValue + secondValue;
                }

                case '-' -> {
                    textField.setText(String.valueOf(firstValue - secondValue));
                    firstValue = firstValue - secondValue;
                }

                case '*' -> {
                    textField.setText(String.valueOf(firstValue * secondValue));
                    firstValue = firstValue * secondValue;
                }

                case '/' -> {
                    try {
                        textField.setText(String.valueOf(firstValue / secondValue));
                        firstValue = firstValue / secondValue;
                    } catch (NumberFormatException numberFormatException) {
                        numberFormatException.printStackTrace();
                        textField.setText("Error");
                        firstValue = 0;
                        secondValue = 0;
                    }
                }
            }
        }

        if (e.getSource().equals(decButton)) {
            StringBuilder stringBuilder = new StringBuilder();
            boolean isValid = true;
            for (int i=0; i < textField.getText().length(); i++) {
                if (textField.getText().charAt(i) == '.') {
                    isValid = false;
                }
            }
            if (isValid) {
                textField.setText(textField.getText().concat("."));
            }

        }

        if (e.getSource().equals(percentButton)) {
            textField.setText(String.valueOf(Double.parseDouble(textField.getText()) * 0.01));
        }

        if (e.getSource().equals(delButton)) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i=0; i < (textField.getText().length() - 1); i++) {
                stringBuilder.append(String.valueOf(textField.getText().charAt(i)));
            }
            textField.setText(stringBuilder.toString());
        }

        if (e.getSource().equals(resetButton)) {
            textField.setText("");
        }

        if (e.getSource().equals(negButton)) {
            textField.setText(String.valueOf(Double.parseDouble(textField.getText()) * (-1)));
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

                if (e.getSource().equals(textField)) {
                   textField.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
                   textField.setForeground(Color.GREEN);
                }

                for (int i=0; i<funButtons.length; i++) {
                    if (e.getSource().equals(funButtons[i])) {
                        funButtons[i].setForeground(Color.GREEN);
                        funButtons[i].setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
                    }
                }

                for (int j=0; j<numberButtons.length; j++) {
                    if (e.getSource().equals(numberButtons[j])){
                        numberButtons[j].setForeground(Color.GREEN);
                        numberButtons[j].setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
                    }
                }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource().equals(textField)) {
            textField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
            textField.setForeground(Color.BLACK);
        }

        for (int i=0; i<funButtons.length; i++) {
            if (e.getSource().equals(funButtons[i])) {
                funButtons[i].setForeground(Color.BLACK);
                funButtons[i].setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
            }
        }

        for (int j=0; j<numberButtons.length; j++) {
            if (e.getSource().equals(numberButtons[j])){
                numberButtons[j].setForeground(Color.BLACK);
                numberButtons[j].setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
            }
        }
    }
}
