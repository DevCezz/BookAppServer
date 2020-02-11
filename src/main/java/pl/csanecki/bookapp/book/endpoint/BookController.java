package pl.csanecki.bookapp.book.endpoint;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.csanecki.bookapp.book.endpoint.model.Book;
import pl.csanecki.bookapp.book.endpoint.model.NewBook;

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
    public List<Book> getBook() {
        return bookService.getBooks().asJava();
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Book addBook(@RequestBody NewBook newBook) {
        return bookService.addBook(newBook);
    }
}