import java.util.Arrays;
import java.util.List;

public class Day2 {
    public static void Part1(List<String> input){
        processInput(input, false);
    }

    public static void Part2(List<String> input) {
        processInput(input, true);
    }

    private static void processInput(List<String> input, Boolean isDampened){
        int safeLines = 0;
        for(String line: input){
            int[] parsedLine = parseLine(line);
            Boolean isLineSafe = isLineSafe(parsedLine, isDampened);
            if(isLineSafe) {
                safeLines++;
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
        Boolean isAscending = isAscending(line);
        if(isSegmentValid(line[0], line[1], isAscending)){
            return isDampened
                    ? isLineSafe(removeIndex(line, 0), false) || isLineSafe(removeIndex(line, 1), false)
                    : false;
        }
        for(int i = 1; i < line.length; i++){
            if(isSegmentValid(line[i-1], line[i], isAscending)) {
                return isDampened
                    ? isLineSafe(removeIndex(line, i), false)
                    : false;
            }
        }
        return true;
    }

    private static Boolean isAscending(int[] line){
        return
            (line[0] < line[1] && line[1] < line[2]) ||
            (line[1] < line[2] && line[2] < line[3]) ||
            (line[0] < line[2] && line[2] < line[3]) ||
            (line[0] < line[1] && line[1] < line[3]);
    }

    private static Boolean isSegmentValid(int a, int b, boolean isAscending){
        int diff = Math.abs(a-b);
        return  (a > b && isAscending) ||
                (a < b && !isAscending) ||
                diff < 1 ||
                diff > 3;
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
