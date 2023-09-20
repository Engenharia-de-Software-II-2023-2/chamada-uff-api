package uff.grupo_3.uff_chamada.modules.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User,Integer> {

    User findByName(String name);
    UserDetails findByUsername(String username);
}

