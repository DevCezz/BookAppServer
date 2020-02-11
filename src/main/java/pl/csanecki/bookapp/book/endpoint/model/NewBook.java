package pl.csanecki.bookapp.book.endpoint.model;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewBook newBook = (NewBook) o;
        return numberOfPages == newBook.numberOfPages &&
                Objects.equals(title, newBook.title) &&
                Objects.equals(author, newBook.author) &&
                Objects.equals(publisher, newBook.publisher) &&
                Objects.equals(publicationYear, newBook.publicationYear);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author, publisher, publicationYear, numberOfPages);
    }
}