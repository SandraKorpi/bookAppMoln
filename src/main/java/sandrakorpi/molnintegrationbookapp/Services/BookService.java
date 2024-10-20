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

@Service
public class BookService {

    private final BookRepository bookRepository;

    private final UserRepository userRepository;

    @Autowired
    public BookService(BookRepository bookRepository, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    public Book getBookById(long id) {
        return getBookOrFail(id);
    }

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

    public Book updateBook(long id, BookDto bookDto) {
        Book existingBook = getBookOrFail(id);
        existingBook.setTitle(bookDto.getTitle());
        existingBook.setAuthor(bookDto.getAuthor());
        existingBook.setGenre(bookDto.getGenre());
        existingBook.setYearPublished(bookDto.getYearPublished());
        existingBook.setRecommended(bookDto.isRecommended());
        return bookRepository.save(existingBook);
    }

    public void deleteBook(long id) {
        Book deleteBook = getBookOrFail(id);
        bookRepository.delete(deleteBook);
    }


    public List<Book> getAllBooks() {
        return bookRepository.findAll();

    }

    public Book getBookOrFail(long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("There is no book with id " + id));
    }
}