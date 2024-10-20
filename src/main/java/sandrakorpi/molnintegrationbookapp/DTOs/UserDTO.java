package sandrakorpi.molnintegrationbookapp.DTOs;

public class UserDTO {
    private String email;

    private String userName;

    public UserDTO() {
    }

    public UserDTO(final String email, final String userName) {
        this.email = email;
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getUserName(){
        return userName;
    }

    public void setUserName(final String userName){
        this.userName = userName;
    }

}
