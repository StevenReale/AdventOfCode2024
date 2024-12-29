import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3 {
    public static void Part1(List<String> input){
        String text = String.join("", input);
        int total = performMults(text);
        displayTotal(total);
    }

    public static void Part2(List<String> input){
        int total = 0;
        String text = String.join("", input);

        int dontIndex = text.indexOf("don't()");
        String localText = text.substring(0, dontIndex + 7);
        total += performMults(localText);

        do{
            text = text.substring(dontIndex + 7);
            dontIndex = text.indexOf("don't()");
            localText = text.substring(0, dontIndex + 7);
            total += findAndParseDoClause(localText);

        } while (dontIndex != -1);
        total += findAndParseDoClause(text);
        displayTotal(total);
    }

    private static void displayTotal (int total) {
        System.out.print("Total: ");
        System.out.println(total);
    }
    private static int findAndParseDoClause(String text) {
        int doIndex = text.indexOf("do()");
        if(doIndex != -1) {
            String doEnabled = text.substring(doIndex);
            return performMults(doEnabled);
        }
        return 0;
    }
    private static int performMults(String input) {
        int total = 0;
        // Regular expression to match "mul(A,B)" where A and B are 1-3 digit numbers
        String regex = "mul\\((\\d{1,3}),(\\d{1,3})\\)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        while (matcher.find()) {
            String firstNum = matcher.group(1);
            String secondNum = matcher.group(2);
            total += Integer.parseInt(firstNum) * Integer.parseInt(secondNum);
        }
        return total;
    }
}
