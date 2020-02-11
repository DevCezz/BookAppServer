package pl.csanecki.bookapp.book.endpoint.response;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoBookFoundException extends RuntimeException {
    public NoBookFoundException(long bookId) {
        super("Book of id: " + bookId + " does not exist");
    }
}
