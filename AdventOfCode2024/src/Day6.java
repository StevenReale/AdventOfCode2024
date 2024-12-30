import java.util.List;

public class Day6 {
    public static void Part1(List<String> input){
        int direction = 0;
        int steps = 0;
        int[] coords = getStartingLocation(input);
        int col = coords[0];
        int row = coords[1];

        while(col >= 0 && col < input.size() && row >= 0 && row < input.get(0).length()){
            char nextLocation = getNextLocation(input, col, row, direction);
        }
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
}
