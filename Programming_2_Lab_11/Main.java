import java.util.Date;


public class Main {
    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("   GYM MANAGEMENT SYSTEM - PHASE 2     ");
        System.out.println("========================================\n");


        // --- Trainer (valid) ---
        System.out.println("--- Trainer ---");
        Trainer trainer = new Trainer("trainer@gym.com", "John Smith",
                "123 Gym St", "M", "Yoga");
        System.out.println(trainer);


        // --- Trainer (name truncated, gender invalid) ---
        System.out.println("\n--- Trainer (invalid gender -> Unspecified) ---");
        Trainer trainer2 = new Trainer("t2@gym.com",
                "ThisNameIsWayTooLongAndShouldBeTruncatedToThirtyChars",
                "456 Fit Ave", "X", "Pilates");
        System.out.println(trainer2);


        // --- PremiumMember ---
        System.out.println("\n--- PremiumMember ---");
        PremiumMember pm = new PremiumMember("alice@mail.com", "Alice Wonderland",
                "1 Wonder Lane", "F", 1.75, 65.0, "Package 1");
        System.out.println(pm);


        System.out.println("\n--- PremiumMember setPackage ---");
        pm.chosenPackage("Package 2");
        System.out.println("Updated package: " + pm.getChosenPackage());


        // --- PremiumMember invalid inputs ---
        System.out.println("\n--- PremiumMember invalid height & weight ---");
        PremiumMember pm2 = new PremiumMember("bob@mail.com", "Bob Builder",
                "2 Build Rd", "M", 0.5, 300, "Package 3");
        System.out.println(pm2);


        // --- StudentMember (known college) ---
        System.out.println("\n--- StudentMember (college with package) ---");
        StudentMember sm1 = new StudentMember("carol@wit.ie", "Carol Danvers",
                "WIT Campus", "F", 1.65, 58.0, "Package 1", "W123456", "WIT");
        sm1.chosenPackage("any");
        System.out.println(sm1);
        System.out.println("Assigned Package: " + sm1.getChosenPackage());


        // --- StudentMember (unknown college) ---
        System.out.println("\n--- StudentMember (unknown college -> Package 3) ---");
        StudentMember sm2 = new StudentMember("dan@xyz.ie", "Dan Brown",
                "Unknown", "M", 1.80, 80.0, "Package 1", "X999999", "XYZ University");
        sm2.chosenPackage("any");
        System.out.println("Assigned Package: " + sm2.getChosenPackage());


        // --- Assessments ---
        System.out.println("\n--- Assessments on PremiumMember ---");
        Date d1 = new Date(2024 - 1900, 0, 10);
        Date d2 = new Date(2024 - 1900, 5, 15);
        Date d3 = new Date(2024 - 1900, 2, 20);
        pm.getAssessments().put(d1, new Assessment(64.0, "Good start"));
        pm.getAssessments().put(d2, new Assessment(60.5, "Excellent progress"));
        pm.getAssessments().put(d3, new Assessment(62.0, "On track"));
        System.out.println("Sorted assessment dates:");
        for (Date d : pm.sortedAssessmentDates()) {
            System.out.println("  " + d + " -> " + pm.getAssessments().get(d));
        }
        System.out.println("Latest assessment: " + pm.latestAssessment());


        System.out.println("\n========================================");
        System.out.println("           TESTS COMPLETE               ");
        System.out.println("========================================");
    }
}
