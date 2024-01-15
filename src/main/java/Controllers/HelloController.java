package Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import difexamples.ClientComponent;

@RestController
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class HelloController {

    private final ClientComponent clientComponent;
    private final ClientComponent clientComponent2;


    @GetMapping("/")
    public String hello() {
//        return "Hello from Spring Boot";
        String helloMessage = "Hello from ClientComponent = %s\n%s".formatted(clientComponent, clientComponent2);
        return helloMessage;
    }
}
