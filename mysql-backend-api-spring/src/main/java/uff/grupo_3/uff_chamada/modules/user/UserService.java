package uff.grupo_3.uff_chamada.modules.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public List<User> getUserByName(String name){
        return userRepository.findByName(name).orElseGet(() -> new ArrayList<>());
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
        existingUser.setRole(user.getRole());
        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        return userRepository.save(existingUser);
    }

}