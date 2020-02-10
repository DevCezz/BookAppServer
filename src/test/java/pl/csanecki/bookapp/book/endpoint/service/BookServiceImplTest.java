package pl.csanecki.bookapp.book.endpoint.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pl.csanecki.bookapp.book.endpoint.BookService;
import pl.csanecki.bookapp.book.endpoint.model.Book;

import static org.assertj.core.api.Assertions.assertThat;

class BookServiceImplTest {

    private BookService bookService;

    @BeforeAll
    void setUp() {
        bookService = new BookServiceImpl();
    }

    @Test
    void shouldReturnBookWhenItIsFound() {
        // given
        long bookId = 34L;

        // when
        Book foundBook = bookService.findBookById(bookId);

        // then
        assertThat(foundBook).isNotNull();
    }

}