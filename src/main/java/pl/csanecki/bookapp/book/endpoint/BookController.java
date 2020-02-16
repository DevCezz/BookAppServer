package pl.csanecki.bookapp.book.endpoint;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.csanecki.bookapp.book.endpoint.model.Book;
import pl.csanecki.bookapp.book.endpoint.model.BookForm;
import pl.csanecki.bookapp.book.endpoint.response.NoBookFoundException;

import java.util.List;

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
        return bookService.deleteBookById(bookId)
                .map(b -> ResponseEntity.status(HttpStatus.OK).body(b.id))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}