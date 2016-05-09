package info.idrishanafi.contact;

/**
 * Created by Idris on 5/2/16.
 */
public class Contacts {
    private int id;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String imageName;
    private int icon;

    public Contacts(int id, String firstName, String lastName, String phone, String email, String imageName, int icon) {
        super();
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.imageName = imageName;
        this.icon = icon;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImageName() { return imageName; }

    public void setImageName(String imageName) { this.imageName = imageName; }

    public int getIcon() { return icon; }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }
}
