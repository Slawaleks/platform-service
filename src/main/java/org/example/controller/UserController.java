package org.example.controller;

import org.example.handler.UserHandler;
import org.example.model.User;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserHandler userHandler = new UserHandler();


    @PostMapping("/")
    public long createUser(@RequestBody User user) throws SQLException {
        return userHandler.createUser(user);
    }

    @PutMapping("/{id}")
    public void updateUser(@RequestBody User user, @PathVariable Long id) throws SQLException {
        userHandler.updateUser(user, id);
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) throws SQLException {
        return userHandler.getUser(id);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) throws SQLException {
        userHandler.deleteUser(id);
    }
}
