import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        DataMap dataMap = new DataMap(new BufferedWriter(new FileWriter("C:\\Users\\jkobm\\OneDrive\\Pulpit\\SIMPLE JAVA SWING PROJECT DATA\\data.txt")), 5);
        new LoginPage(dataMap, dataMap.getDataMap());
        System.out.println(writeIntoTheFile(copyDataFromTheFile()));

    }
    private static String copyDataFromTheFile() throws FileNotFoundException {
        Scanner scanner = new Scanner(new BufferedReader(new FileReader(new File("C:\\Users\\jkobm\\OneDrive\\Pulpit\\SIMPLE JAVA SWING PROJECT DATA\\data.txt"))));
        StringBuilder stringBuilder = new StringBuilder();
        while (scanner.hasNextLine()) {
            stringBuilder.append(scanner.nextLine()).append("\n");
        }
        return stringBuilder.toString();
    }

    private static boolean writeIntoTheFile(String content) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("C:\\Users\\jkobm\\OneDrive\\Pulpit\\SIMPLE JAVA SWING PROJECT DATA\\curentData.txt"));
        bufferedWriter.write("\n\n");
        bufferedWriter.write(content);
        bufferedWriter.close();
        return content.isEmpty();
    }
}
