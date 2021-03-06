package pl.csanecki.bookapp.book.endpoint.service;

import io.vavr.collection.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.csanecki.bookapp.book.db.BookRepository;
import pl.csanecki.bookapp.book.db.BookRow;
import pl.csanecki.bookapp.book.endpoint.BookService;
import pl.csanecki.bookapp.book.endpoint.model.Book;
import pl.csanecki.bookapp.book.endpoint.model.BookForm;

import java.util.Optional;

@Transactional
@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> getAllBooks() {
        return List.ofAll(this.bookRepository.findAll())
            .map(BookRow::toBook);
    }

    @Override
    public Book addBook(BookForm bookForm) {
        BookRow createdBook = bookRepository.save(new BookRow(
                bookForm.title,
                bookForm.author,
                bookForm.publisher,
                bookForm.publicationYear,
                bookForm.numberOfPages
        ));

        return createdBook.toBook();
    }

    @Override
    public Optional<Book> changeBookData(long bookId, BookForm bookForm) {
        final Optional<BookRow> book = bookRepository.findById(bookId);

        return book.map(b -> {
                    b.updateBookData(bookForm);
                    return b.toBook();
                }
        );
    }

    @Override
    public Optional<Book> deleteBookById(long bookId) {
        Optional<BookRow> book = bookRepository.findById(bookId);

        return book.map(b -> {
           bookRepository.delete(b);
           return b.toBook();
        });
    }

    @Override
    public Optional<Book> markAsRead(long bookId) {
        Optional<BookRow> book = bookRepository.findById(bookId);

        return book.map(b -> {
            b.markAsRead();
            return b.toBook();
        });
    }

    @Override
    public Optional<Book> unmarkAsRead(long bookId) {
        Optional<BookRow> book = bookRepository.findById(bookId);

        return book.map(b -> {
            b.unmarkAsRead();
            return b.toBook();
        });
    }
}