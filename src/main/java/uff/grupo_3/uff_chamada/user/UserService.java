package uff.grupo_3.uff_chamada.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// TODO: botar validacoes adicionais no CRUD

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User getUserById(int id){
        return this.userRepository.findById(id).orElseThrow(() -> new IllegalStateException("usuario com id " + id + " não existe"));
    }

    public void createUser(User user){
        this.userRepository.save(user);
    }

    public void updateUser(User user){
        this.userRepository.save(user);
    }

    public void deleteUser(int id){
        this.userRepository.deleteById(id);
    }
}
