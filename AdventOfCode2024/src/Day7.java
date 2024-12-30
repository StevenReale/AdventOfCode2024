import java.util.ArrayList;
import java.util.List;

public class Day7 {
    public static void Part1(List<String> input){
        int validEquations = 0;
        for(String line: input) {
            Equation equation = generateEquation(line);
            validEquations += validateEquation(equation, 1, equation.values.get(0));
        }
        System.out.println("Valid equations: " + validEquations);
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

    private static int validateEquation(Equation equation, int currentIndex, Long currentValue){
        Long potentialSum = currentValue + equation.values.get(currentIndex);
        Long potentialProduct = currentValue * equation.values.get(currentIndex);

        if (currentIndex == equation.values.size() - 1){
            return
                (potentialSum == equation.target ) ||
                (potentialProduct == equation.target )
                    ? 1 : 0;
        }

        int validatedSum = validateEquation(equation, currentIndex+1, potentialSum);
        int validatedProduct = validateEquation(equation,  currentIndex+1, potentialProduct);

        return validatedProduct == 1 || validatedSum == 1 ? 1 : 0;
    }

    private static class Equation {
        public Long target;
        public List<Long> values;

        public Equation(){
            this.values = new ArrayList<>();
        }
    }
}
