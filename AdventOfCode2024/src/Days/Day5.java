package Days;

import java.util.*;
import java.util.stream.IntStream;

public class Day5 {
    public static Map<Integer, Set<Integer>> orderings = new HashMap<>();
    public static void Part1(List<String> input) {
        processInput(input, 1);
    }

    public static void Part2(List<String> input) {
        processInput(input, 2);
    }

    private static void processInput(List<String> input, int part){
        int total = 0;
        int inputLength = input.size();
        int counter = 0;

        while(!input.get(counter).equals("")){
            String thisLine = input.get(counter);
            String[] currentLine = thisLine.split("\\|");
            int firstInt = Integer.parseInt(currentLine[0]);
            int secondInt = Integer.parseInt(currentLine[1]);
            if(!orderings.containsKey(firstInt))
                orderings.put(firstInt, new HashSet<>());
            orderings.get(firstInt).add(secondInt);
            counter++;
        }
        counter++;
        while(counter < inputLength){
            int result = validateLine(input.get(counter));
            if (part == 1) {
                total += result;
            } else if (result == 0) {
                total += fixLineAndGetMiddleValue(input.get(counter));
            }
            counter++;
        }
        System.out.println("Total valid values: " + total);
    }

    private static int validateLine(String line){
        Set<Integer> previousEntries = new HashSet<>();
        String[] entries = line.split(",");
        previousEntries.add(Integer.parseInt(entries[0]));
        for(int i = 1; i < entries.length; i++){
            Set<Integer> intersection = new HashSet<>(previousEntries);
            int currentInt = Integer.parseInt(entries[i]);
            if(orderings.containsKey(currentInt)){
                intersection.retainAll(orderings.get(currentInt));
                if (intersection.size() > 0){
                    return 0;
                }
            }
            previousEntries.add(currentInt);
        }
        return Integer.parseInt(entries[entries.length/2]);
    }

    private static int fixLineAndGetMiddleValue(String line) {
        Set<Integer> previousEntries = new HashSet<>();
        String[] entries = line.split(",");
        previousEntries.add(Integer.parseInt(entries[0]));
        for(int i = 1; i < entries.length; i++){
            Set<Integer> intersection = new HashSet<>(previousEntries);
            int currentInt = Integer.parseInt(entries[i]);
            if(orderings.containsKey(currentInt)){
                intersection.retainAll(orderings.get(currentInt));
                if (intersection.size() > 0){
                    int smallestIndex = 0;
                    for (int j=0; j<i; j++) {
                        if(intersection.contains(Integer.parseInt(entries[j]))){
                            smallestIndex = j;
                            break;
                        }
                    }
                    List<String> entryList = new ArrayList<>();
                    for(String entry : entries){
                        entryList.add(entry);
                    }
                    entryList.remove(i);
                    entryList.add(smallestIndex, entries[i]);
                    entries = entryList.stream().toArray(String[]::new);
                }
            }
            previousEntries.add(currentInt);
        }
        return Integer.parseInt(entries[entries.length/2]);
    }
}
