package eval.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccueilController {

    @GetMapping("/")
    public String home() {
        return "Application is working!";
    }
}