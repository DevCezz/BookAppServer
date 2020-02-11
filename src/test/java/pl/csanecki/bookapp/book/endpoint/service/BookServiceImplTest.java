package pl.csanecki.bookapp.book.endpoint.service;

import io.vavr.collection.List;
import org.junit.jupiter.api.Test;
import pl.csanecki.bookapp.book.endpoint.BookService;
import pl.csanecki.bookapp.book.endpoint.model.Book;
import pl.csanecki.bookapp.book.endpoint.model.NewBook;

import static org.junit.jupiter.api.Assertions.*;

class BookServiceImplTest {

    @Test
    void shouldReturnEmptyList() {
        // given
        final BookService bookService = new BookServiceImpl();

        // when
        final List<Book> books = bookService.getBooks();

        // then
        assertTrue(books.isEmpty());
    }

    @Test
    void shouldReturnIdOfBookWhenNewBookIsAdded() {
        // given
        final BookService bookService = new BookServiceImpl();

        // when
        final Book created = bookService.addBook(new NewBook("Harry Potter i Komnata Tajemnic", "J.K. Rownling",
                "Media Rodzina", "2008", 368));

        // then
        assertNotNull(created);
    }

    @Test
    void shouldReturnAddedBookWhenAdded() {
        // given
        final BookService bookService = new BookServiceImpl();
        final Book created = bookService.addBook(new NewBook("Harry Potter i Komnata Tajemnic", "J.K. Rownling",
                "Media Rodzina", "2008", 368));

        // when
        final List<Book> all = bookService.getBooks();

        // then
        assertEquals(created, all.head());
    }

    @Test
    void shouldReturnedBookHasTheSameFieldsValuesAsNewBook() {
        // given
        final BookService bookService = new BookServiceImpl();
        final NewBook newBook = new NewBook("Harry Potter i Komnata Tajemnic", "J.K. Rownling",
                "Media Rodzina", "2008", 368);

        // when
        final Book created = bookService.addBook(newBook);

        // then
        assertEquals(newBook.title, created.title);
        assertEquals(newBook.author, created.author);
        assertEquals(newBook.publisher, created.publisher);
        assertEquals(newBook.publicationYear, created.publicationYear);
        assertEquals(newBook.numberOfPages, created.numberOfPages);
    }

    @Test
    void shouldReturnAddedBookWhichHasIdAndThereAreTwoBooks() {
        // given
        final BookService bookService = new BookServiceImpl();

        // when
        final Book firstCreated = bookService.addBook(new NewBook("Harry Potter i Komnata Tajemnic", "J.K. Rownling",
                "Media Rodzina", "2008", 368));
        final Book secondCreated = bookService.addBook(new NewBook("Wiedźmin: Ostatnie życzenie", "Andrzej Sapkowski",
                "SuperNowa", "2014", 332));

        // then
        assertNotEquals(firstCreated.id, secondCreated.id);
        assertEquals(2, bookService.getBooks().size());
    }
}