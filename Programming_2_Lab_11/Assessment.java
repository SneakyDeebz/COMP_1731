public class Assessment {
    private double weight;
    private String comment;

    public Assessment(double weight, String comment) {
        this.weight = weight;
        this.comment = comment;
    }

    public double getWeight() {
        return weight;
    }

    public String getComment() {
        return comment;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Assessment{weight=" + weight + ", comment='" + comment + "'}";
    }
}
