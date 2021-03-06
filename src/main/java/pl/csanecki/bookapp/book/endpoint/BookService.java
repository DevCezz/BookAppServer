package pl.csanecki.bookapp.book.endpoint;

import io.vavr.collection.List;
import pl.csanecki.bookapp.book.endpoint.model.Book;
import pl.csanecki.bookapp.book.endpoint.model.BookForm;

import java.util.Optional;

public interface BookService {
    List<Book> getAllBooks();
    Book addBook(final BookForm bookForm);
    Optional<Book> changeBookData(long bookId, BookForm bookForm);
    Optional<Book> deleteBookById(long bookId);

    Optional<Book> markAsRead(long bookId);
    Optional<Book> unmarkAsRead(long bookId);
}