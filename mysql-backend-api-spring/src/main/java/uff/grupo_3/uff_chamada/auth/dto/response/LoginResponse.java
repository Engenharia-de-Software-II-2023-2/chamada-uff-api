package uff.grupo_3.uff_chamada.auth.dto.response;

import lombok.Data;

@Data
public class LoginResponse {
    private String token;
    private int userId;

    public LoginResponse(int userId, String token){
        this.userId = userId;
        this.token = token;
    }
}
