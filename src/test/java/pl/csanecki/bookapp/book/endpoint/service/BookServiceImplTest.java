package pl.csanecki.bookapp.book.endpoint.service;

import io.vavr.collection.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import pl.csanecki.bookapp.book.db.BookRepository;
import pl.csanecki.bookapp.book.endpoint.BookService;
import pl.csanecki.bookapp.book.endpoint.model.Book;
import pl.csanecki.bookapp.book.endpoint.model.BookForm;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class BookServiceImplTest {

    private BookService bookService;

    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    void setUp() {
        bookService = new BookServiceImpl(bookRepository);
    }

    @AfterEach
    void cleanAfterTest() {
        bookRepository.deleteAll();
    }

    @Test
    void shouldReturnEmptyList() {
        // when
        final List<Book> books = bookService.getAllBooks();

        // then
        assertTrue(books.isEmpty());
    }

    @Test
    void shouldReturnIdOfBookWhenNewBookIsAdded() {
        // when
        final Book created = bookService.addBook(new BookForm("Harry Potter i Komnata Tajemnic", "J.K. Rownling",
                "Media Rodzina", "2008", 368));

        // then
        assertNotNull(created);
    }

    @Test
    void shouldReturnAddedBookWhenAdded() {
        // given
        final Book created = bookService.addBook(new BookForm("Harry Potter i Komnata Tajemnic", "J.K. Rownling",
                "Media Rodzina", "2008", 368));

        // when
        final List<Book> all = bookService.getAllBooks();

        // then
        assertEquals(created, all.head());
    }

    @Test
    void shouldReturnedBookHasTheSameFieldsValuesAsNewBook() {
        // given
        final BookForm bookForm = new BookForm("Harry Potter i Komnata Tajemnic", "J.K. Rownling",
                "Media Rodzina", "2008", 368);

        // when
        final Book created = bookService.addBook(bookForm);

        // then
        assertEquals(bookForm.title, created.title);
        assertEquals(bookForm.author, created.author);
        assertEquals(bookForm.publisher, created.publisher);
        assertEquals(bookForm.publicationYear, created.publicationYear);
        assertEquals(bookForm.numberOfPages, created.numberOfPages);
    }

    @Test
    void shouldReturnAddedBookWhichHasIdAndThereAreTwoBooks() {
        // when
        final Book firstCreated = bookService.addBook(new BookForm("Harry Potter i Komnata Tajemnic", "J.K. Rownling",
                "Media Rodzina", "2008", 368));
        final Book secondCreated = bookService.addBook(new BookForm("Wiedźmin: Ostatnie życzenie", "Andrzej Sapkowski",
                "SuperNowa", "2014", 332));

        // then
        assertNotEquals(firstCreated.id, secondCreated.id);
        assertEquals(2, bookService.getAllBooks().size());
    }

    @Test
    void shouldReturnBookById() {
        // given
        final Book created = bookService.addBook(new BookForm("Harry Potter i Komnata Tajemnic", "J.K. Rownling",
                "Media Rodzina", "2008", 368));

        // when
        final Optional<Book> searched = bookService.getBookById(created.id);

        if(searched.isEmpty()) {
            fail();
        }

        // then
        assertEquals(created.title, searched.get().title);
        assertEquals(created.author, searched.get().author);
        assertEquals(created.publisher, searched.get().publisher);
        assertEquals(created.publicationYear, searched.get().publicationYear);
        assertEquals(created.numberOfPages, searched.get().numberOfPages);
    }

    @Test
    void shouldChangeBookData() {
        // given
        final Book created = bookService.addBook(new BookForm("Harry Potter i Komnata Tajemnic", "J.K. Rownling",
                "Media Rodzina", "2008", 368));
        final BookForm editedData = new BookForm("Wiedźmin: Ostatnie życzenie","Andrzej Sapkowski",
                "SuperNowa", "2014", 332);

        // when
        final Optional<Book> edited = bookService.changeBookData(created.id, editedData);

        if(edited.isEmpty()) {
            fail();
        }

        final Optional<Book> checkDbBook = bookService.getBookById(edited.get().id);

        if(checkDbBook.isEmpty()) {
            fail();
        }

        // then
        assertEquals(editedData.title, checkDbBook.get().title);
        assertEquals(editedData.author, checkDbBook.get().author);
        assertEquals(editedData.publisher, checkDbBook.get().publisher);
        assertEquals(editedData.publicationYear, checkDbBook.get().publicationYear);
        assertEquals(editedData.numberOfPages, checkDbBook.get().numberOfPages);
    }

    @Test
    void shouldDeactivateBook() {
        // given
        final Book created = bookService.addBook(new BookForm("Harry Potter i Komnata Tajemnic", "J.K. Rownling",
                "Media Rodzina", "2008", 368));

        // when
        final Optional<Book> deactivated = bookService.deactivateBook(created.id);

        if(deactivated.isEmpty()) {
            fail();
        }

        final Optional<Book> checkDbBook = bookService.getBookById(deactivated.get().id);

        if(checkDbBook.isEmpty()) {
            fail();
        }

        // then
        assertTrue(checkDbBook.get().deactivated);
    }

    @Test
    void shouldActivateDeactivatedBook() {
        // given
        final Book created = bookService.addBook(new BookForm("Harry Potter i Komnata Tajemnic", "J.K. Rownling",
                "Media Rodzina", "2008", 368));
        bookService.deactivateBook(created.id);

        // when
        final Optional<Book> activated = bookService.activateBook(created.id);

        if(activated.isEmpty()) {
            fail();
        }

        final Optional<Book> checkDbBook = bookService.getBookById(activated.get().id);

        if(checkDbBook.isEmpty()) {
            fail();
        }

        // then
        assertFalse(checkDbBook.get().deactivated);
    }

    @Test
    void shouldDeleteAddedBook() {
        // given
        final Book created = bookService.addBook(new BookForm("Harry Potter i Komnata Tajemnic", "J.K. Rownling",
                "Media Rodzina", "2008", 368));

        // when
        final Optional<Book> deleted = bookService.deleteBookById(created.id);

        if(deleted.isEmpty()) {
            fail();
        }

        int numberOfBooks = bookService.getAllBooks().size();

        // then
        assertEquals(created.id, deleted.get().id);
        assertEquals(0, numberOfBooks);
    }

    @Test
    void shouldDeleteDeactivatedBooks() {
        // given
        final Book firstCreated = bookService.addBook(new BookForm("Harry Potter i Komnata Tajemnic", "J.K. Rownling",
                "Media Rodzina", "2008", 368));
        final Book secondCreated = bookService.addBook(new BookForm("Wiedźmin: Ostatnie życzenie","Andrzej Sapkowski",
                "SuperNowa", "2014", 332));

        bookService.deactivateBook(firstCreated.id);

        // when
        bookService.deleteAllDeactivatedBooks();

        // then
        assertEquals(1, bookService.getAllBooks().size());
    }
}