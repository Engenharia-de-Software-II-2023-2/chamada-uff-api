package uff.grupo_3.uff_chamada.auth.dto.response;

import lombok.Data;
import uff.grupo_3.uff_chamada.modules.user.UserRole;

@Data
public class LoginResponse {
    
    private String token;
    private int id;
    private UserRole role;

    public LoginResponse(String token, int id, UserRole role){
        this.token = token;
        this.id = id;
        this.role = role;
    }
}
