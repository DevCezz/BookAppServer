package pl.csanecki.bookapp.book.endpoint.service;

import io.vavr.collection.List;
import pl.csanecki.bookapp.book.endpoint.BookService;
import pl.csanecki.bookapp.book.endpoint.model.Book;

public class BookServiceImpl implements BookService {
    List<Book> books = List.of(
            new Book(1, "Harry Potter i Komnata Tajemnic", "J.K. Rowling", "Media Rodzina", "2016", 358),
            new Book(2, "Władca Pierścieni", "J.R.R. Tolkien", " Wydawnictwo MUZA S.A.", "2012", 1280),
            new Book(3, "Gra o tron. Pieśń Lodu i Ognia.", "Martin George R. R. ", "Wydawnictwo Zysk i S-ka ", "2011", 844)
    );

    @Override
    public Book findBookById(long bookId) {
        return null;
    }
}
