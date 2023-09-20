package uff.grupo_3.uff_chamada.auth;

import lombok.Data;
import uff.grupo_3.uff_chamada.modules.user.UserRole;

@Data
public class RegisterRequest {
    private String username;
    private String password;
    private UserRole role;
}
