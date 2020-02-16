package pl.csanecki.bookapp.book.db;

import pl.csanecki.bookapp.book.endpoint.model.Book;
import pl.csanecki.bookapp.book.endpoint.model.BookForm;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class BookRow {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String title;

    private String author;

    private String publisher;

    private String publicationYear;

    private int numberOfPages;

    private boolean read;

    private LocalDateTime lastModificationTimestamp;

    protected BookRow() {
    }

    public BookRow(String title, String author, String publisher, String publicationYear, int numberOfPages) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.publicationYear = publicationYear;
        this.numberOfPages = numberOfPages;
        this.read = false;
        this.lastModificationTimestamp = LocalDateTime.now();
    }

    public Book toBook() {
        return new Book(
            this.id,
            this.title,
            this.author,
            this.publisher,
            this.publicationYear,
            this.numberOfPages,
            this.read
        );
    }

    public void updateBookData(BookForm bookForm) {
        this.title = bookForm.title;
        this.author = bookForm.author;
        this.publisher = bookForm.publisher;
        this.publicationYear = bookForm.publicationYear;
        this.numberOfPages = bookForm.numberOfPages;

        updateLastModificationTimestamp();
    }

    public void markAsRead() {
        this.read = true;

        updateLastModificationTimestamp();
    }

    public void unmarkAsRead() {
        this.read = false;

        updateLastModificationTimestamp();
    }

    private void updateLastModificationTimestamp() {
        this.lastModificationTimestamp = LocalDateTime.now();
    }
}
