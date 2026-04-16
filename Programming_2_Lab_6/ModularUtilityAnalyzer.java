import java.util.Scanner;
import java.util.Random;

public class ModularUtilityAnalyzer {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Task 1: Dynamic Range Aggregation
        System.out.print("Enter range 1 (start end): ");
        int start1 = input.nextInt();
        int end1 = input.nextInt();

        System.out.print("Enter range 2 (start end): ");
        int start2 = input.nextInt();
        int end2 = input.nextInt();

        System.out.print("Enter range 3 (start end): ");
        int start3 = input.nextInt();
        int end3 = input.nextInt();

        System.out.println("Range total: " + calculateRangeSum(start1, end1));
        System.out.println("Range total: " + calculateRangeSum(start2, end2));
        System.out.println("Range total: " + calculateRangeSum(start3, end3));

        // Task 2: Conditional Evaluation
        System.out.print("\nEnter evaluation score: ");
        int score = input.nextInt();
        evaluatePerformance(score);

        // Task 3: Flexible Value Comparison
        System.out.print("\nEnter two integers: ");
        int int1 = input.nextInt();
        int int2 = input.nextInt();
        System.out.println("Selected value: " + compareValues(int1, int2));

        System.out.print("\nEnter two decimal values: ");
        double double1 = input.nextDouble();
        double double2 = input.nextDouble();
        System.out.println("Selected value: " + compareValues(double1, double2));

        // Task 4: Controlled Random Generation
        System.out.print("\nEnter two characters (start end): ");
        char char1 = input.next().charAt(0);
        char char2 = input.next().charAt(0);
        System.out.println("Generated character: " + generateRandomChar(char1, char2));

        input.close();
    }

    // Task 1: Reusable method to calculate sum of range
    public static int calculateRangeSum(int start, int end) {
        int sum = 0;
        for (int i = start; i <= end; i++) {
            sum += i;
        }
        return sum;
    }

    // Task 2: Void method for conditional evaluation
    public static void evaluatePerformance(int score) {
        if (score >= 90) {
            System.out.println("Performance: Excellent");
        } else if (score >= 70) {
            System.out.println("Performance: Satisfactory");
        } else if (score >= 50) {
            System.out.println("Performance: Needs Improvement");
        } else {
            System.out.println("Performance: Unsatisfactory");
        }
    }

    // Task 3: Method overloading - compare integers
    public static int compareValues(int value1, int value2) {
        return (value1 > value2) ? value1 : value2;
    }

    // Task 3: Method overloading - compare doubles
    public static double compareValues(double value1, double value2) {
        return (value1 > value2) ? value1 : value2;
    }

    // Task 4: Generate random character within range
    public static char generateRandomChar(char start, char end) {
        Random random = new Random();
        int min = Math.min(start, end);
        int max = Math.max(start, end);
        int randomValue = random.nextInt(max - min + 1) + min;
        return (char) randomValue;
    }
}