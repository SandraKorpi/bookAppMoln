package sandrakorpi.molnintegrationbookapp.Models;

import jakarta.persistence.*;

/**
 * Representerar en bok i systemet.
 */
@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId; // Ändrad till Long
    private String title;
    private String author;
    private int yearPublished;
    private String genre;
    @Column(name = "recommended")
    private boolean recommended = false;

    /**
     * Konstruktor för att skapa en ny bok.
     *
     * @param title Titel på boken
     * @param author Författare till boken
     * @param yearPublished År boken publicerades
     * @param genre Genre av boken
     * @param recommended Om boken är rekommenderad eller inte
     */
    public Book(
            final String title,
            final String author,
            final int yearPublished,
            final String genre,
            final boolean recommended
    ) {
        this.title = title;
        this.author = author;
        this.yearPublished = yearPublished;
        this.genre = genre;
        this.recommended = recommended; //Standardvärde
    }

    // Standardkonstruktor
    public Book() {
    }

    public Long getBookId() { // Ändrat till Long
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(final String author) {
        this.author = author;
    }

    public int getYearPublished() {
        return yearPublished;
    }

    public void setYearPublished(final int yearPublished) {
        this.yearPublished = yearPublished;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(final String genre) {
        this.genre = genre;
    }

    public boolean isRecommended() {
        return recommended;
    }

    public void setRecommended(final boolean recommended) {
        this.recommended = recommended;
    }
}
