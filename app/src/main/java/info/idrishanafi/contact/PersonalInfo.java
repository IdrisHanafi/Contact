package info.idrishanafi.contact;

/**
 * Created by Idris on 5/9/16.
 */
public class PersonalInfo {
    public static String firstName = "first";
    public static String lastName = "last";
    public static String phone = "phone";
    public static String email = "email";

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        PersonalInfo.email = email;
    }

    public static String getPhone() {
        return phone;
    }

    public static void setPhone(String phone) {
        PersonalInfo.phone = phone;
    }

    public static String getFirstName() {
        return firstName;
    }

    public static void setFirstName(String firstName) {
        PersonalInfo.firstName = firstName;
    }

    public static String getLastName() {
        return lastName;
    }

    public static void setLastName(String lastName) {
        PersonalInfo.lastName = lastName;
    }
}
