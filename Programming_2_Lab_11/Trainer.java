public class Trainer extends Person {
    private String speciality;

    public Trainer(String email, String name, String address,
            String gender, String speciality) {
        super(email, name, address, gender);
        this.speciality = speciality;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String s) {
        this.speciality = s;
    }

    @Override
    public String toString() {
        return super.toString() + "\nTrainer{speciality='" + speciality + "'}";
    }
}
