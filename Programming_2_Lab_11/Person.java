public abstract class Person {
    private String email;
    private String name;
    private String address;
    private String gender;

    public Person(String email, String name, String address, String gender) {
        this.email = email;
        setName(name);
        this.address = address;
        setGender(gender);
    }

    // Accessors
    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getGender() {
        return gender;
    }

    // Mutators
    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        if (name != null && name.length() > 30)
            this.name = name.substring(0, 30);
        else
            this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setGender(String gender) {
        if ("M".equals(gender) || "F".equals(gender))
            this.gender = gender;
        else
            this.gender = "Unspecified";
    }

    @Override
    public String toString() {
        return "Person{email='" + email + "', name='" + name +
                "', address='" + address + "', gender='" + gender + "'}";
    }
}
