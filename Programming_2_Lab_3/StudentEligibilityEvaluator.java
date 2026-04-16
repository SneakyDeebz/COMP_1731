import java.util.Scanner;


public class StudentEligibilityEvaluator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input collection

        System.out.println("=== Student Eligibility Evaluator ===");

        System.out.print ("Enter student's name: ");
        String studentName = scanner.nextLine();

        System.out.print("Enter GPA (0.0 - 4.0): ");
        double gpa = scanner.nextDouble();

        System.out.print("Enter completed credit hours: ");
        int creditHours = scanner.nextInt();

        scanner.nextLine();

        System.out.print("Enter Major (CS, ENG, BUS, or other): ");
        String major = scanner.nextLine().toUpperCase();

        System.out.print("Has financial hold? (true/false): ");
        boolean hasFinancialHold = scanner.nextBoolean();

        System.out.print("Has disciplinary record? (true/false): ");
        boolean hasDisciplinaryRecord = scanner.nextBoolean();

        System.out.println("\n" + "=".repeat(50));

        // 1.Input Validation 

        boolean isValidInput = true;
        String validationMessage = "";

        if (gpa < 0.0 || gpa > 4.0) {
            isValidInput = false;
            validationMessage = "ERROR: GPA must be between 0.0 and 4.0.";
        } else if (creditHours < 0) {
            isValidInput = false;
            validationMessage = "ERROR: Completed credit hours cannot be negative.";
        }
        if (!isValidInput) {
            System.out.println(validationMessage);
            scanner.close();
            return;
        }

        // 2. Determine Academic Standing using multi-way if-else

        String academicStanding;
        if (gpa >= 3.5) {
            academicStanding = "Dean's List";
        } else if (gpa >= 3.0) {
            academicStanding = "Good Standing";
        } else if (gpa >= 2.0) {
            academicStanding = "Satisfactory";
        } else if (gpa >= 1.0) {
            academicStanding = "Academic Warning";
        } else {
            academicStanding = "Academic Probation";
        }
        
        // 3. Determine Eligibility using logical operators

        boolean isEligible = gpa >= 2.0 && creditHours >= 30 && !hasFinancialHold && !hasDisciplinaryRecord;

        // 4. Determine Scholarship Status

        String scholarshipStatus = (gpa >= 3.8 && creditHours >= 60) ? "Full Scholarship" :
                                    (gpa >= 3.5 && creditHours >= 45) ? "Partial Scholarship" :
                                    (gpa >= 3.0 && creditHours >= 30) ? "Merit Award" :
                                    "No Scholarship";
        
        // 5. Determine Tuition Rate

        int tuitionRate;

        switch (major) {
            case "CS":
                tuitionRate = 550;
                break;
            case "ENG":
                tuitionRate = 500;
                break;
            case "BUS":
                tuitionRate = 450;
                break;
            default:
                tuitionRate = 400;
                break;
        }
        
        // 6. Operator Precedence Demonstration
        // Evaluated as: gpa > 3.0 || (creditHours > 60 && !hasFinancialHold))
        boolean priorityNoParens = gpa > 3.0 || creditHours > 60 && !hasFinancialHold;

        // Evaluated as: (gpa > 3.0 || creditHours > 60) && !hasFinancialHold
        boolean priorityWithParens = (gpa > 3.0 || creditHours > 60) && !hasFinancialHold;

        // Output Formatting

        System.out.println("\n*** STUDENT EVALUATION REPORT ***\n");
        System.out.println("Student: " + studentName);
        System.out.println("Academic Standing: " + academicStanding);
        System.out.println("Eligible for Program: " + isEligible);
        System.out.println("Scholarship Status: " + scholarshipStatus);
        System.out.println("Tuition Rate per Credit: $" + tuitionRate);
        System.out.println("\n--- Operator Precedence Demonstration ---");
        System.out.println("Expression: gpa > 3.0 || creditHours > 60 && !hasFinancialHold");
        System.out.println("Priority Check (no parentheses): " + priorityNoParens);
        System.out.println("  [Evaluated as: gpa > 3.0 || (creditHours > 60 && !hasFinancialHold)]");
        System.out.println("\nExpression: (gpa > 3.0 || creditHours > 60) && !hasFinancialHold");
        System.out.println("Priority Check (with parentheses): " + priorityWithParens);
        System.out.println("  [Evaluated as: (gpa > 3.0 || creditHours > 60) && !hasFinancialHold]");
        System.out.println("\n" + "=".repeat(50));

        scanner.close();
        

    }
}