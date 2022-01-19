import netscape.javascript.JSObject;
import org.jsoup.nodes.Document;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.util.*;
import java.util.List;

public class SeePossibleBranches implements MouseListener, ActionListener {

    private JFrame frame;
    private JLabel mainLabel;
    private Font myFont = new Font("MV Boli", Font.BOLD, 80);
    private JLabel numberOfBranchesLabel;
    private JLabel warsawLabel;   private File warsawBrunchesFile = new File("C:\\Users\\jkobm\\OneDrive\\Pulpit\\SIMPLE JAVA SWING PROJECT DATA\\warsawBrunches.txt");
    private JLabel poznanLabel;   private File  poznanBrunchesFile = new File("C:\\Users\\jkobm\\OneDrive\\Pulpit\\SIMPLE JAVA SWING PROJECT DATA\\poznanBrunches.txt");
    private JLabel wroclawLabel;   private File wroclawBrunchesFile  = new File("C:\\Users\\jkobm\\OneDrive\\Pulpit\\SIMPLE JAVA SWING PROJECT DATA\\wroclawBrunches.txt");
    private JPanel cityLabelsPanel;
    private BackButton backButton;

    private Customer customer;
    private DataMap dataMap;
    private Map<String, String> loginAndPasswords;


    private int branchesQuantity;

    private ImageIcon imageIcon = new ImageIcon("C:\\Users\\jkobm\\OneDrive\\Pulpit\\SIMPLE JAVA SWING PROJECT DATA\\pko.jpg");
    private List<Brunch> warsawBrunches = new LinkedList<>() ;
    private List<Brunch> wroclawBrunches = new LinkedList<>() ;
    private List<Brunch> poznanBrunches = new LinkedList<>() ;

    // robimy label głowny na środku obok liczbe oddziałów z jsoup i potem 3 page po wejściu
    // w różne miasta i w kazdym miescie lista oddziałów
    public SeePossibleBranches(Customer customer, DataMap dataMap, Map<String, String> loginAndPasswords) throws IOException {
        this.customer = customer;
        this.dataMap = dataMap;
        this.loginAndPasswords = loginAndPasswords;
        backButton = new BackButton();
        backButton.setBounds(0,0,100,100);
        backButton.addActionListener(this::actionPerformed);
        Document document = Jsoup.connect("https://media.pkobp.pl/75939-pko-w-liczbach").get();
        Elements elements = document.getElementsByClass("block2-text-big");
        branchesQuantity = Integer.parseInt(elements.get(0).text());

        initialization(); // saves data for branches data

        frame = new JFrame("Choose Location");
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000 ,1000);
        frame.getContentPane().setBackground(Color.LIGHT_GRAY);
        frame.setIconImage(imageIcon.getImage());

        mainLabel = new JLabel("Choose city");
        mainLabel.setFont(myFont);
        mainLabel.setForeground(Color.BLACK);
        mainLabel.setBounds(280, 0, 720, 100);

        numberOfBranchesLabel = new JLabel(String.valueOf("Current number of brunches --> " + this.branchesQuantity + " !"));
        numberOfBranchesLabel.setFont(new Font("MV Boli", Font.BOLD, 40));
        numberOfBranchesLabel.setForeground(Color.CYAN);
        numberOfBranchesLabel.setBounds(180, 600, 800, 100);
        numberOfBranchesLabel.setBorder(BorderFactory.createLineBorder(Color.GREEN, 5));

        cityLabelsPanel = new JPanel(new GridLayout(1, 3, 100, 100));
        cityLabelsPanel.setBounds(5, 200, 980, 200);
        warsawLabel = new JLabel("Warsaw");
        warsawLabel.setFont(new Font("MV Boli", Font.BOLD, 50));
        warsawLabel.addMouseListener(this);

        poznanLabel = new JLabel("Poznan");
        poznanLabel.setFont(new Font("MV Boli", Font.BOLD, 50));
        poznanLabel.addMouseListener(this);

        wroclawLabel = new JLabel("Wroclaw");
        wroclawLabel.setFont(new Font("MV Boli", Font.BOLD, 50));
        wroclawLabel.addMouseListener(this);

        cityLabelsPanel.add(warsawLabel);
        cityLabelsPanel.add(poznanLabel);
        cityLabelsPanel.add(wroclawLabel);
        cityLabelsPanel.setBackground(Color.GRAY);
        cityLabelsPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));

        frame.add(backButton);
        frame.add(cityLabelsPanel);
        frame.add(numberOfBranchesLabel);
        frame.add(mainLabel);
        frame.setVisible(true);
    }


    private void initialization() throws IOException {
        Scanner scanner = new Scanner(new BufferedReader(new FileReader(this.warsawBrunchesFile)));
        while (scanner.hasNextLine()) {
            String[] data = scanner.nextLine().split(",");
            warsawBrunches.add(new Brunch(data[0], data[1], data[2], "Warsaw"));
        }

        scanner = new Scanner(new BufferedReader(new FileReader(this.wroclawBrunchesFile)));
        while (scanner.hasNextLine()) {
            String[] data = scanner.nextLine().split(",");
            wroclawBrunches.add(new Brunch(data[0], data[1], data[2], "Wroclaw"));
        }

        scanner = new Scanner(new BufferedReader(new FileReader(this.poznanBrunchesFile)));
        while (scanner.hasNextLine()) {
            String[] data = scanner.nextLine().split(",");
            poznanBrunches.add(new Brunch(data[0], data[1], data[2], "Poznan"));
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(backButton)) {
            frame.dispose();
            new MainValidPage(customer, dataMap, loginAndPasswords);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource().equals(warsawLabel)) {
            frame.dispose();
            new CityPage(warsawBrunches, customer, dataMap, loginAndPasswords);
        }

        if (e.getSource().equals(poznanLabel)) {
            frame.dispose();
            new CityPage(poznanBrunches, customer, dataMap, loginAndPasswords);
        }

        if (e.getSource().equals(wroclawLabel)) {
            frame.dispose();
            new CityPage(wroclawBrunches, customer, dataMap, loginAndPasswords);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource().equals(warsawLabel)) {
            warsawLabel.setForeground(Color.GREEN);
        }

        if (e.getSource().equals(poznanLabel)) {
            poznanLabel.setForeground(Color.GREEN);
        }

        if (e.getSource().equals(wroclawLabel)) {
            wroclawLabel.setForeground(Color.GREEN);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource().equals(warsawLabel)) {
            warsawLabel.setForeground(Color.BLACK);
        }

        if (e.getSource().equals(poznanLabel)) {
            poznanLabel.setForeground(Color.BLACK);
        }

        if (e.getSource().equals(wroclawLabel)) {
            wroclawLabel.setForeground(Color.BLACK);
        }
    }
    ///////////////////////////////////

    public List<Brunch> getWarsawBrunches() {
        return warsawBrunches;
    }

    public List<Brunch> getWroclawBrunches() {
        return wroclawBrunches;
    }

    public List<Brunch> getPoznanBrunches() {
        return poznanBrunches;
    }

}
