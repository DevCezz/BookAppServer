package pl.csanecki.bookapp.book.endpoint;

import io.vavr.collection.List;
import pl.csanecki.bookapp.book.endpoint.model.Book;
import pl.csanecki.bookapp.book.endpoint.model.NewBook;

public interface BookService {
    List<Book> getBooks();
    Book addBook(final NewBook newBook);
}