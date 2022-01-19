import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DataMap {
    private Map<String, String> dataMap;
    private Map<String, Customer> customerMap = new HashMap<>();
    private BufferedWriter bufferedWriter;
    private static final int LOGIN_LENGTH = 10;
    private static final int PASSWORD_LENGTH = 10;
    private int howManyData;


    String[] randomName = {"Kuba", "Maciek", "Agata", "Daria", "Julia"};
    String[] randomSurnames = {"Zawadzki", "Plichta", "Kolikowski", "Nowak", "Javowy"};
    String[] randomEmailAdresses = {"kuba@onet.pl", "maciek@onet.pl", "agata@onet.pl", "daria@onet.pl", "julia@onet.pl"};
    String[] randomPeselIds = {"02300508891", "98345691042", "43785609123", "45207695640", "1234567123"};
    String[] randomRealAdresses = {"Wojska Polskiego - Pulawy", "Legionow Polskich - Warszawa", "Cracovii - Karkow", "Partyzantow - Poznań", "Bronowice - Lublin"};
    String[] randomDateOfBirths = {"05.12.2002","24.02.1993", "13.9.1997", "01.01.2001", "12.12.1945"};


    public DataMap(BufferedWriter bufferedWriter, int howManyData) throws IOException {
            this.bufferedWriter = bufferedWriter;
            this.howManyData = howManyData;
            this.dataMap = new HashMap<>();
            for (int i=0; i<howManyData; i++) {
                String randomLogin = getAlphaNumericString(LOGIN_LENGTH);
                String randomPassword = getAlphaNumericString(PASSWORD_LENGTH);
               
                bufferedWriter.write(randomLogin + "," + randomPassword + "," +  randomName[i] + "," +  randomSurnames[i] + "," +  randomPeselIds[i] + "," +
                        randomEmailAdresses[i] + "," + randomRealAdresses[i] + "," +
                        randomDateOfBirths[i] + '\n');
                this.dataMap.putIfAbsent(randomLogin, randomPassword);

                Customer customer = new Customer(randomName[i],
                        randomSurnames[i],
                        randomLogin,
                        randomPassword,
                        randomPeselIds[i],
                        randomEmailAdresses[i],
                        randomRealAdresses[i],
                        randomDateOfBirths[i],
                        0);
                customerMap.putIfAbsent(randomPeselIds[i], customer);

            }
            bufferedWriter.close();
    }


    public boolean addAccount(String login, String password) {
        if (this.dataMap.containsKey(login)) {
            return false;
        } else {
            if (login.length() >= 3 && password.length() >= 3) {
            this.dataMap.put(login, password);
            return true;
            }
        }
         return false;
    }

    public boolean addAccount(String peselID, Customer customer) {
        if (this.customerMap.containsKey(peselID)) {
            return false;
        } else {
            this.customerMap.putIfAbsent(peselID, customer);
            return true;
        }
    }

    public Customer findCustomer(String peselID) {
        for (Map.Entry<String, Customer> customer : customerMap.entrySet()) {
            if (customer.getValue().getPeselID().equals(peselID)) {
                return customer.getValue();
            }
        }
        return null;
    }

    public Map<String, String> getDataMap() {
        return dataMap;
    }

    public Map<String, Customer> getCustomerMap() {
        return customerMap;
    }

    static String getAlphaNumericString(int n)
    {

        // chose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                    = (int)(AlphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                    .charAt(index));
        }

        return sb.toString();
    }
}
