package sandrakorpi.molnintegrationbookapp.Models;

import java.util.List;

public class User {
    private int userId;
   private String userName;
   private String email;

  private  List<Book> bookList;

    //Bör lagra haschat lösenord i databasen. Görs när användaren anger lösen
    //Steg för lösenordshashning:
    //Vid registrering/uppdatering: Du hashar användarens lösenord och sparar det hashade lösenordet i databasen.
    //Vid inloggning: Du hashar det inmatade lösenordet och jämför det med det sparade hashade lösenordet.
    //Bcrypt/spring security
    String password;

    // Konstruktor
    public User(int userId, String userName, String email, String password, List<Book> bookList) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.bookList = bookList;
    }

    // Getter och Setter för userId
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }
}
