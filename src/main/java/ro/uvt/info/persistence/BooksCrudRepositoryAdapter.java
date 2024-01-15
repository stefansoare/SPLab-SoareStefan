package ro.uvt.info.persistence;

import org.springframework.stereotype.Repository;
import ro.uvt.info.Classes.Book;

import java.util.List;
import java.util.Optional;

@Repository
public class BooksCrudRepositoryAdapter implements BooksCrudRepository {

    private final BookJpaRepository bookJpaRepository;

    public BooksCrudRepositoryAdapter(BookJpaRepository bookJpaRepository) {
        this.bookJpaRepository = bookJpaRepository;
    }

    @Override
    public List<Book> findAll() {
        return bookJpaRepository.findAll();
    }

    @Override
    public Book findById(Long id) {
        return bookJpaRepository.findById(id).orElse(null);
    }

    @Override
    public Book save(Book book) {
        return bookJpaRepository.save(book);
    }

    @Override
    public void deleteById(Long id) {
        bookJpaRepository.deleteById(id);
    }

    @Override
    public Book update(Long id, Book updatingBook) {

        Optional<Book> foundBook =  bookJpaRepository.findById(id);
        if(foundBook.isPresent()){
            foundBook.get().setTitle(updatingBook.getTitle());
            foundBook.get().setAuthorList(updatingBook.getAuthorList());
            foundBook.get().setElementList(updatingBook.getElementList());
            bookJpaRepository.save(foundBook.get());
        }
        return foundBook.orElse(null);
    }
}