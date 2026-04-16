import java.util.*;

public abstract class Member extends Person {
    private double height;
    private double startingWeight;
    private String chosenPackage;
    private HashMap<Date, Assessment> assessments;

    public Member(String email, String name, String address, String gender,
            double height, double startingWeight, String chosenPackage) {
        super(email, name, address, gender);
        setHeight(height);
        setStartingWeight(startingWeight);
        this.chosenPackage = chosenPackage;
        this.assessments = new HashMap<>();
    }

    // Accessors
    public double getHeight() {
        return height;
    }

    public double getStartingWeight() {
        return startingWeight;
    }

    public String getChosenPackage() {
        return chosenPackage;
    }

    public HashMap<Date, Assessment> getAssessments() {
        return assessments;
    }

    // Mutators
    public void setHeight(double height) {
        if (height >= 1 && height <= 3)
            this.height = height;
        else
            System.out.println("Invalid height. Must be between 1 and 3 metres.");
    }

    public void setStartingWeight(double w) {
        if (w >= 35 && w <= 250)
            this.startingWeight = w;
        else
            System.out.println("Invalid weight. Must be between 35 and 250 kg.");
    }

    public void setChosenPackage(String p) {
        this.chosenPackage = p;
    }

    public void setAssessments(HashMap<Date, Assessment> a) {
        this.assessments = a;
    }

    public Assessment latestAssessment() {
        if (assessments.isEmpty())
            return null;
        Date latestDate = Collections.max(assessments.keySet());
        return assessments.get(latestDate);
    }

    public SortedSet<Date> sortedAssessmentDates() {
        return new TreeSet<>(assessments.keySet());
    }

    public abstract void chosenPackage(String chosenPackage);

    @Override
    public String toString() {
        return super.toString() + "\nMember{height=" + height +
                ", startingWeight=" + startingWeight +
                ", chosenPackage='" + chosenPackage + "'}";
    }
}
