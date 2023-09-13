package uff.grupo_3.uff_chamada.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/getUserById/{id}")
    public User getUserById(@PathVariable("id") int id){
        return this.userService.getUserById(id);
    }

    @PostMapping("/createUser")
    public void createUser(@RequestBody User user){
        this.userService.createUser(user);
    }

    @PutMapping("/updateUser")
    public void updateUser(@RequestBody User user){
        this.userService.updateUser(user);
    }

    @DeleteMapping("/deleteUser/{id}")
    public void deleteUser(@PathVariable("id") int id){
        this.userService.deleteUser(id);
    }
}
