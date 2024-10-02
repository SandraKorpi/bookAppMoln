package sandrakorpi.molnintegrationbookapp.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sandrakorpi.molnintegrationbookapp.DTOs.BookDto;
import sandrakorpi.molnintegrationbookapp.Models.Book;
import sandrakorpi.molnintegrationbookapp.Models.User;
import sandrakorpi.molnintegrationbookapp.Repositories.BookRepository;
import sandrakorpi.molnintegrationbookapp.Repositories.UserRepository;
import sandrakorpi.molnintegrationbookapp.exceptions.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

/**
 * Service-klass som hanterar affärslogik för böcker.
 */
@Service
public class BookService {

    private final BookRepository bookRepository;

    private final UserRepository userRepository;

    @Autowired
    public BookService(BookRepository bookRepository, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    /**
     * Hämtar en bok baserat på dess ID.
     *
     * @param id ID för boken som ska hämtas
     * @return en {@link Book} om den finns
     * @throws ResourceNotFoundException om boken inte finns
     */
    public Book getBookById(int id) {
        return getBookOrFail(id);
    }

    /**
     * Lägger till en ny bok i databasen.
     *
     * @param bookDto DTO med bokinformation
     * @return den sparade {@link Book}
     */
    public Book addBook(BookDto bookDto) {
        Book book = new Book(
                bookDto.getTitle(),
                bookDto.getAuthor(),
                bookDto.getYearPublished(),
                bookDto.getGenre(),
                bookDto.isRecommended()
        );
        return bookRepository.save(book);
    }

    /**
     * Uppdaterar en befintlig bok baserat på dess ID.
     *
     * @param id      ID för boken som ska uppdateras
     * @param bookDto DTO med ny bokinformation
     * @return den uppdaterade {@link Book}
     */
    public Book updateBook(int id, BookDto bookDto) {
        Book existingBook = getBookOrFail(id);
        existingBook.setTitle(bookDto.getTitle());
        existingBook.setAuthor(bookDto.getAuthor());
        existingBook.setGenre(bookDto.getGenre());
        existingBook.setYearPublished(bookDto.getYearPublished());
        existingBook.setRecommended(bookDto.isRecommended());
        return bookRepository.save(existingBook);
    }

    /**
     * Tar bort en bok baserat på dess ID.
     *
     * @param id ID för boken som ska tas bort
     */
    public void deleteBook(int id) {
        Book deleteBook = getBookOrFail(id);
        bookRepository.delete(deleteBook);
    }

    /**
     * Hämtar alla böcker från databasen.
     *
     * @return en lista med alla {@link Book}
     */
    public List<Book> getAllBooks() {
        return bookRepository.findAll();

    }

    /**
     * Hämtar en bok baserat på dess ID och kastar ett undantag om boken inte finns.
     *
     * @param id ID för boken som ska hämtas
     * @return {@link Book} om den finns
     * @throws ResourceNotFoundException om boken inte finns
     */
    public Book getBookOrFail(int id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("There is no book with id " + id));
    }

    // Metod för att skapa eller hämta en bok baserat på dess ID och lägga till den i en användares lista
   /* public Book addOrUpdateBookForUser(int userId, BookDto bookDto, boolean isFavorite) {
        // Försök att hämta boken från databasen
        Book book = bookRepository.findById(bookDto.getBookId())
                .orElseGet(() -> {
                    // Om boken inte finns, skapa en ny bok
                    Book newBook = new Book(bookDto.getTitle(), bookDto.getAuthor(), bookDto.getYearPublished(), bookDto.getGenre(), bookDto.isRecommended());
                    return bookRepository.save(newBook); // Spara den nya boken i databasen
                });

        // Hämta användaren baserat på ID
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("There is no user with id " + userId));

        // Lägg till boken i den angivna listan för användaren
        if (isFavorite) {
            user.getFavoriteBooks().add(book);
        } else {
            user.getBooksToRead().add(book);
        }

        userRepository.save(user); // Spara användaren med den uppdaterade listan
        return book;
    }
*/
}