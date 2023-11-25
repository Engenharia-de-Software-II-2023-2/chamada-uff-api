package uff.grupo_3.uff_chamada.modules.user;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User,Integer> {

    Optional<List<User>> findByName(String name);
    UserDetails findByUsername(String username);
    
    @Query(value = "SELECT * FROM USER WHERE USERNAME = :username", nativeQuery = true)
    User findUserByUsername(@Param("username") String username);
}

