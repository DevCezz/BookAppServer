package pl.csanecki.bookapp.book.endpoint.service;

import io.vavr.collection.List;
import org.springframework.stereotype.Service;
import pl.csanecki.bookapp.book.db.BookRepository;
import pl.csanecki.bookapp.book.db.BookRow;
import pl.csanecki.bookapp.book.endpoint.BookService;
import pl.csanecki.bookapp.book.endpoint.model.Book;
import pl.csanecki.bookapp.book.endpoint.model.NewBook;

import java.util.function.Function;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> getBooks() {
        return List.ofAll(this.bookRepository.findAll())
            .map(mapBookRowToBookFunction());
    }

    @Override
    public Book addBook(NewBook newBook) {
        BookRow createdBook = bookRepository.save(new BookRow(
                newBook.title,
                newBook.author,
                newBook.publisher,
                newBook.publicationYear,
                newBook.numberOfPages
        ));

        return mapBookRowToBookFunction().apply(createdBook);
    }

    private Function<BookRow, Book> mapBookRowToBookFunction() {
        return dbObj ->
                new Book(
                        dbObj.getId(),
                        dbObj.getTitle(),
                        dbObj.getAuthor(),
                        dbObj.getPublisher(),
                        dbObj.getPublicationYear(),
                        dbObj.getNumberOfPages());
    }
}