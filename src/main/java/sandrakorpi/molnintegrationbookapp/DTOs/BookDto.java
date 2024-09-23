package sandrakorpi.molnintegrationbookapp.DTOs;


/**
 * Representerar en bok i systemet som en Data Transfer Object (DTO).
 * Används för att överföra bokdata mellan olika lager i applikationen.
 */
public class BookDto {

    private int bookId;
    private String title;
    private String author;
    private int yearPublished;
    private String genre;
    private boolean recommended;

    /**
     * Konstruktor för att skapa en ny bok DTO.
     *
     * @param bookId ID för boken
     * @param title Titel på boken
     * @param author Författare till boken
     * @param yearPublished År boken publicerades
     * @param genre Genre av boken
     * @param isRecommended Om boken är rekommenderad eller inte
     */
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

    /**
     * Hämtar bok-ID.
     *
     * @return bok-ID
     */
    public int getBookId() {
        return bookId;
    }

    /**
     * Sätter bok-ID.
     *
     * @param bookId bok-ID att sätta
     */
    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    /**
     * Hämtar titel på boken.
     *
     * @return titel på boken
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sätter titel på boken.
     *
     * @param title titel att sätta
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Hämtar författare av boken.
     *
     * @return författare av boken
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Sätter författare av boken.
     *
     * @param author författare att sätta
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Hämtar året boken publicerades.
     *
     * @return år boken publicerades
     */
    public int getYearPublished() {
        return yearPublished;
    }

    /**
     * Sätter året boken publicerades.
     *
     * @param yearPublished år att sätta
     */
    public void setYearPublished(int yearPublished) {
        this.yearPublished = yearPublished;
    }

    /**
     * Hämtar genre av boken.
     *
     * @return genre av boken
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Sätter genre av boken.
     *
     * @param genre genre att sätta
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     * Kontrollerar om boken är rekommenderad.
     *
     * @return true om boken är rekommenderad, annars false
     */
    public boolean isRecommended() {
        return recommended;
    }

    /**
     * Sätter om boken är rekommenderad.
     *
     * @param isRecommended true om boken ska vara rekommenderad, annars false
     */
    public void setIsRecommended(boolean isRecommended) {
        this.recommended = isRecommended;
    }
}
