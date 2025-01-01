package Days;

import java.util.ArrayList;
import java.util.List;

public class Day7 {
    public static void Part1(List<String> input){
        long totalCalibrationResult = 0;
        for(String line: input) {
            Equation equation = generateEquation(line);
            if(validateEquation(equation, 1, equation.values.get(0)))
                totalCalibrationResult += equation.target;
        }
        System.out.println("Total Calibration Result: " + totalCalibrationResult);
    }

    public static void Part2(List<String> input){
        long totalCalibrationResult = 0;
        for(String line: input) {
            Equation equation = generateEquation(line);
            if(validateEquationWithConcat(equation, 1, equation.values.get(0)))
                totalCalibrationResult += equation.target;
        }
        System.out.println("Total Calibration Result: " + totalCalibrationResult);
    }

    private static Equation generateEquation(String line){
        Equation equation = new Equation();
        String[] parts = line.split(":");
        equation.target = Long.parseLong(parts[0]);

        String[] values = parts[1].trim().split(" ");
        for (int i = 0; i < values.length ; i++) {
            equation.values.add(Long.parseLong(values[i]));
        }

        return equation;
    }

    private static boolean validateEquation(Equation equation, int currentIndex, long currentValue){
        long potentialSum = currentValue + equation.values.get(currentIndex);
        long potentialProduct = currentValue * equation.values.get(currentIndex);

        if (currentIndex == equation.values.size() - 1){
            return potentialSum == equation.target || potentialProduct == equation.target;
        }

        return
            validateEquation(equation, currentIndex+1, potentialSum) ||
            validateEquation(equation, currentIndex+1, potentialProduct);
    }

    private static boolean validateEquationWithConcat(Equation equation, int currentIndex, long currentValue){
        long potentialSum = currentValue + equation.values.get(currentIndex);
        long potentialProduct = currentValue * equation.values.get(currentIndex);
        long potentialConcat = Long.parseLong(Long.toString(currentValue) + equation.values.get(currentIndex));

        if (currentIndex == equation.values.size() - 1){
            return potentialSum == equation.target || potentialProduct == equation.target || potentialConcat == equation.target;
        }

        return
            validateEquationWithConcat(equation, currentIndex+1, potentialSum) ||
            validateEquationWithConcat(equation, currentIndex+1, potentialProduct) ||
            validateEquationWithConcat(equation, currentIndex+1, potentialConcat);
    }

    private static class Equation {
        public long target;
        public List<Long> values;

        public Equation(){
            this.values = new ArrayList<>();
        }
    }
}
