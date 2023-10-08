package net.sschwarzbaer.java.tools.rick_and_morty_viewer_backend;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hello")
public class HelloController {
    
    @GetMapping
    public String sayHello() {
        return "Hello";
    }

}
