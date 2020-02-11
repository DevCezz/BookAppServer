package pl.csanecki.bookapp.book.endpoint.model;

public class NewBook {
    public final String title;
    public final String author;
    public final String publisher;
    public final String publicationYear;
    public final int numberOfPages;

    public NewBook(String title, String author, String publisher, String publicationYear, int numberOfPages) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.publicationYear = publicationYear;
        this.numberOfPages = numberOfPages;
    }
}