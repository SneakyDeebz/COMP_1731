import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter start year (2001-2010): ");
        int start = sc.nextInt();

        System.out.print("Enter end year (2001-2010): ");
        int end = sc.nextInt();

        System.out.print("Enter gender (M/F): ");
        String gender = sc.next();

        System.out.print("Enter name: ");
        String name = sc.next();

        ArrayList<NameRecord> data = BabyNameAnalyzer.getNameData(start, end, gender, name);

        BabyNameAnalyzer.analyze(data);

        sc.close();
    }
}
