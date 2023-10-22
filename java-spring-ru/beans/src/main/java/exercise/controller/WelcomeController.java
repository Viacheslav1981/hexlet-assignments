package exercise.controller;

import exercise.daytime.Daytime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class WelcomeController {

    @Autowired
    Daytime daytime;

    @GetMapping(path = "/welcome")
    public String welcome() {
        return "It is " + daytime.getName() + "! Welcome to Spring";

    }
}
