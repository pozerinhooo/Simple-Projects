import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BackButton extends JButton implements ActionListener, MouseListener {
    public BackButton() {
        this.setText("Back");
        this.setBounds(600, 350, 100, 50);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        this.setFont(new Font("MV Boli", Font.BOLD, 30));
        this.setFocusable(false);
        this.setBackground(Color.DARK_GRAY);
        this.setForeground(Color.BLUE);
        this.addActionListener(this::actionPerformed);
        this.addMouseListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

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
        if (e.getSource().equals(this)) {
            this.setForeground(Color.CYAN);
            this.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource().equals(this)) {
            this.setForeground(Color.BLUE);
            this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        }
    }
}
