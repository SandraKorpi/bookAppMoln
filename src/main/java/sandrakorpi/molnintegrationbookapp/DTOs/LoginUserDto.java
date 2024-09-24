package sandrakorpi.molnintegrationbookapp.DTOs;

/**
 * Dataöverföringsobjekt (DTO) för användarinloggning.
 */
public class LoginUserDto {
    private String userName;
    private String password;

    public LoginUserDto() {
    }

    public LoginUserDto(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
