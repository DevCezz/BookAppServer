package pl.csanecki.bookapp.book.endpoint.model;

import java.util.Objects;

public class Book {
    public final long id;
    public final String title;
    public final String author;
    public final String publisher;
    public final String publicationYear;
    public final int numberOfPages;
    public final boolean deactivated;

    public Book(long id, String title, String author, String publisher, String publicationYear, int numberOfPages, boolean deactivated) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.publicationYear = publicationYear;
        this.numberOfPages = numberOfPages;
        this.deactivated = deactivated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id == book.id &&
                numberOfPages == book.numberOfPages &&
                deactivated == book.deactivated &&
                Objects.equals(title, book.title) &&
                Objects.equals(author, book.author) &&
                Objects.equals(publisher, book.publisher) &&
                Objects.equals(publicationYear, book.publicationYear);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, author, publisher, publicationYear, numberOfPages, deactivated);
    }
}