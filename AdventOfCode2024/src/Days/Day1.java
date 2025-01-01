package Days;

import java.util.*;

public class Day1 {
    public static void Part1(List<String> input) {
        int totalDistances = 0;
        List<Integer>[] results = parseInput(input);
        Collections.sort(results[0]);
        Collections.sort(results[1]);
        for(int i = 0; i < results[0].size(); i++) {
            totalDistances += Math.abs(results[0].get(i) - results[1].get(i));
        }
        System.out.print("Total Distance: ");
        System.out.println(totalDistances);
    }

    public static void Part2(List<String> input) {
        int similarityScore = 0;
        List<Integer>[] parsedInput = parseInput(input);
        Map<Integer, Integer> results = new HashMap<>();
        for(int columnA: parsedInput[0]){
            results.put(columnA, 0);
        }
        for(int columnB: parsedInput[1]){
            if(results.containsKey(columnB)){
                results.put(columnB, results.get(columnB) + 1);
            }
        }
        for(int columnA: parsedInput[0]){
            similarityScore += columnA * results.get(columnA);
        }
        System.out.print("Total Similarity Score: ");
        System.out.println(similarityScore);
    }

    private static List<Integer>[] parseInput(List<String> input){
        List<Integer>[] output = new List[2];
        output[0] = new ArrayList<>();
        output[1] = new ArrayList<>();
        for(String line: input) {
            String[] currentLine = line.split("   ");
            output[0].add(Integer.parseInt(currentLine[0].trim()));
            output[1].add(Integer.parseInt(currentLine[1].trim()));
        }
        return output;
    }
}
