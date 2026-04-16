import java.util.Scanner;

public class Lab4 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("==============================================");
        System.out.println("Lab 4: Mathematical Functions, Characters, and Strings");
        System.out.println("==============================================\n");

        System.out.println("PART 1: MATHEMATICAL FUNCTIONS");
        System.out.println("----------------------------------------------");
        System.out.print("Enter a positive double value: ");
        double number = input.nextDouble();

        double squareRoot = Math.sqrt(number);

        double powerOfTwo = Math.pow(number, 2);

        long rounded = Math.round(number);

        double ceiling = Math.ceil(number);

        double floor = Math.floor(number);

        System.out.println("\nResults for value: " + number);
        System.out.printf("Square root: %.2f%n", squareRoot);
        System.out.printf("Value raised to power of 2: %.2f%n", powerOfTwo);
        System.out.printf("Rounded value: %d%n", rounded);
        System.out.printf("Ceiling value: %.2f%n", ceiling);
        System.out.printf("Floor value: %.2f%n", floor);

        System.out.println("\n\nPART 2: CHARACTER PROCESSING AND UNICODE");
        System.out.println("----------------------------------------------");
        System.out.print("Enter a single character: ");
        char character = input.next().charAt(0);

        String charType;
        if (Character.isUpperCase(character)) {
            charType = "uppercase letter";
        } else if (Character.isLowerCase(character)) {
            charType = "lowercase letter";
        } else if (Character.isDigit(character)) {
            charType = "digit";
        } else {
            charType = "other character";
        }

        int unicodeValue = (int) character;

        char nextCharacter = (char) (character + 1);

        System.out.println("\nCharacter Analysis:");
        System.out.println("Character entered: " + character);
        System.out.println("Character type: " + charType);
        System.out.println("Unicode value: " + unicodeValue);
        System.out.println("Next character in Unicode: " + nextCharacter);

        System.out.println("\n\nPART 3: STRING MANIPULATION");
        System.out.println("----------------------------------------------");
        input.nextLine();
        System.out.print("Enter your full name (first and last name): ");
        String fullName = input.nextLine();

        int nameLength = fullName.length();

        char firstChar = fullName.charAt(0);

        int spaceIndex = fullName.indexOf(' ');

        String firstName = fullName.substring(0, spaceIndex);
        String lastName = fullName.substring(spaceIndex + 1);

        String upperCaseName = fullName.toUpperCase();

        System.out.println("\nString Analysis:");
        System.out.println("Full name: " + fullName);
        System.out.println("Length of the name: " + nameLength);
        System.out.println("First character: " + firstChar);
        System.out.println("First name: " + firstName);
        System.out.println("Last name: " + lastName);
        System.out.println("Name in uppercase: " + upperCaseName);

        System.out.println("\n\n==============================================");
        System.out.println("PART 4: FORMATTED OUTPUT SUMMARY");
        System.out.println("==============================================");
        System.out.printf("%-30s %.2f%n", "Number entered:", number);
        System.out.printf("%-30s %.2f%n", "Square root:", squareRoot);
        System.out.printf("%-30s %.2f%n", "Squared value:", powerOfTwo);
        System.out.printf("%-30s %c (Unicode: %d)%n", "Character entered:", character, unicodeValue);
        System.out.printf("%-30s %s%n", "Character type:", charType);
        System.out.printf("%-30s %s%n", "Full name:", fullName);
        System.out.printf("%-30s %s%n", "First name:", firstName);
        System.out.printf("%-30s %s%n", "Last name:", lastName);
        System.out.println("==============================================");
        System.out.println("Lab 4 completed successfully!");
        System.out.println("==============================================");

        input.close();
    }
}
