package sandrakorpi.molnintegrationbookapp.Controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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

@Tag(name = "Book", description = "Operationer relaterade till böcker")
@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @Operation(summary = "Lägg till en ny bok")
    @PostMapping("/add")
    public ResponseEntity<Book> addBook(@Valid @RequestBody BookDto bookDto) {
        Book addedBook = bookService.addBook(bookDto);
        return ResponseEntity.status(HttpStatus.OK).body(addedBook);
    }


    @Operation(summary = "Hämta alla böcker")
    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> bookList = bookService.getAllBooks();
        return ResponseEntity.ok(bookList);
    }


    @Operation(summary = "Hämta bok med specifikt ID")
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable int id) {
        Book searchBook = bookService.getBookById(id);
        return ResponseEntity.ok(searchBook);
    }

    @Operation(summary = "Uppdatera en bok")
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable int id, @Valid @RequestBody BookDto bookDto) {
        Book updatedBook = bookService.updateBook(id, bookDto);
        return ResponseEntity.ok().body(updatedBook);
    }

    @Operation(summary = "Ta bort en bok")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable int id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}
