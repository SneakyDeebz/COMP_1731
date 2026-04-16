import java.util.HashMap;

public class StudentMember extends Member {
    private String studentId;
    private String collegeName;

    private static final HashMap<String, String> collegePackages = new HashMap<>();
    static {
        collegePackages.put("WIT", "Package 1");
        collegePackages.put("UCC", "Package 2");
        collegePackages.put("TCD", "Package 2");
    }

    public StudentMember(String email, String name, String address,
            String gender, double height, double startingWeight,
            String chosenPackage, String studentId, String collegeName) {
        super(email, name, address, gender, height, startingWeight, chosenPackage);
        this.studentId = studentId;
        this.collegeName = collegeName;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setStudentId(String s) {
        this.studentId = s;
    }

    public void setCollegeName(String c) {
        this.collegeName = c;
    }

    @Override
    public void chosenPackage(String packageChoice) {
        String pkg = collegePackages.getOrDefault(collegeName, "Package 3");
        setChosenPackage(pkg);
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nStudentMember{studentId='" + studentId +
                "', collegeName='" + collegeName + "'}";
    }
}
