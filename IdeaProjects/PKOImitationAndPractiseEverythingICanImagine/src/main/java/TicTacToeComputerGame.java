import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Map;

public class TicTacToeComputerGame implements ActionListener, MouseListener, ITicTacToeGame {
    private Customer customer;
    private DataMap dataMap;
    private Map<String, String> passwordsAndLogins;
    private boolean isXTurn = true; // X is player , O - is computer
    private JFrame frame;
    private JPanel fieldsPanel;
    private JLabel messageLabel;
    private JButton[] fieldButtons = new JButton[9];
    private Font fieldsFont = new Font("MV Boli", Font.BOLD, 100);
    private Font messageLabelFont = new Font("MV Boli", Font.BOLD, 40);
    private ImageIcon imageIcon = new ImageIcon("C:\\Users\\jkobm\\OneDrive\\Pulpit\\SIMPLE JAVA SWING PROJECT DATA\\tic.jpg");
    private JButton changeModButton;
    private BackButton backButton;
    public TicTacToeComputerGame() {
        frame = new JFrame("Tic tac toe game");
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setIconImage(imageIcon.getImage());
        frame.getContentPane().setBackground(Color.GRAY);
        frame.setSize(new Dimension(1010, 810));

        backButton = new BackButton();
        backButton.setBounds(0, 50, 100 ,50);
        backButton.addMouseListener(this);
        backButton.addActionListener(this::actionPerformed);

        changeModButton = new JButton("Against computer");
        changeModButton.addActionListener(this::actionPerformed);
        changeModButton.addMouseListener(this);
        changeModButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
        changeModButton.setFont(new Font("MV Boli", Font.BOLD, 20));
        changeModButton.setForeground(Color.BLACK);
        changeModButton.setBackground(Color.lightGray);
        changeModButton.setFocusable(false);
        changeModButton.setBounds(105, 50, 200, 50);

        messageLabel = new JLabel("X - turn");
        messageLabel.setFont(messageLabelFont);
        messageLabel.setForeground(Color.RED);
        messageLabel.setBounds(0, 0, 987, 100);
        messageLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);

        fieldsPanel = new JPanel(new GridLayout(3 ,3 ,10, 10));
        fieldsPanel.setBounds(0, 100, 987 ,670);

        for (int i=0; i < fieldButtons.length; i++) {
            fieldButtons[i] = new JButton();
            fieldButtons[i].setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
            fieldButtons[i].setFocusable(false);
            fieldButtons[i].setBackground(Color.LIGHT_GRAY);
            fieldButtons[i].addActionListener(this::actionPerformed);
            fieldButtons[i].addMouseListener(this);
            fieldButtons[i].setFont(fieldsFont);
            fieldsPanel.add(fieldButtons[i]);
        }

        frame.add(backButton);
        frame.add(messageLabel);
        frame.add(fieldsPanel);
        frame.add(changeModButton);
        frame.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        check();
        if (e.getSource().equals(backButton)) {
            frame.dispose();
            new MainValidPage(customer, dataMap, passwordsAndLogins);
        }

        if (e.getSource().equals(changeModButton)) {
            frame.dispose();
            new TicTacToeGame(customer, dataMap, passwordsAndLogins);
        }

        for (int i=0; i<fieldButtons.length; i++) {
            if (e.getSource().equals(fieldButtons[i])) {
                if (fieldButtons[i].getText().isEmpty()) {
                    if (isXTurn) {
                        fieldButtons[i].setText("X");
                        fieldButtons[i].setForeground(Color.RED);
                        fieldButtons[i].setBorder(BorderFactory.createLineBorder(Color.RED, 5));
                        messageLabel.setText("O - turn");
                        messageLabel.setForeground(Color.YELLOW);
                        fieldButtons[i].removeMouseListener(this);
                        isXTurn = false;
                    }
                }
            }
        }

        check();
        computerMark();

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
        for (int i=0; i<fieldButtons.length; i++) {
            if (e.getSource().equals(fieldButtons[i])) {
                fieldButtons[i].setBorder(BorderFactory.createLineBorder(Color.GREEN, 5));
            }
        }

        if (e.getSource().equals(changeModButton)) {
            changeModButton.setForeground(Color.GREEN);
            changeModButton.setBorder(BorderFactory.createLineBorder(Color.GREEN, 5));

        }

    }

    @Override
    public void mouseExited(MouseEvent e) {
        for (int i=0; i<fieldButtons.length; i++) {
            if (e.getSource().equals(fieldButtons[i])) {
                fieldButtons[i].setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
            }
        }

        if (e.getSource().equals(changeModButton)) {
            changeModButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
            changeModButton.setForeground(Color.BLACK);
        }
    }

    @Override
    public int check() {
        boolean shouldBeDraw = true;
        if (
                fieldButtons[0].getText().equals("X") &&
                        fieldButtons[1].getText().equals("X") &&
                        fieldButtons[2].getText().equals("X")
        ) {
            xWins(0, 1, 2);
            return 10;
        }

        if (
                fieldButtons[3].getText().equals("X") &&
                        fieldButtons[4].getText().equals("X") &&
                        fieldButtons[5].getText().equals("X")
        ) {
            xWins(3, 4, 5);
            return 10;
        }

        if (
                fieldButtons[6].getText().equals("X") &&
                        fieldButtons[7].getText().equals("X") &&
                        fieldButtons[8].getText().equals("X")
        ) {
            xWins(6, 7, 8);
            return 10;
        }

        if (
                fieldButtons[0].getText().equals("X") &&
                        fieldButtons[3].getText().equals("X") &&
                        fieldButtons[6].getText().equals("X")
        ) {
            xWins(0, 3, 6);
            return 10;
        }

        if (
                fieldButtons[1].getText().equals("X") &&
                        fieldButtons[4].getText().equals("X") &&
                        fieldButtons[7].getText().equals("X")
        ) {
            xWins(1, 4, 7);
            return 10;
        }

        if (
                fieldButtons[2].getText().equals("X") &&
                        fieldButtons[5].getText().equals("X") &&
                        fieldButtons[8].getText().equals("X")
        ) {
            xWins(2, 5, 8);
            return 10;
        }

        if (
                fieldButtons[0].getText().equals("X") &&
                        fieldButtons[4].getText().equals("X") &&
                        fieldButtons[8].getText().equals("X")
        ) {
            xWins(0, 4, 8);
            return 10;
        }

        if (
                fieldButtons[2].getText().equals("X") &&
                        fieldButtons[3].getText().equals("X") &&
                        fieldButtons[6].getText().equals("X")
        ) {
            xWins(2, 4, 6);
            return 10;
        }

        if (
                fieldButtons[0].getText().equals("O") &&
                        fieldButtons[1].getText().equals("O") &&
                        fieldButtons[2].getText().equals("O")
        ) {
            yWins(0, 1, 2);
            return -10;
        }

        if (
                fieldButtons[3].getText().equals("O") &&
                        fieldButtons[4].getText().equals("O") &&
                        fieldButtons[5].getText().equals("O")
        ) {
            yWins(3, 4, 5);
            return -10;
        }

        if (
                fieldButtons[6].getText().equals("O") &&
                        fieldButtons[7].getText().equals("O") &&
                        fieldButtons[8].getText().equals("O")
        ) {
            yWins(6, 7, 8);
            return -10;
        }

        if (
                fieldButtons[0].getText().equals("O") &&
                        fieldButtons[3].getText().equals("O") &&
                        fieldButtons[6].getText().equals("O")
        ) {
            yWins(0, 3, 6);
            return -10;
        }

        if (
                fieldButtons[1].getText().equals("O") &&
                        fieldButtons[4].getText().equals("O") &&
                        fieldButtons[7].getText().equals("O")
        ) {
            yWins(1, 4, 7);
            return -10;
        }

        if (
                fieldButtons[2].getText().equals("O") &&
                        fieldButtons[5].getText().equals("O") &&
                        fieldButtons[8].getText().equals("O")
        ) {
            yWins(2, 5, 8);
            return -10;
        }

        if (
                fieldButtons[0].getText().equals("O") &&
                        fieldButtons[4].getText().equals("O") &&
                        fieldButtons[8].getText().equals("O")
        ) {
            yWins(0, 4, 8);
            return -10;
        }

        if (
                fieldButtons[2].getText().equals("O") &&
                        fieldButtons[3].getText().equals("O") &&
                        fieldButtons[6].getText().equals("O")
        ) {
            yWins(2, 4, 6);
            return -10;
        }

        else {
            for (int i=0; i < fieldButtons.length; i++) {
                if (fieldButtons[i].getText().isEmpty()) {
                    shouldBeDraw = false;
                }
            }
            if (shouldBeDraw) {
                draw();
                return 0;
            }
        }
        System.out.println("pocz");
        return 0;
    }

    @Override
    public int[] xWins(int x, int y, int z) {
        int[] winningCoordinates = new int[3];
        winningCoordinates[0] = x;
        winningCoordinates[1] = y;
        winningCoordinates[2] = z;
        System.out.println("X WINS WITH --> " + x + " " + y + " " + z);
        frame.dispose();
        return winningCoordinates;
    }

    @Override
    public int[] yWins(int x, int y, int z) {
        int[] winningCoordinates = new int[3];
        winningCoordinates[0] = x;
        winningCoordinates[1] = y;
        winningCoordinates[2] = z;
        System.out.println("Y WINS WITH --> " + x + " " + y + " " + z);
        frame.dispose();
        return winningCoordinates;
    }
    private int minimax(int depth, boolean isMax) {
        int score = check();
        if (score == 10) {
            return score;
        }
        if (score == -10) {
            return score;
        }
        if (!isMovesLeft()) {
            return 0;
        }

        if (isMax) {
            int bestScore = Integer.MIN_VALUE;

            for (int i=0; i < fieldButtons.length; i++) {
                if (fieldButtons[i].getText().isEmpty()) {
                    fieldButtons[i].setText("O");
                    bestScore = Math.max(bestScore, minimax(depth + 1, !isMax));
                    fieldButtons[i].setText("");
                }
            }
            return bestScore;
        } else {
            int bestScore2 = Integer.MAX_VALUE;

            for (int i=0; i < fieldButtons.length; i++) {
                if (fieldButtons[i].getText().isEmpty()) {
                    fieldButtons[i].setText("X");
                    bestScore2 = Math.min(bestScore2, minimax(depth + 1, !isMax));
                    fieldButtons[i].setText("");
                }
            }
            return bestScore2;
        }
    }
    private boolean isMovesLeft() {
        for (int i=0; i < fieldButtons.length; i++) {
            if (fieldButtons[i].getText().isEmpty()) {
                return true;
            }
        }
        return false;
    }
    private int bestMove() {
        int bestVal = -1000;
        int score = 0;
        for (int i=0; i < fieldButtons.length; i++) {
            if (fieldButtons[i].getText().isEmpty()) {
                fieldButtons[i].setText("O");
                 score = minimax(0, true);
                fieldButtons[i].setText("");
                if (score > bestVal) {
                    bestVal = score;
                }
            }

        }
        return bestVal;
    }

    public void computerMark() {
        if (!isXTurn) {
            // TUTAJ

            int x = bestMove();
            System.out.println(x);
            if (fieldButtons[x].getText().isEmpty()){
            fieldButtons[x].setText("O");
            fieldButtons[x].setForeground(Color.YELLOW);
            fieldButtons[x].setBorder(BorderFactory.createLineBorder(Color.YELLOW, 5));
            messageLabel.setText("X - turn");
            messageLabel.setForeground(Color.RED);
            fieldButtons[x].removeMouseListener(this);
            isXTurn = true;
        }
        }
    }

    @Override
    public void draw() {
        frame.dispose();
        System.out.println("DRAW");
    }

    public static void main(String[] args) {
        new TicTacToeComputerGame();
    }


}


