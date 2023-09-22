package uff.grupo_3.uff_chamada.auth.dto.response;

import lombok.Data;

@Data
public class LoginResponse {
    private String token;

    public LoginResponse(String token){
        this.token = token;
    }
}
