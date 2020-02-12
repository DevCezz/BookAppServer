package pl.csanecki.bookapp.book.db;

import pl.csanecki.bookapp.book.endpoint.model.Book;

import javax.persistence.*;

@Entity
public class BookRow {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String title;

    private String author;

    private String publisher;

    private String publicationYear;

    private int numberOfPages;

    private boolean deactivated;

    protected BookRow() {
    }

    public BookRow(String title, String author, String publisher, String publicationYear, int numberOfPages) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.publicationYear = publicationYear;
        this.numberOfPages = numberOfPages;
        this.deactivated = false;
    }

    public Book toBook() {
        return new Book(
            this.getId(),
            this.getTitle(),
            this.getAuthor(),
            this.getPublisher(),
            this.getPublicationYear(),
            this.getNumberOfPages(),
            this.isDeactivated()
        );
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(String publicationYear) {
        this.publicationYear = publicationYear;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public boolean isDeactivated() {
        return deactivated;
    }

    public void setDeactivated(boolean deactivated) {
        this.deactivated = deactivated;
    }
}
