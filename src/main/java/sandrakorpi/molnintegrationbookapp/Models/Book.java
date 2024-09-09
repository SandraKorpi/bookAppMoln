package sandrakorpi.molnintegrationbookapp.Models;

public class Book {
    private int bookId;
    private String title;
    private String author;
    private int yearPublished;
    private String genre;
    private boolean isRecommended;  // Ja eller nej på om användaren rekommenderar boken

    // Konstruktor
    public Book(int bookId, String title, String author, int yearPublished, String genre, boolean isRecommended) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.yearPublished = yearPublished;
        this.genre = genre;
        this.isRecommended = isRecommended;
    }

    // Getter och Setter för bookId
    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    // Getter och Setter för title
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // Getter och Setter för author
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    // Getter och Setter för yearPublished
    public int getYearPublished() {
        return yearPublished;
    }

    public void setYearPublished(int yearPublished) {
        this.yearPublished = yearPublished;
    }

    // Getter och Setter för genre
    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    // Getter och Setter för recommend
    public boolean isRecommended() {
        return isRecommended;
    }

    public void setIsRecommended(boolean isRecommended) {
        this.isRecommended = isRecommended;
    }
}
