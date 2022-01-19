import java.io.*;
import java.util.*;

public class Algorithms {
    private static Map<String, String> countriesAndCapital() {
        Map<String, String> countriesAndCapital = new HashMap<>();
        Scanner scanner ;
        try {
            scanner = new Scanner(new BufferedReader(new FileReader(new File("C:\\Users\\jkobm\\OneDrive\\Pulpit\\javaData.txt"))));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(" ");
                countriesAndCapital.putIfAbsent(data[0], data[1]);
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return countriesAndCapital;

    }

    private static void writeIntoTheFile(File file, Map<String, String> mapToPrint) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
        for (Map.Entry<String, String> mapKeyAndValues : mapToPrint.entrySet()) {
            bufferedWriter.write("Key --> " + mapKeyAndValues.getKey() + "\tValue --> " + mapKeyAndValues.getValue() + "\n");
        }
        bufferedWriter.close();
    }
    public static void main(String[] args) throws IOException {
        System.out.println(countriesAndCapital());
        writeIntoTheFile(new File("javaOutput.txt"), countriesAndCapital());
    }
}
