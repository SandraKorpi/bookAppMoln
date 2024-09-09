package sandrakorpi.molnintegrationbookapp.DTOs;
//Klassen används vid registreringen.
public class UserRegistrationDto {
    private String userName;
    private String email;
    private String password; // Lösenordet kommer att hashas innan det sparas

    public UserRegistrationDto() {
    }

    public UserRegistrationDto(String userName, String email, String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    // Getter och Setter för userName
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    // Getter och Setter för email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Getter och Setter för password
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
