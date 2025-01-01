package Days;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day9 {
    public static Map<Integer, Integer> gaps = new HashMap<>();
    public static void Part1(List<String> input){
        String fileSystem = input.get(0);
        List<Integer> decryptedDisk = decryptDisk(fileSystem);
        List<Integer> defragmentedDisk = defragmentDiskMk1(decryptedDisk);
        long checkSum = calculateChecksum(defragmentedDisk);
        System.out.println("Checksum: " + checkSum);
    }

    public static void Part2(List<String> input){
        String fileSystem = input.get(0);
        List<Integer> decryptedDisk = decryptDisk(fileSystem);
        List<Integer> defragmentedDisk = defragmentDiskMk2(decryptedDisk);
        long checkSum = calculateChecksum(defragmentedDisk);
        System.out.println("Checksum: " + checkSum);
    }
    private static List<Integer> decryptDisk(String fileSystem){
        List<Integer> disk = new ArrayList<>();
        for (int i = 0; i < fileSystem.length(); i++) {
            int length = Integer.parseInt(String.valueOf(fileSystem.charAt(i)));
            for (int j = 0; j < length; j++) {
                disk.add(i % 2 == 0 ? i/2 : -1);
            }
        }
        return disk;
    }

    private static List<Integer> defragmentDiskMk1(List<Integer> disk) {
        int leftIndex = 0;
        int rightIndex = disk.size()-1;
        int valueToMove;
        while (rightIndex > leftIndex) {
            if(disk.get(leftIndex) != -1) {
                leftIndex++;
                continue;
            }
            valueToMove = disk.get(rightIndex);
            if(valueToMove == -1) {
                rightIndex--;
                continue;
            }
            disk.set(leftIndex, valueToMove);
            disk.set(rightIndex, -1);
            leftIndex++;
            rightIndex--;
        }
        return disk;
    }
    private static List<Integer> defragmentDiskMk2(List<Integer> disk) {
        int firstOpenIndex = -1;
        int indexRecentlyFilled;
        int leftIndex = 0;
        int rightIndex = disk.size()-1;
        int valueToMove;
        int fileTypeValidatedFor = -1;
        boolean isSpaceSufficient = true;
        while (rightIndex > leftIndex) {
            if(disk.get(leftIndex) != -1) {
                leftIndex++;
                continue;
            }
            if(firstOpenIndex == -1) firstOpenIndex = leftIndex;
            valueToMove = disk.get(rightIndex);
            if(valueToMove == -1) {
                rightIndex--;
                continue;
            }
            if(fileTypeValidatedFor != valueToMove) {
                isSpaceSufficient = validateMove(disk, leftIndex, rightIndex);
                fileTypeValidatedFor = valueToMove;
                indexRecentlyFilled = leftIndex;
            }
            if(!isSpaceSufficient) {
                while(disk.get(leftIndex) == -1)
                    leftIndex++;
                fileTypeValidatedFor = -1;
                continue;
            }
            disk.set(leftIndex, valueToMove);
            disk.set(rightIndex, -1);
            
            leftIndex++;
            rightIndex--;
        }
        return disk;
    }

    private static boolean validateMove(List<Integer> disk, int leftIndex, int rightIndex){
        int mapIndex = leftIndex;
        int leftGap = 0;
        int fileSize = 0;
        int fileType = disk.get(rightIndex);
        while (disk.get(leftIndex) == -1) {
            leftGap++;
            leftIndex++;
        }
        gaps.put(mapIndex, leftGap);
        while (disk.get(rightIndex) == fileType) {
            fileSize++;
            rightIndex--;
        }
        return fileSize <= leftGap;
    }

    private static long calculateChecksum(List<Integer> disk){
        long checkSum = 0;
        for (int i = 0; i < disk.size(); i++) {
            if(disk.get(i) == -1) break;
            checkSum += i * disk.get(i);
        }
        return checkSum;
    }
}
