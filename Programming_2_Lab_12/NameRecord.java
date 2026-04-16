public class NameRecord {
    private int year;
    private int rank;

    public NameRecord(int year, int rank) {
        this.year = year;
        this.rank = rank;
    }

    public int getYear() {
        return year;
    }

    public int getRank() {
        return rank;
    }

    // Method overriding
    @Override
    public String toString() {
        if (rank == -1) {
            return "Year: " + year + " - Name not ranked";
        }
        return "Year: " + year + ", Rank: " + rank;
    }
}
