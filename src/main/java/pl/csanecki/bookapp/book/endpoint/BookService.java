package pl.csanecki.bookapp.book.endpoint;

import pl.csanecki.bookapp.book.endpoint.model.Book;

public interface BookService {
    Book findBookById(long bookId);
}
