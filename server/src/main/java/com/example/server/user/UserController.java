package com.example.server.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

//    @PostMapping
//    public void addUser(@RequestBody UserModelDTO userModelDTO) {
//        userService.addUser(userModelDTO);
//    }
//
//    @GetMapping
//    public List<UserModelDTO> getAllUsers() {
//        return userService.findAll();
//
//    }
}
