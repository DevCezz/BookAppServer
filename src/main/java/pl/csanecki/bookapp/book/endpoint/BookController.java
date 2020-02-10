package pl.csanecki.bookapp.book.endpoint;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.csanecki.bookapp.book.endpoint.model.Book;

@RestController
@RequestMapping("/books")
public class BookController {

    @GetMapping(
            value = "/{bookId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Book getBook(@PathVariable long bookId) {
        return new Book(1, "Harry Potter i Komnata Tajemnic", "J.K. Rowling", "Media Rodzina", "2016", 358);
    }
}
