package Days;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day4 {
    private enum DIRECTIONS {
        East,Southeast,South,Southwest,West,Northwest,North,Northeast
    }
    private static final Map<Integer, Character> LETTERS = new HashMap<>();
    static{
        LETTERS.put(1, 'X');
        LETTERS.put(2, 'M');
        LETTERS.put(3, 'A');
        LETTERS.put(4, 'S');
    }

    public static void Part1(List<String> input){
        process(input, 1);
    }

    public static void Part2(List<String> input) {
        process(input, 2);
    }

    private static void process(List<String> input, int part){
        int count = 0;
        int rows = input.size();
        int cols = input.get(0).length();
        for(int row = 0; row < rows; row++){
            for(int col = 0; col < cols; col++){
                if(part == 1 && input.get(row).charAt(col) == LETTERS.get(1)){
                    count += findAllPart1(input, row, col, rows, cols);
                } else if (part == 2 && row > 0 && row < rows - 1 && col > 0 && col < cols - 1 && input.get(row).charAt(col) == LETTERS.get(3)) {
                    count += findAllPart2(input, row, col);
                }
            }
        }
        System.out.println("Total Occurrences: " + count);
    }

    private static int findAllPart1(List<String> input, int row, int col, int cols, int rows){
        int count = 0;
        //East
        if(col < cols - 3){
            count += checkDirectionPart1(DIRECTIONS.East, 2, input, row, col+1);
        }
        //Southeast
        if(col < cols - 3 && row < rows - 3) {
            count += checkDirectionPart1(DIRECTIONS.Southeast, 2, input, row + 1, col + 1);
        }
        //South
        if(row < rows - 3) {
            count += checkDirectionPart1(DIRECTIONS.South, 2, input, row+1, col);
        }
        //Southwest
        if(col > 2 && row < rows - 3){
            count += checkDirectionPart1(DIRECTIONS.Southwest,2, input, row+1, col - 1);
        }
        //West
        if(col > 2){
            count += checkDirectionPart1(DIRECTIONS.West, 2, input, row, col-1);
        }
        //Northwest
        if(col > 2 && row > 2){
            count += checkDirectionPart1(DIRECTIONS.Northwest, 2, input, row-1, col-1);
        }
        //North
        if(row > 2){
            count += checkDirectionPart1(DIRECTIONS.North, 2, input, row-1, col);
        }
        //Northeast
        if(col < cols -3 && row > 2) {
            count += checkDirectionPart1(DIRECTIONS.Northeast, 2, input, row-1, col+1);
        }
        return count;
    }
    private static int findAllPart2(List<String> input, int row, int col) {
        StringBuilder sb = new StringBuilder();
        sb.append(input.get(row - 1).charAt(col-1));
        sb.append(input.get(row - 1).charAt(col+1));
        sb.append(input.get(row + 1).charAt(col+1));
        sb.append(input.get(row + 1).charAt(col-1));
        String result = sb.toString();
        return result.equals("MMSS") || result.equals("MSSM") || result.equals("SSMM") || result.equals("SMMS") ? 1 : 0;
    }

    private static int checkDirectionPart1(DIRECTIONS direction, int letter, List<String> input, int row, int col){
        char currentLetter = input.get(row).charAt(col);
        if(!doesLetterMatch(currentLetter, letter)) return 0;
        if(letter == 4) return 1;
        switch (direction){
            case East:
                return checkDirectionPart1(direction, letter+1, input, row, col+1);
            case Southeast:
                return checkDirectionPart1(direction, letter + 1, input, row + 1, col + 1);
            case South:
                return checkDirectionPart1(direction, letter +1, input, row+1, col);
            case Southwest:
                return checkDirectionPart1(direction, letter + 1, input, row+1, col - 1);
            case West:
                return checkDirectionPart1(direction, letter+1, input, row, col-1);
            case Northwest:
                return checkDirectionPart1(direction, letter+1, input, row-1, col-1);
            case North:
                return checkDirectionPart1(direction, letter+1, input, row-1, col);
            case Northeast:
                return checkDirectionPart1(direction, letter+1, input, row-1, col+1);
        }
        throw new RuntimeException("Invalid Direction");
    }

    private static boolean doesLetterMatch(char testLetter, int matchLetter){
        return testLetter == LETTERS.get(matchLetter);
    }

}
