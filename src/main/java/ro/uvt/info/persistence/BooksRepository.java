package ro.uvt.info.persistence;

import org.springframework.stereotype.Component;
import ro.uvt.info.Classes.Book;

import java.util.List;

@Component
public class BooksRepository {
    private final BooksCrudRepository booksCrudRepository;

    public BooksRepository(BooksCrudRepository booksCrudRepository) {
        this.booksCrudRepository = booksCrudRepository;
    }

    public List<Book> findAllBooks() {
        return booksCrudRepository.findAll();
    }

    public Book findBookById(Long id) {
        return booksCrudRepository.findById(id);
    }

    public Book saveBook(Book book) {
        return booksCrudRepository.save(book);
    }

    public void deleteBookById(Long id) {
        booksCrudRepository.deleteById(id);
    }
}