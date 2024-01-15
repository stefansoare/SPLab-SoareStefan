package ro.uvt.info.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.uvt.info.Classes.Book;
import ro.uvt.info.Classes.MyPair;
import ro.uvt.info.Services.BookStatistics;
import ro.uvt.info.Services.CommandExecutor;
import ro.uvt.info.commands.*;
import ro.uvt.info.persistence.CrudRepository;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/books")
public class BooksController {
    private final Command<List<Book>, Void> getAll;
    private final Command<Book, Long> getOne;
    private final Command<Book, Book> addOne;
    private final Command<Book, MyPair<Long, Book>> updateOne;
    private final Command<Void, Long> deleteOne;
    private final CommandExecutor commandExecutor;

    public BooksController(CrudRepository<Book, Long> repository,
                           CommandExecutor commandExecutor){

//        var repository = new JPACrudRepository<>(booksRepository);

        getAll = new GetAllCommand<Book>(repository);
        getOne = new FindOneCommand<Book>(repository);
        addOne = new AddOneCommand<Book>(repository);
        updateOne = new UpdateOneCommand<Book>(repository);
        deleteOne = new DeleteOneCommand<Book>(repository);
        this.commandExecutor = commandExecutor;
    }

    @GetMapping("/statistics")
    public ResponseEntity<?> printStatistics() {
        var books = getAll.execute();
        if(books.iterator().hasNext())
            return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
        var book = books.iterator().next();
        BookStatistics stats = new BookStatistics();
        book.accept(stats);
        return new ResponseEntity<>(stats.getStatistics(), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<?> getBooks() {
        Iterable<Book> books = commandExecutor.execute(getAll);
        return new ResponseEntity<>(books, HttpStatus.OK);
//        return new ResponseEntity<>(commandExecutor.executeAsync(getAll), HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBook(@PathVariable Long id) {
        try{
            getOne.setCommandContext(id);
            Book book = commandExecutor.execute(getOne);
            return new ResponseEntity<>(book, HttpStatus.OK);
            // return new ResponseEntity<>(commandExecutor.executeAsync(getOne), HttpStatus.ACCEPTED);
        }catch (NoSuchElementException e){
            return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    public ResponseEntity<?> addBook(@RequestBody Book book) {
        addOne.setCommandContext(book);
        Book insertedBook =  commandExecutor.execute(addOne);
        return new ResponseEntity<>(insertedBook, HttpStatus.OK);
//        return new ResponseEntity<>(commandExecutor.executeAsync(addOne), HttpStatus.ACCEPTED);

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> putBook(@PathVariable Long id, @RequestBody Book book) {
        MyPair<Long,Book> pair = new MyPair<>(id, book);
        updateOne.setCommandContext(pair);
        Book updatedBook = commandExecutor.execute(updateOne);
        return new ResponseEntity<>(updatedBook, HttpStatus.OK);
//        return new ResponseEntity<>(commandExecutor.executeAsync(updateOne), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Long id) {
        deleteOne.setCommandContext(id);
        commandExecutor.execute(deleteOne);
        return new ResponseEntity<>("Removed!", HttpStatus.OK);
//        return new ResponseEntity<>(commandExecutor.executeAsync(deleteOne), HttpStatus.ACCEPTED);
    }

    @GetMapping("/async/{opId}")
    public ResponseEntity<?> getAsyncResult(@PathVariable String opId){
        return new ResponseEntity<>(commandExecutor.getAsyncResult(opId), HttpStatus.OK);
    }


}