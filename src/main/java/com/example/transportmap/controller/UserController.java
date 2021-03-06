package com.example.transportmap.controller;

import com.example.transportmap.model.User;
import com.example.transportmap.service.UserService;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Validated
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    public User saveUser(@RequestBody @Valid User user) {
        return userService.saveUser(user);
    }

    @GetMapping
    public List<User> getUsers(@RequestParam(value = "name", required = false) String name){
        List<User> users = userService.getUsers();
        if(StringUtils.hasText(name)){
            return users.stream()
                    .filter(e -> e.getName().matches(name))
                    .collect(Collectors.toList());
        }
        return users;
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable("id") int id){
        return userService.getUser(id);
    }
}
