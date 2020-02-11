package pl.csanecki.bookapp.book.endpoint.model;

import java.util.Objects;

public class BookForm {
    public final String title;
    public final String author;
    public final String publisher;
    public final String publicationYear;
    public final int numberOfPages;

    public BookForm(String title, String author, String publisher, String publicationYear, int numberOfPages) {
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
        BookForm bookForm = (BookForm) o;
        return numberOfPages == bookForm.numberOfPages &&
                Objects.equals(title, bookForm.title) &&
                Objects.equals(author, bookForm.author) &&
                Objects.equals(publisher, bookForm.publisher) &&
                Objects.equals(publicationYear, bookForm.publicationYear);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author, publisher, publicationYear, numberOfPages);
    }
}