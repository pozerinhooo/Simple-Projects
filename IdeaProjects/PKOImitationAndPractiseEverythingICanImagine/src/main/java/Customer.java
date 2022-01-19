import java.util.ArrayList;
import java.util.List;

public class Customer {

    private String name;
    private String surname;
    private String login;
    private String password;
    private String peselID;
    private String emailAdress;
    private String realAdress;
    private String dateOfBirth;
    private double balance;
    private double initialValue;
    private List<Double> transactions = new ArrayList<>();

    public Customer(String name, String surname, String login, String password, String peselID, String emailAdress, String realAdress, String dateOfBirth, double initialValue) {
        this.initialValue = initialValue;
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.peselID = peselID;
        this.emailAdress = emailAdress;
        this.realAdress = realAdress;
        this.dateOfBirth = dateOfBirth;
        addTransaction(initialValue);
    }

    public void addTransaction(double value) {
        this.balance += value;
        transactions.add(value);
    }



    public String getSurname() {
        return surname;
    }

    public double getInitialValue() {
        return initialValue;
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getPeselID() {
        return peselID;
    }

    public String getEmailAdress() {
        return emailAdress;
    }

    public String getRealAdress() {
        return realAdress;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public double getBalance() {
        return balance;
    }

    public List<Double> getTransactions() {
        return transactions;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmailAdress(String emailAdress) {
        this.emailAdress = emailAdress;
    }
}
