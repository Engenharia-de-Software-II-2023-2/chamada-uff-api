package com.engsoft2.mysqlbackendapispringboot.service;

import com.engsoft2.mysqlbackendapispringboot.entity.User;
import com.engsoft2.mysqlbackendapispringboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User addUser(User user){
        return userRepository.save(user);
    }

    public User getUserById(int id){
        return userRepository.findById(id).orElse(null);
    }

    public User getUserByName(String name){
        return userRepository.findByName(name);
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public String deleteUserById(int id){
        userRepository.deleteById(id);
        return "User with id = " + id + " removed!";
    }

    public User updateUser(User user){
        User existingUser = userRepository.findById(user.getId()).orElse(null);
        existingUser.setUsername(user.getUsername());
        existingUser.setPassword(user.getPassword());
        existingUser.setToken(user.getToken());
        existingUser.setType(user.getType());
        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        return userRepository.save(existingUser);
    }

}
