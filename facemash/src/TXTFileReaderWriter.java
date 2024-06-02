import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class TXTFileReaderWriter  {

    // Function to read a TXT file and store its contents into a variable
    public static String readTXTFile(String filePath) throws TXTFileIOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            throw new TXTFileIOException("Error reading TXT file: " + e.getMessage(), e);

        }
        return content.toString();
    }

    // Function to write the contents of a variable into a TXT file
    public static void writeTXTFile(String filePath, String content) throws TXTFileIOException {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(content);
        } catch (IOException e) {
            throw new TXTFileIOException("Error writing to TXT file: " + e.getMessage(), e);

        }
    }

//    public static void main(String[] args) {

//    }
}

    