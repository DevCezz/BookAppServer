package pl.csanecki.bookapp.book.endpoint;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.csanecki.bookapp.book.endpoint.response.NoBookFoundException;
import pl.csanecki.bookapp.book.endpoint.model.Book;
import pl.csanecki.bookapp.book.endpoint.model.BookForm;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<Book> getAllBooks() {
        return bookService.getAllBooks().asJava();
    }

    @GetMapping(
            value = "/{bookId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Book getBookById(@PathVariable long bookId) {
        return bookService.getBookById(bookId)
                .orElseThrow(
                        () -> new NoBookFoundException(bookId)
                );
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Book addBook(@RequestBody BookForm bookForm) {
        return bookService.addBook(bookForm);
    }

    @PutMapping(
            value = "/{bookId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Book changeBookData(@PathVariable long bookId, @RequestBody BookForm bookForm) {
        return bookService.changeBookData(bookId, bookForm)
                .orElseThrow(
                        () -> new NoBookFoundException(bookId)
                );
    }

    @DeleteMapping(
            value = "/{bookId}"
    )
    public ResponseEntity<Long> deleteBook(@PathVariable long bookId) {
        Optional<ResponseEntity<Long>> response = bookService.deleteBookById(bookId)
                .map(b -> ResponseEntity.status(HttpStatus.OK).body(b.id));

        return response
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}