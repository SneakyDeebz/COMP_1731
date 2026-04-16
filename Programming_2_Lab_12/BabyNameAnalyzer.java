import java.util.*;
import java.net.*;

public class BabyNameAnalyzer {

    public static ArrayList<NameRecord> getNameData(int startYear, int endYear, String gender, String name) {
        ArrayList<NameRecord> records = new ArrayList<>();

        for (int year = startYear; year <= endYear; year++) {
            try {
                String urlStr = "https://liveexample.pearsoncmg.com/data/babynamesranking" + year + ".txt";
                URL url = new URL(urlStr);
                Scanner input = new Scanner(url.openStream());

                int rank = -1;

                while (input.hasNext()) {
                    int r = input.nextInt();
                    String boyName = input.next();
                    int boyCount = input.nextInt();
                    String girlName = input.next();
                    int girlCount = input.nextInt();

                    if (gender.equalsIgnoreCase("M") && boyName.equalsIgnoreCase(name)) {
                        rank = r;
                        break;
                    } else if (gender.equalsIgnoreCase("F") && girlName.equalsIgnoreCase(name)) {
                        rank = r;
                        break;
                    }
                }

                records.add(new NameRecord(year, rank));
                input.close();

            } catch (Exception e) {
                System.out.println("Error reading data for year: " + year);
            }
        }

        return records;
    }

    public static void analyze(ArrayList<NameRecord> records) {

        int best = Integer.MAX_VALUE;
        int worst = Integer.MIN_VALUE;
        int sum = 0;
        int count = 0;

        for (NameRecord rec : records) {
            int rank = rec.getRank();

            if (rank != -1) {
                best = Math.min(best, rank);
                worst = Math.max(worst, rank);
                sum += rank;
                count++;
            }
        }

        double avg = (count > 0) ? (double) sum / count : 0;

        // Trend detection
        String trend = "Stable";
        int improving = 0, declining = 0;

        for (int i = 1; i < records.size(); i++) {
            int prev = records.get(i - 1).getRank();
            int curr = records.get(i).getRank();

            if (prev == -1 || curr == -1)
                continue;

            if (curr < prev)
                improving++;
            else if (curr > prev)
                declining++;
        }

        if (improving > declining)
            trend = "Improving";
        else if (declining > improving)
            trend = "Declining";

        // Output results
        System.out.println("\n--- Results ---");

        for (Object obj : records) {
            NameRecord rec = (NameRecord) obj;
            System.out.println(rec);
        }

        if (count > 0) {
            System.out.println("Best Rank: " + best);
            System.out.println("Worst Rank: " + worst);
            System.out.println("Average Rank: " + avg);
        } else {
            System.out.println("No ranking data available.");
        }

        System.out.println("Trend: " + trend);
    }
}
