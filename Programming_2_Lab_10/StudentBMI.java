import java.util.Scanner;

public class StudentBMI {

    // Part 1 – Private data fields (Encapsulation)

    private String name;
    private double height; // in inches
    private double weight; // in pounds

    // Constructor – initializes all fields

    public StudentBMI(String name, double height, double weight) {
        this.name = name;
        this.height = height;
        this.weight = weight;
    }

    // Method 1 – Calculate BMI
    // Formula: BMI = (weight * 703) / (height * height)

    public double calculateBMI() {
        return (weight * 703) / (height * height);
    }

    // Method 2 – Determine BMI health status

    public String getStatus() {
        double bmi = calculateBMI();

        if (bmi < 18.5) {
            return "Underweight";
        } else if (bmi < 25.0) {
            return "Normal";
        } else if (bmi < 30.0) {
            return "Overweight";
        } else {
            return "Obese";
        }
    }

    // Method 3 – Generate a formatted health report using StringBuilder
    // Part 3 – StringBuilder Report

    public String generateReport() {
        double bmi = calculateBMI();
        String status = getStatus();

        StringBuilder report = new StringBuilder();

        report.append("========================================\n");
        report.append("        STUDENT HEALTH REPORT           \n");
        report.append("========================================\n");
        report.append(String.format("  Name   : %s%n", name));
        report.append(String.format("  Height : %.1f inches%n", height));
        report.append(String.format("  Weight : %.1f lbs%n", weight));
        report.append("----------------------------------------\n");
        report.append(String.format("  BMI    : %.2f%n", bmi));
        report.append(String.format("  Status : %s%n", status));
        report.append("========================================\n");

        return report.toString();
    }

    // main method – Part 2: Object Creation and Wrapper Classes

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Collect student information
        System.out.print("Enter student name: ");
        String studentName = scanner.nextLine();

        // Height and weight are read as String, then converted with
        // Double.parseDouble()
        System.out.print("Enter height in inches: ");
        String heightInput = scanner.nextLine();
        double studentHeight = Double.parseDouble(heightInput);

        System.out.print("Enter weight in pounds: ");
        String weightInput = scanner.nextLine();
        double studentWeight = Double.parseDouble(weightInput);

        scanner.close();

        // Create a StudentBMI object
        StudentBMI student = new StudentBMI(studentName, studentHeight, studentWeight);

        // Call the report method on the object
        System.out.println();
        System.out.println(student.generateReport());
    }
}