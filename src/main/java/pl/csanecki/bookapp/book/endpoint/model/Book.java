package pl.csanecki.bookapp.book.endpoint.model;

public class Book {
    public final long id;
    public final String title;
    public final String author;
    public final String publisher;
    public final String publicationYear;
    public final int numberOfPages;

    public Book(long id, String title, String author, String publisher, String publicationYear, int numberOfPages) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.publicationYear = publicationYear;
        this.numberOfPages = numberOfPages;
    }
}
