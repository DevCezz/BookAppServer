package pl.csanecki.bookapp.book.endpoint.service;

import io.vavr.collection.List;
import pl.csanecki.bookapp.book.endpoint.BookService;
import pl.csanecki.bookapp.book.endpoint.model.Book;
import pl.csanecki.bookapp.book.endpoint.model.NewBook;

public class BookServiceImpl implements BookService {

    private List<Book> books = List.empty();

    @Override
    public List<Book> getBooks() {
        return books;
    }

    @Override
    public Book addBook(NewBook newBook) {
        Book created = new Book(books.size() + 1, newBook.title, newBook.author, newBook.publisher, newBook.publicationYear, newBook.numberOfPages);
        books = books.prepend(created);
        return created;
    }
}