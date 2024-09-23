package sandrakorpi.molnintegrationbookapp.Services;

import org.springframework.stereotype.Service;

@Service
public class AuthService {

    public final UserService userService;

    public AuthService(UserService userService) {
        this.userService = userService;
    }
}

/*
AuthService
Registrering och inloggning

registerUser(UserRegistrationDto userRegistrationDto): Registrera en ny användare.
authenticateUser(String username, String password): Verifiera användarens inloggningsuppgifter och returnera en autentiseringstoken.
Tokenhantering

generateToken(User user): Generera en autentiseringstoken för en inloggad användare.
validateToken(String token): Validera en autentiseringstoken.
getUserFromToken(String token): Hämta användarinformation från en autentiseringstoken.
Hantering av lösenord

changePassword(int userId, String newPassword): Ändra användarens lösenord.
Återställning av lösenord

requestPasswordReset(String email): Skicka en begäran om återställning av lösenord till den angivna e-postadressen.
resetPassword(String token, String newPassword): Återställ användarens lösenord med hjälp av en återställningstoken.
Kort översikt
Registrering och inloggning: Hantera användarregistrering och autentisering.
Tokenhantering: Skapa och verifiera autentiseringstokens för säker åtkomst.
Hantering av lösenord: Tillåt användare att byta lösenord.
Återställning av lösenord: Hantera processen för att återställa glömda lösenord.
 */
