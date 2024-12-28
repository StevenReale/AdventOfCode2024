import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Day2 {
    public static void Part1(List<String> input){
        processInput(input, false);
    }

    public static void Part2(List<String> input) {
        processInput(input, true);
    }

    private static void processInput(List<String> input, Boolean isDampened){
        int safeLines = 0;
        int counter = 0;
        Scanner scanner = new Scanner(System.in);
        for(String line: input){
            counter++;
            int[] parsedLine = parseLine(line);
            Boolean isLineSafe = isLineSafe(parsedLine, isDampened);
            if(isLineSafe) {
                safeLines++;
            }
            else{
                System.out.println(line);
            }
//            System.out.print(line + " ");
//            System.out.print(isLineSafe);
//            System.out.print(" Safe: ");
//            System.out.println(safeLines);
            if(counter % 5 == 0){
                //scanner.nextLine();
            }

        }
        System.out.print("Total Safe Lines: ");
        System.out.println(safeLines);
    }

    private static int[] parseLine(String line){
        String[] lineArray = line.split(" ");
        int[] numbers = Arrays.stream(lineArray).mapToInt(Integer::parseInt).toArray();
        return numbers;
    }

    private static Boolean isLineSafe(int[] line, Boolean isDampened){
        Boolean isAscending = isAscending(line, isDampened);
        if((isAscending && !(line[0] < line[1])) || (!isAscending && !(line[0] > line[1]))){
            return isDampened
                    ? isLineSafe(removeIndex(line, 0), false)
                    : false;
        }
        for(int i = 1; i < line.length; i++){
            if((line[i-1] > line[i] && isAscending) || (line[i-1] < line[i] && !isAscending))
                return isDampened
                    ? isLineSafe(removeIndex(line, i), false)
                    : false;
            int diff = Math.abs(line[i] - line[i-1]);
            if(diff < 1 || diff > 3)
                return isDampened
                    ? isLineSafe(removeIndex(line, i), false)
                    : false;
        }
        return true;
    }

    private static Boolean isAscending(int[] line, Boolean isDampened){
        return isDampened
                ? line[0] < line[1] || line[0] < line[2] || (line[1] < line[2] && line[2] < line[3])
                : line[0] < line[1] && line[1] < line[2];
    }

    private static int[] removeIndex(int[] line, int indexToRemove){
        int modifier = 0;
        int[] output = new int[line.length-1];
        for(int i=0; i<line.length; i++){
            if(i == indexToRemove){
                modifier = -1;
                continue;
            }
            output[i + modifier] = line[i];
        }
        return output;
    }
}
