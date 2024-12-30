import java.util.*;

public class Day6 {
    public static void Part1(List<String> input){
        Set<String> locations = new HashSet<>();
        int direction = 0;
        boolean inBounds = true;
        int[] coords = getStartingLocation(input);
        int row = coords[0];
        int col = coords[1];
        locations.add(Integer.toString(col) + row);
        while(inBounds){

            char nextLocation = getNextLocation(input, col, row, direction);
            switch (nextLocation) {
                case '.':
                case 'X':
                case '^':
                    switch (direction) {
                        case 0 -> row--;
                        case 1 -> col++;
                        case 2 -> row++;
                        case 3 -> col--;
                    }
                    locations.add(col + "-" + row);
                    StringBuilder sb = new StringBuilder(input.get(row));
                    sb.setCharAt(col, 'X');
                    input.set(row, sb.toString());
                    break;
                case '#':
                    direction = (direction + 1) % 4;
                    {
                        //this block for debugging
                        //printMap(input);
                        //Scanner scanner = new Scanner (System.in);
                        //scanner.nextLine();
                    }
                    break;
                case 'e':
                    inBounds = false;
                    break;
            }
        }
        System.out.println("Total locations traversed: " + locations.size());
        System.out.println("Counted X's: " + getTraversedLocations(input)); //for debugging the Set implementation
    }

    public static void Part2 (List<String> input) {
        int loopsFound = 0;
        for (int i = 0; i < input.size(); i++) {
            for (int j = 0; j < input.get(0).length(); j++) {
                List<String> copy = new ArrayList<>(input);
                if (copy.get(i).charAt(j) != '.') continue;

                StringBuilder sb = new StringBuilder(copy.get(i));
                sb.setCharAt(j, '#');
                copy.set(i, sb.toString());

                loopsFound += isLoopFound(copy);
            }
        }
        System.out.println("Possible loops found: " + loopsFound);
    }

    public static int isLoopFound(List<String> input){
        Set<String> locations = new HashSet<>();
        int direction = 0;
        boolean inBounds = true;
        int[] coords = getStartingLocation(input);
        int row = coords[0];
        int col = coords[1];
        locations.add(col + "-" + row + "-" + direction);
        while(inBounds){

            char nextLocation = getNextLocation(input, col, row, direction);
            switch (nextLocation) {
                case '.':
                case 'X':
                case '^':
                    switch (direction) {
                        case 0 -> row--;
                        case 1 -> col++;
                        case 2 -> row++;
                        case 3 -> col--;
                    }
                    boolean alreadyTraversed = locations.add(col + "-" + row + "-" + direction);
                    if (!alreadyTraversed) {
                        return 1;
                    }
                    break;
                case '#':
                    direction = (direction + 1) % 4;
                break;
                case 'e':
                    return 0;
            }
        }
        throw new RuntimeException("Invalid char found");
    }

    private static int getTraversedLocations(List<String> input){
        int count = 0;
        for (int i = 0; i < input.size(); i++) {
            String line = input.get(i);
            for (int j = 0; j < input.get(0).length(); j++) {
                if(line.charAt(j) == '^' || line.charAt(j) == 'X'){
                    count++;
                }
            }
        }
        return count;
    }
    private static int[] getStartingLocation(List<String> input){
        for (int i = 0; i < input.size(); i++) {
            String line = input.get(i);
            for (int j = 0; j < input.get(0).length(); j++) {
                if(line.charAt(j) == '^'){
                    int[] result = {i,j};
                    return result;
                }
            }
        }
        throw new RuntimeException("No guard found");
    }

    private static void printMap(List<String> input){
        for (int i = 0; i < input.size(); i++) {
            System.out.println(input.get(i));
        }
        System.out.println();
    }

    private static char getNextLocation(List<String> input, int col, int row, int direction){
        try {
            switch (direction) {
                case 0: //North
                    return input.get(row-1).charAt(col);
                case 1: //East
                    return input.get(row).charAt(col+1);
                case 2: //South
                    return input.get(row+1).charAt(col);
                case 3: //West
                    return input.get(row).charAt(col-1);
                default:
                    return 'e';
            }
        } catch (IndexOutOfBoundsException e) {
            return 'e';
        }
    }
}
