package sandrakorpi.molnintegrationbookapp.DTOs;
//Klassen används vid inloggning.
public class UserDTO {
    private String email;
    private String password;

    public UserDTO() {
    }

    public UserDTO(String email, String password) {
        this.email = email;
        this.password = password;
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
