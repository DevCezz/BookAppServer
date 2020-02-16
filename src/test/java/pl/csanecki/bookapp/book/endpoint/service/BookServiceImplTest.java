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
    void shouldReturnedBookNotBeNullWhenItIsAdded() {
        // when
        final Book created = bookService.addBook(new BookForm("Harry Potter i Komnata Tajemnic", "J.K. Rownling",
                "Media Rodzina", "2008", 368));

        // then
        assertNotNull(created);
    }

    @Test
    void shouldReturnAddedBookWhenItIsAdded() {
        // given
        final Book created = bookService.addBook(new BookForm("Harry Potter i Komnata Tajemnic", "J.K. Rownling",
                "Media Rodzina", "2008", 368));

        // when
        final List<Book> all = bookService.getAllBooks();

        // then
        assertEquals(created, all.head());
    }

    @Test
    void shouldReturnedBookHasTheSameFieldsValuesAsNewBookForm() {
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
    void shouldReturnAddedBooksWhichHaveDifferentIdsAndSizeIsTwo() {
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
    void shouldChangeBookData() {
        // given
        final Book created = bookService.addBook(new BookForm("Harry Potter i Komnata Tajemnic", "J.K. Rownling",
                "Media Rodzina", "2008", 368));
        final BookForm editedData = new BookForm("Wiedźmin: Ostatnie życzenie","Andrzej Sapkowski",
                "SuperNowa", "2014", 332);

        // when
        bookService.changeBookData(created.id, editedData);

        final List<Book> all = bookService.getAllBooks();

        // then
        assertEquals(editedData.title, all.head().title);
        assertEquals(editedData.author, all.head().author);
        assertEquals(editedData.publisher, all.head().publisher);
        assertEquals(editedData.publicationYear, all.head().publicationYear);
        assertEquals(editedData.numberOfPages, all.head().numberOfPages);
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
}