import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String file = "Library/Day7.txt";
        List<String> input = getInput(file);
        Day7.Part2(input);
    }

    public static List<String> getInput(String file) {
        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            List<String> input = new ArrayList<>();
            String line = br.readLine();

            while (line != null) {
                input.add(line);
                line = br.readLine();
            }
            return input;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}