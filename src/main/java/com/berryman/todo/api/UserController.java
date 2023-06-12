package com.berryman.todo.api;

import com.berryman.todo.model.User;
import com.berryman.todo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("users")
public class UserController {

    private UserRepository userRepository;

    @Autowired
    public UserController(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

//    @GetMapping("/find/{id}")
//    public User getUser

    @PostMapping("/add")
    public User addUser(final User user) {
        return userRepository.save(user);
    }
}
