package Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import Controllers.*;
import Classes.Book;
import Classes.MyPair;
import Services.BookRepository;
import Services.BookStatistics;
import Services.CommandExecutor;
import Services.commands.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BooksController {
    private final Command<List<Book>, Void> getAll;
    private final Command<Book, String> getOne;
    private final Command<Void, Book> addOne;
    private final Command<Void, MyPair<String,Book>> updateOne;
    private final Command<Void, String> deleteOne;
    private final Command<String, Object> saveToJson;
    private final CommandExecutor commandExecutor;

    public BooksController(SaveToJsonCommand saveToJsonCmd, BookRepository bookRepository,
                           CommandExecutor commandExecutor){
        saveToJson = saveToJsonCmd;
        getAll = new GetAllCommand<Book>(bookRepository);
        getOne = new FindOneCommand<Book>(bookRepository);
        addOne = new AddOneCommand<Book>(bookRepository);
        updateOne = new UpdateOneCommand<Book>(bookRepository);
        deleteOne = new DeleteOneCommand<Book>(bookRepository);
        this.commandExecutor = commandExecutor;
    }

    @GetMapping("/statistics")
    public ResponseEntity<?> printStatistics() {
        BookRepository bookRepository = new BookRepository();
        var book = bookRepository.getAll().get(0);
        BookStatistics stats = new BookStatistics();
        book.accept(stats);
        return new ResponseEntity<>(stats.getStatistics(), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<?> getBooks() {
//        List<Book> books = commandExecutor.execute(getAll);
//        saveToJson.setCommandContext(books);
//        return new ResponseEntity<>(commandExecutor.execute(saveToJson), HttpStatus.OK);
        return new ResponseEntity<>(commandExecutor.executeAsync(getAll), HttpStatus.ACCEPTED);

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBook(@PathVariable String id) {
//        getOne.setCommandContext(id);
//        Book book = commandExecutor.execute(getOne);
//        saveToJson.setCommandContext(book);
//        return new ResponseEntity<>(commandExecutor.execute(saveToJson), HttpStatus.OK);
        getOne.setCommandContext(id);
        return new ResponseEntity<>(commandExecutor.executeAsync(getOne), HttpStatus.ACCEPTED);
    }

    @PostMapping("")
    public ResponseEntity<?> addBook(@RequestBody Book book) {
//        addOne.setCommandContext(book);
//        commandExecutor.execute(addOne);
//        return new ResponseEntity<>("Added!", HttpStatus.OK);
        addOne.setCommandContext(book);
        return new ResponseEntity<>(commandExecutor.executeAsync(addOne), HttpStatus.ACCEPTED);

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> putBook(@PathVariable String id, @RequestBody Book book) {
//        MyPair<String,Book> pair = new MyPair<String, Book>(id, book);
//        updateOne.setCommandContext(pair);
//        commandExecutor.execute(updateOne);
//        return new ResponseEntity<>("Updated!", HttpStatus.OK);
        MyPair<String,Book> pair = new MyPair<>(id, book);
        updateOne.setCommandContext(pair);
        return new ResponseEntity<>(commandExecutor.executeAsync(updateOne), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable String id) {
//        deleteOne.setCommandContext(id);
//        commandExecutor.execute(deleteOne);
//        return new ResponseEntity<>("Removed!", HttpStatus.OK);
        deleteOne.setCommandContext(id);
        return new ResponseEntity<>(commandExecutor.executeAsync(deleteOne), HttpStatus.ACCEPTED);
    }

    @GetMapping("/async/{opId}")
    public ResponseEntity<?> getAsyncResult(@PathVariable String opId){
        return new ResponseEntity<>( commandExecutor.getAsyncResult(opId), HttpStatus.OK);
    }


}