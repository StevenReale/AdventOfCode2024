package Days;
import Helpers.Helpers;

import java.util.*;

public class Day8 {
    public static void Part1(List<String> input) {
        getAntinodes(input, 1);
    }

    public static void Part2(List<String> input) {
        getAntinodes(input, 2);
    }
    private static void getAntinodes(List<String> input, int part){
        Set<String> antinodes = new HashSet<>();
        Map<Character, List<Coordinate>> antennaNodes = processInput(input);
        for(Map.Entry<Character, List<Coordinate>> entry: antennaNodes.entrySet()){
            List<Coordinate> coordinates = entry.getValue();
            for (int i = 0; i < coordinates.size() - 1; i++) {
                Coordinate firstAntenna = coordinates.get(i);
                for (int j = i+1; j < coordinates.size(); j++) {
                    Coordinate secondAntenna = coordinates.get(j);
                    int rowDistance = secondAntenna.row - firstAntenna.row;
                    int colDistance = secondAntenna.column - firstAntenna.column;
                    if (part == 1)
                        calculateSimpleAntinodes(input, antinodes, firstAntenna, secondAntenna, rowDistance, colDistance);
                    else
                        calculateComplexAntinodes(input, antinodes, firstAntenna, secondAntenna, rowDistance, colDistance);
                }

            }
        }
        System.out.println("Total valid antinodes: " + antinodes.size());
    }

    private static void calculateSimpleAntinodes(List<String> input, Set<String> antinodes, Coordinate firstAntenna, Coordinate secondAntenna, int rowDistance, int colDistance){
        Coordinate firstAntinode = new Coordinate(firstAntenna.row - rowDistance, firstAntenna.column - colDistance);
        Coordinate secondAntinode = new Coordinate(secondAntenna.row + rowDistance, secondAntenna.column + colDistance);
        for (Coordinate antinode : new Coordinate[] {firstAntinode, secondAntinode}) {
            if (antinode.row >= 0 &&
                    antinode.row < input.size() &&
                    antinode.column >= 0 &&
                    antinode.column < input.get(0).length()) {
//                                for debugging
//                                StringBuilder sb = new StringBuilder(input.get(antinode.row));
//                                sb.setCharAt(antinode.column, '@');
//                                input.set(antinode.row, sb.toString());
//                                Helpers.PrintMap(input);
                antinodes.add(antinode.row + "-" + antinode.column);
            }
        }
    }

    private static void calculateComplexAntinodes(List<String> input, Set<String> antinodes, Coordinate firstAntenna, Coordinate secondAntenna, int rowDistance, int colDistance) {
        int currentCol = firstAntenna.column;
        int currentRow = firstAntenna.row;
        while (currentRow >= 0 && currentCol >=0 && currentRow < input.size() && currentCol < input.get(0).length()) {
            antinodes.add(currentRow + "-" + currentCol);
            //                                for debugging
//            if (input.get(currentRow).charAt(currentCol) == '.') {
//                StringBuilder sb = new StringBuilder(input.get(currentRow));
//                sb.setCharAt(currentCol, '@');
//                input.set(currentRow, sb.toString());
//            }
//            Helpers.PrintMap(input);
            currentRow -= rowDistance;
            currentCol -= colDistance;

        }
        currentCol = firstAntenna.column + colDistance;
        currentRow = firstAntenna.row + rowDistance;
        while (currentRow >= 0 && currentCol >=0 && currentRow < input.size() && currentCol < input.get(0).length()) {
            antinodes.add(currentRow + "-" + currentCol);
            //                                for debugging
//            if (input.get(currentRow).charAt(currentCol) == '.') {
//                StringBuilder sb = new StringBuilder(input.get(currentRow));
//                sb.setCharAt(currentCol, '@');
//                input.set(currentRow, sb.toString());
//            }
//            Helpers.PrintMap(input);
            currentRow += rowDistance;
            currentCol += colDistance;

        }
    }
    private static Map<Character, List<Coordinate>> processInput(List<String> input){
        Map<Character, List<Coordinate>> result = new HashMap<>();
        for (int i = 0; i < input.size(); i++) {
            for (int j = 0; j < input.get(0).length(); j++) {
                char currentChar = input.get(i).charAt(j);
                if(currentChar == '.') {
                    continue;
                }
                Coordinate coordinate = new Coordinate(i, j);
                if(!result.containsKey(currentChar)) {
                    result.put(currentChar, new ArrayList<>());
                }
                result.get(currentChar).add(coordinate);
            }
        }
        return result;
    }

    private static class Coordinate {
        public int row;
        public int column;

        public Coordinate (int row, int column) {
            this.row = row;
            this.column = column;
        }
    }
}
