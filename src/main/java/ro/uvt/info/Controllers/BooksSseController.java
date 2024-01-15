package ro.uvt.info.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import ro.uvt.info.Services.AllBooksSubject;
import ro.uvt.info.Services.Observer;
import ro.uvt.info.Services.SseObserver;

@RestController
public class BooksSseController {


    private final AllBooksSubject allBooksSubject;

    public BooksSseController(AllBooksSubject allBooksSubject) {
        this.allBooksSubject = allBooksSubject;
    }

    @RequestMapping("/books-sse")
    public ResponseBodyEmitter getBooksSse() {
        final SseEmitter emitter = new SseEmitter(0L);
        Observer observer = new SseObserver(emitter);
        allBooksSubject.attach(observer);
        return emitter;
    }
}