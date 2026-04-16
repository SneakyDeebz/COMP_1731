import java.util.Scanner;

public class UniversityReporting {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        double tuition = 10000.0;
        double increaseRate = 0.05;

        for (int year = 1; year <= 10; year++) {
            tuition = tuition * (1 + increaseRate);
        }

        System.out.printf("Tuition after 10 years: $%.2f%n", tuition);

        double totalFourYears = 0.0;
        for (int year = 1; year <= 4; year++) {
            totalFourYears += tuition;
            tuition = tuition * (1 + increaseRate);
        }

        System.out.printf("Total cost of four years starting after year 10: $%.2f%n", totalFourYears);

        System.out.print("Enter number of students: ");
        int numStudents = input.nextInt();

        String highestName = "";
        int highestScore = -1;
        String secondHighestName = "";
        int secondHighestScore = -1;

        for (int i = 0; i < numStudents; i++) {
            System.out.print("Enter student name and score: ");
            String name = input.next();
            int score = input.nextInt();

            if (score > highestScore) {

                secondHighestName = highestName;
                secondHighestScore = highestScore;

                highestName = name;
                highestScore = score;
            } else if (score > secondHighestScore) {
                secondHighestName = name;
                secondHighestScore = score;
            }
        }

        System.out.println("Highest score: " + highestName + " (" + highestScore + ")");
        System.out.println("Second highest score: " + secondHighestName + " (" + secondHighestScore + ")");

        input.close();
    }
}