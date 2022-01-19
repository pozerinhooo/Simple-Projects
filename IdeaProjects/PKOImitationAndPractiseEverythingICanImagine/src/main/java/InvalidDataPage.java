import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.ref.PhantomReference;
import java.util.Map;

public class InvalidDataPage implements ActionListener {
    private JFrame frame;
    private JLabel label;
    private ImageIcon imageIcon = new ImageIcon("C:\\Users\\jkobm\\OneDrive\\Pulpit\\SIMPLE JAVA SWING PROJECT DATA\\pko.jpg");
    private JButton backButton;
    private Map<String, String> passwordAndLogins;
    private DataMap dataMap;
    private Font myFont = new Font("Ink Free", Font.BOLD, 80);
    public InvalidDataPage(DataMap dataMap, Map<String, String> passwordAndLogins) {
        this.passwordAndLogins = passwordAndLogins;
        this.dataMap = dataMap;

        this.backButton = new BackButton();
        backButton.setBounds(600, 200, 100 ,100);
        backButton.addActionListener(this::actionPerformed);

        frame = new JFrame("Block");
        frame.setIconImage(imageIcon.getImage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.BLACK);
        frame.setSize(new Dimension(800, 800));

        label = new JLabel("3 Times --> Block");
        label.setFont(myFont);
        label.setForeground(Color.RED);
        label.setBounds(50, 200, 700, 400);

        frame.add(backButton);
        frame.add(label);
        frame.setLayout(null);
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(backButton)) {
            frame.dispose();
            new LoginPage(dataMap, passwordAndLogins);


        }
    }
}
