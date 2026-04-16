import java.util.Scanner;

/**
 * Exercise 2.21: Financial Application - Calculate Future Investment Value
 * This program calculates the future value of an investment based on the
 * investment amount, annual interest rate, and number of years.
 * Formula: futureInvestmentValue = investmentAMount x (1 + monthly
 * InterestRate)^(numberOfYears x 12)
 * 
 * @author Gavin Sullivan
 * @version 1.0
 */

public class Exercise02_21 {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        double investmentAmount;
        double annualInterestRate;
        int numberOfYears;
        double monthlyInterestRate;
        double futureInvestmentValue;

        System.out.print("Enter the investment amount, for example 12000.95: ");
        investmentAmount = input.nextDouble();

        while (investmentAmount < 0) {
            System.out.print("Invalid amount. Please enter a positive value: ");

        }

        System.out.print("Enter the annual interest rate, for example 8.25: ");
        annualInterestRate = input.nextDouble();

        while (annualInterestRate <= 0 || annualInterestRate > 100) {
            System.out.print("Invalid interest rate. Please enter a value between 0 and 100: ");
            annualInterestRate = input.nextDouble();
        }

        System.out.print("Enter number of years as an integer, for example 5: ");
        numberOfYears = input.nextInt();

        while (numberOfYears <= 0) {
            System.out.print("Invalid Years. Please enter a positive integer: ");
        }

        monthlyInterestRate = annualInterestRate / 100 / 12;

        futureInvestmentValue = investmentAmount * Math.pow(1 + monthlyInterestRate, numberOfYears * 12);

        System.out.printf("Future value is $%.2f%n", futureInvestmentValue);

        input.close();
    }
}
