package sandrakorpi.molnintegrationbookapp.DTOs;


/**
 * Representerar en bok i systemet som en Data Transfer Object (DTO).
 * Används för att överföra bokdata mellan olika lager i applikationen.
 */
public class BookDto {

    private long bookId;
    private String title;
    private String author;
    private int yearPublished;
    private String genre;
    private boolean recommended;


    public BookDto(int bookId, String title, String author, int yearPublished, String genre, boolean isRecommended) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.yearPublished = yearPublished;
        this.genre = genre;
        this.recommended = isRecommended;
    }

    // Standardkonstruktor
    public BookDto() {
    }

    public long getBookId() {
        return bookId;
    }
    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYearPublished() {
        return yearPublished;
    }

    public void setYearPublished(int yearPublished) {
        this.yearPublished = yearPublished;
    }


    public String getGenre() {
        return genre;
    }


    public void setGenre(String genre) {
        this.genre = genre;
    }

    public boolean isRecommended() {
        return recommended;
    }

    public void setIsRecommended(boolean isRecommended) {
        this.recommended = isRecommended;
    }
}
