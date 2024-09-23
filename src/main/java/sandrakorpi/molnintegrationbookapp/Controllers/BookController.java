package sandrakorpi.molnintegrationbookapp.Controllers;

import jakarta.validation.Valid;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sandrakorpi.molnintegrationbookapp.DTOs.BookDto;
import sandrakorpi.molnintegrationbookapp.Models.Book;
import sandrakorpi.molnintegrationbookapp.Services.BookService;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/add")
    public ResponseEntity<Book> addBook(@Valid @RequestBody BookDto bookDto) {
        Book addedBook = bookService.addBook(bookDto);
        return ResponseEntity.status(HttpStatus.OK).body(addedBook);
    }
    @PostMapping("/addToList")
    public ResponseEntity<Book> addBookToList (@Valid @RequestBody BookDto bookDto, @RequestParam int userId, @RequestParam boolean isFavorite) {
        // Lägg till eller uppdatera boken för den angivna användaren och listan (favorit eller att läsa)
        Book addedOrUpdatedBook = bookService.addOrUpdateBookForUser(userId, bookDto, isFavorite);
        return ResponseEntity.status(HttpStatus.OK).body(addedOrUpdatedBook);
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> bookList = bookService.getAllBooks();
        return ResponseEntity.ok(bookList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable int id) {
        Book searchBook = bookService.getBookById(id);
        return ResponseEntity.ok(searchBook);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable int id, @Valid @RequestBody BookDto bookDto) {
        Book updatedBook = bookService.updateBook(id, bookDto);
        return ResponseEntity.ok().body(updatedBook);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable int id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}



