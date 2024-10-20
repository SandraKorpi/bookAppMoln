package sandrakorpi.molnintegrationbookapp.Services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import sandrakorpi.molnintegrationbookapp.DTOs.BookDto;
import sandrakorpi.molnintegrationbookapp.Models.Book;
import sandrakorpi.molnintegrationbookapp.Repositories.BookRepository;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    private Book mockBook;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockBook = new Book();
        mockBook.setGenre("mockGenre");
        mockBook.setTitle("mockTitle");
        mockBook.setAuthor("mockAuthor");
        mockBook.setYearPublished(2019);
        mockBook.setRecommended(true);
        mockBook.setBookId(1L);
    }

    @Test
    void getBookById() {
        //testa metoden
        when(bookRepository.findById(mockBook.getBookId())).thenReturn(Optional.of(mockBook));
        Book bookResult = bookService.getBookById(mockBook.getBookId());
        assertNotNull(bookResult);
//Jämföra värdena.
assertEquals(mockBook.getTitle(), bookResult.getTitle());
assertEquals(mockBook.getBookId(), bookResult.getBookId());
assertEquals(mockBook.getAuthor(), bookResult.getAuthor());
assertEquals(mockBook.getGenre(), bookResult.getGenre());
assertEquals(mockBook.getYearPublished(), bookResult.getYearPublished());
assertEquals(mockBook.isRecommended(), bookResult.isRecommended());
;

    }

    @Test
    void addBook() {
        when(bookRepository.save(any(Book.class))).thenReturn(mockBook);
        BookDto mockDto = new BookDto();
        mockDto.setBookId(mockBook.getBookId());
        mockDto.setTitle(mockBook.getTitle());
        mockDto.setGenre(mockBook.getGenre());
        mockDto.setAuthor(mockBook.getAuthor());
        mockDto.setIsRecommended(mockBook.isRecommended());

        Book bookResult = bookService.addBook(mockDto);

assertNotNull(bookResult);
assertEquals(mockDto.getTitle(), bookResult.getTitle());
        verify(bookRepository).save(any(Book.class));
    }

    @Test
    void updateBook() {
        // Mocka att boken hittas
        when(bookRepository.findById(1L)).thenReturn(Optional.of(mockBook));

        // Mocka att `save` returnerar `mockBook` och ger den ett ID
        when(bookRepository.save(any(Book.class))).thenAnswer(invocation -> {
            Book savedBook = invocation.getArgument(0);
            savedBook.setBookId(1L); // Simulera att databasen ger ID:t
            return savedBook;
        });

        // Skapa en BookDto för att uppdatera
        BookDto bookDto = new BookDto();
        bookDto.setIsRecommended(false);
        bookDto.setGenre("horror");

        // Anropa uppdateringen
        bookService.updateBook(1L, bookDto);

        // Verifiera att mockBook har uppdaterats
        assertFalse(mockBook.isRecommended());
        assertEquals("horror", mockBook.getGenre());
    }
    @Test
    void deleteBook() {
        when(bookRepository.findById(1L)).thenReturn(Optional.of(mockBook));
        bookService.deleteBook(mockBook.getBookId());
        verify(bookRepository).delete(mockBook);
    }

    @Test
    void getAllBooks() {
    }

    @Test
    void getBookOrFail() {
    }
}