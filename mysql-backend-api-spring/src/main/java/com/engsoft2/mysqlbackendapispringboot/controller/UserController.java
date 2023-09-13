package com.engsoft2.mysqlbackendapispringboot.controller;

import com.engsoft2.mysqlbackendapispringboot.entity.User;
import com.engsoft2.mysqlbackendapispringboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public User addUser(@RequestBody User user){
        return userService.addUser(user);
    }

    @GetMapping("/id/{id}")
    public User getUserById(@PathVariable int id){
        return userService.getUserById(id);
    }

    @GetMapping("/name/{name}")
    public User getUserByName(@PathVariable String name){
        return userService.getUserByName(name);
    }

    @GetMapping()
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUserById(@PathVariable int id){
        return userService.deleteUserById(id);
    }

    @PutMapping("/update")
    public User updateUser(@RequestBody User user){
        return userService.updateUser(user);
    }

}
