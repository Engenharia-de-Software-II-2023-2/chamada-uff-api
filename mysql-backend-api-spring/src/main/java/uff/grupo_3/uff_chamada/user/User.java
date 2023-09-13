package uff.grupo_3.uff_chamada.user;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;

enum UserType {
    STUDENT,
    PROFESSOR,
}

@Entity
@Data
public class User {
    
    @Id
    @SequenceGenerator(
        name = "user_sequence",
        sequenceName = "user_sequence",
        allocationSize = 1
    )

    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "user_sequence"
    )
    private int id;
    private String username;
    private String password;
    private String token;
    private UserType userType;
    private String name;
    private String email;
}
