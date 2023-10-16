package uff.grupo_3.uff_chamada.modules.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User,Integer> {

    User findByName(String name);
    UserDetails findByUsername(String username);
    
    @Query(value = "SELECT * FROM user WHERE USERNAME = :username", nativeQuery = true)
    User findUserByUsername(@Param("username") String username);
}

