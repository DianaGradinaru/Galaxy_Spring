package com.example.server.registration;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/login")
public class LoginController {

    @CrossOrigin(origins = "http://localhost:8080/login")
    @GetMapping(path = "/login")
    public String login() {
        return "logged in";
    }
}
